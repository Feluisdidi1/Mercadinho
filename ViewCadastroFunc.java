package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

import com.formdev.flatlaf.FlatDarkLaf;

import model.bean.Colaborador;
import model.dao.ColaboradorDao;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class ViewCadastroFunc {

	private JFrame frame;
	private JFormattedTextField txtnome;
	private JFormattedTextField txtrua;
	private JFormattedTextField txtcpf;
	private JFormattedTextField txtTel;
	

	/**
	 * Launch the application.
	 */
	public void limpar() {
		txtnome.setText("");
		txtrua.setText("");
		txtcpf.setText("");
		txtTel.setText("");
		txtTel.requestFocus();
	}
	public void cadastrar() {

		ColaboradorDao cdao = new ColaboradorDao();
		Colaborador colab = new Colaborador();

		colab.setNome(txtnome.getText());
		colab.setCPF(txtcpf.getText());
		colab.setEndereco(txtrua.getText());
		colab.setTelefone(txtTel.getText());
		
		try {
		cdao.cadastrar(colab);
		limpar();
	
		} finally {
		}
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCadastroFunc window = new ViewCadastroFunc();
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
	public ViewCadastroFunc() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("CADASTRO DE FUNCIONARIOS");

		JLabel txttitle = new JLabel("Cadastro de Funcionário");
		txttitle.setFont(new Font("Verdana", Font.PLAIN, 20));
		txttitle.setBounds(280, 11, 267, 68);
		frame.getContentPane().add(txttitle);

		txtnome = new JFormattedTextField();
		txtnome.setBounds(31, 142, 332, 42);
		frame.getContentPane().add(txtnome);
		txtnome.setColumns(10);

		JButton btnNewButton = new JButton("CADASTRAR");
		btnNewButton.setBounds(300, 456, 152, 30);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Nome Completo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(31, 113, 267, 22);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblrua = new JLabel("Endereço :");
		lblrua.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblrua.setBounds(436, 109, 209, 30);
		frame.getContentPane().add(lblrua);

		JButton btnCADASTRO = new JButton("CADASTRAR");
		btnCADASTRO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				cadastrar();
				}catch(Exception cad) {
					JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR " + cad);
				}
			}
		});
		MaskFormatter mascaraCPF = null;
		MaskFormatter mascaraTel = null;
		

		try {
			mascaraCPF = new MaskFormatter("###.###.###-##");
			mascaraTel = new MaskFormatter("(##)#####-####");
			
		} catch (ParseException e1) {
			System.err.println("Erro na formatação: " + e1.getMessage());
			System.exit(-1);
		}
		
		
		btnCADASTRO.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCADASTRO.setBounds(300, 358, 231, 36);
		frame.getContentPane().add(btnCADASTRO);

		txtrua = new JFormattedTextField();
		txtrua.setColumns(10);
		txtrua.setBounds(436, 142, 332, 42);
		frame.getContentPane().add(txtrua);

		txtcpf = new JFormattedTextField(mascaraCPF);  //PASSAR MASCARA CPF
		txtcpf.setColumns(10);
		txtcpf.setBounds(31, 249, 332, 42);
		frame.getContentPane().add(txtcpf);

		JLabel lblCPF = new JLabel("CPF :");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCPF.setBounds(31, 222, 69, 22);
		frame.getContentPane().add(lblCPF);

		JLabel lblimage = new JLabel("");
		lblimage.setBounds(593, 11, 117, 86);
		frame.getContentPane().add(lblimage);
		ImageIcon i = new ImageIcon("src/Imagens/JAKE LAKE (2).png");
		i.setImage(i.getImage().getScaledInstance(lblimage.getWidth(), lblimage.getHeight(), 1));
		lblimage.setIcon(i);
		
		JLabel lblTelefone = new JLabel("Telefone :");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTelefone.setBounds(436, 222, 122, 22);
		frame.getContentPane().add(lblTelefone);
		
		txtTel = new JFormattedTextField(mascaraTel);
		txtTel.setBounds(436, 249, 332, 42);
		frame.getContentPane().add(txtTel);
//		cpf.replace("@",""); 
//		cpf.replace(",","");
//		cpf.replace("=","");

}
}
