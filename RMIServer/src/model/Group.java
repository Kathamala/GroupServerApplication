package model;

import java.util.ArrayList;

public class Group {

	//@ spec_public
	private Integer id;
	//@ spec_public
	private String name;
	//@ spec_public
	private ArrayList<User> users = new ArrayList<User>();

	//@ public normal_behavior
	//@ requires name != null;
	//@ requires id >= 0 && id <= Integer.MAX_VALUE;
	//@ ensures this.name.equals(name);
	//@ ensures this.id == id;
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	//@ requires _user != null;
	//@ ensures users.size() == \old(users.size()) + 1;
	//@ ensures \forall int i; 0 <= i && i < \old(users.size()); users.get(i) == \old(users.get(i));
	public void addUser(User _user) {
		users.add(_user);
		System.out.println("User added!");
	}

	//@ requires _user != null;
	//@ ensures users.size() <= \old(users.size());
	public void removeUser(User _user) {
		int index = findUserIndex(_user);
		if(index != -1) {
			users.remove(index);
		}
		System.out.println("User removed!");
	}

	//@ requires _user != null;
	//@ ensures \result >= -1 && \result < users.size();
	//@ pure
	public int findUserIndex(User _user) {
		for(int i=0; i<users.size(); i++) {
			if(users.get(i).equals(_user)) {
				return i;
			}
		}
		
		return -1;
	}

	//@ ensures \result != null;
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

	//@ ensures \result == id;
	//@ pure
	public Integer getId() {
		return id;
	}

	//@ requires id != null;
	//@ requires id >= 0 && id <= Integer.MAX_VALUE;
	//@ ensures this.id.equals(id);
	public void setId(Integer id) {
		this.id = id;
	}

	//@ ensures \result == name;
	//@ pure
	public String getName() {
		return name;
	}

	//@ requires name != null;
	//@ ensures this.name.equals(name);
	public void setName(String name) {
		this.name = name;
	}

	//@ ensures \result == users;
	//@ pure
	public ArrayList<User> getUsers() {
		return users;
	}

	//@ requires users != null;
	//@ requires users.size() >= 0;
	//@ requires \forall int i; 0 <= i && i < users.size(); users.get(i) != null;
	//@ requires \forall int i; 0 <= i && i < users.size(); users.get(i).getName() != null;
	//@ ensures this.users.equals(users);
	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
