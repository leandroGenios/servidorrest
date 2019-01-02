package br.com.servidorrest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.servidorrest.model.Produto;
import br.com.servidorrest.util.GerenciadorJDBC;

public class ProdutoDAO {

	public List<Produto> listProdutos() throws SQLException{
		List<Produto> lista = new ArrayList<Produto>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = GerenciadorJDBC.getConnection();

			String sql = "SELECT * FROM produto";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto(rs.getInt("id"),
											  rs.getString("descricao"));
				lista.add(produto);
			}

		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return lista;
	}
	
	public Produto getProduto(int codigo) throws SQLException{
		Produto produto = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = GerenciadorJDBC.getConnection();

			String sql = "SELECT * FROM produto WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, codigo);

			rs = stmt.executeQuery();

			while (rs.next()) {
				produto = new Produto(rs.getInt("id"),
									  rs.getString("descricao"));
			}

		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return produto;
	}
	
	public Produto setProduto(Produto produto) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "INSERT INTO produto VALUES (NULL, ?)";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, produto.getDescricao());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			produto.setId(rs.getInt(1));
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
		return produto;
	}

	public Produto updateProduto(Produto produto) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "UPDATE produto SET DESCRICAO = ? WHERE ID = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, produto.getDescricao());
			stmt.setInt(2, produto.getId());
			
			stmt.executeUpdate();
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
		return produto;
	}
	
	public void deleteProduto(int codigo) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "DELETE FROM produto WHERE ID = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.executeUpdate();
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
	}
}
