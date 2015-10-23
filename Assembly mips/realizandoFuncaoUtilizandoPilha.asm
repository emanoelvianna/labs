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


# OBSERVAÇÂO: aqui não estamos usando as convenções do mips
# estamos usando uma pilha para a passagem de parametros e retorno de funções

.data 
	Z: .word 0
	Vet: .word 0 0 0 0
	Tam: .word 4
	Soma: .word 0
.text 
.globl main

main:
	li $t0, 0 # i 
	
	la $t1, Vet
	la $t2, Tam
	lw $t2, 0($t2)	
	la $t3, Soma
	lw $t3, 0($t3)
	la $t6, Z

	# primeiro loop da main
	loop:
		beq $t0, $t2, fimLoop
		sw  $t0, 0($t1) # vet[i] = i
		
		addu $t0, $t0, 1 
		addu $t1, $t1, 4
	j loop
	
	# aqui ira ser chamado a função
	fimLoop:
		addiu $sp, $sp, -4 # abriu espaço na pilha para guardar o $ra
		sw $ra, 0($sp)
		addiu $sp, $sp, -4 # abriu mais espaço na pilha para guardar o tam do vetor
		sw $t2, 0($sp)
		jal sum 
		lw $t7, 0($sp) # pega o valor de retorno da soma
		lw $ra, 0($sp) # desempilha nosso $ra
		
		add $sp, $sp, 8

		sw $t7, 0($t6) # coloca o retorno da função em $t5
		jr $ra

	sum:
		lw $s0, 0($sp) # pegou o tamanho do vetor passado empilhado


		li $s1, 0 # auxiliar para o loop

		# como no primeio for eu já percorri todo o meu vetor eu aqui estou lendo ele de novo 
		# em outro registrador para ter ele no inicio e não me preocupar com fazer o pnteiro retornar para o inicio 
		# caso eu quissese usar o mesmo registrador $t1
		la $t4, Vet 


		loop2:
			beq $s1, $s0, fimLoop2
			lw $t5, 0($t4)
			add $t3, $t3, $t5 # s = s + vet[i]

			addiu $s1, $s1, 1
			addiu $t4, $t4, 4 
			
		j loop2

		fimLoop2:
		# aqui vamos retornar para a função main
		
			# vamos empilhar o resultado
			sw $t3, 0($sp)
			jr $ra



