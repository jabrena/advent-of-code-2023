package info.jab.aoc.day1;

import java.util.List;
import java.util.function.Function;

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

        String result = "";
        if(param.size() == 1) {
            result = String.valueOf(param.getFirst()) + String.valueOf(param.getFirst());
        } else if(param.size() == 2) {
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

        //one, two, three, four, five, six, seven, eight, and nine

        var paramModified = param.replace("one", "1");
        paramModified = paramModified.replace("two", "2");
        paramModified = paramModified.replace("three", "3");
        paramModified = paramModified.replace("four", "4");
        paramModified = paramModified.replace("five", "5");
        paramModified = paramModified.replace("six", "6");
        paramModified = paramModified.replace("seven", "7");
        paramModified = paramModified.replace("eight", "8");
        paramModified = paramModified.replace("nine", "9");

        return paramModified;
    };

    @Override
    public Long getPart2Result(String fileName) {
        return Utils.readFileToList(fileName).stream()
            .map(replacePatterns)
            .map(toCharList)
            .map(toListOfDigits)
            .peek(System.out::println)
            .map(applyRules)
            .reduce(0L, (a, b) -> a + b);
    }
}
