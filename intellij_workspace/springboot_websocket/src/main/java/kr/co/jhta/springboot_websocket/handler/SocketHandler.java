package kr.co.jhta.springboot_websocket.handler;

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
import java.util.Objects;

@Component
public class SocketHandler extends TextWebSocketHandler {

//    List<HashMap<String, Objects>> list = new ArrayList<>(); // 웹 소켓 세션을 담아둘 리스트
    HashMap<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지 발송
        String msg = message.getPayload();
        JSONObject jsonObject = jsonToObjectParser(msg);

        for (String key : sessions.keySet()) {
            WebSocketSession webSocketSession = sessions.get(key);
            webSocketSession.sendMessage(new TextMessage(jsonObject.toJSONString()));
        }

//        super.handleTextMessage(session, message);
    }

    private JSONObject jsonToObjectParser(String jsonStr) {
        // org.json.simple.parser.JSONParser
        JSONParser parser = new JSONParser();
        JSONObject obj = null;

        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        session.sendMessage(new TextMessage(obj.toJSONString()));

        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
