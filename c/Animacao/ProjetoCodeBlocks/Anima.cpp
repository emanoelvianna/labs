/**
TODO:

[] Usuário selecionar o objeto, mostrando algum diferencial no objeto selecionado
[x] Modos de exibição: por objeto
[x] Modos de exibição: por quadro-chave
[] Usuário pode aplicar rotação, translação, escala no objeto selecionado

**/
#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <iostream>
#include <cstdlib>
#include <iomanip>
#include "Point3D.h"
#include "ObjetoGrafico.h"

using namespace std;

/** Constantes **/

/** definição das constantes que identificam o objeto **/
const int CUBE = 0;
const int SPHERE = 1;
const int CONE = 2;
const int DODECAHEDRON = 3;
const int ICOSAEDRO = 4;
const int TEAPOT = 5;
const int TETRAHEDRON = 6;
const int TORUS = 7;
const int OCTAHEDRON = 8;
const int CYLINDER = 9;

/** definição dos valores utilizados para as instancias dos objetos **/
GLdouble base = 5;
GLdouble height = 5;
GLint slices = 5;
GLint stacks = 5;
GLdouble size = 5;
GLdouble innerRadius = 5;
GLdouble outerRadius = 5;
GLdouble nsides = 5;
GLdouble rings = 5;
GLdouble top = 5;
GLUquadric* quad;

const int NRO_QDO_INTERMEDIARIOS = 180;

/** Variáveis **/
GLfloat angle, fAspect, rotX, rotY, obsZ;
GLfloat ratio;
GLint quadro;
GLint quadroChave;
GLint QuadroAnterior;
GLint QuadroSeguinte;


/** utilizado para auxiliar para modos de exibição: por objeto**/
int posicao = 0;

/** utilizado para auxiliar para modo de edição **/
int objSelecionado = 0;

/** guarda os objetos graficos estanciados pelo o usuário **/
ObjetoGrafico* lista[10];

