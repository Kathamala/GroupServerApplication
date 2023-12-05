package model;

import java.util.ArrayList;
import java.rmi.RemoteException;

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
	//@ ensures groups.size() >= \old(groups.size());
	public String createGroup(Group _group) {

		if(_group.getId() == null || _group.getName() == null) {
			return  "Can't create a group with null id or name.";
		}
		try {
			Group g = findGroup(_group.getName());
		} catch (RemoteException e) {
			return "A group with this name already exists.";
		}

		groups.add(_group);
		return "Group Created!";
	}

	//@ requires group != null;
	//@ requires group.getName() != null;
	//@ requires user != null;
	//@ requires user.getName() != null;
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
	// @ signals_only RemoteException;
	//@ pure
	public Group findGroup(String _name) throws RemoteException {
		for(Group g : groups) {
			if(g.getName().equals(_name)) return g;
		}
		
		//System.out.println("Group " + _name + " not found.");
		 throw new RemoteException("Group " + _name + " not found.");
	}

	//@ requires _name != null;
	// @ signals_only RemoteException;
	//@ pure
	public User findUser(String _name) throws RemoteException{
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
		throw new RemoteException("User " + _name + " not found.");
	}	

	//@ requires users_without_group != null;
	//@ requires _user != null;
	//@ requires _group != null;
	//@ requires _user.getName() != null;
	//@ requires _group.getName() != null;
	public String addUserToGroup(User _user, Group _group) {
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
			//@ assert g != null;
			//@ assert g.getId() != null;
			//@ assert g.getName() != null;
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



















