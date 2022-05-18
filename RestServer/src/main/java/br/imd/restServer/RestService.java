package br.imd.restServer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.imd.model.GroupManager;
import br.imd.model.HelloWorld;
import br.imd.model.User;

@Path("groupServer")
public class RestService {

	//private volatile List<ClientInterface> clients = new ArrayList<ClientInterface>();
	GroupManager groupManager = new GroupManager();
	
	public RestService() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("setUsernameIfValid/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setUsernameIfValid(@PathParam("username") String username) {
		if(groupManager.findUser(username) != null) {
			return Response.ok("ERROR: This username is already used. Inform a new username.").build();
		}
		
		//clients.add(client);
		User new_user = new User();
		//new_user.setId(clients.indexOf(client));
		new_user.setId(0);
		new_user.setName(username);
		
		groupManager.addUserWithoutGroup(new_user);
		
		System.out.println("Novo cliente registrado com sucesso!");		

		return Response.ok("Novo cliente registrado com sucesso!").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld() {

		HelloWorld helloWorld = new HelloWorld("Hello World !");

		return Response.ok(helloWorld).build();
	}
	
	
	@GET
	@Path("hello-people/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloPeople(@PathParam("nome") String nome) {

		HelloWorld helloWorld = new HelloWorld("Hello "+nome+" !");

		return Response.ok(helloWorld).build();
	}
	
	@GET
	@Path("hello-people2")
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloPeople2(@QueryParam("nome") String nome, 
			@QueryParam("sobrenome") String sobrenome) {

		HelloWorld helloWorld = new HelloWorld("Hello "+nome+" "+sobrenome+" !");

		return Response.ok(helloWorld).build();
	}
	
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloPost(String nome) {
		
		HelloWorld helloWorld = new HelloWorld("Hello "+nome+" !");

		return Response.ok(helloWorld).build();
	}
}
