.data 
	n : .word 5

.text 
.globl main

main:
	la $t0, n
	lw $t0, 0($t0)
	
	addiu $sp, $sp, -4 # empilha o $ra
	sw $ra, 0($sp)
	addiu $sp, $sp, -4 # empilha o restante dos parametros
	sw $t0, 0($sp)
	
	jal fat
	
fat:
	lw $t1, 0($sp)
	
			