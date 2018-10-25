package br.com.servidorrest.facade;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import br.com.servidorrest.dao.ProdutoDAO;
import br.com.servidorrest.model.Produto;

public class ProdutoFacade {
	private ProdutoDAO dao = new ProdutoDAO();

	public List<Produto> listProdutos() throws SQLException {
		return dao.listProdutos();
	}
	
	public Produto getProduto(int id) throws SQLException{
			return dao.getProduto(id);
	}

	public Produto setProduto(Produto Produto) throws Exception {
		if(validaProduto(Produto)) {
			dao.setProduto(Produto);
		}else {
			throw new WebApplicationException("Todos os campos devem ser preenchidos");
		}
		return Produto;
	}

	private boolean validaProduto(Produto Produto) {
		boolean status = true;
		if(Produto.getDescricao() == null || Produto.getDescricao().equals(""))
			status = false;
		
		return status;
	}

	public Produto updateProduto(Produto Produto) throws Exception {
		if(validaProduto(Produto)) {
			Produto = dao.updateProduto(Produto);
		}else{
			throw new WebApplicationException("Todos os campos devem ser preenchidos");
		}
		
		return Produto;
	}

	public void deleteProduto(int codigo) throws SQLException {
		dao.deleteProduto(codigo);
	}
}
