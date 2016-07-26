class Negociacao {

    // declara os atributos da classe Negociacao
    // toda a classe que possui o constructor deve ser chamada com new, o mesmo funciona com a classe Date
    constructor(data, quantidade, valor) {

        // aqui além de declarar estamos também iniciando eles com campos default
        // a utilização do _ é uma convenção para indicar atributos que são somente leitura
        this._data = data;
        this._quantidade = quantidade;
        this._valor = valor;

        // uma maneira de solucionar a questão de alteração depois de criar a classe é congelar
        // congelando ela, seu metodos não iram poder ser mais alterados

        Object.freeze(this); // this representa essa instancia de Negociacao!

        // Object.freeze é razo ou seja se não retornarmos um clone de data, poderiamos realizar modificações já que data é um object
    }

    getVolume() {

        return this._quantidade * this._valor;
    }

    getData() {

        return new Date(this._data.getTime()); // retorna um clone do object data (encapsulamento)
    }

    getQuantidade() {

        return this._quantidade;
    }

    getValor() {

        return this._valor;
    }
}
