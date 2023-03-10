# Umestzung
Die Coding-Challange wurde objektorientiert umgesetzt. 
Die Wahl der Logik, wie sich die Aufzüge bewegen fiel auf die Variante, dass eine Aufzugfahrt nur zwischen zwei Stockwerken verkehrt.

Die Anfragen sowie die Zustände der Aufzüge wurden innerhalb des gesamten Systems gestellt und verarbeitet. Umgesetzt wurde dies mit zwei verschiedenen Varianten:
1. Manuell
2. Java-Threads

Für die unterschiedlichen Varianten wurden auch seperate Test-Klassen erstellt.
Komplexere Methoden und Input/Output-Werte wurde durch Kommentare ergänzt.

## Grundlegendes zur Umsetzung
- Die Fahrtrichtung (Direction) wurde aufgrund der Wiederverwendbarkeit als Enumarble im Projekt implementiert.
- Die Anfragen an das System wurden in einer Queue gespeichert. Zudem wurde eine Methode implementiert, welche überprüft, ob es Anfragen gibt, die verarbeitet werden können.
Wird eine Anfrage verarbeitet, so wird diese aus der Queue entfernt.
Zusätzlich wurde für die Initatlisierung neuer Anfrage eine Exception-Klasse implementiert, welche auf unerlaubte Parametereingaben reagiert.
### Manuelle Variante
- Für die Abfrage eines freien Aufzugs wurde die Methode *checkAvailableElevators()* implementiert. Diese wurde iterativ umgesetzt und gibt einen freien Aufzug als Rückgabewert zurück. Eine andere Möglickeit wäre eine Rekursion der Methode, da diese automatisch eine Excpetion wirft, wenn die Rekursion zu tief geht. Allerdings ist rekursives Programmieren inperfomanter als iteratives.
- Für die manuelle Verarbeitung der Anfragen und der Manipulation der Aufzüge wurde die Methode *processRequest()* implementiert. 
### Thread Variante
- Um die Nutzung der Java-Threads zu ermöglichen wurde die Methode *checkAvailableElevators()* **synchronized** implementiert, um diese bei einem Thread-Zugriff zu *locken*. Somit kann nur ein Thread zu einem bestimmte Zeitpunkt überprüfen, welcher Aufzug frei ist bzw. übernimmt dieser den nächsten freien Aufzug. 
- Ein bestimmter Code-Block innerhalb der Methode *run()* der ElevatorThread-Klasse wurde ebenfalls **synchronized** implementiert. Hier warten die anderen Threads bis der laufende Thread den übernommenen Aufzug manipuliert hat (Zuweisung der Eigenschaft *available* auf **false**), bevor diese den nächst freien Aufzug abfragen.

## Potentielle Verbesserungsmöglichkeiten
- Um Zugriffsfehler durch die Threads zu vermeiden, kann statt hardgecodedeten Integer-Werten ein Delay implementiert werden, nach welchem Threads nicht gleichzeit und simultan auf Werte/Objekte zugreifen, die durch den Thread selbst manipuliert werden.
- Statt mehreren Methoden-Aufrufen von der Initialisierung einer Anfrage bis hin zu dem Starten/Ausführen eines Threads, wäre es mögliche diese in ein einem weiteren Thread zusammenzufassen. Jedoch kann dadurch die Komplexität des Systems steigen.
- Wenn die Aufzugfahrten auch Zwischenstopps haben können, kann man bei der Wahl der freien Aufüzuge (wenn mehrere zur Verfügung stehen), denjenigen wählen, der dem Stock am nächsten ist, von dem aus eine Anfrage initialisiert wurde.
--Zudem kann anhand der Fahrtrichtung (Direction) und der Abfrage der Stockwerke aus den Anfragen, implementiert werden, dass Aufzüge auch Passagiere mitnehmen, die zwischen dem aktuellen Stockwerk und dem Zielstockwerk liegen.

#### Notiz
Das war meine erste Programmiererfahrung mit Java-Threads. Dementsprechend gibt es mit Sicherheit auch in diesem Bereich Verbesserungspotenzial.

