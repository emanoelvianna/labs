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

/** struct de nodos **/
struct Nodo{
       int classificacao;
       string pais;
       float consumo;
 };

 /** declarando o vetor de struct como const **/
struct Nodo nodos[35];

const char text = 'teste';

void drawBitmapText(char *string,float x,float y,float z)
{
	char *c;
	glRasterPos3f(x, y,z);

	for (c=string; *c != '\0'; c++)
	{
		glutBitmapCharacter(GLUT_BITMAP_TIMES_ROMAN_10, *c);
	}
}

void drawStrokeText(char *string,int x,int y,int z)
{
	  char *c;
	  glPushMatrix();
	  glTranslatef(x, y+8,z);
      glScalef(0.09f,-0.08f,z);

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

void reshape(int w,int h)
{

    glViewport(0,0,w,h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(0,w,h,0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

}


void render(void)
{

        glClear(GL_COLOR_BUFFER_BIT);
	glLoadIdentity();

	glColor3f(1,1,0);

    string STRING;
        ifstream infile;
        infile.open ("dados.csv");
        int cont = 0;
        while(!infile.eof() && cont < 35) // To get you all the lines.
        {
            string text;
            getline(infile,STRING); // Saves the line in STRING.
            vector<string> aux = split(STRING, ';');
            std::istringstream(aux[0]) >> nodos[cont].classificacao; //nodos[cont].classificacao = aux[0];
            nodos[cont].pais = aux[1];
            text = aux[1];
            /** chama a função para escrever na tela **/
            drawStrokeText(const_cast<char*>(nodos[cont].pais.c_str()),200,200,0);

            cont++;
        }
        infile.close();





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
        glutInitWindowSize(500,500);

        // the position where the window will appear
        glutInitWindowPosition(100,100);
        glutCreateWindow("Trabalho computação grafica I");

        glutDisplayFunc(render);
        glutIdleFunc(render);
        glutReshapeFunc(reshape);

        // enter the main loop
        glutMainLoop();
	return 0;
}
