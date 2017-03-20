package Facedoor;

import Interfaccia.InterfacciaHomeNew;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;

/**
 * Classe che permette di inviare le richieste al server e di avviare l'interfaccia grafica per il dialogo con l'utente.
 * @author Marco Mandelli e Mirko Longhi
 *
 */

public class Client extends JFrame{
	private static final long serialVersionUID = 1L;
	//aggiunto commento al client
	//CAMPI
	//scaèopjihfsoiajsf
	static InterfacciaHomeNew home;
	protected static JFrame frameHome;
	public static InterfacciaServer server;
	
	
	//METODI
	public static void main(String[] args) throws Exception{

		java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	Registry reg = LocateRegistry.getRegistry(InterfacciaServer.PORT);
            		server = (InterfacciaServer) reg.lookup("server");
            		System.out.println("Connected! ");
            		
					new InterfacciaHomeNew().setVisible(true);
			
                } catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}

            }
        });
		
	}
	
	
	
	public static synchronized boolean inserimentoUtenti(InformazioneProfilo dati) throws RemoteException{
		return server.inserimentoUtenti(dati);
	}
	
	public static synchronized boolean invioMail(InformazioneProfilo dati) throws RemoteException{
		return server.invioMail(dati);
	}
	
	public static synchronized boolean remove(InformazioneProfilo dati, String info) throws RemoteException{
		return server.remove(dati, info);
	}
	
	public static synchronized ArrayList<String> viewPicture(String user_id) throws RemoteException{
		return server.viewPicture(user_id);
	}
	
	public static synchronized boolean addPicture(String utente, String path) throws RemoteException{
		return server.addPicture(utente, path);
	}
	
	public static synchronized void cronologia(String user_id, String operazione) throws RemoteException{
		server.cronologia(user_id, operazione);
	}
	
	public static synchronized InformazioneProfilo ricercaUtente(String user_id,String pass) throws RemoteException{
		return server.ricercaUtente(user_id, pass);
	}
	
	public static synchronized LinkedList<InformazioneProfilo> ricercaProfilo(String user_id,String cognome) throws RemoteException{
		return server.ricercaProfilo(user_id, cognome);
	}
	
	public static synchronized void cancellaProfilo(String user_Id) throws RemoteException{
		server.cancellaProfilo(user_Id);
	}
	
	public static synchronized void modificaProfilo(String user_id,InformazioneProfilo utente2) throws RemoteException{
		server.modificaProfilo(user_id, utente2);
	}
	
	public static synchronized void storeProfili(String user_id,String email,InformazioneProfilo utente) throws RemoteException{
		server.storeProfili(user_id, email,utente);
	}
	
	public static synchronized void searchUtentiCercati(String user_id,String email) throws RemoteException{
		server.searchUtentiCercati(user_id,email);
	}
	
	public static synchronized boolean modificaFoto(String utente, String path) throws RemoteException{
		return server.modificaFoto(utente, path);
	}
	
	

	

	

	


}
