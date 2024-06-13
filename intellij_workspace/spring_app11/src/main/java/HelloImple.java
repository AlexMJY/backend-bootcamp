import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloImple implements HelloInter {
	@Autowired
	NowTime nt;
//	NewYorkNowTime nt;

//	public void setNt(NowTime nt) {  // nt에 의존 / 의존성 주입 -> 자바 코드가 아닌 xml에서 bean으로 사용
//		this.nt = nt;
//	}
	
	@Override
	public void sayHello() {
		// 현재 시간        
//		int hour = new NowTime().getHour();
//		NowTime nt = new NowTime(); // 의존관계는 xml에서 설정 / 이곳에서 의존으로 엮어버리면 나중에 수정하기 힘들어짐
		int hour = nt.getHour();
		System.out.println("지금 시간은 " + hour + "입니다.");
		if (hour >= 6 && hour <= 10) {
			System.out.println("안녕하세요.");
		} else if (hour >= 10 && hour <= 15) {
			System.out.println("점심 식사하셨나요?");
		} else {
			System.out.println("좋은 저녁되세요.");
		}
	}
}