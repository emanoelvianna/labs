#include <stdio.h>
#include <stdlib.h>

#define N_LINHAS 500
#define N_COLUNAS 500

struct aluno {
	double nota;
	char *nome;
	int turma;
};

struct aluno alunos[N_LINHAS][N_COLUNAS];

int main() {

    int i, j;
		long double mediaEscola = 0;

		/** preenchendo a matriz com valores **/
    for(i = 0; i < N_LINHAS; i++) {
      for(j = 0; j < N_COLUNAS; j++) {
				alunos[i][j].nome = "Aluno";
				alunos[i][j].turma = 10;
				alunos[i][j].nota = 10.00;
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
