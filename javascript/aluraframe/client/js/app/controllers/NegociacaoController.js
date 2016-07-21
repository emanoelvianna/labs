class NegociacaoController {

    constructor() {
        // se inspirado em JQuery
        let $ = document.querySelector.bind(document);
        // quando atribuimos para o $ precisamos ainda referenciar seu objeto original o document
        this._inputData = $('#data');
        this._inputQuantidade = $('#quantidade');
        this._inputValor = $('#valor');

        /*
        Quando buscamos um elemento com o querySelector, ele busca na arevore do DOM, ou seja quando realizamos isso
        em uma função como o adiciona ele ira toda vez que chamar a função buscar novamente no DOM aquele elemento,
        dessa forma realizada no constructor ele guarda a referenciar para o elemento e nós podemos utilizar ele em nosso
        código, uma performance melhor!
        */
    }

    adiciona(event) {
        event.preventDefault();
        // criando uma negociacao
        // convertendo a string que vem do input data para um object Date

        let data = new Date(...this._inputData.value.split('-').map((item, index) => (index == 1) ? item - 1 : item));
        console.log(data);
        // utilizando a nova função Arrow functions

        /*
          -- poderia fazer assim também:

          let data = new Date(...this._inputData.value.split('-').map(function(item, index) {
              if (index === 1) {
                  return item - 1;
              }
              return item;
          }));
          // tranforma em um array, exemplo: ['2016', '11',  '01'], colocando cada index do array no construtor (...)
          // a função map nós ajudar com a criação do mês que começa em 0 ate 11 por isso o -1
        */
        let negociacao = new Negociacao(data, this._inputQuantidade.value, this._inputValor.value);
        console.log(negociacao);
    }
}
