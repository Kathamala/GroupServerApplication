package connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Message;

public interface ClientInterface extends Remote {

	//@ requires message != null;
	//@ requires message.getMessage() != null && message.getMessage().length() >= 2;
	//@ requires message.getSender() != null && message.getSender().length() >= 2;
	//@ requires message.getGroup() != null && message.getGroup().length() >= 2;
	//@ ensures true;
	//@ signals_only RemoteException;
	//@ signals (RemoteException) false;
	public void printMessage(Message message) throws RemoteException;

	//@ requires true;
	//@ ensures true;
	//@ signals_only RemoteException;
	public void startApplication() throws RemoteException;

	//@ requires username != null && username.length() >= 2;
	//@ ensures true;
	//@ signals_only RemoteException;
	public void setUsername(String username) throws RemoteException;
}
