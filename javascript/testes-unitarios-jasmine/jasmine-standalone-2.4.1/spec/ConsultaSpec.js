describe("Consulta:", function() {

  var guilherme;
  var pedro;
  beforeEach(function() {
    guilherme = new PacienteBuilder().comPeso(60).comAltura(1.89).constroi();
    pedro = new PacienteBuilder().comPeso(50).comAltura(1.60).constroi();
  });

  it("não deve cobrar nada se for um retorno", function() {
    var consulta = new Consulta(guilherme, [], false, true);

    expect(consulta.preco()).toEqual(0);
  });

  describe("Consultas e procedimentos", function functionName() { // sub describe
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
});
