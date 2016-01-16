describe("Consulta", function() {

  it("não deve cobrar nada se for um retorno", function() {
    var paciente1 = new Paciente("Pedro", 22, 1.78, 100);
    var consulta = new Consulta(paciente1, [], false, true);

    expect(consulta.preco()).toEqual(0);
  });

  it("deve cobrar 25 reais para procedimento comum", function() {
    var paciente1 = new Paciente("Teste", 50, 1.68, 80);
    var consulta1 = new Consulta(paciente1, ["coleta de sangue"], false, false);

    expect(consulta1.preco()).toEqual(25);

    var paciente2 = new Paciente("Teste", 50, 1.68, 80);
    var consulta2 = new Consulta(paciente2, ["coleta de sangue", "procedimento2"], false, false);

    expect(consulta2.preco()).toEqual(50);

  });

  it("consulta particular deve dobra o valor da consulta.", function() {
    var paciente1 = new Paciente("Teste", 50, 1.68, 80);
    var consulta1 = new Consulta(paciente1, ["coleta de sangue"], true, false);

    expect(consulta1.preco()).toEqual(50);

    var paciente2 = new Paciente("Teste", 50, 1.68, 80);
    var consulta2 = new Consulta(paciente2, ["coleta de sangue", "raio-x"], true, false);

    expect(consulta2.preco()).toEqual(160);

  });


});
