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

import br.com.servidorrest.facade.ProdutoFacade;
import br.com.servidorrest.model.Produto;

@Path("/produtos")
public class ProdutoResource {
	ProdutoFacade facade = new ProdutoFacade();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("")
	public Response listProdutos(){
		List<Produto> produtos;
		try {
			produtos = facade.listProdutos();

			@SuppressWarnings("unused")
			GenericEntity<List<Produto>> lista = new GenericEntity<List<Produto>>(produtos) {};
			
			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(produtos)
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
	public Response getProduto(@PathParam("id") int id){
		Produto produto;
		try {
			produto = facade.getProduto(id);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(produto)
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
	public Response setProduto(Produto produto){
		try {
			produto = facade.setProduto(produto);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(produto)
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
	public Response updateProduto(Produto produto){
		try {
			produto = facade.updateProduto(produto);

			return Response
					.status(Response.Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.entity(produto)
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
	public Response deleteProduto(@PathParam("id") int codigo){
		try {
			facade.deleteProduto(codigo);
			
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
