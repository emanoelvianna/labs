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
    glLineWidth(1);
    glColor3ub(255,255,255);
	glBegin(GL_LINES);
		glVertex2f(0.0,bottom);
		glVertex2f(0.0,top);
		glVertex2f(left,0);
		glVertex2f(right,0);
	glEnd();
}

void DesenhaCasa()
{
    glColor3ub(0,0,255); // Define a cor de desenho: vermelho
    glLineWidth(3); // Determina a espessura da linha que será desenhada
    glBegin(GL_TRIANGLES);
       glColor3f(0.0f, 0.0f, 1.0f);
       glVertex2f(-0.2,0.1);
       glColor3f(1.0f, 0.0f, 0.0f);
       glVertex2f(0.0,0.22);
       glColor3f(0.0f, 0.0f, 1.0f);
       glVertex2f(0.2,0.1);
    glEnd();
    glBegin(GL_LINE_LOOP);
       glColor3f(0.0f, 0.0f, 1.0f);
       glVertex2f(-0.2,0.1);
       glVertex2f(0.2,0.1);
       glVertex2f(0.2,-0.2);
       glVertex2f(-0.2,-0.2);
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
	DesenhaCasa();
	// Execução dos comandos de desenho
	glutSwapBuffers();
}

// Função callback chamada para gerenciar eventos de teclas
void Teclado (unsigned char key, int x, int y)
{
	if (key == 27)
		exit(0);
    else if (key=='l' || key=='L')
        panX+=0.1;
    else if (key=='r' || key=='R')
        panX-=0.1;
    else if (key=='t' || key=='T')
        panY-=0.1;
    else if (key=='b' || key=='B')
        panY+=0.1;
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
        case GLUT_KEY_UP:    ty+=0.1;
                             break;
    	case GLUT_KEY_DOWN:  ty-=0.1;
                             break;
    	case GLUT_KEY_LEFT:  tx-=0.1;
                             break;
        case GLUT_KEY_RIGHT: tx+=0.1;
                             break;
    	case GLUT_KEY_HOME:  left-=0.1;
                             right+=0.1;
                             top+=0.1;
                             bottom-=0.1;
                             break;
        case GLUT_KEY_END:   left+=0.1;
                             right-=0.1;
                             top-=0.1;
                             bottom+=0.1;
                             break;
    }
    glutPostRedisplay();
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
	// Registra a função callback para tratamento das teclas ASCII
	glutKeyboardFunc (Teclado);
	// Registra a função callback para tratamento das teclas especiais
	glutSpecialFunc(TeclasEspeciais);
	// Registra a função callback chamada quando o tamanho da janela é alterado
	glutReshapeFunc(AlteraTamanhoJanela);
	// Chama a função responsável por fazer as inicializações
	Inicializa();
	// Inicia o processamento e aguarda interações do usuário
	glutMainLoop();

	return 0;
}
