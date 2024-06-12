package javaz.network;

// UDP
// 신뢰도 고려 안함, 빠른 속도가 필요한 경우에 사용
// 데이터 분실 및 전송과 수신 순서 불일치 가능

// DatagramSocket
// UDP Protocol을 사용하는 소켓을 생성한느 클래스
// 서버 소켓과 클라이언트 소켓 구분하지 않음

// DatagramPacket
// UDP 패킷을 생성하여 데이터를 전송하는 클래스
// 목적지 주소와 포트 번호를 포함

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {
    public static void main(String[] args) throws IOException {
        System.out.println("** RECEIVER START **");
        byte[] buf = new byte[256];

        DatagramSocket socket = new DatagramSocket(5000);
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        System.out.println(new String(buf));
        socket.close();

    }
}
