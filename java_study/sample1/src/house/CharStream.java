package house;

import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharStream {
    public CharStream() {
        // 문자열의 각 문자를 IntStream으로 변환
        IntStream intStream = "AroundHubStudio".chars()
                .filter((tempInt) -> tempInt > 100);
        intStream.forEach(System.out::println);

        // 패턴(정규표현식)을 통해 문자열을 자르고 스트림으로 생성
        Stream<String> stringStream = Pattern.compile(" ").splitAsStream("Around Hub Studio의 Alex입니다.");
        stringStream.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new CharStream();
    }
}
