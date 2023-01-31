package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
//import java.util.Calendar;
import java.util.List;
//import java.util.logging.Logger;

import javax.swing.JOptionPane;

import connection.retornaConexao;
import model.bean.Produto;

public class ProdutoDAO {

	PreparedStatement stmt = null;
	Connection conexao = null;
	ResultSet rs = null;
	
	public void create(Produto p) throws SQLException {
		

		try {
			conexao = retornaConexao.obterconexao();
			stmt = conexao
					.prepareStatement("INSERT INTO MERCADINHO (ID,DESCRICAO,QTD,PRECO) VALUES (SEQ_ID.NEXTVAL,?,?,?)");
			stmt.setString(1, p.getDescricao());
			stmt.setInt(2, p.getQtd());
			stmt.setDouble(3, p.getPreco());

			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO NO ProdutoDAO create " + erro);
		} finally {
			conexao.close();
		}

	}

	public List<Produto> ler() {
		// CRIA UMA LISTA DE COLABORADORES

		List<Produto> lista = new ArrayList<>();

		try {
			conexao = retornaConexao.obterconexao();
			stmt = conexao.prepareStatement("SELECT * FROM MERCADINHO");
			//GUARDA OS RESULTADOS DO EXCECUTEQUEERY NA VARIAVEL RS
			rs = stmt.executeQuery();

			// PERCORRE TODOS OS REGISTROS ENCONTRADOS
			while (rs.next()) {
				
				Produto produto = new Produto();
				
				produto.setId(rs.getInt("ID"));
				produto.setDescricao(rs.getString("DESCRICAO"));
				produto.setQtd(rs.getInt("QTD"));
				produto.setPreco(rs.getDouble("PRECO"));
				// java.sql.Date data = rs.getDate("DATA_CONTRATACAO");
				// Calendar dataContratacao = Calendar.getInstance();
				// dataContratacao.setTimeInMillis(data.getTime());

				// CRIA UM OBJETO COLABORADOR COM AS INFORMALÇOES ENCONTRADAS
				//Produto produto = new Produto(ide,desc,qtde,prec);
				// ADICIONA O COLABORADOR NA LISTA
				lista.add(produto);
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
		return lista;
	}

	public void atualizar(Produto produto) {
		
		try {
			conexao = retornaConexao.obterconexao();
			String sql = "UPDATE MERCADINHO SET DESCRICAO = ?, QTD = ?, PRECO = ? WHERE ID = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getDescricao());
			stmt.setInt(2, produto.getQtd());
			stmt.setDouble(3, produto.getPreco());
			stmt.setInt(4, produto.getId());

			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "ATUALIZADO COM SUCESSO !");
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ERRO NA CLASSE PRODUTODAO atualizar " + erro);
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "ERRO AO FECHAR CONEXAO !"+ e);
			}
		}
	}

	public void deletar(Produto produto) {

		try {

			conexao = retornaConexao.obterconexao();
			String sql = "DELETE FROM MERCADINHO WHERE ID = ?";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, produto.getId());
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ProdutoDAO remover " +e);
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException erro) {
				JOptionPane.showMessageDialog(null, "ProdutoDAO remover " + erro);
			}
		}
		
	}

	public List<Produto> buscarDesc(String desc) {
		
		
		List<Produto> produtos = new ArrayList<>();
		try {
		  conexao = retornaConexao.obterconexao();
		  stmt = conexao.prepareStatement("SELECT * FROM MERCADINHO WHERE DESCRICAO LIKE ?");
		  stmt.setString(1, desc+"%");
		  rs = stmt.executeQuery();

	    while (rs.next()){
	    	Produto produto = new Produto();
	    	
		    produto.setId(rs.getInt("ID"));
		    produto.setDescricao(rs.getString("DESCRICAO"));
		    produto.setQtd(rs.getInt("QTD"));
		    produto.setPreco(rs.getDouble("PRECO"));
		   // java.sql.Date data = rs.getDate("DATA_CONTRATACAO");
		   // Calendar dataContratacao = Calendar.getInstance();
		   // dataContratacao.setTimeInMillis(data.getTime());
		    produtos.add(produto);
		  }
		  
		} catch (SQLException erro) {
		  JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR POR DESCRICAO "+ erro);
		}finally {
		  try {
		    stmt.close();
		    rs.close();
		    conexao.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		}
		return produtos;
	}}

