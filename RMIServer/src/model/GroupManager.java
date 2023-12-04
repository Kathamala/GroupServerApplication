package model;

import java.util.ArrayList;

public class GroupManager {

	//@ spec_public
	//@ non_null
	private ArrayList<Group> groups;
	//@ spec_public
	//@ non_null
	private ArrayList<User> users_without_group;

	//@ public normal_behavior
	//@ requires true;
	//@ ensures groups != null && users_without_group != null;
	//@ ensures groups.isEmpty() && users_without_group.isEmpty();
	//@ ensures groups.size() >= 0 && users_without_group.size() >= 0;
	public GroupManager() {
		super();
		this.groups = new ArrayList<Group>();
		this.users_without_group = new ArrayList<User>();
	}

	//@ requires _group != null;
	//@ requires _group.getId() != null;
	//@ requires _group.getName() != null;
	//@ requires findGroup(_group.getName()) == null;
	//@ ensures groups.size() >= \old(groups.size());
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

	//@ requires group != null;
	//@ requires group.getName() != null;
	//@ requires user != null;
	//@ requires user.getName() != null;
	//@ requires \forall int i; 0 <= i && i < groups.size(); groups.get(i).getName() != null;
	//@ ensures \result == true || \result == false;
	public boolean checkIfUserInGroup(User user, Group group) {
		for(Group g : groups) {
			for(User u : g.getUsers()) {
				if(g.getName().equals(group.getName()) && u.getName().equals(user.getName())) {
					return true;
				};
			}
		}
		return false;
	}

	//@ requires _name != null;
	//@ requires \forall int i; i >= 0 && i < groups.size(); groups.get(i).getName() != null;
	//@ pure
	public Group findGroup(String _name) {
		for(Group g : groups) {
			if(g.getName().equals(_name)) return g;
		}
		
		//System.out.println("Group " + _name + " not found.");
		return null;
	}

	//@ requires _name != null;
	//@ ensures \result == null || \result instanceof User;
	//@ pure
	public User findUser(String _name) {
		for(Group g : groups) {
			for(User u : g.getUsers()) {
				if(u.getName().equals(_name)) {
					return u;
				}
			}
		}
		
		for(User u : users_without_group) {
			if(u.getName().equals(_name)){
				return u;
			}
		}		
		
		//System.out.println("User " + _name + " not found.");
		return null;
	}	

	//@ requires users_without_group != null;
	//@ requires _user != null;
	//@ requires _group != null;
	//@ requires _user.getName() != null;
	//@ requires _group.getName() != null;
	// @ requires /(for any int i; 0 <= i && i < users_without_group.size(); users_without_group.get(i).getName() != null)/;
	//@ requires _user.findGroup(_group) == -1;
	// @ ensures users_without_group.size() <= \old(users_without_group.size());
	public String addUserToGroup(User _user, Group _group) {

		/*
		if(_group == null) {
			text += "This group does not exists.";
		}
		else if(_user == null) {
			text += "This user does not exists.";
		}

		else if(_user.findGroup(_group) != -1) {
			text += "The user is already part of this group.";
		}

		else {
			_group.addUser(_user);
			_user.joinGroup(_group);
			for(int i=0; i<users_without_group.size(); i++) {
				if(users_without_group.get(i).getName().equals(_user.getName())) {
					users_without_group.remove(i);
				}
			}
			text += "User added to group!";
		}

		 */

		_group.addUser(_user);
		_user.joinGroup(_group);
		for(int i=0; i < users_without_group.size(); i++) {
			if(users_without_group.get(i).getName().equals(_user.getName())) {
				users_without_group.remove(i);
			}
		}
		return "User added to group!";
	}

	//@ requires users_without_group != null;
	//@ requires _user != null;
	//@ requires _group != null;
	//@ requires _user.getName() != null;
	//@ requires _group.getName() != null;
	public String removeUserFromGroup(User _user, Group _group) {

		if(_user.findGroup(_group) == -1) {
			return "User was not on the group";
		}

		/*
		if(_group == null) {
			text += "This user isn't part of any groups.";
		}
		else if(_user == null) {
			text += "This user does not exists.";
		}
		else if(_user.findGroup(_group) == -1) {
			text += "The user does not belong to this group.";
		}
		else {
			_group.removeUser(_user);	
			_user.leaveGroup(_group);
			if(_user.getGroup().size() == 0) users_without_group.add(_user);
			text += "User left the group";
		}

		 */

		_group.removeUser(_user);
		_user.leaveGroup(_group);
		if(_user.getGroup().size() == 0){
			users_without_group.add(_user);
		}
		return "User left the group";
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

	//@ ensures \result == groups;
	//@ pure
	public ArrayList<Group> getGroups() {
		return groups;
	}

	//@ requires _user != null;
	//@ requires _user.getName() != null;
	//@ requires _user.getGroup() == null;
	//@ ensures users_without_group.size() >= \old(users_without_group.size());
	public void addUserWithoutGroup(User _user) {
		users_without_group.add(_user);
	}
}



















