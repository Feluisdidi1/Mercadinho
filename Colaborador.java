package model.bean;
import java.util.Date;


public class Colaborador {
	
	private int codigo;
	
	private String nome;
	
	private String CPF;
	
	private String endereco;

	private String telefone;
	
	public Colaborador (int codigo, String nome, String CPF, String endereco, String telefone) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.CPF = CPF;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	public Colaborador() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	
	
}
