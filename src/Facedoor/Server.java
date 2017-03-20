package Facedoor;


import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;
import Interfaccia.Email;


/**
 * Classe che gestisce le richieste effettuate dal client andando a dialogare anche con il db.
 * Sarà quindi un DB lato server!
 * 
 * @author Marco Mandelli e Mirko Longhi
 *
 */


public class Server extends UnicastRemoteObject implements InterfacciaServer,Serializable {
	//CAMPI
	private static final long serialVersionUID = 1L;
	private static String url = "jdbc:postgresql:facedoor";
	private static String user = "postgres";
	private static String password = "postgres";
	private static Connection connect;
	private static String email_facedoor = "facedoorMM@gmail.com";
	private static String password_facedoor = "facedoorservice01";
	
	//stringhe per la creazione del database
	private static String creazioneTab1="CREATE TABLE IF NOT EXISTS public.utente ( nome VARCHAR(50) NOT NULL,"
			+ "  cognome VARCHAR(50) NOT NULL, user_id VARCHAR(20) NOT NULL, residenza VARCHAR(100) NOT NULL,"
			+ "  data DATE NOT NULL, email VARCHAR(50) NOT NULL,  password VARCHAR(20) NOT NULL,"
			+ "  stato VARCHAR(10) NOT NULL, descrizione VARCHAR(500) NOT NULL, image VARCHAR(150) NOT NULL,"
			+ "  pdf VARCHAR(150) NOT NULL,  PRIMARY KEY (user_id));"; 
	private static String creazioneTab2="CREATE TABLE IF NOT EXISTS public.immagine ( user_id VARCHAR(20) NOT NULL,"
			+ "  nomeimg VARCHAR(150) NOT NULL, urlimg VARCHAR(150) NOT NULL, data VARCHAR(11) NOT NULL,"
			+ "  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.utente (user_id) ON DELETE CASCADE ON UPDATE CASCADE);";
	private static String creazioneTab3="CREATE TABLE IF NOT EXISTS public.cronologia (user_id VARCHAR(20) NOT NULL,"
			+ "  operazione VARCHAR(100) NOT NULL);";
	private static String creazioneTab4="CREATE TABLE IF NOT EXISTS public.profilicercati ( user_id VARCHAR(20) NOT NULL,"
			+ "email VARCHAR(50) NOT NULL,nome_cercato VARCHAR(15) NOT NULL, cognome_cercato VARCHAR(15) NOT NULL, user_id_cercato VARCHAR(20) NOT NULL,ora VARCHAR(10) NOT NULL,data VARCHAR(10) NOT NULL,"
			+ "  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES public.utente (user_id) ON DELETE CASCADE ON UPDATE"
			+ " NO ACTION);";
	
