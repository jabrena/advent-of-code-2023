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
        var parts = game.split(":");
        var parts2 = parts[1].split(";");

        for (String cubeSet : parts2) {
            var cubeSetParts = cubeSet.split(",");
            for (String item : cubeSetParts) {
                var tuple = item.trim().split(" ");
                String colorName = tuple[1];

                CubeColor cubeColor = CubeColor.getColorFromName(colorName);
                if (cubeColor != null) {
                    int value = Integer.parseInt(tuple[0]);
                    int maxValue = cubeColor.getMaxValue();

                    if (value > maxValue) {
                        return false;
                    }
                }
            }
        }

        return true;
    };

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

    Function<String, Long> calculatePower = param -> {
        long red = 0L;
        long green = 0L;
        long blue = 0L;

        for (String cubeSet : param.split(":")[1].split(";")) {
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
    };

    // @formatter:off

    @Override
    public Long getPart2Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            .map(calculatePower)
            .reduce(0L, Long::sum);
    }
    // @formatter:on

}
