package info.jab.aoc.day1;

import info.jab.aoc.Day;
import info.jab.aoc.Utils;
import java.util.HashMap;
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

        //TODO Improve with a Switch case
        String result = "";
        if (param.size() == 1) {
            result = String.valueOf(param.getFirst()) + String.valueOf(param.getFirst());
        } else if (param.size() == 2) {
            result = String.valueOf(param.getFirst()) + String.valueOf(param.getLast());
        } else {
            result = String.valueOf(param.getFirst()) + String.valueOf(param.getLast());
        }
        return Long.valueOf(result);
    };

    @Override
    public Long getPart1Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            .map(toCharList)
            .map(toListOfDigits)
            //.peek(System.out::println)
            .map(applyRules)
            .reduce(0L, (a, b) -> a + b);
    }

    // @formatter:on

    Function<String, String> replacePatterns = param -> {
        var paramModified = param;

        Boolean exitFlag = true;
        Map<String, Integer> detections = new HashMap<>();

        while (exitFlag) {
            //Detect first pattern to apply
            //System.out.println("running");

            //one, two, three, four, five, six, seven, eight, and nine

            detections.put("one", paramModified.indexOf("one"));
            detections.put("two", paramModified.indexOf("two"));
            detections.put("three", paramModified.indexOf("three"));
            detections.put("four", paramModified.indexOf("four"));
            detections.put("five", paramModified.indexOf("five"));
            detections.put("six", paramModified.indexOf("six"));
            detections.put("seven", paramModified.indexOf("seven"));
            detections.put("eight", paramModified.indexOf("eight"));
            detections.put("nine", paramModified.indexOf("nine"));

            var result = detections
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() != -1)
                .sorted(Map.Entry.comparingByValue())
                //.peek(System.out::println)
                .map(entry -> entry.getKey())
                .findFirst();

            //Apply pattern

            if (result.isPresent()) {
                if (result.get().equals("one")) {
                    paramModified = paramModified.replace("one", "1");
                } else if (result.get().equals("two")) {
                    paramModified = paramModified.replace("two", "2");
                } else if (result.get().equals("three")) {
                    paramModified = paramModified.replace("three", "3");
                } else if (result.get().equals("four")) {
                    paramModified = paramModified.replace("four", "4");
                } else if (result.get().equals("five")) {
                    paramModified = paramModified.replace("five", "5");
                } else if (result.get().equals("six")) {
                    paramModified = paramModified.replace("six", "6");
                } else if (result.get().equals("seven")) {
                    paramModified = paramModified.replace("seven", "7");
                } else if (result.get().equals("eight")) {
                    paramModified = paramModified.replace("eight", "8");
                } else if (result.get().equals("nine")) {
                    paramModified = paramModified.replace("nine", "9");
                }
            } else {
                //Detect not found pattern
                exitFlag = false;
            }
        }

        return paramModified;
    };

    private Long alternative2(List<String> input) {
        Map<String, Integer> stringToInt = new HashMap<>();
        stringToInt.put("one", 1);
        stringToInt.put("two", 2);
        stringToInt.put("three", 3);
        stringToInt.put("four", 4);
        stringToInt.put("five", 5);
        stringToInt.put("six", 6);
        stringToInt.put("seven", 7);
        stringToInt.put("eight", 8);
        stringToInt.put("nine", 9);

        int totalSum = 0;

        for (String line : input) {
            int firstDigit = 0;
            int lastDigit = 0;
            boolean hasChanged = false;

            for (int i = line.length() - 1; i > -1; i--) {
                char currentChar = line.charAt(i);
                if (!hasChanged) {
                    if (Character.isDigit(currentChar)) {
                        lastDigit = Character.getNumericValue(currentChar);
                        hasChanged = true;
                    } else {
                        for (int j = line.length(); j >= i; j--) {
                            String isNumber = line.substring(i, j);
                            if (stringToInt.containsKey(isNumber)) {
                                lastDigit = stringToInt.get(isNumber);
                                hasChanged = true;
                                break;
                            }
                        }
                    }
                }
            }
            hasChanged = false;
            for (int i = 0; i != line.length(); i++) {
                char currentChar = line.charAt(i);
                if (!hasChanged) {
                    if (Character.isDigit(currentChar)) {
                        firstDigit = Character.getNumericValue(currentChar);
                        hasChanged = true;
                    } else {
                        for (int j = 0; j <= i; j++) {
                            String isNumber = line.substring(j, i + 1);
                            if (stringToInt.containsKey(isNumber)) {
                                firstDigit = stringToInt.get(isNumber);
                                hasChanged = true;
                                break;
                            }
                        }
                    }
                }
            }

            firstDigit *= 10;
            int result = firstDigit + lastDigit;
            totalSum += result;
        }

        return Long.valueOf(totalSum);
    }

    @Override
    public Long getPart2Result(String fileName) {
        var badResult = Utils
            .readFileToList(fileName)
            .stream()
            .map(replacePatterns)
            .map(toCharList)
            .map(toListOfDigits)
            //.peek(System.out::println)
            .map(applyRules)
            .reduce(0L, (a, b) -> a + b);

        return alternative2(Utils.readFileToList(fileName));
    }
}
