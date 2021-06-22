# TicTacToe
TicTacToe assignment for RMDY

=== Hoe compileren en uitvoeren ===

In GitHub:
1. Navigeer naar de master branch
2. Klik op Code -> Download ZIP
3. Unzip de file naar een opslaglocatie

In Android Studio:
1. Klik op File -> Open... -> selecteer de unzipped folder -> OK
2. Klik op de groene startknop in de tweede bovenbalk, of druk Shift+F10
3. Android Studio zal voorstellen om een nieuwe emulator te instantieren, of een bestaande te gebruiken
4. Klik op OK
5. Wacht tot de emulator is opgestart en de applicatie is geladen


=== Werkwijze ===

GitHub:

Master branch: master

Main development branch: main_activity
- Alle feature branches zijn hierop gebaseerd. Commits ivm deze features werden op de overeenkomstige branch gemaakt. Wanneer alle commits voor een bepaalde feature voltooid zijn wordt de branch terug in main_activity gemerged.
- Commits voor code die niet rechtstreeks te maken heeft met een feature (algemene xml, activity lifecycle, code cleanup, ...) werden rechtstreeks op de main_activity branch gemaakt. Voor verbetering van de eindlayout werd wel een aparte branch aangemaakt.

Feature branches:
- onclick: On click functionaliteit van het 9x9 raster
- playerturn: logica voor het bepalen welke speler aan beurt is
- gameover: logica voor het bepalen wanneer het spel beeindigd is
- playagain: "Play again" button en de functionaliteit daarvan
- wincounter: logica voor het tellen van het aantal wins per speler
- layout-improvements: aanpassingen aan layout zoals het veranderen van font, text colors, background color, ...


Code:

Test Driven Development werden per feature in deze volgorde gecommit:
  1. Implementatie van UI-elementen in XML
  2. Schrijven van tests met behulp van de Espresso library voor het nabootsen van UI-acties -> tests falen nog
  3. Implementatie van businesslogica -> tests slagen nu
  4. Merge naar main branch (main_activity)

Libraries:

- Espresso: Krachtige tool die toestaat om met weinig code UI-acties na te bootsen tijdens tests
- Calligraphy: Custom fonts globaal instellen met weinig code
- Material design library: Standaard dependency
