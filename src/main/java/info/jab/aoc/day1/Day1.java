package info.jab.aoc.day1;

import info.jab.aoc.Day;
import info.jab.aoc.Utils;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Solution for AOC 2023, Day 1
 * https://adventofcode.com/2023/day/1
 *
 */
public class Day1 implements Day<Long> {

    // @formatter:off

    Function<String, List<Character>> toCharList = param -> {
        return param.chars()
            .mapToObj(c -> (char) c)
            .toList();
    };

    Function<List<Character>, List<Long>> toListOfDigits = param -> {
        return param.stream()
            .filter(c -> Character.isDigit(c))
            .map(String::valueOf)
            .map(Long::valueOf)
            .toList();
    };

    Function<List<Long>, Long> applyRules = param -> {
        String result = switch (param.size()) {
            case 1 -> String.valueOf(param.getFirst()) + String.valueOf(param.getFirst());
            case 2 -> String.valueOf(param.getFirst()) + String.valueOf(param.getLast());
            default -> String.valueOf(param.getFirst()) + String.valueOf(param.getLast());
        };
        return Long.valueOf(result);
    };

    @Override
    public Long getPart1Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            .map(toCharList)
            .map(toListOfDigits)
            .map(applyRules)
            .reduce(0L, Long::sum);
    }

    // @formatter:on

    Function<String, Long> processGame = line -> {
        // @formatter:off

        Map<String, Integer> stringToInt = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9
        );

        // @formatter:on

        int result = 0;
        int firstDigit = 0;
        int lastDigit = 0;

        for (int i = line.length() - 1; i >= 0; i--) {
            char currentChar = line.charAt(i);
            if (Character.isDigit(currentChar)) {
                lastDigit = Character.getNumericValue(currentChar);
                break;
            } else {
                for (int j = line.length(); j > i; j--) {
                    String isNumber = line.substring(i, j);
                    if (stringToInt.containsKey(isNumber)) {
                        lastDigit = stringToInt.get(isNumber);
                        break;
                    }
                }
                if (lastDigit != 0) {
                    break;
                }
            }
        }

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if (Character.isDigit(currentChar)) {
                firstDigit = Character.getNumericValue(currentChar);
                break;
            } else {
                for (int j = 0; j <= i; j++) {
                    String isNumber = line.substring(j, i + 1);
                    if (stringToInt.containsKey(isNumber)) {
                        firstDigit = stringToInt.get(isNumber);
                        break;
                    }
                }
                if (firstDigit != 0) {
                    break;
                }
            }
        }

        firstDigit *= 10;
        result = firstDigit + lastDigit;

        return Long.valueOf(result);
    };

    // @formatter:off

    @Override
    public Long getPart2Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            .map(processGame)
            .reduce(0L, Long::sum);
    }
    // @formatter:on
}
