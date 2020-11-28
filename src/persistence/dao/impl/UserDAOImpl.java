package persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.UserDAO;
import service.dto.UserDTO;

public class UserDAOImpl implements UserDAO {
	private JDBCUtil jdbcUtil = null;

	public UserDAOImpl() {
		jdbcUtil = new JDBCUtil(); // JDBCUtil 占쏙옙체 占쏙옙占쏙옙
	}

	/**
	 * User 占쏙옙占싱븝옙 占쏙옙占싸울옙 占쏙옙 占쏙옙占쏙옙
	 */
	public int create(UserDTO user) {
		String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getUserId(), user.getPassword(), user.getName(), user.getEmail(),
				user.getPhone(), user.getRegistDate(), user.getAge(), user.getRegion(), user.getJob(), user.getMbti(),
				user.getNickname() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 占쏙옙 insert占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		String sql1 = "begin for i in 1..2 loop INSERT INTO USERS_HOBBY VALUES (?, ?) end loop; end";
		Object[] parameters = new Object[] { user.getHobby(), user.getUserId() };

		jdbcUtil.setSqlAndParameters(sql1, parameters);
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
	 * 占쏙옙占쏙옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙
	 */
	public int update(UserDTO user) {
		String sql = "UPDATE USERINFO "
				+ "SET pw = ?, name = ?, email = ?, phone = ?, age = ?, region = ?, job = ?, mbti = ?, nickname = ? "
				+ "WHERE user_id = ?";
		Object[] param = new Object[] { user.getPassword(), user.getName(), user.getEmail(), user.getPhone(),
				user.getAge(), user.getRegion(), user.getJob(), user.getMbti(), user.getNickname(), user.getUserId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil占쏙옙 update占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		String sql1 = "begin for i in 1..2 loop UPDATE USERS_HOBBY SET HB_ID = ? WHERE user_id = ? end loop; end";
		Object[] parameters = new Object[] { user.getHobby(), user.getUserId() };

		jdbcUtil.setSqlAndParameters(sql1, parameters);

		try {
			int result = jdbcUtil.executeUpdate(); // update 占쏙옙 占쏙옙占쏙옙
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
	 * 占쏙옙占쏙옙占폠D占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
	 */
	public int remove(String userId) {
		
		try {
			String sql = "DELETE FROM USERINFO WHERE user_id = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil占쏙옙 delete占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

			jdbcUtil.executeUpdate(); // delete 占쏙옙 占쏙옙占쏙옙

			String sql1 = "DELETE FROM USERS_HOBBY WHERE user_id = ?";
			jdbcUtil.setSqlAndParameters(sql1, new Object[] { userId }); // JDBCUtil占쏙옙 delete占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

			jdbcUtil.executeUpdate();
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
	 * 占쌍억옙占쏙옙 占쏙옙占쏙옙占� ID占쏙옙 占쌔댐옙占싹댐옙 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶븝옙占싱쏙옙占쏙옙占쏙옙 찾占쏙옙 User 占쏙옙占쏙옙占쏙옙 클占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙환.
	 */
	@Override
	public UserDTO findUser(String userId) {
		System.out.println("userDAOImpl finduser");
//		String sql = "SELECT USER_ID, PW, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION, JOB, MBTI, u.CLUB_ID, NICKNAME "
//				+ "FROM USERINFO u LEFT OUTER JOIN CLUB c ON u.CLUB_ID = c.CLUB_ID " + "WHERE user_id = ? ";
		String sql = "SELECT USER_ID, PW, NAME, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION AS USER_REGION, "
				+ "JOB, u.MBTI AS USER_MBTI, u.CLUB_ID AS USER_CLUBID, NICKNAME "
		+ "FROM USERINFO u LEFT OUTER JOIN CLUB c ON u.CLUB_ID = c.CLUB_ID "
		+ "WHERE USER_ID = ? "; 
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			UserDTO user = null;
			while (rs.next()) { // 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
				user = new UserDTO(); // User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占싻삼옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PW"));
                user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setRegistDate(rs.getString("REGISTERDATE"));
				user.setAge(rs.getString("AGE"));
				user.setRegion(rs.getString("USER_REGION"));
				user.setJob(rs.getString("JOB"));
				user.setMbti(rs.getString("USER_MBTI"));
				user.setClub(rs.getString("USER_CLUBID"));
				user.setNickname(rs.getString("NICKNAME"));
				//user.setHobby(rs.getArray("HOBBY"));
			}
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}

    @Override
    public String findId(String email) {
        System.out.println("userDAOImpl findId");
        String sql = "SELECT USER_ID "
            + "FROM USERINFO "
            + "WHERE EMAIL = ? "; 
        jdbcUtil.setSqlAndParameters(sql, new Object[] { email });

        try {
            ResultSet rs = jdbcUtil.executeQuery(); 
            String userId = null;
            if (rs.next()) { // 占싻삼옙 占쏙옙占쏙옙 占쌩곤옙
                userId = rs.getString("USER_ID");
            }
            System.out.println("userId by findId in userDAOimpl : " + userId);
            return userId;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            jdbcUtil.close(); // resource 占쏙옙환
        }
        return null;
    }
    

    public int updatePw(String userId, String password) {
        String sql = "UPDATE USERINFO "
                + "SET pw = ? "
                + "WHERE user_id = ?";
        Object[] param = new Object[] {password, userId};
        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            int result = jdbcUtil.executeUpdate(); // update 占쏙옙 占쏙옙占쏙옙
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
	 * 占쏙옙체 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환
	 */
	public List<UserDTO> findUserList() {
		String sql = "SELECT USER_ID, PW, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION AS REGION, JOB, u.MBTI AS MBTI, u.CLUB_ID AS CLUB_ID, NICKNAME "
				+ "FROM USERINFO u LEFT OUTER JOIN Club c ON u.club_id = c.club_Id " + "ORDER BY user_Id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil占쏙옙 query占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			List<UserDTO> userList = new ArrayList<UserDTO>(); // User占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				UserDTO user = new UserDTO();// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PW"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setRegistDate(rs.getString("REGISTERDATE"));
				user.setAge(rs.getString("AGE"));
				user.setRegion(rs.getString("REGION"));
				user.setJob(rs.getString("JOB"));
				user.setMbti(rs.getString("MBTI"));
				user.setClub(rs.getString("CLUB_ID"));
				user.setNickname(rs.getString("NICKNAME"));
				userList.add(user); // List占쏙옙 User 占쏙옙체 占쏙옙占쏙옙
			}
			return userList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}

	/**
	 * 특占쏙옙 클占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙湄占쏙옙占� 占싯삼옙占싹울옙 List占쏙옙 占쏙옙占쏙옙 占쏙옙 占쏙옙환
	 */
	public List<UserDTO> findUsersInClub(int clubId) {
		String sql = "SELECT USER_ID, NAME FROM USERINFO " + "WHERE club_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { clubId }); // JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			List<UserDTO> memList = new ArrayList<UserDTO>(); // member占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙占쏙옙
			while (rs.next()) {
				UserDTO member = new UserDTO();// User 占쏙옙체占쏙옙 占쏙옙占쏙옙占싹울옙 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
				member.setUserId(rs.getString("user_Id"));
				member.setName(rs.getString("name"));
				memList.add(member); // List占쏙옙 占쏙옙체 占쏙옙占쏙옙
			}
			return memList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return null;
	}

	public boolean existingUser(String userId) {
		String sql = "SELECT count(*) FROM USERINFO WHERE USER_ID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil占쏙옙 query占쏙옙占쏙옙 占신곤옙 占쏙옙占쏙옙 占쏙옙占쏙옙

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 占쏙옙占쏙옙
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 占쏙옙환
		}
		return false;
	}

}
