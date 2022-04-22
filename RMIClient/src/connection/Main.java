package connection;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		ServerInterface server = (ServerInterface) 
				Naming.lookup("rmi://127.0.0.1:3005/HelloServerCallbak");

		ClientInterface client = new Client(server);
		server.registerClient(client);
	}

}