#	int main() {
#  	int i, digito1, digito2, pos; 
#
#  		for(i = 0; i < NUM; i++){
#			// Gera Digitos
#			digito1 = dig_1(i);
#			digito2 = dig_2(i);
#  
# 			//Valida Digitos
#			if((digito1==CPF[i][9]) && (digito2==CPF[i][10]))
#				imprime_cpf(i); // CPF VALIDADO!!!
#		}
#
# 	return 0;  
#	}

#	int dig_1(int pos) {
#		int i, resultado, somador;
#
#		somador=0;
#		for(i=0;i<9;i++)
#	  		somador+=CPF[pos][i]*(10-i);
#	
#		resultado=somador%11;
# 
#		if( (resultado==0) || (resultado==1) )
#			return 0;
#  		else
#			return 11-resultado;
#	}



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
	
	la $t0, NUM
	lw $t0, 0($t0)	# faz a leitura do NUM
	
	li $t2, 0	# i = 0
	li $t4, 0
	
	li $t5, 0	# aux
	li $t6, 0	# 
	loop:
		beq $t2, $t0, fim	# i < NUM então continua, caso contrario fim

		loop2:
			beq $t5, 11, continua
			lw $t3, CPF($t6)
		
			li $v0, 1		# imprimindo o valor
			move $a0, $t3
			syscall
			
			addiu $t5, $t5, 1
			addiu $t6, $t6, 4
		j loop2
				
	continua:
		move $t5, $zero 	# limpa o $t5
		
		li $v0, 4		# imprimindo nova_linha
		la $a0, nova_linha
		syscall
		
		addiu $t2, $t2, 1	# i++
		move $t4, $t2		
		mul $t4, $t4, 44
	j loop
	
	fim:
		jr $ra

