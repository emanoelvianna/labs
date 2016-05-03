# realiza o fatorial.
#	void main(){
# 		int x, y;
# 		x = 12;
#		y = fact(x);
# 		printf("O fatorial de 12 eh %d", y);
#	}
#	int fact(int n){
# 		if (n < 1)
# 			return(1);
#		else
# 			return(n * fact(n-1));
#	}

.text 
.globl main
main:
	addiu $sp, $sp, -4	# abrindo espaço na pilha para o $ra e o x
	sw $ra, 0($sp)		# guarda na pilha o $ra		
	li $a0, 12	
	jal fact
	
	move $t1, $v0
	li $v0, 1
	move $a0, $t1
	syscall
	
	lw $ra, 0($sp)
	addiu $sp, $sp, 4    # libera a posição alocada
	jr $ra
	
	fact:
		#// ** dúvida, porque se empilhar aqui? 
		addiu $sp, $sp, -8   	# aloca 2 posições na pilha
		sw $ra, 4($sp)		# guarda o $ra
		sw $a0, 0($sp)		# guarda o $a0
		
		blt $a0, 1, fim	# if (n < 1)
			sub $a0, $a0, 1
			jal fact
			lw $t1, 0($sp)	# $t1 <- $a0
			mul $v0, $v0, $t1
			j retorna
		fim:
			li $v0, 1
		retorna:
			lw    $ra, 4($sp)
			addiu $sp, $sp, 8    # libera as 2 posições alocadas
			jr    $ra            # fim da subrotina FATORIAL
		 
	
	