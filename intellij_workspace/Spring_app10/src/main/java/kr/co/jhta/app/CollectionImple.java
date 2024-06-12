package kr.co.jhta.app;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionImple implements CollectionInter {
    private List<String> list;
    private Map<String, String> map;

    public CollectionImple() {}

    public CollectionImple(List<String> list, Map<String, String> map) {
        this.list = list;
        this.map = map;
    }

    @Override
    public void printList() {
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Override
    public void printMap() {
        Set<String> set = map.keySet();
        for (String s : set) {
            System.out.println(s + " : " + map.get(s));
        }
    }
}
