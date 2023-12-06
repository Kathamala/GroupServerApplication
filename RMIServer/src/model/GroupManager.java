package model;

import java.util.ArrayList;
import java.rmi.RemoteException;

public class GroupManager {

	//@ spec_public
	private ArrayList<Group> groups ;
	//@ spec_public
	private ArrayList<User> users_without_group;

	//@ public normal_behavior
	//@ ensures groups != null && users_without_group != null;
	//@ ensures groups.isEmpty() && users_without_group.isEmpty();
	//@ ensures groups.size() >= 0 && users_without_group.size() >= 0;
	//@ pure
	public GroupManager() {
		groups = new ArrayList<Group>();
		users_without_group = new ArrayList<User>();
	}

	//@ requires _group != null;
	//@ ensures \result.equals("Can't create a group with null id or name.") || \result.equals("A group with this name already exists.") || \result.equals("Group Created!");
	//@ ensures \result.equals("Group Created!") ==> (groups.size() == \old(groups.size()+1));
	//@ ensures !\result.equals("Group Created!") ==> (groups.size() == \old(groups.size()));
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
	//@ pure
	public Group findGroup(String _name) throws RemoteException {
		for(Group g : groups) {
			if(g.getName().equals(_name)) return g;
		}
		
		//System.out.println("Group " + _name + " not found.");
		 throw new RemoteException("Group " + _name + " not found.");
	}

	//@ requires _name != null;
	//@ requires groups != null;
	//@ requires users_without_group != null;
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
	//@ requires _user != null && _group != null;
	//@ requires _user.getName() != null && _group.getName() != null && _group.getUsers() != null;
	//@ ensures \result.equals("User added to group!");
	public String addUserToGroup(User _user, Group _group) {
		//_group.addUser(_user);
		//_user.joinGroup(_group);
		ArrayList<User> usersToRemove = new ArrayList<>();

		for (User u : users_without_group) {
			if (u.getName().equals(_user.getName())) {
				usersToRemove.add(u);
			}
		}

		users_without_group.removeAll(usersToRemove);
		//@ assume groups != null;
		return "User added to group!";
	}

	//@ requires users_without_group != null;
	//@ requires _user != null && _group != null;
	//@ requires _user.getName() != null && _group.getName() != null;
	//@ ensures \result.equals("User was not on the group") || \result.equals("User left the group");
	public String removeUserFromGroup(User _user, Group _group) {

		if(_user.findGroup(_group) == -1) {
			return "User was not on the group";
		}

		//_group.removeUser(_user);
		//_user.leaveGroup(_group);
		if(_user.getGroup().size() == 0){
			users_without_group.add(_user);
		}
		return "User left the group";
	}	

	//@ requires groups != null;
	//@ ensures \result != null;
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
		}
		
		return text;
	}

	//@ ensures \result == groups;
	//@ pure
	public ArrayList<Group> getGroups() {
		return groups;
	}

	//@ requires users_without_group != null;
	//@ requires users_without_group.size() >= 0;
	//@ requires _user != null;
	//@ requires _user.getName() != null;
	//@ requires _user.getGroup() == null;
	//@ ensures users_without_group.size() >= \old(users_without_group.size());
	public void addUserWithoutGroup(User _user) {
		//users_without_group.add(_user);
	}
}



















