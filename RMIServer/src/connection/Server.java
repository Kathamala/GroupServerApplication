package connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import model.GroupManager;
import model.Message;

public class Server extends UnicastRemoteObject implements ServerInterface {

	private static final long serialVersionUID = 1L;
	private volatile List<ClientInterface> clients = new ArrayList<ClientInterface>();
	GroupManager groupManager = new GroupManager();
	
	protected Server() throws RemoteException {
		super();	
		
		//new Notify().start();
	}

	@Override
	public void registerClient(ClientInterface client) throws RemoteException {
			
		clients.add(client);
		System.out.println("Novo cliente registrado com sucesso! Total: "+clients.size());
	}	

	@Override
	public String listGroups() throws RemoteException {
		return groupManager.listGroups();
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
