package br.com.simplemongodb;

import br.com.entidade.Endereco;
import br.com.entidade.Pessoa;
import br.com.entidade.Sexo;
import br.com.entidade.Telefone;

public class Principal {
	public static void main(String[] args) {
		Endereco endereco1 = new Endereco("RS", "Santa Rosa", "Livres campos", "94500548");
		Telefone telefone1 = new Telefone("021-3424.6494", "021-3424.6494");
		Pessoa pessoa1 = new Pessoa("Pedro", "Silva", Sexo.Masculino, telefone1, endereco1);
	}
}
