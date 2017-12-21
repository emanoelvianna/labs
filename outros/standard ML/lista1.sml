(* Primeiro lista de exercicios *)

(* Dado um número X inteiro, calcular X^3. *)
fun triploInteiro(x) = x * x * x;

(* Dado um número X real, calcular X^3 *)
fun triploReal(x: real) = x * x * x;

(* Verificar se um número é positivo.  *)
fun isPositivo(n) = if n > 0 then "positivo" else "negativo";

(* Calcular a área de um retângulo com base e altura conhecidas (área = base * altura). *)
fun areaRetangulo(base: real, altura) = base * altura;

(* Calcular a capacitância de capacitor com carga e tensão conhecidas (capacitância = carga / tensão).  *)
fun capacitancia(carga: real, tensao) = carga / tensao;

(* Calcular a soma de N números *)
fun soma([]) = 0 | soma(x::xr) = x + soma(xr);

(* Calcular o produto de N números.  *)
fun produto([]) = 0 | produto(lista) = 
let 
	fun p([]) = 1 | p(e::t) = e * p(t)
in
	p(lista)
end; 

(* Calcular a média de N números.  *)
fun media([]) = 0.0 | media(lista) = real(soma(lista)) / real(length(lista));
	
	