void inicializarLista() {

    lista[0] = new ObjetoGrafico(CUBE);
    Point3D* p0 = new Point3D(0, 0, 10);
    lista[0]->SetTranslacao(*p0);
    lista[0]->SetPosAltura(-9);
    lista[0]->SetPosInicial(-10);
    lista[0]->SetTX1(-50);
    lista[0]->SetTX2(40);
    lista[0]->SetRY1(0);
    lista[0]->SetRY2(360);
    lista[0]->SetTY(-40);

    lista[1] = new ObjetoGrafico(SPHERE);
    Point3D* p1 = new Point3D(0, 0, 10);
    lista[1]->SetTranslacao(*p1);
    lista[1]->SetPosAltura(9);
    lista[1]->SetPosInicial(-20);
    lista[1]->SetTX1(-50);
    lista[1]->SetTX2(40);
    lista[1]->SetRY1(0);
    lista[1]->SetRY2(360);
    lista[1]->SetTY(-80);
    lista[1]->SetColor(1, 1, 1, 1);

    lista[2] = new ObjetoGrafico(CONE);
    Point3D* p2 = new Point3D(0, 0, 10);
    lista[2]->SetTranslacao(*p2);
    lista[2]->SetPosAltura(14);
    lista[2]->SetPosInicial(-30);
    lista[2]->SetTX1(-50);
    lista[2]->SetTX2(40);
    lista[2]->SetRY1(0);
    lista[2]->SetRY2(360);
    lista[2]->SetTY(30);
    lista[2]->SetColor(1, 1, 1, 1);

    lista[3] = new ObjetoGrafico(DODECAHEDRON);
    Point3D* p3 = new Point3D(0, 0, 10);
    lista[3]->SetTranslacao(*p3);
    lista[3]->SetPosAltura(14);
    lista[3]->SetPosInicial(10);
    lista[3]->SetPosInicial(-10);
    lista[3]->SetTX1(-50);
    lista[3]->SetTX2(40);
    lista[3]->SetRY1(0);
    lista[3]->SetRY2(360);
    lista[3]->SetTY(-10);
    lista[3]->SetColor(1, 1, 1, 1);

    lista[4] = new ObjetoGrafico(ICOSAEDRO);
    Point3D* p4 = new Point3D(0, 0, 10);
    lista[4]->SetTranslacao(*p4);
    lista[4]->SetPosAltura(14);
    lista[4]->SetPosInicial(10);
    lista[4]->SetPosInicial(-10);
    lista[4]->SetTX1(-50);
    lista[4]->SetTX2(40);
    lista[4]->SetRY1(0);
    lista[4]->SetRY2(360);
    lista[4]->SetTY(-20);
    lista[4]->SetColor(1, 1, 1, 1);

    lista[5] = new ObjetoGrafico(TEAPOT);
    Point3D* p5 = new Point3D(0, 0, 10);
    lista[5]->SetTranslacao(*p5);
    lista[5]->SetPosAltura(14);
    lista[5]->SetPosInicial(10);
    lista[5]->SetPosInicial(-10);
    lista[5]->SetTX1(-50);
    lista[5]->SetTX2(40);
    lista[5]->SetRY1(0);
    lista[5]->SetRY2(360);
    lista[5]->SetTY(-60);
    lista[5]->SetColor(1, 1, 1, 1);

    lista[6] = new ObjetoGrafico(TETRAHEDRON);
    Point3D* p6 = new Point3D(0, 0, 10);
    lista[6]->SetTranslacao(*p6);
    lista[6]->SetPosAltura(14);
    lista[6]->SetPosInicial(10);
    lista[6]->SetPosInicial(-10);
    lista[6]->SetTX1(-50);
    lista[6]->SetTX2(40);
    lista[6]->SetRY1(0);
    lista[6]->SetRY2(360);
    lista[6]->SetTY(-30);
    lista[6]->SetColor(1, 1, 1, 1);

    lista[7] = new ObjetoGrafico(TORUS);
    Point3D* p7 = new Point3D(0, 0, 10);
    lista[7]->SetTranslacao(*p7);
    lista[7]->SetPosAltura(14);
    lista[7]->SetPosInicial(10);
    lista[7]->SetPosInicial(-10);
    lista[7]->SetTX1(-50);
    lista[7]->SetTX2(40);
    lista[7]->SetRY1(0);
    lista[7]->SetRY2(360);
    lista[7]->SetTY(5);
    lista[7]->SetColor(1, 1, 1, 1);

    lista[8] = new ObjetoGrafico(OCTAHEDRON);
    Point3D* p8 = new Point3D(0, 0, 10);
    lista[8]->SetTranslacao(*p8);
    lista[8]->SetPosAltura(14);
    lista[8]->SetPosInicial(10);
    lista[8]->SetPosInicial(-10);
    lista[8]->SetTX1(-50);
    lista[8]->SetTX2(40);
    lista[8]->SetRY1(0);
    lista[8]->SetRY2(360);
    lista[8]->SetTY(39);
    lista[8]->SetColor(1, 1, 1, 1);

    lista[9] = new ObjetoGrafico(CYLINDER);
    Point3D* p9 = new Point3D(0, 0, 10);
    lista[9]->SetTranslacao(*p9);
    lista[9]->SetPosAltura(14);
    lista[9]->SetPosInicial(10);
    lista[9]->SetPosInicial(-10);
    lista[9]->SetTX1(-50);
    lista[9]->SetTX2(40);
    lista[9]->SetRY1(0);
    lista[9]->SetRY2(360);
    lista[9]->SetTY(50);
    lista[9]->SetColor(1, 1, 1, 1);
}

