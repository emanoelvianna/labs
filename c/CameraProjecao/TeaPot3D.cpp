// TeaPot3D.cpp
// Isabel H. Manssour e Marcelo Cohen
// Um programa OpenGL que exemplifica a visualização
// de objetos 3D.


#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdio.h>
#include <stdlib.h>

GLfloat angle, fAspect;
GLdouble obsX, obsY, obsZ; //acrescente esta linha

// Função callback chamada para fazer o desenho
void Desenha(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	glColor3f(0.0f, 0.0f, 1.0f);

	// Desenha o teapot com a cor corrente (wire-frame)
	glutWireTeapot(50.0f);

	// Executa os comandos OpenGL
	glutSwapBuffers();
 }

// Inicializa parâmetros de rendering
void Inicializa (void)
{
    // Especifica a cor de fundo
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
	// Inicializa a variável que especifica o ângulo da projeção
	// perspectiva
	// Inicializa a posição do observador
    obsX=0;
    obsY=0;
    obsZ=200;
	angle=48;
}


// Função usada para especificar o volume de visualização
void EspecificaParametrosVisualizacao(void)
{
	// Especifica sistema de coordenadas de projeção
	glMatrixMode(GL_PROJECTION);
	// Inicializa sistema de coordenadas de projeção
	glLoadIdentity();

	// Especifica a projeção perspectiva
    gluPerspective(angle,fAspect,0.2,500);

	// Especifica sistema de coordenadas do modelo
	glMatrixMode(GL_MODELVIEW);
	// Inicializa sistema de coordenadas do modelo
	glLoadIdentity();

	// Especifica posição do observador, do alvo e o vetor up
    gluLookAt(obsX,obsY,obsZ, 0,0,0, 0,1,0);
}

// Função callback chamada quando o tamanho da janela é alterado
void AlteraTamanhoJanela(GLsizei w, GLsizei h)
{
	// Para previnir uma divisão por zero
	if ( h == 0 ) h = 1;

	// Especifica o tamanho da viewport
    glViewport(0, 0, w, h);

	// Calcula a correção de aspecto
	fAspect = (GLfloat)w/(GLfloat)h;

	EspecificaParametrosVisualizacao();
}

// Função callback chamada para gerenciar eventos de teclas
void Teclado (unsigned char key, int x, int y)
{
	if (key == 27)
		exit(0);
}

// Função callback chamada para gerenciar eventos de teclas especiais (F1,PgDn,...)
void TeclasEspeciais (int tecla, int x, int y)
{
    switch (tecla) {
        case GLUT_KEY_LEFT: obsX -=10;
                             break;
        case GLUT_KEY_RIGHT:obsX +=10;
                             break;
        case GLUT_KEY_UP:   obsY +=10;
                             break;
        case GLUT_KEY_DOWN: obsY -=10;
                             break;
        case GLUT_KEY_HOME: obsZ +=10;
                             break;
        case GLUT_KEY_END : obsZ -=10;
                            break;
    }
    EspecificaParametrosVisualizacao();
	glutPostRedisplay();
}

// Função callback chamada para gerenciar eventos do mouse
void GerenciaMouse(int button, int state, int x, int y)
{
	if (button == GLUT_LEFT_BUTTON)
		if (state == GLUT_DOWN) {  // Zoom-in
			if (angle >= 10) angle -= 5;
		}
	if (button == GLUT_RIGHT_BUTTON)
		if (state == GLUT_DOWN) {  // Zoom-out
			if (angle <= 130) angle += 5;
		}
	EspecificaParametrosVisualizacao();
	glutPostRedisplay();
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
	glutCreateWindow("Visualizacao 3D");
	// Registra callback de redesenho da janela de visualização
	glutDisplayFunc(Desenha);
	// Registra a função callback para tratamento das teclas ASCII
	glutKeyboardFunc (Teclado);
    // Registra a função callback para tratamento das teclas especiais
	glutSpecialFunc (TeclasEspeciais);
	// Registra a função callback que gerencia os eventos do mouse
	glutMouseFunc(GerenciaMouse);
	// Registra a função callback de redimensionamento da janela de visualização
    glutReshapeFunc(AlteraTamanhoJanela);
	// Chama a função responsável por fazer as inicializações
	Inicializa();
	// Inicia o processamento e aguarda interações do usuário
	glutMainLoop();

	return 0;
}
