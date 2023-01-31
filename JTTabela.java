package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.table.TableRowSorter;

import model.bean.Produto;
import model.bean.ProdutoTableModel;
import model.dao.ProdutoDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JTTabela extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public JFrame frame;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |javax.swing.UnsupportedLookAndFeelException    ex) {
         ex.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JTTabela Nimbus = new JTTabela();
					Nimbus.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ProdutoTableModel tableModel = new ProdutoTableModel();

	/**
	 * Create the application.
	 */
	public JTTabela() {
		initialize();
		// ProdutoTableModel tableModel = new ProdutoTableModel();
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		table.setRowSorter(new TableRowSorter<DefaultTableModel>(modelo));
		frame.setTitle("TABELA DE PRODUTOS"); //COMANDO PARA DEFINIR UM TITULO 
		frame.setLocationRelativeTo(null); //COMANDO PARA LOCALIZAÇÃO AO EXECUTAR

		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setBounds(12, 90, 41, 28);
		frame.getContentPane().add(txtid);
		txtid.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(21, 58, 46, 14);
		frame.getContentPane().add(lblNewLabel);

		JButton btnCarregarDados = new JButton("Carregar Produto");
		btnCarregarDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (table.getSelectedRow() != -1) {
					carregarCampos();
					lerTabela();
				} else {
					JOptionPane.showMessageDialog(null, "Nessário selecionar o Produto!");
				}

				lerTabela();
			}
			
		});
		btnCarregarDados.setBounds(21, 194, 196, 25);
		frame.getContentPane().add(btnCarregarDados);

		JButton btnRefresh = new JButton("f5");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lerTabela();
				limpar();
			}
		});
		btnRefresh.setBounds(547, 23, 55, 23);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//BOTÃO QUE REALIZA A PESQUISA PELA DESCRIÇÃO
				buscarPorDesc(txtbuscarDesc.getText());
			}
		});
		btnBuscar.setBounds(500, 140, 100, 28);
		frame.getContentPane().add(btnBuscar);
		
		txtbuscarDesc = new JTextField();
		txtbuscarDesc.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

					buscarPorDesc(txtbuscarDesc.getText());
					
				}

			}
		});
		txtbuscarDesc.setBounds(335, 140, 164, 28);
		frame.getContentPane().add(txtbuscarDesc);
		txtbuscarDesc.setColumns(10);

		lerTabela();
	}

	// METODOS
	// METODO PARA LER A TABELA
	public void lerTabela() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		ProdutoDAO pdao = new ProdutoDAO();
		modelo.setNumRows(0);

		for (Produto p : pdao.ler()) {
			modelo.addRow(new Object[] { p.getId(), p.getDescricao(), p.getQtd(), p.getPreco()

			});

		}
	}

	
	public void limpar() {
		txtdesc.setText("");
		txtqtd.setText("");
		txtpreco.setText("");
		txtid.setText("");
		txtdesc.requestFocus();
		txtbuscarDesc.setText("");
	}

	public void carregarCampos() {

		int setar = table.getSelectedRow();

		txtid.setText(table.getModel().getValueAt(setar, 0).toString());
		txtdesc.setText(table.getModel().getValueAt(setar, 1).toString());
		txtqtd.setText(table.getModel().getValueAt(setar, 2).toString());
		txtpreco.setText(table.getModel().getValueAt(setar, 3).toString());
	}

	public void cadastrar() {

//		if(txtdesc.getText(table.setText(), 1).equals("")) {
//			JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR, FALTA DADOS!");
//		}else {
		Produto p = new Produto();
		ProdutoDAO dao = new ProdutoDAO();
		p.setDescricao(txtdesc.getText());
		p.setQtd(Integer.parseInt(txtqtd.getText()));
		p.setPreco(Double.parseDouble(txtpreco.getText()));

		tableModel.addRow(p);
		try {
			dao.create(p);
			limpar();
			lerTabela();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR " + e1);
		} finally {
		}
	}
