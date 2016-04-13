# Ordenação de Vetor com bubble sort 
.data
	vetor: .word 3 2 1 0
	tamanho: .word 4
.text 
.globl main
main:
	la $t0, vetor 
	la $t1, tamanho
	lw $t1, 0($t1)
	
	beq $1, $zero fim 	# Caso tamanho zero cai fora!
	loop:
		beq $1, $zero fim 	# Caso tamanho zero cai fora!
		
		lw $t2, 0($t0)		# $t2 <- vetor[i]
		
		addiu $t0, $t0, 4	# vetor[i+1]
		lw $t3, 0($t0)		# $t3 <- vetor[i+1]
		slt $t1,$t2,$t3      # checks if $s0 > $s1
		
	j loop
	fim:
		jr $ra
	
	