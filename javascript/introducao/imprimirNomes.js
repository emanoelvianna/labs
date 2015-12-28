var trsPacientes = document.getElementsByClassName("paciente");


percorreArray(trsPacientes,
              
function (pacienteTr) {
    
    var paciente = montaPaciente(pacienteTr);
    
    console.log(paciente.nome);   
});