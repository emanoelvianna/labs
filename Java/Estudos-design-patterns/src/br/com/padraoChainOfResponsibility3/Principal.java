package br.com.padraoChainOfResponsibility3;

public class Principal {
	public static void main(String[] args) {
		Resposta resposta = new RespostaEmXml();
		resposta.setProxima(new RespostaEmPorcento());
		resposta.setProxima(new RespostaEmCsv());

		resposta.responde(new Requisicao(Formato.CSV), new Conta("Pedro", 500));
		resposta.responde(new Requisicao(Formato.XML), new Conta("Maria", 1500));
		resposta.responde(new Requisicao(Formato.CSV), new Conta("Marcos", 400));
		resposta.responde(new Requisicao(Formato.PORCENTO), new Conta("Matheus", 2500));
		

	}
}
