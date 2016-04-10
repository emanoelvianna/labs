# Fazer uma função que cacula a enésima potência de uma variável real x, f(x, n): x^n

.data 
	x: .word 
	n: .word 
.text 
.globl main
main:	
	li $v0, 5	# "informe o valor do x:"
	syscall
	move $t0, $v0
	li $v0, 5	# "informe o valor do n:"
	syscall 
	move $t1, $v0
	
	loop:
		beq $t1, $zero, fim
		mult $t0, $t0
		mflo $t2
		sub $t1, $t1, 1	
	j loop
	
	fim:
		li $v0, 1	# imprimir resposta
		move $a0, $t0
		syscall
		jr $ra