#language: nl

Functionaliteit: Security Test features
  #SSD-normen: 01 - Out of Scope

#  SSD-normen: 02
  Scenario: Security-02-001: Als hacker behoor ik geen gevoelige informatie te krijgen uit cookies van reguliere gebruikers.
    Gegeven een burger opent de inlogpagina
    En een burger navigeert naar "Mijn DigiD"
    En een burger kiest voor 'Ik wil inloggen met gebruikersnaam en wachtwoord'
    En een burger logt in met gebruikersnaam "u_300649927" en wachtwoord "Pw_300649927"
#    Als een hacker kijkt naar de cookies van de burger
#    Dan ziet een hacker geen ongeregistreerde cookies

#  Scenario: Security-02-002: Als hacker behoor ik de sessie van een reguliere gebruiker niet te kunnen hergebruiken door een cookie te kopiëren.
#    Gegeven een externe gebruiker opent de "loginpagina"
#    En een externe gebruiker logt in
#    Als een hacker de sessie cookie gebruikt om een nieuwe sessie op te zetten
#    Dan is een hacker niet ingelogd op het account van de externe gebruiker


  #SSD-normen: 03 - Out of Scope

  #SSD-normen: 04
  Scenario: Security-04-001: Als externe gebruiker behoor ik alleen encrypted met de website te kunnen verbinden
    Als een externe gebruiker verbind met de "hoofdpagina" zonder encryptie
    Dan ziet de externe gebruiker dat zijn verbinding toch encrypted is


  #SSD-normen: 05
#  Scenario: Security-05-001: Als hacker behoor ik geen authenticatie te kunnen omzeilen m.b.v. sql injectie tijdens het inloggen
# DigiD inlog buiten scope.
  # Gegeven een hacker opent de "loginpagina"
  # Als een hacker sql code invoert
  # Dan is een hacker niet ingelogd


  Scenario: Security-05-002: Als hacker behoor ik geen delen van de website te kunnen bezoeken zonder in te loggen, waar dit wel vereist is
  # Gegeven een hacker is niet ingelogd
    Als een hacker een pagina opent voor geauthenticeerde gebruikers
    Dan komt een hacker niet op een pagina voor geauthenticeerde gebruikers

#  Scenario: Security-05-003: Als hacker behoor ik geen functionaliteit te kunnen uitvoeren zonder in te loggen, waar dit wel vereist is.
#    # Gegeven een hacker is niet ingelogd
#  Als een hacker een actie uitvoert voor geauthenticeerde externe gebruikers
#  Dan wordt deze opdracht niet uitgevoerd

    #SSD-normen 6-11 - Out of Scope
#  #SSD-normen: 06
#  Scenario: Security-06-001: Als hacker behoor ik geen authenticatie voor interne gebruikers te kunnen omzeilen m.b.v. sql injectie tijdens het inloggen
  # DigiD inlog buiten scope. (mogelijk andere vorm van login voor admin?)
    # Gegeven een hacker opent de "interneloginpagina"
  # Als een hacker sql code invoert
  # Dan is een hacker niet ingelogd
#
#  Scenario: Security-06-002: Als hacker behoor ik geen delen van de website te kunnen bezoeken die bedoeld zijn voor interne gebruikers zonder in te loggen, waar dit wel vereist is
#    #Gegeven een hacker is niet ingelogd
#    Als een hacker opent een pagina voor interne gebruikers
#    Dan komt een hacker niet op een pagina voor interne gebruikers
#
#   #SSD-normen: 07 - Out of Scope
#
#  #SSD-normen: 08
#  Scenario: Security-08-001: Als hacker behoor ik geen delen van de website te kunnen bezoeken die bedoeld zijn voor interne gebruikers als ik ingelogd ben met het account van een externe gebruiker
#    Gegeven een hacker opent de "interneloginpagina"
#    En een hacker logt in als een externe gebruiker
#    Als een hacker opent een pagina voor interne gebruikers
#    Dan komt een hacker niet op een pagina voor interne gebruikers
#
#  Scenario: Security-08-002: Als hacker behoor ik geen delen van de website te kunnen bezoeken die bedoeld zijn voor interne gebruikers zonder in te loggen, waar dit wel vereist is
#    #Gegeven een hacker is niet ingelogd
#    Als een hacker opent een pagina voor interne gebruikers
#    Dan komt een hacker niet op een pagina voor interne gebruikers
#
   #SSD-normen: 09 (DigiD)
