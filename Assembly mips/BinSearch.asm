# Trabalho 2, pesquisa binária.
#
# Para a realização do trabalho utilizamos uma pilha
#


.data
	vet : .word -5 -1 5 9 12 15 21 29 31 58 250 325 
	prim : .word 0
	ult : .word 11
	valor : .word 5 # * retorno deve ser 2
	
	meio : .word 0 # * ira variar segundo tam vetor
	
	
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
	
	addiu $sp, $sp, -16 # empilha o restante dos parametros
	sw $a0, 0($sp) # passa para a pilha o A
	sw $a1, 4($sp) # passa para a pilha o Pri
	sw $a2, 8($sp) # passa para a pilha o Ult
	sw $a3, 12($sp) # passa para a pilha o Valor a ser buscado
	
	jal BinSearch
	
	# tratando o valor do retorno da funcao
	
	
	jal fim

BinSearch:

	# desempilha e recursao?!
	lw $t0, 0($sp) # desempilha o valor a ser buscado
	lw $t1, 0($sp) # desempilha valor Ult
	lw $t2, 0($sp) # desempilha valor Pri
	lw $t3, 0($sp) # desempilha ref vetor A
	
	
		
	add $a1, $t1, $zero # $a1 <- ref primeiro 
	add $a2, $t2, $zero # $a2 <- ref ultimo
	# if (Prim > Ult) return -1; // Valor não existe
	slt $s0, $a2, $a1
	beq $s0, $0, erro # Se verdade continua, se não erro	
	
	add $s1, $a1,$s2  # $s1 <- prim + ult
	li $s2, 2 # auxiliar na divisao
	div $s1, $s2 # $s1 / 2
	mflo $s1 # $s1 <- $s1 / 2
	
	# if (Valor == A[Meio])
	
	# else if (Valor<A[Meio])
	
	# recursao?!

erro:
	# retorno -1
	

fim:
	jr $ra

	
	
