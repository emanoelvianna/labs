/***** Quest�o 3: ****/

apresenta(cron�metro, tempoErrado).
indica(tempoErrado, descalibragem).
indica(tremor, desregulagem).
indica(tranco, desregulagem).
corrige(calibracao, tempoErrado).
corrige(ajusteFino, tremor).
corrige(alinhamento, tranco).

tem(X, Y):-  X = meg�metro, Y = desregulagem.
tem(X, Y):- apresenta(X, Z) , indica(Z, Y).
necessita(X, Y):- tem(X, A), indica(Z, A), corrige(Y, Z).
elimina(X, Y) :- corrige(X, Z), indica(Z, Y).
