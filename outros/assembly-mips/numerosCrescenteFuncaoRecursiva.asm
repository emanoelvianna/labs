# Escreva um programa em linguagem de montagem do MIPS que mostre na tela os números em
# ordem crescente utilizando uma função recursiva. Os números de início e fim estão definidos.
# Lembre-se que recursividade ocorre quando dentro uma função existe uma chamada para a
# mesma função.

#	void escreva(int inicio, int fim){
#		printf("%d ", fim);
#		if (inicio < fim)
#		escreva(inicio, fim-1);
#	}
#	void main(){
#		int valInicio = 16;
#		int valFim = 61;
#		escreva(valInicio, valFim);
#	}

.data
	valInicio: .word 16
	valFim: .word 61
	inicio: .asciiz "\n-- INICIO --\n"
	fim: .asciiz "\n-- FIM --\n"
	espaco: .asciiz " "

.text
.globl main
main:
	la $t0, valInicio	# $t0 <- valInicio
	lw $t0, 0($t0)
	
	la $t1, valFim		# $t1 <- valFim
	lw $t1, 0($t1)
	
	li $v0, 4		# escreve INICIO na tela
	la $a0, inicio
	syscall
	
	addi $sp, $sp, -4	# abre espaço na pilha
	sw $ra, 0($sp)
	
	move $a0, $t0		# passando como parametro o valInicio 
	move $a1, $t1		# passando como parametro o valFim 
	jal escreve
	
	lw $ra 0($sp)
	addiu $sp, $sp, 4
	jr $ra
	
	escreve:
		move $t0, $a0		# $t0 <- valInicio
		move $t1, $a1		# $t1 <- valFim
		
		li $v0, 4		# imprime espaço entre os números
		la $a0, espaco
		syscall
		
		li $v0, 1		# printf("%d ", fim);
		move $a0, $t1
		syscall
		
		blt $t0, $t1, menor 	# if (inicio < fim)
		jr $ra
		
		menor:	
			move $a0, $t0		# $a0 <- valInicio		
			sub $t1, $t1, 1
			move $a1, $t1		# $a1 <- valFim - 1
			
			
			addiu $sp, $sp, 4
			sw $ra, 0($sp)
			jal escreve
					
	
	