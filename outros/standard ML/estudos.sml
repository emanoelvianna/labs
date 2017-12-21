(* soma de n numeros *)
fun somaNumeros([]) = 0 | somaNumeros(x::r) = x + somaNumeros(r);

(* produto de n numeros *)
fun produto([]) = 1 | produto(x::r) = x * produto(r);

(* media de n numeros *)
fun media([]) = 0.0 | media(lista) = real(somaNumeros(lista)) / real(length(lista));

(* econtra o maior *)
fun retornaMaior(n1, n2) = if n1 > n2 then n1 else n2;

(* verifica se o numero maior é multiplo do menor *)
fun eMultiplo(n1, n2) =  
let 
	val maior = retornaMaior(n1, n2)
	val menor = n1 + n2 - maior
in 
	if maior mod menor = 0 then true else false
end; 

(* remove elemento da lista *)
fun removeElemento([], elemento) = [] | removeElemento(x::r, elemento) = 
	if x = elemento then removeElemento(r, elemento) 
	else x ::(removeElemento(r, elemento));

(* inserir elemento no inicio da lista *)
fun inserirElementoNoInicio([], elemento) = [elemento] | inserirElementoNoInicio(x::r, elemento) = elemento::(x::r);

(* inserir elemento no final da lista *)
fun inserirElementoNoFinal([], elemento) = [elemento] | inserirElementoNoFinal(x::r, elemento) = rev(elemento::(rev(x::r)));

(* calcula a soma de inteiros de 1 a N *)
fun somaUmAteN(0) = 0 | somaUmAteN(n) = n + somaUmAteN(n-1);  

(* retira o ultimo da lista *)
fun retiraUltimo [] = [] |
    retiraUltimo(x::[]) = [] |
    retiraUltimo(x::r)= if tl(r) = [] then [x] else x :: retiraUltimo(r);
    
(* retira valores pares *)
fun retiraPares([]) = [] | retiraPares(x::r) = if x mod 2 = 0 then retiraPares(r) else x::(retiraPares(r));

(* media de lista sem pares *)
fun mediaSemPares([]) = 0.0 | mediaSemPares(lista) = media(retiraPares(lista));

(* encontra o maior da lista *)
fun encontraMaior(lista) = hd([]) 
| encontraMaior(x::[]) = x
| encontraMaior(x::xs) =
let 
	val m = encontraMaior(xs)
in
	if x > m then x else m
end;
 
	



