class DataHelper {

    constructor() {
        throw new Error('Classe não pode ser instanciada');
    }

    // a utilizanção de static nós diz que o método pertence a definição da classe
    static textoParaData(texto) {
        // expressão para validar o texto que deve estar no formato: ano/mes/dia, ex: 2016/11/12
        let isValido = /\d{4}-\d{2}-\d{2}/.test(texto);
        if (isValido) {
            // convertendo a string que vem do input data para um object Date
            return new Date(...texto.split('-').map((item, index) => (index === 1) ? item - 1 : item));
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
        } else {
            throw new Error('Deve estar no formato aaaa-mm-dd');
        }

    }

    static dataParaTexto(data) {
        // ao invés de fazer assim:
        //  return data.getDate() + '/' + (data.getMonth() + 1) + '/' + data.getFullYear();
        // utilizamos o template string da nova versão do JavaScript

        return `${data.getDate()}/${data.getMonth() + 1}/${data.getFullYear()}`
    }
}
