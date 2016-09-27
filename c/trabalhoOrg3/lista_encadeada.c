#include <stdio.h>
#include <stdlib.h>

#define TAMANHO 500

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
            // aloca mem�ria
            proximo_aluno->proximo = (t_alunos *)malloc(sizeof(t_alunos));
            proximo_aluno = proximo_aluno->proximo; // aponta para o pr�ximo
        }
        else
            break; // sai do loop
    }
    /*
        O proximo e NULL porque ss sabemos que uma lista encadeada chegou
        ao fim quando o proximo elemento aponta para NULL
    */
    proximo_aluno->proximo = NULL;
    // proximo_aluno aponta para o mesmo de ini_aluno, comeca do inicio
    proximo_aluno = ini_aluno;
    printf("\n");
    // mostra todos os dados
    // enquanto proximo_aluno for diferente de NULL
    while(proximo_aluno != NULL)
    {
        printf("nota: %6.f\n", proximo_aluno->nota);
        proximo_aluno = proximo_aluno->proximo; // aponta para o proximo
    }
    return 0;
}
