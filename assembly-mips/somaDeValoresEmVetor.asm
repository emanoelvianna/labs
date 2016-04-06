#some dois vetores (V1 e V2) em um terceiro vetor (V3).
.data
v1: .word 1 2 3 4 5 6 7 8 9 10 11
v2: .word 1 2 3 4 5 6 7 8 9 10 11
v3: .word 0x0 0x0 0x0 0X0 0x0 0x0 0x0 0X0 0x0 0x0 0x0
size: .word 11

.text 
.globl main
main:
	la $t0, v1
	la $t1, v2
	# la $2, v3
	la $t3, size
	lw $t3, 0($t3)

	li $s0, 0 # i

	loop:
		lw $t4, 0($t0) 		# $t4 <- V1[i]
		lw $t5, 0($t1) 		# $t5 <- V1[i]
		
		addu $t6, $t4, $t5
		sw $t6, v3($t2)
		
		addiu $s0, $s0, 1 # incrementa i
		addiu $t0, $t0, 4 # incrementa V1
		addiu $t1, $t1, 4 # incrementa V2
		addiu $t2, $t2, 4 # incrementa V3
	blt $s0, $t3, loop
	
	jr $ra
