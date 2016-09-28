#include <stdio.h>
#include <stdlib.h>

#define TAMANHO 250000

// estrutura ponto
typedef struct aluno
{
    double nota;
    struct aluno * proximo; // ponteiro para o proximo
    char *nome;
    int turma;
} t_aluno;

int main(int argc, char *argv[])
{
    int i = 0;
    t_aluno * ini_aluno; // ponteiro para a struct ponto
    t_aluno * proximo_aluno; // ponteiro para o proximo
		long double mediaEscola = 0;

    // aloca memoria
    ini_aluno = (t_aluno *)malloc(sizeof(t_aluno));
    // proximo_aluno aponta para endereco de ini_aluno
    proximo_aluno = ini_aluno;

    // enquanto for verdade...
    while(1)
    {
        if(i < TAMANHO) // se deseja continuar...
        {

    				proximo_aluno->nome = "Aluno";
    				proximo_aluno->turma = 10;
            proximo_aluno->nota = 10.00;

            // aloca memoria
            proximo_aluno->proximo = (t_aluno *)malloc(sizeof(t_aluno));
            proximo_aluno = proximo_aluno->proximo; // aponta para o pr�ximo
            i = i + 1;
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
        //printf("nota: %6.f", proximo_aluno->nota);
        proximo_aluno = proximo_aluno->proximo; // aponta para o pr�ximo
    }

    printf("quantidade de nodos: %d\n", TAMANHO);
    printf("media: %LF\n", mediaEscola/TAMANHO);

    return 0;
}
