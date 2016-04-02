#	main() {
# 		int opA = leOperando();
# 		int opB = leOperando();
# 		imprimeSoma(opA, opB);
#	}
#	int leOperando(){
# 		int val;
# 		printf("Insira um valor: ");
# 		scanf(“%d”, &val);
# 		return val;
#	}
#	void ImprimeSoma(int _opA, int _opB){
# 		printf("Soma= %d", _opA+_opB);
#	}
.data 
	texto1: .ascii "Insira um valor: "
	texto2: .ascii "Soma: "
.text 
.globl main
main:
	move $s0, $ra	# salva o endereço de retorno
	jal leOperando 	# chama subrotina leOperando
	move $ra, $s0
	move $t0, $a0 	# $t0 <- retorno da funcao
	
	move $s0, $ra	# salva o endereço de retorno
	jal leOperando 	# chama subrotina leOperando
	move $ra, $s0
	move $t1, $a0 	# $t0 <- retorno da funcao
	
	jal ImprimeSoma
	move $ra, $s0
	jr $ra
	
	leOperando:
		li $v0, 4
		la $a0, texto1
		syscall
		li $v0, 5
		syscall
		move $a0, $v0
		jr $ra
		
	ImprimeSoma:
		addu $t2, $t1, $t0
		li $v0, 4
		la $a0, texto2
		syscall
		li $v0, 1
		move $a0, $t2
		syscall
		jr $ra
