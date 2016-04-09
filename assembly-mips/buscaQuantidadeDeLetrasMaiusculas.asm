# Escreva  um  programa  em  linguagem  de  montagem  (assembly)  do  processador 
# MIPS  que  recebe  como  entrada  um  vetor  de  caracteres  denominado ORIGEM
# codificado em ASCII, filtra todos os caracteres maiúsculos presentes no texto de 
# entrada   e  armazena  o  resultado  em um  vetor  resultante  chamado DESTINO. 
# Além disto, o programa deve armazenar na variávelmaiúsculas a quantidade de 
# elementos do vetor DESTINO.

.data 
origem:  .word  0x61  0x4C  0x6F  0x25  0x24  0x23  0x4D  0x41  0x6D  0xE3  0x65 0x0
destino: .word 0x0
maiusculas:.word 0x0
.text 
.globl main
main:
	la $t0, origem
	
	la $t2, maiusculas
	lw $t2, 0($t2)
	
	move $s0, $ra	# guarda a referencia para o retorno
	li $s1, 0 # i
	loop:
		lw $t4, 0($t0) 		# $t4 <- origem[i]
		beq $t4, $zero, fim
				
		jal FILTRAUP
		move $ra, $s0	# recupera a referencia de retorno
		
		beq $a3, $zero, naoSalva
			addu $t2, $t2, 1	# incrementa a quantidade 
			
			sw $t4, destino($t1)
			
			addiu $t0, $t0, 4 # incrementa origem
			addiu $t1, $t1, 4 # incrementa destino
			j loop
			
		naoSalva:
			addiu $t0, $t0, 4 # incrementa origem
			j loop
	j loop
	
	fim:
		jr $ra
	
	FILTRAUP:
		li $a0, 0x41
		li $a1, 0x5A
		bge $t4, $a0, continua	# continua se possivel caractere maiúsculo
			li $a3, 0	# caso contrario cai fora
			jr $ra
		continua:
			ble $t4, $a1, salva	# continua se possivel caractere maiúsculo
			li $a3, 0		# caso contrario cai fora
			jr $ra
		salva:
			li $a3, 1		# deve salvar, caractere maiúsculo
			jr $ra
		
		