<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*"%>  
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<%
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	String url = "jdbc:odbc:board";
	String id = "";
	String pass = "";
	String password = "";
	
		try{			
			request.setCharacterEncoding("euc-kr");
			int idx = Integer.parseInt(request.getParameter("idx"));
			int pg = Integer.parseInt(request.getParameter("pg"));
			String title = request.getParameter("title");
			String memo = request.getParameter("memo");
			String passw = request.getParameter("password");
		
			Connection conn = DriverManager.getConnection(url,id,pass);
			Statement stmt = conn.createStatement();
		
			String sql = "SELECT PASSWORD FROM board WHERE NUM=" + idx;
			ResultSet rs = stmt.executeQuery(sql);
		
		 if(rs.next()){
				password = rs.getString(1);
		 }
		
		 if(password.equals(passw)) {
				sql = "UPDATE board SET TITLE='" + title+ "' ,MEMO='"+ memo +"' WHERE NUM=" + idx;				
				stmt.executeUpdate(sql);
				
%>
				  <script language=javascript>
				  	self.window.alert("���� �����Ǿ����ϴ�.");
				  	location.href="view.jsp?idx=<%=idx%>&pg=<%=pg%>";
				  </script>
<%

			rs.close();
			stmt.close();
			conn.close();
			
		} else {
%>
			<script language=javascript>
				self.window.alert("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				location.href="javascript:history.back()";
			</script>
<%			
		}
		 
 } catch(SQLException e) {
	out.println( e.toString() );
} 



%>