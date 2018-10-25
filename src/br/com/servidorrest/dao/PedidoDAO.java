package br.com.servidorrest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.com.servidorrest.model.Cliente;
import br.com.servidorrest.util.GerenciadorJDBC;

public class PedidoDAO {

	public List<Cliente> listClientes() throws SQLException{
		List<Cliente> lista = new ArrayList<Cliente>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = GerenciadorJDBC.getConnection();

			String sql = "SELECT * FROM CLIENTE";
			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id"),
											  rs.getString("cpf"),
											  rs.getString("nome"),
											  rs.getString("sobrenome"));
				lista.add(cliente);
			}

		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return lista;
	}
	
	public Cliente getCliente(int codigo) throws SQLException{
		Cliente cliente = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = GerenciadorJDBC.getConnection();

			String sql = "SELECT * FROM CLIENTE WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, codigo);

			rs = stmt.executeQuery();

			while (rs.next()) {
				cliente = new Cliente(rs.getInt("id"),
									  rs.getString("cpf"),
									  rs.getString("nome"),
									  rs.getString("sobrenome"));
			}

		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return cliente;
	}
	
	public Cliente setCliente(Cliente cliente) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "INSERT INTO cliente VALUES (NULL, ?, ?, ?)";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getSobrenome());
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			cliente.setId(rs.getInt(1));
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
		return cliente;
	}
	
	public Cliente getCliente(String cpf) throws SQLException{
		Cliente cliente = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "SELECT * FROM CLIENTE WHERE cpf = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cpf);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				cliente = new Cliente(rs.getInt("id"),
						rs.getString("cpf"),
						rs.getString("nome"),
						rs.getString("sobrenome"));
			}
		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return cliente;
	}
	
	public Cliente updateCliente(Cliente cliente) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "UPDATE CLIENTE SET CPF = ?, NOME = ?, SOBRENOME = ? WHERE ID = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, cliente.getCpf());
			stmt.setString(2, cliente.getNome());
			stmt.setString(3, cliente.getSobrenome());
			stmt.setInt(4, cliente.getId());
			
			stmt.executeUpdate();
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
		return cliente;
	}
	
	public void deleteCliente(int codigo) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = GerenciadorJDBC.getConnection();
			
			String sql = "DELETE FROM CLIENTE WHERE ID = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, codigo);
			
			stmt.executeUpdate();
		}
		finally {
			GerenciadorJDBC.close(conn, stmt);
		}
	}
}
