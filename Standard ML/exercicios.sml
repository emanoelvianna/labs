
(* função auxiliariares *)
fun removePares([]) = [] | removePares(x::xs) = if x mod 2 = 0 then removePares(xs) else  x::(removePares(xs));

(* soma elementos da lista *)
fun somaLista([]) = 0 | somaLista(c::r) = c + somaLista(r); 

(* menor da lista *)
fun maiorDeLista([]) = hd([])
| maiorDeLista(x::[]) = x
| maiorDeLista(x::xs) = 
	let
		val m = maiorDeLista(xs)
	in 	
		if x > m then x else m
	end;

(* remove elemento da lista *)
fun remover(a, []) = []
| remover(a, x::xs) = if a = x then remover(a,xs) else x::(remover(a,xs));

(* retira o ultimo elemento *)
fun menosUlt [] = [] 
| menosUlt (x::[]) = []
| menosUlt (x::xs) = if tl(xs) = [] then [x] else x :: menosUlt(xs);

(* ---- *)

(* Questão 1 *)
fun mediaListaSemPares([]) = 0.0
| mediaListaSemPares lista = real(somaLista(removePares(lista))) / real(length(removePares(lista)));  

(* Questão 2 *)
fun listaSemMaior([]) = [] | listaSemMaior lista = remover(maiorDeLista(lista), lista);

(* exercicio 3 *)
fun espelhaDobraLista [] = []
| espelhaDobraLista (x::[]) = [x]
| espelhaDobraLista umaLista = 
let
	val lista = umaLista
in
	rev(umaLista)@lista
end;

(* Questão 4 *)
fun semDoisExtremos [] = []
| semDoisExtremos (x::xs) = if length(x::xs) = 2 then (x::xs) else menosUlt(xs);