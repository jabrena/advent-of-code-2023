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
            .peek(System.out::println)
            .map(applyRules)
            .reduce(0L, (a, b) -> a + b);
    }

    // @formatter:on

    @Override
    public Long getPart2Result(String fileName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPart2Result'");
    }
}
