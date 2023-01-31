package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.retornaConexao;
import model.bean.Colaborador;

public class ColaboradorDao {

	private Connection conexao;
	
	public void cadastrar(Colaborador colaborador) {
		PreparedStatement stmt = null;
		
		try {
			conexao = retornaConexao.obterconexao();
			String sql = "INSERT INTO TAB_COLABORADOR(ID, NOME, CPF, ENDERECO, TELEFONE) VALUES (SEQ_ID.NEXTVAL, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, colaborador.getNome());
			stmt.setString(2, colaborador.getCPF());
            stmt.setString(4, colaborador.getTelefone());
            stmt.setString(3, colaborador.getEndereco());
            
            stmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
			stmt.close();
			conexao.close();
		}catch (SQLException e) {
		 	e.printStackTrace();
		}
	}
}
}

