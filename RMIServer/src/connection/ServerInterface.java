package connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{

	public boolean registerClient(ClientInterface client, String username) throws RemoteException;
	
	public String listGroups() throws RemoteException;
	
	public String createGroup(String name) throws RemoteException;
	
	public String joinGroup(String group_name, String user_name) throws RemoteException;
	
	public String leaveGroup(String name, String user_name) throws RemoteException;
	
	public String sendMessage(String destiny_group, String sender_user, String message) throws RemoteException;
}
