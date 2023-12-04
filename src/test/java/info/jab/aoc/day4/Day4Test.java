package info.jab.aoc.day4;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;

public class Day4Test {

    @Test
    void should_solve_day4_part1_with_sample() {
        //Given
        String fileName = "day4/input-sample.txt";

        //When
        Day4 day = new Day4();
        var result = day.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(8);
    }
}
