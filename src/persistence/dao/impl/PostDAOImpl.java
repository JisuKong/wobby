package persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.impl.JDBCUtil;
import persistence.dao.PostDAO;
import service.dto.BoardDTO;
import service.dto.CommunityDTO;
import service.dto.PostDTO;

public class PostDAOImpl implements PostDAO {
private JDBCUtil jdbcUtil = null;
	
	public PostDAOImpl() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 媛앹껜 �깮�꽦
	}
	
	/*UPDATE BOARD 
	  SET NUMOFPOST = (SELECT COUNT(POST_ID)
                    FROM POST)
      WHERE BOARD_ID=3;
      
      NUMOFPOST瑜� 異붽��븯�뒗 肄붾뱶 
	 * */
	public PostDTO create(PostDTO post, int boardId) {
		String sql = "INSERT INTO Post (POST_ID, POSTDATE, USER_ID, TITLE, CONTENTS,BOARD_ID) VALUES (POST_SEQ.nextval, SYSDATE, ?, ?, ?, ?)";
		
		String sql2 = "UPDATE BOARD SET NUMOFPOST = (SELECT COUNT(POST_ID) FROM POST)" + 
				"      WHERE BOARD_ID=?";
		
		String sql3 = "UPDATE COMMUNITY SET NUMOFPOST = (SELECT COUNT(POST_ID) FROM POST)" + 
				"      WHERE BOARD_ID=?";
		
		Object[] param = new Object[] {post.getUserId(), post.getTitle(), post.getContents(),post.getBoardId()};				
			jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil �뿉 insert臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
							
			String key[] = {"POST_ID"};	// PK 而щ읆紐�
			try {    
				jdbcUtil.executeUpdate(key);  // insert 臾� �떎�뻾
			   	ResultSet rs = jdbcUtil.getGeneratedKeys();
			   	if(rs.next()) {
			   		int generatedKey = rs.getInt(1);   // �깮�꽦�맂 PK 媛�
			   		post.setPostId(generatedKey); 	// id�븘�뱶�뿉 ���옣  
			   	}
			   	return post;
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			} finally {		
				jdbcUtil.commit();
				jdbcUtil.close();	// resource 諛섑솚
			}		
			return null;
	}
	
	/**
	 * 湲곗〈�쓽 寃뚯떆湲��쓽 �젙蹂대�� �닔�젙
	 */
	public int update(PostDTO post) {
		String sql = "UPDATE Post "
				+ "SET TITLE=?, CONTENTS = ?, UPDATEDATE=SYSDATE"
				+ "WHERE USER_ID=?";
		int postId = post.getPostId();
	
//		if (clubId.equals("")) clubId = null;
		Object[] param = new Object[] {post.getTitle(), post.getContents(), post.getUserId()};				
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
	 * 二쇱뼱吏� ID�뿉 �빐�떦�븯�뒗 寃뚯떆湲� �젙蹂대�� �궘�젣.
	 */
	public int delete(int postId)  {
		String sql = "DELETE FROM POST WHERE POST_ID=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�뿉 delete臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

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
	
	/*
	 * 寃뚯떆湲��쓽 議고쉶�닔 
	 * */
	public int increasePostViewCnt(int postId) {
		String sql = "UPDATE Post "
				+ "SET NUMOFVIEW=NVL(NUMOFVIEW,0) + 1 "
				+ "WHERE POST_ID=?";
				
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			
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
	
	/*
	 * 寃뚯떆湲��쓽 �뒪�겕�옪�닔 
	 * */
	public int increasePostScrapCnt(int postId) {
		String sql = "UPDATE Post "
				+ "SET NUMOFSCRAPS=NVL(NUMOFSCRAPS,0) + 1 "
				+ "WHERE POST_ID=?";
				
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�뿉 update臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
			
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
	 * 二쇱뼱吏� 寃뚯떆�뙋id�뿉�꽌 �씤湲곗엳�뒗 湲��쓣 List�뿉 �떞�븘 諛섑솚
	 * �씤湲곌� 湲곗� -> 議고쉶�닔 : 10�쉶 �씠�긽, �뒪�겕�옪�닔 : 10�쉶�씠�긽
	 */
	 public List<PostDTO> findPopularPostList(int boardId) {
		 String sql = "SELECT POST_ID, TITLE, NICKNAME, POSTDATE ,NUMOFVIEW, NUMOFSCRAPS "
	    			+ "FROM POST " 
					+ "WHERE BOARD_ID=? AND NUMOFVIEW >= 10 AND NUMOFSCRAPS >= 10 "
			   		+ "ORDER BY NUMOFVIEW ";        
					jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});		// JDBCUtil�뿉 query臾� �꽕�젙
								
					try {
						ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾			
						List<PostDTO> postList = new ArrayList<PostDTO>();	// Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
						PostDTO post = null;
						while (rs.next()) {
							post = new PostDTO();			// Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣

							post.setPostId(rs.getInt("POST_ID"));
							post.setTitle(rs.getString("TITLE"));
							post.setNickname(rs.getString("NICKNAME"));
							post.setPostDate(rs.getDate("POSTDATE"));
							post.setNumOfView(rs.getInt("NUMOFVIEW"));
							post.setNumOfScraps(rs.getInt("NUMOFSCRAPS"));
							
							postList.add(post);				// List�뿉 Community 媛앹껜 ���옣
						}	
						jdbcUtil.commit();
						return postList;					
						
					} catch (Exception ex ) {
						if (ex instanceof AppException) {
							jdbcUtil.rollback();
							System.out.println("�듃�옖�옲�뀡�씠 rollback �릺�뿀�뒿�땲�떎.");	
						}
						ex.printStackTrace();
					}
					finally {	
						if (jdbcUtil != null) {
							jdbcUtil.close();
						}
					}
					return null;
	 }
	/**
	 * 二쇱뼱吏�  ID�뿉 �빐�떦�븯�뒗 寃뚯떆湲� �젙蹂대�� �뜲�씠�꽣踰좎씠�뒪�뿉�꽌 李얠븘 Post �룄硫붿씤 �겢�옒�뒪�뿉 
	 * ���옣�븯�뿬 諛섑솚.
	 */
	public PostDTO findPost(int postId)  {
		String sql = "SELECT p.POST_ID AS ID, p.TITLE AS POST_TITLE, p.CONTENTS AS POST_CONTENTS, p.POSTDATE AS POST_DATE, NICKNAME "
    			+ "FROM POST p LEFT OUTER JOIN USERINFO u ON p.USER_ID = u.USER_ID   "
    			+ "WHERE POST_ID=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙
		PostDTO post = null;
		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query �떎�뻾
			if (rs.next()) {						// �븰�깮 �젙蹂� 諛쒓껄
				post = new PostDTO(		// Community 媛앹껜瑜� �깮�꽦�븯�뿬 而ㅻ�ㅻ땲�떚 �젙蹂대�� ���옣
						postId,
					rs.getString("POST_TITLE"),
					rs.getString("POST_CONTENTS"),
					rs.getDate("POST_DATE"),
					rs.getString("NICKNAME"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 諛섑솚
		}
		return post;
	}
	
	/**
	 * boardId�쓽 寃뚯떆湲� �젙蹂대�� 寃��깋�븯�뿬 List�뿉 ���옣 諛� 諛섑솚
	 */
	public List<PostDTO> findPostList(int boardId)  {
		String sql = "SELECT POST_ID, TITLE, NICKNAME, POSTDATE ,NUMOFVIEW, NUMOFSCRAPS "
    			+ "FROM POST " 
				+ "WHERE BOARD_ID=? "
		   		+ "ORDER BY POST_ID ";        
				jdbcUtil.setSqlAndParameters(sql, new Object[] {boardId});		// JDBCUtil�뿉 query臾� �꽕�젙
							
				try {
					ResultSet rs = jdbcUtil.executeQuery();			// query �떎�뻾			
					List<PostDTO> postList = new ArrayList<PostDTO>();	// Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
					PostDTO post = null;
					while (rs.next()) {
						post = new PostDTO();			// Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣

						post.setPostId(rs.getInt("POST_ID"));
						post.setTitle(rs.getString("TITLE"));
						post.setNickname(rs.getString("NICKNAME"));
						post.setPostDate(rs.getDate("POSTDATE"));
						post.setNumOfView(rs.getInt("NUMOFVIEW"));
						post.setNumOfScraps(rs.getInt("NUMOFSCRAPS"));
						
						postList.add(post);				// List�뿉 Community 媛앹껜 ���옣
					}	
					jdbcUtil.commit();
					return postList;					
					
				} catch (Exception ex ) {
					if (ex instanceof AppException) {
						jdbcUtil.rollback();
						System.out.println("�듃�옖�옲�뀡�씠 rollback �릺�뿀�뒿�땲�떎.");	
					}
					ex.printStackTrace();
				}
				finally {	
					if (jdbcUtil != null) {
						jdbcUtil.close();
					}
				}
				return null;
	}
	
    public List<PostDTO> searchPostList(String keyword)  {
        String sql = "SELECT POST_ID, TITLE, NICKNAME, POSTDATE, NUMOFVIEW, NUMOFSCRAPS "
                + "FROM POST "
                + "WHERE CONTENTS LIKE '%'||?||'%' "
                + "ORDER BY POST_ID ";        
                jdbcUtil.setSqlAndParameters(sql, new Object[] {keyword});      // JDBCUtil�뿉 query臾� �꽕�젙
                           
                try {
                    ResultSet rs = jdbcUtil.executeQuery();         // query �떎�뻾           
                    List<PostDTO> postList = new ArrayList<PostDTO>();  // Community�뱾�쓽 由ъ뒪�듃 �깮�꽦
                    PostDTO post = null;
                    while (rs.next()) {
                        post = new PostDTO();           // Community 媛앹껜瑜� �깮�꽦�븯�뿬 �쁽�옱 �뻾�쓽 �젙蹂대�� ���옣

                        post.setPostId(rs.getInt("POST_ID"));
                        post.setTitle(rs.getString("TITLE"));
                        post.setNickname(rs.getString("NICKNAME"));
                        post.setPostDate(rs.getDate("POSTDATE"));
                        post.setNumOfView(rs.getInt("NUMOFVIEW"));
                        post.setNumOfScraps(rs.getInt("NUMOFSCRAPS"));
                        
                        postList.add(post);             // List�뿉 Community 媛앹껜 ���옣
                    }   
                    jdbcUtil.commit();
                    return postList;                    
                    
                } catch (Exception ex ) {
                    if (ex instanceof AppException) {
                        jdbcUtil.rollback();
                        System.out.println("�듃�옖�옲�뀡�씠 rollback �릺�뿀�뒿�땲�떎.");  
                    }
                    ex.printStackTrace();
                }
                finally {   
                    if (jdbcUtil != null) {
                        jdbcUtil.close();
                    }
                }
                return null;
    }
    
	/**
	 * 二쇱뼱吏�  ID�뿉 �빐�떦�븯�뒗 寃뚯떆湲��씠 議댁옱�븯�뒗吏� 寃��궗 
	 */
	public boolean existingPost(int postId)  {
		String sql = "SELECT count(*) FROM POST WEHRE POST_ID=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {postId});	// JDBCUtil�뿉 query臾멸낵 留ㅺ컻 蹂��닔 �꽕�젙

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
}
