Lab.1 :
homework: mai intai am generat o aranjare random a nodurilor. Primele k formeaza o clica, urmatoarele k nu au niciun vecin (pentru multimea stabila) si restul de noduri formeaza muchii la intamplare.
bonus: Solutia mea se bazeaza pe gasirea clicii maxime, pentru ca pentru orice k < dimensiunea clicii maxime avem ca exista o clica de dimensiune k.
Pentru a gasi o clica de dimensiune maxima mai intai am ordonat crescator nodurile dupa gradul lor (puteam sa fac si fara). Dupa aia am generat toate combinatiile de noduri care pot forma o clica.
Nodurile folosite pentru generarea de combinari sunt toate nodurile cu grad minim k-1, pentru ca intr-o clica cu k noduri , toate nodurile sigur au grad macar k-1. Si pentru fiecare combinatie de noduri descrisa mai sus am testat 
efectiv daca exista muchie. Daca pentru toate nodurile exista muchie catre toate restul atunci gruparea aceea de noduri este o clica.
Pentru a gasi clica de dimensiune maxima eficient am facut un fel de cautare binara care porneste cu cautarea dimensiunii clicii intre 1 si n evident.
Pentru multimea stabila de cardinal minim k se aplica algortimul de clica doar ca il aplicam pe graful inversat al lui G, adica pentru nodurile care nu au muchie adaugam muchie si cele care deja aveau muchie o eliminam.
