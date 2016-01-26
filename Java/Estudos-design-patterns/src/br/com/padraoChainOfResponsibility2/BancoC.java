package br.com.padraoChainOfResponsibility2;

public class BancoC extends Banco {

    public BancoC() {
        super(IDBancos.bancoC);
    }
 
    @Override
    protected void efetuaPagamento() {
        System.out.println("Pagamento efetuado no banco C");
    }

}
