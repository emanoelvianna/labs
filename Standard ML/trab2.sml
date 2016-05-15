val turmas =
[
	[
		(* primeira turma *)
		("12345678", "Delo",[ 8.0,10.0,6.0]),
		("12345677", "Tulo",[ 9.0,9.0,9.0]),
		("12345676", "Balo",[3.0,5.0,4.0])
	],
	[	
		(* segunda turma *)
		("12345675", "Meno",[7.0,7.0,7.0]),
		("12345674", "Leno",[5.0,6.0,7.0])
	]
]; 

(*
1 X (3 pontos) Considerando todos os alunos de todas as turmas, obter média de G1
dentre os que passaram em G1.
Na base de dados apresentada, a média é 8.0 
*)

fun soma([]) = 0 | soma(x::xr) = x + soma(xr);

fun media([]) = 0.0 | media(lista) = real(soma(lista)) / real(length(lista));

fun buscarNotas(m, []) = [] | buscarNotas(m, ((( matricula, nome, notas))::sz)::xr) = if m = matricula then notas else buscarNotas(m, (sz)::xr);



