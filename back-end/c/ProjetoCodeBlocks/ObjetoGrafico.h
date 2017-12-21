#ifndef OBJETOGRAFICO_H_INCLUDED
#define OBJETOGRAFICO_H_INCLUDED


class ObjetoGrafico
{
    private:

        Point3D tranlacao, rotacao, escala;
        int tipoDoObjeto;
        float posInicial, posFinal, posAltura;
    public:
        ObjetoGrafico(int tipo) {tipoDoObjeto = tipo;};
        void Print();

        void SetTranslacao(Point3D p){tranlacao = p;};
        void SetRotacao(float ang, Point3D p);
        void SetEscala(Point3D p);
        void SetPosInicial(float pi){posInicial = pi;};
        void SetPosFinal(float pf){posFinal = pf;};
        void SetPosAltura(float al){posAltura = al;};

        int getTipoDoObjeto(){ return tipoDoObjeto;};
        float getPosInicial(){ return posInicial;};
        float getPosFinal(){ return posFinal;};
        float getPosAltura(){ return posAltura;};

};

#endif // OBJETOGRAFICO_H_INCLUDED
