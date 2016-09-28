#include <stdio.h>
#include <stdlib.h>

#define N_LINHAS 5000
#define N_COLUNAS 5000

struct aluno {
	char *nome;
	int turma;
	double nota;
};

struct aluno alunos[N_LINHAS][N_COLUNAS];

int main() {

    int i, j;
		int k = 0;
		long double mediaEscola = 0;

		/** preenchendo a matriz com valores **/
    for(i = 0; i < N_LINHAS; i++) {
      for(j = 0; j < N_COLUNAS; j++) {
				alunos[i][j].nome = "Aluno";
				alunos[i][j].turma = 10;
				alunos[i][j].nota = 10.00;
				k++;
      }
    }

		/** calcula a media da escola **/
		for(i = 0; i < N_LINHAS; i++) {
      for(j = 0; j < N_COLUNAS; j++) {
				mediaEscola += alunos[i][j].nota;
			}
		}

		int quantidadeNodos = N_LINHAS * N_COLUNAS;
		printf("quantidade de nodos: %d\n", quantidadeNodos);
		printf("media: %LF\n", mediaEscola/quantidadeNodos);

   	return 0;
}
