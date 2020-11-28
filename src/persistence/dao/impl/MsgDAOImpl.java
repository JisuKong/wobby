package persistence.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistence.dao.MsgDAO;
import service.dto.MsgDTO;

//������ �߰�
public class MsgDAOImpl implements MsgDAO {

	private JDBCUtil jdbcUtil = null;

	public MsgDAOImpl() {
		jdbcUtil = new JDBCUtil();
	}

	/**
	 * ���� Message ��� ��ȯ
	 */
	public List<MsgDTO> ReceiveMsgList(String userId) {
		String query = "SELECT MESSAGENO, SEND_ID, RECIEVE_ID, TEXT, SEND_DATE "
				+ "FROM MESSAGE "
				+ "WHERE RECIVE_ID = ?";
		jdbcUtil.setSqlAndParameters(query, new Object[] {userId});		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<MsgDTO> list = new ArrayList<MsgDTO>();		// MsgDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				MsgDTO dto = new MsgDTO();		// �ϳ��� MsgDTO ��ü ���� �� ���� ����
				dto.setMsgNo(rs.getString("MESSAGENO"));
				dto.setSendId(rs.getString("SEND_ID"));
				dto.setRcverId(rs.getString("RECIEVE_ID"));
				dto.setText(rs.getString("TEXT"));
				dto.setSendDate(rs.getString("SEND_DATE"));
				list.add(dto);		
			}
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}
	
	/**
	 * ���� Message ��� ��ȯ
	 */
	public List<MsgDTO> SendMsgList(String userId) {
		String query = "SELECT MESSAGENO, SEND_ID, RECIEVE_ID, TEXT, SEND_DATE "
				+ "FROM MESSAGE "
				+ "WHERE SEND_ID = ?";
		jdbcUtil.setSqlAndParameters(query, new Object[] {userId});		// JDBCUtil �� query ����
		
		try { 
			ResultSet rs = jdbcUtil.executeQuery();		// query �� ����			
			List<MsgDTO> list = new ArrayList<MsgDTO>();		// MsgDTO ��ü���� ������� list ��ü
			while (rs.next()) {	
				MsgDTO dto = new MsgDTO();		// �ϳ��� MsgDTO ��ü ���� �� ���� ����
				dto.setMsgNo(rs.getString("MESSAGENO"));
				dto.setSendId(rs.getString("SEND_ID"));
				dto.setRcverId(rs.getString("RECIEVE_ID"));
				dto.setText(rs.getString("TEXT"));
				dto.setSendDate(rs.getString("SEND_DATE"));
				list.add(dto);		
			}
			return list;		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return null;	
	}
	
	/**
	 * Message ���̺� ���ο� �� ���� (PK ���� Sequence�� �̿��Ͽ� �ڵ� ����)
	 */
	public int sendMsg(MsgDTO msg) {
		int result = 0;
		String insertQuery = "INSERT INTO STUDENT (MESSAGENO, SEND_ID, RECIVE_ID, TEXT, SEND_DATE) "
				+ "VALUES (MSG_SEQ.NEXTVAL, ?, ?, ?, ?) ";
		Object[] param = new Object[] { msg.getSendId(), msg.getRcverId(), msg.getText(), msg.getSendDate()};
		
		jdbcUtil.setSql(insertQuery); // JDBCUtil �� insert �� ����
		jdbcUtil.setParameters(param);
		
		try {				
			result = jdbcUtil.executeUpdate();		// insert �� ����
			System.out.println("���ο� �޼����� �ۼ��Ǿ����ϴ�.");
		} catch (SQLException ex) {
			System.out.println("�Է¿��� �߻�!!!");
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();		// ResultSet, PreparedStatement, Connection ��ȯ
		}		
		return result;
	}

	/**
	 * Message ����
	 */
	public int deleteMsg(String msgNo){
		String sql = "DELETE FROM MESSAGE WHERE MESSAGENO=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {msgNo});	// JDBCUtil�� delete���� �Ű� ���� ����

		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
	
	/**
	 * Message�� ���� User�� ID, ���� �˻�
	 */
	public int searchMsg(String data) {
		String query = "SELECT MESSAGENO, SEND_ID, RECIEVE_ID, TEXT, SEND_DATE "
				+ "FROM MESSAGE ";
		String sql = query + "WHERE SEND_ID=? OR TEXT=?";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {data});
	
		try {				
			int result = jdbcUtil.executeUpdate();	// delete �� ����
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return 0;
	}
}
