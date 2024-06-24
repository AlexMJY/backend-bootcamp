package kr.co.jhta.app;

import kr.co.jhta.dao.DeptDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class OtherTest {

    @DisplayName("기타 테스트")
    @Test
    public void test1() {
        List<DeptDTO> list = new ArrayList<DeptDTO>();

        DeptDTO dto1 = new DeptDTO();
        dto1.setDeptno(99);
        dto1.setDname("AI");
        dto1.setLoc("busan");

        list.add(dto1);

        DeptDTO dto2 = DeptDTO.builder()
                            .deptno(98)
                            .dname("IT")
                            .loc("Seoul")
                            .build();
        DeptDTO dto3 = DeptDTO.builder().deptno(97).dname("IT").loc("서울").build();
        DeptDTO dto4 = DeptDTO.builder().deptno(96).dname("경영").loc("울산").build();

        list.add(dto2);
        list.add(dto3);
        list.add(dto4);

        // dto1의 부서명이 AI가 맞는지 테스트
        // dto2의 부서위치가 서울이 맞는지 테스트
        // list의 사이즈가 2인지 테스트

        assertEquals("AI", dto1.getDname());

        assertEquals("Seoul", dto2.getLoc());
//        assertThat(dto2.getLoc()).as("부서위치 : %s", dto2.getLoc()).isEqualTo("서울");

        assertEquals(4, list.size());

//        assertThat(list).filteredOn(dto -> dto.getLoc().contains("울")).containsOnly(dto4);
    }
}
