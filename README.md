# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Ali Anjum, S358976, s358976@oslomet.no


# Oppgavebeskrivelse

I oppgave 1 gikk jeg frem ved å kopiere kildekode fra kompendiet Programkode 5.2.3 a. Imidlertid måtte 
kildekoden tilpasses ettersom vi bruker foreldre peker. Det løste jeg ved å sette til en foreldre peker hver gang en ny node ble opprettet. 

I oppgave 2 brukte jeg kildekode fra kompendiet Programkode 5.2.6 a). 
Ettersom vi vet at nodene blir lagt inn gjennom «oppskriften», altså at verdier som er høyere går mot venstre, 
mens verdier som er lavere går mot høyere. Denne kunnskapen kunne jeg bruke her. Måtte endre koden i 
antall ved at verdier som er lik eller høyere går mot venstre. På den måten fortsetter vi videre i treet når vi finner vår verdi høyt oppe i treet.

I oppgave 3 a brukte jeg kildekode fra kompendiet Programkode 5.1.7 h). Her er poenget å finne noden som er lengste nederst til venstre. 
For oppgave b fulgte jeg oppskriften om å finne nestepostorden:

•Hvis p ikke har en forelder ( p er rotnoden), så er p den siste i postorden.
•Hvis p er høyre barn til sin forelder f, er forelderen f den neste.
•Hvis p er venstre barn til sin forelder f, gjelder:
	oHvis p er enebarn (f.høyre er null), er forelderen f den neste.
	oHvis p ikke er enebarn (dvs. f.høyre er ikke null), så er den neste den noden som kommer først i postorden i subtreet med f.høyre som rot.

I oppgave 4 brukte jeg metodene fra oppgave 3. 
Startet først med å finne førstePostOrden -> skriv ut. Videre en while nøkkel som fant nestePostOrden og ->skriv ut.

I oppgave 5 a brukte jeg ArrayDeque fordi den har metoder som er nyttig. 
Brukte FIFO metoden. La inn først rot node. Inne i while nøkkelen fjernes en node og deres barn blir lagt inn, der jeg starter 
først med å legge inn deres venstre barn først. Den første ungen som ble lagt inn blir da først tatt ut, men da legges inn deres barn inn igjen.
Hver node som ble tatt ut av køen ble lagt inn i et array. I b oppgaven opprettet jeg et nytt tre. 
Den har legg inn metoden. La inn verdiene fra array gjennom en for løkke som itererte seg gjennom array og la det inn i treet.

I oppgave 6 brukte jeg kildekode fra kompendiet Programkode 5.2.8 d). Den måtte tilpasses ettersom vi bruker foreldrenode. 
Dette løste jeg ved å bruke tre pekere. En for foreldre, en for den noden som skal slettes og barnet til noden som skal slettes. 
Man sjekker først om noden som skal bli fjernet har barn eller ikke. Spesielt om den har 1 eller to barn. 
Her gjaldt det å sikre at barne node sin foreldrepeker ekte til sin besteforeldre. 
