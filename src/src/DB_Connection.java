import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
// 라이브러리를 정상적으로 넣었는데 오류가 나면 데이터베이스의 이름, 아이디, 비밀번호를 체크해줘야한다.
public class DB_Connection
{
		// 드라이버 설정
		String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		
		// 데이터베이스 URL 설정
		String url = "jdbc:mysql://localhost:3306/goodsdb?"; // 데이터베이스 이름만 수정하자
		
		
		Connection conn;
				
		String id = "root";   // DB에 로그인할 ID
		String pw = "1234";   // MYSQL 설정 시 입력한 PASSWORD

		PreparedStatement pstmt;
		ResultSet rs;
		String sql;
		Vector<String> items = null;
		
		/**DB연결 메소드*/ 
		public void connectDB() 
		{
			try 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url,id,pw);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		/**DB 연결 종료 메소드*/
		public void closeDB()
		{
			try 
			{
				pstmt.close();
				conn.close();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		/**전체 제품 목록을 가지고 오는 메소드*/
		public ArrayList<goods> allgoods()
		{
			connectDB();
			sql = "select * from goods";
			
			// 전체 검색 데이터를 전달하기 위한 ArrayList
			ArrayList<goods> datas = new ArrayList<goods>();
			
			items = new Vector<String>();
			items.add("연번선택");
			
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				// 검색된 데이터수 만큼 루프를 돌며 goods 객체를 만들고 이를 다시 ArrayList 에 추가함.
				while(rs.next()) {
					goods gs = new goods();
					gs.SET_name_FD(rs.getString("name_FD"));
					gs.SET_SNum_FD(rs.getString("SNum_FD"));
					gs.SET_makeDAY_FD(rs.getString("makeDAY_FD"));
					gs.SET_pdtNO_FD(rs.getString("pdtNO_FD"));
					gs.SET_total_FD(rs.getString("total_FD"));
					datas.add(gs);
					items.add(String.valueOf(rs.getString("SNum_FD")));
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				return null;
			}
			finally 
			{
				closeDB();
			}
			return datas;
		}// 제품 전체 목록 가져오기 메소드
		
		/**새로운 제품을 등록하는 메소드*/
		public boolean insertgoods(goods gs) 
		{
			connectDB();
			
			sql = "insert into goods(name_FD,SNum_FD,makeDAY_FD,pdtNO_FD,total_FD) values(?,?,?,?,?)";
			try 
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, gs.GET_name_FD());
				pstmt.setString(2, gs.GET_SNum_FD());
				pstmt.setString(3, gs.GET_makeDAY_FD());
				pstmt.setString(4, gs.GET_pdtNO_FD());
				pstmt.setString(5, gs.GET_total_FD());
				pstmt.executeUpdate();
				
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			finally 
			{
				closeDB();
			}
			return true;
		}//새로운 제품을 등록하는 메소드
		
		/** 선택한 제품 관리 일련번호에 해당하는 제품 정보를 가지고 오는 메소드*/
		public goods selectgoods(String SNUM)
		{
			connectDB();
			sql = "select * from goods where SNum_FD=?";
			goods gs = null;
			try
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, SNUM);
				rs = pstmt.executeQuery();
				rs.next();
				gs = new goods();
				gs.SET_name_FD(rs.getString("name_FD"));
				gs.SET_makeDAY_FD(rs.getString("makeDAY_FD"));
				gs.SET_pdtNO_FD(rs.getString("pdtNO_FD"));
				gs.SET_total_FD(rs.getString("total_FD"));
				
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
				return null;
			}
			finally 
			{
				closeDB();
			}
			return gs;
		}
		
		/**수정한 정보롤 제품 정보를 업데이트 하는 메소드*/
		public boolean updategoods(goods gs)
		{
			connectDB();
			sql = "update goods set name_FD=?, makeDAY_FD=?, pdtNO_FD=?, total_FD=? where SNum_FD=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, gs.GET_name_FD());
				pstmt.setString(2, gs.GET_makeDAY_FD());
				pstmt.setString(3, gs.GET_pdtNO_FD());
				pstmt.setString(4, gs.GET_total_FD());
				pstmt.setString(5, gs.GET_SNum_FD());
				pstmt.executeUpdate();
				
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				return false;
			}
			finally 
			{
				closeDB();
			}
			return true;
		}
		
		/** 선택한 제품을 삭제하는 메소드*/
		public boolean deletegoods(String SNUM) 
		{
			connectDB();
			sql = "delete from goods where SNum_FD=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, SNUM);
				pstmt.executeUpdate();
			}
			catch(SQLException e) 
			{
				e.printStackTrace();
				return false;
			}
			finally 
			{
				closeDB();
			}
			return true;
		}
}

