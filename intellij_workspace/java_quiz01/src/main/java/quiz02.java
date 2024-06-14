import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class quiz02 {
    public static void main(String[] args) {
        String filename = "src/main/java/info.txt";
        List<String> lines = readFile(filename);
        String[] linesArray = lines.toArray(new String[0]);

        for (String str : linesArray) {
            processLine(str);
        }
    }

    // 파일을 읽어서 각 라인을 리스트에 저장하는 메서드
    private static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    // 각 라인을 처리하여 결과를 출력하는 메서드
    private static void processLine(String str) {
        String cleanedStr = str.replaceAll("[- ]", ""); // 하이픈과 공백 제거
        boolean containsAlphabet = containsAlphabet(cleanedStr);

        if (!containsAlphabet) {
            if (cleanedStr.length() == 13 && quiz01.rightId(cleanedStr)) {
                System.out.println("[" + str + "] 는 주민번호가 맞습니다.");
            } else if (cleanedStr.length() == 16) {
                System.out.println("[" + str + "] 는 카드번호가 맞습니다.");
            } else {
                System.out.println("[" + str + "] 는 개인정보가 아닙니다.");
            }
        } else {
            System.out.println("[" + str + "] 는 개인정보가 아닙니다.");
        }
    }

    // 문자열에 알파벳이 포함되어 있는지 확인하는 메서드
    private static boolean containsAlphabet(String str) {
        for (char ch : str.toCharArray()) {
            if (Character.isLetter(ch)) {
                return true;
            }
        }
        return false;
    }
}
