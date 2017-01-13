class ListaNegociacoes {
    constructor() {
        this._negociacoes = [];
    }

    adiciona(negociacao) {
        this._negociacoes.push(negociacao);
    }

    get negociacoes() { //getter
        // programação defensiva
        return [].concat(this._negociacoes);
    }
}
