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
- https://en.wikibooks.org/wiki/Reversi/Strategy
- http://www.samsoft.org.uk/reversi/strategy.htm

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

## 29.03
state of the art
* czy sa prace na ten temat
* inne platformy
* state of the art na początku - czego brakuje, co można poprawić -> definicja problemu wynika ze state of the art

znaleźć artykuły o znajdowaniu strategii

żeby rozwiązać problem przeszukiwania (od tego zacząć):
* zdefiniowane strategie
* klasa strategii i parametrów, zbiory wartośći parametrów

ocena strategii:
* wynik turnieju
* wybór maty turniejowej

## 05.04
pomysł: wyeliminować bezsensowne strategie - trudne (random można od razu wywalić :D)

operacje zaburzania stchastycznego: (w każdym jeszcze losujemy czy zaburzamy)
* losowanie punkt zmiany strategii - sprawdzamy czy została jedna czy dwie
* losowanie strategii
* losowanie parametrów strategii

może rozpatrywać tylko strategie mieszane? (warunek zmiany "0" było by pojedynczą strategią)

wymyślić krzyżowanie strategii (może )

więcej zadań:
* zdefiniować przestrzeń rozwiązań dopuszczalnych (wszystkie strategie, wszystkie parametry - czy brać pod uwagę wszystkie parametry) - efekt: "struktura" rozwiązania - reprezentacja przełączanej strategii
* sformalizować zaburzanie - drzewo decyzyjne
* najwięcej do zastanawiania - synteza/krzyżowanie strategii - jak po wylosowaniu dwóch/więcej strategii zrobić z nich lepsze "dziecko"
