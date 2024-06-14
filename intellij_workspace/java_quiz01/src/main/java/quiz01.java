import java.util.Arrays;
import java.util.Scanner;

public class quiz01 {
    public static boolean rightId() {
        // 1. 총 13자리중 마지막을 제외한 12자리에 정해진 숫자를 곱한다
        // 2. 12자리의 숫자를 모두 더한다 = total_number
        // 3. total_number를 11로 나눈 나머지의 값 div_number
        // 4. 11 - div_number = min_number
        // 5. min_number == 13번째자리일 경우 올바른 주민번호

        System.out.println("주민번호를 입력해주세요 : ");
        Scanner sc = new Scanner(System.in);
        String str = sc.next().replaceAll("-", "");

        int[] chkNums = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
        int totalNumber = 0;

        // 변환과 계산을 한 번에 처리
        for (int i = 0; i < chkNums.length; i++) {
//            totalNumber += (str.charAt(i) - '0') * chkNums[i];
            totalNumber += (str.charAt(i) - '0') * chkNums[i];
        }

        int minNumber = 11 - (totalNumber % 11);
        if (minNumber >= 10) {
            minNumber -= 10;
        }

        // 주민번호의 마지막 자리와 비교
        if (minNumber == (str.charAt(12) - '0')) {
            System.out.println("올바른 주민번호입니다.");
            return true;
        } else {
            System.out.println("올바르지 않은 주민번호입니다.");
            return false;
        }
    }

    public static boolean rightId(String input) {
        // 1. 총 13자리중 마지막을 제외한 12자리에 정해진 숫자를 곱한다
        // 2. 12자리의 숫자를 모두 더한다 = total_number
        // 3. total_number를 11로 나눈 나머지의 값 div_number
        // 4. 11 - div_number = min_number
        // 5. min_number == 13번째자리일 경우 올바른 주민번호

//        System.out.println("주민번호를 입력해주세요 : ");
//        Scanner sc = new Scanner(System.in);
        String str = input.replaceAll("-", "");

        int[] chkNums = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
        int totalNumber = 0;

        // 변환과 계산을 한 번에 처리
        for (int i = 0; i < chkNums.length; i++) {
//            totalNumber += (str.charAt(i) - '0') * chkNums[i];
            totalNumber += (str.charAt(i) - '0') * chkNums[i];
        }

        int minNumber = 11 - (totalNumber % 11);
        if (minNumber >= 10) {
            minNumber -= 10;
        }

        // 주민번호의 마지막 자리와 비교
        if (minNumber == (str.charAt(12) - '0')) {
//            System.out.println("올바른 주민번호입니다.");
            return true;
        } else {
//            System.out.println("올바르지 않은 주민번호입니다.");
            return false;
        }
    }

    public static void main(String[] args) {
        rightId();

    }
}
