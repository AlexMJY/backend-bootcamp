import java.time.LocalTime;

public class NowTime {
	LocalTime now = LocalTime.now();
	
	public int getHour() {
		return now.getHour();
	}
	
//	public int getMinute() {
//		return now.getMinute();
//	}
//	
//	public int getSecond() {
//		return now.getSecond();
//	}

}
