# Softwareprojekt-2
<h5> Website can be found under http://127.0.0.1:8080/Softwareprojekt2-1.0-SNAPSHOT/ <h5>

<h1>==========Change Log==========</h1>

<h3>*Please provide date along with all changes made.*</h3>



<h1>==========Versions==========</h1>

<h3>*Please also provide date.*</h3>

<h1>===== Anforderungen =====</h1>

<h2>Fertiges wird durchgestrichen!</h2>

<div style="color: red">
<h4 id="rollen">Rollen</h4>

<p>Die folgenden Rollen sind vorzusehen:</p>
<ul>
    <li>Administrator: Verwaltet Nutzer, Experimentierstationen und Berechtigung. </li>
    <li>Prozesskettenplaner: Legt Prozessschritte an und verwaltet Prozessketten.</li>
    <li>Logistiker: Verwaltet das Probenlager und -archiv.</li>
    <li>Transporteur: Transportiert Proben zwischen Stationen und dem Lager (<strong>O«Transporteur»</strong>)</li>
    <li>Technologe: Hat Übersicht über seine Arbeitsaufträge.</li>
</ul>
<h4 id="anforderungen">Anforderungen</h4>
<h5 id="administrator">Administrator</h5>
<strike>
<ul>
    <li>Übersicht über alle existierenden Nutzer
        <ul>
            <li>Anlegen neuer Nutzer<ul>
            <li>Neue Nutzer werden per Mail über ihren Account informiert.</li>
        </ul>
    </li>
    <li>
        <strike>Löschen bestehender Nutzer</strike>
    </li>
    <li>
        <strike>Bearbeiten bestehender Nutzer</strike>
    </li>
</ul>
</strike>
</li>
    <li>
        <strike>Übersicht über alle Experimentierstationen
            <ul>
                <li>Anlegen von neuen Experimentierstationen</li>
                <li>Löschen von bestehenden Experimentierstationen</li>
                <li>Bearbeiten von bestehenden Experimentierstationen</li>
                <li>Zuordnen von Nutzern zu Experimentierstation</li>
            </ul>
        </strike>
    </li>
    <strike><li>Übersicht über alle Standorte
        <ul>
            <li>Anlegen von neuen Standorten</li>
            <li>Löschen von bestehenden Standorten</li>
            <li>Bearbeiten von bestehenden Standorten</li>
        </ul>
    </strike></li>
<li><strike>Konfiguration globaler Einstellungen</strike><ul>
<li><strike>Einstellung der Zeiten für alte Aufträge (<strong>O«UI.AlteAufträge»</strong>)</strike></li>
</ul>
</li>
<li><strike>Backup der Systemdaten</strike></li>
</ul>
<h5 id="prozesskettenplaner">Prozesskettenplaner</h5>
<ul><strike>
    <li>Übersicht über alle Prozessschritte<ul>
    <li>Anlegen neuer Prozessschritte<ul>
    <li>Auswahl von vordefinierten Abläufen (Zustandsautomat)</li>
    <li>Auswahl von dyn. Abläufen (<strong>O«Dynamische Zustandsautomaten»</strong>)</li>
    <li>Eingabe von voraussichtlicher Prozessschrittdauer</li>
    <li>Definition von Prozessschrittparametern (Name, Einheit)</li>
    <li>Zuordnung von Experimentierstation</li>
    <li>Setzen von Attributen wie "einfärbend", Präparationsart</li>
</ul>
</li>
    <li>Löschen bestehender Prozessschritte</li>
    <li>Bearbeiten von "nicht gestarteten" Prozessschritten</li>
    <li>Hervorhebung der Prozessschritte, die im Zustand auf kaputt gesetzt sind</li>
    </strike>
</ul>
</li>
    <strike><li>Übersicht über dynamische Abläufe (<strong>O«Dynamische Zustandsautomaten»</strong>)<ul>
    <li>Anlegen von Zustandsautomaten</li>
    <li>Löschen von Zustandsautomaten</li>
    <li>Bearbeiten von unbenutzten Zustandsautomaten</li></strike>
