import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**제품 관리 탭
 * ::: 우리가 다뤄야 할 변수 이름들 :::
 * 입력란
 * input_name_FD    제품 이름 입력란
 * input_SNum_FD    제품 일련번호 입력란
 * input_makeDAY_FD 제품 공장시기 입력란
 * input_pdtNO_FD   제품 번호 입력란
 * 
 * 버튼
 * add_BT 추가
 * adjust_BT 수정
 * delete_BT 삭제
 * search_BT 검색
 * 
 * 텍스트 에어리어 <------------------- 스크롤 기능 추가하면 좋을듯
 * output_list_TA   목록
 * */
class PRODUCT_management extends JPanel
{
	
	private static final long serialVersionUID = 1L; /**<<< 무시하세요 */
	
	// 라벨
	JLabel name_label; 	  /*제품 이름 라벨*/
	JLabel SNum_label;	  /*일련 번호 라벨*/
	JLabel makeDAY_label; /*공장 시기 라벨*/
	JLabel pdtNO_label;   /*제품 번호 라벨*/
	
	/**blank_label은 UI 꾸민다고 선언한거니깐 무시해요*/
	JLabel blank_label;
	JLabel blank_label2;
	
	JLabel list_label;	  /*제품 목록 라벨*/
	
	// 입력란
	private JTextField input_name_FD;
	private JTextField input_SNum_FD;
	private JTextField input_makeDAY_FD;
	private JTextField input_pdtNO_FD;
	
	// 목록
	private JTextArea output_list_TA; 
	
	/**제품 관리 탭 생성자*/
	public PRODUCT_management()
	{
		// 라벨처리
		name_label = new JLabel("제품 이름");
		SNum_label = new JLabel("일련 번호");
		makeDAY_label = new JLabel("공장 시기");
		pdtNO_label = new JLabel("제품 번호");
		blank_label = new JLabel(" ");
		blank_label2 = new JLabel(" ");
		list_label = new JLabel("  제품 목록");
		
		// 버튼 객체 생성
		JButton add_BT = new JButton("추가");
		JButton adjust_BT = new JButton("수정");
		JButton delete_BT = new JButton("삭제");
		JButton search_BT = new JButton("검색");
		
		// 텍스트 필드 칸 정립
		input_name_FD = new JTextField(60);
		input_SNum_FD = new JTextField(60); 
		input_makeDAY_FD = new JTextField(60);
		input_pdtNO_FD = new JTextField(60); 
		
		// 텍스트 에이리어 줄 칸 정립
		output_list_TA = new JTextArea(17 , 67 );
		
		// 패널 생성
		JPanel LEFT_SIDE = new JPanel(); /*왼쪽 배치 패널*/
		JPanel RIGHT_SIDE = new JPanel(); /*오른쪽 배치 패널*/
		JPanel SOUTH_SIDE = new JPanel(); /*아래쪽 배치 패널*/
		
		// 패널에 필드,라벨 넣기
		LEFT_SIDE.add(name_label);
		LEFT_SIDE.add(input_name_FD);	 /*이름 입력란,라벨*/
		LEFT_SIDE.add(SNum_label);
		LEFT_SIDE.add(input_SNum_FD); 	 /*일련번호 입력란,라벨*/
		LEFT_SIDE.add(makeDAY_label);
		LEFT_SIDE.add(input_makeDAY_FD); /*공장시기 입력란,라벨*/
		LEFT_SIDE.add(pdtNO_label);
		LEFT_SIDE.add(input_pdtNO_FD);	 /*제품번호 입력란,라벨*/
		LEFT_SIDE.add(blank_label2);
		LEFT_SIDE.add(list_label);
		
		RIGHT_SIDE.add(add_BT);
		RIGHT_SIDE.add(adjust_BT);
		RIGHT_SIDE.add(delete_BT);
		RIGHT_SIDE.add(blank_label);
		RIGHT_SIDE.add(search_BT);
		
		SOUTH_SIDE.add(output_list_TA);
		
		// GridLayout 배치 관리자 생성
		GridLayout gl_l = new GridLayout(10,1,0,0);
		GridLayout gl_r = new GridLayout(5,1,1,10);
		GridLayout gl_s = new GridLayout(1,1,0,0);
		
		// 열 정리하기
		LEFT_SIDE.setLayout(gl_l);
		RIGHT_SIDE.setLayout(gl_r);
		SOUTH_SIDE.setLayout(gl_s);
		
		JPanel SET_WEST = new JPanel();
		JPanel SET_EAST = new JPanel();
		JPanel SET_SOUTH = new JPanel();
		
		SET_WEST.add(LEFT_SIDE);
		SET_EAST.add(RIGHT_SIDE);
		SET_SOUTH.add(SOUTH_SIDE);
		
		this.setLayout(new BorderLayout());
		
		this.add(SET_SOUTH,BorderLayout.SOUTH);
		this.add(SET_WEST,BorderLayout.CENTER);
		this.add(SET_EAST,BorderLayout.EAST);
		
		
		/**요기 액션 리스너 등록 하고 액션이벤트 적어주면 됩니다. 
		 * implements ActionListener 해줘야하는거 잊지마요 
		 * */
	}
}

/**메뉴(초기화면)*/
class GUI_main_TAB extends JFrame
{	
	/**무시하세요*/
	private static final long serialVersionUID = 1L;
	
	/**관리자
	 * 아이디 & 비밀번호 */
	static String ID = "root";
	static String PW = "0000";
	
	/** UI 생성자*/
	public GUI_main_TAB()
	{
		// 그룹폴더 객체 생성
		JTabbedPane main_tab = new JTabbedPane();
		
		// 각 패널 객체 생성
		PRODUCT_management pdt_management = new PRODUCT_management();
		
		/**아이디와 비밀번호를 다이얼로그 형태로 입력받고 각 변수에 초기화한다.*/
		String answer_id = JOptionPane.showInputDialog("아이디 입력");
		System.out.println(answer_id);
		String answer_pw = JOptionPane.showInputDialog("비밀번호 입력");
		System.out.println(answer_pw);
		
		/**아이디와 비밀번호가 일치 할 경우 탭 생성.*/
		if(answer_id.equals(ID) && answer_pw.equals(PW))
		{
			main_tab.addTab("제품 관리",pdt_management);
		}else// 일치하지 않을 경우 에러메세지 출력
		{
			JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 일치하지 않습니다!", "Message",JOptionPane.ERROR_MESSAGE );
		}
		// 컨테이너 생성
		Container ct = getContentPane();
		
		// 컨테이너에 그룹폴더 객체 추가
		ct.add(main_tab);
		
		// 사이즈
		setTitle("제품 관리 프로그램");
		setSize(760,550);
		setVisible(true);
		
		// 화면고정
		setResizable(false); 
						
		// 원도우창 종료 시 프로세스까지 닫음
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/**실행부*/
public class Main
{
	public static void main(String[] args)
	{
		new GUI_main_TAB();
	}

}
