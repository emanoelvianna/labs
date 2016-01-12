describe("Maior e Menor", function() {

      //aqui são descritos os testes, cada cenario do código
      it("deve entender numeros em ordem nao sequencial", function() {
          var algoritmo = new MaiorEMenor();
          algoritmo.encontra([5,15,7,9]);

          expect(algoritmo.pegaMaior()).toEqual(15);
          expect(algoritmo.pegaMenor()).toEqual(5);
      });

      it("deve entender numeros em ordem crescente", function() {
          var algoritmo = new MaiorEMenor();
          algoritmo.encontra([5,6,7,8]);

          expect(algoritmo.pegaMaior()).toEqual(8);
          expect(algoritmo.pegaMenor()).toEqual(5);
      });

      it("deve entender numeros em ordem decrescente", function() {
          var algoritmo = new MaiorEMenor();
          algoritmo.encontra([8,7,6,5]);

          expect(algoritmo.pegaMaior()).toEqual(8);
          expect(algoritmo.pegaMenor()).toEqual(5);
      });


      it("deve entender array com um elemento", function() {
          var algoritmo = new MaiorEMenor();
          algoritmo.encontra([5]);

          expect(algoritmo.pegaMaior()).toEqual(5);
          expect(algoritmo.pegaMenor()).toEqual(5);
      });

  });
