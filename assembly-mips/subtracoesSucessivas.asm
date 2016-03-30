.data 
	Dividendo:	.word 10
	Divisor: 	.word 3
	
.text 
.globl main

main:
	la $t0, Dividendo
	lw $t0, 0($t0)
	
	la $t1, Divisor
	lw $t1, 0($t1)
	
	# $a0 = resto
	
	sub $a0, $t0, $t1
	li $a1, 1
	loop:
		blt $a0, $t1, fim
		addu $t0, $zero, $a0 # só para reutilizar o $a0 abaixo
		sub $a0, $t0, $t1
		
		li $a2, 1
		addu $a1, $a1, $a2
		
		j loop
	
	fim:
		jr $ra
		
	
	