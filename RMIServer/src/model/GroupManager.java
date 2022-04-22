package model;

import java.util.ArrayList;

public class GroupManager {
	ArrayList<Group> groups = new ArrayList<Group>();
	
	public void createGroup(Group _group) {
		if(_group.getId() != null && _group.getName() != null) {
			groups.add(_group);
			System.out.println("Group Created!");
		}
		else {
			System.out.println("Can't create a group with null id or name.");
		}
	}
	
	public Group findGroup(String _name) {
		for(Group g : groups) {
			if(g.getName() == _name) return g;
		}
		
		System.out.println("Group " + _name + " not found.");
		return null;
	}
	
	public void addUserToGroup(User _user, Group _group) {
		Group g = findGroup(_group.getName());
		if(g == null) {
			System.out.println("The group " + _group.getName() + " does not exists.");
			return;
		}
		
		g.addUser(_user);
	}
	
	public void removeUserFromGroup(User _user, Group _group) {
		Group g = findGroup(_group.getName());
		if(g == null) {
			System.out.println("The group " + _group.getName() + " does not exists.");
			return;
		}
		
		g.removeUser(_user);
	}	
}
