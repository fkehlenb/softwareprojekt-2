# Struktur
__Fragen__
_Admin_
Wo ist welche Probe
Wann bekomme ich welche Probe

    Dauer, Entfernung, Verlauf
_Technologen_
Welche Priotiät haben 

_Logistik_

Proben: Jeder Kugel hat eine ID, Menge von Atributen (werden dann vergeben, wenn ein Prozesschritt vergeben wurde. Nach dem Ausführen des PS werden wie Atribute gesetzt)

Wunsch: PS erwartet eigenschaften
Vor der Instanzierung Valiederung, von reqs jeder PS



Legierung : Umformung -> Herstellung ist klar aus was -> setze das Atribut der Lergierung -> bei Wärme behadlung setze das Atribut

_Proben_
* id (im format der db ID)
* Legierung (TODO: Wann ist dies bekannt?)
* Wärmebehandlung (Eigner Klasse mit atribut in Kelvin, leere Liste für nichts oder nur Eintrag)

_Träger_
* ID = eingebette Sachen, Kugelen, Stückkasten (5x5)
* Standort : Expermentierstation
* Mehre Proben (beliebig nicht unendlich)
* Art, für relevant für die PK
    * vereinzelt: Träger
    * eingebette: Schütgut
    * Glas
* Auftrag : Suche nach Auftrag
* Verlust: jeder proble möglich
Prozessparameter haben abstraje parameter
    Abkühlrate einstellen, verändern -> instanzierung -> Fest

__Prozesssschritt__
*  Namen
*  Experimentierstation
*  PROZESSPARAMETER
* Einfärbend?
*  Probenzustand (Präparation)
*  Zustände und Transitionen
*  GESCHÄTZTE DAUER



__Prozesskette__
* Auftrag
* Prozessschnitt
    * Zustände
    * Transitionen (Ich habe ihn bekommen, ich bearbeite Ihn jetzt, bin fertig, habe es weitergeleitet )
* Name

__Auftrag__
* PK
* Standort
* Status kaputt : Boolean

* Prozessparameterinstanzen für jeden ps
  Instanzerung der ProzessSchritte
  
 Kickoff Folien xml 40

__Experimentierstation__
* Namen
* Ort
* Status
* Nutzer
   
# Pflichtenheft 
## Akteure 
Im folgendem Teil erläutern wir die verschienden Akteure Prozesskettenadministrator, Logitisker, Techniker, Transport und Administrator. Nach dem Auflisten der Aufgaben der Akteure
**1. Prozesskettenadministrator**
 * **Aufgabe im SFB:**
    1. Prozessketten anlegen
    2. Prozessketten instanziieren
    3. Den Überblick behalten“
 * **Sein Problem:**
     1. Kein direktes Feedback von den Experimentalstationen
     2. Keine Abbildung von Prozessketten in der DB
 * Was wünscht er sich?
   1. Übersicht über aktuell laufende Prozessketteninstanzen (und deren Status)
