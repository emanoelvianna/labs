class ListaNegociacaoes {

    constructor() {

        this._lista = [];
    }

    adicionar(Negociacao) {

        this._lista.push(Negociacao);
    }

    getListaNegociacao() {

        return [].concat(this._lista);
    }

}
