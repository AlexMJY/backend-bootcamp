package Ex12;

import java.util.ArrayList;

public class AnnotationEx03 {
    @SuppressWarnings("deprecation") // deprecation 관련 경고 억제
    public static void main(String[] args) {
        NewClass nc = new NewClass();

        nc.oldField = 10;
        System.out.println(nc.getOldField());

        @SuppressWarnings("unchecked")
        ArrayList<NewClass> list = new ArrayList();
        list.add(nc);

    }
}