* **Was muss er tun?**
    * Übersicht über alle ProzessSchritte
        1. Anlegen neuer ProzessSchritte
            * Auswahl von vordefinierten Abläufen (Zustandsautomat)
            * Auswahl von dyn. Abläufen (O«Dynamische Zustandsautomaten»)
            * Eingabe von voraussichtlicher ProzessSchrittdauer
            * Definition von ProzessSchrittparametern (Name, Einheit)
            * Zuordnung von Experimentierstation
            * Setzen von Attributen wie â€žeinfärbendâ€œ, Präparationsart
        3. Löschen bestehender ProzessSchritte
        2. Prüfen (Auf Korrektheit)
        3. Bearbeiten von â€œnicht gestarteten ProzessSchritten
        4. Hervorhebung der ProzessSchritte, die im Zustand auf kaputt gesetzt sind

    * Übersicht über dynamische Abläufe (O«Dynamische Zustandsautomaten»)
        1. Anlegen von Zustandsautomaten
        2. Löschen von Zustandsautomaten
        3. Bearbeiten von unbenutzten Zustandsautomaten
    
    * Übersicht über alle Prozessketten
        1. Anlegen von neuen Prozessketten aus bestehenden ProzessSchritten
        1. Löschen von bestehenden Prozessketten
        1. Bearbeiten von bestehenden Prozessketten (nicht durchgeführt oder freigegeben)

    * Übersicht über Aufträge
        * Anlegen von Aufträgen
            * Manuelles Setzen der Prozess(schritt)parameter
            * Import der Prozess(schritt)parameter (O«Parameter.Import»)
        * Freigabe von Aufträgen
            * Logistiker muss daraufhin Proben zuordnen
        * Stoppen von Aufträgen
            * Aktueller ProzessSchritt wird beendet
            * Aufträge verschwinden aus der Übersicht der Technologen
            * Transport bringt Proben ins Lager (O«Transporteur»)
        * Löschen von Aufträgen
        * Bearbeiten von Aufträgen (wenn noch nicht freigegeben)
        * Priorisierung der Aufträgen

    * Übersicht über Arbeitsauslastung der Experimentierstationen
      > siehe (O«UI.Auslastung»)
      > 
    * Export des Protokolls nach JSON, so dass eine maschinelle Auswertung möglich ist 
        
    * Prozessketteninstanzen
        1. Priorisieren
        2. Anlegen und Probeneigenschaften festlegen (Legierung + Wärmebehandlung)

Es gibt Prozessketten Templates

> Ich will effizient den Überblick über die laufende Prozessketteninstanzen behalten und direkt Feedback über von den Experimentalstationen erhalten.  Prozesskette hätte ich gerne in der Software abgebildet. 
 

3. Logistik: 

    __Aufgaben im SFB:__
    * Planung: 
        * Was ist der Status? 
        * Lagerverwaltung
        * Prozessketten starten 
        * Archivieren
    * sieht Informationen über:
        * Charge, Proben-ID (Lazy Loading Tabele mit Pagination)
        * Proben-Zahl
        * Eigenschaften
        * Standort
        * Status
        * Materialeigenschaften
        * Prozessketten
        * Einfärbung
    
    __Sein Problem:__ 
    * Was habe ich im Lager?
    
    __Was wünscht er sich:__
    * Übersicht über den Standort aller Proben (auch schon archivierter) (O«Lagerverwaltung»)
    * Übersicht über Probenmengen (O«Lagerverwaltung»)
        * Gruppierung nach Parameterwerten wie Legierung und Wärmebehandlung
    * Gruppierung nach Stationen
    * Übersicht über Träger
        * Anlegen von neuen
        * Löschen von alten
    * Übersicht über abgegebene Aufträge 
        * Zuordnen von Trägern/Proben zu freigegebenen Aufträgen
            * Manuell (korrektheit der eingegebenen Sachen werden vorrausgesetzt)
            * Automatisch auf Basis von geforderten Parameterwerten (O«Lagerverwaltung»)
            
    __Was muss er tun?__
    * Proben mit Prozessketten Instanz assoziieren
        
        >PKA Instanziert  PK: 100 Kugelen mit Eigenschaft, Gibt es diese Kugelen -> Logistiker weisst dem zu, Gibt es die Kugeln im Lager
        * Sind genügend Proben der entsprechenden Anforderung (Legierung + Wärmebehandlung) im Lager?
        * Falls ja, zuweisen & starten
        * Falls nein, Rückmeldung an PK Admin
    * Prozesskette starten, stoppen
    
    
