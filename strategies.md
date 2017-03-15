# Strategie
Opis poszczeglnych strategii używanych w stworzonym modelu do symulacji.
## Max-Min Discs
Strategia polegająca na maksymalizowaniu aktualnych punktów jednocześnie minimalizując punkty przeciwnika. Można rozegrać strategię tylko maksymalizując swoje punkty, lub tylko (poza samą końcówką) minimalizować punkty przeciwnika, aby w końcowych ruchach przejąć jego piony.
- Da się uśpić czujność AI?
- Czy AI próbuje odgadnąć strategię przeciwnika i do niej się dostosować?

## Stable Discs
Polega na zdobywaniu pól na planszy, których nie można stracić. Przeciwnik nie będzie w stanie nam już ich odebrać.

- Mamy kilka stabilnych/pewnych pól. Co dalej? Zmiana strategi?
- Ile pól stabilnych, aby zmienić strategię?

## Positional
Strategia opierająca się o "mapę" ryzyka. Gracz chce zdobyć odpowiednie pola na planszy. Jednocześnie unikając pól z największym ryzykiem.

## Random
Jak w przypadku warcabow. Losować grę do końca i sprawdzać co da nam ile wygranych.

## Mobility
Strategia podobna do Max-Min. Polega na wykonywaniu przez gracza ruchów, które prowadzą do uzyskania wielu możliwości ruchu. Przy jednoczesnym minimalizoawniu możliwości ruchu przeciwnika.

## Frontiers
Strategia mająca na celu umieszczenie jak najwięcej własnych pionów otoczonych przez piony gracza. Trochę podobne do Mobility. Im więcej pionów gracz ma na zewnątrz tym więcej możliwości ruchu stwarza przeciwnikowi.

## Parity
Strategia dzieląca planszę na obszary. W których to gracz musi wykonać ruch jako ostatni. Taki obszar nie może już zostać odwrócony na przeciwny kolor. Kolejną zaletą jest rozpoczęcie ruchu w nowym obszarze przez przeciwnika. Podobne do Stable Discs.