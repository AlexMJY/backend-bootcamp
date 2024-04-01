package javaz.util;

import java.util.LinkedList;
import java.util.Queue;

class Message {
	private String command;	//sms, katalk, alarm
	private String from;	//Lee, Han,    clock
	private String content; //
	
	public Message(String command, String from, String content) {
		this.command = command;
		this.from = from;
		this.content = content;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

public class MessageQueue {
	public static void main(String[] args) {
		Queue<Message> msgQ = new LinkedList<Message>();
		msgQ.offer(new Message("sms",   "Lee",   "Hi~"));
		msgQ.offer(new Message("alarm", "clock", "일어나!!"));
		msgQ.offer(new Message("katalk", "Han", "ㅋㅋㅋㅋㅋ"));
		
		System.out.println("도착 메시지 건 수 : " + msgQ.size());
		//3. msgQ의 내용을 화면에 표시
		while(!msgQ.isEmpty()) {	//메시지큐가 비어있지 않다면
			Message msg = msgQ.poll();
			String post = "";
			switch (msg.getCommand()) {
			case "sms":		post = "님에게서 문자 도착";		break;
			case "katalk":	post = "님에게서 카톡 수신";		break;
			case "alarm":	post = "알람 울림";			break;				
			}
			System.out.println(msg.getFrom() + post);
		}
		
		System.out.println("----------------------");
		System.out.println("도착 메시지 건 수 : "+ msgQ.size());
	}
}