4. Technologe
    **Aufgaben im SFB:**
    * experimente durchführen
    * Rohdaten auswerten 
    * In die Datenbank hochladen
    
    **Sein Problem:**
    * Unregelmäßige Probenankunft
    * Welcher Probensatz hat Priorität?

    **Was wünscht er sich?**
    * Eine Übersicht über die aktuellen Aufträge mit  Priorisierung
    * Übersicht über nächste Aufträge mit prognostizierter Ankunftszeit
    * Übersicht über Aufträge mit fehlendem Upload
    * Abrufen von Prozessparametern
    * Export dieser zum Einlesen in die DB
    * Link zur entsprechenden Uploadseite
    
    __Was muss er tun?__
    * Den Zustand eines Auftrags angeben
        * Angenommen
        * In Bearbeitung
        * Pr üfung
        * Weitergeschickt
        * In die DB hochgeladen
    * Aufnahme der Systemzeit oder manuelle Eingabe der Zeit, falls nachträglich 
    * Melden, falls Fehler an der Station
Den Zustand eines Auftrags angeben
    Will: Priorisierung, 
    Angabe der Prozess Parameter
    _Hochladen fehlt_ Prozessparameter export 
   
4. **(O)Transport**
    * Übersicht über anstehende Transportaufträge
    * Meldet Probenverlust
    Aufgabe: Proben von A nach B
    Problem: Wo  muss ich als nächstes hin
    Wünsche:

    Zustände = (Annahme, Ausliefern, Verloren)
    Nur einer kann den Transport zugewiesen bekommen
    Zustände (waiting, reserved, transport)

8. Admin:
    
    __Aufgaben im SFB:__
    * Auftragsmanagmentsystem administrieren
    
    __Was muss er tun?__
    * Nutzer verwalten
        * Anlegen neuer
            * neue Nutzer werden per Mail informiert
        * Bearbeiten bestehender
        * Löschen bestehender
        * Berechtigungen der Rollen betrachten
    * Experimentierstationen verwalten
        * Anlegen neuer
        * Bearbeiten bestehender
        * Löschen bestehender
        * Nutzer zu Stationen zuordnen
    * Konfiguration globaler Einstellungen 
        * Einstellung der Zeiten für alte Aufträge (O«Alte Aufträge») (Aufträge (Task), bei denen länger nichts passiert ist, sollen visuell hervorgehoben werden. Was länger heißt, entscheidet der Admin)
    * Backup der Systemdaten 
    
    
## Anwendungsfälle
Beschreibung von vorher & nachher sowie den jeweiligen Akteuren. 



Zusammenhänge zwischen Anwendungsfällen und Architektur: Für die Architekturbeschreibung werden 3 Anwendugsfälle benötigt. Mithilfe eines Sequenzdiagramms wird der Nachrichtenverkehr aller involvierten Module beschrieben 

pk instanzierung req [es gibt pk, und er ist eingelogt] nach [ist instanziert]
    welche parameter, welche Reihenfolge
**3 AWs!!!!** 

1. Erstbenutzung
    *    Login -> Hilfe(Verlinkung zu Benutzerhandbuch reicht!!! Kein Tutorial)  ~~(Intro, Glossar, Tutorial) ~~
    *    => Homepage (Admin, PKAdmin, Technologe, Transporter, Logistiker)
2. Überblick laufender Prozessketten(PKA)
    *    Aktueller Standort
    *    Status der Bearbeitung
    *    Priorisierung der Aufträgen

    2.1 Bearbeiten
    *   auf Korrektheit prüfen
    *   Bearbeiten von bestehenden Prozessketten (nicht durchgeführt oder freigegeben)

3. Erfassung und Bearbeitung eines Auftrags (Technologe)
    *    Auftrag erfassen(Liste mit allen für den Technologen zugewiesenen Aufgaben), Auftrag annehmen, Auftrag bearbeiten(Attribute hinzufügen), bearbeitet (Zeitstempel hinzufügen, Änderungen und Notizen prüfen),weitergeleitet(freigegeben für nächsten Schritt), upload in DB. versch. Zustände beschreiben 
    
