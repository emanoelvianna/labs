# Trabalho 2, pesquisa binÃ¡ria.
# Para a realizaÃ§Ã£o do trabalho utilizamos uma pilha para a passagem dos argumentos


.data
	res: .word 0
	vet : .word -5 -1 5 9 12 15 21 29 31 58 250 325 
	prim : .word 0
	ult : .word 11
	valor : .word 58 # * retorno deve ser 2
	tam: .word 12
.text 
.globl main

main:
	la $a0, vet 
	
	la $a1, prim
	lw $a1, 0($a1)	# ref comeco do vetor
	
	la $a2, ult 
	lw $a2, 0($a2) # ref final do vetor
	
	la $a3, valor 
	lw $a3, 0($a3) # valor a ser buscado
	
	addiu $sp, $sp, -4 # empilha o $ra
	sw $ra, 0($sp)

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $a0, 0($sp) # empilha A

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $a1, 0($sp) # empilha o Pri

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $a2, 0($sp) # empilha o Ult

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $a3, 0($sp) # empilha o valor
	
	jal BinSearch

	lw $s3, 0($sp) # lê o index retornado da pilha

	addiu $sp, $sp, 4 # tira esp da pilha
	lw $ra, 0($sp)
	addiu $sp, $sp, 4

	addiu $sp, $sp, -4 # empilha o index
	sw $s3, 0($sp)
	
	# PRECISO tratar o valor do retorno da funcao
	
	j fim

BinSearch:

	# desempilha e recursao?!

	lw $t3, 0($sp) # desempilha o valor a ser buscado
	addiu $sp, $sp, 4

	lw $t2, 0($sp) 	# desempilha valor Ult
	addiu $sp, $sp, 4
	
	lw $t1, 0($sp) 	# desempilha valor Pri
	addiu $sp, $sp, 4
		
	lw $t0, 0($sp) 	# desempilha ref vetor A 
	addiu $sp, $sp, 4
	
	bge $t1, $t2, retorna	# teste se prim > ult
	
	add $t5,  $t1, $t2 	# meio <- pri + ult
	li $t6, 2 		# axuliar na divisao
	div $t5, $t6 		# meio / 2
	mflo $t5		# meio recebe o resultado 
	
	add $t6, $t5, $t5 	# caminhando no vetor 4 em 4	
	add $t6, $t6, $t6 	# i * 4

	# $t9 guarda a ref para o indice do vetor
	add $t9, $t6, $t0 	# $t9 <- A[meio]

	lw $t7, 0($t9) 		# $t7 <- recebe valor A[meio]
	beq $t7, $t3, achou	# caso $t7 == $t3 achou o valor a ser buscado
		
	# else if(valor<A[meio]) retorna BinSearch(A, prim, meio-1, valor);
	blt $t3, $t7, recursao # Se valor < A[meio] então recursao, senão  
	
	# else retorna BinSearch(A, prim, meio+1, valor);
	addiu $t5, $t5, 1 # meio + 1

	addiu $sp, $sp, -4 # empilha o $ra
	sw $ra, 0($sp)

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t0, 0($sp) # empilha A

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t5, 0($sp) # empilha o MEIO+1

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t2, 0($sp) # empilha o Ult

	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t3, 0($sp) # empilha o valor

	jal BinSearch
	
	lw $s3, 0($sp) # lê o index retornado da pilha
	addiu $sp, $sp, 4 # tira esp da pilha
	lw $ra, 0($sp)
	addiu $sp, $sp, 4
	
	addiu $sp, $sp, -4 # empilha o index
	sw $s3, 0($sp)

	jr $ra

achou:
	# deve retornar a ref da posicao que achou o elemento
	add $v0, $t5, $zero	
	addiu $sp, $sp, -4
	sw $v0, 0($sp)

	jr $ra
	

recursao:
	# realiza retorna BinSearch(A, prim, meio-1, valor);
	addiu $t5, $t5, -1 # meio - 1
	 
	addiu $sp, $sp, -4 # empilha o $ra
	sw $ra, 0($sp)
	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t0, 0($sp) # empilha A
	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t1, 0($sp) # empilha o Pri
	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t5, 0($sp) # empilha o MEIO-1
	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t3, 0($sp) # empilha o valor
	
	jal BinSearch	

	lw $s3, 0($sp) # lê o index retornado da pilha
	addiu $sp, $sp, 4 # tira esp da pilha
	lw $ra, 0($sp)
	addiu $sp, $sp, 4
	
	addiu $sp, $sp, -4 # empilha o index
	sw $s3, 0($sp)

	jr $ra

retorna:
	# deve retornar -1
	li $v0, -1
	addiu $sp, $sp, -4
	sw $v0, 0($sp)
	jr $ra

fim:
	# fim do programa
	jr $ra
