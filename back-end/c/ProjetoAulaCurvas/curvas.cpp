// Isabel H. Manssour
// Um programa OpenGL que exemplifica como trabalhar com curvas param�tricas.

#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <iostream>
#include <cstdlib>
#include <vector>
#include "Ponto.h"

using namespace std;


GLfloat left_, right_, bottom, top;
GLint largura, altura;
GLfloat vetorDePontos[6];
GLint contador = 0;


// Fun��o que converte um ponto x,y da coordenada de tela recebido como
// par�metro para um objeto Ponto do universo, que � retornado.
Ponto mapeamentoViewportWindow(int x, int y)
{
    GLfloat x1 = ( ( x * (right_-left_) ) / largura ) + left_;
    GLfloat y1 = ( ( (y-altura) * (top-bottom) ) / (-altura) ) + bottom;
    Ponto p(x1,y1);
    return p;
}


void DesenhaEixos()
{
    glLineWidth(1);
    glColor3ub(255,255,255);
	glBegin(GL_LINES);
		glVertex2f(0.0,bottom);
		glVertex2f(0.0,top);
		glVertex2f(left_,0);
		glVertex2f(right_,0);
	glEnd();
}


void DesenhaTriangulo()
{
    glColor3ub(0,0,255);
    glBegin(GL_TRIANGLES);
        glVertex2d(-1.5, -1.5);
        glVertex2d(   0,  1.5);
        glVertex2d( 1.5, -1.5);
    glEnd();
}


// Fun��o callback de redesenho da janela de visualiza��o
void Desenha(void)
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
    // Estabelece a janela de sele��o (esquerda, direita, inferior,
	// superior) mantendo a propor��o com a janela de visualiza��o
	if (largura <= altura)
		gluOrtho2D (left_, right_, bottom*altura/largura, top*altura/largura);
	else
		gluOrtho2D (left_*largura/altura, right_*largura/altura, bottom, top);
	glMatrixMode(GL_MODELVIEW);


	if(contador == 6) {

	}

	// Limpa a janela de visualiza��o com a cor preta
	glClearColor(0,0,0,0);
	glClear(GL_COLOR_BUFFER_BIT);
	glLoadIdentity();

	DesenhaEixos();


	// Execu��o dos comandos de desenho
	glutSwapBuffers();
}


// Fun��o callback chamada para gerenciar eventos de teclas
void Teclado (unsigned char key, int x, int y)
{
	if (key == 27)
		exit(0);

    glutPostRedisplay();
}


// Fun��o callback chamada quando o tamanho da janela � alterado
void AlteraTamanhoJanela(GLsizei w, GLsizei h)
{
	// Para previnir uma divis�o por zero
	if ( h == 0 ) h = 1;

    largura = w;
    altura = h;

	// Especifica o tamanho da viewport
    glViewport(0, 0, w, h);
}


void TeclasEspeciais(int key, int x, int y)
{
    switch (key)
    {
        case GLUT_KEY_UP:   left_-=1;
                            right_+=1;
                            top+=1;
                            bottom-=1;
                            break;
        case GLUT_KEY_DOWN: left_+=1;
                            right_-=1;
                            top-=1;
                            bottom+=1;
                            break;
    }
    glutPostRedisplay();
}


// Fun��o callback chamada para gerenciar eventos do mouse
void GerenciaMouse(int button, int state, int x, int y)
{
    if (button == GLUT_LEFT_BUTTON)
         if (state == GLUT_DOWN) {
			cout << "(" << x << ", " << y << ")";
			if(contador <= 6){
                vetorDePontos[contador] = x;
                contador++;
                vetorDePontos[contador] = y;
                contador++;
            }
		}

    glutPostRedisplay();
}


// Fun��o respons�vel por inicializar par�metros e vari�veis
void Inicializa(void)
{
    // Inicializa��o das vari�veis globais
	left_ = -50.0;
	right_ = 50.0;
	bottom = -50.0;
	top = 50.0;
	largura = 400;
	altura = 400;
}


void Anima(int value)
{

	// Redesenha
	glutPostRedisplay();
	glutTimerFunc(30,Anima, 1);
}

// Programa Principal
int main(void)
{
	int argc = 0;
	char *argv[] = { (char *)"gl", 0 };

	glutInit(&argc,argv);

	// Define do modo de opera��o da GLUT
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	// Especifica o tamanho inicial em pixels da janela GLUT
	glutInitWindowSize(400,400);
	// Cria a janela passando como argumento o t�tulo da mesma
	glutCreateWindow("Curva Param�trica");
	// Registra callback de redesenho da janela de visualiza��o
	glutDisplayFunc(Desenha);
	// Registra a fun��o callback para tratamento das teclas ASCII
	glutKeyboardFunc (Teclado);
	// Registra a fun��o callback para tratamento das teclas especiais
	glutSpecialFunc(TeclasEspeciais);
	// Registra a fun��o callback que gerencia os eventos do mouse
	glutMouseFunc(GerenciaMouse);
	// Registra a fun��o callback chamada quando o tamanho da janela � alterado
	glutReshapeFunc(AlteraTamanhoJanela);
	// Registra a fun��o callback que ser� chamada a cada intervalo de tempo
	glutTimerFunc(30, Anima, 1);
	// Chama a fun��o respons�vel por fazer as inicializa��es
	Inicializa();
	// Inicia o processamento e aguarda intera��es do usu�rio
	glutMainLoop();

	return 0;
}