4. Technloge bearbeitet einen Prozesschritt
    **Vorbedingung:
    1. Eine Probe samt Träger ist für den Technologen __freigeben__: Die Probe ist im System für die jeweilige nächste Station sichtbar und abholbar. 
    
        Dabei sollen die Mitarbeiter welche dieser Station zugeteilt sind 2 Optionen zur Auswahl haben. Die erste Option ist für jeden Forscher verfügbar der gleichzeitig die Rolle eines Transporters hat, nämlich die Option die Probe selbst abzuholen. Die zweite Option ist ein Button der besagt dass der Transport nicht von einem Forscher in der Station übernommen werden soll/kann und ein freier Transporter diese übernehmen soll. 
        * ~~Falls dies erwünscht ist kann der Forscher selbst die Probe holen und trägt sich somit als Transporter in diesem Auftrag ein, der Forscher bekommt daraufhin einen Link in das Transporter interface und der Transport Auftrag erlischt bei allen anderen Transportern. Diese Option ist für Proben höherer Priorität vorbehalten.~~ 
        * ~~Falls also nun der Forscher selbst den Transport übernimmt oder ein Stationsunabhängiger Transporter, so wird der Transport Auftrag angenommen und sobald die Probe vom zuständigen Transporter angenommen wird, ist die Probe im Auftragsstatus "angenommen".~~ 

    2.Bei erfolgreicher Abgabe der Probe an der neuen Station gilt die Probe im Transportauftrag als "abgegeben". Die Zuständige Station muss dies bestätigen, also gilt beim Transport die Probe als "pending/ warten auf Bestätigung" Falls beim Transport oder der Annahme Verluste oder Beschädigungen auftreten können diese im Auftragsinterface gemeldet werden.  
    * Erst jetzt wo die Probe an der neuen Station angekommen ist kann der bearbeitene Forscher in den 2. Schritt "in Bearbeitung" übergehen, bevor die Probe nicht an der Station ist, kann dieser Schritt nicht durchgeführt werden.

    3. __In Bearbeitung__: Der Technologe hat einsicht auf alle für den PS relevanten Prozess Parameter. 
        Sommit weiss der Technologe wie er den Prozesschritt durchführen soll. Protokol (StartZeit)
        
    4. __Eintragung:__ Nach der Auswertung des PS trägt der T in die dafür vorgesehenen Felder alle für die PK relevanten Daten ein, dies sind die sog. Ergebnisse eines Prozessschrites (PSE). Fehler oder anderswertige Unvorhersehbarkeiten können vom T vermerkt werden. Protokol (EndZeit)
    
    5. __Freigabe:__ Der T kann den PS als beendet erklären. Falls dies Geschieht erhält der Transport einen neuen Auftrag. Der Träger der Proben wird nun abgeholt und zur Station des nächsten PS gebracht.


5. Auftrag erstellen und Initalisieren (PKA u. Logistiker)
    * PKA erstellt Prozesskette nach einer Vorlage oder erstellt eine neue Vorlage selbst
    * PKA gibt PZK in Auftrag
    * Logistiker bekommt nun den Auftrag und muss im Lager überprüfen ob die PZK umsetzbar ist und kann diese entweder bestätigen oder ablehnen mit einer Notiz an den PKA was verändert werden muss.
    * Falls alles richtig ist kann der Logistiker die PZK starten, vorher ist diese nur initialisiert!
    * Transporter bekommt Benachrichtigung das Material aus Lager an Station X muss.

## Zustandsautomaten
    * Technologe
        - Angenommen
        - In Bearbeitung
        - Bearbeitet
        - Weitergeleitet
    * Transporteur
        - Abgeholt
        - Abgegeben
        

## Stichpunkte von Problemen
Rollen und Authentifizierung
Peristenz in der Datenbank
Aktivitätsprotokol Serialisieren
{Technologe, Tranport} Mitteilungen von Prioritäten
Kompetenzen der Entwickler

## Stichpunkte für die konzeptionelle Sicht

