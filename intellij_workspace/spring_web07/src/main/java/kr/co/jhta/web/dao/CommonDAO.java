package kr.co.jhta.web.dao;

import kr.co.jhta.web.dto.DeptDTO;
import kr.co.jhta.web.dto.MemberDTO;

import java.util.List;

public interface CommonDAO {
    public boolean findByIdAndPw(String id, String pw);

    public void insert(MemberDTO dto);

    public List<DeptDTO> deptList();
}
