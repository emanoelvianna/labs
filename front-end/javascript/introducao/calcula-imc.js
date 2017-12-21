//calcula-imc.js

function calculaTodosImcs(){

  var trsPacientes = document.getElementsByClassName("paciente");

  precorreArray(trsPacientes, function (pacienteTr){

      var tdNome = pacienteTr.getElementsByClassName("info-nome")[0];
      var tdPeso = pacienteTr.getElementsByClassName("info-peso")[0];
      var tdAltura = pacienteTr.getElementsByClassName("info-altura")[0];

      var pacienteAtual = {
          nome : tdNome.textContent,
          peso : tdPeso.textContent,
          altura : tdAltura.textContent,
          pegaImc: function() {

              if(this.altura != 0){
                  var imc = this.peso / (this.altura * this.altura);
                  return imc;
              } else{

                  console("Não posso dividir por zero!");
              }
          }
      };

      var imc = pacienteAtual.pegaImc();

      var tdImc = pacienteTr.getElementsByClassName("info-imc")[0];
      tdImc.textContent = imc;

      console.log(imc);
  }); //muito cuidado pra não esquecer de fechar aqui a chamada da função

}


var botao = document.getElementById('calcula-imcs');
//botao.onclick = calculaTodosImcs;
//botao.onclick = function() {console.log("calculando imcs")}

// ira ouvir o evento
botao.addEventListener("click", calculaTodosImcs);
botao.addEventListener("click", function() {
    console.log("calculando imcs");
});
