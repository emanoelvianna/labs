describe("Paciente", function() {

  it("deve calcular o imc", function() {
    var paciente1 = new Paciente("Pedro", 22, 1.78, 100);
    var imc = paciente1.imc();
    expect(imc).toEqual(100 / (1.78 * 1.78));
  });

  it("deve calcular batimentos", function() {
    var idade = 22;
    var paciente2 = new Paciente("Emanoel", idade, 1.94, 100);
    var batimentos = paciente2.batimenos();
    expect(batimentos).toEqual(idade * 365 * 24 * 60 * 80);
  });

});
