(* lista de exercicios 2 *)

(* lista de funções para auxiliar *)
fun RetornaMaior(a, b) = if a > b then a else b;

(* Dados dois números, verificar se o maior é múltiplo do menor.  *)
fun multiplo (a,b) =
	let 
		val maior = RetornaMaior(a,b)
		val menor = a + b - maior
	in
		if maior mod menor = 0 then true
		else false
	end;

(* Verificar se um retângulo (com conhecidas base e altura) tem área menor que uma determinada área. *)

	
(* Remover um elemento de uma lista.  *)
fun removeElemento(elemento, []) = [] | removeElemento(elemento, x::xr) =
	if elemento = x then
		removeElemento(elemento, xr)
	else 
		x::(removeElemento(elemento, xr));
		
(* Inserir um elemento em uma lista *)
fun insert(elemento, []) = [elemento] | insert(elemento, x::xr) = elemento::(x::xr);


(* Dada uma lista L, verificar se uma lista P é seu prefixo. Por exemplo: P = [1,2,3] é prefixo de L = [1,2,3,4,5]. *)
fun prefixo([], []) = true | prefixo(lista, []) = false 
| prefixo(a::[], b::r) = a = b
| prefixo(a::p, b::l) = a = b andalso prefixo(p, l);
	

(* Dada uma lista L, verificar se uma lista S é seu sufixo. Por exemplo: S = [4,5] é sufixo de L = [1,2,3,4,5] *)
fun sufixo(listaA, listaB) = prefixo(rev(listaA), rev(listaB));


(*  Calcular a soma dos inteiros de 1 a N *)
fun soma 0 = 0 | soma n = n + soma(n-1);

(* Dado um número de 1 a 7, traduzir este número no dia da semana correspondente *)
fun diaSemana(n) =
case n of
	1 => "Domingo" |
	2 => "Segunda-feira" |
	3 => "Terça-feira" |
	4 => "Quarta-feira" |
	5 => "Quinta-feira" |
	6 => "Sexta-feira" |
	7 => "Sabado";
	
(* Calcular a média de N inteiros, descartando os negativos. *)
fun soma([]) = 0 | soma(x::xr) = x + soma(xr);

fun media([]) = 0.0 | media(lista) = real(soma(lista)) / real(length(lista));

fun retiraNegativos([]) = [] | retiraNegativos(x::xr) = if x >= 0 then x::(retiraNegativos(xr)) else retiraNegativos(xr);

fun mediaSemNegativos(lista) =
	let
		val x = retiraNegativos(lista)
	in 
		media(x)
	end;


	
