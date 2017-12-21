# Escreva um programa contando quantos números pares e impares existem em um vetor de inteiros. a qantidade deve ser guardada
# em registradores chamados par e impar

.data 
	vet: .word 22 -43 55 -9 -7 21 -76 12 -45 -10
	tam: .word 10
	par: .word 0
	impar: .word 0

.text 
.globl main
main:
	la $t0, vet
	la $t1, tam
	lw $t1, 0($t1)
	la $t2, par
	lw $t2, 0($t2)
	la $t3, impar
	lw $t3, 0($t3)
	li $s0, 0 # i
loop:
	beq $s0, $t1, fim # if($s0 == $t1) then fim
	
	lw $t4, 0($t0) # $t4 <- vet[i]
	li $t6, 2
	div $t4, $t6
	mfhi $t5
	bne $t5, $zero, nuImpar # if($t5 != 0) then impar++
	addiu $t2, $t2, 1 # par++
	
	addiu $s0, $s0, 1 # incrementa i
	addiu $t0, $t0, 4 # incrementa vet
	
j loop

nuImpar:
	addiu $t3, $t3, 1 #impar++
	
	addiu $s0, $s0, 1 # incrementa i
	addiu $t0, $t0, 4 # incrementa vet
	j loop
	
fim:
	jr $ra
	
	
	
	