</ul>
</li>
    <li><strike>Übersicht über alle Prozessketten<ul>
    <li>Anlegen von neuen Prozessketten aus bestehenden Prozessschritten</li>
    <li>Löschen von bestehenden Prozessketten</li>
    <li>Bearbeiten von bestehenden Prozessketten (nicht durchgeführt oder freigegeben)</li>
</ul>
</strike>
</li>
<li><strike>Übersicht über Aufträge</strike><ul>
    <li><strike>Anlegen von Aufträgen</strike><ul>
    <li><strike>Manuelles Setzen der Prozess(schritt)parameter</strike></li>
    <li>Import der Prozess(schritt)parameter (<strong>O«Parameter.Import»</strong>)</li>
</ul>
</li>
    <li><strike>Freigabe von Aufträgen</strike><ul>
    <li>Logistiker muss daraufhin Proben zuordnen</li>
</ul>
</li>
    <li><strike>Stoppen von Aufträgen</strike><ul>
    <li><strike>Aktueller Prozessschritt wird beendet</strike></li>
    <li><strike>Aufträge verschwinden aus der Übersicht der Technologen</strike></li>
    <li>Transport bringt Proben ins Lager (<strong>O«Transporteur»</strong>)</li>
</ul>
</li>
    <li><strike>Löschen von Aufträgen</strike> </li>
    <li><strike>Bearbeiten von Aufträgen (wenn noch nicht freigegeben)</strike></li>
    <li><strike>Priorisierung der Aufträgen</strike></li>
</ul>
</li>
<ul><li>Übersicht über Arbeitsauslastung der Experimentierstationen (<strong>O«UI.Auslastung»</strong>)</li>
<li>Export des Protokolls nach JSON, so dass eine maschinelle Auswertung möglich ist</li></ul>
</ul>
<h5 id="transporteur-otransporteur">Transporteur (<strong>O«Transporteur»</strong>)</h5>
<ul>
<li>Übersicht über anstehende Transportaufträge</li>
<strike><li>Meldet Probenverlust</li></strike>
</ul>
<h5 id="logistiker">Logistiker</h5>
<ul>
<strike><li>Übersicht über Träger<ul></strike>
<li>Anlegen von neuen Trägern</li>
<li>Löschen von alten Trägern</li>
</ul>
</li>
<li>Übersicht über freigegebene Aufträge<ul>
<li>Zuordnen von Trägern/Proben zu freigegebenen Aufträgen<ul>
<li>Manuell (Korrektheit wird von außen sichergestellt)</li>
<li>Automatisch auf Basis von geforderten Parameterwerten (<strong>O«Lagerverwaltung»</strong>)</li>
</ul>
</li>
</ul>
</li>
<li>Übersicht über Probenmengen (<strong>O«Lagerverwaltung»</strong>)<ul>
<li>Klassifizierung nach Parameterwerten wie Legierung und Wärmebehandlung</li>
</ul>
</li>
<li><strike>Übersicht über archivierten Proben (<strong>O«Lagerverwaltung»</strong>)</strike></li>
<li><strike>Übersicht über Probenstandorte (<strong>O«Lagerverwaltung»</strong>)</strike></li>
</ul>
<h4 id="technologe">Technologe</h4>
<ul>
<li><strike>Übersicht über zugehörige Experimentierstationen</strike><ul>
<li>Sichtwechsel zwischen Experimentierstationen</li>
</ul>
</li>
<li>Übersicht seiner aktuellen Arbeitsaufträge geordnet nach Priorisierung<ul>
<li>Detaillierte Ansicht seiner Aufträge (Prozessparameter etc)</li>
<li>Export der Prozessparameter als JSON (<strong>O«Parameter.Export»</strong>)</li>
<li>Kenntlichmachung alter Arbeitsaufträge (<strong>O«UI.AlteAufträge»</strong>)</li>
</ul>
</li>
<li>Übersicht der angekündigten Arbeitsaufträge (<strong>O«UI.Ankündigung»</strong>)</li>
<li>Aktualisierung des Zustands eines Arbeitsauftrags<ul>
<li>Weiterschalten zum nächsten Schritt im Zustandsautomaten (mit automatischer
  oder manueller Eingabe der Transitionszeit für das Protokoll)</li>
