comida(banana).
comida(laranja).
comida(abacaxi).
not(comida(veneno)).
come(joao, laranja).
come(joao, banana).
come(joao, abacaxi).
mata(X, Y):- come(X, Y), comida(Y).
