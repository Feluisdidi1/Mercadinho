package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JLabel;


import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewHome extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    UIManager.setLookAndFeel( new FlatDarkLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHome frame = new ViewHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
//		contentPane.setBackground(Color.BLACK);
		contentPane.setBackground(new Color(150,120,220));

		JLabel lblNewLabel = new JLabel("DOMBEZ inc");
		lblNewLabel.setBounds(348, 6, 193, 63);
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);
		
		JButton btnTabela = new JButton("Tabela Produtos");
		btnTabela.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				 
			 JTTabela tela = new JTTabela();
			 tela.frame.setVisible(true);
			    
			  contentPane.disable();
			}});
		btnTabela.setBounds(39, 480, 131, 25);
		contentPane.add(btnTabela);
		
		JButton btnNewButton = new JButton("Cadastro Funcionário");
		btnNewButton.setBounds(241, 481, 169, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblimage = new JLabel("");
		lblimage.setBounds(358, 81, 314, 256);
		contentPane.add(lblimage);
		ImageIcon i = new ImageIcon("src/Imagens/JAKE LAKE (2).png");
		i.setImage(i.getImage().getScaledInstance(lblimage.getWidth(), lblimage.getHeight(), 1));
		lblimage.setIcon(i);
	}
}
