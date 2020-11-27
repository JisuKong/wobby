package persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.dao.CommunityDAO;
import persistence.dao.impl.JDBCUtil;
import service.dto.CommunityDTO;

public class CommunityDAOImpl implements CommunityDAO {
private JDBCUtil jdbcUtil = null;
	
	public CommunityDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	/**
	 * 커뮤니티 테이블에 새로운 행 생성 (PK 값은 Sequence를 이용하여 자동 생성)
	 */
	
	/*UPDATE COMMUNITY 
	  SET NUMOFMEMBERS = (SELECT COUNT(USER_ID)
                    FROM USERINFO)
      WHERE COMMUNITY_ID=1;
      
      NUMOFMEMBERS를 추가하는 코드 추가하여 수정하기
	 * 
	 * */
	public CommunityDTO create(CommunityDTO comm) {
		String sql1 = "INSERT INTO Community (COMMUNITY_ID, NAME, STARTDATE,KEEPER_ID)VALUES (communityId_seq.nextval, ?, SYSDATE, ?)";		

				
		Object[] param = new Object[] {comm.getName(), comm.getKeeperId()};				
		jdbcUtil.setSqlAndParameters(sql1, param);	// JDBCUtil 에 insert문과 매개 변수 설정
						
		String key[] = {"COMMUNITY_ID"};	// PK 컬럼명
		try {    
			jdbcUtil.executeUpdate(key);  // insert 문 실행
		   	ResultSet rs = jdbcUtil.getGeneratedKeys();
		   	if(rs.next()) {
		   		int generatedKey = rs.getInt(1);   // 생성된 PK 값
		   		comm.setCommunityId(generatedKey); 	// id필드에 저장  
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
	 * 기존의 커뮤니티 정보를 수정
	 */
	public int update(CommunityDTO comm) {
		String sql = "UPDATE Community "
				+ "SET COMMUNITYNAME=?, KEEPERID=? "
				+ "WHERE COMMUNITY_ID=?";
		String keeperId = comm.getKeeperId();
		if (keeperId.equals("")) keeperId = null;
		Object[] param = new Object[] {comm.getName(), comm.getKeeperId(),
				keeperId, comm.getCommunityId()};				
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
	 * 관리자를 추가
	 */
	public int insertAdmin(String userId){
		String sql = "UPDATE COMMUNITY "
				+ "SET ADMIN_ID = ? " ;
			
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	
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
	 * 관리자를 삭제
	 */
	public int deleteAdmin(String userId) {
		String sql = "DELETE FROM ADMIN_USER WHERE ADMIN_ID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	// JDBCUtil에 delete문과 매개 변수 설정

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
	 * 커뮤니티의 회장을 변경  
	 */
	public int updateChair(CommunityDTO comm) {
		String sql = "UPDATE Community "
				+ "SET KEEPERID= ? "
				+ "WHERE COMMUNITY_ID=?";
		Object[] param = new Object[] {comm.getKeeperId(), comm.getCommunityId()};				
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
	 * 주어진 ID에 해당하는 커뮤니티 정보를 삭제.
	 */
	public int delete(int communityId) {
		String sql = "DELETE FROM Community WHERE COMMUNITY_ID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil에 delete문과 매개 변수 설정

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
	 * 주어진  ID에 해당하는 커뮤니티 정보를 데이터베이스에서 찾아 Community 도메인 클래스에 
	 * 저장하여 반환.
	 */
	public CommunityDTO findCommunity(int communityId){
		String sql = "SELECT COMMUNITYNAME, STARTDATE, KEEPERID "
    			+ "FROM Community  "
    			+ "WHERE COMMUNITY_ID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil에 query문과 매개 변수 설정
		CommunityDTO comm = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {						// 학생 정보 발견
				comm = new CommunityDTO(		// Community 객체를 생성하여 커뮤니티 정보를 저장
					communityId,
					rs.getString("COMMUNITYNAME"),
					rs.getDate("STARTDATE"),
					rs.getString("KEEPERID") );
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return comm;
	}
	
	/**
	 * 전체 커뮤니티 정보를 검색하여 List에 저장 및 반환
//	 */
//	public List<Community> findCommunityList() throws SQLException {
//		 String sql = "SELECT communityId, name, descr, COUNT(u.userId) AS numOfMembers "
//      		   + "FROM Community c LEFT OUTER JOIN UserInfo u ON c.communityId = u.communityId "
//      		   + "GROUP BY communityId, name, descr "
//      		   + "ORDER BY name";        
//		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil에 query문 설정
//					
//		try {
//			ResultSet rs = jdbcUtil.executeQuery();			// query 실행			
//			List<Community> commList = new ArrayList<Community>();	// Community들의 리스트 생성
//			while (rs.next()) {
//				Community comm = new Community(			// Community 객체를 생성하여 현재 행의 정보를 저장
//						rs.getInt("communityId"),
//						rs.getString("name"),
//						rs.getString("descr"),
//						rs.getInt("numOfMem"));
//				commList.add(comm);				// List에 Community 객체 저장
//			}		
//			return commList;					
//			
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			jdbcUtil.close();		// resource 반환
//		}
//		return null;
//	}
	
	/**
	 * 주어진  ID에 해당하는 커뮤니티가 존재하는지 검사 
	 */
	public boolean existingCommunity(int communityId)  {
		String sql = "SELECT count(*) FROM Community WEHRE COMMUNITY_ID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {communityId});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
}
