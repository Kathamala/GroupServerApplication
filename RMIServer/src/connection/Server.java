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

    //@ spec_public
    private volatile List<ClientInterface> clients;
    //@ spec_public
    private GroupManager groupManager;

    //@ ensures clients != null && clients.isEmpty();
    //@ ensures groupManager != null;
    //@ pure
    protected Server() throws RemoteException {
        clients = new ArrayList<ClientInterface>();
        groupManager = new GroupManager();
    }

    //@ requires client != null && username != null;
    //@ requires groupManager != null && clients != null;
    //@ ensures \result == true || \result == false;
    //@ ensures (\result == true) ==> (clients.size() == \old(clients.size()) + 1);
    //@ ensures (\result == true) ==> (clients.get(\old(clients.size()+1)) == clients.get(clients.size()));
    //@ ensures (\result == false) ==> clients.size() == \old(clients.size());
    @Override
    public boolean registerClient(ClientInterface client, String username) throws RemoteException {
        if (groupManager.findUser(username) != null) {
            return false;
        }
        clients.add(client);
        groupManager.addUserWithoutGroup(new User(clients.indexOf(client), username));
        System.out.println("Novo cliente registrado com sucesso! Total: " + clients.size());
        return true;
    }


	//@ requires groupManager != null;
	//@ ensures \result != null;
	@Override
	public String listGroups() throws RemoteException {
		return groupManager.listGroups();
	}

	//@ requires name != null;
	//@ requires groupManager != null && groupManager.getGroups() != null;
	//@ ensures \result != null;
	@Override
	public String createGroup(String name) throws RemoteException {
		return groupManager.createGroup(new Group(groupManager.getGroups().size(), name));
	}

	//@ requires group_name != null && user_name != null;
	//@ requires groupManager != null;
	//@ ensures \result != null;
	@Override
	public String joinGroup(String group_name, String user_name) throws RemoteException {
		Group g = groupManager.findGroup(group_name);
		User u = groupManager.findUser(user_name);
		return groupManager.addUserToGroup(u, g);
	}

	//@ requires group_name != null && user_name != null;
	//@ requires groupManager != null;
	//@ ensures \result != null;
	@Override
	public String leaveGroup(String group_name, String user_name) throws RemoteException {
		User u = groupManager.findUser(user_name);
		Group g = u.getGroup() == null ? null : groupManager.findGroup(group_name);
		return groupManager.removeUserFromGroup(u, g);
	}

	//@ requires destiny_group != null && sender_user != null && message != null;
	//@ requires groupManager != null;
	//@ ensures \result.equals("You are not in this group!") || \result.equals("Message sent!");
	@Override
	public String sendMessage(String destiny_group, String sender_user, String message) throws RemoteException {
		User user = groupManager.findUser(sender_user);
		Group group = user.getGroup() == null ? null : groupManager.findGroup(destiny_group);
		if(group == null || !groupManager.checkIfUserInGroup(user, group)) {
			return "You are not in this group!";
		}

		for(User u : group.getUsers()) {
			if(u.getName().equals(sender_user)) continue;
			Message m = new Message(message);
			m.setDate(new Date(System.currentTimeMillis()).toString());
			m.setSender(sender_user);
			m.setGroup(destiny_group);
			clients.get(u.getId()).printMessage(m);
		}

		return "Message sent!";
	}
}