#
##SSD-normen: 10
#  Scenario: Security-10-001: Als hacker behoor ik niet meerdere sessies tegelijk op te kunnen zetten waar dit niet strict noodzakelijk is
#    Als een hacker "2" sessies tegelijk opzet
#    Dan is een hacker niet met "2" sessies tegelijk actief
#
#    #Scenario: Security-10-002: Als hacker behoor ik niet meer sessies tegelijk op te kunnen zetten dan strict noodzakelijk is
#    #Als een hacker "3" sessies tegelijk opzet
#    #Dan is een hacker niet met "3" sessies tegelijk actief

  #SSD-normen: 12
  Scenario: Security-12-001: Als externe gebruiker behoren mijn sessies niet na lange tijd nog actief te blijven
    Gegeven een externe gebruiker opent de "loginpagina"
    En een externe gebruiker logt in
    Als een externe gebruiker voor lange tijd inactief is
    Dan is een externe gebruiker uitgelogd

  Scenario: Security-13-002: Als hacker behoor ik geen sessies van uitgelogde gebruikers te kunnen hergebruiken
    Gegeven een externe gebruiker opent de "loginpagina"
    En een externe gebruiker logt in
    En een hacker kopieert de sessie cookie
    En een externe gebruiker logt uit
    Als een hacker met de sessie cookie een pagina opent voor geauthenticeerde gebruikers
    Dan komt een hacker niet op een pagina voor geauthenticeerde gebruikers

#  #SSD-normen: 13 - Geen specifieke testen voor deze norm.
#
#  #SSD-normen: 14
  Scenario: Security-14-001: Als hacker behoor ik geen sessiesleutels van een externe gebruiker te kunnen voorspellen omdat deze niet uniek zijn
    Gegeven een externe gebruiker opent de "loginpagina"
    En een externe gebruiker logt in
    Als een hacker kijkt naar de inhoud van de cookies van een externe gebruiker
    En een externe gebruiker logt uit
    En een externe gebruiker logt in
    Dan ziet een hacker dat de inhoud van de sessie cookie is veranderd

  Scenario: Security-14-002: Als hacker behoor ik geen sessiesleutels te kunnen creëren
    Gegeven een externe gebruiker opent de "loginpagina"
    En een externe gebruiker logt in
    Als een hacker de sessie kopieert met een ongeldige sessie cookie
    En een hacker een pagina opent voor geauthenticeerde gebruikers
    Dan komt een hacker niet op een pagina voor geauthenticeerde gebruikers

#       #SSD-normen: 18
  Scenario: Security-18-001: Als hacker behoor ik geen informatie over de webserver te verkrijgen door de HTTP-request te manipuleren
    Gegeven een hacker opent de "hoofdpagina"
    Als een hacker de GET-request verandert naar een OPTIONS-request
    Dan komt een hacker op een scherm met de titel "Action Controller: Exception caught"



    #Dan komt een hacker op een scherm met de titel "Action Controller: Exception caught"

      #SSD-normen: 19
  Scenario: Security-19-001: Als hacker behoor ik geen XSS script toe te kunnen voegen aan de URL van hoofdpagina.
    Gegeven een hacker opent de "zoekpagina"
    Als een hacker een javascript alert toevoegt aan de URL
    Dan ziet een hacker geen javascript alert
#    En bevat de URL geen javascript alert

  Abstract Scenario: Security-19-002: Als hacker behoor ik geen gevaarlijke SQL tekens in te kunnen vullen in een zoekveld.
    Gegeven een hacker opent de "zoekpagina"
    Als een hacker het zoekveld invult met de waarde "<input>"
    Dan ziet een hacker na het zoeken de waarde "<output>"
    Voorbeelden:
      | input     | output       |
      | <script>te      | TODO       |
      | 'test     | TODO   |
      | <test | TODO |



  Abstract Scenario: Security-19-002: Als hacker behoor ik geen gevaarlijke SQL tekens in te kunnen vullen in een zoekveld.
    Gegeven een hacker opent de "formulierpagina"
    En een hacker het formulier vult
    Als een hacker het formulierveld invult met de waarde "<input>"
    Dan ziet een hacker na het zoeken de waarde "<output>"
    Voorbeelden:
      | input     | output       |
      | <script>te      | TODO       |
      | 'test     | TODO   |
      | <test | TODO |



  #SSD-normen: 20 - Geen specifieke testen voor deze norm.


