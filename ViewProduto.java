package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.bean.Produto;
import model.dao.ProdutoDAO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ViewProduto {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProduto window = new ViewProduto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewProduto() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("PRODUTOS");
		frame.setLocationRelativeTo(null);
		
		final JComboBox<Produto> comboProduto = new JComboBox<Produto>();
		comboProduto.setModel(new DefaultComboBoxModel<Produto>());
		comboProduto.setBounds(81, 59, 258, 22);
		frame.getContentPane().add(comboProduto);
		
		JButton btnPegar = new JButton("Pegar");
		btnPegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Produto produto = (Produto) comboProduto.getSelectedItem();
			JOptionPane.showMessageDialog(null, "ID: "+produto.getId()+" - "+ "DESCRIÇÃO: "+produto.getDescricao()+" - "+ "QUANTIDADE: "+produto.getQtd()+" - "+ "PREÇO: "+produto.getPreco());
			}
		});
		btnPegar.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(btnPegar);
		
		final JLabel aImage = new JLabel("");
		aImage.setForeground(new Color(0, 0, 0));
		aImage.setBounds(81, 92, 258, 124);
		frame.getContentPane().add(aImage);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon i = new ImageIcon("src/Imagens/MONEY_MONEY2_copiar.JPEG.png");
				aImage.setIcon(i);
			}
		});
		btnMostrar.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnMostrar);
		
		JButton btnMostrartudo = new JButton("MostrarTudo");
		btnMostrartudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon i = new ImageIcon("src/Imagens/MONEY_MONEY2_copiar.JPEG.png");
				i.setImage(i.getImage().getScaledInstance(aImage.getWidth(), aImage.getHeight(), 1));
				aImage.setIcon(i);
			}
		});
		btnMostrartudo.setBounds(105, 227, 105, 23);
		frame.getContentPane().add(btnMostrartudo);
		
ProdutoDAO dao = new ProdutoDAO();
		
		for(Produto p: dao.ler()) {
			
			comboProduto.addItem(p);
		}
		
	}


	}

