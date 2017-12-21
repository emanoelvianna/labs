class Mensagem {

    constructor(texto = '') { // adicionando valor padrão para o parametro
        this._texto = texto;
    }

    get texto() {
        return this._texto;
    }

    set texto(texto) {
        return this._texto = texto;
    }
}
