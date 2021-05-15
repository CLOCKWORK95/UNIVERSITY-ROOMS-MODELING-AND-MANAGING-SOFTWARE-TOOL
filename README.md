# UNIVERSITY-ROOMS-MODELING-AND-MANAGING-SOFTWARE-TOOL

L’architettura del software è basata sul pattern architetturale BCE (Boundary-Control-Entity), 
moderna evoluzione del tipico pattern architetturale MVC. Secondo BCE è opportuno mantenere 
strettamente separati blocchi di software destinati a funzionalità di Boundary (confine utente-macchina), di Controllo (corpo centrale/logica del codice), e di modellizzazione, come le entità. Questa 
separazione netta è cruciale, e la comunicazione tra le sezioni di codice avviene tramite delle classi 
messaggere, quali i Java “Beans”. 
L’utilità di questa architettura risiede nella estrema praticità di gestione: nel caso in questione, è stato 
davvero semplice il passaggio da applicazione laptop ad applicazione web, in quanto si è dovuto metter 
mano esclusivamente alla sezione di Boundary dell’applicativo, lasciando il resto invariato.

▪ Il Software è stato integralmente sviluppato su Eclipse Photon.

▪ Per ciò che concerne il lato utente, si è modellata un’interfaccia grafica (GUI) che è stato possibile 
connettere intuitivamente all’applicativo utilizzando la piattaforma SceneBuilder per generare file 
FXML (interfaccia di funzionamento per l’applicazione laptop).

▪ Per la parte di persistenza, implementata nelle classi DAO dell’applicativo, si è utilizzato il DBMS 
ad oggetti PostgreSQL, con connessione tramite il driver di Java, JDBC, orientata ai database 
relazionali
