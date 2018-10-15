package br.com.servidorrest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.servidorrest.model.Cliente;
import br.com.servidorrest.util.GerenciadorJDBC;

public class ClienteDAO {

	public List<Cliente> listClientes(){
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

		} catch (SQLException ex) {
			 ex.printStackTrace();
		} finally {
			GerenciadorJDBC.close(conn, stmt, rs);
		}
		return lista;
	}
}
