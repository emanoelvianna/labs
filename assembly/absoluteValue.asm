# recebendo o vetor de inteiros, tranfome seus valores em valores absolutos

.data
	vet: .word 23 -43 55 -9 -7 21 -76 12 -45 -10
	tam: .word 10
.text 
.globl main
main:
	la $t0, vet
	la $t1, tam
	lw $t1, 0($t1)
	li $s0, 0
		loop:
			beq $s0, $t1, fim # case ($s0 == $t1) fim
			lw $t3, 0($t0)
	
			addiu $sp, $sp, -4 # abre espaço na pilha
			sw $ra, 0($sp) # guarda o $ra
		
			add $a0, $t3, $zero # passagem por parametro
		
			jal calc_abs
			lw $ra, 0($sp) # desimpilha $ra e coloca no $ra
			addiu $sp, $sp, 4 # retorna posicao da pilha 
			
			sw $v0, 0($t0) # coloca o retorno da função em $t3
			
			addiu $s0, $s0, 1 # incrementa o i
			addiu $t0, $t0, 4 # incrementa o vet
		j loop
	
	
calc_abs:
	add $s1, $a0, $zero
	abs $s1, $s1
	add $v0, $s1, $zero
	jr $ra 	

fim:
	jr $ra




