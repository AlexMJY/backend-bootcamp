package kr.co.jhta.app;

import java.util.Scanner;

public class NumCheck {

    public static boolean rightId(String input) {
        String str = input.replaceAll("-", "");

        int[] chkNums = {2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5};
        int totalNumber = 0;

        // 변환과 계산을 한 번에 처리
        for (int i = 0; i < chkNums.length; i++) {
            totalNumber += (str.charAt(i) - '0') * chkNums[i];
        }

        int minNumber = 11 - (totalNumber % 11);
        if (minNumber >= 10) {
            minNumber -= 10;
        }

        // 주민번호의 마지막 자리와 비교
        if (minNumber == (str.charAt(12) - '0')) {
            return true;
        } else {
            return false;
        }
    }


    public static void main(String[] args) {
        rightId("731127-2121311");
    }
}