S ist eine Webanwendung die auf dem Framework JSF basiert als Container dient die Server Software Wildfly. Interaktion erfolgt über HTTP/S. 
S komuniziert über SQL mit einer H2 Datenbank

Teil zu MVC
        
## Chinese Menu
Die Basisversion umfasst alle oben genannten Anforderungen, die nicht explizit als optional mit der Notation O«X» gekennzeichnet sind. Die Basisversion muss vollständig und in ausreichender Qualität (sowohl äußere Qualität, d.h. Fehlerfreiheit und Benutzbarkeit, als auch innere Qualität, d.h. Verständlichkeit und Änderbarkeit) fürs Bestehen umgesetzt sein. Durch eine besonders gute Umsetzung der Funktionen der Basisversion können bis zu fünf Zusatzpunkte erworben werden.

Die folgenden weiteren Zusatzpunkte werden vollständig vergeben, wenn die dazugehörige optionale Anforderung vollständig und benutzbar umgesetzt wurde. Ansonsten gibt es entsprechend weniger Punkte.
### **(O)«Dynamische Zustandsautomaten» (15P)**
Anstelle der vorkonfigurierten Zustandsautomaten für die ProzessSchritte möchte der Kunde die Möglichkeit haben diese selber zu konfigurieren. Die Zustandsautomaten sind einfacher linearer Natur und bieten keine Verzweigungen.
### **O«Lagerverwalter» (10P)**
Übersicht über die im Lager vorhandenen Proben, gruppiert nach einstellbaren Proben-ID-Mustern oder Probenattributen, und Hervorhebung von Gruppen, die unter einen konfigurierbaren Schwellenwert fallen.
### **O«Parameter.Import» (5P)**
Beim Anlegen von Aufträgen sollen die Prozessketten- und ProzessSchrittparameter mit Hilfe einer JSON-Datei importiert werden können.
### **O«Parameter.Export» (5P)**
Ein Technologe soll die Parameter seines ProzessSchrittes nach JSON exportieren und runterladen können.
### **O«Sprache» (5P)**
Die Sprache der GUI kann von jedem Nutzer individuell eingestellt werden. Erforderliche Sprachen hierfür sind englisch und deutsch.
### **O«UI.Ankündigung» (10P)**
Der Technologe bekommt eine Übersicht der Aufträge angezeigt, die sich auf dem Weg zu ihm befinden. Dazu wird auch die voraussichtliche Ankunft angezeigt.
### **O«UI.AlteAufträge» (5P) **
Visuelle Hervorhebung von Arbeitsaufträgen, deren letzte Änderung länger zurückliegt. Der Zeitraum kann vom Administrator vorgegeben werden.
### **O«WebService.API» (15P) **
Implementieren Sie eine auf HTTP-basierende REST-Schnittstelle, die es erlaubt, den Datenbestand der Anwendung abzufragen und mit Hilfe geeigneter Methoden zu aktualisieren. Diese Schnittstelle soll später genutzt werden, um externe Auswertungen auf den erhobenen Daten umzusetzen oder um die Priorisierungen von außen zu steuern. Implementieren Sie eine externe Auswertung, die die Dauer der ProzessSchritte auf Basis der erhobenen Logs berechnet. Dazu soll der Mittelwert aller bereits protokollierten Dauern eines Schritts berechnet werden.
### **O«WebService.Upload» (10P) **
 Für bestimmte ProzessSchritte müssen die Technologen ihre Experimentergebnisse in die SFB-interne Probendatenbank einpflegen. Das zu entwickelnde System soll die Technologen an diese Aufgabe erinnern, indem es die Proben von abgeschlossenen Arbeitsaufträge anzeigt. Implementieren Sie zudem eine auf HTTP-basierende REST-Schnittstelle, die es erlaubt, den Daten-Upload der einzelnen Experimente für Proben zu vermerken, so dass diese dann aus der Übersicht der Technologen entfernt werden.
