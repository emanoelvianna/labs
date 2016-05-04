# usando a pilha para a passagem de parametros e retorno de função

#	void main(){
#		int A, B, cont, resposta;
# 		scanf("%d", &A);
# 		scanf("%d", &B);
# 		scanf("%d", &cont);
# 		resposta = soma(A, B, cont);
# 		printf("O valor da soma eh %d", resposta);
#	}
#	int soma(int x, int y, int z){
# 		if(z<=0)
# 			return y;
# 		else
# 			return soma(x+y, x, z-1);
#		}

.data
	inicio: .asciiz "\n-- INICIO --\n"
	fim: .asciiz "\n-- FIM --\n"
	texto: .asciiz "O valor da soma eh "
.text 
.globl main
main:
	addiu $sp, $sp, -16	# abre espaço na pilha
	sw $ra, 0($sp)		# empilha o $ra
	li $v0, 5		# scanf("%d", &A);
	syscall
	sw $v0, 4($sp)		# empilha o valor A
	
	li $v0, 5		# scanf("%d", &B);
	syscall
	sw $v0, 8($sp)		# empilha o valor B
	
	li $v0, 5		# scanf("%d", &Cont);
	syscall
	sw $v0, 12($sp)		# empilha o valor Cont
	
	jal soma
	
	soma:
		lw $a0, 4($sp)	# A
		lw $a1, 8($sp)	# B
		lw $a2, 12($sp)	# Cont
		
		beqz $a2, retorna
		
		 
		retorna:
			 
		
	
