package br.com.servidorrest.facade;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import br.com.servidorrest.dao.ClienteDAO;
import br.com.servidorrest.model.Cliente;

public class ClienteFacade { 
	private ClienteDAO dao = new ClienteDAO();

	public List<Cliente> listClientes() throws SQLException {
		return dao.listClientes();
	}
	
	public Cliente getCliente(int id) throws SQLException{
			return dao.getCliente(id);
	}

	public Cliente setCliente(Cliente cliente) throws Exception {
		if(validaCliente(cliente)) {
			verificaCpf(cliente);
			dao.setCliente(cliente);
		}else {
			throw new WebApplicationException("Todos os campos devem ser preenchidos");
		}
		return cliente;
	}

	private boolean validaCliente(Cliente cliente) {
		boolean status = true;
		if(cliente.getCpf() == null || cliente.getCpf().equals("")) {
			status = false;
		}else if(cliente.getNome() == null || cliente.getNome().equals("")) {
			status = false;
		}else if(cliente.getSobrenome() == null || cliente.getSobrenome().equals("")) {
			status = false;
		}
		
		return status;
	}

	public Cliente updateCliente(Cliente cliente) throws Exception {
		if(validaCliente(cliente)) {
			verificaCpf(cliente);
			cliente = dao.updateCliente(cliente);
		}else{
			throw new WebApplicationException("Todos os campos devem ser preenchidos");
		}
		
		return cliente;
	}
	
	public boolean verificaCpf(Cliente cliente) throws SQLException {
		
		Cliente c = dao.getCliente(cliente.getCpf());
		if(c != null) {
			if(c.getId() != cliente.getId()) {
				throw new WebApplicationException("O CPF informado já existe.");
			}else {
				return true;
			}
		}
		return true;
	}

	public void deleteCliente(int codigo) throws SQLException {
		dao.deleteCliente(codigo);
	}
}
