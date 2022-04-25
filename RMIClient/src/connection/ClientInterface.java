package connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Message;

public interface ClientInterface extends Remote {
	
	public void printMessage(Message message) throws RemoteException;
	
	public void startApplication() throws RemoteException;
	
	public void setUsername(String username) throws RemoteException;
}
