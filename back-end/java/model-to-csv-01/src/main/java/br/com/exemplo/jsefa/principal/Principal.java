package br.com.exemplo.jsefa.principal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.exemplo.jsefa.modelo.Pessoa;
import net.sf.jsefa.Serializer;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;

/*
 * Exemplo de utlização da lib JSefa
 */

public class Principal {
	public static void main(String[] args) throws IOException {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("Pedro");
		pessoa1.setIdade(25);
		
		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Maria");
		pessoa2.setIdade(22);
		
		List<Pessoa> lista = new ArrayList<Pessoa>();
		lista.add(pessoa1);
		lista.add(pessoa2);
		
		File file = new File("jsefa.csv");
		CsvConfiguration config = new CsvConfiguration();
		config.setFieldDelimiter(';');
		Serializer serializer = CsvIOFactory.createFactory(config, Pessoa.class).createSerializer();

		serializer.open(new FileWriter(file));
		for (Pessoa pessoa : lista) {
			serializer.write(pessoa);
		}
		serializer.close(true);

	}
}
