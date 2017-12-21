#	somaValores(){
# 		int soma = 0;
# 		int index;
#		scanf("%d", &index);
# 		while(index >0){
# 			soma = soma + index;
# 			index--;
# 		}
# 		printf("O resultado da soma é %d", soma);
#	}

.data 
	soma: .word 0
	label: .ascii "O resultado da soma é: "
.text 
.globl main

main:
	la $t0, soma
	lw $t0, 0($t0)
	
	li $v0, 5 	# scanf("%d", &index);
	syscall
	move $t1,$v0 	# $t1 = index
	
	while:
	beq $t1, $zero, fim
		addu $t0, $t0, $t1	# soma = soma + index;
		sub $t1, $t1, 1
	j while
	
	fim:
		li $v0, 4
		la $a0, label
		syscall #imprime texto
		li $v0, 1
		move $a0, $t0
		syscall #imprime inteiro
		jr $ra
	
	
	