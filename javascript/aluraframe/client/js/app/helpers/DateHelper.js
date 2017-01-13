class DateHelper {

    //quando não se define um constructor, por padrão ele é vazio! constructor() {}

    constructor() {
        // lançando erro caso ocorrer uma tentativa de criar uma instancia da classe
        throw new Error('Classe DateHelper não pode ser instanciada');
    }

    static textoParaData(texto) {
        //fail fast, detectando caso usuário passe como parametro algo fora de ano-mes-dia
        if (!(/^\d{4}-\d{2}-\d{2}$/.test(texto)))
            throw new Error('Deve estar no formato aaaa/mm/dd');

        // decrementando caso index igual a mês
        return new Date(...texto.split('-').map((item, indice) => item - indice % 2));
    }

    static dataParaTexto(data) {
        // usando um novo modo de concatenar strings no js6
        // templateString
        return `${data.getDate()}/${data.getMonth()+1}/${data.getFullYear()}`;
    }
}
