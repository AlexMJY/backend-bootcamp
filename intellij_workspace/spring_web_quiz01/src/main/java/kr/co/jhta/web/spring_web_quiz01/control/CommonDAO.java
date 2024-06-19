package kr.co.jhta.web.spring_web_quiz01.control;

import java.util.List;

public interface CommonDAO {
    public boolean findByIdAndPw(String id, String pw);

    public void insert(MemberDTO dto);

    public List<DeptDTO> deptList();
}
