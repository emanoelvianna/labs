(* Primeiro lista de exercicios *)

(* Dado um n�mero X inteiro, calcular X^3. *)
fun triploInteiro(x) = x * x * x;

(* Dado um n�mero X real, calcular X^3 *)
fun triploReal(x: real) = x * x * x;

(* Verificar se um n�mero � positivo.  *)
fun isPositivo(n) = if n > 0 then "positivo" else "negativo";

(* Calcular a �rea de um ret�ngulo com base e altura conhecidas (�rea = base * altura). *)
fun areaRetangulo(base: real, altura) = base * altura;

(* Calcular a capacit�ncia de capacitor com carga e tens�o conhecidas (capacit�ncia = carga / tens�o).  *)
fun capacitancia(carga: real, tensao) = carga / tensao;

(* Calcular a soma de N n�meros *)
fun soma([]) = 0 | soma(x::xr) = x + soma(xr);

(* Calcular o produto de N n�meros.  *)
fun produto([]) = 0 | produto(lista) = 
let 
	fun p([]) = 1 | p(e::t) = e * p(t)
in
	p(lista)
end; 

(* Calcular a m�dia de N n�meros.  *)
fun media([]) = 0.0 | media(lista) = real(soma(lista)) / real(length(lista));
	
	

