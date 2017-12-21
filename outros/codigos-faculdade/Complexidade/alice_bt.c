/*
	COMPLEXIDADE E OTIMIZACAO - T3 (BACKTRACKING)
	
	Nomes: Emanoel Aurelio Viana Fabiano; Mauricio Steinert
	Data: novembro/2016
*/

#include <stdio.h>
#include <stdlib.h>

// Variaveis globais
unsigned int *vet_val, vet_length = 0, valor_ref = 0, depth = 0, *vet_res;
unsigned char *vet_usados;


void calcula_selos(unsigned int s, unsigned int idx) {
	unsigned int soma = 0, k = 0;

	vet_res[depth++] = vet_val[idx];
	vet_usados[idx] = 1;
	soma = s + vet_val[idx];

	// BEGIN DEBUG
	/*
	printf("*** Testando sequencia ");
	for(int i = 0; i < depth; i++)
			printf("%d ", vet_res[i]);
	printf("\n");
	*/
	// END DEBUG

	if(soma == valor_ref) {
		printf("Resposta valida = ");
		int acc = 0;
		for(int i = 0; i < depth; i++) {
			acc += vet_res[i];
			printf("%d ", vet_res[i]);
		}
		printf("\n");
		// BEGIN DEBUG
		//printf(" = %d\n", acc);
		// END DEBUG
		return;
	} else if(soma > valor_ref) return;

	while(k < vet_length) {
		if(vet_usados[k] == 0) {
			calcula_selos(soma, k);
			vet_usados[k] = 0;
			depth--;
		}
		k++;
	}
	vet_usados[idx] = 0;
}

int main(int argc, char **argv) {
	if(argc < 3) {
		printf("Quantidade de parametros invalida!\n");
		exit(1);
	}

	valor_ref = atoi(argv[1]);
	vet_length = argc - 2;

	vet_val = malloc(vet_length * 4);
	vet_res = malloc(vet_length * 4);
	vet_usados = malloc(vet_length);

	for(int i = 0; i < vet_length; i++) {
		vet_val[i] = atoi(argv[i + 2]);
		vet_res[i] = 0;
		vet_usados[i] = 0;
	}

	printf("Inicializacao: Valor referencia = %d\tVetor = { ", valor_ref);
	for(int i = 0; i < vet_length; i++)
		printf("%d ", vet_val[i]);

	printf("}\n");
	
	for(int i = 0; i < vet_length; i++) {
			depth = 0;
			calcula_selos(0, i);
	}
}
