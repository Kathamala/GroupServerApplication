package connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.User;

public interface ServerInterface extends Remote{

	public void registerClient(ClientInterface client, String username) throws RemoteException;
	
	public String listGroups() throws RemoteException;
	
	public String createGroup(String name) throws RemoteException;
	
	public String joinGroup(String group_name, String user_name) throws RemoteException;
	
	public String leaveGroup(String user_name) throws RemoteException;
}
