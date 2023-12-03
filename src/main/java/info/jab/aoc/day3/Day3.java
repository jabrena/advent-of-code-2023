package info.jab.aoc.day3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import info.jab.aoc.Day;
import info.jab.aoc.Utils;

/**
 * Solution for AOC 2023, Day 3
 * https://adventofcode.com/2023/day/3
 *
 */
class Day3  implements Day<Long> {

    Function<List<String>, Set<String>> findDifferentSymbols = param -> {
        var nonSymbols = List.of("0","1","2","3","4","5","6","7","8","9",".");
        Set<String> symbols = new HashSet<>();

        param.stream()
            .forEach(list -> {
                var symbolsInLine = list.chars()
                    .mapToObj(c -> String.valueOf((char) c))
                    .filter(ch -> nonSymbols.stream().noneMatch(s -> s.equals(ch)))
                    .distinct()
                    .collect(Collectors.toSet());

                symbols.addAll(symbolsInLine);
            });
        return symbols;
    };

    record MatrixDimension(int x, int y) {};

    Function<List<String>, MatrixDimension> getMatrixDimension = param -> {
        var x = param.get(0).length();
        var y = param.size();
        return new MatrixDimension(x,y);
    };

    Function<List<String>, String[][]> createMatrix = param -> {
        MatrixDimension matrixDimension = getMatrixDimension.apply(param);
        System.out.println(matrixDimension);

        // Creating a 2D array with dimensions rows x cols
        String[][] matrix = new String[matrixDimension.y()][matrixDimension.x()];

        // Initializing elements in the 2D array
        for (int i = 0; i < matrixDimension.y(); i++) {
            for (int j = 0; j < matrixDimension.x(); j++) {
                matrix[i][j] = "" + param.get(i).charAt(j);
            }
        }

        /*
        // Printing the 2D array
        for (int i = 0; i < matrixDimension.y(); i++) {
            for (int j = 0; j < matrixDimension.x(); j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        */

        return matrix;
    };

    record Symbol(String symbol, int x, int y) {
        int[][] getAdjacents() {
            return new int[][] {
                    {x - 1, y - 1},
                    {x, y - 1},
                    {x + 1, y - 1},
                    {x - 1, y},
                    {x + 1, y},
                    {x - 1, y + 1},
                    {x, y + 1},
                    {x + 1, y + 1}
            };
        }
    }

    //TODO Improve the scope
    private static Set<String> symbols;

    private int findNumber(String[][] grid, int x, int y) {
        // go all the way left until we hit a ., x=0, or a symbol knowing we are past the left end of the number
        while (x >= 0 && grid[y][x].charAt(0) != '.' && !symbols.contains(grid[y][x])) {
            x--;
        }

        // go back to the first digit of the number
        x++;

        StringBuilder number = new StringBuilder();

        // start moving right to read the entire number, stopping at a . or if we hit the right end of the grid
        while (x < grid[y].length && grid[y][x].charAt(0) != '.' && !symbols.contains(grid[y][x])) {
            number.append(grid[y][x]);
            x++;
        }

        return Integer.parseInt(number.toString());
    }

    private Set<Integer> findNumbersAdjacentTo(String[][] grid, Symbol symbol) {
        int[][] directions = symbol.getAdjacents();

        Set<Integer> numbers = new HashSet<>();

        for (int[] direction : directions) {
            int x = direction[0];
            int y = direction[1];

            String atPosition = grid[y][x];
            if (Character.isDigit(atPosition.charAt(0))) {
                numbers.add(findNumber(grid, x, y));
            }
        }

        return numbers;
    }

    @Override
    public Long getPart1Result(String fileName) {

        var lines = Utils.readFileToList(fileName);
        symbols = findDifferentSymbols.apply(lines);
        System.out.println("Combined Set: " + symbols);

        String[][] matrix = createMatrix.apply(lines);
        int rows = matrix.length;
        int cols = matrix[0].length;

        String[][] grid = matrix;
        List<Symbol> symbols2 = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String element = matrix[i][j];
                if(symbols.contains(element)) {
                    //System.out.println("x:" + j + " y:" + i + " = " + element);

                    //Find adjacents
                    int symbolX = j;
                    int symbolY = i;

                    symbols2.add(new Symbol(element, symbolX, symbolY));
                }
            }
        }

        var result = String.valueOf(symbols2.stream()
                .flatMap(sym -> findNumbersAdjacentTo(grid, sym).stream())
                .reduce(0, Integer::sum));

        return Long.valueOf(result);
    }

    @Override
    public Long getPart2Result(String fileName) {

        var lines = Utils.readFileToList(fileName);
        symbols = findDifferentSymbols.apply(lines);
        System.out.println("Combined Set: " + symbols);

        String[][] matrix = createMatrix.apply(lines);
        int rows = matrix.length;
        int cols = matrix[0].length;

        String[][] grid = matrix;
        List<Symbol> symbols2 = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String element = matrix[i][j];
                if(symbols.contains(element)) {
                    //System.out.println("x:" + j + " y:" + i + " = " + element);

                    //Find adjacents
                    int symbolX = j;
                    int symbolY = i;

                    symbols2.add(new Symbol(element, symbolX, symbolY));
                }
            }
        }

        List<Symbol> gears = symbols2.stream().filter(symbol -> symbol.symbol.equals("*")).toList();

        var result = String.valueOf(gears.stream()
                .map(symbol -> findNumbersAdjacentTo(grid, symbol))
                .filter(set -> set.size() == 2)
                .map(set -> {
                    List<Integer> nums = set.stream().toList();
                    return nums.get(0) * nums.get(1);
                })
                .reduce(0, Integer::sum));

        return Long.valueOf(result);
    }

}
