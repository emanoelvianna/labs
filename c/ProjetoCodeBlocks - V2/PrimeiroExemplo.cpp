#include<GL/glut.h>

#include <stdio.h>
#include <stdlib.h>

#include <conio.h>
#include <iostream>
#include <fstream>

#include <string>
#include <sstream>
#include <vector>
#include <iomanip>

#pragma comment (lib,"opengl32.lib")
#pragma comment (lib,"glu32.lib")
#pragma comment (lib,"glut32.lib")

using namespace std;

GLfloat tx, ty;
GLfloat lef, righ, bottom, top;
GLfloat panX, panY;
GLint largura, altura;

/** utilizado para a leitura do arquivo **/
std::vector<std::string> &split(const std::string &s, char delim, std::vector<std::string> &elems) {
    std::stringstream ss(s);
    std::string item;
    while (std::getline(ss, item, delim)) {
        elems.push_back(item);
    }
    return elems;
}

std::vector<std::string> split(const std::string &s, char delim) {
    std::vector<std::string> elems;
    split(s, delim, elems);
    return elems;
}

/** utilizado no mecanismo para o zoom **/
double rotate_by_key = 0;
double rotate_x = 0.5;

/** struct de nodos **/
struct Nodo{
       int classificacao;
       string pais;
       float consumo;
       int posicaoX;
       int posicaoY;
       float tamanho;
 };

 /** declarando o vetor de struct como const **/
struct Nodo nodos[35];

/** struct de informações para cada nodo **/
struct Informacoes{
    float corX;
    float corY;
    float corZ;
};

/** declara o vetor de struct como const **/
struct Informacoes info[35];

/** utilizado para escrever **/
void drawStrokeText(char *string,int x,int y,int z, float tamanho)
{
      char *c;
      glPushMatrix();
      glTranslatef(x, y+8,z);   /** define a posição **/
      glScalef(tamanho,-tamanho, z);    /** define o tamanho **/
      //glScalef(0.19f,-0.18f, z);    /** define o tamanho **/

      for (c=string; *c != '\0'; c++)
      {
            glutStrokeCharacter(GLUT_STROKE_ROMAN , *c);
      }
      glPopMatrix();
}

void init()
{
	glClearColor(0.0,0.0,0.0,0.0);
}


