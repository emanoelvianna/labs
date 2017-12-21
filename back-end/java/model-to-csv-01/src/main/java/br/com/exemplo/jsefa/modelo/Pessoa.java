package br.com.exemplo.jsefa.modelo;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

@CsvDataType()
public class Pessoa {
	@CsvField(pos = 1)
	public String nome;
	@CsvField(pos = 2)
	public Integer idade;

	public Pessoa() {
	}

	public Pessoa(String nome, Integer idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
}
