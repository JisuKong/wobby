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
		jdbcUtil = new JDBCUtil(); // JDBCUtil 객체 생성
	}

	/**
	 * User 테이블에 새로운 행 생성
	 */
	public int create(UserDTO user) {
		String sql = "INSERT INTO USERINFO VALUES (?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";
		Object[] param = new Object[] { user.getUserId(), user.getPassword(), user.getName(), user.getEmail(),
				user.getPhone(), user.getRegistDate(), user.getAge(), user.getRegion(), user.getJob(), user.getMbti(),
				user.getNickname() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil 에 insert문과 매개 변수 설정

		String sql1 = "begin for i in 1..2 loop INSERT INTO USERS_HOBBY VALUES (?, ?) end loop; end";
		Object[] parameters = new Object[] { user.getHobby(), user.getUserId() };

		jdbcUtil.setSqlAndParameters(sql1, parameters);
		try {
			int result = jdbcUtil.executeUpdate(); // insert 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	/**
	 * 사용자 정보 수정
	 */
	public int update(UserDTO user) {
		String sql = "UPDATE USERINFO "
				+ "SET pw = ?, name = ?, email = ?, phone = ?, age = ?, region = ?, job = ?, mbti = ?, nickname = ? "
				+ "WHERE user_id = ?";
		Object[] param = new Object[] { user.getPassword(), user.getName(), user.getEmail(), user.getPhone(),
				user.getAge(), user.getRegion(), user.getJob(), user.getMbti(), user.getNickname(), user.getUserId() };
		jdbcUtil.setSqlAndParameters(sql, param); // JDBCUtil에 update문과 매개 변수 설정

		String sql1 = "begin for i in 1..2 loop UPDATE USERS_HOBBY SET HB_ID = ? WHERE user_id = ? end loop; end";
		Object[] parameters = new Object[] { user.getHobby(), user.getUserId() };

		jdbcUtil.setSqlAndParameters(sql1, parameters);

		try {
			int result = jdbcUtil.executeUpdate(); // update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	/**
	 * 사용자ID에 해당하는 정보를 삭제
	 */
	public int remove(String userId) {
		String sql = "DELETE FROM USERINFO WHERE user_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 delete문과 매개 변수 설정

		try {
			jdbcUtil.executeUpdate(); // delete 문 실행

			String sql1 = "DELETE FROM USERS_HOBBY WHERE user_id = ?";
			jdbcUtil.setSqlAndParameters(sql1, new Object[] { userId }); // JDBCUtil에 delete문과 매개 변수 설정

			jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close(); // resource 반환
		}
		return 0;
	}

	/**
	 * 주어진 사용자 ID에 해당하는 사용자 정보를 데이터베이스에서 찾아 User 도메인 클래스에 저장하여 반환.
	 */
	public UserDTO findUser(String userId) {
		String sql = "SELECT USER_ID, PW, EMAIL, PHONE, REGISTERDATE, AGE, REGION, JOB, MBTI, NICKNAME "
		        + "FROM USERINFO " 
		        + "WHERE user_id = ? ";

		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			UserDTO user = null;
			while (rs.next()) { // 학생 정보 발견
				user = new UserDTO(); // User 객체를 생성하여 학생 정보를 저장
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PW"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setRegistDate(rs.getString("REGISTERDATE"));
				user.setAge(rs.getString("AGE"));
				user.setRegion(rs.getString("U.REGION"));
				user.setJob(rs.getString("JOB"));
				user.setMbti(rs.getString("MBTI"));
				user.setClub(rs.getString("U.CLUB_ID"));
				user.setNickname(rs.getString("NICKNAME"));
				user.setHobby(rs.getArray("HOBBY"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 전체 사용자 정보를 검색하여 List에 저장 및 반환
	 */
	public List<UserDTO> findUserList() {
		String sql = "SELECT USER_ID, PW, EMAIL, PHONE, REGISTERDATE, AGE, u.REGION, JOB, MBTI, u.CLUB_ID, NICKNAME "
				+ "FROM USERINFO u LEFT OUTER JOIN Club c ON u.club_id = c.club_Id " + "ORDER BY user_Id";
		jdbcUtil.setSqlAndParameters(sql, null); // JDBCUtil에 query문 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<UserDTO> userList = new ArrayList<UserDTO>(); // User들의 리스트 생성
			while (rs.next()) {
				UserDTO user = new UserDTO();// User 객체를 생성하여 현재 행의 정보를 저장
				user.setUserId(rs.getString("USER_ID"));
				user.setPassword(rs.getString("PW"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setRegistDate(rs.getString("REGISTERDATE"));
				user.setAge(rs.getString("AGE"));
				user.setRegion(rs.getString("U.REGION"));
				user.setJob(rs.getString("JOB"));
				user.setMbti(rs.getString("MBTI"));
				user.setClub(rs.getString("U.CLUB_ID"));
				user.setNickname(rs.getString("NICKNAME"));
				userList.add(user); // List에 User 객체 저장
			}
			return userList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	/**
	 * 특정 클럽에 속한 사용자들을 검색하여 List에 저장 및 반환
	 */
	public List<UserDTO> findUsersInClub(int clubId) {
		String sql = "SELECT USER_ID, NAME FROM USERINFO " + "WHERE club_id = ?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { clubId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			List<UserDTO> memList = new ArrayList<UserDTO>(); // member들의 리스트 생성
			while (rs.next()) {
				UserDTO member = new UserDTO();// User 객체를 생성하여 현재 행의 정보를 저장
				member.setUserId(rs.getString("user_Id"));
				member.setName(rs.getString("name"));
				memList.add(member); // List에 객체 저장
			}
			return memList;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return null;
	}

	public boolean existingUser(String userId) {
		String sql = "SELECT count(*) FROM USERINFO WHERE USER_ID=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] { userId }); // JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery(); // query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close(); // resource 반환
		}
		return false;
	}

}
