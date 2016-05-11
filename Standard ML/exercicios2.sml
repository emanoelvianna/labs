(* remover elemento de uma lista *)
fun removeElemento(elemento, []) = [] | 
	removeElemento(elemento, x::xs) = 
	if x = elemento then
		removeElemento(elemento, xs) 
	else x::(removeElemento(elemento, xs));

(* Inserir um elemento em uma lista *)
fun inserir(elemento, []) = [elemento] | 
	inserir(elemento, x::xs) = elemento::(x::xs);

(* Soma de 0 ate N : Solução 1 *)
fun somaAteN n = 0 | somaAteN n = n + somaAteN(n-1);

(* Som elementos na lista não negativos *)
fun somaSemNegativos([]) = 0 | somaSemNegativos(x::xs) =
	if x > 0 then x + somaSemNegativo(xs)
	else somaSemNegativo(xs);