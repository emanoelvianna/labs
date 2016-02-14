describe("Consulta", function() {

  var guilherme;
  var pedro;
  beforeEach(function() {
    guilherme = new Paciente(guilherme, 32, 1.60, 70);
    pedro = new Paciente(pedro, 22, 1.90, 80);
  });

  it("não deve cobrar nada se for um retorno", function() {
    var consulta = new Consulta(guilherme, [], false, true);

    expect(consulta.preco()).toEqual(0);
  });

  it("deve cobrar 25 reais para procedimento comum", function() {
    var consulta1 = new Consulta(guilherme, ["coleta de sangue"], false, false);
    expect(consulta1.preco()).toEqual(25);
    var consulta2 = new Consulta(pedro, ["coleta de sangue", "procedimento2"], false, false);
    expect(consulta2.preco()).toEqual(50);
  });

  it("consulta particular deve dobra o valor da consulta.", function() {
    var consulta1 = new Consulta(guilherme, ["coleta de sangue"], true, false);
    expect(consulta1.preco()).toEqual(50);
    var consulta2 = new Consulta(pedro, ["coleta de sangue", "raio-x"], true, false);

    expect(consulta2.preco()).toEqual(160);

  });
});
