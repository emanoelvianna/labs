# O objetivo desse código é mostrar como é possivel obter uma possição do vetor
# que se comporta como uma matriz (importante em mips matriz não existe é só um comportamento) 
# utilizando um padrão

.data 
   VET: .word 6 4 6 3 1 1 5 8 0 0 0 
              1 3 7 6 6 1 2 8 8 7 6 
              8 6 2 7 5 5 4 2 2 5 7 
              1 7 8 1 8 8 8 7 2 8 8 
              8 2 2 4 3 4 1 5 0 0 3
   nova_linha: .asciiz "\n"    
.text 
.globl main
main:
	# VET[i][9]
	# 36 ou 76 ou 120 ou 208
	li $t0, 0	# i
	li $t1, 0	# aux
	loop:
		beq $t0, 5, fim		# caso i == 4 então fim
			mul $t1, $t0, 44	# aux = i * 44
			addiu $t1, $t1, 36	# aux = aux + 32
			lw $t2, VET($t1)	# $t2 = VET[i][9]
			
			li $v0, 1		# imprime o valor
			move $a0, $t2
			syscall
			
			li $v0, 4		# nova linha
			la $a0, nova_linha
			syscall
		
		addiu $t0, $t0, 1	# i++
	j loop
	
	fim:
		jr $ra