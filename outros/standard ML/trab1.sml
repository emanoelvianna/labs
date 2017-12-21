(* 
1) (3,5 pontos) Especifique a fun��o mediaListaSemPares que recebe uma lista de inteiros
umaLista e retorna a m�dia dos elementos de umaLista, descartando os valores pares (listas
vazias retornam zero)
val mediaListaSemPares : intlist -> real = _fn
*)

fun soma([]) = 0 | soma(x::xr) = x + soma(xr);

fun media([]) = 0.0 | media(lista) = real(soma(lista)) / real(length(lista));

fun removePar([]) = [] | removePar(x::xr) = if x mod 2 = 0 then removePar(xr) else x::(removePar(xr));

fun mediaListaSemPares([]) = 0.0 | mediaListaSemPares(lista) =
	let 
		val limpa = removePar(lista);
	in
		media(limpa);
	end;
(*
2) (3 pontos) Especifique a fun��o listaSemMaior que recebe uma lista de inteiros umaLista e
retorna outra lista igual a umaLista sendo exclu�do o maior valor.
listaSemMaior : intlist ->intlist = _fn
*)	

fun encontraMaior([]) = hd([]) |
encontraMaior(x::[]) = x |
encontraMaior(x::xs) =
	let 
		val m = encontraMaior(xs);
	in
		if m > x then m else x
	end;
	
fun removeElemento(elemento, []) = [] | removeElemento(elemento, x::xr) = 
	if elemento = x then 
		removeElemento(elemento, xr) 
	else 
		x::(removeElemento(elemento, xr)) 

fun listaSemMaior(umaLista) = removeElemento(encontraMaior(umaLista), umaLista);
	
(*
3) (2,5 pontos) Especifique a fun��o espelhaDobraLista que recebe umaLista e retorna outra
lista de forma que a primeira parte resultante � igual a umaLista em sequ�ncia invertida e a
segunda parte � esta mesma lista na sequ�ncia original.
espelhaDobraLista : 'a list -> 'a list = _fn
*)

fun espelhaDobraLista([]) = [] | 
espelhaDobraLista(x::[]) = [x] |
espelhaDobraLista(umaLista) =
	let
		val lista = umaLista
	in 
		rev(umaLista)@lista
	end;

(*
4) (1 ponto)Especifique a fun��o semDoisExtremos que recebe uma lista umaLista e retorna uma
lista semelhante sem os dois elementos extremos de umaLista (o primeiro e o �ltimo). Se
umaLista tiver menos de dois elementos, retorna nil.
semDoisExtremos : 'a list -> 'a list = _fn
*)

fun menosUlt([]) = [] | 
menosUlt(x::[]) = [] |
menosUlt(x::xr) = if tl(xr) = [] then [x] else x::(menosUlt(xr));

fun semDoisExtremos [] = [] | semDoisExtremos (x::xs) = if length(x::xs) = 2 then (x::xs) else menosUlt(xs);
	
