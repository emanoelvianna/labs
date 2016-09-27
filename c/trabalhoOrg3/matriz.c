/**

Multiplicação de matrizes
Obrigatoriamente: o numero de colunas de A deve ser igual a numero de linhas de B

Observações:

Quem sabe utilizar um long double inicialmente para os valores da matriz ( para isso precisa modificar a função random ).
Dara uma pesquisada e ver se é possivel guardar os valores quem sabe em um segundo momento em um short int.
Também avaliar o que se pode melhorar nos loops e questões de desempenho e hierarquia de memoria.
	* Para saber se as alteraçẽos realmente surtiram efeito precisamos utilizar os softwares!

Ainda precisa se avaliar se utilizamso uma função randomica para gerar os valores

[] Identificar o trecho que código que gera mais ‘misses’ e analisar a causa do miss. Se possível, propor
uma alteração (não trivial) no programa para melhorar o desempenho do acesso à memória;
[] Relatório conciso (+-7 páginas) feito em Latex
[] Caracterizar o programa em termos de parâmetros de hierarquia de memória (tamanho da cache,
associatividade, e tamanho do bloco) utilizando range de parâmetros de cache realistas.
[] Se houver uma proposta de otimização, mostrar como era o código original e o otimizado e explicar precisamente o
motivo da otimização proposta ter melhorado o desempenho.

**/

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

		printf("Media da escola : %LF \n", mediaEscola/N_COLUNAS);
		printf("Quantidade de alunos : %d \n", k);

   	/** escrevendo a matriz resultante
   	for(i = 0; i < N_LINHAS; i++) {
     	for(j = 0; j < N_COLUNAS; j++) {
        	printf("%LF", escola[i][j].nota);
        	printf(" ");
     	}
     	printf("\n");
   	}
		**/

   	return 0;
}
