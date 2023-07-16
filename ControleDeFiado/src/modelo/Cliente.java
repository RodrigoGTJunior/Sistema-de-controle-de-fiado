package modelo;

public class Cliente {
	
	
	int idCliente;
	String nomeCliente;
	String cpf;
	String telefone;
	Double limite;
	Boolean ativo;
	String ativadoDesativado;

	//ID Cliente
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	//Nome Cliente
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	//Cpf 
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	//Telefone
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	//Limite
	public Double getLimite() {
		return limite;
	}
	public void setLimite(Double limite) {
		this.limite = limite;
	}
	//Ativo (banco de dados)
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	//Ativado/Desativado (aplicação)
	public String getAtivadoDesativado() {
		return ativadoDesativado;
	}
	public void setAtivadoDesativado(String ativadoDesativado) {
		this.ativadoDesativado = ativadoDesativado;
	}
}
