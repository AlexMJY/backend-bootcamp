import java.time.LocalTime;

public class NewYorkNowTime {
	LocalTime now = LocalTime.now();
	
	public int getHour() {
		int usHour = now.getHour() - 13;
		if (usHour < 0) {
			usHour += 24;
		}
		return usHour;
	}
}
