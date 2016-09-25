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

#define N_LINHAS 500
#define N_COLUNAS 500

long double M1[N_LINHAS][N_COLUNAS];
long double M2[N_LINHAS][N_COLUNAS];
long double matrizResultante[N_LINHAS][N_COLUNAS];

int randomInteger (double low, double high)
{
    int k;
    double d;
    d = (double) rand () / ((double) RAND_MAX + 1);
    k = d * (high - low + 1);
    return low + k;
}

int main()
{

    /** preenchendo a matriz com valores randomicos **/
    for(int i = 0; i < N_LINHAS; i++) {
      for(int j = 0; j < N_COLUNAS; j++) {
        M1[i][j] = 1;
        M2[i][j] = 1;
      }
    }

   /** multiplicando a matriz **/
   for (int i = 0; i < N_LINHAS; i++) {
      for (int j = 0; j < N_COLUNAS; j++) {
         matrizResultante[i][j] = 0;
         for (int k = 0; k < N_COLUNAS; k++) {
            matrizResultante[i][j] += M1[i][k] * M2[k][j];
         }
      }
   }

   /** escrevendo a matriz resultante **/
  //  for(int i = 0; i < N_LINHAS; i++) {
  //    for(int j = 0; j < N_COLUNAS; j++) {
  //       printf("%LF", matrizResultante[i][j]);
  //      printf(" ");
  //    }
  //    printf("\n");
  //  }

   return 0;
}
