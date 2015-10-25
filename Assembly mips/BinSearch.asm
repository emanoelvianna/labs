# Trabalho 2, pesquisa binária.
# Para a realização do trabalho utilizamos uma pilha para a passagem dos argumentos


.data
	vet : .word -5 -1 5 9 12 15 21 29 31 58 250 325 
	prim : .word 0
	ult : .word 11
	valor : .word 5 # * retorno deve ser 2
	
	
.text 
.globl main

main:
	la $a0, vet 
	
	la $a1, prim
	lw $a1, 0($a1)	# ref começo do vetor
	
	la $a2, ult 
	lw $a2, 0($a2) # ref final do vetor
	
	la $a3, valor 
	lw $a3, 0($a3) # valor a ser buscado
	
	addiuj $sp, $sp, -4 # preparando para empilhar o $ra
	sw $ra, 0($sp) # empilha o $ra
	
	jal BinSearch
	
	# tratando o valor do retorno da funcao
	
	
	jal fim

BinSearch:

	# desempilha e recursao?!
	lw $s0, 0($sp) 	# desempilha o valor a ser buscado
	lw $s1, 0($sp) 	# desempilha valor Ult
	lw $s2, 0($sp) 	# desempilha valor Pri
	lw $s3, 0($sp) 	# desempilha ref vetor A
	
	slt $s4, $s1, $s2 		# teste se ult < prim
	beq $s4, $zero, retorna 	# caso sim retorna erro, se não continua
	
	add $s5,  $s2, $s1 	# meio <- pri + ult
	li $s6, 2 		# axuliar na divisao
	div $s5, $s6 		# meio / 2
	mflo $s5		# meio recebe o resultado 	
		
	addu $s7, $s3, $s5	# $s1 <- A[meio]
	beq $s2, $s0, acho	# caso $s7 == $s0 achou o elemento
	
	# else if(valor<A[meio]) retorna BinSearch(A, prim, meio-1, valor);
	# else retorna BinSearch(A, prim, meio+1, valor);
	
	# recursao?!
	
acho:
	# deve retornar a ref da posicao que achou o elemento

retorna:
	# deve retornar -1
	

fim:
	# fim do programa
	jr $ra

	
	
