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
        this._negociacoesView = new NegociacoesView($('#negociacoesView'));
        this._negociacoesView.update(this._listaNegociacao);

        this._mensagem = new Mensagem();
        this._mensagemView = new MensagemView($('#mensagemView'));
        this._mensagemView.update(this._mensagem); //passando o modelo
    }

    adiciona(event) {
        // lá no html estou utilizando o onsubmit="negociacaoController.adicionar(event)"
        event.preventDefault(); // não deixa recaregar o formulário, assim os dados não são perdidos

        // adicionando uma negociacao na Lista
        this._listaNegociacao.adiciona(this._criarNegociacao());
        this._negociacoesView.update(this._listaNegociacao);

        // nova mensagem e atualizado a view
        this._mensagem.texto = 'Negociação adicionada com sucesso';
        this._mensagemView.update(this._mensagem);

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
