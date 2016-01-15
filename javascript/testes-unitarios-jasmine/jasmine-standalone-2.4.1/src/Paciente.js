function paciente(nome, idade, altura) {
  var clazz = {
    imprime: function() {
      alert("nome: " + nome + ", idade: " + idade);
    }
  }

  return clazz;
}
