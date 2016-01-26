package br.com.padraoChainOfResponsibility2;

public class BancoD extends Banco {

    public BancoD() {
        super(IDBancos.bancoD);
    }
 
    @Override
    protected void efetuaPagamento() {
        System.out.println("Pagamento efetuado no banco D");
    }

}
