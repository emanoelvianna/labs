package br.com.entidade;

public class Endereco {
	private String estado;
	private String cidade;
	private String rua;
	private String cep;

	public Endereco(String estado, String cidade, String rua, String cep) {
		super();
		this.setEstado(estado);
		this.setCidade(cidade);
		this.setRua(rua);
		this.setCep(cep);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return "Endereco{" +
        ", estado='" + estado + '\'' +
        ", cidade='" + cidade + '\'' +
        ", rua='" + rua + '\'' +
        ", cep=" + cep +
        '}';
	}

}
