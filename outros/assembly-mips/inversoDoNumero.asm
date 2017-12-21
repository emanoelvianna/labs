# Escreva um programa em linguagem de montagem do MIPS que implemente o trecho de c�digo
# definido abaixo. Essa uma fun��o recursiva recebe um valor inteiro por par�metro e mostra na
# tela o seu inverso (o valor equivalente lido da esquerda para a direita). Por exemplo: Ao receber
# o valor 754, a fun��o deve mostrar 457.

#	int inverso(int n){
#		if(n==0)
#			return n;
#		else{
#			printf("%d", n % 10);
#			return inverso(n/10);
#		}
#	}
#	void main(){
#		int num;
#		scanf("%d",&num);
#		inverso(num);
#	}

.data
	inicio: .asciiz "\n-- INICIO --\n"
	fim: .asciiz "\n-- FIM --\n"
.text 
.globl main
main:
	li $v0, 4		# escreve incio na tela
	la $a0, inicio
	syscall
	
	li $v0, 5		# faz a leitua de um numero
	syscall
	move $a0, $v0
	
	addiu $sp, $sp, -4	# abre espa�o na pilha para guardar o $ra
	sw $ra, 0($sp)
	
	jal inverso
	
	lw $ra, 0($sp)		# retorna o valor do $ra que estava na pilha
	addiu $sp, $sp, 4	# fecha o espa�o aberto na pilha que estava o $ra
	jr $ra
	
	inverso:
		move $t0, $a0
		beq $t0, $zero, retorna
		
		div $t0, $t0, 10	# $t0 receve o resultado da divisao de $t0 / 10
		mfhi $t1		# recebe o resto da divis�o
		
		li $v0, 1	# printf("%d", n % 10);
		move $a0, $t1
		syscall
		
		move $a0, $t0
		addiu $sp, $sp, -4	# abre espa�o na pilha para guardar o $ra
		sw $ra, 0($sp)
		jal inverso
		
		retorna:
			move $v0, $t0
			lw $ra, 0($sp)		# retorna o valor do $ra que estava na pilha
			addiu $sp, $sp, 4	# fecha o espa�o aberto na pilha que estava o $ra
			jr $ra
