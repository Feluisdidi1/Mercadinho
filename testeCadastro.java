package view;


import model.bean.Colaborador;
import model.dao.ColaboradorDao;

public class testeCadastro {
public static void main(String[] args) {
 



		
		
		//Instancia o DAO
		ColaboradorDao dao = new ColaboradorDao();

         
		//Instancia o Colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setNome("test");
		colaborador.setCPF("49982447874");
		colaborador.setEndereco("R Orlando Pazotto 283");
		colaborador.setTelefone("1197252354");
		
		//Cadastra no banco de dados
		dao.cadastrar(colaborador);

		System.out.println("Cadastrado!");
		
	}

}
