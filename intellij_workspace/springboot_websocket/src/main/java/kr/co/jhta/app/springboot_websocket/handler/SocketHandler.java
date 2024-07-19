package kr.co.jhta.app.springboot_websocket.handler;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class SocketHandler extends TextWebSocketHandler {

    List<HashMap<String, Object>> list = new ArrayList<>(); // 웹 소켓 세션을 담아둘 리스트

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지 발송
        String msg = message.getPayload();
        JSONObject jsonObject = jsonToObjectParser(msg); // JSON 문자열을 파싱하여 JSONObject로 변환

        String rNumber = (String) jsonObject.get("roomNumber"); // 방 번호를 가져옴
        HashMap<String, Object> temp = new HashMap<>(); // 임시로 사용할 해시맵

        if (!list.isEmpty()) { // 리스트가 비어있지 않으면
            for (HashMap<String, Object> stringObjectHashMap : list) {
                String roomNumber = (String) stringObjectHashMap.get("roomNumber");
                // 만약 같은 방이 존재한다면
                if (roomNumber.equals(rNumber)) {
                    // 해당 방 번호의 세션 리스트의 존재하는 모든 object 값을 가져옴
                    temp = stringObjectHashMap;
                    break;
                }
            }
        }

        for (String key : temp.keySet()) { // 임시 해시맵의 모든 키에 대해 반복
            WebSocketSession webSocketSession = (WebSocketSession) temp.get(key);
            webSocketSession.sendMessage(new TextMessage(jsonObject.toJSONString())); // 메시지를 해당 세션으로 보냄
        }
    }

    private JSONObject jsonToObjectParser(String jsonStr) {
        // org.json.simple.parser.JSONParser
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr); // JSON 문자열을 파싱하여 JSONObject로 변환
        } catch (ParseException e) {
            throw new RuntimeException(e); // 파싱 오류 발생 시 런타임 예외 발생
        }
        return obj; // 변환된 JSONObject 반환
    }

    // 소켓 연결
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        // 소켓 연결이 되면 현재 세션을 맵에 담는다
        boolean flag = false;
        String url = session.getUri().toString();
        System.out.println("url : " + url);
        String roomNumber = url.split("/chatting/")[1];
        System.out.println("room number : " + roomNumber);
        int idx = list.size();
        if (!list.isEmpty()) {
            for (int i = 0; i < idx; i++) {
                String rNumber = (String) list.get(i).get("roomNumber");
                if (rNumber.equals(roomNumber)) {
                    flag = true;
                    idx = i;
                    break;
                }
            }
        }
        // 존재하는 방이라면 세션만 추가
        if (flag) {
            HashMap<String, Object> map = list.get(idx);
            map.put(session.getId(), session);

        }

        // 맵에 세션 정보를 담기
        HashMap<String, Object> sessionMap = new HashMap<>(); // 새로운 세션을 담을 해시맵 생성
        sessionMap.put("roomNumber", "default"); // 기본 방 번호를 설정 (필요에 따라 변경 가능)
        sessionMap.put(session.getId(), session); // 세션 ID와 세션 객체를 맵에 추가
        list.add(sessionMap); // 리스트에 맵을 추가

        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId()); // 세션 ID를 JSON 객체에 추가
        session.sendMessage(new TextMessage(obj.toJSONString())); // 메시지를 클라이언트로 보냄
    }

    // 소켓 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 리스트에서 해당 세션 정보를 제거
        list.removeIf(sessionMap -> sessionMap.containsKey(session.getId()));
        super.afterConnectionClosed(session, status);
    }
}
