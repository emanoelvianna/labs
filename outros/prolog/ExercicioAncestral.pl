progenitor(antonio,joaozinho).
progenitor(maria,joaozinho).
progenitor(antonio,luis).
progenitor(maria,luis).
progenitor(antonio, cristina).
progenitor(maria, cristina).
progenitor(carlos, antonio).
progenitor(fernanda, antonio).
progenitor(pedro, maria).
progenitor(mariana, maria).
progenitor(jose, mariana).
progenitor(sandra, mariana).
progenitor(carlos, alberto).
progenitor(fernanda, alberto).
progenitor(carlos, marcia).
progenitor(fernanda, marcia).
sexo(joaozinho,masc).
sexo(antonio,masc).
sexo(maria,fem).
sexo(carlos,masc).
sexo(pedro,masc).
sexo(jose,masc).
sexo(alberto,masc).
sexo(luis,masc).
sexo(cristina,fem).
sexo(fernanda,fem).
sexo(mariana,fem).
sexo(sandra,fem).
sexo(marcia,fem).
pai(X,Y):- progenitor(X, Y), sexo(X, masc).
mae(X,Y):- progenitor(X, Y), sexo(X, fem).
irmao(X,Y):- progenitor(A, X), progenitor(A, Y), sexo(X, masc).
tio(X,Y):- pai(A,X), pai(A,Z), pai(Z,Y), sexo(X, masc).
avo(X,Y):- pai(X,Z), pai(Z,Y), sexo(X, masc).
ancestral(X,Y):- progenitor(X,A), progenitor(A,Y).
