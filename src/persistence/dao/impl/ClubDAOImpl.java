package persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.catalina.User;

import persistence.dao.ClubDAO;
import persistence.dao.impl.JDBCUtil;
import service.dto.ClubDTO;
import service.dto.UserDTO;

public class ClubDAOImpl implements ClubDAO {
private JDBCUtil jdbcUtil = null;
	
	public ClubDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 媛앹껜 �깮�꽦
	}
	/**
	 * Club �뀒�씠釉붿뿉 �깉濡쒖슫 �뻾 �깮�꽦 (PK 媛믪� Sequence瑜� �씠�슜�븯�뿬 �옄�룞 �깮�꽦)
	 * @return 
	 */
	public int createClub(ClubDTO club) {
		int result = 0;
		String sql = "INSERT INTO Club (CLUB_ID, CLUBNAME, STARTDATE, REGION, MAXOFMEMBERS, "
				+ "CHAIR_ID)VALUES (?, ?, ?, SYSDATE, ?, ?, ?)";
		Object[] param = new Object[] {club.getName(),
				club.getRegion(), club.getMaxNumMembers(), club.getChairId()};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			

			int n = club.getCategory().length;
			
			String sql1 = "begin for i in 1.." + n + "loop INSERT INTO CLUBS_CATEGORY VALUES (?, ?) end loop; end";
			Object[] parameters = new Object[] {club.getClubId(), club.getCategory()};
			
			jdbcUtil.setSqlAndParameters(sql1, parameters);
			
			
			String key[] = {"BOARD_ID"};	// PK 而щ읆紐�
			try {    
				jdbcUtil.executeUpdate(key);  // insert 臾� �떎�뻾
			   	ResultSet rs = jdbcUtil.getGeneratedKeys();
			   	if(rs.next()) {
			   		String generatedKey = rs.getString(1);   // �깮�꽦�맂 PK 媛�
			   		club.setClubId(generatedKey); 	// id�븘�뱶�뿉 ���옣  
			   	}
			   	return result;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource 諛섑솚
			}		
			return 0;
	}
	
	/**
	 * �겢�읇�뿉 媛��엯
	 */
	public int insertUser(String userId, String clubId){
		String sql = "UPDATE USERINFO "
				+ "SET CLUB_ID = ? " 
				+ "WHERE USER_ID = ?";
			
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId, userId});	
		try {				
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	
	/**
	 * �겢�읇 �궗�슜�옄 �궘�젣
	 */
	public int removeUser(String userId, String clubId){
		String sql = "DELETE FROM USERINFO "
				+ "WHERE CLUB_ID = ? AND USER_ID = ?";
			
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId, userId});	
		try {				
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	/**
	 * 湲곗〈�쓽 Club �젙蹂대�� �닔�젙
	 */
	public int update(ClubDTO club) {
		String sql = "UPDATE Club "
				+ "SET REGION=?, CHARI_ID=? "
				+ "WHERE CLUB_ID = ?";

		
		Object[] param = new Object[] { club.getRegion(), 
				club.getChairId(), club.getClubId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			
		
		int n = club.getCategory().length;

		String sql1 = "begin for i in 1.." + n + "loop UPDATE CLUB_CATEGORY SET HB_ID = ? WHERE club_id = ? end loop; end";
		Object[] parameters = new Object[] {club.getCategory(), club.getClubId()};
		
		jdbcUtil.setSqlAndParameters(sql1, parameters);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	
	/**
	 * �겢�읇�쓽 �쉶�옣�쓣 蹂�寃�  
	 */
	public int updateChair(ClubDTO club) {		
		String sql = "UPDATE CLUB "
				+ "SET CHAIR_ID= ? "
				+ "WHERE CLUB_ID=?";
		Object[] param = new Object[] {club.getChairId(), club.getClubId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	
	/**
	 * �겢�읇 ID�뿉 �빐�떦�븯�뒗 �겢�읇 �젙蹂대�� �궘�젣.
	 */
	public int delete(String clubId) {
		String sql = "DELETE FROM CLUB WHERE CLUB_ID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {				
			int result = jdbcUtil.executeUpdate();	// delete 臾� �떎�뻾
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 諛섑솚
		}		
		return 0;
	}
	
	/* 吏��뿭 留ㅼ묶*/ 
	public ClubDTO regionMatching(UserDTO user) {
		String sql = "SELECT c.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, c.REGION AS CLUB_REGION "
    			+ "FROM Club c LEFT OUTER JOIN User u ON c.region = u.region "
    			+ "WHERE u.USER_ID = ?";    			
	   jdbcUtil.setSqlAndParameters(sql, new Object[] {user.getUserId()});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
	   																		// 紐낆� UserDTO 異붽��븯硫� �맖.
		ClubDTO club = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {					
				club = new ClubDTO(		// Club 媛앹껜瑜� �깮�꽦�븯�뿬 而ㅻ�ㅻ땲�떚 �젙蹂대�� ���옣
					rs.getString("ID"),
					rs.getString("CLUBNAME"),			
					rs.getDate("STARTDATE"),
					rs.getString("CHAIR_ID"),
					rs.getString("CLUB_REGION"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return club;
	}
	
	/**
	 * 二쇱뼱吏�  �겢�읇ID�뿉 �빐�떦�븯�뒗 �겢�읇 �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 Club �룄硫붿씤 �겢�옒�뒪�뿉 
	 * ���옣�븯�뿬 諛섑솚.
	 */
	public ClubDTO findClub(String clubId) {
		String sql = "SELECT c.CLUB_ID , CLUBNAME,  STARTDATE, NUMOFMEMBERS ,CHAIR_ID, u.name As chairName, POPULARITY "
    			+ "FROM Club c LEFT OUTER JOIN User u ON c.CLUB_ID = u.CLUB_ID  "
    			+ "WHERE c.CLUB_ID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		ClubDTO club = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {					
				club = new ClubDTO(		// Club 媛앹껜瑜� �깮�꽦�븯�뿬 而ㅻ�ㅻ땲�떚 �젙蹂대�� ���옣
						clubId,
					rs.getString("CLUBNAME"),
					rs.getString("CHAIR_ID"),
					rs.getInt("NUMOFMEMBERS"),
					rs.getDate("STARTDATE"),
					rs.getString("chairName"),
					rs.getString("POPULARITY"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return club;
	}
	
	/**
	 * �쟾泥� �겢�읇 �젙蹂대�� 寃��깋�븯�뿬 List�뿉 ���옣 諛� 諛섑솚
	 */
	public List<ClubDTO> findClubList() {
		String sql = "SELECT C.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, u.name As chairName, "
		   + "COUNT(u.USER_ID) AS NUMOFMEMBERS, POPUALRITY "
   		   + "FROM Club c LEFT OUTER JOIN USERINFO u ON c.CHAIR_ID = u.USER_ID  "
   		   + "GROUP BY C.CLUB_ID, CLUBNAME, STARTDATE, CHAIR_ID, c.POPUALRITY, u.name, POPUALRITY "
   		   + "ORDER BY CLUBNAME";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�뿉 query臾� �꽕�젙
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾			
			List<ClubDTO> clubList = new ArrayList<ClubDTO>();	// Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
			while (rs.next()) {
				ClubDTO club = new ClubDTO(			// Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
						rs.getString("ID"),
						rs.getString("CLUBNAME"),
						rs.getString("CHAIR_ID"),
						rs.getInt("NUMOFMEMBERS"),
						rs.getDate("STARTDATE"),
						rs.getString("chairName"),
						rs.getString("POPUALRITY"));
				clubList.add(club);				// List�뿉 Community 媛앹껜 ���옣
			}		
			return clubList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return null;
	}
	
	/**
	 * 二쇱뼱吏�  �겢�읇ID�뿉 �빐�떦�븯�뒗 �겢�읇�씠 議댁옱�븯�뒗吏� 寃��궗 
	 */
	public boolean existingClub(String clubId) {
		String sql = "SELECT count(*) FROM Club WHERE CLUB_ID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return false;
	}

    public List<ClubDTO> searchClubList(String keyword){
        String sql = "SELECT C.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, u.name As chairName, "
                + "COUNT(u.USER_ID) AS NUMOFMEMBERS, POPUALRITY "
                + "FROM Club c LEFT OUTER JOIN USERINFO u ON c.CHAIR_ID = u.USER_ID  "
                + "WHERE C.CLUBNAME LIKE '%'||?||'%' "
                + "GROUP BY C.CLUB_ID, CLUBNAME, STARTDATE, CHAIR_ID, c.POPUALRITY, u.name, POPUALRITY "
                + "ORDER BY CLUBNAME";        
            jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword});   // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
                         
             try {
                 ResultSet rs = jdbcUtil.executeQuery();         // query �떎�뻾           
                 List<ClubDTO> clubList = new ArrayList<ClubDTO>();  // Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
                 while (rs.next()) {
                     ClubDTO club = new ClubDTO(         // Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
                             rs.getString("ID"),
                             rs.getString("CLUBNAME"),
                             rs.getString("CHAIR_ID"),
                             rs.getInt("NUMOFMEMBERS"),
                             rs.getDate("STARTDATE"),
                             rs.getString("chairName"),
                             rs.getString("POPUALRITY"));
                     clubList.add(club);             // List�뿉 Community 媛앹껜 ���옣
                 }       
                 return clubList;                    
                 
             } catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 jdbcUtil.close();       // resource 諛섑솚
             }
             return null;
    }
	
    public List<ClubDTO> findClubListbyMBTI(String mbti){
        String sql = "SELECT C.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, u.name As chairName, "
                + "COUNT(u.USER_ID) AS NUMOFMEMBERS, POPUALRITY "
                + "FROM Club c LEFT OUTER JOIN USERINFO u ON c.CHAIR_ID = u.USER_ID  "
                + "WHERE C.MBTI = ?  "
                + "GROUP BY C.CLUB_ID, CLUBNAME, STARTDATE, CHAIR_ID, c.POPUALRITY, u.name, POPUALRITY "
                + "ORDER BY CLUBNAME";        
            jdbcUtil.setSqlAndParameters(sql, new Object[] {mbti});   // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
                         
             try {
                 ResultSet rs = jdbcUtil.executeQuery();         // query �떎�뻾           
                 List<ClubDTO> clubList = new ArrayList<ClubDTO>();  // Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
                 while (rs.next()) {
                     ClubDTO club = new ClubDTO(         // Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
                             rs.getString("ID"),
                             rs.getString("CLUBNAME"),
                             rs.getString("CHAIR_ID"),
                             rs.getInt("NUMOFMEMBERS"),
                             rs.getDate("STARTDATE"),
                             rs.getString("chairName"),
                             rs.getString("POPUALRITY"));
                     clubList.add(club);             // List�뿉 Community 媛앹껜 ���옣
                 }       
                 return clubList;                    
                 
             } catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 jdbcUtil.close();       // resource 諛섑솚
             }
             return null;
    }

    
    public List<ClubDTO> findClubListbyRegion(String region){
        String sql = "SELECT C.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, u.name As chairName, "
                + "COUNT(u.USER_ID) AS NUMOFMEMBERS, POPUALRITY "
                + "FROM Club c LEFT OUTER JOIN USERINFO u ON c.CHAIR_ID = u.USER_ID  "
                + "WHERE C.REGION = ?  "
                + "GROUP BY C.CLUB_ID, CLUBNAME, STARTDATE, CHAIR_ID, c.POPUALRITY, u.name, POPUALRITY "
                + "ORDER BY CLUBNAME";        
            jdbcUtil.setSqlAndParameters(sql, new Object[] {region});   // JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
                         
             try {
                 ResultSet rs = jdbcUtil.executeQuery();         // query �떎�뻾           
                 List<ClubDTO> clubList = new ArrayList<ClubDTO>();  // Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
                 while (rs.next()) {
                     ClubDTO club = new ClubDTO(         // Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣
                             rs.getString("ID"),
                             rs.getString("CLUBNAME"),
                             rs.getString("CHAIR_ID"),
                             rs.getInt("NUMOFMEMBERS"),
                             rs.getDate("STARTDATE"),
                             rs.getString("chairName"),
                             rs.getString("POPUALRITY"));
                     clubList.add(club);             // List�뿉 Community 媛앹껜 ���옣
                 }       
                 return clubList;                    
                 
             } catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 jdbcUtil.close();       // resource 諛섑솚
             }
             return null;
    }
}
