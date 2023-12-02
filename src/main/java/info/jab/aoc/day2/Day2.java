package info.jab.aoc.day2;

import info.jab.aoc.Day;
import info.jab.aoc.Utils;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Solution for AOC 2023, Day 2
 * https://adventofcode.com/2023/day/2
 *
 */
public class Day2 implements Day<Long> {

    Predicate<String> isValidGame = game -> {
        final String[] colors = { "red", "green", "blue" };
        var parts = game.split(":");
        var parts2 = parts[1].split(";");

        for (String cubeSet : parts2) {
            var cubeSetParts = cubeSet.split(",");
            for (String item : cubeSetParts) {
                var tuple = item.trim().split(" ");
                String color = tuple[1];

                for (String currentColor : colors) {
                    if (color.contains(currentColor)) {
                        int value = Integer.parseInt(tuple[0]);
                        int maxValue = getMaxValueForColor(currentColor);

                        if (value > maxValue) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    };

    private int getMaxValueForColor(String color) {
        return switch (color) {
            case "red" -> 12;
            case "green" -> 13;
            case "blue" -> 14;
            default -> Integer.MAX_VALUE; // Or handle unknown colors differently
        };
    }

    Function<String, Long> extractGameId = param -> {
        var parts = param.split(":");
        var parts2 = parts[0].split(" ");
        return Long.valueOf(parts2[1]);
    };

    // @formatter:off

    @Override
    public Long getPart1Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            .filter(isValidGame)
            .map(extractGameId)
            .reduce(0L, Long::sum);
    }

    // @formatter:on

    @Override
    public Long getPart2Result(String fileName) {
        return Utils
            .readFileToList(fileName)
            .stream()
            .map(game -> {
                long red = 0L;
                long green = 0L;
                long blue = 0L;

                for (String cubeSet : game.split(":")[1].split(";")) {
                    for (String item : cubeSet.split(",")) {
                        var tuple = item.trim().split(" ");
                        int value = Integer.parseInt(tuple[0]);

                        switch (tuple[1]) {
                            case "red" -> red = Math.max(red, value);
                            case "green" -> green = Math.max(green, value);
                            case "blue" -> blue = Math.max(blue, value);
                            default -> new RuntimeException("Katakroker");
                        }
                    }
                }

                return red * green * blue;
            })
            .reduce(0L, Long::sum);
    }
}
