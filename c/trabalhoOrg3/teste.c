/***
    IMPORTANTE NAO USAR! só para teste
****/


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
    t_alunos* aluno_atual; // ponteiro para a struct aluno
	t_alunos* head;
    //t_alunos* proximo_aluno; // ponteiro para o proximo
    int i = 0;
    long double mediaEscola = 0;

    // aloca memoria
    aluno_atual = (t_alunos *)malloc(sizeof(t_alunos));
	head = (t_alunos *)malloc(sizeof(t_alunos));
    // proximo_aluno aponta para endereco de ini_aluno
    //proximo_aluno = aluno_atual;


	aluno_atual->nome = "First";
    aluno_atual->turma = 10;
    aluno_atual->nota = 10.00;
	head = aluno_atual;

    // enquanto for verdade...
    while(1)
    {

        if(i < TAMANHO) // se deseja continuar...
        {
            // aloca memoria
            aluno_atual->proximo = (t_alunos *)malloc(sizeof(t_alunos));
            aluno_atual->proximo->nome = "Aluno";
			aluno_atual->proximo->turma = 10;
			aluno_atual->proximo->nota = 10.00;
			aluno_atual = aluno_atual->proximo;


			if(i % 100000 == 0){printf("%i\n", i);}
            i++;
        }
        else {
            break; // sai do loop
		  }
	}

	printf("[%s ---- %d ---- %f]\n", head->nome, head->nota, head->nota);
/*
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
*/
    return 0;
}
