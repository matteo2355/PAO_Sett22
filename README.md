# Programmazione ad oggetti
# Temperature App
## INTRODUZIONE
La mia Applicazione SpringBoot permette di visionare le informazioni metereologiche attuali di qualunque città nel mondo in qualsiami momento. Inoltre permette di calcolare e filtrare statistiche sui dati storici riguardanti la temperatura di 5 città (Milano, Bologna, Ancona, Cagliari, Lecce) e mettere queste statistiche di diverse città a confronto tra loro.

## CONFIGURAZIONE
Per accedere al servizio, è necessario ottenere un API key. Si può ottenere gratuitamente autenticandosi sul sito di [OpenWeather](https://openweathermap.org/). Dopo averla ottenuta, è necessario modificarla nella sezione [ServiceImplementation.java](https://github.com/matteo2355/PAO_Sett22/blob/main/temperature-app/src/main/java/com/progetto/temperatureapp/service/ServiceImplementation.java).

## UML
### *Use Case Diagram*
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/Use%20Case%20diagram.jpg)

#### Class Diagram
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/Class%20diagram.jpg)

#### TemperatureApp.controller
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/Controller.jpg)

#### TemperatureApp.model
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/models.jpg)

#### TemperatureApp.service
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/service.jpg)

#### TemperatureApp.statistics
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/utils%20(1).jpg)

#### TemperatureApp.exceptions
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/exceptions.jpg)

### *Sequence Diagram*
![](https://github.com/matteo2355/PAO_Sett22/blob/main/UML_PAO/sequence.jpg)

## ROTTE
Le richieste devono essere effettuate all'indirizzo 
```
localhost:8080
```
tramite POSTMAN. Bisognerà avviare il programma come applicazone Springboot. Va fatta attenzione al tipo di richiesta: ne abbiamo 2 tip in questo caso, GET e POST.

|N°    |TIPO         | ROTTA                                    | SPIEGAZIONE |
|-----|--------     |--------                                   |--------                               |
|  1  | ``` GET ``` |  ``` /weather?cityName=Ancona```         |restituisce un HttpEntity contenente le informazioni sulle attuali condizioni climatiche (temperatura, temperatura percepita, pressione, umidità...)della città passata in ingresso.|
|  2  | ``` GET ``` |  ``` /saveEveryHour?cityName=Bologna ``` |restituisce il path in cui è stato salvato il file contenente le informazioni attuali relative alla condizioni climatiche aggiornate ogni ora.|
|  3  | ``` POST ```|  ``` /stats ``` |restituisce un HttpEntity contenente le statistiche sui parametri indicati in ingresso della città passata in ingresso.|
|  4  | ``` POST ```|  ``` /filters ``` |restituisce un HttpEntity contentente le statistiche filtrare secondo l'informazione che si vuole richiedere (anche di piu città). |

## AUTORE
|NOME|EMAIL|CONTRIBUTO|
|--------|--------|----|
|  Matteo Angelini | s1094976@studenti.univpm.it |100%|

