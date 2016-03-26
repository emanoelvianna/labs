# retorna 1 caso número for primo e 0 caso contraio.

.text 
.globl main

main:
	la $t0, numero
	lw $t0, 0($t0) 		# $t0 <- numero
	
	li $t1, 2 		# $t1 <- 2
	div $t1, $t0, $t1	# $t1 <- numero / 2
	
	la $s0, resposta 	
	
	
	beq $t1, $zero, primo
		li $s3, 0	
		sw $s3, 0($s0)
		jr $ra
			
	primo:
		li $s2, 1
		sw $s2, 0($s0)
		jr $ra
	
.data 
	numero: .word 3
	resposta: .word 0