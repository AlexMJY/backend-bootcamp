package kr.co.jhta.app.springbootex13_board_mybatis.control;

import kr.co.jhta.app.springbootex13_board_mybatis.dto.BoardDTO;
import kr.co.jhta.app.springbootex13_board_mybatis.service.BoardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @Test
    public void testList() throws Exception {

        Mockito.when(boardService.getList(1, 20))
                .thenReturn(mock(List.class));

        mockMvc.perform(get("/board/list")
                .param("page", "1")
                .param("keyword", "none"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("map"))
                .andDo(print());
    }

    @Test
    public void testDetail() throws Exception {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBno(1L);
        boardDTO.setTitle("Test Title");
        boardDTO.setContents("Test Content");
        boardDTO.setWriter("Test Writer");

        when(boardService.getOne(1L)).thenReturn(boardDTO);

        mockMvc.perform(get("/board/detail/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("board"))
                .andDo(print());
    }

}