### **O«UI.Auslastung» (15P) 
Der Prozesskettenplaner wünscht eine Übersicht über die Auslastung der verschiedenen Experimentierstationen. Von Interesse sind die Anzahl der Aufträge/Proben und die darauf basierende geschätzte Gesamtdauer.
## Fragerunde
    Nutzerverwaltung
    Sehr Restriktiv Lesen und Schreiben nur wenn Zugeordnet
    Feste Rollen, Nutzer kann Transport
    Beschränken auf Exports
    BSP: Prozesskette
    Schema der DB
    ID Projekt - Beliebige Folgen von Zahlen mit Punten getrennt
    Nutzer : Typ Rechte, 
    Nutzer: Rolle Forscher = (Station x)
    Vorhandene Resultate müssen nicht früh erkannt werden
    Nennung der Blockierte Maschiene: Dann Technologe setzt status auf 
        Kaputt, PKAdmin sieht das, 
    Material Schein und Proben
    

Use Cases
    Übersicht
    Zustandsaktualisierung
    Proben Annahme und Weitergabe
    

Die Software Attraktiv machen
Virtuelles Lager, Final Archiviert kann nicht für PK verwendet werden
Protok der Stationen -> Keine Arbeitsprofile

Loging: Mit assozaizions Klassen
Dauer des PS: Summe der Zustands übergänge
# Recherche zu UML
- SysMLfor Systems Engineerin g 2nd Edition, Seite 131 (Pfeile/ Datensicht)
- 
# Fragen
**Neue Fragen:**
* Unterschied zwischen Auftrag (Parameter für den Prozess)? Definition? was genau gibt es alles außer dem Auftrag mit der Prozesskette und den Parametern für diese.  




**Fragen beantwortet:**
* Woher was das BAckend an welcher Station ein PS ausgeführt werden kann
    * Feste Station, PKA legt Station fest
* Sind die Aufgaben der Stationen Statisch
    * Admin kann PK Aufgaben zuweisen
* AW4: Bearbeitung: Bei Problemen, wie soll die Zeit aktuallisiert werden
    * Geschätze dauer wird vom pka gestetzt
        Template hat die Dauer. Retrospektive Boxplots für Schätzungen editieren
        Erfassung im Protokl. Keine geschätze dauer automatisch berechnen
* Themaabweichende Daten: Wenn freigeben aber nicht hochgeladne, dann upoload in davis und änderung in davis, wie ändert es sich hier
    Entweder Upload vor oder nach freigabe 
* Richtig: falls Träger vom Transport geliefert, wie viele Technologen können ändern
        Nur der der akzeptiert
    * 1 Technologe für einen PS!

Serialiserung des Protokols (json? CSV / PDF) Freie Wahl

Nur ein Technologe, bei ankunft des Traegers, nimmt er an. nicht mehr alle der station sondern nur noch ein Technologe

M: PS hat flag uploaded, nicht im FSM


BSP: PK name aus Template, wenn pka namen ändert, soll keine ref für die protokol generoeng 

Beim druchführen eines PS hat ein Technologe diese akzeptiert

Gloassar: 
    Auftrag == Instanz PK


Station: uuid

Schnittstellen beschreibung: 
Fertiges Maven Projekt mit Daten Modell in Java
Inform von Interfaces. Methoden Aufschreiben
Dokumentieren mit Javadocs

Modulsicht: Welche klassen gibt es:
    Klassen Diagramme, Nehme alle Klassen. 
    A.java alle methoden als Signaturen
    
Fertiges Maven Projekt mit implementierten Daten Klassen

Änderungsdokument spezifiert wie sich die ab geändert hat



Wann ist es Sinvoll in der PK zu speichern
Wann ist es Sinvoll in dem Auftrag zu speichern

BSP Träger werden vom Logistiker im Auftrag gespeichert
Dann Daten aus Auftrag als Konstruktionwerte für die PK Instanz genutzt