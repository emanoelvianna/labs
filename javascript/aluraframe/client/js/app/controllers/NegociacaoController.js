class NegociacaoController {

    constructor() {
        // capturando os dados de input da tela, daria para fazer algo como:
        /**
          let inputData = document.querySelector('#data');
        **/
        // mas assim o código fica mais limpo:
        let $ = document.querySelector.bind(document); // bind deixando o contexto ainda sobre document
        this._inputData = $('#data');
        this._inputQuantidade = $('#quantidade');
        this._inputValor = $('#valor');
    }

    adiciona(event) {
        // lá no html estou utilizando o onsubmit="negociacaoController.adicionar(event)"
        event.preventDefault(); // não deixa recaregar o formulário, assim os dados não são perdidos

        let data = DateHelper.textoParaData(this._inputData.value);

        let negociacao = new Negociacao(
            data,
            this._inputQuantidade.value,
            this._inputValor.value
        );

        console.log(DateHelper.dataParaTexto(data));
    }

}
