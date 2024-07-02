package kr.co.jhta.app.springbootex12.control;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.jhta.app.springbootex12.domain.Dept;
import kr.co.jhta.app.springbootex12.dto.DeptDTO;
import kr.co.jhta.app.springbootex12.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "예제 API", description = "SWAGGER 테스트용 API")

//@Controller
@RestController
@RequiredArgsConstructor
public class DeptController {
    private final DeptService deptService;

    @Operation(summary = "전체조회", description = "부서의 목록을 확인합니다.", tags = "Dept Controller")
    @ApiResponse(responseCode = "200", description = "정상처리")
    @ApiResponse(responseCode = "404", description = "찾을 수 없음")
    @ApiResponse(responseCode = "500", description = "서버 에러")
    @GetMapping("/dept")
    public ResponseEntity<List<DeptDTO>> dept() {
        ResponseEntity<List<DeptDTO>> list = deptService.readAll();
        return list;
    }

    @Operation(summary = "특정 부서 정보 조회", description = "주어진 부서를 조회합니다.", tags = "Dept Controller")
    @Parameter(name = "deptno", description = "검색하고자 하는 부서번호를 입력하세요", required = true, example = "1")
    @ApiResponse(responseCode = "200", description = "정상처리")
    @ApiResponse(responseCode = "404", description = "찾을 수 없음")
    @ApiResponse(responseCode = "500", description = "서버 에러")
    @GetMapping("/dept/{deptno}")
    public ResponseEntity<DeptDTO> getOne(@PathVariable int deptno) {
        DeptDTO dto = deptService.readOne(deptno);
        return ResponseEntity.ok().body(dto);
    }

    @Operation(summary = "부서 정보 추가", description = "한개의 부서정보를 입력", tags = "Dept Controller")
    @Parameter(name = "deptDTO", description = "추가할 내용을 json 형식으로 입력하세요", required = true)
    @ApiResponse(responseCode = "200", description = "정상처리", content = {@Content(schema = @Schema(implementation = DeptDTO.class))})
    @ApiResponse(responseCode = "404", description = "찾을 수 없음")
    @ApiResponse(responseCode = "500", description = "서버 에러")
    @PostMapping("/dept")
    public ResponseEntity<DeptDTO> writeOne(@ModelAttribute DeptDTO deptDTO) {
        deptService.write(deptDTO);
        return ResponseEntity.ok().body(deptDTO);
    }

    @Operation(summary = "부서 정보 수정", description = "한개의 부서정보를 수정", tags = "Dept Controller")
    @Parameter(name = "no", description = "수정할 부서번호를 입력하세요", required = true, example = "1")
    @Parameter(name = "deptDTO", description = "수정할 내용을 json 형식으로 입력하세요", required = true)
    @ApiResponse(responseCode = "200", description = "정상처리", content = {@Content(schema = @Schema(implementation = DeptDTO.class))})
    @ApiResponse(responseCode = "404", description = "찾을 수 없음")
    @ApiResponse(responseCode = "500", description = "서버 에러")
    @PutMapping("/dept/{no}")
    public ResponseEntity<DeptDTO> updateOne(@PathVariable int no, @ModelAttribute DeptDTO deptDTO) {
        deptService.modifyOne(no, deptDTO);
        return ResponseEntity.ok().body(deptDTO);
    }


    @Operation(summary = "부서 삭제", description = "한개의 부서 삭제", tags = "Dept Controller")
    @ApiResponse(responseCode = "200", description = "정상처리")
    @ApiResponse(responseCode = "404", description = "찾을 수 없음")
    @ApiResponse(responseCode = "500", description = "서버 에러")
    @DeleteMapping("/dept/{no}")
    public ResponseEntity<String> deleteOne(@PathVariable int no) {
        deptService.remove(no);
        return ResponseEntity.ok("OK");
    }
}
