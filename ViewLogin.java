package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import model.dao.UsuarioDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ViewLogin {

	JFrame frame;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// MODELO ACINZENTADO DO NETBEANS
		try {
		    UIManager.setLookAndFeel( new FlatDarkLaf() );
		} catch( Exception ex ) {
		    System.err.println( "Failed to initialize LaF" );
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin window = new ViewLogin();
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
	public ViewLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 444);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("PAGINA DE LOGIN");
								frame.getContentPane().setLayout(null);
						
								JLabel lblNewLabel = new JLabel("Login:");
								lblNewLabel.setBounds(60, 52, 57, 26);
								lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
								frame.getContentPane().add(lblNewLabel);
				
						txtLogin = new JTextField();
						txtLogin.setBounds(60, 89, 378, 52);
						txtLogin.setColumns(10);
						frame.getContentPane().add(txtLogin);
				
						txtSenha = new JPasswordField();
						txtSenha.setBounds(60, 235, 378, 52);
						txtSenha.addKeyListener(new KeyAdapter() {
							@Override
							public void keyPressed(KeyEvent evt) {

								if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

									checagem();
									
								}

							}
						});
						
								JLabel lblSenha = new JLabel("Senha:");
								lblSenha.setBounds(62, 198, 65, 26);
								lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 21));
								frame.getContentPane().add(lblSenha);
						frame.getContentPane().add(txtSenha);
				
						JButton btnNewButton = new JButton("Entrar");
						btnNewButton.setBounds(169, 323, 180, 38);
						btnNewButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								checagem();

//				if(checkLogin(txtLogin.getText(),new String (txtSenha.getPassword() ) ) ) {

//					JOptionPane.showMessageDialog(null, "BEM VINDO AO SISTEMA FERNANDO ! " , "Logou !" , JOptionPane.WARNING_MESSAGE);

//				}else {
//					JOptionPane.showMessageDialog(null, "DADOS INVÁLIDOS ","Mensagem de Login" , JOptionPane.ERROR_MESSAGE);
//					}

							}
						});
						frame.getContentPane().add(btnNewButton);
	}
	public void checagem() {//METODO PARA VALIDAÇÃO DE LOGIN E LIBERAÇÃO DE TELA PRINCIPAL
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.checkLogin(txtLogin.getText(), new String(txtSenha.getPassword()))) {
			new ViewHome().setVisible(true); // COMANDO QUE ABRE A OUTRA VIEW HOME
			frame.dispose(); // COMANDO PARA FECHAR A VIEW
		} else {
			JOptionPane.showMessageDialog(null, "LOGIN INVALIDO", null, JOptionPane.ERROR_MESSAGE);
		}
	}

	//	public boolean checkLogin(String login, String senha) {return login.equals("luisdidi") && senha.equals("fernando1");}
}
