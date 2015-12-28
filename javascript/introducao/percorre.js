function percorreArray(trPacientes, comportamento) {
    for(var posicaoAtual = 0; posicaoAtual <= trsPacientes.length -1; posicaoAtual++) { 
            var pacienteTrAtual = trsPacientes[posicaoAtual];
            
            comportamento(pacienteTrAtual); 
    }
}