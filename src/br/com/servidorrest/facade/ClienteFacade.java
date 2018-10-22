package br.com.servidorrest.facade;

import java.sql.SQLException;
import java.util.List;

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
		}else {
			throw new RuntimeException("Todos os campos devem ser preenchidos");
		}
		return cliente;
	}

	private boolean validaCliente(Cliente cliente) {
		boolean status = true;
		if(cliente.getCpf().equals("")) {
			status = false;
		}else if(cliente.getNome().equals("")) {
			status = false;
		}else if(cliente.getSobrenome().equals("")) {
			status = false;
		}
		
		return status;
	}

	/*public Retorno editaCliente(Cliente cliente) {
		Retorno retorno = new Retorno();
		
		if(validaCliente(cliente)) {
			Retorno c = verificaCpfEditar(cliente);
			if(!c.getObjeto().equals("true")) {
				retorno.setErro(c.getErro());				
			}else {
				Object r = dao.editarCliente(cliente);
				if(r.getClass() == RuntimeException.class) {
					retorno.setErro(new ErroMessenger("Ocorreu um erro ao editar o registro.", (RuntimeException)r));
				}else {
					retorno.setObjeto(r);
				}
			}
		}
		
		return retorno;
	}*/
	
	public boolean verificaCpf(Cliente cliente) throws SQLException {
		
		Cliente c = dao.getCliente(cliente.getCpf());
		if(c != null) {
			if(c.getId() == cliente.getId()) {
				throw new RuntimeException("O CPF informado já existe.");
			}else {
				return true;
			}
		}
		return true;
	}

	/*public Retorno verificaCpfEditar(Cliente cliente) {
		Retorno retorno = new Retorno();
		retorno.setObjeto("");
		Object r = dao.getCliente(cliente.getCpf());
		if(r != null && r.getClass() == RuntimeException.class) {
			retorno.setErro(new ErroMessenger("Ocorreu um erro ao editar o cliente.", (RuntimeException)r));
		}else {
			if(((Cliente)r).getId() == cliente.getId()){
				retorno.setObjeto("true");				
			}else {
				retorno.setErro(new ErroMessenger("O CPF informado já existe.", (RuntimeException)r));				
			}
		}
		retorno.setObjeto("true");
		return retorno;
	}

	public Retorno excluirCliente(int codigo) {
		Retorno retorno = new Retorno();
		
		Object r = dao.deleteCliente(codigo);
		if(r != null) {
			if(r.getClass() == RuntimeException.class) {
				retorno.setErro(new ErroMessenger("Ocorreu um erro ao editar o cliente.", (RuntimeException)r));
			}else {
				retorno.setErro(new ErroMessenger("O cliente selecionado possui pedidos pendentes e não pode ser removido.", null));				
			}
		}else {
			retorno.setObjeto("true");
		}
		return retorno;
	}
	
	public Retorno getCliente(String cpf) {
		Retorno retorno = new Retorno();
		
		Object r = dao.getCliente(cpf);
		if(r != null) {
			if(r.getClass() == RuntimeException.class) {
				retorno.setErro(new ErroMessenger("Ocorreu um erro ao buscar o cliente", (RuntimeException)r));
			}else {
				retorno.setObjeto(r);
			}
		}else {
			retorno.setErro(new ErroMessenger("Cliente não encontrado.", null));			
		}
		return retorno;
	}*/
}
