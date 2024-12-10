import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
 * input_total_FD   제품 수량
 * 
 * 버튼
 * add_BT 추가
 * adjust_BT 수정
 * delete_BT 삭제
 * search_BT 검색
 * 
 * 텍스트 에어리어
 * output_list_TA   목록
 * */
class PRODUCT_management extends JPanel implements ActionListener
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
	JTextField input_name_FD = new JTextField(60);
	JTextField input_SNum_FD = new JTextField(60); 
	JTextField input_makeDAY_FD = new JTextField(60);
	JTextField input_pdtNO_FD = new JTextField(60); 
	JTextField input_total_FD = new JTextField(10);
	// 목록
	JTextArea output_list_TA; 
	
	// 버튼 객체 생성
	JButton add_BT = new JButton("추가");
	JButton adjust_BT = new JButton("수정");
	JButton delete_BT = new JButton("삭제");
	JButton search_BT = new JButton("검색");

	/**데이터 베이스 연동 클래스 인스턴스*/
	DB_Connection DB_Conn = new DB_Connection();
	
	/** 전체 상품목록 출력을 위한 ArrayList 생성 */
	ArrayList<goods> data = new  ArrayList<goods>();
	
	/** 제품정보 출력을 위한 제품 인스턴스 생성*/
	goods goods;
	
	String msg = "";
	
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
		list_label = new JLabel("         제품 목록");
		JLabel total_label = new JLabel("수량");
		JLabel blank_label3 = new JLabel(" ");
		JLabel blank_label4 = new JLabel(" ");
