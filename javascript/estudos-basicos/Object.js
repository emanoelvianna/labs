// os objs em js são coleções de chaves e valores
// diferente do java eles são dinamicos!

var pessoa = {}; // basta abrir e fechar chaves que temos um obj

var pessoa = {
  nome: "jão", // exp as propriedades
  idade: 20,
  endereco: {
    cidade: "Porto Alegre",
    estado: "RS",
  }
};

pessoa.nome = "Pedro";
// dessa maneira aqui em baixo é muito utilizado quando etamos em um for:
pessoa[nome] = "Pedro";

pessoa.endereco.cidade = "São Paulo";
pessoa.estado = null;

pessoa["endereco"]["cidade"] = "Porto Alegre";

// apagar propriedades dos obj

delete pessoa.idade

// exemplo de for com obj

for(var propriedade in pessoa) {
  console.log(propriedade + "" + pessoa[propriedade]);
}
