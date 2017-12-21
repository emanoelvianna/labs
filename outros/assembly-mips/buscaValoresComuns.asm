#  Escreva um programa em linguagem de montagem do MIPS que leia dois vetores (V1 e V2) e	
# armazene no terceiro vetor (V3) os elementos comuns desses dois vetores. Por exemplo, se	
# V1[0] == V2[0], então V3[0]=V1[0], caso contrário (V1[0] != V2[0]), não escreva nada em V3[0].	
# No final, a variável "comum" deve conter o número de elementos comuns aos dois vetores (V1 e V2).

.data
v1: .word 0x12 0xff 0x3 0x14 0x878
v2: .word 0x12 0x03 0x33 0x04 0x5
v3: .word 0x0 0x0 0x0 0x0 0x0
size: .word 5
comum: .word 0

.text 
.globl main
main:
	la $t0, v1 
	la $t1, v2
	la $t2, size
	lw $t2, 0($t2)	# $t2 <- tamanho do vetor
	li $s0, 0	# i
	loop:
		lw $t4, 0($t0)	# $t4 <- v1[i]
		lw $t5, 0($t1)	# $t4 <- v1[i]
		beq $t4, $t5, igual
	
		addiu $s0, $s0, 1 # incrementa o i
		addiu $t0, $t0, 4 # incrementa o v1
		addiu $t1, $t1, 4 # incrementa o v2
		addiu $t3, $t3, 4 # incrementa o v3
	blt $0, $t2, loop
	
	
	igual:
		
	
	jr $ra