package persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import persistence.dao.ClubDAO;
import service.dto.ClubDTO;
import service.dto.UserDTO;

public class ClubDAOImpl implements ClubDAO {
private JDBCUtil jdbcUtil = null;
	
	public ClubDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 揶쏆빘猿� 占쎄문占쎄쉐
	}
	
	public int createClub(ClubDTO club) {
		int result = 0;
		String sql = "INSERT INTO Club (CLUB_ID, CLUBNAME, STARTDATE, REGION, MAXOFMEMBERS, "
				+ "CHAIR_ID)VALUES (?, ?, ?, SYSDATE, ?, ?, ?)";
		Object[] param = new Object[] {club.getName(),
				club.getRegion(), club.getMaxNumMembers(), club.getChairId()};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil 占쎈퓠 insert�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
			

			int n = club.getCategory().length;
			
			String sql1 = "begin for i in 1.." + n + "loop INSERT INTO CLUBS_CATEGORY VALUES (?, ?) end loop; end";
			Object[] parameters = new Object[] {club.getClubId(), club.getCategory()};
			
			jdbcUtil.setSqlAndParameters(sql1, parameters);
			
			
			String key[] = {"BOARD_ID"};	// PK �뚎됱쓥筌륅옙
			try {    
				jdbcUtil.executeUpdate(key);  // insert �눧占� 占쎈뼄占쎈뻬
			   	ResultSet rs = jdbcUtil.getGeneratedKeys();
			   	if(rs.next()) {
			   		String generatedKey = rs.getString(1);   // 占쎄문占쎄쉐占쎈쭆 PK 揶쏉옙
			   		club.setClubId(generatedKey); 	// id占쎈툡占쎈굡占쎈퓠 占쏙옙占쎌삢  
			   	}
			   	return result;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource 獄쏆꼹�넎
			}		
			return 0;
	}
	
	/**
	 * 占쎄깻占쎌쓦占쎈퓠 揶쏉옙占쎌뿯
	 */
	public int insertUser(String userId, String clubId){
		String sql = "UPDATE USERINFO "
				+ "SET CLUB_ID = ? " 
				+ "WHERE USER_ID = ?";
			
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId, userId});	
		try {				
			int result = jdbcUtil.executeUpdate();	// update �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;
	}
	
	/**
	 * 占쎄깻占쎌쓦 占쎄텢占쎌뒠占쎌쁽 占쎄텣占쎌젫
	 */
	public int removeUser(String userId, String clubId){
		String sql = "DELETE FROM USERINFO "
				+ "WHERE CLUB_ID = ? AND USER_ID = ?";
			
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId, userId});	
		try {				
			int result = jdbcUtil.executeUpdate();	// update �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;
	}
	/**
	 * 疫꿸퀣�덌옙�벥 Club 占쎌젟癰귣�占쏙옙 占쎈땾占쎌젟
	 */
	public int update(ClubDTO club) {
        String sql = "UPDATE CLUB "
                + "SET CLUBNAME = ?, MBTI = ? "
                + "WHERE CLUB_ID = ?";
        Object[] param = new Object[] { club.getName(), club.getMbti(), club.getClubId() };
        jdbcUtil.setSqlAndParameters(sql, param); 
        
        try {               
            int result = jdbcUtil.executeUpdate();  // update �눧占� 占쎈뼄占쎈뻬
            return result;
        } catch (Exception ex) {
            jdbcUtil.rollback();
            ex.printStackTrace();
        }
        finally {
            jdbcUtil.commit();
            jdbcUtil.close();   // resource 獄쏆꼹�넎
        }       
        return 0;
        
        /*
		String sql = "UPDATE Club "
				+ "SET REGION=?, CHAIR_ID=? "
				+ "WHERE CLUB_ID = ?";

		
		Object[] param = new Object[] { club.getRegion(), 
				club.getChairId(), club.getClubId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쎈퓠 update�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
			
		
		int n = club.getCategory().length;

		String sql1 = "begin for i in 1.." + n + "loop UPDATE CLUB_CATEGORY SET HB_ID = ? WHERE club_id = ? end loop; end";
		Object[] parameters = new Object[] {club.getCategory(), club.getClubId()};
		
		jdbcUtil.setSqlAndParameters(sql1, parameters);
		
		try {				
			int result = jdbcUtil.executeUpdate();	// update �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;*/
	}
	
	/**
	 * 占쎄깻占쎌쓦占쎌벥 占쎌돳占쎌삢占쎌뱽 癰귨옙野껓옙  
	 */
	public int updateChair(ClubDTO club) {		
		String sql = "UPDATE CLUB "
				+ "SET CHAIR_ID= ? "
				+ "WHERE CLUB_ID=?";
		Object[] param = new Object[] {club.getChairId(), club.getClubId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil占쎈퓠 update�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
			
		try {				
			int result = jdbcUtil.executeUpdate();	// update �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;
	}
	
	/**
	 * 占쎄깻占쎌쓦 ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄깻占쎌쓦 占쎌젟癰귣�占쏙옙 占쎄텣占쎌젫.
	 */
	public int delete(String clubId) {
		String sql = "DELETE FROM CLUB WHERE CLUB_ID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId});	// JDBCUtil占쎈퓠 delete�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �눧占� 占쎈뼄占쎈뻬
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 獄쏆꼹�넎
		}		
		return 0;
	}
	
	/* 筌욑옙占쎈열 筌띲끉臾�*/ 
	public ClubDTO regionMatching(UserDTO user) {
		String sql = "SELECT c.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, c.REGION AS CLUB_REGION "
    			+ "FROM Club c LEFT OUTER JOIN User u ON c.region = u.region "
    			+ "WHERE u.USER_ID = ?";    			
	   jdbcUtil.setSqlAndParameters(sql, new Object[] {user.getUserId()});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
	   																		// 筌뤿굞占� UserDTO �빊遺쏙옙占쎈릭筌롳옙 占쎈쭡.
		ClubDTO club = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {					
				club = new ClubDTO(		// Club 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 �뚣끇占썬끇�빍占쎈뼒 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
					rs.getString("ID"),
					rs.getString("CLUBNAME"),			
					rs.getDate("STARTDATE"),
					rs.getString("CHAIR_ID"),
					rs.getString("CLUB_REGION"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return club;
	}
	
	/**
	 * 雅뚯눘堉깍쭪占�  占쎄깻占쎌쓦ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄깻占쎌쓦 占쎌젟癰귣�占쏙옙 占쎈쑓占쎌뵠占쎄숲甕곗쥙�뵠占쎈뮞占쎈퓠占쎄퐣 筌≪뼚釉� Club 占쎈즲筌롫뗄�뵥 占쎄깻占쎌삋占쎈뮞占쎈퓠 
	 * 占쏙옙占쎌삢占쎈릭占쎈연 獄쏆꼹�넎.
	 */
	public ClubDTO findClub(String clubId) {
		String sql = "SELECT c.CLUB_ID AS ID, CLUBNAME,  STARTDATE, CHAIR_ID, u.name As chairName, POPUALRITY, c.MBTI AS MBTI "
    			+ "FROM Club c LEFT OUTER JOIN USERINFO u ON c.CHAIR_ID = u.USER_ID  "
    			+ "WHERE c.CLUB_ID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
		ClubDTO club = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {
                System.out.println(rs.getString("ID"));
                club = new ClubDTO();
                club.setClubId(rs.getString("ID"));
                club.setName(rs.getString("CLUBNAME"));
                club.setChairId(rs.getString("CHAIR_ID"));
                club.setChairName(rs.getString("chairName"));
                club.setStartDate(rs.getDate("STARTDATE"));
                club.setPopularity(rs.getString("POPUALRITY"));
                club.setMbti(rs.getString("MBTI"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return club;
	}
	
	/**
	 * 占쎌읈筌ｏ옙 占쎄깻占쎌쓦 占쎌젟癰귣�占쏙옙 野껓옙占쎄퉳占쎈릭占쎈연 List占쎈퓠 占쏙옙占쎌삢 獄쏉옙 獄쏆꼹�넎
	 */
	public List<ClubDTO> findClubList() {
		String sql = "SELECT C.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, u.name As chairName, "
		   + "COUNT(u.USER_ID) AS NUMOFMEMBERS, POPUALRITY "
   		   + "FROM Club c LEFT OUTER JOIN USERINFO u ON c.CHAIR_ID = u.USER_ID  "
   		   + "GROUP BY C.CLUB_ID, CLUBNAME, STARTDATE, CHAIR_ID, c.POPUALRITY, u.name, POPUALRITY "
   		   + "ORDER BY CLUBNAME";        
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil占쎈퓠 query�눧占� 占쎄퐬占쎌젟
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query 占쎈뼄占쎈뻬			
			List<ClubDTO> clubList = new ArrayList<ClubDTO>();	// Community占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
			while (rs.next()) {
				ClubDTO club = new ClubDTO(			// Community 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
						rs.getString("ID"),
						rs.getString("CLUBNAME"),
						rs.getString("CHAIR_ID"),
						rs.getInt("NUMOFMEMBERS"),
						rs.getDate("STARTDATE"),
						rs.getString("chairName"),
						rs.getString("POPUALRITY"));
				clubList.add(club);				// List占쎈퓠 Community 揶쏆빘猿� 占쏙옙占쎌삢
			}		
			return clubList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
		}
		return null;
	}
	
	/**
	 * 雅뚯눘堉깍쭪占�  占쎄깻占쎌쓦ID占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 占쎄깻占쎌쓦占쎌뵠 鈺곕똻�삺占쎈릭占쎈뮉筌욑옙 野껓옙占쎄텢 
	 */
	public boolean existingClub(String clubId) {
		String sql = "SELECT count(*) FROM Club WHERE CLUB_ID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {clubId});	// JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 占쎈뼄占쎈뻬
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 獄쏆꼹�넎
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
            jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword});   // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
                         
             try {
                 ResultSet rs = jdbcUtil.executeQuery();         // query 占쎈뼄占쎈뻬           
                 List<ClubDTO> clubList = new ArrayList<ClubDTO>();  // Community占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
                 while (rs.next()) {
                     ClubDTO club = new ClubDTO(         // Community 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
                             rs.getString("ID"),
                             rs.getString("CLUBNAME"),
                             rs.getString("CHAIR_ID"),
                             rs.getInt("NUMOFMEMBERS"),
                             rs.getDate("STARTDATE"),
                             rs.getString("chairName"),
                             rs.getString("POPUALRITY"));
                     clubList.add(club);             // List占쎈퓠 Community 揶쏆빘猿� 占쏙옙占쎌삢
                 }       
                 return clubList;                    
                 
             } catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 jdbcUtil.close();       // resource 獄쏆꼹�넎
             }
             return null;
    }
	
    public List<ClubDTO> findClubListbyMBTI(String mbti){
        System.out.println(mbti);
        String sql = "SELECT C.CLUB_ID AS ID, CLUBNAME, STARTDATE, CHAIR_ID, u.name As chairName, "
                //+ "COUNT(u.USER_ID) AS NUMOFMEMBERS, "
                + "POPUALRITY "
                + "FROM CLUB c LEFT OUTER JOIN USERINFO u ON c.CHAIR_ID = u.USER_ID  "
                + "WHERE C.MBTI = ?  "
                + "GROUP BY C.CLUB_ID, CLUBNAME, STARTDATE, CHAIR_ID, u.name, POPUALRITY "
                + "ORDER BY CLUBNAME";  
            jdbcUtil.setSqlAndParameters(sql, new Object[] {mbti});   // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟

             try {
                 ResultSet rs = jdbcUtil.executeQuery();         // query 占쎈뼄占쎈뻬           
                 List<ClubDTO> clubList = new ArrayList<ClubDTO>();  // Community占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
                 while (rs.next()) {
                     System.out.println(rs.getString("ID"));
                     ClubDTO club = new ClubDTO();
                     club.setClubId(rs.getString("ID"));
                     club.setName(rs.getString("CLUBNAME"));
                     club.setChairId(rs.getString("CHAIR_ID"));
                     club.setChairName(rs.getString("chairName"));
                     //club.setNumOfMembers(rs.getInt("NUMOFMEMBERS"));
                     club.setStartDate(rs.getDate("STARTDATE"));
                     club.setPopularity(rs.getString("POPUALRITY"));
                     clubList.add(club);             // List占쎈퓠 Community 揶쏆빘猿� 占쏙옙占쎌삢
                 }       
                 return clubList;
             } catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 jdbcUtil.close();       // resource 獄쏆꼹�넎
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
            jdbcUtil.setSqlAndParameters(sql, new Object[] {region});   // JDBCUtil占쎈퓠 query�눧硫몃궢 筌띲끆而� 癰귨옙占쎈땾 占쎄퐬占쎌젟
                         
             try {
                 ResultSet rs = jdbcUtil.executeQuery();         // query 占쎈뼄占쎈뻬           
                 List<ClubDTO> clubList = new ArrayList<ClubDTO>();  // Community占쎈굶占쎌벥 �뵳�딅뮞占쎈뱜 占쎄문占쎄쉐
                 while (rs.next()) {
                     ClubDTO club = new ClubDTO(         // Community 揶쏆빘猿쒐몴占� 占쎄문占쎄쉐占쎈릭占쎈연 占쎌겱占쎌삺 占쎈뻬占쎌벥 占쎌젟癰귣�占쏙옙 占쏙옙占쎌삢
                             rs.getString("ID"),
                             rs.getString("CLUBNAME"),
                             rs.getString("CHAIR_ID"),
                             rs.getInt("NUMOFMEMBERS"),
                             rs.getDate("STARTDATE"),
                             rs.getString("chairName"),
                             rs.getString("POPUALRITY"));
                     clubList.add(club);             // List占쎈퓠 Community 揶쏆빘猿� 占쏙옙占쎌삢
                 }       
                 return clubList;                    
                 
             } catch (Exception ex) {
                 ex.printStackTrace();
             } finally {
                 jdbcUtil.close();       // resource 獄쏆꼹�넎
             }
             return null;
    }
}
