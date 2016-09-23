/** Multiplicação de matrizes  **/
// Obrigatoriamente: o numero de colunas de A deve ser igual a numero de linhas de B

#include <stdio.h>
#include <stdlib.h>

void multiplicaMatrizes(int M1[4][4],int L1, int C1, int M2[4][4], int L2, int C2, int *M3[4][4]) {
	if(C1 != L2) {
		printf("Soh pode haver multiplicacao se C1 = L2");
		return;
	}
	for(int i = 0; i < L1; i++) {
		for(int j = 0; j < C2; j++) {
			M3[i][j] = 0;
			for(int k = 0; k < C1; k++) {
				M3[i][j] += M1[i][k] * M2[k][j];
			}
		}
	}
}

int main() {
  int M1[4][4] = {{1,   2,    3,    4},
                  {5,   6,    7,    8},
                  {9,   10,   11,   12},
                  {13,  14,   15,   16}};

  int M2[4][4] = {{1,   2,    3,    4},
                  {5,   6,    7,    8},
                  {9,   10,   11,   12},
                  {13,  14,   15,   16}};

  int M3[4][4] = {{0,  0,   0,   0},
                  {0,  0,   0,   0},
                  {0,  0,   0,   0},
                  {0,  0,   0,   0}};;

  multiplicaMatrizes(M1, 4, 4, M2, 4, 4, M3);

  return 0;
}
