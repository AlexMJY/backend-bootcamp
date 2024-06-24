package kr.co.jhta.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OracleDAOTest {
    OracleDAO dao;

    @BeforeEach
    void setUp() {
        dao = new OracleDAO();
    }

    @AfterEach
    void setDown() {
        dao = null;
    }

    @Test
    void findByIdAndPw() {
        assertNotNull(dao);
        boolean result = dao.findByIdAndPw("aaa", "111");
        assertTrue(result);
    }

    @Test
    void deptList() {
//        assertEquals(8, dao.deptList().size());
        assertThat(dao.deptList().size()).isEqualTo(8);
        DeptDTO dto = dao.deptList().get(0);
        assertThat(dto.getDeptno()).as("부서명 %s", dto.getDname()).isEqualTo(10);

    }
}