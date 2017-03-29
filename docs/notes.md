# Notatki

## 08.03
platforma do wstrzykiwania strategii
spłaszczony zbiór strategii - różne parametry = różna strategia

algorytm do szukania najlepszej strategii - ewolucyjny
trywialny - każdy z każdym
losujemy strategię z wagą z ilości wygranych - losujemy czy zaburzamy parametry czy nie

dla każdego parametru losujemy czy zmieniamy, czy nie i losujemy nową wartość
jeżeli strategia bez parametrów zmieniamy na inną strategię

2 losowania:
* czy zmieniamy?
* czy zmieniamy na inne parametry czy na inny algorytm?

do zrobienia:

1. platforma
1. strategie/algorytmy
1. turnieje

- https://github.com/LinxiFan/Reversi
- http://mnemstudio.org/game-reversi-example-2.htm
- https://github.com/Zolomon/reversi-ai

## 15.03
8 strategii + parametryzacja

mieszane strategie:
* trójki: (strategia1, warunek zmiany, strategia2)
* klasyfikować strategie: czy się nadają na pierwszą, drugą czy na obie

raport:
* state of art (opis podobnych problemów + rozwiązań)
* definicja problemu
* opis oprogramowania
* plan testów i wyniki

TODO:
* skończyć platformę
* dwa proste algorytmy (random + minmax?)
* silnik do turniejów
* szczątkowa definicja problemu i opis rozwiązania
