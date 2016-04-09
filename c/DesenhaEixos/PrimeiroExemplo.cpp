// PrimeiroPrograma.cpp
// Isabel H. Manssour e Marcelo Cohen
// Um programa OpenGL que abre uma janela, desenha uma casa
// e permite fazer zoom, pan e translação.

#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdio.h>
#include <stdlib.h>

GLfloat tx, ty;
GLfloat left, right, bottom, top;
GLfloat panX, panY;
GLint largura, altura;

void DesenhaEixos()
{
    glColor3ub(255,255,255);
	glBegin(GL_LINES);
		glVertex2f(0.0,bottom);
		glVertex2f(0.0,top);
		glVertex2f(left,0);
		glVertex2f(right,0);
	glEnd();
}
// Função callback de redesenho da janela de visualização
void Desenha(void)
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
    // Estabelece a janela de seleção (esquerda, direita, inferior,
	// superior) mantendo a proporção com a janela de visualização
	if (largura <= altura)
		gluOrtho2D (left+panX, right+panX, bottom*altura/largura+panY, top*altura/largura+panY);
	else
		gluOrtho2D (left*largura/altura+panX, right*largura/altura+panX, bottom+panY, top+panY);
	glMatrixMode(GL_MODELVIEW);

	// Limpa a janela de visualização com a cor branca
	glClearColor(0,0,0,0);
	glClear(GL_COLOR_BUFFER_BIT);
	glLoadIdentity();
	DesenhaEixos();
	glTranslatef(tx, ty, 0);
	// Execução dos comandos de desenho
	glutSwapBuffers();
}

// Função responsável por inicializar parâmetros e variáveis
void Inicializa(void)
{
    // Inicialização das variáveis globais
    tx = 0;
    ty = 0;
	left = -1.0;
	right = 1.0;
	bottom = -1.0;
	top = 1.0;
	panX = panY = 0;
	largura = 400;
	altura = 400;

	// Define a janela de visualização 2D
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(left+panX, right+panX, bottom+panY, top+panY);
	glMatrixMode(GL_MODELVIEW);
}

// Programa Principal
int main(void)
{
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
	// Chama a função responsável por fazer as inicializações
	Inicializa();
	// Inicia o processamento e aguarda interações do usuário
	glutMainLoop();

	return 0;
}
