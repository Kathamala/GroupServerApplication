package connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Group;
import model.GroupManager;
import model.Message;
import model.User;

public class Server extends UnicastRemoteObject implements ServerInterface {

	private static final long serialVersionUID = 1L;
	private volatile List<ClientInterface> clients = new ArrayList<ClientInterface>();
	GroupManager groupManager = new GroupManager();
	
	protected Server() throws RemoteException {
		super();
	}

	@Override
	public boolean registerClient(ClientInterface client, String username) throws RemoteException {
		if(groupManager.findUser(username) != null) {
			return false;
		}
		clients.add(client);
		User new_user = new User();
		new_user.setId(clients.indexOf(client));
		new_user.setName(username);
		groupManager.addUserWithoutGroup(new_user);
		System.out.println("Novo cliente registrado com sucesso! Total: "+clients.size());
		return true;
	}	

	@Override
	public String listGroups() throws RemoteException {
		return groupManager.listGroups();
	}
	
	@Override
	public String createGroup(String name) throws RemoteException {
		Group g = new Group();
		g.setName(name);
		g.setId(groupManager.getGroups().size());
		return groupManager.createGroup(g);
	}
	
	@Override
	public String joinGroup(String group_name, String user_name) throws RemoteException {
		Group g = groupManager.findGroup(group_name);
		User u = groupManager.findUser(user_name);
		return groupManager.addUserToGroup(u, g);
	}	
	
	@Override
	public String leaveGroup(String group_name, String user_name) throws RemoteException {
		User u = groupManager.findUser(user_name);
		Group g = u.getGroup() == null ? null : groupManager.findGroup(group_name);
		return groupManager.removeUserFromGroup(u, g);
	}	
	
	@Override
	public String sendMessage(String destiny_group, String sender_user, String message) throws RemoteException {
		//1. Check if user is in group
		User user = groupManager.findUser(sender_user);
		Group group = user.getGroup() == null ? null : groupManager.findGroup(destiny_group);
		if(group == null || !groupManager.checkIfUserInGroup(user, group)) {
			return "You are not in this group!";
		}
		
		//2. Send message
		for(User u : group.getUsers()) {
			if(u.getName().equals(sender_user)) continue;
			Message m = new Message(message);
			m.setDate(new Date(System.currentTimeMillis()));
			m.setSender(sender_user);
			m.setGroup(destiny_group);
			clients.get(u.getId()).printMessage(m);
		}
		
		return "Message sent!";
	}		
}
