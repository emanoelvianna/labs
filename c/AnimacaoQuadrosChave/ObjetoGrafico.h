#ifndef OBJETOGRAFICO_H_INCLUDED
#define OBJETOGRAFICO_H_INCLUDED


class ObjetoGrafico
{
    private:
        Point3D tranlacao, rotacao, escala;
        int tipoDoObjeto;
        GLint quadro, quadroAnterior, quadroSeguinte;
    public:
        ObjetoGrafico(){tipoDoObjeto = 0;};
        //ObjetoGrafico(int tipo) {tipoDoObjeto = tipo;};
        void SetTranslacao(Point3D p) {tranlacao = p;};
        void SetRotacao(float ang, Point3D p);
        void SetEscala(Point3D p);
        void Print();
        void setQuadro(GLint q){quadro = q;};
        void setQuadroAnterior(GLint qA){quadroAnterior = qA;};
        void setQuadroSeguinte(GLint qS){quadroSeguinte = qS;};
        GLint getQuadro(){return quadro;};
        GLint getQuadroAnterior(){return quadroAnterior;};
        GLint getQuadroSeguinte(){return quadroSeguinte;};
        int getTipo(){return tipoDoObjeto;};
        Point3D getTranslacao() {return tranlacao;};
};

#endif // OBJETOGRAFICO_H_INCLUDED
