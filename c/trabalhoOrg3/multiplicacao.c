/**

Multiplicação de matrizes
Obrigatoriamente: o numero de colunas de A deve ser igual a numero de linhas de B

**/

#include <stdio.h>
#include <stdlib.h>

/** decleracao do tamanho da linha e coluna da matriz **/
#define linha 500
#define coluna 500

int randomInteger (float low, float high)
{
    int k;
    double d;
    d = (double) rand () / ((double) RAND_MAX + 1);
    k = d * (high - low + 1);
    return low + k;
}

int main() {

  int M1[linha][coluna];
	int M2[linha][coluna];
  int matrizResultante[linha][coluna];

	/** preenchendo a matriz com valores randomicos **/

	for(int i = 0; i < linha; i++) {
		for(int j = 0; j < coluna; j++) {
			M1[i][j] = randomInteger(100, 500);
			M2[i][j] = randomInteger(100, 500);
		}
	}

	/** multiplicando a matriz **/
	for(int i = 0; i < linha; i++) {
		for(int j = 0; j < coluna; j++) {
			matrizResultante[i][j] = 0;
			for(int k = 0; k < coluna; k++) {
				matrizResultante[i][j] += M1[i][k] * M2[k][j];
			}
		}
	}

	/** escrevendo a matriz resultante **/
	for(int i = 0; i < linha; i++) {
		for(int j = 0; j < coluna; j++) {
			printf("%d", matrizResultante[i][j]);
			printf(" ");
		}
		printf("\n");
	}

  return 0;
}