/** Função responsável pela especificação dos parâmetros de iluminação **/
void DefineIluminacao (void)
{
    /** Define cores para um objeto dourado **/
    GLfloat LuzAmbiente[]   = {0.24725f, 0.1995f, 0.07f } ;
    GLfloat LuzDifusa[]   = {0.75164f, 0.60648f, 0.22648f, 1.0f };
    GLfloat LuzEspecular[] = {0.626281f, 0.555802f, 0.366065f, 1.0f };
    GLfloat PosicaoLuz0[]  = {3.0f, 3.0f, 0.0f, 1.0f };
    GLfloat PosicaoLuz1[]  = {-3.0f, -3.0f, 0.0f, 1.0f };
    GLfloat Especularidade[] = {1.0f, 1.0f, 1.0f, 1.0f };

    /** Ativa o "Color Tracking" **/
	glEnable (GL_COLOR_MATERIAL);

    // Habilita o uso de iluminação
    glEnable(GL_LIGHTING);

    // Ativa o uso da luz ambiente
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, LuzAmbiente);

    // Define os parametros da Luz número Zero
    glLightfv(GL_LIGHT0, GL_AMBIENT, LuzAmbiente);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, LuzDifusa  );
    glLightfv(GL_LIGHT0, GL_SPECULAR, LuzEspecular  );
    glLightfv(GL_LIGHT0, GL_POSITION, PosicaoLuz0 );
    glEnable(GL_LIGHT0);

    // Define a reflectancia do material
    glMaterialfv(GL_FRONT,GL_SPECULAR, Especularidade);

    // Define a concentração do brilho.
    // Quanto maior o valor do Segundo parametro, mais
    // concentrado será o brilho. (Valores válidos: de 0 a 128)
    glMateriali(GL_FRONT,GL_SHININESS,51);

    // Define os parametros da Luz número Um
    glLightfv(GL_LIGHT1, GL_AMBIENT, LuzAmbiente);
    glLightfv(GL_LIGHT1, GL_DIFFUSE, LuzDifusa  );
    glLightfv(GL_LIGHT1, GL_SPECULAR, LuzEspecular  );
    glLightfv(GL_LIGHT1, GL_POSITION, PosicaoLuz1 );
    glEnable(GL_LIGHT1);

    // Define a reflectancia do material
    glMaterialfv(GL_FRONT,GL_SPECULAR, Especularidade);

    // Define a concentração do brilho.
    // Quanto maior o valor do Segundo parametro, mais
    // concentrado será o brilho. (Valores válidos: de 0 a 128)
    glMateriali(GL_FRONT,GL_SHININESS,20);
}

/** Função usada para especificar a posição do observador virtual **/
void PosicionaObservador(void)
{
	/** Especifica sistema de coordenadas do modelo **/
	glMatrixMode(GL_MODELVIEW);
	/** Inicializa sistema de coordenadas do modelo **/
	glLoadIdentity();

	/** Especifica posição do observador e do alvo **/
	glTranslatef(0,0,-obsZ);
	glRotatef(rotX,1,0,0);
	glRotatef(rotY,0,1,0);
}

// Função usada para especificar o volume de visualização
void EspecificaParametrosVisualizacao(void)
{
	// Especifica sistema de coordenadas de projeção
	glMatrixMode(GL_PROJECTION);
	// Inicializa sistema de coordenadas de projeção
	glLoadIdentity();

	// Especifica a projeção perspectiva(angulo,aspecto,zMin,zMax)
	gluPerspective(angle,fAspect,0.5,500);

	PosicionaObservador();
}

/** Função que desenha um objeto **/
void DesenhaObjeto(int obj)
{
    switch (obj)
    {
        case CUBE: glutSolidCube(2);
                break;
        /** glutSolidSphere(radius, slices, stacks) **/
        case SPHERE: glutSolidSphere(5, 10, 20);
                break;
        /** glutSolidCone(base ,height ,slices, stacks) **/
        case CONE: glutSolidCone(4 ,height ,slices, stacks);
                break;
        case DODECAHEDRON: glutSolidDodecahedron();
                break;
        case ICOSAEDRO: glutSolidIcosahedron();
                break;
        case TEAPOT: glutSolidTeapot(size);
                break;
        case TETRAHEDRON: glutSolidTetrahedron();
                break;
        case TORUS: glutSolidTorus(innerRadius, outerRadius, nsides, rings);
                break;
        case OCTAHEDRON: glutSolidOctahedron();
                break;
        /** gluCylinder(GLUquadric* quad, GLdouble base,GLdouble top,GLdouble height,GLint slices,GLint stacks); **/
        case CYLINDER: gluCylinder(quad, base, top, height, slices, stacks);
                break;
    }
}

