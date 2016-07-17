// captura os valores passados nos campos de input
var campos = [
    document.querySelector('#data'),
    document.querySelector('#quantidade'),
    document.querySelector('#valor')
];

var tbody = document.querySelector('table tbody');

// função callback chamada quando for realizado algum evento no formulário
document.querySelector('.form').addEventListener('submit', function(event) {
    event.preventDefault(); // não submete o formulário, caso contrario irá perder o append do tr

    var tr = document.createElement('tr');

    // percorre todos os campos criando os td para fazer o append
    campos.forEach(function(campo) {
        var td = document.createElement('td');
        td.textContent = campo.value;
        tr.appendChild(td);
    });

    // realiza o calculo do volumero
    var tdVolume = document.createElement('td');
    tdVolume.textContent = campos[1].value * campos[2].value;

    // faz os append
    tr.appendChild(tdVolume);
    tbody.appendChild(tr);

    // campos default
    campos[0].value = '';
    campos[0].value = 1;
    campos[0].value = 0;

    // campos da data ganha o foco
    campos[0].focus();

});
