# Trabalho 2, pesquisa binÃ¡ria.
# Para a realizaÃ§Ã£o do trabalho utilizamos uma pilha para a passagem dos argumentos


.data
	vet : .word -5 -1 5 9 12 15 21 29 31 58 250 325 
	prim : .word 0
	ult : .word 11
	valor : .word 5 # * retorno deve ser 2
	tam: .word 11
	
.text 
.globl main

main:
	la $a0, vet 
	
	la $a1, prim
	lw $a1, 0($a1)	# ref comeÃ§o do vetor
	
	la $a2, ult 
	lw $a2, 0($a2) # ref final do vetor
	
	la $a3, valor 
	lw $a3, 0($a3) # valor a ser buscado
	
	addiu $sp, $sp, -4 # empilha o $ra
	sw $ra, 0($sp)
	addiu $sp, $sp, -16 # empilha o restante dos parametros
	sw $a0, 0($sp) # empilha A
	sw $a1, 4($sp) # empilha o Pri
	sw $a2, 8($sp) # empilha o Ult
	sw $a3, 12($sp) # empilha o valor
	
	jal BinSearch
	
	
	# tratando o valor do retorno da funcao
	
	
	jal fim

BinSearch:

	# desempilha e recursao?!
	lw $t0, 0($sp) 	# desempilha ref vetor A 
	lw $t1, 4($sp) 	# desempilha valor Pri
	lw $t2, 8($sp) 	# desempilha valor Ult
	lw $t3, 12($sp) # desempilha o valor a ser buscado	
	
	bgt $t1, $t2, retorna		# teste se prim > ult
	
	add $t5,  $t1, $t2 	# meio <- pri + ult
	li $t6, 2 		# axuliar na divisao
	div $t5, $t6 		# meio / 2
	mflo $t5		# meio recebe o resultado 	
	
	lw $t7, vet($t5)
	beq $t7, $t3, achou
		
	# else if(valor<A[meio]) retorna BinSearch(A, prim, meio-1, valor);
	blt $t3, $t7, recursao # Se valor < A[meio] então recursao, senão  
	
	# else retorna BinSearch(A, prim, meio+1, valor);
	
	addiu $sp, $sp, -4 # empilha o $ra
	sw $ra, 0($sp)
	addiu $sp, $sp, -16 # empilha o restante dos parametros
	sw $a0, 0($sp) # empilha A
	sw $a1, 4($sp) # empilha o Pri
	sw $a2, 8($sp) # empilha o Ult
	sw $a3, 12($sp) # empilha o valor
	
achou:
	# deve retornar a ref da posicao que achou o elemento
	# eu devo fazer o retorno via pilha também?!
	
recursao:
	# recursao caso valor < a[meio]

retorna:
	# deve retornar -1
	

fim:
	# fim do programa
	jr $ra

	
	
