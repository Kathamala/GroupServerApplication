package br.imd.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.imd.rest.expections.RestRequestException;

public class Client {

	static Map<String, String> headerParams;
	static String username;
	
	public Client() {
		headerParams = new HashMap<String, String>();
		headerParams.put("accept", "application/json");
	}
	
	public void helloWorld() throws RestRequestException {

		String uri = "http://localhost:8080/RestServer/restapi/groupServer";
		Map<String, String> headerParams = new HashMap<String, String>();

		headerParams.put("accept", "application/json");

		String response = HttpUtils.httpGetRequest(uri, headerParams);

		System.out.println(response);
	}
	/*
	public void helloPeople() throws RestRequestException {

		String name = "Jorge";
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/hello-people/"+name;
		Map<String, String> headerParams = new HashMap<String, String>();

		headerParams.put("accept", "application/json");

		String response = HttpUtils.httpGetRequest(uri, headerParams);

		System.out.println(response);
	}
	
	public void helloPeople2() throws RestRequestException {

		String name = "Jorge";
		String sobrenome = "Silva";
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/hello-people2"
				+ "?nome="+name+"&sobrenome="+sobrenome;
		Map<String, String> headerParams = new HashMap<String, String>();

		headerParams.put("accept", "application/json");

		String response = HttpUtils.httpGetRequest(uri, headerParams);

		System.out.println(response);
	}
	
	
	public void helloPOST() throws RestRequestException {

		String uri = "http://localhost:8080/RestServer/restapi/groupServer";
		Map<String, String> headerParams = new HashMap<String, String>();

		headerParams.put("accept", "application/json");
		headerParams.put("content-type", "text/plain");
		
		String response = HttpUtils.httpPostRequest(uri, headerParams, "Jorge", 200);

		System.out.println(response);
	}
	*/
	

	public static void main(String[] args) throws RestRequestException {
		Client restClient = new Client();

		restClient.setUsername();
		
		int option = -1;
		Scanner sc = new Scanner(System.in);
		
		while(option != 0) {
			System.out.println("==================================");
			System.out.println("USER: " + username);
			System.out.println("0 - Leave Application.");
			System.out.println("1 - List Groups.");
			System.out.println("2 - Create Group.");
			System.out.println("3 - Join Group.");
			System.out.println("4 - Leave Group.");
			System.out.println("5 - Send Message to Group.");
			System.out.println("==================================");
			option = sc.nextInt();
			sc.nextLine();
			
			if(option == 1) {
				listGroups();
			}
			else if(option == 2) {
				String group_name;
				System.out.println("Inform the name of the new group: ");
				group_name = sc.nextLine();
				createGroup(group_name);
			}
			else if(option == 3) {
				String group_name;
				System.out.println("Inform the name of the group you'd like to join: ");
				group_name = sc.nextLine();
				joinGroup(group_name);
			}				
			else if(option == 4) {
				String group_name;
				System.out.println("Inform the name of the group you'd like to leave: ");
				group_name = sc.nextLine();					
				leaveGroup(group_name);
			}/*		
			else if(option == 5) {
				String group_name;
				String message;
				System.out.println("Inform the name of the group you'd like to send a message: ");
				group_name = sc.nextLine();
				System.out.println("Type the message: ");
				message = sc.nextLine();
				sendMessage(group_name, message);
			}		*/
		}		
	}
	
	public void setUsername() throws RestRequestException {
		Scanner sc = new Scanner(System.in);
		String _username = "";
		
		System.out.println("Please, inform your username: ");
		_username = sc.nextLine();
		
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/setUsernameIfValid/"+_username;
		String response = HttpUtils.httpGetRequest(uri, headerParams);
		
		System.out.println(response);
		
		while(response.startsWith("ERROR")) {
			_username = sc.nextLine();
			uri = "http://localhost:8080/RestServer/restapi/groupServer/setUsernameIfValid/"+_username;
			response = HttpUtils.httpGetRequest(uri, headerParams);
			
			System.out.println(response);
		};
		
		username = _username;
	}	
	
	public static void listGroups() throws RestRequestException {
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/listGroups";
		String response = HttpUtils.httpGetRequest(uri, headerParams);
		
		System.out.println(response);
	}
	
	public static void createGroup(String name) throws RestRequestException {	
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/createGroup/"+name;
		String response = HttpUtils.httpGetRequest(uri, headerParams);
		
		System.out.println(response);		
	}
	
	public static void joinGroup(String groupname) throws RestRequestException {		
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/joinGroup"
						+ "?groupname="+groupname+"&username="+username;
		String response = HttpUtils.httpGetRequest(uri, headerParams);
		
		System.out.println(response);
	}		
	
	public static void leaveGroup(String groupname) throws RestRequestException {		
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/leaveGroup"
				+ "?groupname="+groupname+"&username="+username;
		String response = HttpUtils.httpGetRequest(uri, headerParams);
		
		System.out.println(response);		
	}			
	
	/*
	public void sendMessage(String group_name, String message) throws RemoteException {
		System.out.println(server.sendMessage(group_name, username, message));
	}
	 */
}
