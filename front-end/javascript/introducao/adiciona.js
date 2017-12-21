

var botao = document.querySelector("#adicionar-paciente");
botao.addEventListener("click", function() {

  event.preventDefault(); // impede comportamento padrão

  var campoNome = document.querySelector("#campo-nome");
  var campoPeso = document.querySelector("#campo-peso");
  var campoAltura = document.querySelector("#campo-altura");

  var pacienteNovo = "<tr class='paciente'>" +
                     "<td class='info-nome'>"+campoNome.value+"</td>" +
                     "<td class='info-peso'>"+campoPeso.value+"</td>" +
                     "<td class='info-altura'>"+campoAltura.value+"</td>"+
                     "<td class='info-imc'></td>"+
                     "</tr>"

  // var tabela = document.getElementsByTagName('table')[0];

  // outra maneira alem de usar os gets.. mas ele sempre busca o primeiro elemento, ou seja não se usa o [0]
  var tabela = document.querySelector("table");
  tabela.innerHTML = tabela.innerHTML + pacienteNovo;

  // limpando os input
  campoNome.value = "";
  campoPeso.value = "";
  campoAltura.value = "";


});
