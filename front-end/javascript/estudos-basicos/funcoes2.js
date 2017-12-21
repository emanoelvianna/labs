// parte dois dos estudos

// -- Encapsulamento

// imagine que temos:

var conta = 0;

function add() {
  return ++conta;
}

console.log(add()); // retorna 1
console.log(add()); // retorna 2


var itens = [];
function add(item) {
  return itens.push(item);
}

console.log(add(1)); // return [1]
console.log(add(2)); // return [1, 2]

// agora fazer isso aqui novamente:

console.log(add()); // ou seja tentar chamar a funcao de cima, que soma o conta não rola mais!

// pos isso existem tecnica para Encapsulamento
// -- primeira tecnica utilizando obj

var conta = {
  value: 0,
  add: function() {
    return ++this.value;
  }
}

console.log(conta.add()); // return 1
console.log(conta.add()); // return 2

var itens = {
  value: [],
  add: function(item) {
    this.value.push(item);
    return this.value;
  }
}

console.log(itens.add(1)); // return [1]
console.log(itens.add(2)); // return [1, 2]

// -- mas um dos probolemas de utilizar essa tecnica é acessar atributos sem permisao
//por exemplo
conta.value = undefined;
console.log(conta.add()); // ira me retornar NaN, ja era!

// -- Por isso encapsulamento por meio de funções é uma boa prática

var conta = function() {
  var value = 0;
  var add = function() {
    return ++value;
  };
};

console.log(conta.value); // retorn undefined!
console.log(conta.add);// retorna undefined
console.log(conta.add());// ira dizer que não existe, ou nao possui
// -- para podemos realiar o que quremos teremos que utilizar a Factory function
// -- Factory function ( ira expor o que queroemos)
// -- utilizando a Factory function:

var createConta = function() {
  var value = 0;
  return {
    var add = function() {
      return ++value;
    }
  };
};

var conta = createConta();
console.log(conta.value);// retorna undefined
console.log(conta.add());// retorna 1

// ou seja isso gerou encapsulamento

// -- ainda podemos utilizar essa outra tecnica para encapsulamento

var Conta = function() {
  var value: 0;
  this.add = function() {
    return ++value;
  };
};

var conta = new Conta(); // a diferenca é o new
console.log(conta.value);// retorna undefined
console.log(conta.add());// retorna 1



// -- Outro padrão é IIFE chamado de Module Pattern
// -- isso ira ser automaticamento invocado
// muito utilizando no jsquery e angularjs

var counter = (function() { // esta alto invocando a funcao
  var value = 0;
  return {
    add: function() {
      return ++value;
    }
  };
})();

// caso tenha mais uma operacao:

var counter = (function() { // esta alto invocando a funcao
  var value = 0;
  return {
    add: function() {
      return ++value;
    }
    reset: function() {
      value = 0;
    }
  };
})();

// outro Pattern se utiliza assim:

var counter = (function() { // esta alto invocando a funcao
  // tudo aqui é privado
  var _value = 0;
  var _add = function() {
    return ++_value;
  }
  var _reset = function() {
    _value = 0;
  }
  // aqui eu irei colocar o que eu quero deixar publico
  return {
    add: _add,
    reset: _reset
  };
})();

//-- outra boa pratica é colcoar _ para indicar o que é privado
