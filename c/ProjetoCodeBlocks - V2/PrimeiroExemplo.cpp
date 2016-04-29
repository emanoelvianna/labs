#ifdef __APPLE__
#include <GLUT/glut.h>
#else
#include <GL/glut.h>
#endif

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>    /* Biblioteca necessária para uso de configurações regionais. */

#include <conio.h>
#include <iostream>
#include <fstream>

#include <string>
#include <sstream>
#include <vector>

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


 struct Nodo{
      int classificacao;
      string pais;
      float consumo;
};

//void leArquivoDeDados() {
//
//
//    struct Nodo nodo[35];
//
//    FILE *f = fopen("dados.csv","r");
//
//    int x;
//    for(x=0;x<35/*tamanho do vetor*/;x++){
//    /* %d - lê um inteiro
//       %30[^;] - lê os bytes até o caractere ';'
//       %f - lê um float
//    */
//        fscanf(f,"%d ; %30 [ ^ ; ] ; %f\n", &nodo[x].classificacao, nodo[x].pais, &nodo[x].consumo); // lê os dados e armazena em nodo
//        printf("%d - %s - %f\n", nodo[x].classificacao, nodo[x].pais, nodo[x].consumo);
//    }
//}

void drawText(const char *text, int length, int x, int y) {
	glMatrixMode(GL_PROJECTION);
    double *matrix = new double[16];
    glGetDoublev(GL_PROJECTION_MATRIX, matrix);
    glLoadIdentity();
    glOrtho(0, 800, 0, 600, -5, 5);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glPushMatrix();
    glLoadIdentity();
    glRasterPos2i(x, y);
    for(int i= 0; i < length; i++) {
        glutBitmapCharacter(GLUT_BITMAP_9_BY_15, (int)text[i]);
    }
    glPopMatrix();
    glMatrixMode(GL_PROJECTION);
    glLoadMatrixd(matrix);
    glMatrixMode(GL_MODELVIEW);
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

    std::string text;
	text = "ola mundo!";
	drawText(text.data(), text.size(), 0, 0);


	// Execução dos comandos de desenho
	glutSwapBuffers();
}

// Função callback chamada para gerenciar eventos de teclas
void Teclado (unsigned char key, int x, int y){
	if (key == 27)
		exit(0);
}

// Função responsável por inicializar parâmetros e variáveis
void Inicializa(void) {
	// Define a janela de visualização 2D
	glMatrixMode(GL_PROJECTION);
	gluOrtho2D(-1.0,1.0,-1.0,1.0);
	glMatrixMode(GL_MODELVIEW);
}



// Programa Principal
int main(void)
{
    setlocale(LC_ALL, "Portuguese");
    struct Nodo nodos[35];
    string STRING;
	ifstream infile;
	infile.open ("dados.csv");
	int cont = 0;
    while(!infile.eof() && cont < 35) // To get you all the lines.
    {
        getline(infile,STRING); // Saves the line in STRING.
        vector<string> aux = split(STRING, ';');
        std::istringstream(aux[0]) >> nodos[cont].classificacao; //nodos[cont].classificacao = aux[0];
        nodos[cont].pais = aux[1];
        std::istringstream(aux[2]) >> nodos[cont].consumo;       //nodos[cont].consumo = aux[2];
        //cout<<STRING; // Prints our STRING.
        //cout << nodos[cont].classificacao << " -- ";
        //cout << nodos[cont].pais << " -- ";
        //cout << nodos[cont].consumo << "\n";
        cont++;
    }
	infile.close();

    // leArquivoDeDados();
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

