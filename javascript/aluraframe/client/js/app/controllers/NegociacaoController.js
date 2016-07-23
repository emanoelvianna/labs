class NegociacaoController {

    constructor() {
        // se inspirado em JQuery
        let $ = document.querySelector.bind(document);
        // quando atribuimos para o $ precisamos ainda referenciar seu objeto original o document
        this._inputData = $('#data');
        this._inputQuantidade = $('#quantidade');
        this._inputValor = $('#valor');

        /*
        Quando buscamos um elemento com o querySelector, ele busca na árvore do DOM, ou seja quando realizamos isso
        em uma função como o adiciona ele ira toda vez que chamar a função buscar novamente no DOM aquele elemento,
        dessa forma realizada no constructor ele guarda a referenciar para o elemento, então nós podemos utilizar ele em nosso
        código, uma performance melhor!
        */
    }

    adiciona(event) {
        event.preventDefault(); // não faz o refresh do formulário!
        // criando uma negociacao
        let data = DataHelper.textoParaData(this._inputData.value);
        let negociacao = new Negociacao(data, this._inputQuantidade.value, this._inputValor.value);
        console.log(negociacao);

        console.log(DataHelper.dataParaTexto(negociacao.getData()));
    }
}
