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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

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
		if(nickname == null) return; 			// 1.1 대화명을 입력하지 않은 경우 실행 중단

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
					br = new BufferedReader(					//2.1
							new InputStreamReader(clientSocket.getInputStream()));
					pw = new PrintWriter(clientSocket.getOutputStream(), true);
					pw.println(nickname + "#Hello Server!");	//2.2			
					
				} catch(ConnectException ce) {
					System.err.println(">> 서버에 연결하지 못했습니다.");
					System.err.println(">> 서버 가동 상태를 확인해 주세요.");
				} catch (IOException ie) {
					ie.printStackTrace();
				}
				
				new Thread(() -> {				//3
					try {
						while(br != null) {
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
				pw.println(-1);	//4.서버 연결 종료 처리 >> 서버에 -1 전송
				dispose();		//채팅 창닫기
				System.exit(0); //4.2 시스템 종료
				
				try {			//4.1 입출력 스트림 및 소켓 닫기
					if(pw != null) 				pw.close();
					if(br != null) 				br.close();
					if(clientSocket != null) 	clientSocket.close();
				} catch (IOException ie) {
					ie.printStackTrace();
				}
				
			}//END windowClosing()
		});//
	}// END MultiClient 기본 생성자
	

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 10 &&			//5
		   !chatTxt.getText().trim().isEmpty()) { 
			pw.println(chatTxt.getText());	//5.1 
			chatTxt.setText("");	//입력값 지우기
			chatTxt.requestFocus();	//입력 필드 포커스 맞추기
		}
	}//END keyReleased()

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == clearBtn) {		//6
			chatArea.setText("");	//입력값 지우기
			chatTxt.requestFocus();	//입력 필드 포커스 맞추기
		}
		
		//7.SAVE 버튼이 눌린 경우 이벤트 처리
		if(obj == saveBtn) {
			//파일 저장 대화창 띄우기
			JFileChooser fc = new JFileChooser("c:\\dev\\");
			fc.setAcceptAllFileFilterUsed(false); //파일 유형에 모든 항목 X
			
			FileNameExtensionFilter extFilter  //확장자 txt로 필터링
				= new FileNameExtensionFilter("텍스트(*.txt)", "txt");
			fc.addChoosableFileFilter(extFilter);
			
			//a.저장 버튼이 눌린 경우
			if(fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {	
				File file = fc.getSelectedFile();	//b
				
				if(file.exists()) { 				//c
					int returnVal = JOptionPane.showConfirmDialog(  //d
										this, 
										"파일이 존재합니다.\n덮어쓸까요?",
										"알림",
										JOptionPane.YES_NO_OPTION);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						fileSave(file.toString());
					}
				} else { 							//e
					String filename = file.toString().endsWith(".txt")
										?file.toString() 
										:file + ".txt";
					fileSave(filename);
				}
			}//END 저장 버튼이 눌린 경우
		}
	}//END actionPerformed()

	public void fileSave(String filename) {
		try(FileWriter fw = new FileWriter(filename)) {
			fw.write(chatArea.getText());
			
			JOptionPane.showMessageDialog(	//파일에 대화 내용 저장 후 알림창 표시
					this, 
					"대화 내용이 저장되었습니다.",
					"알림",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//END fileSave()
	
	public static void main(String[] args) {
		new MultiClient();
	}
}

































