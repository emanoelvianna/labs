#include<GL/glut.h>

#include <stdio.h>
#include <stdlib.h>

#include <conio.h>
#include <iostream>
#include <fstream>

#include <string>
#include <sstream>
#include <vector>


#pragma comment (lib,"opengl32.lib")
#pragma comment (lib,"glu32.lib")
#pragma comment (lib,"glut32.lib")

using namespace std;

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
       int tamanho;
 };

 /** declarando o vetor de struct como const **/
struct Nodo nodos[35];

/** utilizado para escrever **/
void drawBitmapText(char *string,float x,float y,float z)
{
	char *c;
	glRasterPos3f(x, y,z);

	for (c=string; *c != '\0'; c++)
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_10, *c);
	}
}

/** utilizado para escrever **/
void drawStrokeText(char *string,int x,int y,int z)
{
      char *c;
      glPushMatrix();
      glTranslatef(x, y+8,z);   /** define a posição **/
      glScalef(0.50f,-0.50f, z);    /** define o tamanho **/
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
    gluOrtho2D(0,w,h,0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
}

void keyPress(int key,int x,int y)
{

    if(key==27)
          exit(0);
    if (key == GLUT_KEY_UP)
            rotate_x += .05;
        if (key == GLUT_KEY_DOWN)
            rotate_x -= .05;

    glutPostRedisplay();

}

int randomInteger (int low, int high)
{
    int k;
    double d;
    d = (double) rand () / ((double) RAND_MAX + 1);
    k = d * (high - low + 1);
    return low + k;
}

void render(void)
{

    glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

    glMatrixMode(GL_MODELVIEW);

    glLoadIdentity();

    glScalef( rotate_x,rotate_x,1.0f );
    //glScalef(1.0f,1.0f,rotate_x);
    glRotatef(  rotate_by_key,-1.0f, 1.5f, -5.0f );

	glColor3f(1,1,0);

    string STRING;
    ifstream infile;
    infile.open ("dados.csv");
    int cont = 1;
    while(!infile.eof() && cont < 16) // To get you all the lines.
    {
        getline(infile,STRING); // Saves the line in STRING.
        vector<string> aux = split(STRING, ';');
        std::istringstream(aux[0]) >> nodos[cont].classificacao; //nodos[cont].classificacao = aux[0];
        nodos[cont].pais = aux[1];
        std::istringstream(aux[2]) >> nodos[cont].consumo;
        std::istringstream(aux[3]) >> nodos[cont].posicaoX;
        std::istringstream(aux[4]) >> nodos[cont].posicaoY;

        /** chama a função para escrever na tela **/
        drawStrokeText(const_cast<char*>(nodos[cont].pais.c_str()),nodos[cont].posicaoX,nodos[cont].posicaoY,0);

        cont++;
    }
    infile.close();

    //drawStrokeText(const_cast<char*>(nodos[1].pais.c_str()),200,300,0);
    glutSwapBuffers();

}

int main(int argc, char* argv[])
{
		// initialize glut
        glutInit(&argc, argv);

        // specify the display mode to be RGB and single buffering
        // we use single buffering since this will be non animated
        glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE);

        // define the size
        glutInitWindowSize(500,300);

        // the position where the window will appear
        glutInitWindowPosition(100,100);
        glutCreateWindow("Trabalho computação grafica I");

        glutDisplayFunc(render);

        glutIdleFunc(render);

        glutSpecialFunc(keyPress);

        glutReshapeFunc(handleResize);
        // enter the main loop
        glutMainLoop();

        return 0;
}