/** controla a window **/
void handleResize(int w, int h) {

    // Para previnir uma divisão por zero
	if ( h == 0 ) h = 1;

    //Tell OpenGL how to convert from coordinates to pixel values
    glViewport(0, 0, w, h);

    glMatrixMode(GL_PROJECTION); //Switch to setting the camera perspective

    //Set the camera perspective
    glLoadIdentity(); //Reset the camera
    gluPerspective(45.0,                  //The camera angle
                   (double)w / (double)h, //The width-to-height ratio
                   1.0,                   //The near z clipping coordinate
                   200.0);                //The far z clipping coordinate
    glLoadIdentity();
    //gluOrtho2D(left+panX, right+panX, bottom+panY, top+panY);
    gluOrtho2D(0,w,h,0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glViewport(0, 0, w, h);
}

int randomInteger (float low, float high)
{
    int k;
    double d;
    d = (double) rand () / ((double) RAND_MAX + 1);
    k = d * (high - low + 1);
    return low + k;
}

/** incializa as cores **/
void inicializaCor() {
    int cont = 1;
    while(cont < 15) {
        /** define as cores **/
        info[cont].corX = randomInteger(-4.0f, 10.0f);
        info[cont].corY = randomInteger(-4.0f, 10.0f);
        info[cont].corZ = randomInteger(-4.0f, 10.0f);
        cont++;
    }
}


void keyPress(int key,int x,int y)
{
    if(key==27)
        exit(0);
    /** zoom **/
    if (key == GLUT_KEY_F10)
        rotate_x += .05;
    if (key == GLUT_KEY_F11)
        rotate_x -= .05;
    /** pan **/
    if (key == GLUT_KEY_LEFT){
        lef += 5.0;
        righ += 5.0;
    }
    if (key == GLUT_KEY_RIGHT){
        lef -= 5.0;
        righ -= 5.0;
    }
    if (key == GLUT_KEY_UP){
        top += 5.0;
        bottom += 5.0;
    }
    if (key == GLUT_KEY_DOWN){
        top -= 5.0;
        bottom -= 5.0;
    }
    if(key == GLUT_KEY_HOME)
        inicializaCor();
    glutPostRedisplay();
}



void lerArquivo() {
    string STRING;
    ifstream infile;
    infile.open ("dados.csv");
    int cont = 0;
    while(!infile.eof() && cont < 17) // To get you all the lines.
    {
        getline(infile,STRING); // Saves the line in STRING.
        vector<string> aux = split(STRING, ';');
        std::istringstream(aux[0]) >> nodos[cont].classificacao; //nodos[cont].classificacao = aux[0];
        nodos[cont].pais = aux[1];
        std::istringstream(aux[2]) >> nodos[cont].consumo;
        std::istringstream(aux[3]) >> nodos[cont].posicaoX;
        std::istringstream(aux[4]) >> nodos[cont].posicaoY;
        std::istringstream(aux[5]) >> nodos[cont].tamanho;

        /** chama a função para escrever na tela **/
        drawStrokeText(const_cast<char*>(nodos[cont].pais.c_str()),nodos[cont].posicaoX,nodos[cont].posicaoY,0, nodos[cont].tamanho);
        glColor3f(info[cont].corX, info[cont].corY, info[cont].corZ);
        cont++;
    }
    infile.close();
};

void render(void)
{

    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

    glMatrixMode(GL_MODELVIEW);

    glLoadIdentity();

    glScalef( rotate_x,rotate_x,1.0f );
    //glScalef(1.0f,1.0f,rotate_x);
    glRotatef(  rotate_by_key,-1.0f, 1.5f, -5.0f );

    gluOrtho2D(lef+panX, righ+panX, bottom+panY, top+panY);

    int cont = 1;
    while(cont < 15) // To get you all the lines.
    {
       /** chama a função para escrever na tela **/
        drawStrokeText(const_cast<char*>(nodos[cont].pais.c_str()),nodos[cont].posicaoX,nodos[cont].posicaoY,0, nodos[cont].tamanho);
        glColor3f(info[cont].corX, info[cont].corY, info[cont].corZ);
        cont++;
    }
    //drawStrokeText(const_cast<char*>(nodos[1].pais.c_str()),200,300,0);

    glutSwapBuffers();

}


void inicializaInformacoes() {

    lef = -1.0;
    righ = 1.0;
    bottom = -1.0;
    top = 1.0;
    panX = 1.0;
    panY = 1.0;
}

int main(int argc, char* argv[])
{
		cout << "----LISTA DE COMANDOS:----\n";
		cout << "-- ZOMM + : F9 \n-- ZOOM - : F10\n";
		cout << "-- PAM DIREITA: SETA DIREITA\n-- PAM ESQUERDA: SETA ESQUERDA\n";
		cout << "-- MUDAR A COR: HOME\n";
		cout << "-- ELEVAR O TEXTO: SETA PARA CIMA\n";
		cout << "-- ABAIXAR O TEXTO: SETA PARA BAIXO\n";
		// initialize glut
        glutInit(&argc, argv);
        inicializaCor();
        inicializaInformacoes();

       /** importante, leitura de arquivo e incialização de tmanhos e cores **/
        lerArquivo();
        glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);

        glutInitWindowSize(650,300);

        glutInitWindowPosition(250,250);
        glutCreateWindow("Trabalho computação grafica I");

        glutDisplayFunc(render);

        glutIdleFunc(render);

        glutSpecialFunc(keyPress);

        glutReshapeFunc(handleResize);

        glutMainLoop();

        return 0;
}
