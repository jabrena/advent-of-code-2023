package info.jab.aoc.day2;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.Test;

class Day2Test {

    @Test
    void should_solve_day2_part1_with_sample() {
        //Given
        String fileName = "day2/input-sample.txt";

        //When
        Day2 day1 = new Day2();
        var result = day1.getPart1Result(fileName);

        //Then
        then(result).isEqualTo(8);
    }
}
