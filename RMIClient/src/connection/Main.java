package connection;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		ServerInterface server = (ServerInterface) 
				Naming.lookup("rmi://127.0.0.1:3005/HelloServerCallbak");		
		

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please, inform your username: ");
		String username = sc.nextLine();
		
		ClientInterface client = new Client(server);
	
		client.getUser().setName(username);
		
		server.registerClient(client, username);
	}

}
