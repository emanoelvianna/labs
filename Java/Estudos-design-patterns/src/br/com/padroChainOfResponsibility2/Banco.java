package br.com.padroChainOfResponsibility2;

public abstract class Banco {
	 
    protected Banco proximo;
    protected IDBancos identificadorDoBanco;
 
    public Banco(IDBancos id) {
        proximo = null;
        identificadorDoBanco = id;
    }
 
    public void setProximo(Banco forma) {
        if (proximo == null) {
            proximo = forma;
        } else {
            proximo.setProximo(forma);
        }
    }
    
    public void efetuarPagamento(IDBancos id) throws Exception {
        if (podeEfetuarPagamento(id)) {
            efetuaPagamento();
        } else {
            if (proximo == null) {
                throw new Exception("banco não cadastrado");
            }
            proximo.efetuarPagamento(id);
        }
    }
     
    private boolean podeEfetuarPagamento(IDBancos id) {
        if (identificadorDoBanco == id) {
            return true;
        }
        return false;
    }
     
    protected abstract void efetuaPagamento();
}