//		JLabel blank_label5 = new JLabel(" ");
//		JLabel blank_label6 = new JLabel(" ");
//		JLabel blank_label7 = new JLabel(" ");
		
		// 텍스트 에이리어 줄 칸 정립 || 스크롤 기능 추가 || 수정불가
		output_list_TA = new JTextArea(20 , 67 );
		output_list_TA.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(output_list_TA);
		//output_list_TA.append("  제품이름\t\t일련번호\t\t공장시기\t\t제품번호\t   수량\n");
		
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
		LEFT_SIDE.add(blank_label3);
		LEFT_SIDE.add(blank_label4);
		//LEFT_SIDE.add(blank_label5);
		//LEFT_SIDE.add(blank_label6);
		//LEFT_SIDE.add(blank_label7);
		LEFT_SIDE.add(list_label);
		
		RIGHT_SIDE.add(add_BT);
		RIGHT_SIDE.add(adjust_BT);
		RIGHT_SIDE.add(delete_BT);
		RIGHT_SIDE.add(blank_label);
		RIGHT_SIDE.add(search_BT);
		RIGHT_SIDE.add(total_label);
		RIGHT_SIDE.add(input_total_FD);
		
		SOUTH_SIDE.add(scrollPane);

		
		// GridLayout 배치 관리자 생성
		GridLayout gl_l = new GridLayout(15,1,0,0);
		GridLayout gl_r = new GridLayout(7,1,1,3);
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
	
		// 화면 데이터 갱신
		refreshDATA();
		
		// 이벤트 리스너 등록
		add_BT.addActionListener(this);
		adjust_BT.addActionListener(this);
		delete_BT.addActionListener(this);
		search_BT.addActionListener(this);
		
	}//PRODUCT_management 생성자
	
	/**등록 , 삭제 , 전체목록 조회시 필드 초기화*/
	public void clearField() 
	{
		input_name_FD.setText("");
		input_SNum_FD.setText("");
		input_makeDAY_FD.setText("");
		input_pdtNO_FD.setText("");
		input_total_FD.setText("");
	}//텍스트필드 초기화
	
	/**전체 데이터 출력*/
	public void refreshDATA()
	{
		output_list_TA.setText("");
		clearField();
		data = DB_Conn.allgoods();
		output_list_TA.append("제품이름\t\t일련번호\t\t공장시기\t\t제품번호\t   수량\n");
		if(data != null) 
		{
			// ArrayList 의 전체 데이터를 형식에 맞춰 출력
			for(goods p : data) 
			{
				StringBuffer sb = new StringBuffer();
				sb.append(p.GET_name_FD() + "\t\t");
				sb.append(p.GET_SNum_FD()+"\t\t");
				sb.append(p.GET_makeDAY_FD()+"\t\t");
				sb.append(p.GET_pdtNO_FD()+ "\t");
				sb.append("   "+p.GET_total_FD() + "\n");
				output_list_TA.append(sb.toString());
			}
		}
	}//refreshDATA

	/**이벤트 처리*/
	public void actionPerformed(ActionEvent e)
	{
		//Object obj = e.getSource();
		String HowPushBT =  e.getActionCommand();
		
		/**등록 버튼을 클릭한 경우.*/
		if(HowPushBT == "추가") 
		{
			System.out.println("추가버튼 누름");
			goods = new goods();
			goods.SET_name_FD(input_name_FD.getText());
			goods.SET_SNum_FD(input_SNum_FD.getText());
			goods.SET_makeDAY_FD(input_makeDAY_FD.getText());
			goods.SET_pdtNO_FD(input_pdtNO_FD.getText());
			goods.SET_total_FD(input_total_FD.getText());
			
			if(DB_Conn.insertgoods(goods))
			{
				JOptionPane.showMessageDialog(this, "제품 등록 완료", " ",JOptionPane.DEFAULT_OPTION);
				System.out.println("제품을 등록했습니다.");
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "제품 등록 실패", " ",JOptionPane.DEFAULT_OPTION);
				System.out.println("제품을 등록실패했습니다.");
			}
			// 결과 데이트 출력
			refreshDATA();
		}//add button
		
		/**검색 버튼을 클릭한 경우*/
		if(HowPushBT == "검색") 
		{
			System.out.println("검색버튼 누름");
			/**answer_SNum으로 일련번호를 입력받는다.*/
			String answer_SNum = JOptionPane.showInputDialog("검색할 제품의 일련번호를 입력");
			goods = DB_Conn.selectgoods(answer_SNum);
			if(!answer_SNum.equals("몰라")) 
			{
				if(goods != null) 
				{
					JOptionPane.showMessageDialog(this, "제품 검색 완료", " ",JOptionPane.DEFAULT_OPTION);
					
					input_name_FD.setText(goods.GET_name_FD());
					input_SNum_FD.setText(answer_SNum);
					input_makeDAY_FD.setText(goods.GET_makeDAY_FD());
					input_pdtNO_FD.setText(goods.GET_pdtNO_FD());
					input_total_FD.setText(goods.GET_total_FD());
				}
				else
				{
					JOptionPane.showMessageDialog(this, "검색하신 제품은 등록되어 있지 않습니다.", " ",JOptionPane.DEFAULT_OPTION);
					System.out.println("검색하신 제품은 존재하지 않습니다.");
				}
			}
			else
			{
				// 결과 데이터 출력
				refreshDATA();
			}
		}// search button
		
		/**수정 버튼을 클릭한 경우.*/ 
		if(HowPushBT == "수정")
		{
			goods = new goods();
			System.out.println("수정버튼 누름");
			goods.SET_name_FD(input_name_FD.getText());
			goods.SET_SNum_FD(input_SNum_FD.getText());
			goods.SET_makeDAY_FD(input_makeDAY_FD.getText());
			goods.SET_pdtNO_FD(input_pdtNO_FD.getText());
			goods.SET_total_FD(input_total_FD.getText());
			
			if(DB_Conn.updategoods(goods)) 
			{
				JOptionPane.showMessageDialog(this, "제품 수정 완료", " ",JOptionPane.DEFAULT_OPTION);
				System.out.println("제품 정보를 수정했습니다.");
			}
			else 
			{
				JOptionPane.showMessageDialog(this, "제품 수정 실패", " ",JOptionPane.DEFAULT_OPTION);
				System.out.println("제품 정보를 수정실패했습니다.");
			}
			// 결과 데이터 출력
			refreshDATA();
		}//adjust button
		
		/**삭제 버튼을 클릭한 경우*/
		if(HowPushBT == "삭제") 
		{
			System.out.println("삭제버튼 누름");
			String s = JOptionPane.showInputDialog("삭제할 제품의 일련번호");
			
			if(s.equals("전체"))
			{
				JOptionPane.showMessageDialog(this, "전체 삭제는 되지 않습니다.", " ",JOptionPane.DEFAULT_OPTION);
				System.out.println("전체 삭제는 되지 않습니다.!");
			}
			else 
			{
				if(DB_Conn.deletegoods(s))
				{
					JOptionPane.showMessageDialog(this, "삭제되었습니다.", " ",JOptionPane.DEFAULT_OPTION);
					System.out.println("삭제되었습니다.");
					clearField();
				}
				else 
				{
					JOptionPane.showMessageDialog(this, "삭제가 불가능합니다.", " ",JOptionPane.DEFAULT_OPTION);
					System.out.println("삭제가 불가능합니다.");
					
				}
			}
			//  결과 데이터 출력
			refreshDATA();
		}//delete button
		
	}//action
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
		setSize(760,650);
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
