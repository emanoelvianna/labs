
(*
23) Dada uma base de dados com nome, idade, matrícula e notas de 3 disciplinas para cada um de N
alunos, encontrar
a) a idade do aluno com um determinado nome
b) encontrar as notas de um aluno com determinada matrícula
c) encontrar a nota da segunda disciplina de um aluno que tirou uma dada nota na primeira
*)

val alunos = [("Fulano",17,"12345678",[9.5,8.7,10.0]),("Beltrano",18,"87654321",[5.2,6.6,4.1])];

(* a) a idade do aluno com um determinado nome *)
fun buscaIdade(n, []) = 0 | buscaIdade(n, (nome, idade, matricula, notas)::xr) = if n = nome then idade else buscaIdade(n, xr);

(* b) encontrar as notas de um aluno com determinada matrícula *)
fun buscarNotas(m, []) = [] | buscarNotas(m, (nome, idade, matricula, notas)::xr) = if m = matricula then notas else buscarNotas(m, xr);

(* c) encontrar a nota da segunda disciplina de um aluno que tirou uma dada nota na primeira *)
fun buscarSegundaNota(n, []) = 0.0 | 
	buscarSegundaNota(n, (nome, idade, matricula, [p1, p2,p3])::xr) = 
	if p1 = n then 
		p2 
	else 
		buscarSegundaNota(n, xr);


(* Dada uma base de dados com nome do produto, custo unitário e quantidade comprada de cada um de N produtos, calcular o total a ser pago.  *)

val compra = [("Produto1", 2.0, 5), ("Produto2", 3.0, 2)];

fun total([]) = 0.0 | total((produto, valor, quantidade)::xr) = valor * real(quantidade) + total(xr);

(* Dada uma lista, substituir toda a ocorrência de um elemento por outro. 
Por exemplo: a lista [10,2,3,10,4] resulta da substituição de 1 por 10 na lista [1,2,3,1,4].  *)
fun substitui(s, n, []) = [] | substitui(s, n, x::xr) = if x = n then s::(substitui(s, n, xr)) else x::(substitui(s, n, xr));
	
(* Verificar se um número inteiro é primo *)

fun primo n =
let
	fun pri (x,d) = d > x div 2 orelse x mod d <> 0 andalso pri(x,d+1)
in
	pri (n,2)
end;


	
