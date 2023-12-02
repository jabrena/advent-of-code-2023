package info.jab.aoc.day1;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;

class Day1Test {

    @Test
    void should_solve_day1_part1_with_sample() {
        //Given
        String fileName = "day1/day1-input-sample.txt";

        //When
        Day1 day1 = new Day1();
        var result = day1.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(142);
    }

    @Test
    void should_solve_day1_part1() {
        //Given
        String fileName = "day1/day1-input.txt";

        //When
        Day1 day1 = new Day1();
        var result = day1.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(53974);
    }

    @Test
    void should_solve_day1_part2_with_sample() {
        //Given
        String fileName = "day1/day1-input2-sample.txt";

        //When
        Day1 day1 = new Day1();
        var result = day1.getPart2Result(fileName);

        //Then
        then(result).isEqualTo(281);
    }

    @Test
    void shouldReplace() {

        //Given

        Day1 day1 = new Day1();
        var result = day1.replacePatterns
            .andThen(day1.toCharList)
            .andThen(day1.toListOfDigits)
            .andThen(day1.applyRules)
            .apply("7pqrstsixteen");

        //When

        //Then
        then(result).isEqualTo(76);
    }

    @Test
    void should_solve_day1_part2() {
        //Given
        String fileName = "day1/day1-input.txt";

        //When
        Day1 day1 = new Day1();
        var result = day1.getPart2Result(fileName);

        //Then
        then(result).isEqualTo(52840);
    }
}
