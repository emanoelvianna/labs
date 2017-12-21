#	int potencia(int a, int b){
# 		if(b==0)
# 			return 1;
# 		else
# 			return a * potencia(a, b-1);
#	}
#	int main(){
# 		int X, N, R;
# 		scanf(“%d”, &X);
# 		scanf(“%d”, &N);
# 		R = potencia(X, N);
# 		printf("O resultado do calculo da potencia eh %d", R);
# 		return 0;
#	}
.data
	txtResult: .asciiz "O resultado do calculo de potencia eh "
.text 
.globl main
main:
	addiu $sp, $sp, 4	# abriu espaço na pilha
	sw $ra, 0($sp)		# guarda o $ra na pilha
	
	li $v0, 5
	syscall
	move $a0, $v0	# scanf(“%d”, &X);
	
	li $v0, 5
	syscall
	move $a1, $v0	# scanf(“%d”, &N);
	
	jal potencia
	
	lw $ra, 0($sp)
	
	move $t1, $v0	# guarda o resultado
	
	li $v0, 4
	la $a0, txtResult
	syscall
	
	li $v0, 1	# escreve o resultado
	move $a0, $t1
	syscall
	
	potencia:
		addiu $sp, $sp, -12	# abriu espaço na pilha
		sw $ra, 0($sp)		# empilha o $ra
		sw $a0, 4($sp)		# empilha o $a0
		sw $a1, 8($sp)		# empilha o $a1
		
		beqz $a1,fim
			sub $a1, $a1, 1		# descrementa o valor de n
			jal potencia
			lw $t1, 4($sp)		# desempilha o $a0 (X) para realizar a multiplicação 
			mul $v0, $v0, $a0 	# a * potencia(a, b-1)
			j retorna
			
		fim:
			li $v0, 1
		retorna:
			lw $ra, 0($sp)	# antes de retornar restaura o $ra
			addiu $sp, $sp, 12	# desfaz os espaços na pilha
			jr $ra	
		
		
	