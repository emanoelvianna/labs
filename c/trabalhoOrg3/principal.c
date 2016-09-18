#include <stdio.h>

int regras(int matriz[4][4], int movimentacao1, int movimentacao2, int coluna, int linha, int cont) {
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

int minimizar(int matriz[4][4]) {
		int parada;

		do {
			parada = 0;

			for (int cont = 0; cont < 3; cont++) {

				if (cont != 4) {
					for (int linha = 0; linha < 3; linha++) {
						if (matriz[cont][linha] != 0) {
							for (int coluna = 0; coluna < 3; coluna++) {
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

int main() {
  int mat[4][4] = {{0, 1, 1, 0},
                   {1, 0, 1, 1},
                   {0, 1, 0, 0},
                   {1, 1, 0, 0}};
  int i, j;

  /** populando a matriz **/
  for(i = 0; i < 3; i++){
    for(j = 0; j < 3; j++){
      if( (j + i) % 2 == 0 ){
        mat[i][j] = 1;
      } else {
        mat[i][j] = 0;
      }
    }
  }

  minimizar(mat);

  for(i = 0; i < 3; i++){
    printf("\n");
    for(j = 0; j < 3; j++){
        printf("%i ", mat[i][j]);
    }
  }

  printf("\n");
  return (0);
}
