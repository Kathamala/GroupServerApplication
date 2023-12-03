package connection;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {

	//@ signals_only RemoteException, MalformedURLException, NotBoundException;
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		ServerInterface server = (ServerInterface) 
				Naming.lookup("rmi://127.0.0.1:3005/HelloServerCallbak");		
		

		Scanner sc = new Scanner(System.in);
		String username = "";
		
		ClientInterface client = new Client(server);
		
		System.out.println("Please, inform your username: ");
		username = sc.nextLine();
		
		while(!server.registerClient(client, username)) {
			System.out.println("This username is already used. Inform a new username: ");
			username = sc.nextLine();
		};
		
		client.setUsername(username);
		client.startApplication();
	}
}
