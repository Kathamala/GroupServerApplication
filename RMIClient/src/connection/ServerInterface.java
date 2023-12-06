package connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

	//@ requires client != null;
	//@ requires username != null;
	//@ ensures \result == true || \result == false;
	//@ signals_only RemoteException;
	public boolean registerClient(ClientInterface client, String username) throws RemoteException;

	//@ requires true;
	//@ ensures \result != null;
	//@ signals_only RemoteException;
	public String listGroups() throws RemoteException;

	//@ requires name != null;
	//@ ensures \result != null;
	//@ signals_only RemoteException;
	public String createGroup(String name) throws RemoteException;

	//@ requires group_name != null;
	//@ requires user_name != null;
	//@ ensures \result != null;
	//@ signals_only RemoteException;
	public String joinGroup(String group_name, String user_name) throws RemoteException;

	//@ requires name != null;
	//@ requires user_name != null;
	//@ ensures \result != null;
	// @ signals_only RemoteException;
	public String leaveGroup(String name, String user_name) throws RemoteException;

	//@ requires destiny_group != null;
	//@ requires sender_user != null ;
	//@ requires message != null;
	//@ ensures \result != null;
	//@ signals_only RemoteException;
	public String sendMessage(String destiny_group, String sender_user, String message) throws RemoteException;
}
