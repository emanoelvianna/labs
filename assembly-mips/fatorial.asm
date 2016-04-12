# funcao que recebe um numero inteiro e retorna o seu fatorial.

.data 
	numero: .word 0
	resultado: .word 0
.text 
.globl main
main:
	li $v0, 5
	syscall
	move $t1, $v0 # move numero lido para $t0
	
	la $t0, numero
	lw $t0, 0($t0)	
	move $t0, $t1	# move numero lido para numero
	
	addi $t2, $t2, 1	# só para auxiliar na multiplicacao
	loop:
		beq $t1, $zero,fim 	# caso $t1 igual a zero, cai fora	
		mul $t2, $t2, $t1	# caso contrario multiplica ele novamente
		sub $t1, $t1, 1
		move $s0, $t2		# guarda o resultado em $s0
	j loop
	
	fim:
		li $v0, 1
		move $a0, $s0
		syscall
		jr $ra