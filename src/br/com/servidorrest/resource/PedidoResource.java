package br.com.servidorrest.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.servidorrest.facade.PedidoFacade;
import br.com.servidorrest.model.Pedido;

@Path("/pedidos")
public class PedidoResource {
	PedidoFacade facade = new PedidoFacade();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	public Response listPedidos(){
		List<Pedido> pedidos;
		try {
			pedidos = facade.listPedidos();

			@SuppressWarnings("unused")
			GenericEntity<List<Pedido>> lista = new GenericEntity<List<Pedido>>(pedidos) {};
			
			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(pedidos)
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
	public Response getPedido(@PathParam("id") int id){
		Pedido pedido;
		try {
			pedido = facade.getPedido(id);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(pedido)
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
	@Path("/cliente/{cpf}")
	public Response listPedidos(@PathParam("cpf") String cpf){
		List<Pedido> pedidos;
		try {
			pedidos = facade.listPedidos(cpf);

			@SuppressWarnings("unused")
			GenericEntity<List<Pedido>> lista = new GenericEntity<List<Pedido>>(pedidos) {};
			
			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(pedidos)
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
	public Response setPedido(Pedido pedido){
		try {
			pedido = facade.setPedido(pedido);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(pedido)
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
}
