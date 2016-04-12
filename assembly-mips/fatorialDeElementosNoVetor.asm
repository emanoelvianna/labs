# Criar um vetor A com 5 elementos inteiros. Construir um vetor B de mesmo tamanho, 
# sendo que cada elemento do vetor B seja o fatorial do elemento correspondente em A.

.data 
	A: .word 5 5 
	B: .word 0 0  
	tam: .word 2
.text 
.globl main
main:
	la $t0, A
	la $t1, B
	
	la $t2, tam
	lw $t2, 0($t2)
	beq $t2, $zero, fim 	# já cai fora de primeira se tamanho igual a zero
	
	loop:
		beq $t2, $zero, fim 	# já cai fora de primeira se tamanho igual a zero
		move $v0, $ra		# guarda o contexto para a volta
		beq $t2, $zero, fim
		lw $t3, 0($t0) 		# $t3 <- A[i]
		
		jal calcula_fotorial
		move $ra, $v0
		
		sw $t5, 0($t1)
		li $t5, 0 	# limpa o registrador $t5
		addiu $t0, $t0, 4 	# incrementa A
		addiu $t1, $t1, 4 	# incrementa B
		sub $t2, $t2, 1
	j loop
	
	fim:
		jr $ra
	
	calcula_fotorial:
		addiu $t5, $t5, 1 	# auxiliar
		loop2:
			beq $t3, $zero, volta
			mul $t5, $t5, $t3 	# calcular o fatorial  
			sub $t3, $t3, 1
		j loop2
	volta:
		jr $ra
	
		
		
	
	 
