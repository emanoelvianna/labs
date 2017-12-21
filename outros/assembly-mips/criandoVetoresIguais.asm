# Criar um vetor A com 5 elementos inteiros. 
# Construir um vetor B de mesmo tipo e tamanho e com os "mesmos" elementos do vetor A, 
# ou seja, B[i] = A[i].

.data 
	A: .word 0 1 2 3 4
	B: .word 0 0 0 0 0
	tam: .word 5
.text 
.globl main
main:
	la $t0,	A
	la $t1,	B
	
	la $t2, tam
	lw $t2, 0($t2)
	
	beq $t2, $zero, fim # caso tamanho igual a zero cai fora
	loop:
		beq $t2, $zero, fim # caso tamanho igual a zero cai fora
		lw $t4, 0($t0) # A[i]
		sw $t4, 0($t1) # B[i]	
		sub $t2, $t2, 1
		
		addiu $t0, $t0, 4 # incrementa A
		addiu $t1, $t1, 4 # incrementa B
	j loop		
		
		
	fim:
		jr $ra