package model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	//@ spec_public
	private String message;
	//@ spec_public
	private String sender = "";
	//@ spec_public
	private String group = "";
	//@ spec_public
	private Date date;

	//@ public normal_behavior
	//@ requires mes != null;
	//@ ensures message.equals(mes);
	//@ ensures date != null;
	//@ pure
	public Message(String mes) {
		this.message = mes;
		this.date = new Date();
	}

	//@ ensures \result == message;
	//@ pure
	public String getMessage() {
		return message;
	}

	//@ requires mes != null;
	//@ ensures message.equals(mes);
	//@ assigns message;
	public void setMessage(String mes) {
		this.message = mes;
	}

	//@ ensures \result == date;
	//@ pure
	public Date getDate() {
		return date;
	}

	//@ requires dat != null;
	//@ ensures date != null;
	//@ assigns date;
	public void setDate(Date dat) {
		this.date = dat;
	}

	//@ ensures \result == sender;
	//@ pure
	public String getSender() {
		return sender;
	}

	//@ requires sen != null;
	//@ ensures sender.equals(sen);
	//@ assigns sender;
	public void setSender(String sen) {
		this.sender = sen;
	}

	//@ ensures \result == group;
	//@ pure
	public String getGroup() {
		return group;
	}

	//@ requires gro != null;
	//@ ensures group.equals(gro);
	//@ assigns group;
	public void setGroup(String gro) {
		this.group = gro;
	}

	//@ ensures \result != null;
	public String _toString() {
		String text = "";
		text += "======> Group: " + group + "\n";
		text += "==> [" + date + "] " + sender + ": " + message;
		return text;
	}

}
