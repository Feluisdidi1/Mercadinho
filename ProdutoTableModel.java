package model.bean;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ProdutoTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// CRIADO UMA LISTA DADOS E UMA STRING DEFINIDO O NOME DAS COLUNAS
	private List<Produto> dados = new ArrayList<>();
	private String[] colunas = { "ID, Descrição", "QTD", "Preco" };

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colunas[column];
	}

	// METODO QUE CHAMA A QUANTIDADE
	@Override
	public int getRowCount() {
		return dados.size();
	}

	// METODO QUE CHAMA AS COLUNAS E COMPRIMENTO
	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	// PEGA A COLUNA E LINHA PARA RETORNAR UM VALOR
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return dados.get(rowIndex).getId();
		case 1:
			return dados.get(rowIndex).getDescricao();
		case 2:
			return dados.get(rowIndex).getQtd();
		case 3:
			return dados.get(rowIndex).getPreco();
		}
		return null;
	}

	@Override
	public void setValueAt(Object valor, int linha, int coluna) {
	
		switch (coluna) {
		 case 0:
			dados.get(linha).setId(Integer.parseInt((String) valor));
			break; 
		case 1:
			dados.get(linha).setDescricao((String) valor);
			break;
		 case 2:
			dados.get(linha).setQtd(Integer.parseInt((String) valor));
			break;
		 case 3:
			dados.get(linha).setPreco(Double.parseDouble((String) valor));
			break;
		}

		this.fireTableRowsUpdated(linha, linha);
	}

	// ADICIONA UMA LINHA A TABELA
	public void addRow(Produto p) {

		this.dados.add(p);
		this.fireTableDataChanged();

	}

	// REMOVE UMA LINHA DA TABELA
	public void removeRow(int linha) {
		this.dados.remove(linha);
		this.fireTableRowsDeleted(linha, linha);
	}

}
