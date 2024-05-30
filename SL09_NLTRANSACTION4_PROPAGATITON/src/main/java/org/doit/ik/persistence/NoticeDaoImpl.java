package org.doit.ik.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.doit.ik.domain.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
// @Transactional => ���� �ִ°� ��� transaction ó���ϰڴ�
public class NoticeDaoImpl implements NoticeDao{
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
//	@Autowired
//	private DataSourceTransactionManager transactionManager;
	
//	@Autowired
//	private TransactionTemplate transactionTemplate;
	
	
	
	// ���������� ���� ��ȯ�ϴ� �޼���
	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT COUNT(*) CNT "
				+ "FROM NOTICES "
				+ "WHERE "+field+" LIKE :query";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("query", query);
		
		return this.npJdbcTemplate.queryForObject(sql, parameterSource, Integer.class);
	}

	// ���������� ����� List �÷������� ��ȯ
	@Override
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
				+ "          WHERE "+field+" LIKE :query "
				+ "          ORDER BY REGDATE DESC"
				+ "          ) N"
				+ "  ) "
				+  " WHERE NUM BETWEEN :srow AND :erow ";
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("query", "%"+query+"%");
		paramMap.put("srow", srow);
		paramMap.put("erow", erow);
		return this.npJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
		
	}
	
	@Override
	public int delete(String seq) throws ClassNotFoundException, SQLException
	{
		// 2. ������ ���̽� ������ ���� ������ ���� �ڵ� �ۼ�
		String sql = "DELETE NOTICES WHERE seq= :seq";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("seq", seq);
		
		return this.npJdbcTemplate.update(sql, parameterSource);
	}
	
	// �������� ����
	@Override
	public int update(NoticeVO notice) throws ClassNotFoundException, SQLException{

		// 2. ������ ���̽��� �����ϱ� ���� ������ �����ͺ��̽� ������ ���� �ڵ带 �ۼ�
		String sql = "UPDATE NOTICES SET title=:title, content=:content, filesrc=:filesrc WHERE seq=:seq";
		
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(notice);
		return this.npJdbcTemplate.update(sql, parameterSource);
		
		/*
		 * MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		 * parameterSource.addValue("title", notice.getTitle());
		 * parameterSource.addValue("content", notice.getContent());
		 * parameterSource.addValue("filesrc", notice.getFilesrc());
		 * parameterSource.addValue("title", notice.getTitle());
		 * 
		 * return this.npJdbcTemplate.update(sql, parameterSource);
		 */
	}
	
	@Override
	public NoticeVO getNotice(String seq) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM NOTICES WHERE seq= :seq ";
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
	      parameterSource.addValue("seq", seq );
	      
	      return this.npJdbcTemplate.queryForObject(sql
	            , parameterSource
	            , new BeanPropertyRowMapper<NoticeVO>(NoticeVO.class));
	}
	
	@Override
	@Transactional
	public int insert(NoticeVO notice) throws ClassNotFoundException, SQLException {
		// A. 공지사항 쓰기
	      String sql = "INSERT INTO NOTICES"
	            + " (SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) "
	            + " VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, :writer, SYSDATE, 0, :filesrc)";
	      // A
	      SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
	      npJdbcTemplate.update(sql,  parameterSource);

	      // B. �ۼ��� ����Ʈ 1����
	      String   sql2    = " UPDATE member "
	            + " SET point  = point + 1 "
	            + " WHERE id = :id "; 
	      // B
	      MapSqlParameterSource paramSource = new MapSqlParameterSource();
	      paramSource.addValue("id", "youngjin");      
	      int updateCount = npJdbcTemplate.update(sql2, paramSource);
	      
	      return updateCount;
		
	}
	
	// [1] Ʈ����� ó������ ���� �޼���
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {

		// 1. �������� ����
		String sql = "INSERT INTO NOTICES(seq, title, content, writer, regdate, hit, filesrc) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'jindol', SYSDATE, 0, :filesrc)";
		
		SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
		
		this.npJdbcTemplate.update(sql,  parameterSource);
		
		// 2. �ۼ��� ����Ʈ 1����
		sql = "UPDATE MEMBER SET point = point + 1 WHERE ID=:id";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("id", id );
		this.npJdbcTemplate.update(sql, paramSource);
		
	}
	*/
	
	/*
	// [2] transactionManager�� ����ؼ� Ʈ����� ó��
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		
		// 1. �������� ����
		String sql1 = "INSERT INTO NOTICES(seq, title, content, writer, regdate, hit, filesrc) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'jindol', SYSDATE, 0, :filesrc)";
		
		// 2. �ۼ��� ����Ʈ 1����
		String sql2 = "UPDATE MEMBER SET point = point + 1 WHERE ID=:id";
		
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = this.transactionManager.getTransaction(definition );
		
		try {
			SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
			this.npJdbcTemplate.update(sql1,  parameterSource);
			
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("id", id );
			this.npJdbcTemplate.update(sql2, paramSource);
			
			this.transactionManager.commit(status);
		} catch (Exception e) {
			this.transactionManager.rollback(status);
		}
		
	}
	*/
	
	// 3. TransactionTemplate
	/*
	@Override
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		
		// 1. �������� ����
		String sql1 = "INSERT INTO NOTICES(seq, title, content, writer, regdate, hit, filesrc) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'jindol', SYSDATE, 0, :filesrc)";
		
		// 2. �ۼ��� ����Ʈ 1����
		String sql2 = "UPDATE MEMBER SET point = point + 1 WHERE ID=:id";
		
		this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				
				SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
				npJdbcTemplate.update(sql1,  parameterSource);
				
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("id", id );
				npJdbcTemplate.update(sql2, paramSource);
				
			}
		});
	}
	*/
	
	// 4. ������ Ʈ����� ó��
	/*
	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {

		// 1. �������� ����
		String sql = "INSERT INTO NOTICES(seq, title, content, writer, regdate, hit, filesrc) "
				+ "VALUES( (SELECT NVL(MAX(TO_NUMBER(SEQ)),0)+1 FROM NOTICES), :title, :content, 'jindol', SYSDATE, 0, :filesrc)";
		
		SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(notice);
		
		this.npJdbcTemplate.update(sql,  parameterSource);
		
		// 2. �ۼ��� ����Ʈ 1����
		sql = "UPDATE MEMBER SET point = point + 1 WHERE ID=:id";
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
	    paramSource.addValue("id", id );
		this.npJdbcTemplate.update(sql, paramSource);
		
	}
	*/
	
	// 5. Ʈ������� ���Ĺ�� ���� - ����
	/*
	@Override
	// @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	public void insertAndPointUpOfMember(NoticeVO notice, String id) throws ClassNotFoundException, SQLException {
		
		insert(notice);
		
		// title UK
		notice.setTitle(notice.getTitle() + "-2");
		insert(notice);
		
	}
	*/
}