</ul>
</li>
<li>Ansicht der Proben mit ausstehendem Daten-Upload (<strong>O«WebService.Upload»</strong>)</li>
<li>Angabe, falls Experimentierstation kaputt</li>
<li>Meldet Probenverlust</li>
<li>Urformende Prozessschritte legen, wenn der Auftrag bearbeitet wurde, die Proben
   mit ihren IDs an.<ul>
<li>ProbenIDs haben die Form [A-Z][0-9][0-9].[0-9]+(.[0-9]+)+</li>
</ul>
</li>
<li>Können Kommentare zu Proben hinzufügen </li>
</ul>
<h4 id="nichtfunktionale-anforderungen">Nichtfunktionale Anforderungen</h4>
<ul>
<li><strike>Sicherheit</strike><ul>
<li><strike>Nutzer müssen sich am System authentisieren</li>
<li><strike>Jeder Nutzer soll nur die Informationen einsehen können, die er für seine
  Arbeit benötigt</strike></li></strike>
</ul>
</li>
<li><strike>Benutzeroberfläche soll einfach benutzbar sein</strike></li>
<li><strike>Benutzeroberfläche muss von mehreren Nutzern gleichzeitig benutzt werden können</strike></li>
</ul>
<h4 id="zustandsautomaten">Zustandsautomaten</h4>
<ul>
<li>Technologe<ul>
<li>Angenommen</li>
<li>In Bearbeitung</li>
<li>Bearbeitet</li>
<li>Weitergeleitet</li>
</ul>
</li>
<li>Transporteur<ul>
<li>Abgeholt</li>
<li>Abgegeben</li>
</ul>
</li>
</ul>
<h4 id="chinese-menu">Chinese Menu</h4>
<p>Die Basisversion umfasst alle oben genannten Anforderungen, die nicht explizit
als optional mit der Notation <strong>O«X»</strong> gekennzeichnet sind. Die Basisversion muss
vollständig und in ausreichender Qualität (sowohl äußere Qualität, d.h.
Fehlerfreiheit und Benutzbarkeit, als auch innere Qualität, d.h. Verständlichkeit
und Änderbarkeit) fürs Bestehen umgesetzt sein. Durch eine besonders gute Umsetzung
der Funktionen der Basisversion können bis zu fünf Zusatzpunkte erworben werden.</p>
<p>Die folgenden weiteren Zusatzpunkte werden vollständig vergeben, wenn die
dazugehörige optionale Anforderung vollständig und benutzbar umgesetzt wurde.
Ansonsten gibt es entsprechend weniger Punkte.</p>
<p><strong>O«Dynamische Zustandsautomaten» (15P)</strong> Anstelle der vorkonfigurierten
Zustandsautomaten für die Prozessschritte möchte der Kunde die Möglichkeit haben
diese selber zu konfigurieren. Die Zustandsautomaten sind einfacher linearer
Natur und bieten keine Verzweigungen.</p>
<p><strong>O«Lagerverwalter» (10P)</strong> Übersicht über die im Lager vorhandenen Proben,
gruppiert nach einstellbaren Proben-ID-Mustern oder Probenattributen, und
Hervorhebung von Gruppen, die unter einen konfigurierbaren Schwellenwert fallen.</p>
<p><strong>O«Transporteur» (5P)</strong> Der Transporteur ist für die Abholung und Auslieferung
von Trägern zuständig. Dafür benötigt er einen eigenen Zustandsautomaten und eine
entsprechende Ansicht, wo er sehen kann, wo er welche Träger abholen muss.</p>
<p><strong>O«Parameter.Import» (5P)</strong> Beim Anlegen von Aufträgen sollen die Prozessketten-
und Prozessschrittparameter mit Hilfe einer JSON-Datei importiert werden können.</p>
<p><strong>O«Parameter.Export» (5P)</strong> Ein Technologe soll die Parameter seines Prozessschrittes
nach JSON exportieren und runterladen können.</p>
<p><strong>O«Sprache» (5P)</strong> Die Sprache der GUI kann von jedem Nutzer individuell eingestellt
werden. Erforderliche Sprachen hierfür sind englisch und deutsch.</p>
<p><strong>O«UI.Ankündigung» (10P)</strong> Der Technologe bekommt eine Übersicht der Aufträge
angezeigt, die sich auf dem Weg zu ihm befinden. Dazu wird auch die voraussichtliche
Ankunft angezeigt.</p>
<p><strong>O«UI.AlteAufträge» (5P)</strong> Visuelle Hervorhebung von Arbeitsaufträgen, deren
letzte Änderung länger zurückliegt. Der Zeitraum kann vom Administrator vorgegeben
werden.</p>
<p><strong>O«WebService.API» (15P)</strong> Implementieren Sie eine auf HTTP-basierende
REST-Schnittstelle, die es erlaubt, den Datenbestand der Anwendung abzufragen
und mit Hilfe geeigneter Methoden zu aktualisieren. Diese Schnittstelle soll
später genutzt werden, um externe Auswertungen auf den erhobenen Daten umzusetzen
oder um die Priorisierungen von außen zu steuern. Implementieren Sie eine externe
Auswertung, die die Dauer der Prozessschritte auf Basis der erhobenen Logs
berechnet. Dazu soll der Mittelwert aller bereits protokollierten Dauern eines
Schritts berechnet werden.</p>
<p><strong>O«WebService.Upload» (10P)</strong> Für bestimmte Prozessschritte müssen die Technologen
ihre Experimentergebnisse in die SFB-interne Probendatenbank einpflegen. Das zu
entwickelnde System soll die Technologen an diese Aufgabe erinnern, indem es die
Proben von abgeschlossenen Arbeitsaufträge anzeigt. Implementieren Sie zudem eine
auf HTTP-basierende REST-Schnittstelle, die es erlaubt, den Daten-Upload der
einzelnen Experimente für Proben zu vermerken, so dass diese dann aus der Übersicht
der Technologen entfernt werden.</p>
<p><strong>O«UI.Auslastung» (15P)</strong> Der Prozesskettenplaner wünscht eine Übersicht über
die Auslastung der verschiedenen Experimentierstationen. Von Interesse sind die
Anzahl der Aufträge/Proben und die darauf basierende geschätzte Gesamtdauer.</p>
<h4 id="technische-anforderungen">Technische Anforderungen</h4>
<ul>
<li>Implementieren Sie in Java 11 oder höher</li>
<li>Client-Server-Architektur<ul>
<li>Server<ul>
<li>Verwendung von JavaEE 8</li>
<li>JSF als Framework zur Erstellung der Oberfläche</li>
<li>Applikations-Container Wildfly 18</li>
<li>Zielplattform: Windows, Linux , MacOS</li>
<li>Relationale Datenbank H2 muss für die Persistenz benutzt werden.
  Persistenz-Frameworks sind erlaubt, aber dann deklarative Verwendung
  von SQL-ähnlichen Abfragen verlangt.</li>
</ul>
</li>
<li>Clients<ul>
<li>Gängige Browser (Firefox, Internet Explorer, Edge, Safari) in den
  aktuellen Versionen unter Windows 10, Linux und MacOS</li>
</ul>
</li>
</ul>
</li>
<li>Maven muss als Build-System eingesetzt werden</li>
<li>Mehrere Nutzer sollen auf das System gleichzeitig zugreifen können.</li>
</div>