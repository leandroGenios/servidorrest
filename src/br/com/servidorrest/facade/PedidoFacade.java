package br.com.servidorrest.facade;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import br.com.servidorrest.dao.PedidoDAO;
import br.com.servidorrest.model.ItemDoPedido;
import br.com.servidorrest.model.Pedido;

public class PedidoFacade { 
	private PedidoDAO dao = new PedidoDAO();

	public List<Pedido> listPedidos() throws SQLException {
		return dao.listPedidos();
	}

	public List<Pedido> listPedidos(String cpf) throws SQLException {
		return dao.listPedidos(cpf);
	}
	
	public Pedido getPedido(int id) throws SQLException{
			return dao.getPedido(id);
	}

	public Pedido setPedido(Pedido pedido) throws Exception {
		if(validaPedido(pedido)) {
			dao.setPedido(pedido);
		}else {
			throw new WebApplicationException("Todos os campos devem ser preenchidos");
		}
		return pedido;
	}

	private boolean validaPedido(Pedido pedido) {
		boolean status = true;
		if(pedido.getCliente() == null) {
			status = false;
		}else if(pedido.getItens() == null || pedido.getItens().size() < 1) {
			status = false;
		}else {
			for (ItemDoPedido itens : pedido.getItens()) {
				if(itens.getProduto() == null) {
					status = false;
				}else if(itens.getQuantidade() < 1) {
					status = false;
				}
			}
		}
		
		return status;
	}

}
