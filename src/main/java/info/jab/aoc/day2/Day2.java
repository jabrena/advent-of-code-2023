package info.jab.aoc.day2;

import info.jab.aoc.Day;
import info.jab.aoc.Utils;

/**
 * Solution for AOC 2023, Day 2
 * https://adventofcode.com/2023/day/2
 *
 */
public class Day2 implements Day<Long> {

    @Override
    public Long getPart1Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            //.peek(System.out::println)
            .filter(game -> {
                //only 12 red cubes, 13 green cubes, and 14 blue cubes

                var maxRed = 12;
                var maxGreen = 13;
                var maxBlue = 14;

                var parts = game.split(":");
                var parts2 = parts[1].split(";");

                for (String cubeSet : parts2) {
                    var cubeSetParts = cubeSet.split(",");
                    for (String item : cubeSetParts) {
                        var tuple = item.trim().split(" ");
                        if(tuple[1].contains("red")) {
                            var currentRed = Integer.valueOf(tuple[0]);
                            if(currentRed > maxRed) {
                                return false;
                            }
                        }
                        if(tuple[1].contains("green")) {
                            var currentGreen = Integer.valueOf(tuple[0]);
                            if(currentGreen > maxGreen) {
                                return false;
                            }
                        }
                        if(tuple[1].contains("blue")) {
                            //blue += Integer.valueOf(tuple[0]);
                            var currentBlue = Integer.valueOf(tuple[0]);
                            if(currentBlue > maxBlue) {
                                return false;
                            }
                        }
                    }
                }

                return true;
            })
            .peek(System.out::println)
            .map(game -> {
                var parts = game.split(":");
                var parts2 = parts[0].split(" ");
                return Long.valueOf(parts2[1]);
            })
            .reduce(0L, (a, b) -> a + b);
    }

    @Override
    public Long getPart2Result(String fileName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
