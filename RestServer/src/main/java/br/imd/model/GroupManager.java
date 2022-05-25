package br.imd.model;

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
			
			for(Message m : _group.getMessages()) {
				_user.addRecievedMessage(m);
			}
		}

		return text;
	}
	
	public String removeUserFromGroup(User _user, Group _group) {
		String text = "";
		
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

		return text;
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
	
	public String addMessage(String groupname, String username, String text) {
		User u = findUser(username);
		
		Group g = u.getGroup() == null ? null : findGroup(groupname);
		if(g == null || !checkIfUserInGroup(u, g)) {
			return "You are not in this group!";
		}

		Message m = new Message(text);
		m.setSender(username);
		m.setGroup(groupname);
		
		g.addMessage(m);
		u.addRecievedMessage(m);
		return "Message sent!";
	}
	
	public String getMessages(String username) {
		User u = findUser(username);
		String text = "";
		
		boolean firstMessageOfGroup = true;
		
		for(Group g : u.getGroup()) {
			firstMessageOfGroup = true;
			for(Message m : g.getMessages()) {
				if(!u.getRecievedMessages().contains(m)) {
					if(firstMessageOfGroup) {
						text += "======> Group: " + g.getName() + "\n";
						firstMessageOfGroup = false;
					}
					text += "==> [" + m.getDate() + "] " + m.getSender() + ": " + m.getMessage();
					u.addRecievedMessage(m);
				}
			}
		}
		
		return text != "" ? text : null;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}
	
	public void addUserWithoutGroup(User _user) {
		users_without_group.add(_user);
	}
}






