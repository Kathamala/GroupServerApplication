package connection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import model.Message;



public class Client extends UnicastRemoteObject implements ClientInterface {

	private static final long serialVersionUID = 1L;

	protected Client() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printMessage(Message message) throws RemoteException {
		
		System.out.println(message);
		
	}
}
