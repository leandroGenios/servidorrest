package br.com.servidorrest.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.servidorrest.facade.ClienteFacade;
import br.com.servidorrest.model.Cliente;

@Path("/clientes")
public class ClienteResource {
	ClienteFacade facade = new ClienteFacade();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	public Response listClientes(){
		List<Cliente> clientes;
		try {
			clientes = facade.listClientes();

			@SuppressWarnings("unused")
			GenericEntity<List<Cliente>> lista = new GenericEntity<List<Cliente>>(clientes) {};
			
			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(clientes)
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.entity(e.getErrorCode() + "-" + e.getMessage())
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCliente(@PathParam("id") int id){
		Cliente cliente;
		try {
			cliente = facade.getCliente(id);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(cliente)
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.entity(e.getErrorCode() + "-" + e.getMessage())
					.build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	public Response setCliente(Cliente cliente){
		try {
			cliente = facade.setCliente(cliente);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(cliente)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.entity(new Gson().toJson(e.getMessage()))
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	public Response updateCliente(Cliente cliente){
		try {
			cliente = facade.updateCliente(cliente);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(cliente)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.entity(new Gson().toJson(e.getMessage()))
					.build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response deleteCliente(@PathParam("id") int codigo){
		try {
			facade.deleteCliente(codigo);
			
			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			
			return Response
					.status(Response.Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.entity(e.getErrorCode() + "-" + e.getMessage())
					.build();
		}
	}
}
