package model;

import java.util.ArrayList;

public class User {
	//@ spec_public
	private Integer id;
	//@ spec_public
	private String name;
	//@ spec_public
	private ArrayList<Group> groups = new ArrayList<Group>();

	//@ public normal_behavior
	//@ requires name != null && name.length() > 2;
	//@ requires id >= 0 && id <= Integer.MAX_VALUE;
	//@ ensures this.name.equals(name);
	//@ ensures this.id == id;
	//@ pure
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	//@ requires _group != null;
	//@ requires _group.getUsers() != null;
	//@ requires _group.getName() != null;
	//@ ensures groups.size() >= \old(groups.size());
	//@ ensures (groups.size() == \old(groups.size())) || (groups.size() == \old(groups.size()) + 1);
	//@ ensures \forall int i; 0 <= i && i < \old(groups.size()); groups.get(i) == \old(groups.get(i));
	public void joinGroup(Group _group) {
		if(findGroup(_group) == -1) {
			groups.add(_group);
			System.out.println("You joined the group!");
		}
		else {
			System.out.println("You are already part of the group: " + _group.getName());
		}
	}

	//@ requires _group != null;
	//@ ensures groups.size() <= \old(groups.size());
	public void leaveGroup(Group _group) {
		int index = findGroup(_group); 
		if(index != -1) {
			groups.remove(index);
			System.out.println("You left the group!");
		}
		else {
			System.out.println("You are not part of the group: " + _group.getName());
		}
	}

	//@ requires _group != null;
	//@ ensures \result >= -1 && \result < groups.size();
	//@ pure
	public int findGroup(Group _group) {
		for(int i = 0; i < groups.size(); i++) {
			if(groups.get(i).getName().equals(_group.getName())) {
				return i;
			}
		}
		
		return -1;
	}

	//@ ensures \result == id;
	//@ pure
	public Integer getId() {
		return id;
	}

	//@ requires id != null;
	//@ requires id >= 0;
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
	//@ requires name.length() > 2;
	//@ ensures this.name.equals(name);
	public void setName(String name) {
		this.name = name;
	}
	//@ ensures \result == groups;
	//@ pure
	public ArrayList<Group> getGroup() {
		return groups;
	}

	//@ requires group != null;
	//@ ensures this.groups.equals(group);
	public void setGroup(ArrayList<Group> group) {
		this.groups = group;
	}
}
