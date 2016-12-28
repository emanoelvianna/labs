class NegociacaoController {

    constructor() {
        // capturando os dados de input da tela, daria para fazer algo como:
        /**
          let inputData = document.querySelector('#data');
        **/
        // mas assim o código fica mais limpo:
        let $ = document.querySelector.bind(document); // bind deixando o contexto ainda sobre document
        this.inputData = $('#data');
        this.inputQuantidade = $('#quantidade');
        this.inputValor = $('#valor');
    }

    adicionar(event) {
        // lá no html estou utilizando o onsubmit="negociacaoController.adicionar(event)"
        event.preventDefault(); // não deixa recaregar o formulário, assim os dados não são perdidos

        console.log(this.inputData.value);
    }
}
