package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connection.retornaConexao;
import model.bean.Produto;

public class teste {
		
	
	

	PreparedStatement stmt = null;
	Connection conexao = null;
	ResultSet rs = null;
public void atualizar(Produto produto) {
		
		try {
			conexao = retornaConexao.obterconexao();
			String sql = "UPDATE MERCADINHO SET DESCRICAO = ?, QTD = ?, PRECO = ? WHERE ID = 1";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(0, "OLAVO");
			stmt.setString(1, "444");
			stmt.setString(2, "999");
			//stmt.setString(1, produto.getDescricao());
			//stmt.setInt(2, produto.getQtd());
			//stmt.setDouble(3, produto.getPreco());
			//stmt.setInt(4, produto.getId());

			stmt.executeUpdate();
			
			System.out.println("ATUALIZADO");
			JOptionPane.showMessageDialog(null, "ATUALIZADO COM SUCESSO !");
		} catch (SQLException erro) {
			System.out.println("FALHA AO ATUALIZAR");
			JOptionPane.showMessageDialog(null, "ERRO NA CLASSE PRODUTODAO atualizar " + erro);
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