/** Função que desenha os objetos no quadro especificado por parâmetro **/
void DesenhaObjetoNoQuadro (int quadrocorrente)
{
    for(int i = 0 ; i < 10; i++){
        float TX, TY, TZ, RX, RY, RZ;

        /** Calcula o valor da translação e rotação no quadro corrente **/
        TX = (lista[i]->getTX2()-lista[i]->getTX1()) / NRO_QDO_INTERMEDIARIOS * quadrocorrente + lista[i]->getTX1();
        RY = (lista[i]->getRY2()-lista[i]->getRY1()) / NRO_QDO_INTERMEDIARIOS * quadrocorrente + lista[i]->getRY1();
        TY = lista[i]->getTY();

        /** fazer o mesmo para TY, TZ, RX e RZ **/
        TZ=RX=RZ=0;

        // desenha o objeto
        glPushMatrix();
            glColor4f(lista[i]->getRed(), lista[i]->getGreen(), lista[i]->getBlue(), lista[i]->getAlpha());
            glTranslatef(TX, TY, TZ);
            glRotatef(RX, 1,0,0);
            glRotatef(RY, 0,1,0);
            glRotatef(RX, 0,0,1);
            DesenhaObjeto(lista[i]->getTipoDoObjeto());
        glPopMatrix();
    }
}

/** Função que desenha todos os quadros do objeto selecionado **/
void DesenhaQuadrosDoObjetoSelecionado (int quadrocorrente, int p)
{
    float TX, TY, TZ, RX, RY, RZ;
    /** Calcula o valor da translação e rotação no quadro corrente **/
    TX = (lista[p]->getTX2()-lista[p]->getTX1()) / NRO_QDO_INTERMEDIARIOS * quadrocorrente + lista[p]->getTX1();
    RY = (lista[p]->getRY2()-lista[p]->getRY1()) / NRO_QDO_INTERMEDIARIOS * quadrocorrente + lista[p]->getRY1();
    TY = lista[p]->getTY();

    /** fazer o mesmo para TY, TZ, RX e RZ **/
    TZ=RX=RZ=0;
    glColor4f(1, 1, 1, 1);
    glPushMatrix();
        glColor4f(lista[p]->getRed(), lista[p]->getGreen(), lista[p]->getBlue(), lista[p]->getAlpha());
        glTranslatef(TX, TY, TZ);
        glRotatef(RX, 1,0,0);
        glRotatef(RY, 0,1,0);
        glRotatef(RX, 0,0,1);
        DesenhaObjeto(lista[p]->getTipoDoObjeto());
    glPopMatrix();
}

/** Função callback chamada para fazer o desenho **/
void Desenha()
{
	static double angY = 0;

	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

	DefineIluminacao();
	EspecificaParametrosVisualizacao();

    for(int i = 0; i < 10; i++) {
        DesenhaObjetoNoQuadro(quadro);
    }
    // se não exibiu todos os quadros intermediários, passa para o próximo
	if (quadro < NRO_QDO_INTERMEDIARIOS)
	   quadro++;
 	else quadro=0; /** aqui o correto será avançar os nros dos Quadro-Chave **/

	/** Executa os comandos OpenGL **/
	glutSwapBuffers();
 }

/** um objeto, em todos os seus quadros-chave **/
 void QuadrosChavesDeTodosOsObjetos()
{
	static double angY = 0;

	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

	DefineIluminacao();
	EspecificaParametrosVisualizacao();

	DesenhaObjetoNoQuadro (quadroChave);

	glutSwapBuffers();
 }
/** mostra todos os quadros chaves dos objetos **/
void QuadrosChavesDoObjeto() {
    static double angY = 0;

	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

	DefineIluminacao();
	EspecificaParametrosVisualizacao();

    for(int i = 0; i < 10; i++) {
        DesenhaQuadrosDoObjetoSelecionado(i*20, posicao);
    }
	glutSwapBuffers();
}

/** Inicializa parâmetros de rendering **/
void Inicializa (void)
{
    /** usado para desenhar o cilindro **/
    quad = gluNewQuadric();
    gluQuadricNormals(quad, GLU_SMOOTH);

	glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Fundo de tela preto

	/** Inicializa as variáveis usadas para alterar a posição do **/
	/** observador virtual **/
	rotX = 30;
	rotY = 0;
	obsZ = 250;

	/** Inicializa a variável que especifica o ângulo da projeção **/
	/** perspectiva **/
	angle=30;

    quadro = 0;
    QuadroAnterior = 1;
    QuadroSeguinte = 2;

	glShadeModel(GL_SMOOTH);
	glColorMaterial (GL_FRONT, GL_AMBIENT_AND_DIFFUSE);
	glEnable(GL_DEPTH_TEST);
	glEnable (GL_CULL_FACE);
}

