# --------------------------
# --      Trabalho 2      --
# --------------------------
# Aluno 1: EMANOEL A VIANNA FABIANO
# Aluno 2: MATHEUS BRITZKE
# --------------------------
.data
  CPF:  .word 6 4 6 3 1 1 5 8 0 0 0 
              1 3 7 6 6 1 2 8 8 7 6 
              8 6 2 7 5 5 4 2 2 5 7 
              1 7 8 1 8 8 8 7 2 8 8 
              8 2 2 4 3 4 1 5 0 0 3 
              3 5 7 5 9 0 4 2 5 0 2 
              1 1 1 5 8 1 3 3 3 1 6 
              2 1 7 3 6 0 4 1 3 9 8 
              0 3 3 1 5 2 8 5 1 8 1 
              4 7 6 3 2 4 5 2 8 7 7 
              2 4 0 7 4 8 1 4 6 1 7 
              0 1 6 7 6 8 5 7 9 8 8 
              6 4 1 0 1 3 1 5 3 4 5 
              3 8 0 7 5 3 2 5 6 8 7
              4 1 2 2 2 4 9 3 6 8 3
	NUM:        .word 15
  	SIZE:       .word 11
	ponto:      .asciiz "."
	traco:      .asciiz "-"
	nova_linha: .asciiz "\n"
	
.text                   
.globl  main
main:
	addiu $sp, $sp, -4	# abre espaço na pilha
	sw    $ra, 0($sp)	# guarda o $ra
	
	la $t0, NUM
	lw $t0, 0($t0)	# faz a leitura do NUM
	
	li $t1, 0	# i = 0
	loop:
		beq $t1, $t0, fim	# i == NUM então fim
		move $a0, $t1	  	# $a0 = pos
		
		jal dig_1		# dig_1(i);
		move $t2, $v0		# digito1 = dig_1(i);
		jal dig_2		# dig_2(i);
		move $s0, $v0
		
			# if((digito1==CPF[i][9]) && (digito2==CPF[i][10]))
			mul $s1, $t1, 44	# aux = i * 44
			addiu $s1, $s1, 32	# aux = aux + 32
			lw $s2, CPF($s1)	# $t2 = CPF[i][9]
			
			mul $s3, $t1, 44	# aux = i * 44
			addiu $s3, $s3, 36	# aux = aux + 36
			lw $s4, CPF($s3)	# $t2 = CPF[i][10]
			
			beq $t2, $s2, continua3
			continua3:
			beq $s0, $s4, imprime
			
			imprime:
				move $a0, $t1
				jal imprime_cpf
				
			invalido:	
			addiu $t1, $t1, 1	# i++
	j loop
	
	fim:
		lw    $ra, 0($sp)
		addiu $sp, $sp, 4
		li	  $v0, 10
		syscall
		jr    $ra		 

dig_1:
  	addiu $sp, $sp, -4	# abre espaço na pilha
	sw    $ra, 0($sp)	# guarda o $ra	
	
	li $t3, 0		# somador=0;
	li $t4, 0		# i = 0
	li $t6, 0		# auxiliar para ajudar a percorrer o vetor
 
	mul $a0, $a0, 44	# usado para percorrer a linha  
	addu $t6, $t6, $a0	# proxima linha
	
	loop2:
		beq $t4, 9, continua
		li $t5, 10
		sub $t5, $t5, $t4	# 10-i
		lw $t7, CPF($t6)	# CPF[pos][i] , lê a posição do vetor
		mul $t7, $t7, $t5 	# CPF[pos][i]*(10-i)
		addu $t3, $t3, $t7	# somador+=CPF[pos][i]*(10-i)

		addiu $t4, $t4, 1	# i++
		addiu $t6, $t6, 4	# posição do vetor ++

	j loop2
	
	continua:
		div $t4, $t3, 11	# somador / 11
		mfhi $t4		# $t4 recebe o resto da divisão, $t4 é o resultado
		
		beq $t4, $zero, retornaZero	# if(resultado==0) 
		beq $t4, 1, retornaZero		# if(resultado==1)
		
		else:
			li $t3, 11
			sub $v0, $t3, $t4
			j sai
		
		retornaZero:
			move $v0, $zero		# return 0
 			j sai
	
	sai:
		lw    $ra, 0($sp)
		addiu $sp, $sp, 4 
  		jr $ra

dig_2:
  	addiu $sp, $sp, -4	# abre espaço na pilha
	sw    $ra, 0($sp)	# guarda o $ra	
	
	li $t3, 0		# somador=0;
	li $t4, 0		# i = 0
	li $t6, 0		# auxiliar para ajudar a percorrer o vetor
 
	mul $a0, $a0, 44	# usado para percorrer a linha  
	addu $t6, $t6, $a0	# proxima linha
	
	loop3:
		beq $t4, 10, continua2
		li $t5, 11
		sub $t5, $t5, $t4	# 11-i
		lw $t7, CPF($t6)	# CPF[pos][i] , lê a posição do vetor
		mul $t7, $t7, $t5 	# CPF[pos][i]*(11-i)
		addu $t3, $t3, $t7	# somador+=CPF[pos][i]*(10-i)

		addiu $t4, $t4, 1	# i++
		addiu $t6, $t6, 4	# posição do vetor ++

	j loop3
	
	continua2:
		div $t4, $t3, 11	# somador / 11
		mul $t6, $t4, 11	# $t4 recebe o resto da divisão, $t4 é o valor
		
		sub $t5, $t3, $t6	# resultado=somador-valor;
	
		beq $t5, $zero, retornaZero1	# if(resultado==0) 
		beq $t5, 1, retornaZero1		# if(resultado==1)
		
		else1:
			li $t3, 11
			sub $v0, $t3, $t5
			j sai1
		
		retornaZero1:
			move $v0, $zero		# return 0
 			j sai1
	
	sai1:
		lw    $ra, 0($sp)
		addiu $sp, $sp, 4 
  		jr $ra
  		
imprime_cpf:
  	addiu $sp, $sp, -4 # Atualizar o tamanho da pilha de acordo com os seus registradores!
	sw    $ra, 0($sp)
	
	la $t3, SIZE
	lw $t3, 0($t3)
	
	li $t4, 0		# i = 0
	li $t6, 0		# auxiliar para ajudar a percorrer o vetor
	mul $a0, $a0, 44	# usado para percorrer a linha  
	addu $t6, $t6, $a0	# proxima linha
	loop4:
		beq $t4, $t3, fim2
		lw $t7, CPF($t6)	# CPF[pos][i] , lê a posição do vetor
		
		li $v0, 1		# imprime o valor
		move $a0, $t7
		syscall
		
		beq $t4, 2, p 		# if(i==2 || i==5) printf(".");
		beq $t4, 5, p
		j elseTraco		# caso contrario pula
		p:
			li $v0, 4
			la $a0, ponto
			syscall
		
		elseTraco:
		beq $t4, 8, t 		# if(i==8) printf("-");
		j elseIncrementa	# caso contrario pula
		t:
			li $v0, 4
			la $a0, traco
			syscall
		elseIncrementa:
		addiu $t4, $t4, 1	# i++
		addiu $t6, $t6, 4	# posição do vetor ++
	j loop4
	
	fim2:
	# nova linha
	li $v0, 4
	la $a0, nova_linha
	syscall
	
	lw    $ra, 0($sp)
	addiu $sp, $sp, 4 # Atualizar o tamanho da pilha de acordo com os seus registradores!
  	jr    $ra