package model;

import java.util.ArrayList;

public class GroupManager {
	ArrayList<Group> groups = new ArrayList<Group>();
	ArrayList<User> users_without_group = new ArrayList<User>();
	
	public String createGroup(Group _group) {
		String text = "";
		
		if(_group.getId() == null || _group.getName() == null) {
			text += "Can't create a group with null id or name.";
		}
		else if(findGroup(_group.getName()) != null) {
			text += "A group with this name already exists.";
		}
		else {
			groups.add(_group);
			text += "Group Created!";
		}
		
		return text;
	}
	
	public Group findGroup(String _name) {
		for(Group g : groups) {
			if(g.getName().equals(_name)) return g;
		}
		
		System.out.println("Group " + _name + " not found.");
		return null;
	}
	
	public User findUser(String _name) {
		for(Group g : groups) {
			for(User u : g.getUsers()) {
				if(u.getName().equals(_name)) return u;
			}
		}
		
		for(User u : users_without_group) {
			if(u.getName().equals(_name)) return u;
		}		
		
		System.out.println("User " + _name + " not found.");
		return null;
	}	
	
	public String addUserToGroup(User _user, Group _group) {
		String text = "";

		if(_group == null) {
			text += "This group does not exists.";
		}
		else if(_user == null) {
			text += "This user does not exists.";
		}
		else if(_user.getGroup() != null) {
			text += "This user can't join another group, because it's already part of the group " + _user.getGroup().getName()
					+ ". Leave this group to join another.";
		}
		else {
			_group.addUser(_user);
			_user.setGroup(_group);
			for(int i=0; i<users_without_group.size(); i++) {
				if(users_without_group.get(i).getName().equals(_user.getName())) {
					users_without_group.remove(i);
				}
			}
			text += "User added to group!";
		}

		return text;
	}
	
	public void removeUserFromGroup(User _user, Group _group) {
		Group g = findGroup(_group.getName());
		if(g == null) {
			System.out.println("The group " + _group.getName() + " does not exists.");
			return;
		}
		//TODO:
			// After removing user from group, update the reference from the user
			// Add the user to the users_without_groups array
		g.removeUser(_user);
	}	
	
	public String listGroups() {
		String text = "";
		if(groups.size() == 0) {
			text += "======> No groups added.\n";
			return text;
		}
		
		for(Group g : groups) {
			text += "======> Group " + g.getId() + ": " + g.getName() + "\n";
			text += g.listUsers();
			text += "\n";
		}
		
		return text;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public void addUserWithoutGroup(User _user) {
		users_without_group.add(_user);
	}
}



















