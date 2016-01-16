function Paciente(nome, idade, altura, peso) {
  var clazz = {
    imprime: function() {
      alert("nome: " + nome + ", idade: " + idade);
    },

    batimenos: function() {
      return idade * 365 * 24 * 60 * 80;
    },

    imc : function() {
      return peso/(altura * altura);
    }
  }

  return clazz;
}
