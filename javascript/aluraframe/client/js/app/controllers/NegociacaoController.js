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
        this._listaNegociacao = new ListaNegociacoes();
    }

    adiciona(event) {
        // lá no html estou utilizando o onsubmit="negociacaoController.adicionar(event)"
        event.preventDefault(); // não deixa recaregar o formulário, assim os dados não são perdidos

        // adicionando uma negociacao na Lista
        this._listaNegociacao.adiciona(this._criarNegociacao());
        console.log(this._listaNegociacao);

        this._limparCampos();
    }

    _criarNegociacao() {
        return new Negociacao(
            DateHelper.textoParaData(this._inputData.value),
            this._inputQuantidade.value,
            this._inputValor.value
        );
    }

    _limparCampos() {
        this._inputData.value = '';
        this._inputQuantidade.value = 1;
        this._inputValor.value = 0.0;

        this._inputData.focus();
    }

}
