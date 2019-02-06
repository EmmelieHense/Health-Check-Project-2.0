Feature: Healthcheck Outtake Test

  Scenario: Aanvragen DigiD account met controle via sms
    Given ik ga naar MijnDigiD op omgeving "t1"
    When ik navigeer naar "Aanvragen"
    #Then kom ik op een scherm met de h1-titel "DigiD aanvragen"
    And ik vul mijn bsn "304637828" in
    And ik vul mijn geboortedatum "25","04","1988" in
    And ik vul mijn adres "2563SB","21" in
    And ik klik op de knop Volgende
    And ik wacht op de GBA controle
#    #Then kom ik op een scherm met de h1-titel "DigiD aanvragen"
    Then kom ik op een scherm met de titel "DigiD: Aanvragen | Gegevens"
#    When ik voer "u_304637828" als gebruikersnaam in
#    And ik voer "Pw_304637828" als wachtwoord in
#    And ik voer "Pw_304637828" als wachtwoord-bevestiging in
#    And ik voer "06-58787878" als telefoonnummer in
#    And ik voer "test@test.nl" als e-mailadres in
#    And ik klik op de knop "Volgende"
#    Then kom ik op een scherm met de titel "DigiD: Aanvragen | Controle via sms"
#    When ik open een nieuw tabblad
#    And ik haal de smscode van de eindgebruiker met bsn "304637828" op uit config op "t1" en vul deze in het sms-invoerveld in
#    And ik klik op de knop "Volgende"
#    Then kom ik op een scherm met de titel "DigiD: Aanvragen | Controle via e-mail"
#    And ik haal de emailcode van de eindgebruiker met bsn "304637828" op uit config op "t1" en vul deze in het email-invoerveld in
#    And ik klik op de knop "Volgende"
#    Then kom ik op een scherm met de titel "DigiD: Aanvragen | Bevestiging aanvraag"
#    Then kom ik op een scherm met de h1-titel "Let op, wij sturen u een brief!"
#    And moet ik de melding "U ontvangt binnen 3 werkdagen een brief met deze activeringscode." zien

  Scenario: Activeren DigiD account met controle via sms
    Given ik ga naar MijnDigiD op omgeving "t1"
    When ik navigeer naar "Activeren"
    Then kom ik op een scherm met de titel "DigiD: Activeren | Inloggen"
    And ik kies voor het activeren van mijn DigiD
    And ik voer mijn gebruikersnaam "u_304637828" in
    And ik voer mijn wachtwoord "Pw_304637828" in
    And ik klik op de knop "Inloggen"
    Then kom ik op een scherm met de titel "DigiD: Activeren | Controle via sms"
    And kom ik op een scherm met de h1-titel "Inloggen"
    And moet ik de melding "Er is een sms-code gestuurd naar:" zien
#   And ik zie een verplicht veld voor "Vul de code in die u op uw telefoon heeft ontvangen.*"
    And ik open een nieuw tabblad
    And ik haal de smscode van de eindgebruiker met bsn "304637828" op uit config op "t1" en vul deze in het sms-invoerveld in
    And ik klik op de knop "Volgende"
    Then kom ik op een scherm met de titel "DigiD: Activeren | Activeringscode"
    And kom ik op een scherm met de h1-titel "Activeren"
    And ik haal de activeringscode van de eindgebruiker met bsn "304637828" op uit config op "t1" en vul deze in het activeringscodeveld in
    And ik klik op de knop "Activeren"
    Then kom ik op een scherm met de titel "DigiD: Activeren | Bevestiging activering"
    And moet ik de melding "U kunt nu met uw DigiD inloggen op websites van de overheid." zien
    And moet ik de melding "U kunt dit venster sluiten." zien




#  @inloggen_digid_basis
#  Scenario: Inloggen bij Mijn DigiD
#  Given ik ben op "t1"
#  En ik navigeer naar "Mijn DigiD"
#  Dan kom ik op een scherm met de titel "DigiD: Inloggen | Inloggegevens"
#  Dan heeft het scherm de heading "Inloggen bij Mijn DigiD"
#  Als ik de radiobutton "Ik wil inloggen met gebruikersnaam en wachtwoord" aanvink
#  En ik voer mijn gebruikersnaam "<user>" in
#  En ik voer mijn wachtwoord "<password>" in
#  Als ik op de knop "Inloggen" klik
#  Dan kom ik op een scherm met de titel "Mijn DigiD"
#  Dan heeft het scherm de heading "Welkom bij Mijn DigiD"
#  Als ik navigeer naar "Gebruiksgeschiedenis"
#  Dan kom ik op een scherm met de titel "Mijn DigiD | Gebruiksgeschiedenis"
#  Dan heeft het scherm de heading "Gebruiksgeschiedenis"
#  Dan moet ik een logregel zien met de tekst "Inloggen met niveau basis (gebruikersnaam en wachtwoord) gelukt bij webdienst Mijn DigiD"
#  En ik klik op "Uitloggen"
#  Dan kom ik op een scherm met de titel "DigiD: Uitgelogd"
#  En moet ik de melding "U bent nu uitgelogd bij Mijn DigiD." zien
#
#  Voorbeelden:
#  |bsn|date|month|year|postcode|huisnummer|user|password|e-mail|telephone|
#  |304637828|25|04|1988|2563SB|21|Healthchecktest123|Pw_304637828|test@digid.nl|0656575859|