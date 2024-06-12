package spring_app04;

import java.sql.Connection;
import java.util.List;

public interface CommonDAO {
    public Connection connect();
    public List<DeptDTO> selectAll();
}
