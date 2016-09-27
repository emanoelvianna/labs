#include <stdio.h>
#include <stdlib.h>

#define TAMANHO 1000000

// estrutura aluno
typedef struct aluno
{
    float nota;
    struct aluno * proximo; // ponteiro para o proximo
    char *nome;
    int turma;
} t_alunos;

int main(int argc, char *argv[])
{
    t_alunos * ini_aluno; // ponteiro para a struct aluno
    t_alunos * proximo_aluno; // ponteiro para o proximo
    int i = 0;
    long double mediaEscola = 0;

    // aloca memoria
    ini_aluno = (t_alunos *)malloc(sizeof(t_alunos));
    // proximo_aluno aponta para endereco de ini_aluno
    proximo_aluno = ini_aluno;

    // enquanto for verdade...
    while(1)
    {

        proximo_aluno[i].nome = "Aluno";
        proximo_aluno[i].turma = 10;
        proximo_aluno[i].nota = 10.00;

        if(i < TAMANHO) // se deseja continuar...
        {
            // aloca memoria
            proximo_aluno->proximo = (t_alunos *)malloc(sizeof(t_alunos));
            proximo_aluno = proximo_aluno->proximo; // aponta para o pr�ximo
            printf("%i\n", i);
            i++;
        }
        else
            break; // sai do loop
    }

    proximo_aluno->proximo = NULL;
    proximo_aluno = ini_aluno;
    printf("\n");

    while(proximo_aluno != NULL)
    {
        mediaEscola += proximo_aluno->nota;
        proximo_aluno = proximo_aluno->proximo;
    }
    printf("Media da escola : %LF \n", mediaEscola/TAMANHO);

    while(proximo_aluno != NULL)
    {
        printf("nota: %6.f\n", proximo_aluno->nota);
        proximo_aluno = proximo_aluno->proximo; // aponta para o proximo
    }
    return 0;
}
