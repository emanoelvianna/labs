class DateHelper {

    //quando não se define um constructor, por padrão ele é vazio! constructor() {}

    constructor() {
        // lançando erro caso ocorrer uma tentativa de criar uma instancia da classe
        throw new Error('Classe DateHelper não pode ser instanciada');
    }

    static textoParaData(texto) {
        // decrementando caso index igual a mês
        return new Date(...texto.split('-').map((item, indice) => item - indice % 2));
    }

    static dataParaTexto(data) {

        return data.getDate() +
            '/' + (data.getMonth() + 1) + // incrementado o mês, lembre-se mes é guardado em um vetor no js
            '/' + data.getFullYear();
    }
}
