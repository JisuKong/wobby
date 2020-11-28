package persistence.dao.impl;

import java.sql.ResultSet;

import persistence.dao.CommentDAO;
import service.dto.BoardDTO;
import service.dto.CommentDTO;
import service.dto.PostDTO;

public class CommentDAOImpl implements CommentDAO{
private JDBCUtil jdbcUtil = null;
	
	public CommentDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/**
	 * 새 댓글 작성 (PK 값은 Sequence를 이용하여 자동 생성)
	 */
	public CommentDTO create(CommentDTO comm){
		String sql = "INSERT INTO COMMENTS (COMMENTS_ID, POST_ID, USER_ID, CONTENTS,POSTDATE, UPDATEDATE) VALUES (COMM_SEQ.nextval,?,?,?,SYSDATE, null) ";
		Object[] param = new Object[] {  comm.getPostId(),comm.getUserId(), comm.getContents() };				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 에 insert문과 매개 변수 설정
							
			String key[] = {"COMMNETS_ID"};	// PK 컬럼명
			try {    
				jdbcUtil.executeUpdate(key);  // insert 문 실행
			   	ResultSet rs = jdbcUtil.getGeneratedKeys();
			   	if(rs.next()) {
			   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
			   		comm.setCommentId(generatedKey); 	// id필드에 저장  
			   	}
			   	return comm;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource 반환
			}		
			return null;
	}
	
	/**
	 * 기존의 댓글 수정
	 */
	public int update(CommentDTO comm) {
		String sql = "UPDATE COMMENTS "
				+ "SET CONTENTS=?,  UPDATEDATE=SYSDATE "
				+ "WHERE COMMENTS_ID=?" ;
		int commId = comm.getCommentId();
	
//		if (clubId.equals("")) clubId = null;
		Object[] param = new Object[] {comm.getContents(), commId};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	/**
	 * 주어진 ID에 해당하는 댓글을  삭제.
	 */
	public int delete(int commId)  {
		String sql = "DELETE FROM COMMENTS WHERE COMMENTS_ID=? ";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil에 delete문과 매개 변수 설정

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	}
	
	/**
	 * 주어진  ID에 해당하는 댓글 정보를 데이터베이스에서 찾아 COMMENT 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public CommentDTO findComment(int commId)  {
		String sql = "SELECT COMMENTS_ID, c.CONTENTS,  c.POST_ID, c.POSTDATE, c.UPDATEDATE, u.USER_ID, NICKNAME "
    			+ "FROM COMMENTS c LEFT OUTER JOIN USERINFO u ON c.USER_ID = u.USER_ID   "
    			+ "WHERE COMMENTS_ID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {commId});	// JDBCUtil에 query문과 매개 변수 설정
		CommentDTO comm = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				comm = new CommentDTO(		// Community 객체를 생성하여 커뮤니티 정보를 저장
						commId,
					rs.getInt("postid"),	
					rs.getString("contents"),
					rs.getDate("postdate"),
					rs.getDate("updatedate"),
					rs.getString("user_Id"),
					rs.getString("nickname"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return comm;
	}
}
