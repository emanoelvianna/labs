class NegociacaoController {

    constructor() {

        // se inspirado em JQuery
        let $ = document.querySelector.bind(document);
        // quando atribuimos para o $ precisamos ainda referenciar seu objeto original o document
        this._inputData = $('#data');
        this._inputQuantidade = $('#quantidade');
        this._inputValor = $('#valor');
        this._listaNegociacaoes = new ListaNegociacaoes();
        /*
        Quando buscamos um elemento com o querySelector, ele busca na árvore do DOM, ou seja quando realizamos isso
        em uma função como o adiciona ele ira toda vez que chamar a função buscar novamente no DOM aquele elemento,
        dessa forma realizada no constructor ele guarda a referenciar para o elemento, então nós podemos utilizar ele em nosso
        código, uma performance melhor!
        */
    }

    adiciona(event) {

        event.preventDefault(); // não faz o refresh do formulário!
        // adiciona negociacao na lista de negociacoes
        this._listaNegociacaoes.adicionar(this._criarNegociacao());
        console.log(this._listaNegociacaoes.getListaNegociacao());
        this._limparCampos();
    }

    _criarNegociacao() {

        // criando uma negociacao
        let data = DataHelper.textoParaData(this._inputData.value);
        return new Negociacao(data, this._inputQuantidade.value, this._inputValor.value);
    }

    _limparCampos() {
        this._inputData.value = '';
        this._inputQuantidade.value = 1;
        this._inputValor.value = 0.0;

        this._inputData.focus();
    }
}
