package model;

import java.util.ArrayList;

public class User {
	private Integer id;
	private String name;
	private ArrayList<Group> groups = new ArrayList<Group>();

	public User() {
		super();
	}
	
	public void joinGroup(Group _group) {
		if(findGroupIndex(_group) == -1) {
			groups.add(_group);
			System.out.println("You joined the group!");
		}
		else {
			System.out.println("You are already part of the group: " + _group.getName());
		}
	}
	
	public void leaveGroup(Group _group) {
		int index = findGroupIndex(_group); 
		if(index != -1) {
			groups.remove(index);
			System.out.println("You left the group!");
		}
		else {
			System.out.println("You are not part of the group: " + _group.getName());
		}
	}
	
	public int findGroupIndex(Group _group) {
		for(int i = 0; i < groups.size(); i++) {
			if(groups.get(i).getName().equals(_group.getName())) {
				return i;
			}
		}
		
		return -1;
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
	public ArrayList<Group> getGroup() {
		return groups;
	}
	public void setGroup(ArrayList<Group> group) {
		this.groups = group;
	}
}
