package org.doit.ik.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.doit.ik.domain.NoticeVO;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeDao {

	// ���������� ���� ��ȯ�ϴ� �޼���
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT "
				+ "FROM NOTICES "
				+ "WHERE "+field+" LIKE ?";

		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. ����
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. ����
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");

		// 3. ���
		ResultSet rs = st.executeQuery();
		rs.next();

		int cnt = rs.getInt("cnt");

		rs.close();
		st.close();
		con.close();

		return cnt;
	}

	// ���������� ����� List �÷������� ��ȯ
	public List<NoticeVO> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException
	{					

		int srow = 1 + (page-1)*15; // 1, 16, 31, 46, 61, ... an = a1 + (n-1)*d
		int erow = 15 + (page-1)*15; //15, 30, 45, 60, 75, ...

		String sql = " SELECT * "
				+ "  FROM ( "
				+ "          SELECT ROWNUM NUM, N.* "
				+ "          FROM ("
				+ "          SELECT * "
				+ "          FROM NOTICES "
				+ "          WHERE "+field+" LIKE ? "
				+ "          ORDER BY REGDATE DESC"
				+ "          ) N"
				+ "  ) "
				+  " WHERE NUM BETWEEN ? AND ? ";
		// 0. ����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. ����
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. ����
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, srow);
		st.setInt(3, erow);
		// 3. ���
		ResultSet rs = st.executeQuery();

		List<NoticeVO> list = new ArrayList<NoticeVO>();

		while(rs.next()){
			NoticeVO notice = new NoticeVO();
			notice.setSeq(rs.getString("seq"));
			notice.setTitle(rs.getString("title"));
			notice.setWriter(rs.getString("writer"));
			notice.setRegdate(rs.getDate("regdate"));
			notice.setHit(rs.getInt("hit"));
			notice.setContent(rs.getString("content"));
			notice.setFilesrc(rs.getString("filesrc"));

			list.add(notice);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		// 2. ������ ���̽� ������ ���� ������ ���� �ڵ� �ۼ�
		String sql = "DELETE NOTICES WHERE seq=?";
		// 0. ����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. ����
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. ����
		PreparedStatement st = con.prepareStatement(sql);	
		st.setString(1, seq);

		int rowCount = st.executeUpdate();

		return rowCount;
	}
	
	// �������� ����
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{

		// 2. ������ ���̽��� �����ϱ� ���� ������ �����ͺ��̽� ������ ���� �ڵ带 �ۼ�
		String sql = "UPDATE NOTICES SET title=?, content=?, filesrc=? WHERE seq=?";
		// 0. ����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. ����
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. ����
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setString(3, notice.getFilesrc());
		st.setString(4, notice.getSeq());		

		int rowCount = st.executeUpdate();

		return rowCount;
	}

	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM NOTICES WHERE seq="+seq;
		// 0. ����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. ����
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. ����
		Statement st = con.createStatement();
		// 3. ���
		ResultSet rs = st.executeQuery(sql);
		rs.next();

		NoticeVO notice = new NoticeVO();
		notice.setSeq(rs.getString("seq"));
		notice.setTitle(rs.getString("title"));
		notice.setWriter(rs.getString("writer"));
		notice.setRegdate(rs.getDate("regdate"));
		notice.setHit(rs.getInt("hit"));
		notice.setContent(rs.getString("content"));
		notice.setFilesrc(rs.getString("fileSrc"));

		rs.close();
		st.close();
		con.close();

		return notice;
	}

	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO NOTICES(seq, title, content, writer, regdate, hit, filesrc) "
					+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), ?, ?, 'dcha', SYSDATE, 0, ?)";
		// 0. ����̹� �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. ����
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"scott", "tiger");
		// 2. ����
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, notice.getTitle());
		st.setString(2, notice.getContent());
		st.setString(3, notice.getFilesrc());

		int rowCount = st.executeUpdate();

		st.close();
		con.close();

		return rowCount;
	}
}
