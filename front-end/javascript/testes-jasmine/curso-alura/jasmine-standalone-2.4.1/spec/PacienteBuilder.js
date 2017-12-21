// O obj do desse arquivo é facilitar a criação dos pacientes

function PacienteBuilder() {
  // valores padroes, caso usuário não os informe
  var nome = "Teste";
  var idade = 28;
  var peso = 72;
  var altura = 1.80;

  var clazz = {
    constroi: function() {
      return Paciente(nome, idade, peso, altura);
    },
    // mofidicadores de valores padroes
    comNome: function(valor) {
      nome = valor;
      return this;
    },
    comIdade: function(valor) {
      idade = valor;
      return this;
    },
    comPeso: function(valor) {
      peso = valor;
      return this;
    },
    comAltura: function(valor) {
      altura = valor;
      return this;
    }
  };

  return clazz;
}
