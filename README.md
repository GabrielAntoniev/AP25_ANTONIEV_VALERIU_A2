Lab.1 :
homework: mai intai am generat o aranjare random a nodurilor. Primele k formeaza o clica, urmatoarele k nu au niciun vecin (pentru multimea stabila) si restul de noduri formeaza muchii la intamplare.

bonus: Solutia mea se bazeaza pe gasirea clicii maxime, pentru ca pentru orice k < dimensiunea clicii maxime avem ca exista o clica de dimensiune k.
Pentru a gasi o clica de dimensiune maxima mai intai am ordonat crescator nodurile dupa gradul lor (puteam sa fac si fara). Dupa aia am generat toate combinatiile de noduri care pot forma o clica.
Nodurile folosite pentru generarea de combinari sunt toate nodurile cu grad minim k-1, pentru ca intr-o clica cu k noduri , toate nodurile sigur au grad macar k-1. Si pentru fiecare combinatie de noduri descrisa mai sus am testat 
efectiv daca exista muchie. Daca pentru toate nodurile exista muchie catre toate restul atunci gruparea aceea de noduri este o clica.
Pentru a gasi clica de dimensiune maxima eficient am facut un fel de cautare binara care porneste cu cautarea dimensiunii clicii intre 1 si n evident.
Pentru multimea stabila de cardinal minim k se aplica algortimul de clica doar ca il aplicam pe graful inversat al lui G, adica pentru nodurile care nu au muchie adaugam muchie si cele care deja aveau muchie o eliminam.

Lab.2 :
homework: Fiecare student poate avea asignat un singur proiect, dar poate avea o lista cu mai multe preferinte pentru proiect. Clasa Teacher are ca membru un array de proiecte pe care un profesor le poate propune studentilor.
Un proiect poate avea asignat un singur profesor, deci un proiect anume poate fi propus de un singur profesor.
Clasa Person este clasa abstracta care se extinde la Teacher si Student.
Clasa Problem defineste toate elementele (toti "actorii") pentru problema data in enunt. Scopul este ca, bazandu-ne pe preferintele studentilor, sa le asignam la fiecare cate un proiect care nu a mai fost ales. Solutia problemei se gaseste
in clasa Solution, care are o metoda ce implementeaza un algoritm greedy. Solutia furnizeaza o lista cu fiecare student si ce proiect i-a fost repartizat.

bonus: Pentru fiecare submultime de studenti se verifica teorema lui Hall, daca cardinalul reuniunii optiunilor studentilor din sumbumtimea selectata este mai mare sau egal decat numarul de studenti
din submultime. Daca se gaseste o astfel de submultime care nu respecta aceasta conditie atunci nu exista o repartizare a proiectelor. Pentru a reprezenta submultimile de studenti am folosit
reprezentarea binara a tuturor numerelor de la 1 la 2^nrStudents -1. La final pentru a gasi efectiv o solutie, am ordonat pentru fiecare student, preferintele lor de proiecte in functie de cat de
dorite au fost proiectele de catre studenti, si am mai facut o sortare a studentilor dupa numarul de optiuni pe care l au ales pentru proiecte.

Lab3:
homework: Se ia fiecare zbor si se verifica daca exista o pista care nu este deja ocupata. Daca se gaseste o prima pista care nu este ocupata, atunci acel zbor ii va corespunde pista gasita, si perioada
in care pista este ocupata va fi in functie de diferenta dintre ora sosirii si ora plecarii. O pista poate fi folosita daca ora de sosire a ultimului zbor asociat pistei este mai mica decat ora de plecare
a unui alt zbor.
