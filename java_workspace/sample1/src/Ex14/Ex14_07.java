package Ex14;

import java.util.Arrays;
import java.util.stream.Stream;
public class Ex14_07 {
    public static void main(String[] args) {
        Stream<String[]> strArrStrm = Stream.of(
                new String[]{"abc", "def", "jkl"},
                new String[]{"ABC", "GHI", "JKL"}
        );

        Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);

        strStrm.map(String::toLowerCase)
                .distinct().sorted().forEach(System.out::println);
        System.out.println();

        String[] lineArr = {
                "Beleive of not It is true",
                "Do or do not There is no try",
        };

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +")))
                .map(String::toLowerCase).distinct().sorted().forEach(System.out::println);
        System.out.println();
    }
}
