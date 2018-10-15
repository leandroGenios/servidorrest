package br.com.servidorrest.resource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.servidorrest.dao.ClienteDAO;
import br.com.servidorrest.model.Cliente;
import br.com.servidorrest.util.Result;

@Path("/cliente")
public class ClienteResource {
	private ClienteDAO dao = new ClienteDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public Result listClientes(){
		Result result = new Result();
		try {
			result.setResultado(dao.listClientes());
		} catch (Exception e) {
			e.printStackTrace();
			result.setErro("Ocorreu um erro ao buscar a lista de clientes.\nTente novamente mais tarte.");
		}
		return result;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/set")
	public Result setCliente(@Valid@NotNull(message="teste")@HeaderParam("cliente")Cliente cliente){
		Result result = new Result();
		try {
			result.setResultado(dao.setCliente(cliente));
		} catch (Exception e) {
			e.printStackTrace();
			result.setErro("Ocorreu um erro ao salvar o cliente.\nTente novamente mais tarte.");
		}
		return result;
	}
}
