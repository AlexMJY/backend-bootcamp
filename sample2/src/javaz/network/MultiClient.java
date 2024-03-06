//package javaz.network;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ConnectException;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class MultiClient extends JFrame implements ActionListener, KeyListener {
//    private static final int SERVER_PORT = 5000;
//    private BufferedReader br;
//    private PrintWriter pw;
//    private Socket clientSocket;
//
//    private JLabel titleLbl, nicknameLbl;
//    private JTextField chatTxt;
//    private JButton clearBtn, saveBtn;
//    private JTextArea chatArea;
//    private JPanel panel;
//    private JScrollPane scrollPane;
//    private String nickname;
//
//
//    private static final String DATE_PTN = "--- yyyy년 MM월 dd일 ---";
//    private static final String TIME_PTN = "[a hh:mm:ss]";
//
//    public MultiClient() throws IOException {  // 채팅 창 UI 설정
//
//        nickname = JOptionPane.showInputDialog(">> 대화명을 입력해주세요");
//        if (nickname == null)   return;
//
//
//        titleLbl    = new JLabel("JAVA CHAT v.1", JLabel.CENTER);
//        nicknameLbl = new JLabel(nickname);
//        chatTxt    = new JTextField(10);
//        clearBtn    = new JButton("CLEAR");
//        saveBtn    = new JButton("SAVE");
//        chatArea    = new JTextArea();
//        panel       = new JPanel();
//
//        titleLbl.setFont(new Font("Arial Black", Font.BOLD, 15));
//        titleLbl.setPreferredSize(new Dimension(350, 40));
//
//        chatArea.setLineWrap(true);
//        chatArea.setWrapStyleWord(true);
//        chatArea.setEditable(false);
//        scrollPane = new JScrollPane(chatArea,
//                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
//                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//
//        chatTxt.addKeyListener(this);
//        clearBtn.addActionListener(this);
//        saveBtn.addActionListener(this);
//
//        panel.add(nicknameLbl);
//        panel.add(chatTxt);
//        panel.add(clearBtn);
//        panel.add(saveBtn);
//
//        add(scrollPane, "Center");
//        add(titleLbl, "North");
//        add(panel, "South");
//        setSize(350, 300);
//        setTitle("JAVA CHAT v.1");
//        setLocation(300, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//
//        addWindowFocusListener(new WindowAdapter() {
//            // 채팅 창 오픈 시 서버 연결 및 입출력 스트림 생성
//            @Override
//            public void windowOpened(WindowEvent e) {
//                //2. 서버 연결하여 clientSocket 객체 생성
//                //2.1 입출력 스트림 생성
//                //2.2 서버로 "nickname#Hello Sever!" 전송
//                try {
//                    Socket clientSocket = new Socket("localhost", SERVER_PORT);
//                    // 서버로 메시지를 보낼 스트림 생성
//                    br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //2.1
//                    pw = new PrintWriter(clientSocket.getOutputStream(), true);
//                    pw.println(nickname + "#Hello Server!"); //2.2
//
//
//                } catch (ConnectException ex) {
//                    System.err.println("서버 가동상태를 확인해주세요.");
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//
//                //3. 서버에서 더 이상 읽어올 값이 없을 때까지
//                //   메시지를 반복하여 수신하고 채팅 화면에 덧붙이기 >> 스레드로 처리하고 시작시키기
//                chatArea.append("~~~");
//            }
//
//            // 채팅 창 종료 시
//            @Override
//            public void windowClosing(WindowEvent e) {
//                //4. 서버 연결 종료 처리
//                System.out.println("WINDOW CLOSING....");
//                // 서버에 -1 전송 : pw.println(-1)
//                dispose();  // 채팅 창닫기
//                //4.1 입출력 스트림 및 소켓 닫기
//                //4.2 시스템 종료
//            }
//        }); {
//
//        }
//    }
//
//
//
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        //5. 엔터키 입력 시 - 서버로 메시지 전송, 값이 입력되어 있는 경우
//
//        if (e.getKeyCode() == 10) {
//            //5.1 입력값을 서버로 전송하고
//            System.out.println("입력 값 : " + chatTxt.getText());
//
//            chatTxt.setText(""); // 입력값 지우기
//            chatTxt.requestFocus(); // 입력 필드 포커스 맞추기
//        }
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object obj = e.getSource();
//        //6. CLEAR 버튼이 눌린 경우
//        if (obj == clearBtn) {
//            System.out.println("clear btn");
//            chatTxt.setText(""); // 입력값 지우기
//            chatTxt.requestFocus(); // 입력 필드 포커스 맞추기
//        }
//        //7. SAVE 버튼이 눌린 경우
//        if (obj == saveBtn) {
//            System.out.println("save btn");
//            chatTxt.setText(""); // 입력값 지우기
//            chatTxt.requestFocus(); // 입력 필드 포커스 맞추기
//
//        }
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        new MultiClient();
//    }
//}

