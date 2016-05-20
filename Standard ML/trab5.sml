val turmas =
[
[("12345678", "Delo",[ 8.0,10.0,6.0]),("12345677", "Tulo",[ 9.0,9.0,9.0]),("12345676", "Balo",[3.0,5.0,4.0])],
[("12345675", "Meno",[7.0,7.0,7.0]),("12345674", "Leno",[5.0,6.0,7.0])]
] ; 

(* funções de apoio *)

fun maiorDeLista [] = hd([])
| maiorDeLista (x::[]) = x
| maiorDeLista (x::xs) = 
let 
	val m = maiorDeLista(xs)
in
	if x > m then x else m
end;

fun somaLista [] = 0.0
| somaLista (c::r) = c + somaLista r;

fun mediaLista [] = 0.0
| mediaLista umaLista = somaLista umaLista / real(length umaLista);  

fun aprovadosG1 [] = []
| aprovadosG1 ((m,n,notas)::xs) = if mediaLista(notas) >= 7.0 then mediaLista(notas)::aprovadosG1(xs) else aprovadosG1(xs) ; 

fun passadosEmG1TodasAsTurmas [] = []
| passadosEmG1TodasAsTurmas (t::tt) = aprovadosG1(t)@passadosEmG1TodasAsTurmas(tt);

(* questão 1 *)
fun resposta1 [] = 0.0
| resposta1 lista = mediaLista(passadosEmG1TodasAsTurmas(lista));

(* teste *)
fun retornaMaior(x,y)= if x > y then x else y;

fun encontraMaior([])= hd([]) | encontraMaior(x::[]) = x | encontraMaior(x::xs) =
	let 
		val m = encontraMaior(xs);
	in
		if m > x then m else x
	end;

fun retiraUltimo(lista) = tl(lista);




