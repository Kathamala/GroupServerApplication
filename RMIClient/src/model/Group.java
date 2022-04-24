package model;

import java.util.ArrayList;

public class Group {
	private Integer id;
	private String name;
	private ArrayList<User> users;
	
	public Group() {
		super();
	}
	
	public void addUser(User _user) {
		users.add(_user);
		System.out.println("User added!");
	}
	
	public void removeUser(User _user) {
		int index = findUserIndex(_user);
		if(index != -1) {
			users.remove(index);
		}
		System.out.println("User removed!");
	}
	
	public int findUserIndex(User _user) {
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).equals(_user)) {
				return i;
			}
		}
		
		return -1;
	}
	
	public String listUsers() {
		String text = "";
		
		if(users.size() == 0) {
			text += "==> No users in this group.\n";
			return text;
		}
		
		for(User u : users) {
			text += "==> User " + u.getId() + ": " + u.getName() + "\n";
		}
		
		return text;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
