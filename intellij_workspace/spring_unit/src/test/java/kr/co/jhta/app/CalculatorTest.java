package kr.co.jhta.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator cal;

    @BeforeEach
    void setUp() {
        cal = new Calculator();
    }

    @DisplayName("합계 테스트")
    @Test
    void sum() {
        assertEquals(300, cal.sum(100, 200));
    }

    @Test
    void minus() {
        assertEquals(300, cal.minus(500, 200));

    }

    @Test
    void multi() {
        assertEquals(500, cal.multi(50, 10));

        // assertj
        assertThat(cal.multi(100, 200)).isEqualTo(20000);

    }

    @Test
    void test_assertThat() {
        assertThat("Hello, World today is Monday.")
                .isNotEmpty()
                .contains("Hello, World")
                .contains("today is Monday")
                .doesNotContain("sunday")
                .startsWith("Hell")
                .endsWith("y.")
                .isEqualTo("Hello, World today is Monday.");
    }

    @Test
    void test_assertThatNumber() {
        assertThat(3.14159265358979323846)
                .isPositive()
                .isNotNegative()
                .isGreaterThan(3)
                .isLessThan(4)
                .isBetween(3.1d, 3.2d)
                .isEqualTo(3.14159265358979323846);
    }
}