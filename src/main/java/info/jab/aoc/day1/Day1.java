package info.jab.aoc.day1;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.jab.aoc.Day;
import info.jab.aoc.Utils;

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

    private static Map<String, Integer> createWordToNumberMap() {
        return Map.ofEntries(
            Map.entry("one", 1),
            Map.entry("two", 2),
            Map.entry("three", 3),
            Map.entry("four", 4),
            Map.entry("five", 5),
            Map.entry("six", 6),
            Map.entry("seven", 7),
            Map.entry("eight", 8),
            Map.entry("nine", 9),
            Map.entry("oneight", 18), //AOC Trick
            Map.entry("twone", 21), //AOC Trick
            Map.entry("eightwo", 82) //AOC Trick
        );
    }

    private static final Pattern DIGITS_REGEX_PATTERN = Pattern.compile(
        "(?:\\d+|oneight|twone|eightwo|one|two|three|four|five|six|seven|eight|nine)"
    );

    Function<String, Long> extractNumber = param -> {
        Map<String, Integer> wordToNumber = createWordToNumberMap();

        StringBuilder digits = new StringBuilder();
        Matcher matcher = DIGITS_REGEX_PATTERN.matcher(param);
        while (matcher.find()) {
            String match = matcher.group();
            digits.append(Character.isDigit(match.charAt(0)) ? match : wordToNumber.get(match));
        }

        String number = String.valueOf(digits.charAt(0)) + digits.charAt(digits.length() - 1);

        return Long.valueOf(number);
    };

    // @formatter:off

    @Override
    public Long getPart2Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            .map(extractNumber)
            .reduce(0L, Long::sum);
    }
    // @formatter:on
}
