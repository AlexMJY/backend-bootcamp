package kr.co.jhta.web.util;

import java.util.HashMap;
import java.util.Map;

public class PageUtil {

    public static Map<String, Object> getPageData(int totalNumber, int countPerPage, int currentPage) {
        Map<String, Object> map = new HashMap<>();


        // 총 페이지 수 : 총 게시물 수 / 한 페이지당 게시물 수
        // 209 / 10 ==> 20 나머지 9  => 21
        int totalPages = totalNumber % countPerPage == 0 ?
                totalNumber / countPerPage : (totalNumber / countPerPage) + 1;
        // 현재 페이지의 시작 번호
        int startNo = (currentPage - 1) * countPerPage + 1;  // (3 - 1) * 10 + 1 ==> 21
        // 현재 페이지의 끝 번호
        int endNo = currentPage * countPerPage;

        // 시작 페이지 번호
        int startPageNo = currentPage - 5 <= 0 ? 1 : currentPage - 5;
        // 끝 페이지 번호
        int endPageNo = startPageNo + 9 <= totalPages ? startPageNo + 9 : totalPages;
        // 이전
        boolean prev = startPageNo > 1;
        // 다음
        boolean next = endPageNo < totalPages;


        map.put("totalNumber", totalNumber);
        map.put("countPerPage", countPerPage);
        map.put("currentPage", currentPage);
        map.put("totalPages", totalPages);
        map.put("startNo", startNo);
        map.put("endNo", endNo);
        map.put("startPageNo", startPageNo);
        map.put("endPageNo", endPageNo);
        map.put("prev", prev);
        map.put("next", next);


        return map;
    }
}