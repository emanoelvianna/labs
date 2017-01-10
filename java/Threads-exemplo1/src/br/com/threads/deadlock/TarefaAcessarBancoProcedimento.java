package br.com.threads.deadlock;

public class TarefaAcessarBancoProcedimento implements Runnable {

	private PoolDeConexao pool;
	private GerenciadorDeTransacao tx;

	public TarefaAcessarBancoProcedimento(PoolDeConexao pool, GerenciadorDeTransacao tx) {
		this.pool = pool;
		this.tx = tx;
	}

	@Override
	public void run() {
		synchronized (tx) {
			System.out.println("começando o gerenciamento da transação");
			tx.begin();
			synchronized (pool) {
				System.out.println("pegando a chave do pool");
				pool.getConnection();
			}
		}
	}

}
