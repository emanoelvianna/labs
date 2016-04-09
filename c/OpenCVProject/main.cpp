

#include <C:\OpenCV2.2\include\opencv\cv.h>
#include <C:\OpenCV2.2\include\opencv\highgui.h>
#include <C:\OpenCV2.2\include\opencv\cxcore.h>
#include <C:\OpenCV2.2\include\opencv2\imgproc\imgproc.hpp>

int main( int argc, char** argv )
{
    IplImage* image = cvLoadImage("Imagens/circulos.png", CV_LOAD_IMAGE_COLOR);

    cvNamedWindow( "Original", CV_WINDOW_AUTOSIZE );

    cvShowImage( "Original", image );

    IplImage* imageGray = cvCreateImage( cvGetSize(image), 8, 1 ); // Tamanho. n�mero de bits por canal, n�mero de canais

	cvCvtColor( image, imageGray, CV_BGR2GRAY );

	cvShowImage( "Tons de Cinza", imageGray );

    IplImage* auxImage;
    bool fim = false;
    int key;

    while(!fim){
        key=cvWaitKey(10);
        switch(key){
            case 'l': auxImage =
            cvCreateImage(cvSize(imageGray->width,imageGray->height), IPL_DEPTH_16S, 1);
                      cvLaplace(imageGray, auxImage, 7);
                      cvShowImage("Exemplo Laplace", auxImage);
                    break;
            case 's': auxImage =
            cvCreateImage(cvSize(imageGray->width,imageGray->height), 8, 1);
                    cvSmooth(imageGray,auxImage,CV_GAUSSIAN,7,7);
                    cvShowImage("Exemplo Smooth", auxImage);
                    break;
            case 'o': auxImage =
            cvCreateImage(cvSize(imageGray->width,imageGray->height), IPL_DEPTH_16S, 1);
                    cvSobel(imageGray,auxImage,1,1,7);
                    cvShowImage("Exemplo Sobel", auxImage);
                    break;
            case 'e':
                    auxImage = cvCloneImage(imageGray);
                    cvErode(imageGray,auxImage,NULL,4);
                    cvShowImage("Exemplo Erode", auxImage);
                    break;
            case 'd':
                    auxImage = cvCloneImage(imageGray);
                    cvDilate(imageGray,auxImage,NULL,2);
                    cvShowImage("Exemplo Dilate", auxImage);
                    break;
            case 27:
                    fim = true;
                    break;
        }
    }
	cvDestroyAllWindows();

	return 0;
}
