#	main() {
#	 	int i, x[5];
#	 	for(i=0; i<5; i++){
#	 		scanf(“%d”, &x[i]);
#	 	} 
#	 	for(i=0; i<5; i++) {
#	 		if(i==3) x[i] = x[0] + x[3];
#	 		else x[i] = x[i] + x[i+1];
#	 	}
#	 }
.data
	x: .word 0:5
	i: .word 0
	tam: .word 20
.text 
.globl main
main:
	la $t0, i
	lw $t0, 0($t0)
	
	la $t1, tam
	lw $t1, 0($t1)
	loop:
		slt $t2,$t0,$t1		#teste para i<tam
		beq $t2,$zero,para 	#se i>=tam, então para
 		li $v0, 5
 		syscall
 		sw $v0, x($t0)
 		addi $t0, $t0, 4
 	j loop
 	para:
 		jr $ra
		
		  			
		  			  			  			
