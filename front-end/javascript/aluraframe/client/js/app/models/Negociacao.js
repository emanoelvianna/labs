class Negociacao {

    // Toda a classe que possui um constructor deve ser chamado com new
    // o operador new que irá tornar o this relacionado com a instancia do obj
    constructor(data, quantidade, valor) {
        // _algumaCoisa = conversão para deixar claro que as propriedade dessa classe
        // só devem ser acessadas atravez da propria classe, ou seja, não deve ser acessado de fora!
        this._data = new Date(data.getTime()); // programação defensiva
        this._quantidade = quantidade;
        this._valor = valor;

        Object.freeze(this); // congelando o objeto, assim as suas propriedade não pode ser alteradas!
    }

    // declarando o metodo para calcular o volume
    get volume() {
        return this._quantidade * this._valor;
    }

    /* Modificadores de acesso */

    /*
    -- também é possivel criar os modificadores de acesso desta maneira
    getData() {
        return this._data;
    }

    -- Iriamos acessar eles assim:
    negociacao.getData();
    */

    get data() {
        return new Date(this._data.getTime()); // programação defensiva, retornando uma copia do obj Date
    }

    get quantidade() {
        return this._quantidade;
    }

    get valor() {
        return this._valor;
    }
}
