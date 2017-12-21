#include <stdio.h>
#include <stdlib.h>

unsigned int *valores, tot_num = 0, valor_ref = 0;
unsigned char *usados;

void gera_selo(unsigned int soma_p, unsigned int testados_l) {
	char testados[testados_l];
	unsigned int soma = 0, prox_n_testado = 0;

	for(int i = 0; i < testados_l; i++)
		testados[i] = 0;

	soma += soma_p;

	if(soma == valor_ref)
		// imprime resultado
		return;
	else if(soma > valor_ref)
		return;

	while(prox_n_testado < testados_l 
				&& usados[prox_n_testado] == 1
				&& testados[prox_n_testado] == 1)
		prox_n_testado++;

	while(prox_n_testado < testados_l) {
		gera_selo(soma, testados_l);
	}
		
	
}


int main(int argc, char **argv) {
	valores = malloc(argc - 1);
	usados = malloc(argc - 1);

	valor_ref = atoi(argv[1]);

	for(int i = 0; i < argc - 2; i++) {
		valores[i] = atoi(argv[i + 2]);
		tot_num++;
	}

	for(int i = 0; i < tot_num; i++)
		printf("%d ", valores[i]);

	printf("Valor referencia: %d\n", valor_ref);

	gera_selo(0, argc);
}
