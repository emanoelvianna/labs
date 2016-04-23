#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdio.h>
#include <stdlib.h>

#include <conio.h>
#include <iostream>
#include <fstream>

using namespace std;

 struct Nodo{
      int classificacao;
      char pais[30];
      float consumo;
};

void leArquivoDeDados() {
 int length;
 char * buffer;

 struct Nodo nodo[35];

 ifstream is;
 is.open ("dados.csv", ios::in);

 is.seekg (0, ios::end);
 length = is.tellg();
 is.seekg (0, ios::beg);

 if (is.is_open())
 {
    int n = 0;
    buffer = new char[length];
    is.read(buffer,length);

   if (length > 0)
   {
      while(n < length) //isso substitue o ";" para um espaço
      {
         n++;
         if (buffer[n] == ';') buffer[n] = '\t';
      }

      printf("%s", buffer);
      getch();
      delete []buffer;
   }
 }
 else {
   printf("Arquivo não foi aberto");
   getch();
}

  is.close();
}

// Função callback de redesenho da janela de visualização
void Desenha(void) {
	// Limpa a janela de visualização com a cor branca
	glClearColor(1,1,1,0);
	glClear(GL_COLOR_BUFFER_BIT);
	glLoadIdentity();
	glColor3f(0.0f,0.0f,1.0f); // Define a cor de desenho: azul
	glBegin(GL_TRIANGLES); // Desenha triângulo no centro da janela
		glVertex2f(-0.5,-0.5);
		glVertex2f( 0.0, 0.5);
		glVertex2f( 0.5,-0.5);
	glEnd();
	// Execução dos comandos de desenho
	glutSwapBuffers();
}

// Função callback chamada para gerenciar eventos de teclas
void Teclado (unsigned char key, int x, int y)
{
	if (key == 27)
		exit(0);
}

// Função responsável por inicializar parâmetros e variáveis
void Inicializa(void)
{
	// Define a janela de visualização 2D
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(-1.0,1.0,-1.0,1.0);
	glMatrixMode(GL_MODELVIEW);
}

// Programa Principal
int main(void)
{
    leArquivoDeDados();
	int argc = 0;
	char *argv[] = { (char *)"gl", 0 };

	glutInit(&argc,argv);

	// Define do modo de operação da GLUT
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	// Especifica o tamanho inicial em pixels da janela GLUT
	glutInitWindowSize(400,400);
	// Cria a janela passando como argumento o título da mesma
	glutCreateWindow("Primeiro Programa");
	// Registra callback de redesenho da janela de visualização
	glutDisplayFunc(Desenha);
	// Registra a função callback para tratamento das teclas ASCII
	glutKeyboardFunc (Teclado);
	// Chama a função responsável por fazer as inicializações
	Inicializa();
	// Inicia o processamento e aguarda interações do usuário
	glutMainLoop();


	return 0;
}

