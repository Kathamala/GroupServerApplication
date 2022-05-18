package br.imd.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.imd.rest.expections.RestRequestException;

public class Client {

	Map<String, String> headerParams;
	
	public Client() {
		// TODO Auto-generated constructor stub
		headerParams = new HashMap<String, String>();
		headerParams.put("accept", "application/json");
	}
	
	public void setUsername() throws RestRequestException {
		Scanner sc = new Scanner(System.in);
		String username = "";
		
		System.out.println("Please, inform your username: ");
		username = sc.nextLine();
		
		String uri = "http://localhost:8080/RestServer/restapi/groupServer/setUsernameIfValid/"+username;
		String response = HttpUtils.httpGetRequest(uri, headerParams);
		
		System.out.println(response);
		
		while(response.startsWith("ERROR")) {
			username = sc.nextLine();
			uri = "http://localhost:8080/RestServer/restapi/groupServer/setUsernameIfValid/"+username;
			response = HttpUtils.httpGetRequest(uri, headerParams);
			
			System.out.println(response);
		};
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
	}

}
