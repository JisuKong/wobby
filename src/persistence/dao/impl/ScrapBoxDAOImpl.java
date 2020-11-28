package persistence.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.ScrapBoxDAO;
import service.dto.ScrapBoxDTO;

//占쏙옙占쏙옙占쏙옙 占쌩곤옙
public class ScrapBoxDAOImpl implements ScrapBoxDAO {

	private JDBCUtil jdbcUtil = null;

	public ScrapBoxDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	/**
	 * 占쏙옙체 Scrap 占쏙옙占쏙옙트 占쏙옙환
	 */
	public List<ScrapBoxDTO> getScrapList(String userId) {
		String query = "SELECT SCRAP_NO, POST_NO " + "FROM SCRAPBOX " + "WHERE USER_ID = ? ";
		
		Object[] param = new Object[] { userId };
		jdbcUtil.setSqlAndParameters(query, param);
		
		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙 占쏙옙占쏙옙
			List<ScrapBoxDTO> list = new ArrayList<ScrapBoxDTO>(); // MsgDTO 占쏙옙체占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占� list 占쏙옙체
			while (rs.next()) {
				ScrapBoxDTO dto = new ScrapBoxDTO(); // 占싹놂옙占쏙옙 MsgDTO 占쏙옙체 占쏙옙占쏙옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
				dto.setScrapNo(rs.getString("SCRAP_NO"));
				dto.setPostNo(rs.getString("POST_NO"));
				dto.setUserId(rs.getString("USER_ID"));
				list.add(dto);
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // ResultSet, PreparedStatement, Connection 占쏙옙환
		}
		return null;
	}

	/**
	 * Post占쏙옙 ScrapBox占쏙옙 占쏙옙占쏙옙
	 */
	public int createScrap(ScrapBoxDTO scrap) {
		String sql = "INSERT INTO SCRAPBOX VALUES (SCRAP_SEQ.NEXTVAL, ?, ?)";

		String sql2 = "UPDATE POST SET NUMOFSCRAPS = (SELECT COUNT(SCRAP_NO) FROM SCRAP)" + "      WHERE POST_ID=?";
		Object[] param = new Object[] { scrap.getPostNo(), scrap.getUserId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 占쏙옙 insert占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			int result = jdbcUtil.executeUpdate(); // insert 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return 0;
	}

	/**
	 * Scrap占쏙옙 Post占쏙옙占쏙옙 占쏙옙占쏙옙
	 */
	public int deleteScrap(String ScrapNo) {
		String sql = "DELETE FROM SCRAPBOX WHERE SCRAP_NO = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { ScrapNo }); // JDBCUtil占쏙옙 delete占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			int result = jdbcUtil.executeUpdate(); // delete 占쏙옙 占쏙옙占쏙옙
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return 0;
	}

}
