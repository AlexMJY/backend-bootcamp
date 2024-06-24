package kr.co.jhta.app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumCheckTest {
    NumCheck nc;

    @BeforeEach
    void setUp() {
        nc = new NumCheck();
    }

    @AfterEach
    void tearDown() {
        nc = null;
    }

    @DisplayName("민증번호 테스트")
    @Test
    void rightId() {
        assertEquals(true, nc.rightId("731127-2121311"));
    }
}