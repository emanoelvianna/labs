(* programas de estudos *)
fun prefixo([], []) = true |
prefixo(lista, []) = false |
prefixo(a::[], b::br) = a = b |
prefixo(a::p, b::l) = a = b andalso prefixo(p, l);

fun sufixo(L, S) = prefixo(rev(L), rev(S)); 
	
fun soma(n) = if n > 1 then n + soma(n-1) else 1;

fun retiraNegativos([]) = [] | retiraNegativos(x::xr) = if x >= 0 then x :: retiraNegativos(xr) else retiraNegativos(xr);

fun maior([]) = hd([]) | maior(x::[]) = x | maior(x::xr) =
	let 
		val aux = maior(xr);
	in 
		if x > aux then x else aux
	end;	

fun retiraUltimo(lista) = rev(tl(rev(lista)));

(* exercicios com base de dados *)
val alunos = [("Fulano",17,"12345678",[9.5,8.7,10.0]),("Beltrano",18,"87654321",[5.2,6.6,4.1])];


(*
23) Dada uma base de dados com nome, idade, matrícula e notas de 3 disciplinas para cada um de N
alunos, encontrar
a) a idade do aluno com um determinado nome,
b) encontrar as notas de um aluno com determinada matrícula e
c) encontrar a nota da segunda disciplina de um aluno que tirou uma dada nota na primeira. 
*)


fun idadeDadoNome(nome,[]) = 0
| idadeDadoNome(nome,(n,i,m,notas)::xs) = if nome = n then i else idadeDadoNome(nome,xs);
	
fun buscaNotas(m, []) = [] 
| buscaNotas(m, (nome, idade, matricula, notas)::xr) = if m = matricula then notas else buscaNotas(m, xr);

fun buscaNotaP2(n1, []) = 0.0
| buscaNotaP2(n1, (nome, idade, matricula, [])::r) = buscaNotaP2(n1,r)
| buscaNotaP2(n1, (nome, idade, matricula, [p1, p2, p3])::r)) = if n1 = p1 then p2 else buscaNotaP2(r);
