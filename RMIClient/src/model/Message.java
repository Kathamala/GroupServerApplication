package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	
	private String message;
	private String sender;
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

	@Override
	public String toString() {
		return "[" + date + "] " + sender + ": " + message;
	}
	
	
	
	

}
