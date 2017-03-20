package Facedoor;

import java.io.Serializable;
import java.sql.Date;

/**
 * Classe che permette di identificare un utente con tutti i suoi dati.
 * 
 * @author Marco Mandelli e Mirko Longhi
 *
 */

public class InformazioneProfilo implements Serializable{
	//CAMPI
	private static final long serialVersionUID = 1L;
	private String user_ID,nome,cognome,email,password,tipo,descrizione,residenza;
	private Date dataDiNascita;
	private String linkFoto;
	private String linkPdf;
	
	//COSTRUTTORE
	/**
	 * Costruttore che permette di creare un utente con le seguenti caratteristiche.
	 * @param nome nome utente
	 * @param cognome cognome utente
	 * @param user_ID nickname utente
	 * @param residenza residenza utente
	 * @param dataDiNascita data di nascita dell'utente
	 * @param email email dell'utente
	 * @param password password scelta dall'utente
	 * @param tipo tipo di utente facedoor scelto dall'utente
	 * @param descrizione stato dell'utente per il suo profilo
	 * @param linkFoto URL della foto profilo dell'utente
	 * @param linkPdf URL del Curriculum vitae
	 */
	public InformazioneProfilo(String nome, String cognome,String user_ID,String residenza,Date dataDiNascita,String email, String password, String tipo,String descrizione, String linkFoto,String linkPdf){
		this.nome = nome;
		this.cognome = cognome;
		this.user_ID = user_ID;
		this.email = email;
		this.password = password;
		this.tipo = tipo;
		this.descrizione = descrizione;
		this.linkFoto = linkFoto;
		this.linkPdf = linkPdf;
		this.dataDiNascita = dataDiNascita;
		this.residenza = residenza;
	}
		
	//METODI
	/**
	 * Metodo che specifica il nome dell'utente
	 * @return nome nome utente 
	 */
	public String getNome(){
		return nome;
	}
	
	/**
	 * Metodo che permette di settare nuovamente il nome dell'utente
	 * @param nome nome specificato per l'utente
	 */
	public void setNome(String nome){
		this.nome = nome;
	}
	
	/**
	 * Metodo che specifica il cognome dell'utente
	 * @return cognome cognome utente
	 */
	public String getCognome(){
		return cognome;
	}
	
	/**
	 * Metodo che permette di settare il cognome  dell'utente
	 * @param cognome cognome specificato per l'utente
	 */
	public void setCognome(String cognome){
		this.cognome = cognome;
	}
	
	/**
	 * Metodo che specifica l'user_id dell'utente
	 * @return user_id user_id dell'utente
	 */
	public String getUser_ID(){
		return user_ID;
	}
	
	/**
	 * Metodo che permette di settare l'user_id dell'utente
	 * @param user_ID user_id specificato per l'utente
	 */
	public void setUser_ID(String user_ID){
		this.user_ID = user_ID;
	}
	
	/**
	 * Metodo che specifica la mail dell'utente
	 * @return email email dell'utente 
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * Metodo che permette di settare la mail dell'utente
	 * @param email indirizzo email specificato per l'utente
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/**
	 * Metodo che specifica la password dell'utente
	 * @return password password dell'utente
	 */
	public String getPassword(){
		return password;
	}
	
	/**
	 * Metodo che permette di settare la password dell'utente
	 * @param password password specificato per l'utente
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * Metodo che specifica il tipo se pubblico o privato che l'utente ha scelto
	 * @return tipo tipo del profilo
	 */
	public String getTipo(){
		return tipo;
	}
	
	/**
	 * Metodo che permette di settare il tipo di utente
	 * @param tipo tipo specificato per l'utente
	 */
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	/**
	 * Metodo che specifica la descrizione personale dell'utente
	 * @return descrizione descrizione dell'utente
	 */
	public String getDescrizione(){
		return descrizione;
	}
	
	/**
	 * Metodo che permette di settare la descrizione personale dell'utente
	 * @param descrizione descrizione specificata per l'utente
	 */
	public void setDescrizione(String descrizione){
		this.descrizione = descrizione;
	}
	
	/**
	 * Metodo che specifica il link della foto scelta dall'utente
	 * @return linkFoto URL della foto inserita precedentemente
	 */
	public String getLinkFoto(){
		return linkFoto;
	}
	
	/**
	 * Metodo che permette di settare il link della foto dell'utente
	 * @param linkFoto URL della foto selezionata
	 */
	public void setLinkFoto(String linkFoto){
		this.linkFoto = linkFoto;
	}
	
	/**
	 * Metodo che specifica il link del pdf scelto dall'utente
	 * @return linkPdf URL del documento inserito precedentemente
	 */
	public String getLinkPdf(){
		return linkPdf;
	}
	
	/**
	 * Metodo che permette di settare il link del pdf scelto dall'utente
	 * @param linkPdf URL della foto selezionata
	 */
	public void setLinkPdf(String linkPdf){
		this.linkPdf = linkPdf;
	}
	
	/**
	 * Metodo che specifica la residenza dell'utente
	 * @return residenza residenza specificata precedentemente
	 */
	public String getResidenza(){
		return residenza;
	}
	
	/**
	 * Metodo che permette di settare la data di nascita dell'utente
	 * @return dataDiNascita data di nascita precedentemente 
	 */
	public Date getDataDiNascita(){
		return dataDiNascita;
	}
	
}

