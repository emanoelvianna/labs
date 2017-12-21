// Isabel H. Manssour
// Um programa OpenGL que exemplifica como trabalhar com curvas paramétricas.

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


// Função que converte um ponto x,y da coordenada de tela recebido como
// parâmetro para um objeto Ponto do universo, que é retornado.
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


// Função callback de redesenho da janela de visualização
void Desenha(void)
{
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
    // Estabelece a janela de seleção (esquerda, direita, inferior,
	// superior) mantendo a proporção com a janela de visualização
	if (largura <= altura)
		gluOrtho2D (left_, right_, bottom*altura/largura, top*altura/largura);
	else
		gluOrtho2D (left_*largura/altura, right_*largura/altura, bottom, top);
	glMatrixMode(GL_MODELVIEW);


	if(contador == 6) {

	}

	// Limpa a janela de visualização com a cor preta
	glClearColor(0,0,0,0);
	glClear(GL_COLOR_BUFFER_BIT);
	glLoadIdentity();

	DesenhaEixos();


	// Execução dos comandos de desenho
	glutSwapBuffers();
}


// Função callback chamada para gerenciar eventos de teclas
void Teclado (unsigned char key, int x, int y)
{
	if (key == 27)
		exit(0);

    glutPostRedisplay();
}


// Função callback chamada quando o tamanho da janela é alterado
void AlteraTamanhoJanela(GLsizei w, GLsizei h)
{
	// Para previnir uma divisão por zero
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


// Função callback chamada para gerenciar eventos do mouse
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


// Função responsável por inicializar parâmetros e variáveis
void Inicializa(void)
{
    // Inicialização das variáveis globais
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

	// Define do modo de operação da GLUT
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	// Especifica o tamanho inicial em pixels da janela GLUT
	glutInitWindowSize(400,400);
	// Cria a janela passando como argumento o título da mesma
	glutCreateWindow("Curva Paramétrica");
	// Registra callback de redesenho da janela de visualização
	glutDisplayFunc(Desenha);
	// Registra a função callback para tratamento das teclas ASCII
	glutKeyboardFunc (Teclado);
	// Registra a função callback para tratamento das teclas especiais
	glutSpecialFunc(TeclasEspeciais);
	// Registra a função callback que gerencia os eventos do mouse
	glutMouseFunc(GerenciaMouse);
	// Registra a função callback chamada quando o tamanho da janela é alterado
	glutReshapeFunc(AlteraTamanhoJanela);
	// Registra a função callback que será chamada a cada intervalo de tempo
	glutTimerFunc(30, Anima, 1);
	// Chama a função responsável por fazer as inicializações
	Inicializa();
	// Inicia o processamento e aguarda interações do usuário
	glutMainLoop();

	return 0;
}

