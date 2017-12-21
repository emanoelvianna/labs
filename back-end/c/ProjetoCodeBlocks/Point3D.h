#ifndef POINT3D_H_INCLUDED
#define POINT3D_H_INCLUDED

#include <iostream>
using namespace std;

class Point3D
{
   private:
      float x, y, z;
   public:
      Point3D () {x = 0; y = 0; z = 0; };
      Point3D (float xp, float yp, float zp ) { x = xp; y = yp; z = zp; };
      void Setfloat (float xp, float yp, float zp ) { x = xp; y = yp; z = zp; };
      float GetX() { return x; };
      float GetY() { return y; };
      float GetZ() { return z; };
      Point3D Get() {
                        Point3D temp;
                        temp.x = x;
                        temp.y = y;
                        temp.z = z;
                        return temp;
                    };
      void Print()
      {
            cout.precision(3);
            cout << "X=" << x << "  Y=" << y << "  Z=" << z;
      };

};


#endif // POINT3D_H_INCLUDED
