package connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import model.Message;
import model.User;



public class Client extends UnicastRemoteObject implements ClientInterface {

	private static final long serialVersionUID = 1L;
	private ServerInterface server;
	private User user = new User();
	
	protected Client(ServerInterface _server) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		server = _server;
		new Application().start();
	}

	@Override
	public void printMessage(Message message) throws RemoteException {
		System.out.println(message);
	}	
	
	@Override
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private class Application extends Thread{
		
		public void run() {
			
			int option = -1;
			Scanner sc = new Scanner(System.in);
			
			while(option != 0) {
				System.out.println("==========================");
				System.out.println("0 - Leave Application.");
				System.out.println("1 - List Groups.");
				System.out.println("2 - Create Group.");
				System.out.println("3 - Join Group.");
				System.out.println("==========================");
				option = sc.nextInt();
				sc.nextLine();
				
				if(option == 1) {
					try {
						listGroups();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				else if(option == 2) {
					String group_name;
					System.out.println("Inform the name of the new group: ");
					group_name = sc.nextLine();
					try {
						createGroup(group_name);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				else if(option == 3) {
					String group_name;
					System.out.println("Inform the name of the group you'd like to join: ");
					group_name = sc.nextLine();
					try {
						joinGroup(group_name);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}				
			}
		}
		
		public void listGroups() throws RemoteException {
			System.out.println(server.listGroups());
		}
		
		public void createGroup(String name) throws RemoteException {
			System.out.println(server.createGroup(name));
		}
		
		public void joinGroup(String name) throws RemoteException {
			System.out.println(server.joinGroup(name, user.getName()));
		}		
	}	
}






