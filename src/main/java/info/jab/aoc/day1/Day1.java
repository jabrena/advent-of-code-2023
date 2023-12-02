package info.jab.aoc.day1;

import info.jab.aoc.Day;
import info.jab.aoc.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Map<String, Integer> mp = new HashMap<>();
        mp.put("one", 1);
        mp.put("two", 2);
        mp.put("three", 3);
        mp.put("four", 4);
        mp.put("five", 5);
        mp.put("six", 6);
        mp.put("seven", 7);
        mp.put("eight", 8);
        mp.put("nine", 9);
        mp.put("oneight", 18); //AOC Trick
        mp.put("twone", 21); //AOC Trick
        mp.put("eightwo", 82); //AOC Trick
        return mp;
    }

    private static final String DIGIT_REGEX =
        "(?:\\d+|oneight|twone|eightwo|one|two|three|four|five|six|seven|eight|nine)";
    private static final Pattern DIGITS_REGEX_PATTERN = Pattern.compile(DIGIT_REGEX);

    Function<String, Long> extractNumber = param -> {
        Map<String, Integer> wordToNumber = createWordToNumberMap();

        StringBuilder digits = new StringBuilder();
        Matcher matcher = DIGITS_REGEX_PATTERN.matcher(param);
        while (matcher.find()) {
            String match = matcher.group().toLowerCase();
            if (Character.isDigit(match.charAt(0))) {
                digits.append(match);
            } else {
                digits.append(wordToNumber.get(match));
            }
        }

        String number = "";
        number += digits.charAt(0);
        number += digits.charAt(digits.length() - 1);

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
