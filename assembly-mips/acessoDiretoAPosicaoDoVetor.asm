#	void main() {
# 		int medida;
# 		int RESULTADO[3];
# 		printf ("Entre o valor em metros: ");
# 		scanf("%d", &medida);
# 		RESULTADO[0] = medida*10;
# 		RESULTADO[1] = medida*100;
# 		RESULTADO[2] = medida*1000;
#	}

.data 
	resultado: .word 0:2
	media: .word 0
	texto: .ascii "Entre o valor em metros:"
.text 
.globl main
main:
	li $v0, 4	# imprimindo o texto
	la $a0, texto 
	syscall
	
	li $v0, 5	# scanf("%d", &medida);
	syscall
	move $t0, $v0	# $t0 <- $v0
	
	mul $t1, $t0, 10	# $t1 <- medida*10;
	li $t2, 0		# auxiliar para percorrer o vetor
	sw $t1, resultado($t2)
	
	mul $t1, $t0, 100	# $t1 <- medida*10;
	li $t2, 4		# auxiliar para percorrer o vetor
	sw $t1, resultado($t2)
	
	mul $t1, $t0, 1000	# $t1 <- medida*10;
	li $t2, 8		# auxiliar para percorrer o vetor
	sw $t1, resultado($t2)
	
	jr $ra
	
	