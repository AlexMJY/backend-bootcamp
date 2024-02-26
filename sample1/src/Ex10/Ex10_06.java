package Ex10;
import java.text.DecimalFormat;

public class Ex10_06 {
    public static void main(String[] args) {
        double number = 1234567.89;
        String[] pattern = {
          "0",
          "#",
          "0.0",
          "#.#",
          "0000000000.0000",
          "#.#-",
          "#,###.##",
          "#E0",
          "####E0",
          "0.0E0",
          "\u00A4 #,###",
          "'#'#,###",
        };

        for (int i = 0; i < pattern.length; i++) {
            DecimalFormat df = new DecimalFormat(pattern[i]);
            System.out.printf("%19s : %s\n", pattern[i], df.format(number));
        }


    }
}