	public Server() throws SQLException, RemoteException{
		try{
			
			//STEP 0: Caricamento driver
			Class.forName("org.postgresql.Driver");
			
			//STEP 1: definizione della connessione al db
			System.out.println("-Apro la connessione con il db");
			connect = DriverManager.getConnection(url,user,password);

		}catch(ClassNotFoundException c){
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, c);
		}catch(SQLException s){
			System.out.println(s.getMessage());
			JOptionPane.showMessageDialog(null, "Connection to DB failed");
		}
	}
	
	public static void main(String[] args) throws Exception, SQLException{	
				
		try{
		
			Server server = new Server();
			System.out.println("Waiting a connection...");
			Registry reg = LocateRegistry.createRegistry(InterfacciaServer.PORT);
			reg.rebind("server", server);
			System.out.println("Started! Server ready... ");
					
									
			//STEP 2: creazione tabelle
			if(server.existTable()){
				System.out.println("-Tabelle gia esistenti!");
			}else {
				server.creazioneTabelle(connect);	
				System.out.println("-Creo le tabelle! ");
			}	
		}catch(RemoteException rem){
			System.out.println(rem.getMessage());
			System.exit(0);
		}	
	}
	//METODI GESTIONE UTENTI
	
	public void creazioneTabelle(Connection connect) throws SQLException, IOException{	
		Statement stm = connect.createStatement();
		stm.execute(creazioneTab1);
		stm.execute(creazioneTab2);
		stm.execute(creazioneTab3);
		stm.execute(creazioneTab4);
	
	}
	
	public boolean existTable() throws RemoteException, SQLException{
		String query= " SELECT * FROM information_schema.tables where table_schema ='public'";
		Connection connect = DriverManager.getConnection(url, user, password);;
		Statement stm = connect.createStatement();
		ResultSet rs = stm.executeQuery(query);
			
		if(rs.next() == true){
			return true;
		}else{
			return false;
		}
	}
	
	public synchronized boolean inserimentoUtenti(InformazioneProfilo dati) throws RemoteException{
		try{
			String sql ="INSERT INTO utente(nome,cognome,user_id,residenza,data,email,password,"
					+ "stato,descrizione,image,pdf) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = connect.prepareStatement(sql);
				pst.setString(1, dati.getNome());
				pst.setString(2, dati.getCognome());
				pst.setString(3, dati.getUser_ID());
				pst.setString(4, dati.getResidenza());
				pst.setDate(5, dati.getDataDiNascita());
				pst.setString(6, dati.getEmail());
				pst.setString(7, dati.getPassword());
				pst.setString(8, dati.getTipo());
				pst.setString(9, dati.getDescrizione());
				pst.setString(10, dati.getLinkFoto());
				pst.setString(11, dati.getLinkPdf());
				pst.executeUpdate();
				addPicture(dati.getUser_ID(), dati.getLinkFoto());
				
				return true;				
		}catch(SQLException s){
			System.out.println("Errore inserimento utente! " + s.getMessage());
			return false;
		}
	}
	
	public synchronized boolean invioMail(InformazioneProfilo dati) throws RemoteException{
		try{		
			Email.sendMail(dati.getEmail(), email_facedoor, password_facedoor, 
					"Welcome to FACEDOOR", "Ti sei iscritto a Facedoor!"
							+ " Benvenuto nella piattaforma facedoor."
							+ " Ecco i tuoi dati personali: \n"
							+ " Nome: " + dati.getNome() + "\n" 
							+ " Cognome: " + dati.getCognome() 
							+ "\n" + "User_ID: " + dati.getUser_ID() + "\n" 
							+ " Password: " + dati.getPassword() + 
							"\n" + "Ciao e buon divertimento dai fondatori di FACEDOOR! ");
			return true;
			
		}catch(AddressException ad){
			remove(dati,"Registazione non avvenuta!"); //rimuovo i dati relativi a quell'utente
			System.out.println(ad.getMessage());
			return false;
		}catch(SendFailedException send){
			remove(dati,"Registazione non avvenuta!");
			System.out.println(send.getMessage());
			return false;
		}catch(MessagingException msg){
			remove(dati, "Registrazione non avvenuta!");
			System.out.println(msg.getMessage());
			return false;
			}
	}
	
	public synchronized boolean remove(InformazioneProfilo dati, String info) throws RemoteException{
		try{		
			String sql = "DELETE FROM utente WHERE user_id = ?";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setString(1, dati.getUser_ID());
			pst.executeUpdate();
			return true;
		
		}catch(SQLException s){
			System.out.println(s.getMessage());
			s.printStackTrace();
			return false;
		}
	}
	
	public synchronized void cancellaProfilo(String user_id) throws RemoteException{
		try{
			String sql = "DELETE FROM utente WHERE user_id=?;";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setString(1,user_id);
			pst.executeUpdate();
			
		}catch(SQLException sq){
			System.out.println(sq.getMessage());
			sq.printStackTrace();
		}
	}
	
	public synchronized ArrayList<String> viewPicture(String user_id) throws RemoteException{
		ArrayList<String> listFoto = new ArrayList<>();
		try{
			String sql = "SELECT URLImg FROM immagine WHERE user_id =?";
			PreparedStatement pst = connect.prepareStatement(sql, 
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY,
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			
			pst.setString(1, user_id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				listFoto.add(rs.getString(1));
			}
			return listFoto;
		
		}catch(SQLException sq){
			System.out.println(sq.getMessage());
			sq.printStackTrace();
		}
		return listFoto;
	}
	
	public synchronized boolean addPicture(String utente, String path) throws RemoteException{
		try{
			String sql1="SELECT * FROM immagine WHERE user_id = ?";
			PreparedStatement pst = connect.prepareStatement(sql1,ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			
			pst.setString(1, utente);
			ResultSet rs = pst.executeQuery();
			
			int numFoto = 0; //contantore foto aggiunte
			
			while(rs.next()){
				numFoto++;
			}
			
			//ovviamente il numero di foto dovra essere non superiore a 5
			if(numFoto < 5){
				String sql2 ="INSERT INTO immagine(user_id,nomeimg,urlimg,data) VALUES (?,?,?,?)";
				PreparedStatement pst2 = connect.prepareStatement(sql2);
				String nomefoto = "foto" + numFoto;
				Calendar c = Calendar.getInstance();
				String dataOdierna = c.get(Calendar.YEAR) + "/" + c.get(Calendar.MONTH) 
				+ "/" + c.get(Calendar.DAY_OF_MONTH);
				pst2.setString(1, utente);
				pst2.setString(2, nomefoto);
				pst2.setString(3, path);
				pst2.setString(4, dataOdierna);
				pst2.executeUpdate();
		
				return true;
		}else{
				return false;
			}
			
		}catch(SQLException sq){
			System.out.println(sq.getMessage());
			sq.printStackTrace();
			return false;
		}
	}
	
	public synchronized void cronologia(String user_id, String operazione) throws RemoteException{
		try{
			String sql = "INSERT INTO Cronologia(user_id,operazione) VALUES (?,?)";
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setString(2, operazione);
			pst.executeUpdate();
			
		}catch(SQLException sq){
			System.out.println(sq.getMessage());
			sq.printStackTrace();
		}
	}
		
	public synchronized InformazioneProfilo ricercaUtente(String user_id,String pass) throws RemoteException{
		
		InformazioneProfilo utente = null;
		try{
			
			String sql ="SELECT * FROM utente WHERE user_id=? and password=?";
			
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setString(2, pass);
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()){
				
				JOptionPane.showMessageDialog(null, "Username and password are correct");
				System.out.println("Utente loggato: " + rs.getString(1) + " " +
				rs.getString(2) + " " + rs.getString(3)+ " " + rs.getString(6));
				utente = new InformazioneProfilo(rs.getString(1), rs.getString(2), 
				rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6),
				rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10),
				rs.getString(11));
				JOptionPane.showMessageDialog(null, "Login effettuato dall'utente: \n"
				+ "Nome: " + utente.getNome() + "\n" + "Cognome: " + utente.getCognome());
				
			}else{
				JOptionPane.showMessageDialog(null, "ERROR! Username and password aren't correct");
				return utente;
			}
		}catch(Exception e2){
			JOptionPane.showMessageDialog(null, e2);
		}
		return utente;
	}		
	
	public synchronized LinkedList<InformazioneProfilo> ricercaProfilo(String nome,String cognome) throws RemoteException{
		LinkedList<InformazioneProfilo> utenti = new LinkedList<>();
		
		try{
			String sql = "SELECT distinct * FROM utente WHERE "
					+ "(nome like ? AND cognome like ? ) AND stato ='Pubblico';";
			
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setString(1, nome);
			pst.setString(2, cognome);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()){
				utenti.add(new InformazioneProfilo(rs.getString(1),rs.getString(2),
				rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),
				rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)));
			}
			return utenti;
			
		}catch(SQLException sq){
			System.out.println(sq.getMessage());
		}
		
		return utenti;
	}
	
	public synchronized void modificaProfilo(String user_id,InformazioneProfilo utente2) throws RemoteException{
		try{
			String sql = "UPDATE utente SET nome=?,cognome=?,user_id=?,residenza=?,data=?,"
					+ "email=?,password=?,stato=?,descrizione=?,image=?,pdf=? WHERE user_id=?";
			
			PreparedStatement pst = connect.prepareStatement(sql);
			pst.setString(1, utente2.getNome());
			pst.setString(2, utente2.getCognome());
			pst.setString(3, utente2.getUser_ID());
			pst.setString(4, utente2.getResidenza());
			pst.setDate(5, utente2.getDataDiNascita());
			pst.setString(6, utente2.getEmail());
			pst.setString(7, utente2.getPassword());
			pst.setString(8, utente2.getTipo());
			pst.setString(9, utente2.getDescrizione());
			pst.setString(10, utente2.getLinkFoto());
			pst.setString(11, utente2.getLinkPdf());
			pst.setString(12, user_id);
			modificaFoto(user_id,utente2.getLinkFoto());
			pst.executeUpdate();
			
		}catch(SQLException sq){
			System.out.println(sq.getMessage());
		}
	}
	
	public synchronized boolean modificaFoto(String utente, String path) throws RemoteException{
		try{
			String sql1="SELECT * FROM immagine WHERE user_id = ?";
			PreparedStatement pst = connect.prepareStatement(sql1,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY, 
					ResultSet.HOLD_CURSORS_OVER_COMMIT);
			
			pst.setString(1, utente);
			
			//ovviamente il numero di foto dovra essere non superiore a 5
			//cambio solo la foto profilo
				String sql2 ="UPDATE immagine SET user_id=?,nomeimg=?,urlimg=?,data=?"
						+ " WHERE user_id=? and nomeimg='foto0';";
				PreparedStatement pst2 = connect.prepareStatement(sql2);
				Calendar c = Calendar.getInstance();
				String dataOdierna = c.get(Calendar.YEAR) 
						+ "/" + c.get(Calendar.MONTH) 
						+ "/" + c.get(Calendar.DAY_OF_MONTH);
				pst2.setString(1, utente);
				pst2.setString(2, "foto0");
				pst2.setString(3, path);
				pst2.setString(4, dataOdierna);
				pst2.setString(5, utente);
				pst2.executeUpdate();
		
				return true;
			
		}catch(SQLException sq){
			System.out.println(sq.getMessage());
			sq.printStackTrace();
			return false;
		}
	}	
	
	public synchronized void storeProfili(String user_id,String email, InformazioneProfilo utente) throws RemoteException{
		try{
		String sql = "INSERT INTO profiliCercati(user_id,email,nome_cercato,cognome_cercato,user_id_cercato,ora,data) "
				+ "VALUES (?,?,?,?,?,?,?);";
		PreparedStatement pst = connect.prepareStatement(sql);
		pst.setString(1, user_id);
		pst.setString(2, email);
		pst.setString(3, utente.getNome());
		pst.setString(4, utente.getCognome());
		pst.setString(5, utente.getUser_ID());
		
		
		Calendar c = Calendar.getInstance();
		String ora = c.get(Calendar.HOUR)  + ":" +c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
		pst.setString(6, ora);
		String data = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
		pst.setString(7, data);
		pst.executeUpdate();

		}catch(SQLException sq){
			System.out.println(sq.getMessage());
		}	
	}

	public synchronized void searchUtentiCercati(String user_id,String email) throws RemoteException{
		try{
		LinkedList<String> ricercati = new LinkedList<>();
		String sqlselect ="SELECT * FROM profiliCercati WHERE user_id=?";
		PreparedStatement pst2 = connect.prepareStatement(sqlselect);
		pst2.setString(1, user_id);
		ResultSet rs = pst2.executeQuery();
		while(rs.next()){
			ricercati.add(rs.getString(3) + " " 
					+ rs.getString(4) + " " 
					+ rs.getString(5) + " " 
					+ rs.getString(6) + " " 
					+ rs.getString(7) + "\n") ;
		}
		Email.sendMail(email, email_facedoor, password_facedoor, 
				"Utenti Ricercati", ricercati.toString());
		JOptionPane.showMessageDialog(null, "Controlla la tua mail! ");

		}catch(SQLException sq){
			System.out.println(sq.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}	
}

