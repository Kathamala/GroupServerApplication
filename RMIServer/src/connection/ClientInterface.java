package connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Message;
import model.User;

public interface ClientInterface extends Remote {
	
	public void printMessage(Message message) throws RemoteException;
	
	public User getUser() throws RemoteException;

}
