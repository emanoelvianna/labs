package br.com.padroChainOfResponsibility2;

public class BancoA extends Banco {
	 
    public BancoA() {
        super(IDBancos.bancoA);
    }
 
    @Override
    protected void efetuaPagamento() {
        System.out.println("Pagamento efetuado no banco A");
    }
}