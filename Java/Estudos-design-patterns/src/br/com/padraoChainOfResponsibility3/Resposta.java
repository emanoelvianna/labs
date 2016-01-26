package br.com.padraoChainOfResponsibility3;

public interface Resposta {
	void responde(Requisicao req, Conta conta);

	void setProxima(Resposta resposta);
}