// Função callback chamada quando o tamanho da janela é alterado
void AlteraTamanhoJanela(GLsizei w, GLsizei h)
{
	// Para previnir uma divisão por zero
	if ( h == 0 ) h = 1;

	// Especifica as dimensões da viewport
	glViewport(0, 0, w, h);

	// Calcula a correção de aspecto
	fAspect = (GLfloat)w/(GLfloat)h;

	EspecificaParametrosVisualizacao();
}

/** Função callback chamada para gerenciar eventos de teclas **/
void Teclado (unsigned char tecla, int x, int y)
{
	if(tecla==27) /** ESC ? **/
		exit(0);
    if(tecla=='0') /** cria o primeiro quadro chave **/
        quadroChave = 0;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='1') /** cria o primeiro quadro chave **/
        quadroChave = 20;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='2') /** cria o primeiro quadro chave **/
        quadroChave = 40;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='3') /** cria o primeiro quadro chave **/
        quadroChave = 60;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='4') /** cria o primeiro quadro chave **/
        quadroChave = 80;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='5') /** cria o primeiro quadro chave **/
        quadroChave = 100;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='6') /** cria o primeiro quadro chave **/
        quadroChave = 120;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='7') /** cria o primeiro quadro chave **/
        quadroChave = 140;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='8') /** cria o primeiro quadro chave **/
        quadroChave = 160;
    QuadrosChavesDeTodosOsObjetos();
    if(tecla=='9') /** cria o primeiro quadro chave **/
        quadroChave = 180;
    QuadrosChavesDeTodosOsObjetos();
    /**
        TODO: ainda é preciso fazer para os outros quadros: 0'...'9'.
    **/
}

/** Função callback chamada pela GLUT a cada intervalo de tempo **/
void Anima(int value)
{
	// Faz o redesenho
	glutPostRedisplay();
	glutTimerFunc(60,Anima, 1);
}

/** realiza a edição do objeto **/
void edicao(int quadrocorrente) {
     for(int i = 0 ; i < 10; i++){
        if(i == objSelecionado)
            /** seta diferente **/
            lista[i]->SetColor(0, 1, 0, 1);

        float TX, TY, TZ, RX, RY, RZ;

        /** Calcula o valor da translação e rotação no quadro corrente **/
        TX = (lista[i]->getTX2()-lista[i]->getTX1()) / NRO_QDO_INTERMEDIARIOS * quadrocorrente + lista[i]->getTX1();
        RY = (lista[i]->getRY2()-lista[i]->getRY1()) / NRO_QDO_INTERMEDIARIOS * quadrocorrente + lista[i]->getRY1();
        TY = lista[i]->getTY();

        /** fazer o mesmo para TY, TZ, RX e RZ **/
        TZ=RX=RZ=0;

        // desenha o objeto
        glPushMatrix();
            glColor4f(lista[i]->getRed(), lista[i]->getGreen(), lista[i]->getBlue(), lista[i]->getAlpha());
            glTranslatef(TX, TY, TZ);
            glRotatef(RX, 1,0,0);
            glRotatef(RY, 0,1,0);
            glRotatef(RX, 0,0,1);
            DesenhaObjeto(lista[i]->getTipoDoObjeto());
        glPopMatrix();
    }
    /** utilizado para retornar a cor padrão, quando objeto deselecionar **/
    inicializarLista();
}

/** função para selecionar objeto **/
void selecionaObjeto() {
	static double angY = 0;

	glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );

	DefineIluminacao();
	EspecificaParametrosVisualizacao();

    for(int i = 0; i < 10; i++) {
        edicao(quadro);
    }
    // se não exibiu todos os quadros intermediários, passa para o próximo
	if (quadro < NRO_QDO_INTERMEDIARIOS)
	   quadro++;
 	else quadro=0; /** aqui o correto será avançar os nros dos Quadro-Chave **/

	/** Executa os comandos OpenGL **/
	glutSwapBuffers();
}

