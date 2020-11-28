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
		jdbcUtil = new JDBCUtil(); // JDBCUtil ��ü ����
	}

	/**
	 * User ���̺� ���ο� �� ����
	 */
	public int create(UserDTO user) {
		String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getUserId(), user.getPassword(), user.getName(), user.getEmail(),
				user.getPhone(), user.getRegistDate(), user.getAge(), user.getRegion(), user.getJob(), user.getMbti(),
				user.getNickname() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil �� insert���� �Ű� ���� ����

		String sql1 = "begin for i in 1..2 loop INSERT INTO USERS_HOBBY VALUES (?, ?) end loop; end";
		Object[] parameters = new Object[] { user.getHobby(), user.getUserId() };

		jdbcUtil.setSqlAndParameters(sql1, parameters);
		try {
			int result = jdbcUtil.executeUpdate(); // insert �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	
	/**
	 * ����� ���� ����
	 */
	public int update(UserDTO user) {
		String sql = "UPDATE USERINFO "
				+ "SET pw = ?, name = ?, email = ?, phone = ?, age = ?, region = ?, job = ?, mbti = ?, nickname = ? "
				+ "WHERE user_id = ?";
		Object[] param = new Object[] { user.getPassword(), user.getName(), user.getEmail(), user.getPhone(),
				user.getAge(), user.getRegion(), user.getJob(), user.getMbti(), user.getNickname(), user.getUserId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil�� update���� �Ű� ���� ����

		String sql1 = "begin for i in 1..2 loop UPDATE USERS_HOBBY SET HB_ID = ? WHERE user_id = ? end loop; end";
		Object[] parameters = new Object[] { user.getHobby(), user.getUserId() };

		jdbcUtil.setSqlAndParameters(sql1, parameters);

		try {
			int result = jdbcUtil.executeUpdate(); // update �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * �����ID�� �ش��ϴ� ������ ����
	 */
	public int remove(String userId) {
		
		try {
			String sql = "DELETE FROM USERINFO WHERE user_id = ?";
			jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�� delete���� �Ű� ���� ����

			jdbcUtil.executeUpdate(); // delete �� ����

			String sql1 = "DELETE FROM USERS_HOBBY WHERE user_id = ?";
			jdbcUtil.setSqlAndParameters(sql1, new Object[] { userId }); // JDBCUtil�� delete���� �Ű� ���� ����

			jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource ��ȯ
		}
		return 0;
	}

	/**
	 * �־��� ����� ID�� �ش��ϴ� ����� ������ �����ͺ��̽����� ã�� User ������ Ŭ������ �����Ͽ� ��ȯ.
	 */
	@Override
	public UserDTO findUser(String userId) {
		System.out.println("userDAOImpl finduser");
//		String sql = "SELECT USER_ID, PW, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION, JOB, MBTI, u.CLUB_ID, NICKNAME "
//				+ "FROM USERINFO u LEFT OUTER JOIN CLUB c ON u.CLUB_ID = c.CLUB_ID " + "WHERE user_id = ? ";
		String sql = "SELECT USER_ID, PW, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION AS USER_REGION, "
				+ "JOB, u.MBTI AS USER_MBTI, u.CLUB_ID AS USER_CLUBID, NICKNAME "
		+ "FROM USERINFO u LEFT OUTER JOIN CLUB c ON u.CLUB_ID = c.CLUB_ID "
		+ "WHERE USER_ID = ? "; 
		
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			UserDTO user = null;
			while (rs.next()) { // �л� ���� �߰�
				user = new UserDTO(); // User ��ü�� �����Ͽ� �л� ������ ����
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PW"));
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
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * ��ü ����� ������ �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<UserDTO> findUserList() {
		String sql = "SELECT USER_ID, PW, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION AS REGION, JOB, u.MBTI AS MBTI, u.CLUB_ID AS CLUB_ID, NICKNAME "
				+ "FROM USERINFO u LEFT OUTER JOIN Club c ON u.club_id = c.club_Id " + "ORDER BY user_Id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil�� query�� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<UserDTO> userList = new ArrayList<UserDTO>(); // User���� ����Ʈ ����
			while (rs.next()) {
				UserDTO user = new UserDTO();// User ��ü�� �����Ͽ� ���� ���� ������ ����
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
				userList.add(user); // List�� User ��ü ����
			}
			return userList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	/**
	 * Ư�� Ŭ���� ���� ����ڵ��� �˻��Ͽ� List�� ���� �� ��ȯ
	 */
	public List<UserDTO> findUsersInClub(int clubId) {
		String sql = "SELECT USER_ID, NAME FROM USERINFO " + "WHERE club_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { clubId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			List<UserDTO> memList = new ArrayList<UserDTO>(); // member���� ����Ʈ ����
			while (rs.next()) {
				UserDTO member = new UserDTO();// User ��ü�� �����Ͽ� ���� ���� ������ ����
				member.setUserId(rs.getString("user_Id"));
				member.setName(rs.getString("name"));
				memList.add(member); // List�� ��ü ����
			}
			return memList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return null;
	}

	public boolean existingUser(String userId) {
		String sql = "SELECT count(*) FROM USERINFO WHERE USER_ID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil�� query���� �Ű� ���� ����

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query ����
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource ��ȯ
		}
		return false;
	}

}
