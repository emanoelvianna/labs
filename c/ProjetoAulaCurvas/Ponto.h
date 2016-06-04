
#include <iostream>

class Ponto 
{
	private:
		float x;
		float y;
	public:
		Ponto() { x=y=1; }
		Ponto(float x1, float y1) { x = x1; y = y1; }
		float getX() { return x; }
		void setX(float x1) { x = x1; }
		float getY() { return y; }
		void setY(float y1) { y = y1; }
};