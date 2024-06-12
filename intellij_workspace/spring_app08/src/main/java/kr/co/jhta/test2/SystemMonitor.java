package kr.co.jhta.test2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMonitor {
    Sender sender;
    
    public void print() {
        System.out.println("SystemMonitor클래스의 print() 실행 중");
        sender.show();
    }
}
