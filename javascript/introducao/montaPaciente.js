function montaPaciente(trDoPaciente){
    var nome = trDoPaciente.getElementsByClassName("info-nome")[0].textContent;
    var altura = trDoPaciente.getElementsByClassName("info-altura")[0].textContent;
    var peso = trDoPaciente.getElementsByClassName("info-peso")[0].textContent;
    var paciente = {
        nome : nome,
        altura: altura,
        peso : peso,
        pegaImc : function(){
            return peso/(altura*altura);
        }
    }
    return paciente;
}

var pacientes = document.getElementsByClassName("paciente");

for(var posicaoAtual = 0; posicaoAtual <= pacientes.length; posicaoAtual++){
    var trDoPaciente = pacientes[posicaoAtual];
    var trDoImc = trDoPaciente.getElementsByClassName("info-imc")[0];
    var paciente = montaPaciente(trDoPaciente);
    var imc = paciente.pegaImc();
    trDoImc.textContent = imc;
    console.log(imc);
}