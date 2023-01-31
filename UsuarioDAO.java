package model.dao;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.retornaConexao;
import model.bean.Usuario;

public class UsuarioDAO {
	
	PreparedStatement stmt = null;
	Connection conexao = null;
	ResultSet rs = null;
	boolean check = false;

	public boolean checkLogin(String login, String senha) {
		// CRIA UM MÉTODO DE CHECKLOGIN


		try {
			conexao = retornaConexao.obterconexao();
			stmt = conexao.prepareStatement("SELECT * FROM USUARIO WHERE LOGIN = ? AND SENHA = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			//GUARDA OS RESULTADOS DO EXCECUTEQUEERY NA VARIAVEL RS
			rs = stmt.executeQuery();

			// PERCORRE TODOS OS REGISTROS ENCONTRADOS
			if (rs.next()) {
				
				check = true;
				
			}
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO listar" + erro);
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return check;
	}
}
