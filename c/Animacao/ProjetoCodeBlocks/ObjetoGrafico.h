#ifndef OBJETOGRAFICO_H_INCLUDED
#define OBJETOGRAFICO_H_INCLUDED


class ObjetoGrafico
{
    private:
        Point3D tranlacao, rotacao, escala;
        int tipoDoObjeto;
        float posInicial, posFinal, posAltura;
        float TX1, TX2, RY1, RY2, TY;
    public:
        ObjetoGrafico(int tipo) {tipoDoObjeto = tipo;};
        void Print();

        void SetTranslacao(Point3D p){tranlacao = p;};
        void SetRotacao(float ang, Point3D p);
        void SetEscala(Point3D p);
        void SetPosInicial(float pi){posInicial = pi;};
        void SetPosFinal(float pf){posFinal = pf;};
        void SetPosAltura(float al){posAltura = al;};
        void SetTX1(float t){TX1 = t;};
        void SetTX2(float t){TX2 = t;};
        void SetRY1(float t){RY1 = t;};
        void SetRY2(float t){RY2 = t;};
        void SetTY(float t){TY = t;};

        int getTipoDoObjeto(){ return tipoDoObjeto;};
        float getPosInicial(){ return posInicial;};
        float getPosFinal(){ return posFinal;};
        float getPosAltura(){ return posAltura;};
        float getTX1(){ return TX1;};
        float getTX2(){ return TX2;};
        float getRY1(){ return RY1;};
        float getRY2(){ return RY2;};
        float getTY(){ return TY;};
};

#endif // OBJETOGRAFICO_H_INCLUDED
