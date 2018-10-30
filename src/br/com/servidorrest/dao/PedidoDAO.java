package br.com.servidorrest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.servidorrest.model.Cliente;
import br.com.servidorrest.model.ItemDoPedido;
import br.com.servidorrest.model.Pedido;
import br.com.servidorrest.util.GerenciadorJDBC;

public class PedidoDAO {

	public List<Pedido> listPedidos() throws SQLException{
		List<Pedido> lista = new ArrayList<Pedido>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = GerenciadorJDBC.getConnection();

			String sql = "SELECT P.id ID_PEDIDO"
						+"		,P.data"
						+ "		,C.id ID_CLIENTE"
						+ "		,C.cpf"
						+ "		,C.nome"
						+ "		,C.sobrenome"
						+ " FROM PEDIDO P "
						+ "INNER JOIN CLIENTE C "
						+ "	  ON C.id = P.id";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Pedido pedido = new Pedido(rs.getInt("ID_PEDIDO")
										  ,rs.getDate("data")
										  ,new Cliente(rs.getInt("ID_CLIENTE")
												  	  ,rs.getString("cpf")
												  	  ,rs.getString("nome")
												  	  ,rs.getString("sobrenome"))
										  , null);
				lista.add(pedido);
			}

		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return lista;
	}

	public List<Pedido> listPedidos(String cpf) throws SQLException{
		List<Pedido> lista = new ArrayList<Pedido>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "SELECT P.id ID_PEDIDO"
					+"		,P.data"
					+ "		,C.id ID_CLIENTE"
					+ "		,C.cpf"
					+ "		,C.nome"
					+ "		,C.sobrenome"
					+ " FROM PEDIDO P "
					+ "INNER JOIN CLIENTE C "
					+ "	  ON C.id = P.id"
					+ "WHERE C.CPF = ?";
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Pedido pedido = new Pedido(rs.getInt("ID_PEDIDO")
						,rs.getDate("data")
						,new Cliente(rs.getInt("ID_CLIENTE")
								,rs.getString("cpf")
								,rs.getString("nome")
								,rs.getString("sobrenome"))
						, null);
				lista.add(pedido);
			}
			
		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return lista;
	}
	
	public Pedido getPedido(int codigo) throws SQLException{
		Pedido pedido = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = GerenciadorJDBC.getConnection();

			String sql = "SELECT P.id ID_PEDIDO"
					+"		,P.data"
					+ "		,C.id ID_CLIENTE"
					+ "		,C.cpf"
					+ "		,C.nome"
					+ "		,C.sobrenome"
					+ " FROM PEDIDO P "
					+ "INNER JOIN CLIENTE C "
					+ "	  ON C.id = P.id"
					+ "WHERE P.ID = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, codigo);

			rs = stmt.executeQuery();

			while (rs.next()) {
				pedido = new Pedido(rs.getInt("ID_PEDIDO")
						,rs.getDate("data")
						,new Cliente(rs.getInt("ID_CLIENTE")
								,rs.getString("cpf")
								,rs.getString("nome")
								,rs.getString("sobrenome"))
						, null);
			}

		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return pedido;
	}
	
	public Pedido setPedido(Pedido pedido) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "INSERT INTO pedido VALUES (NULL, ?, NOW())";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, pedido.getCliente().getId());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			pedido.setId(rs.getInt(1));
			
			setItensPedido(pedido);
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
		return pedido;
	}
	
	public Pedido setItensPedido(Pedido pedido) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			for (ItemDoPedido item : pedido.getItens()) {
				String sql = "INSERT INTO item_do_pedido VALUES (?, ?, ?)";
				stmt = conn.prepareStatement(sql);
				
				stmt.setInt(1, pedido.getId());
				stmt.setInt(2, item.getProduto().getId());
				stmt.setInt(3, item.getQuantidade());
				
				stmt.executeUpdate();				
			}
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
		return pedido;
	}
}
