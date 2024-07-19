package kr.co.jhta.app.springboot_websocket.control;

import kr.co.jhta.app.springboot_websocket.dto.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class MainController {
    List<Room> roomList = new ArrayList<>();
    static int roomNumber  = 0 ;
    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/room")
    public String room() {
        return "room";
    }

    @PostMapping("/createRoom")
    @ResponseBody
    public List<Room> createRoom(@RequestParam  HashMap<Object, Object> params) {
        String roomName = (String) params.get("roomName");

        if(roomName != null && !roomName.trim().equals("")) {
            Room room = new Room();
            room.setRoomNumber(++roomNumber);
            room.setRoomName(roomName);
            roomList.add(room);
        }
        log.info("roomList : " + roomList);
        return roomList;
    }
    // 방정보 가져오기
    @RequestMapping("/getRoom")
    @ResponseBody
    public List<Room> getRoom(@RequestParam  HashMap<Object, Object> params) {
        return roomList;
    }

    @RequestMapping("/moveChat")
    public String moveChat(@RequestParam  String roomName,
                           @RequestParam int roomNumber, Model model) {

        List<Room> collect = roomList.stream()
                .filter(obj -> obj.getRoomNumber() == roomNumber)
                .collect(Collectors.toList());

        if(collect != null && collect.size() >0) {
            model.addAttribute("roomName", roomName);
            model.addAttribute("roomNumber", roomNumber);
            return "chat";
        }else{
            return "room";
        }
    }
}
