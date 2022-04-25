package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String message;
	private String sender;
	private String group;
	private Date date;
	
	public Message(String message) {
		this.message = message;
		this.date = new Date();
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String toString() {
		String text = "";
		text += "======> Group:" + group;
		text += "[" + date + "] " + sender + ": " + message;
		return text;
	}
	
	
	
	

}
