function TelaDePessoa() {
  var clazz = {
    pegaPessoa: function() {
      return Pessoa(
        var nome = getElementsByClassName('nome'),
        console.log(nome)
      );
    }
    exibeIMC: function(imc) {
      $("#resultado").val("O IMC é " + imc);
    }
  };
  return clazz;
}

var botao = document.getElementById('calcular');
botao.addEventListener("click", function() {
  TelaDePessoa().pegaPessoa();
};
