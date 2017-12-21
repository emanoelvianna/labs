#	int array[4]; 
#
#	int function1()
#	{
#		int z;
#		int i;
#		for (i = 0; i < 1024; i++)
#			array[i] = i;
#		z = sum(1024);
#	}

#	int sum(int n)
#	{
#		int s = 0;
#		int i;
#		for (i = 0; i < n; i ++)
#			s = s + vector[i];
#		return s;
#	}

# Seguindo a convenção do mips sobre parâmetros e retornos 

.data 
	Z: .word 0
	VET: .word 0 0 0 0
	TAM: .word 4
	S: .word 0
.text
.globl main

main:
	li $s0, 0 # i 

	la $t1, VET # leio vetor
	
	la $t2, TAM # leio tamanho do vetor
	lw $t2, 0($t2)

	la $t3, Z

	la $t4, S
	lw $t4, 0($t4)

	loop:
		beq $s0, $t2, continua # if(i==tam)então pula para continuar
		
		sw $s0, 0($t1) # vet[i] = i
		
		addu $s0, $s0, 1 # incrementa o i
		addu $t1, $t1, 4 # anda pelo vetor

		j loop

	continua:
		addiu $sp, $sp, -4 # abriu espaço na pilha
		sw $ra, 0($sp)
		addu $a0, $t2, $zero # passando por argumento o tam (seguindo a conversão mips) $a0 <- tam
		jal sum
		lw $ra, 0($sp) # desimpilha $ra e coloca no $ra
		addiu $sp, $sp, 4 # retorna posicao da pilha 
		
		addu $t5, $v0, $zero # $t5 recebe o retorno da função
		sw $t5, 0($t3) # coloca o retorno da função em $t3
		
		j fim


	sum:
		li $t7, 0 # i interno
		la $t8, VET # ref inicial vetor

		loop2:
			beq $t7, $a0, retorna # se $t9 == $a0 então retorna
			lw $t9, 0($t8) # carrega o valor do vetor na pos vet[i]
			add $t4, $t4, $t9 # s = s + vet[i]
			
			addu $t7, $t7, 1 # incrementa o i
			addu $t8, $t8, 4 # anda pelo vetor
			
			j loop2
	retorna:
		add $v0, $t4, $zero # $v0 <- s	
		jr $ra
	
	fim:
		jr $ra





