package service.dto;


public class MsgDTO {
	private String MsgNo;
	private String rcverId;
	private String text;
	private String sendDate;
	private String sendId;
	public String getMsgNo() {
		return MsgNo;
	}
	public void setMsgNo(String msgNo) {
		MsgNo = msgNo;
	}
	public String getRcverId() {
		return rcverId;
	}
	public void setRcverId(String rcverId) {
		this.rcverId = rcverId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

}
