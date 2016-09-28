#include <stdio.h>
#include <stdlib.h>

#define N_LINHAS 5000
#define N_COLUNAS 5000

// estrutura ponto
typedef struct aluno
{
    double nota;
    struct aluno * proximo; // ponteiro para o proximo
    char *nome;
    int turma;
} t_aluno;

t_aluno alunos[N_LINHAS][N_COLUNAS];

int main() {

    int i, j;
		long double mediaEscola = 0;

		t_aluno * p;
		p = &alunos[0][0];

		for (i = 0; i < N_LINHAS * N_COLUNAS; i++, p++) {
			p->nome = "Aluno";
			p->turma = 10;
			p->nota = 10.00;
		}

		p = &alunos[0][0];

		for (i = 0; i < N_LINHAS * N_COLUNAS; i++, p++) {
			mediaEscola += p->nota;
		}

		int quantidadeNodos = N_LINHAS * N_COLUNAS;
		printf("quantidade de nodos: %d\n", quantidadeNodos);
		printf("media: %LF\n", mediaEscola/quantidadeNodos);

   	return 0;
}
