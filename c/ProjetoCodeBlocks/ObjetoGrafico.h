#ifndef OBJETOGRAFICO_H_INCLUDED
#define OBJETOGRAFICO_H_INCLUDED


class ObjetoGrafico
{
    private:
        Point3D tranlacao, rotacao, escala;
        int tipoDoObjeto;
    public:
        ObjetoGrafico(int tipo) {tipoDoObjeto = tipo;} ;
        void SetTranslacao(Point3D p);
        void SetRotacao(float ang, Point3D p);
        void SetEscala(Point3D p);
        void Print();
};

#endif // OBJETOGRAFICO_H_INCLUDED
