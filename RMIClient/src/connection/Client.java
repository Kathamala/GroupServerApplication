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
				System.out.println("4 - Leave Group.");
				System.out.println("5 - Send Message to Group.");
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
				else if(option == 4) {
					String group_name;
					System.out.println("Inform the name of the group you'd like to leave: ");
					group_name = sc.nextLine();					
					try {
						leaveGroup(group_name);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}		
				else if(option == 5) {
					String group_name;
					String message;
					System.out.println("Inform the name of the group you'd like to send a message: ");
					group_name = sc.nextLine();
					System.out.println("Type the message: ");
					message = sc.nextLine();
					try {
						sendMessage(group_name, message);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}					
			}
			
			System.exit(0);
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
		
		public void leaveGroup(String name) throws RemoteException {
			System.out.println(server.leaveGroup(name, user.getName()));
		}			
		
		public void sendMessage(String group_name, String message) throws RemoteException {
			System.out.println(server.sendMessage(group_name, user.getName(), message));
		}				
	}	
}






