
// declaracao de função declaration
function soma(a, b) {
  return a + b;
}

// declaracao de função expression

var soma = function(a, b) {
  return a + b;
}

// diferença entre as duas?
// a declaration irá ser "carregada" antes da compilação do código;
// ou seja, se eu fizer isso em baixo, ira funcionar!

/**

console.log(soma(10, 10));

function soma(a, b) {
  return a + b;
}

**/

// ------ passagem de parametro de funções
// ------ perceba a passagem de função por parametro abaixo:

var produto = {nome:'Sapato', preco: 150};

var imposto1 = function functionName(preco) { return preco * 0.1};
var imposto2 = function functionName(preco) { return preco * 0.5};


var calcularPrecoFinal = function functionName(produto, imposto) {
  return produto.preco + imposto(produto.preco);
}

calcularPrecoFinal(produto, imposto1);

calcularPrecoFinal(produto, imposto2);

// Perceba o reuso de codigo acima!

// --- retorno de função dentro de outra funcao

function ola() {
  return function() {
    return "olá mundo"
  }
}

console.log(ola); // ira retorna que é uma funcao
console.log(ola()()); // agora sim ira retorna o "olá mundo", perceba os dois parenteses


// -- não existem metodos em javascript, mas posso simular eles como abaixo:

var pessoa = {
  nome: "Pedro",
  idade: 20,
  getIdade: function() {
    return this.idade; // sem o this, ele ira retornar que a idade não foi definida
  }
}

console.log(pessoa); // retorna obj
console.log(pessoa.getIdade()); // retorna 20

// vale fazer também:

var getIdade = function() {
  return this.idade;
}

var pessoa = {
  nome: "Pedro",
  idade: 20,
  getIdade: getIdade //não se coloca os parenteses, se não iria ser uma invocação
}


console.log(getIdade()); // ira retornar undefine
console.log(pessoa.getIdade); // ira retornar 20

// é possivel passar a pessoa como contexto

console.log(getIdade.call(pessoa)); // retorna 20

// caso getIdade tive parametro iria ser assim para usar o call:

var getIdade = function(extra) {
  return this.idade + extra;
}

console.log(getIdade.call(pessoa, 2)); // retorna 22, 2 é o parametro


// é possivel acessar os argumentos atravez de:

console.log(arguments);

var getIdade = function(extra) {
  console.log(arguments);  // retorna 1
  return this.idade + extra;
}


// utilizando o new em javascript
// ao invez de fazer assim:
var pessoa1 = {
  nome: "Pedro",
  idade: 20;
}


var pessoa2 = {
  nome: "Pedro",
  idade: 20;
}

// podemos utilizar o new

// funcao construtora vs funcao frabrica

// -- funcao fabrica:

var criarPesssoa = function(nome, idade) {
  return { // retorna o novo obj
    nome: nome,
    idade: idade
  };
};

console.log(criarPesssoa("Pedro", 21));
console.log(criarPesssoa("Maria", 24));


// -- funcao construtora:

var Pessoa = function(nome, idade) { // nota a letra maiúscula
  this.nome = nome;
  this.idade = idade;
}

console.log(new Pessoa("Pedro", 20));
console.log(new Pessoa("Maria", 26));

// Por trás ela acaba fazendo isso:
var pedro = {};
Pessoa.cal(pedro, "Pedro", 20);


// funcao fabrica é mais clara, mais verbosa
// funcao construtora é mais simples, mas pode acabar sendo confusa

// -- Closures (fechamento)
var ola = function () {
  var message = "olá mundo";
  return function() { // iner function
    return message;
  };
};

console.log(ola()); // retorna "olá mundo"

// A iner function acima ira manter a referença por isso ela não se perde
