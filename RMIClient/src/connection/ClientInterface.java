package connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Message;

public interface ClientInterface extends Remote {

	//@ requires message != null;
	public void printMessage(Message message) throws RemoteException;

	public void startApplication() throws RemoteException;

	//@ requires username != null;
	public void setUsername(String username) throws RemoteException;
}
