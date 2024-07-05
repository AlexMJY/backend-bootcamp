package com.aibayo.springbootex16.test;

// 정규표현식
// 특정한 규칙을 가진 문자열의 집합을 표현하기 위해
// 사용하는 형식 언어

// 언어, 텍스트 편집기 문자열의 검색, 치환을 위해 지원

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {
    public static void main(String[] args) {

        String searchData = "hongGilDong 20 남원_한양 hong@gmail.com 02-1234-5678" +
                "KS 20 서울 dagda@hanafos.com 010-222-3333" +
                "이순신 20 부산 lee@gmail.com 080 3333 4444";

        // 특정문자 : Gil (대소문자 구분)
        // 숫자 : d (decimal)
        // 문자 : w (word)
        // * : 0번 이상 반복
        // + : 1번 이상 반복
        // ? : 1 or 0
        // {} : 횟수를 표시

//        Pattern pattern = Pattern.compile("\\d{3}-\\d{3,4}-\\d{3,4}");
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_\\-\\.]+@[a-zA-Z0-9_\\-\\.]+\\.[a-zA-Z0-9]{2,}");
        Matcher matcher = pattern.matcher(searchData);

        while (matcher.find()) {
//            System.out.println(matcher.group());
            System.out.println(matcher.group());
        }
    }
}
