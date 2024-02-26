package Ex14;

import java.util.Optional;

public class Ex14_0 {
    public static void main(String[] args) {
//        int[] arr = null;  // lenth를 불러오면 에러 발생
        int[] arr = new int[0];

        System.out.println("arr.length" + arr.length);

//        Optional<String> opt = null; // 가능. 하지만 바람직하지 않음
        Optional<String> opt = Optional.empty();
        System.out.println("opt = " + opt);
//        System.out.println("opt.get = " + opt.get()); // Error

        String str = "";

//        try {
//            str = opt.get();
//        } catch (Exception e) {
//            str = "";
//        }
//        System.out.println("str : " + str);
        str = opt.orElse("Empty"); // Optional에 저장된 값이 null이면 "Empty"반환


        String str2 = "";
        str2 = opt.orElseGet(() -> "Empty2");

        String str3 = "";
        str3 = opt.orElseGet(String::new);

        System.out.println("str : " + str);
        System.out.println("str2 : " + str2);
        System.out.println("str3 : " + str3);

    }
}
