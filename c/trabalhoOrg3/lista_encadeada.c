#include <stdio.h>
#include <stdlib.h>

#define TAMANHO 1000000

// estrutura aluno
typedef struct aluno
{
    char *nome;
    int turma;
    double nota;
    struct aluno * proximo; // ponteiro para o proximo
} t_alunos;

int main(int argc, char *argv[])
{
    t_alunos* aluno_atual; // ponteiro para a struct aluno
    t_alunos* head;

    int i = 0;
    long double mediaEscola = 0;

    aluno_atual = (t_alunos *)malloc(sizeof(t_alunos));
    head = (t_alunos *)malloc(sizeof(t_alunos));

    aluno_atual->nome = "First";
    aluno_atual->turma = 10;
    aluno_atual->nota = 10.00;
    head = aluno_atual;

    // enquanto for verdade...
    while(1)
    {
      if(i < TAMANHO) {
        // aloca memoria
        aluno_atual->proximo = (t_alunos *)malloc(sizeof(t_alunos));
        aluno_atual->proximo->nome = "Aluno";
        aluno_atual->proximo->turma = 10;
        aluno_atual->proximo->nota = 10.00;
        aluno_atual = aluno_atual->proximo;

        i++;
      } else {
        break; // sai do loop
		  }
    }

    while(aluno_atual != NULL)
    {
        mediaEscola += aluno_atual->nota;
        aluno_atual = aluno_atual->proximo;
    }
    printf("Media da escola : %LF \n", mediaEscola/TAMANHO);

    while(aluno_atual != NULL)
    {
        //printf("nota: %6.f\n", aluno_atual->nota);
        aluno_atual = aluno_atual->proximo; // aponta para o proximo
    }
    return 0;
}
