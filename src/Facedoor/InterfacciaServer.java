package Facedoor;

import java.io.IOException;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Questa interfaccia permette di definire i metodi per la gestione del database e le funzioni relative al funzionamento di Faceboor
 * 
 * @author Marco Mandelli e Mirko Longhi
 *
 */

public interface InterfacciaServer extends Remote{

	//CAMPI
	public final int PORT = 9999;
	
	//METODI
	/**
	 * Metodo che permettte di creare le tabelle nel db
	 * @param connect connessione ad uno specifico db sulla quale sono eseguiti SQLStatement
	 * @throws SQLException errore sql (database)
	 * @throws IOException errore
	 */
	public void creazioneTabelle(Connection connect) throws SQLException, IOException;
	
	/**
	 * Metodo che permette di verificare se le tabelle sono gia presenti nel db
	 * @return boolean restituisce {@code true} se le tabelle sono gia presenti altrimenti false;
	 * @throws RemoteException errore connessione
	 * @throws SQLException errore sql (database)
	 */
	public boolean existTable() throws RemoteException,SQLException;

	
	/**
	 * Metodo che permette l'inserimento di utenti all'interno del db
	 * @param dati informazioni relative all'utente
	 * @return boolean restituisce true se l'utente e' stato inserito correttamente altrimenti false;
	 * @throws RemoteException errore connessione
	 */
	public boolean inserimentoUtenti(InformazioneProfilo dati) throws RemoteException;
	
	/**
	 * Metodo che permette di inviare un mail dal MailServer automaticamente quando utente viene registrato
	 * @param dati informazioni relative all'utente che effettua la registrazione
	 * @return boolean restituisce true se la registrazione e' avvenuta correttamente altrimenti false;
	 * @throws RemoteException errore connessione
	 */
	public boolean invioMail(InformazioneProfilo dati) throws RemoteException;
	
	/**
	 * Metodo che permette di cancellare un utente nel momento in cui la registrazione non andasse a buon fine
	 * @param dati informazioni relative all'utente 
	 * @param info informazioni relative alla rimozione
	 * @return boolean restituisce true se la cancellazione e' terminata con successo, altrimento false; 
	 * @throws RemoteException errore connessione
	 */
	public boolean remove(InformazioneProfilo dati, String info) throws RemoteException;
	
	/**
	 * Metodo che permette di visualizzare un immagine inserita da uno specifico utente tramite user_id
	 * @param user_id informazione identificativa di un utente per la visualizzazione della sua immagine di profilo
	 * @return ArrayList ritorna la lista di immagini
	 * @throws RemoteException errore connessione
	 */
	public ArrayList<String> viewPicture(String user_id) throws RemoteException;
	
	/**
	 * Metodo che permette di aggiungere un immagine dal profilo dell'utente loggato
	 * @param utente stringa che identifica l'utente nel suo profilo
	 * @param path stringa identificativa dell'immagine
	 * @return boolean restituisce true se l'immagine e' stata inserita correttamente, altrimenti false.
	 * @throws RemoteException errore connessione
	 */
	public boolean addPicture(String utente, String path) throws RemoteException;
	
	/**
	 * Metodo che permette di registrare le operazione effettuate da un utente
	 * @param user_id stringa che identifica l'utente
	 * @param operazione stringa che specifica l'operazione svolta dall'utente
	 * @throws RemoteException errore connessione
	 */
	public void cronologia(String user_id, String operazione) throws RemoteException;
	
	/**
	 * Metodo che permette all'utente di accedere al proprio profilo. Verra effetuata un ricerca dell'utente per controllare se e' presente in Facedoor
	 * @param user_id elemento identificativo dell'utente
	 * @param pass	elemento significativo per l'accesso al profilo 
	 * @return InformationProfilo ritornera un utente se esso e' iscritto a facedoor altrimenti errore.
	 * @throws RemoteException errore connessione
	 */
	public InformazioneProfilo ricercaUtente(String user_id,String pass) throws RemoteException;
	
	/**
	 * Metodo che permette sia a un utente registrato che non di cercare un utente pubblico registrato a facedoor
	 * @param user_id elemento identificativo dell'utente
	 * @param cognome elemento identificativo dell'utente
	 * @return LinkedList ritorna la lista dei dati inerenti all'utente cercato
	 * @throws RemoteException errore connessione
	 */
	public LinkedList<InformazioneProfilo> ricercaProfilo(String user_id,String cognome) throws RemoteException;
	
	/**
	 * Metodo che permette all'utente di cancellare il proprio profilo
	 * @param user_Id elemento identificativo dell'utente
	 * @throws RemoteException errore connessione
	 */
	public void cancellaProfilo(String user_Id) throws RemoteException;
	
	/**
	 * Metodo che permette all'utente di modificare il proprio profilo. E' possibile modificare tutti i campi tranne l'elemento identificativo cioe user_id
	 * @param user_id elemento identificativo dell'utente
	 * @param utente2 aggiornamento dati utente
	 * @throws RemoteException errore connessione
	 */
	public void modificaProfilo(String user_id,InformazioneProfilo utente2) throws RemoteException;
	
	/**
	 * Metodo che permette di memorizzare gli utenti cercati da uno specifico utente
	 * @param user_id elemento identificativo dell'utente
	 * @param email email dell'utente che cerca
	 * @param utente dati relativi all'utente cercato
	 * @throws RemoteException errore connessione
	 */
	public void storeProfili(String user_id,String email,InformazioneProfilo utente) throws RemoteException;
	
	/**
	 * Metodo che permette di trovare tutti gli utenti cercati da un specifico utente
	 * @param user_id elemento identificativo dell'utente
	 * @param email email dell'utente che cerca
	 * @throws RemoteException errore connessione
	 */
	public void searchUtentiCercati(String user_id,String email) throws RemoteException;
	
	/**
	 * Metodo che permette di modificare la foto nel database immagini quando un utente effettua la modifica del campo desiderato
	 * @param utente cliente che vuole modificare la propria immagine di profilo
	 * @param path immaginare da modificare
	 * @return true se la modifica dei campi dell'immagine è avvenuta con successo, altrimenti false.
	 * @throws RemoteException errore connessione
	 */
	public boolean modificaFoto(String utente, String path) throws RemoteException;


}
