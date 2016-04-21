# Escreva um programa em linguagem de montagem do MIPS que leia	o vetor	SALARIO, que	
# contém todos os salários anuais de um vendedor, faça o somatório dos mesmos e guarde o total	
# de salários recebidos no ano(SAL_SOMA), o maior salário (SAL_MAIOR) e o menor salário
# (SAL_MENOR) nas suas respectivas variáveis.

.data
	SALARIO: .word 0x8FC 0x834 0x802 0xABE 0x7BC 0xA28 0x9C4 0xA4B 0xAF0 0xB5E 0xADC 0xA78
	SAL_TAM: .word 12
	SAL_SOMA: .word 0
	SAL_MAIOR: .word 0
	SAL_MENOR: .word 0
.text 
.globl main
main:
	la $t0,SALARIO 		# referencia do vetor de salarios em $t0
	la $t1, SAL_TAM		# $t1 <- tamanho do vetor
	lw $t1, 0($t1) 
	
	la $t2, SAL_MAIOR	# $t2 ira guardar o valor SAL_MAIOR
	lw $t2, 0($t2)
	
	la $t3, SAL_MENOR	# $t3 ira guardar o valor SAL_MENOR
	lw $t3, 0($t3)
	
	lw $t2, 0($t0)		# $t2 recebe o primeiro valor do vetor
	lw $t3, 0($t0)		# $t3 recebe o primeiro valor do vetor
	
	la $t4, SAL_SOMA	# $t4 ira guardar o valor SAL_SOMA
	lw $t4, 0($t4)
	
	loop:
		beq $t1, $zero, fim
		lw $t5, 0($t0)		# $t5 recebe o valor atual no ponteiro para o vetor
		add $t4, $t4, $t5	# realiza a soma dos salarios
		
		blt $t5, $t3, menor	# caso $t5 menor que $t3, então $t3 recebe o valor do $t5
	volta1:
		bgt $t5, $t2, maior	# caso $t5, maior que $t3, então $t3 recebe o valor do $t5
	volta2:		
		addiu $t0, $t0, 4	# incrementa o ponteiro para o vetor
		sub $t1, $t1, 1		# decrementa o a referencia para o tam do vetor
	j loop
	
	menor:
		move $t3, $t5
		j volta1
		
	maior:
		move $t2, $t5
		j volta2
	
	fim:
		jr $ra