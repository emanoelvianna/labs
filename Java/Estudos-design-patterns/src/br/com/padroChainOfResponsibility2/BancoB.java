package br.com.padroChainOfResponsibility2;

public class BancoB extends Banco {

    public BancoB() {
        super(IDBancos.bancoB);
    }
 
    @Override
    protected void efetuaPagamento() {
        System.out.println("Pagamento efetuado no banco B");
    }

}
