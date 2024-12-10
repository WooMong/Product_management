// 제품 정보 테이블 데이터 표현을 위한 클래스
public class goods 
{
	// 컬럼 정보에 따른 필드 선언
	/**제품 이름*/
	private String name_FD;
	/**일련 번호*/
	private String SNum_FD;
	/**공장 시기*/
	private String makeDAY_FD;
	/**제품 번호*/
	private String pdtNO_FD;
	/**수량*/
	private String total_FD;
	
	/**제품 이름 가져오기 메소드*/
	public String GET_name_FD()
	{
		return name_FD;
	}
	/**제품 이름 넣기 메소드*/
	public void SET_name_FD(String name_fd)
	{
		name_FD = name_fd;
	}
	
	/**일련 번호 가져오기 메소드*/
	public String GET_SNum_FD()
	{
		return SNum_FD;
	}
	/**일련 번호 넣기 메소드*/
	public void SET_SNum_FD(String snum_fd)
	{
		SNum_FD = snum_fd;
	}
	
	/**공장 시기 가져오기 메소드*/
	public String GET_makeDAY_FD()
	{
		return makeDAY_FD;
	}
	/**공장 시기 넣기 메소드*/
	public void SET_makeDAY_FD(String makeday_fd)
	{
		makeDAY_FD = makeday_fd;
	}
	
	/**제품 번호 가져오기 메소드*/
	public String GET_pdtNO_FD()
	{
		return pdtNO_FD;
	}
	/**제품 번호 넣기 메소드*/
	public void SET_pdtNO_FD(String pdtno_fd)
	{
		pdtNO_FD = pdtno_fd;
	}
	
	/**수량 가져오기 메소드*/
	public String GET_total_FD()
	{
		return total_FD;
	}
	/**수량 넣기 메소드*/
	public void SET_total_FD(String total_fd)
	{
		total_FD = total_fd;
	}
}
