package connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
		
		//new Notify().start();
	}

	@Override
	public void registerClient(ClientInterface client, String username) throws RemoteException {
		clients.add(client);
		User new_user = new User();
		new_user.setId(clients.indexOf(client));
		new_user.setName(username);
		groupManager.addUserWithoutGroup(new_user);
		System.out.println("Novo cliente registrado com sucesso! Total: "+clients.size());
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
	public String leaveGroup(String user_name) throws RemoteException {
		User u = groupManager.findUser(user_name);
		Group g = u.getGroup() == null ? null : groupManager.findGroup(u.getGroup().getName());
		return groupManager.removeUserFromGroup(u, g);
	}	
	
	private class Notify extends Thread{
		
		public void run() {
			
			for(;;) {
				
				if(clients.size() > 0) {
					
					System.out.println("Notificando clientes");
					
					int i = 0;
					for (ClientInterface helloClientInterface : clients) {
						
						try {
							helloClientInterface.printMessage(new Message("Hello client " + (i++)));
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
					
					
					try {
						Thread.sleep(15 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
				}
				
			}
			
		}
	}
}
