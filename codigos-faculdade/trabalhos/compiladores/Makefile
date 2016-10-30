# Jflex e Byaccj devem estar na pasta
# Observacao: estou utilizando o jar do JFlex

JFLEX = JFlex.jar
BYACCJ = ./yacc.linux
JAVAC = javac
JAVA = java

all:	flex Parser java

run:	Parser
	$(JAVA) Parser

build:	clean Parser

clean:
	rm -f *~ *.class BC.java Parser.java y.output ParserVal.java

flex:
	java -jar $(JFLEX) lexico.flex

Parser:	
		$(BYACCJ) -J sintatico.y

java:
		$(JAVAC) BC.java
