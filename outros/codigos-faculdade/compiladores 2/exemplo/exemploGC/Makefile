# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

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
	java -jar $(JFLEX) exemploGC.flex

Parser:	
		$(BYACCJ) -J exemploGC.y

java:
		$(JAVAC) Parser.java
