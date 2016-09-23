#include <stdio.h>
#include <stdlib.h>

int regras(int matriz[150][150], int movimentacao1, int movimentacao2, int coluna, int linha, int cont) {
		if (movimentacao1 >= movimentacao2) {

			int dif = movimentacao1 - movimentacao2;
			matriz[cont][linha] = dif;
			if (cont == coluna) { // ciclo
				matriz[linha][cont] = 0;
				return 1;
			}
			matriz[cont][coluna] += matriz[linha][coluna];
			matriz[linha][coluna] = 0;
			return 1;
		} else {

			int dif = movimentacao2 - movimentacao1;
			matriz[linha][coluna] = dif;
			if (cont == coluna) { // ciclo
				matriz[cont][coluna] = 0;
				return 1;
			}
			matriz[cont][coluna] += matriz[cont][linha];
			matriz[cont][linha] = 0;
			return 1;
		}
}

int minimizar(int matriz[150][150]) {
		int parada;

		do {
			parada = 0;

			for (int cont = 0; cont < 3; cont++) {

				if (cont != 4) {
					for (int linha = 0; linha < 149; linha++) {
						if (matriz[cont][linha] != 0) {
							for (int coluna = 0; coluna < 149; coluna++) {
								if (matriz[linha][coluna] != 0) {

									int movimentacao1 = matriz[cont][linha];
									int movimentacao2 = matriz[linha][coluna];
									if (movimentacao1 != 0) {
										parada = regras(matriz, movimentacao1, movimentacao2, coluna, linha, cont);
									}
								}
							}
						}
					}
				}
			}
		} while (parada == 1);

    return (0);
}

/** funcao random para gerar valores para popular a matriz **/
int randomInteger (float low, float high) {
	int k;
	double d;
	d = (double) rand () / ((double) RAND_MAX + 1);
	k = d * (high - low + 1);
	return low + k;
}

/** funcao para popular a matriz **/
int popular(int mat[150][150]) {
	for(int i = 0; i < 149; i++) {
		for(int j = 0; j < 149; j++) {
			mat[i][j] = randomInteger(0, 50);
		}
	}
}

int main() {
	int mat[150][150];

	popular(mat);

	for(int i = 0; i < 149; i++){
    for(int j = 0; j < 149; j++){
        printf("%i ", mat[j][i]);
				printf("\n");
    }
		break;
  }

	minimizar(mat);

  for(int i = 0; i < 149; i++){
    for(int j = 0; j < 149; j++){
        printf("%i ", mat[j][i]);
				printf("\n");
    }
		break;
  }

  printf("\n");
  return (0);
}
