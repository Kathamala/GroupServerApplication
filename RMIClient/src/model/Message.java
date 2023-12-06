package model;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	//@ spec_public
	private String message;
	//@ spec_public
	private String sender = "";
	//@ spec_public
	private String group = "";
	//@ spec_public
	private String date = "";

	//@ public normal_behavior
	//@ requires message != null;
	//@ ensures this.message.equals(message);
	//@ pure
	public Message(String message) {
		this.message = message;
	}

	//@ ensures \result == message;
	//@ pure
	public String getMessage() {
		return message;
	}

	//@ requires message != null;
	//@ ensures this.message.equals(message);
	//@ assignable this.message;
	public void setMessage(String message) {
		this.message = message;
	}

	//@ ensures \result == date;
	//@ pure
	public String getDate() {
		return date;
	}

	//@ requires date != null;
	//@ ensures this.date.equals(date);
	//@ assignable this.date;
	public void setDate(String date) {
		this.date = date;
	}

	//@ ensures \result == sender;
	//@ pure
	public String getSender() {
		return sender;
	}

	//@ requires sender != null;
	//@ ensures this.sender.equals(sender);
	//@ assignable this.sender;
	public void setSender(String sender) {
		this.sender = sender;
	}

	//@ ensures \result == this.group;
	//@ pure
	public String getGroup() {
		return group;
	}

	//@ requires group != null;
	//@ ensures this.group.equals(group);
	//@ assignable this.group;
	public void setGroup(String group) {
		this.group = group;
	}

	//@ ensures \result != null;
	public String _toString() {
		String text = "";
		text += "======> Group: " + group + "\n";
		text += "==> [" + date + "] " + sender + ": " + message;
		return text;
	}
}