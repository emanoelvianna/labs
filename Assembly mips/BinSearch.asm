# Trabalho 2, pesquisa binária.
#
# Para a realização do trabalho utilizamos uma pilha
#


.data
	vet : .word -5 -1 5 9 12 15 21 29 31 58 250 325 
	prim : .word 0
	ult : .word 11
	meio : .word 0 # * ira variar segundo tam vetor
	valor : .word 5 # * retorno deve ser 2
	
.text 
.globl main

main:
	la $t0, vet 
	
	la $t1, prim
	lw $t1, 0($t1)	# ref começo do vetor
	
	la $t2, ult 
	lw $t2, 0($t2) # ref final do vetor
	
	la $t3, meio
	lw $t3, 0($t3) # meio ( auxilio para o método )
	
	la $t4, valor 
	lw $t4, 0($t4) # valor a ser buscado
	
	addiu $sp, $sp, -4 # empilha o $ra
	sw $ra, 0($sp)
	addiu $sp, $sp, -16 # empilha o restante dos parametros
	sw $t0, 0($sp) # passa para a pilha o A
	sw $t1, 4($sp) # passa para a pilha o Pri
	sw $t2, 8($sp) # passa para a pilha o Ult
	sw $t3, 12($sp) # passa para a pilha o Tam
	
	jal BinSearch
	
	# tratando o valor do retorno da funcao
	lw      $ra, 0($sp)      
        lw      $t0, 4($sp)       
        addiu   $sp, $sp, 12  
	
	
	jal fim

BinSearch:

	# desempilha e recursao?!
		
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

	
	
