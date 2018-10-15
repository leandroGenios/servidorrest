package br.com.servidorrest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.servidorrest.dao.ClienteDAO;
import br.com.servidorrest.model.Cliente;

@Path("/cliente")
public class ClienteResource {
	private ClienteDAO dao = new ClienteDAO();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	public List<Cliente> listClientes(){
		return dao.listClientes();
	}
}