//	}

	public void atualizarDados() {
		int id;

		Produto p = new Produto(); // CHAMO O PRODUTO DA BEAN
		ProdutoDAO dao = new ProdutoDAO(); // CHAMO O PRODUTO DAO DA DAO

		if (table.getSelectedRow() != -1) {
			id = (Integer.parseInt(txtid.getText()));
			p.setId(id);
			p.setDescricao(txtdesc.getText());
			p.setQtd(Integer.parseInt(txtqtd.getText()));
			p.setPreco(Double.parseDouble(txtpreco.getText()));

			// tableModel.addRow(p);
			// if(table.getSelectedRow() != -1) {
			try {
				dao.atualizar(p);
				limpar();
			} finally {
				lerTabela();
			}
		}

		else {
			JOptionPane.showMessageDialog(null, "nenhum Produto Selecionado");
		}

		lerTabela();

	}

	public void deletar() {
		int id;

		Produto p = new Produto(); // CHAMO O PRODUTO DA BEAN
		ProdutoDAO dao = new ProdutoDAO(); // CHAMO O PRODUTO DAO DA DAO

		id = (Integer.parseInt(txtid.getText()));
		p.setId(id);
		p.setDescricao(txtdesc.getText());
		p.setQtd(Integer.parseInt(txtqtd.getText()));
		p.setPreco(Double.parseDouble(txtpreco.getText()));

		// tableModel.addRow(p);
		try {
			dao.deletar(p);
			limpar();
		} finally {
			lerTabela();
		}

	}

	public Boolean CadastroVazio(String s) {
		return s == null || s.isEmpty();
	}
	
	public void buscarPorDesc(String desc) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		ProdutoDAO pdao = new ProdutoDAO();
		modelo.setNumRows(0);

		for (Produto p : pdao.buscarDesc(desc)) {
			modelo.addRow(new Object[] { p.getId(), p.getDescricao(), p.getQtd(), p.getPreco()

			});

		}
		
	}
	
	
	private JTextField txtdesc;
	private JTextField txtqtd;
	private JTextField txtpreco;
	private JTable table;
	private JTextField txtid;
	private JTextField txtbuscarDesc;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 626, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel txtddd = new JLabel("Descrição");
		txtddd.setBounds(72, 51, 118, 28);
		frame.getContentPane().add(txtddd);

		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(298, 51, 118, 28);
		frame.getContentPane().add(lblQuantidade);

		JLabel lblPreo = new JLabel("Preço");
		lblPreo.setBounds(457, 51, 118, 28);
		frame.getContentPane().add(lblPreo);

		txtdesc = new JTextField();
		txtdesc.setBounds(63, 90, 225, 28);
		frame.getContentPane().add(txtdesc);
		txtdesc.setColumns(10);

		txtqtd = new JTextField();
		txtqtd.setColumns(10);
		txtqtd.setBounds(298, 90, 146, 28);
		frame.getContentPane().add(txtqtd);

		txtpreco = new JTextField();
		txtpreco.setColumns(10);
		txtpreco.setBounds(454, 90, 146, 28);
		frame.getContentPane().add(txtpreco);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				if(CadastroVazio(txtdesc.getText()) == true) {
					JOptionPane.showMessageDialog(null, "FALTA A DESCRIÇÃO PARA REALIZAR O CADASTRO ! ");
				}if(CadastroVazio(txtqtd.getText()) == true) {
					JOptionPane.showMessageDialog(null, "FALTA A QUANTIDADE PARA REALIZAR O CADASTRO ! ");
				}if(CadastroVazio(txtpreco.getText()) == true) {
					JOptionPane.showMessageDialog(null, "FALTA O PREÇO PARA REALIZAR O CADASTRO ! ");
				}else {
					cadastrar(); 
				}
					
			}

		});
		btnCadastrar.setBounds(21, 140, 205, 28);
		frame.getContentPane().add(btnCadastrar);

		JButton btnAtualizar = new JButton("Alterar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
					atualizarDados();
					lerTabela();
					limpar();
				} else {
					JOptionPane.showMessageDialog(null, "Nessário carregar Produto!");
				}

				lerTabela();
			}
		});
		btnAtualizar.setBounds(255, 194, 146, 25);
		frame.getContentPane().add(btnAtualizar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() != -1) {
			//		JOptionPane.showMessageDialog(null, "Nessário Carregar os dados!");
					JOptionPane.showMessageDialog(null, "Produto excluido");
					lerTabela();
					deletar();
				//	limpar();

				} else {
					JOptionPane.showMessageDialog(null, "Nenhum Produto Selecionado! ");
					lerTabela();
				}

			}
		});
		btnExcluir.setBounds(422, 194, 146, 25);
		frame.getContentPane().add(btnExcluir);

		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				/*
				 * EVENTO QUE DIRECIONA A TABELA (CIMA OU BAIXO) DE ACORDO COM AS SETAS DO
				 * TECLADO
				 */
				carregarCampos();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
				carregarCampos();
				}else { 
					lerTabela();
				}
				// METODO QUE CARREGA E EXIBE AS INFORMAÇÕES COM BASE NO CLIQUE DO MOUSE
			}
		});
		/*
		 * table.setEditingColumn(false); CODIGO QUE NÃO FUNCIONOU NA TENTATIVA DE //
		 * DESATIVAR A OPÇÃO EDITAVÉL // table.setEditingRow(false); CODIGO QUE NÃO
		 * FUNCIONOU NA TENTATIVA DE // DESATIVAR A OPÇÃO EDITAVÉL //
		 * table.setEditable(false); CODIGO QUE NÃO FUNCIONOU NA TENTATIVA DE DESATIVAR
		 * A OPÇÃO EDITAVÉL
		 */
		table.setFocusable(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "DESCRICAO", "QTD", "PRECO" }));
		table.setBounds(21, 230, 568, 174);
		frame.getContentPane().add(table);
	}
}