package javaz.network;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class MultiClient extends JFrame implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;
    private static final int SERVER_PORT = 5000;
    private BufferedReader br;		//서버의 메시지 읽기
    private PrintWriter pw;			//서버에게 메시지 보내기
    private Socket clientSocket;

    private JLabel titleLbl, nicknameLbl;//닉네임 표시 레이블
    private JTextField chatTxt;			//채팅 내용 입력 필드
    private JButton clearBtn, saveBtn;	//CLEAR, SAVE 버튼
    private JTextArea chatArea;			//채팅 내용 표시 영역
    private JPanel panel;
    private JScrollPane scrollPane;
    private String nickname;

    public MultiClient() { // 채팅 창 UI 설정 ---------------------
        nickname = JOptionPane.showInputDialog(
                "대화명을 입력해 주세요."); 	// 1.대화명을 입력받아 nickname에 저장
        if(nickname == null) {
            return; 			// 1.1 대화명을 입력하지 않은 경우 실행 중단
        }



        titleLbl = new JLabel("JAVA CHAT v.1", JLabel.CENTER);
        nicknameLbl = new JLabel(nickname);
        chatTxt = new JTextField(10);
        clearBtn = new JButton("CLEAR");
        saveBtn = new JButton("SAVE");
        chatArea = new JTextArea();
        panel = new JPanel();

        titleLbl.setFont(new Font("Arial Black", Font.BOLD, 15));
        titleLbl.setPreferredSize(new Dimension(350, 40));

        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setEditable(false);
        scrollPane = new JScrollPane(chatArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        chatTxt.addKeyListener(this);
        clearBtn.addActionListener(this);
        saveBtn.addActionListener(this);

        panel.add(nicknameLbl);
        panel.add(chatTxt);
        panel.add(clearBtn);
        panel.add(saveBtn);

        add(scrollPane, "Center");
        add(titleLbl, "North");
        add(panel, "South");
        setSize(350, 300);
        setTitle("JAVA CHAT v.1");
        setLocation(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override		//채팅 창 오픈 시 --------------------------
            public void windowOpened(WindowEvent e) {
                try{
                    clientSocket = new Socket("localhost", SERVER_PORT); //2
                    pw = new PrintWriter(clientSocket.getOutputStream(), true);
                    pw.println(nickname + "#Hello Server!");	//2.2

                    br = new BufferedReader(					//2.1
                            new InputStreamReader(clientSocket.getInputStream()));
                } catch(ConnectException ce) {
                    System.err.println(">> 서버에 연결하지 못했습니다.");
                    System.err.println(">> 서버 가동 상태를 확인해 주세요.");
                } catch (IOException ie) {
                    ie.printStackTrace();
                }

                //3.서버에서 더 이상 읽어올 값이 없을 때까지
                //  메시지를 반복하여 수신하고 채팅 화면에 덧붙이기
                //  >> 스레드로 처리하고 시작시키기
                new Thread(() -> {
                    try {
                        while (br != null) {
                            chatArea.append(br.readLine() + "\n");
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }).start();
                chatTxt.requestFocus();
            }//END windowOpened()

            //채팅 창 종료시 --------------------------
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("WINDOW CLOSING....");
                //4.서버 연결 종료 처리 >> 서버에 -1 전송
                dispose();	//채팅 창닫기

                System.exit(0); //4.2 시스템 종료

                try { //4.1 입출력 스트림 및 소켓 닫기
                    if (pw != null) pw.close();
                    if (br != null) br.close();
                    if (clientSocket != null) clientSocket.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }//END windowClosing()
        });
    }// END MultiClient 기본 생성자


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        //5.엔터키 입력 시 - 값이 입력되어 있는 경우
        if(e.getKeyCode() == 10 && !chatTxt.getText().trim().isEmpty())  {
            System.out.println("입력 값 : " + chatTxt.getText());
            //5.1 입력값을 서버로 전송하고
            pw.println(chatTxt.getText());
            chatTxt.setText("");	//입력값 지우기
            chatTxt.requestFocus();	//입력 필드 포커스 맞추기
        }
    }//END keyReleased()

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        //6.CLEAR 버튼이 눌린 경우 이벤트 처리
        if(obj == clearBtn) {
            System.out.println("CLEAR 버튼 클릭됨!!");
            chatTxt.setText("");	//입력값 지우기
            chatTxt.requestFocus();	//입력 필드 포커스 맞추기
        }

        //7.SAVE 버튼이 눌린 경우 이벤트 처리
        if(obj == saveBtn) {
            System.out.println("SAVE 버튼 클릭됨!!");
            chatTxt.setText("");	//입력값 지우기
            chatTxt.requestFocus();	//입력 필드 포커스 맞추기
        }
    }//END actionPerformed()


    public static void main(String[] args) {
        new MultiClient();
    }
}