#      #SSD-normen: 21
#  Abstract Scenario: Security-21-001: Als hacker behoor ik geen SQL injectie uit te kunnen voeren in het zoekveld van de header.
#    Gegeven een hacker opent de "hoofdpagina"
#    Als een hacker het zoekveld invult met de waarde "<input>"
#    Dan ziet een hacker in de zoekresultaten de waarde "<output>"
#    Voorbeelden:
#      | input       | output                                             |
#      | a'OR 'a'='a | U heeft gezocht naar 'a&#039OR &#039a&#039=&#039a' |
#      | 1 OR 1=1    | resultaten voor '1 OR 1=1'                         |
#      | a or true   | U heeft gezocht naar 'a or true'                   |
#      | *           | U heeft gezocht naar 'wildcard:*'                  |
##      | 1\xbf\x27 OR 1=1 | resultaten voor '1' OR 1=1'                        |
#
#  #SSD-normen: 22
#  Abstract Scenario: Security-22-001: Als hacker behoor ik geen ongewenste invoer te kunnen versturen middels een URL.
#    Gegeven een hacker opent de "hoofdpagina"
#    Als een hacker een "input" toevoegt aan de URL
#    Dan ziet een hacker "output"
#    Voorbeelden:
#      | input | output |
#      | <     | TODO   |
#      | >     | TODO   |
#
#  Abstract Scenario: Security-22-002: Als hacker behoor ik geen ongewenste invoer te kunnen versturen middels een formulier.
#    Gegeven een hacker opent de "formulierpagina"
#    Als een hacker het formulier vult
#    En een hacker een "input" toevoegt aan "veld" in het formulier
#    Dan ziet een hacker "output"
#    Voorbeelden:
#      | input | veld        | output |
#      | <     | Achternaam  | TODO   |
#      | >     | E-mailadres | TODO   |
#
#
#  Abstract Scenario: Security-22-003: Als hacker behoor ik geen ongewenste invoer te kunnen versturen middels een zoekveld.
#    Gegeven een hacker opent de "zoekpagina"
#    Als een hacker het zoekveld invult met de waarde "<input>"
#    Dan ziet een hacker na het zoeken de waarde "<output>"
#    Voorbeelden:
#      | input | output |
#      | <     | TODO   |
#      | >     | TODO   |
#
#  Scenario: Security-22-004: Als hacker behoor niet te veel invoer te kunnen versturen.
#    Gegeven een hacker opent de "formulierpagina"
#    Als een hacker het formulier vult
#    En een hacker 64000 tekens toevoegt aan de achternaam
#    Dan ziet een hacker TODO
#
#
#  #SSD-normen: 23 - Geen specifieke testen voor deze norm.

      #SSD-normen: 24
  Scenario: Security-24-001: Als hacker behoor ik geen informatie te krijgen uit niet noodzakelijke HTTP-headers in de HTTP-response.
    Gegeven een hacker zet een HTTP-connectie op met "hoofdpagina"
    Als een hacker kijkt naar de HTTP-headers
    Dan ziet een hacker geen ongeregistreerde headers in de HTTP-response

      #SSD-normen: 25
  Scenario: Security-25-001: Als hacker behoor ik geen informatie te krijgen uit de inhoud van niet noodzakelijke HTTP-headers in de HTTP-response.
    Gegeven een hacker zet een HTTP-connectie op met "hoofdpagina"
    Als een hacker kijkt naar de HTTP-headers
    Dan ziet een hacker geen ongeregistreerde informatie in de headers
    # registratie van header value's is lastig met whitelist omdat dit variabelen kunnen zijn. (denk aan csrf token, of tijden)


  Scenario: Security-25-002: Als hacker behoor ik geen informatie te krijgen uit de inhoud van de HTTP SERVER-header.
    Gegeven een hacker zet een HTTP-connectie op met "hoofdpagina"
    Als een hacker kijkt naar de HTTP-headers
    Dan ziet een hacker geen ongeregistreerde informatie in de server header
#
#          #SSD-normen: 26
  Scenario: Security-26-001: Als hacker behoor ik alleen strict noodzakelijke HTTP-methoden aan te kunnen roepen.
    Gegeven een hacker opent de "hoofdpagina"
    Als een hacker de GET-request verandert naar een OPTIONS-request
    Dan ziet een hacker dat alleen GET, POST en OPTIONS zijn toegestaan

#  SSD-normen: 27
#  Scenario: Security-27-001: Als hacker behoor ik geen informatieve meldingen te krijgen bij het verkeerd invullen van formulieren
#    Gegeven een hacker opent de "formulierpagina"
#    En een hacker het formulier vult
#    Als een hacker het formulierveld vult met "ongeldige waarden"
#    Dan krijgt de hacker de standaard melding te zien
#    Als een hacker het formulierveld vult met "sql"
#    Dan krijgt de hacker de standaard melding te zien
#    Als een hacker het formulierveld vult met "xss"
#    Dan krijgt de hacker de standaard melding te zien
#    Als een hacker het formulierveld vult met "buffer overflow"
#    Dan krijgt de hacker de standaard melding te zien

  #SSD-normen: 28
  Scenario: Security-28-001: Als hacker behoor ik geen informatie te krijgen uit commentaarregels in de broncode van werkNl
    Gegeven een hacker opent de "hoofdpagina"
    Als een hacker kijkt naar de broncode
    Dan ziet een hacker geen ongeregistreerde commentaarregels

  Scenario: Security-28-002: Als hacker behoor ik geen informatie te krijgen uit META/PARAM/OBJECT/INPUT HTML-headers in de broncode die niet strict noodzakelijk is voor de functionaliteit
    Gegeven een hacker opent de "hoofdpagina"
    Als een hacker kijkt naar de broncode
    Dan ziet een hacker geen ongeregistreerde waarden in de HTML-headers



    #SSD-normen: 29 - Geen specifieke testen voor deze norm.

  #SSD-normen: 30 - Out of Scope.



