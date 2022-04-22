package connection;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		
		System.setProperty("java.rmi.server.hostname","127.0.0.1");
		
		ServerInterface server = new Server();

		LocateRegistry.createRegistry(3005);
		
		Naming.rebind("rmi://127.0.0.1:3005/HelloServerCallbak", server);
		
		System.out.println("RMI Callback Server Starterd.");
		
	}

}
