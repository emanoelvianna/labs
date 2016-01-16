function Consulta(Paciente, procedimento, particular, retorno) {
  var clazz = {
      preco: function() {
        if(retorno) return 0;

        var precoFinal = 0;

        procedimento.forEach(function(procedimento) {

          if("raio-x" == procedimento) precoFinal += 55;
          else if("gesso" == procedimento) precoFinal += 33;
          else precoFinal += 25; // procedimento comum
        });

        if(particular) precoFinal *= 2;

        return precoFinal;
      }
  }
  return clazz;
}
