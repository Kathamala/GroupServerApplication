package connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import model.Message;



public class Client extends UnicastRemoteObject implements ClientInterface {

	private static final long serialVersionUID = 1L;
	private ServerInterface server;
	
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
	
	private class Application extends Thread{
		
		public void run() {
			
			int option = -1;
			Scanner sc = new Scanner(System.in);
			
			while(option != 0) {
				System.out.println("==========================");
				System.out.println("0 - Leave Application.");
				System.out.println("1 - List Groups.");
				System.out.println("==========================");
				option = sc.nextInt();
				
				if(option == 1) {
					try {
						listGroups();
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public void listGroups() throws RemoteException {
			System.out.println(server.listGroups());
		}
	}	

}
