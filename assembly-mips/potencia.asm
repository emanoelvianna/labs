# potências: x^y

.data 
Z: .word 0
x: .word 2
y: .word 4 

.text 
.globl main

main:
	la $t3, Z
	la $t0, x
	lw $t0, 0($t0) 
	la $t1, y
	lw $t1, 0($t1)
	li $t2, 1
loop:
	beq $t1, $t2, fim
		mul $t0, $t0, 2
		sub $t1, $t1, 1	
		sw $t0, 0($t3)	
	j loop	
	
	
fim:

	
	jr $ra
	