/** Função callback chamada para gerenciar eventos de teclas especiais (F1,PgDn,...) **/
void TeclasEspeciais (int tecla, int x, int y)
{
	switch (tecla)
	{

		case GLUT_KEY_LEFT:	rotY--;
							break;
		case GLUT_KEY_RIGHT:rotY++;
							break;
		case GLUT_KEY_UP:	rotX++;
							break;
		case GLUT_KEY_DOWN:	rotX--;
							break;
        /** Modos de Exibição: Por quadro-chave **/
		case GLUT_KEY_HOME:	glutDisplayFunc(QuadrosChavesDoObjeto);
							break;
        /** Modos de Exibição: Por objeto **/
		case GLUT_KEY_END:	glutDisplayFunc(QuadrosChavesDeTodosOsObjetos);
							break;
        /** Modo de animação **/
		case GLUT_KEY_F1:   glutDisplayFunc(Desenha);
                            break;
        /** seleção do objeto modos de exibição: Por objeto **/
        case GLUT_KEY_F11:
                            if(posicao > 0)
                                posicao = posicao - 1;
                            break;
        /** seleção do objeto modos de exibição: Por objeto **/
        case GLUT_KEY_F12:
                            if(posicao < 9)
                                posicao = posicao + 1;
                            break;
        /** seleção do objeto modos de edição **/
        case GLUT_KEY_F9:
                            if(objSelecionado > 0){
                                objSelecionado = objSelecionado - 1;
                                glutDisplayFunc(selecionaObjeto);
                            }
                            break;
        /** seleção do objeto modos de edição **/
        case GLUT_KEY_F10:
                            if(objSelecionado < 9){
                                objSelecionado = objSelecionado + 1;
                                glutDisplayFunc(selecionaObjeto);
                            }
                            break;
	}
	PosicionaObservador();
}

/** Função callback chamada para gerenciar eventos do mouse **/
void GerenciaMouse(int button, int state, int x, int y)
{
	if (button == GLUT_LEFT_BUTTON)
		if (state == GLUT_DOWN)   // Zoom in
			if (angle >= 10) angle -= 5;

	if (button == GLUT_RIGHT_BUTTON)
		if (state == GLUT_DOWN)   // Zoom out
			if (angle <= 130) angle += 5;

	EspecificaParametrosVisualizacao();
}

/** Programa Principal **/
int main(void)
{
    cout << "--------\n";
    cout << " F1: modo de animacao. \n";
    cout << " F10: modo de edicao, utilize F9 e F10 seleciona objeto, \n \t 'r': rotacaoo; 'e': escala; 't': translacao. \n";
    cout << " HOME: modo de exibicao por objeto, F11 e F12 para selecionar o objeto. \n";
    cout << " END: modo de exibicao por quadro-chave, 0 a 9 para selecionar o quadro-chave. \n";

    cout << "--------\n";

    inicializarLista();
    int argc = 0;
	char *argv[] = { (char *)"gl", 0 };

	glutInit(&argc,argv);

	// Define do modo de operação da GLUT
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	// Especifica o tamanho inicial em pixels da janela GLUT
	glutInitWindowSize(800,500);
	// Cria a janela passando como argumento o título da mesma
	glutCreateWindow("Animacao por Quadros Chave");
	// Registra callback de redesenho da janela de visualização
	glutDisplayFunc(Desenha);
	/** Registra a função callback para tratamento das teclas ASCII **/
	glutKeyboardFunc (Teclado);
    /** Registra a função callback para tratamento das teclas especiais **/
	glutSpecialFunc (TeclasEspeciais);
	// Registra a função callback que gerencia os eventos do mouse
	glutMouseFunc(GerenciaMouse);
	// Registra a função callback que será chamada a cada intervalo de tempo
	glutTimerFunc(60, Anima, 1);
	// Registra a função callback de redimensionamento da janela de visualização
    glutReshapeFunc(AlteraTamanhoJanela);
	// Chama a função responsável por fazer as inicializações
	Inicializa();
	// Inicia o processamento e aguarda interações do usuário
	glutMainLoop();

	return 0;
}
