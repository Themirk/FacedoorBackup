package Interfaccia;


import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.xswingx.PromptSupport;
import Facedoor.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultButtonModel;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JEditorPane;

/**
 * Classe che raffigura il client che permetterà all'utente di dialogare con l'applicazione, permettendogli di registrarsi, loggarsi e cercare un utente pubblico registrato in facedoor.
 * La procedura di ricerca di un utente potrà esser fatta anche da utente non registrato a facedoor!
 * 
 * @author Marco Mandelli e Mirko Longhi
 *
 */
public class InterfacciaHomeNew extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected static JTextField usernameLoginField;
	protected JPasswordField passwordLoginField;
	private JTextField nameRegistrationField;
	private JTextField surnameRegistrationField;
	private JTextField usernameRegistrationField;
	private JTextField photoPathField;
	private JTextField cvPathField;
	private JTextField mailRegistrationField;
	private JPasswordField passwordRegistrationField;
	private JTextField nameSearchField;
	private JTextField surnameSearchField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JFileChooser fileChooser = new JFileChooser();
	private final FileNameExtensionFilter photoFilter = new FileNameExtensionFilter("Filtro immagine", "jpeg", "jpg");
	private final FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Filtro file", "pdf");
	private JTextField residenceRegistrationField;
	private JLabel labelFoto;
	private JRadioButton publicButton,privateButton;
	private JDateChooser dateRegistration;
	private JEditorPane descriptionRegistrationField;
	private Profilo profilo;
	private ProprietaFoto viewFoto = new ProprietaFoto();
	private String linkfoto;
	private String linkFile;
	private LinkedList<InformazioneProfilo> listaUtenti = new LinkedList<>();
	private RicercatoreNonRegistrato ricerca;
	private RilevamentoVolto rileva;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaHomeNew frame = new InterfacciaHomeNew();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws RemoteException errore sollevato in caso di comunicazione interrotta
	 * @throws NotBoundException errore
	 */
	public InterfacciaHomeNew() throws RemoteException, NotBoundException {
		setTitle("Facedoor");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//INVOCO PER OGNI INTERFACCIA UN OGGETTO REMOTO CHE SI COLLEGA AL SERVER E INVIA LE RICHIESTE DELL'INTERFACCIA PRESA IN CONSIDERAZIONE
		
		JLabel welcomeLabel = new JLabel("Benvenuto su Facedoor");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		welcomeLabel.setBounds(168, 27, 268, 16);
		contentPane.add(welcomeLabel);
		
		//JFileChooser Filters
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		fileChooser.setFileFilter(filter);
		
		//componenti necessarie per la sezione LOGIN
		//LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN LOGIN 
		
		JLabel loginLabel = new JLabel("Sei già dei nostri?");
		loginLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setBounds(40, 180, 521, 16);
		contentPane.add(loginLabel);
		
		usernameLoginField = new JTextField();
		usernameLoginField.setBounds(40, 208, 134, 26);
		contentPane.add(usernameLoginField);
		usernameLoginField.setColumns(10);
		PromptSupport.init("Username", getForeground(), getBackground(), usernameLoginField);
		
		passwordLoginField = new JPasswordField();
		passwordLoginField.setBounds(225, 208, 134, 26);
		contentPane.add(passwordLoginField);
		PromptSupport.init("Password", getForeground(), getForeground(), passwordLoginField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {

					if(!usernameLoginField.getText().equals("") && !passwordLoginField.getText().equals("")){
						InformazioneProfilo p = Client.ricercaUtente(usernameLoginField.getText(), passwordLoginField.getText());
						//se non ci sono utenti e quindi il valore e' null l'operazione di log errata
						if(p != null){
							Client.cronologia(usernameLoginField.getText(), "LOGIN");
							
							profilo = new Profilo();
							profilo.frameProfilo.setVisible(true);
							//frameLogin.setVisible(false);
							setCampiProfilo(p);	
					}else{
						System.out.println("Operazione di log errata!");
					}
					
					}else{
						JOptionPane.showMessageDialog(null, "Inserisci i campi per loggarti! ");
					}
					resetLogin();

				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} 
			}
			
		});
		loginButton.setBounds(422, 208, 117, 26);
		contentPane.add(loginButton);
		
		//FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN FINE LOGIN 
		
		//componenti necessarie alla sezione di REGISTRAZIONE
		//REGISTRAZIONE REGISTRAZIONE REGISTRAZIONE REGISTRAZIONE REGISTRAZIONE REGISTRAZIONE REGISTRAZIONE REGISTRAZIONE REGISTRAZIONE 
		
		final JButton registrationButton = new JButton("Registration");
		registrationButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				boolean mail = Email.checkEmail(mailRegistrationField.getText());
				
				if(privateButton.isSelected()){
				if(!nameRegistrationField.getText().equals("") && !surnameRegistrationField.getText().equals("") && !mailRegistrationField.getText().equals("")
					&& !photoPathField.getText().equals("") && !cvPathField.getText().equals("") && !dateRegistration.getDate().equals(null) && !residenceRegistrationField.getText().equals("")){
					try{
						//controllo se il nome della mail è corretta
					if(mail){
						
						//Inserimento Utente Privato
						System.out.println("Email corretta");
						Date sqldata = new Date(dateRegistration.getDate().getTime());
						InformazioneProfilo utente = new InformazioneProfilo(nameRegistrationField.getText(),surnameRegistrationField.getText(),usernameRegistrationField.getText(),
								residenceRegistrationField.getText(),sqldata,mailRegistrationField.getText(),passwordRegistrationField.getText(),privateButton.getText(),descriptionRegistrationField.getText(),
								photoPathField.getText(),cvPathField.getText());
						
						boolean conferma = Client.inserimentoUtenti(utente);
					
						if(conferma){
						
								boolean invioEmail = Client.invioMail(utente);

								if(invioEmail){
								JOptionPane.showMessageDialog(null, "Registrazione effettuata con successo!");
								resetRegistration();								
							}else{
								JOptionPane.showMessageDialog(null, "Indirizzo Email non valido! ");
							}
						}else{
							JOptionPane.showMessageDialog(null, "**ERRORE** \n User_id gia esistente! Inserire un altro user!");
						}
						
		
					}else{
						JOptionPane.showMessageDialog(null, "Email non corretta!");
						}
					} catch (RemoteException e1) {

						e1.printStackTrace();
					}				
				}
					}else if(publicButton.isSelected()){
						if(!nameRegistrationField.getText().equals("") && !surnameRegistrationField.getText().equals("") && !mailRegistrationField.getText().equals("")
								&& !usernameRegistrationField.getText().equals("") && !passwordRegistrationField.getText().equals("") && !descriptionRegistrationField.getText().equals("")
								&& !photoPathField.getText().equals("") && !cvPathField.getText().equals("") && !dateRegistration.getDate().equals(null) && !residenceRegistrationField.getText().equals("") ){
							try {	
							if(mail){
							
								
								//Inserimento Utente Pubblico
								System.out.println("Email corretta");
								Date sqldata = new Date(dateRegistration.getDate().getTime());

								InformazioneProfilo utente = new InformazioneProfilo(nameRegistrationField.getText(),surnameRegistrationField.getText(),usernameRegistrationField.getText(),
										residenceRegistrationField.getText(),sqldata,mailRegistrationField.getText(),passwordRegistrationField.getText(),publicButton.getText(),descriptionRegistrationField.getText(),
										photoPathField.getText(),cvPathField.getText());
									
								boolean conferma = Client.inserimentoUtenti(utente);
								
								if(conferma){
								
										boolean invioEmail = Client.invioMail(utente);
										
										if(invioEmail){
											JOptionPane.showMessageDialog(null, "Registrazione effettuata con successo!");
											resetRegistration();
										
									}else{
										JOptionPane.showMessageDialog(null, "Indirizzo Email non valido! ");
									}
								}else{
									JOptionPane.showMessageDialog(null, "**ERRORE** \n User_id gia esistente! Inserire un altro user!");
								}
							
							}else{
								JOptionPane.showMessageDialog(null, "Indirizzo Email non valido!");
							}
								
							} catch (RemoteException e1) {

									JOptionPane.showMessageDialog(null, e1.getMessage());
							}
								
						
						}else{
							JOptionPane.showMessageDialog(null, "Campi non compilati correttamente");
						}
							
						
						}else{
								JOptionPane.showMessageDialog(null,"Campi non compilati correttamente");
							}
					
					
			
			}
		});
		registrationButton.setBounds(444, 578, 117, 66);
		contentPane.add(registrationButton);
		
		JLabel registrationLabel = new JLabel("Nuovo su Facedoor?  ");
		registrationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registrationLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		registrationLabel.setBounds(40, 274, 521, 16);
		contentPane.add(registrationLabel);
		
		nameRegistrationField = new JTextField();
		nameRegistrationField.setBounds(40, 302, 156, 26);
		contentPane.add(nameRegistrationField);
		nameRegistrationField.setColumns(10);
		PromptSupport.init("Name", getForeground(), getForeground(), nameRegistrationField);
		
		surnameRegistrationField = new JTextField();
		surnameRegistrationField.setBounds(40, 338, 156, 26);
		contentPane.add(surnameRegistrationField);
		surnameRegistrationField.setColumns(10);
		PromptSupport.init("Surname", getForeground(), getForeground(), surnameRegistrationField);
		
		usernameRegistrationField = new JTextField();
		usernameRegistrationField.setBounds(40, 414, 156, 26);
		contentPane.add(usernameRegistrationField);
		usernameRegistrationField.setColumns(10);
		PromptSupport.init("Username", getForeground(), getForeground(), usernameRegistrationField);
		
		mailRegistrationField = new JTextField();
		mailRegistrationField.setBounds(40, 376, 156, 26);
		contentPane.add(mailRegistrationField);
		mailRegistrationField.setColumns(10);
		PromptSupport.init("E-mail", getForeground(), getForeground(), mailRegistrationField);
		
		passwordRegistrationField = new JPasswordField();
		passwordRegistrationField.setBounds(40, 494, 156, 26);
		contentPane.add(passwordRegistrationField);
		PromptSupport.init("Password", getForeground(), getForeground(), passwordRegistrationField);
		
		residenceRegistrationField = new JTextField();
		residenceRegistrationField.setBounds(40, 454, 156, 28);
		contentPane.add(residenceRegistrationField);
		residenceRegistrationField.setColumns(10);
		PromptSupport.init("Residence", getForeground(), getBackground(), residenceRegistrationField);
		
		descriptionRegistrationField = new JEditorPane();
		descriptionRegistrationField.setBounds(422, 448, 152, 72);
		contentPane.add(descriptionRegistrationField);

		
		photoPathField = new JTextField();
		photoPathField.setBounds(40, 567, 156, 26);
		contentPane.add(photoPathField);
		photoPathField.setColumns(10);
		
		cvPathField = new JTextField();
		cvPathField.setBounds(40, 623, 156, 26);
		contentPane.add(cvPathField);
		cvPathField.setColumns(10);
		
		final JButton photoSearchButton = new JButton("Scegli...");
		photoSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				addFoto();
			}
		});
		
		photoSearchButton.setBounds(225, 567, 117, 29);
		contentPane.add(photoSearchButton);
		
		
		JButton cvSearchButton = new JButton("Scegli...");
		cvSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFile();
				try {
					Desktop desktop = null;
					if (Desktop.isDesktopSupported()) {
						desktop = Desktop.getDesktop();
					}

					desktop.open(new File(cvPathField.getText()));
					
				} catch (IOException ioe) {
						ioe.printStackTrace();
				
				} catch (IllegalArgumentException n){
						JOptionPane.showMessageDialog(null,"Il file non esiste!","errore", JOptionPane.WARNING_MESSAGE);
						return;
					}
			}
		});
		cvSearchButton.setBounds(225, 623, 117, 29);
		contentPane.add(cvSearchButton);
		
		JLabel typeProfileLabel = new JLabel("Tipo di profilo");
		typeProfileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		typeProfileLabel.setBounds(241, 312, 127, 16);
		contentPane.add(typeProfileLabel);
		
		publicButton = new JRadioButton("Pubblico");
		buttonGroup.add(publicButton);
		publicButton.setBounds(208, 339, 96, 23);
		contentPane.add(publicButton);
		
		privateButton = new JRadioButton("Privato");
		buttonGroup.add(privateButton);
		privateButton.setBounds(316, 339, 88, 23);
		contentPane.add(privateButton);
		
		JLabel selectPhotoLabel = new JLabel("Seleziona una foto profilo");
		selectPhotoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectPhotoLabel.setBounds(104, 549, 186, 16);
		contentPane.add(selectPhotoLabel);
		
		JLabel selectCvLabel = new JLabel("Seleziona il tuo curriculum vitae");
		selectCvLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectCvLabel.setBounds(78, 605, 223, 16);
		contentPane.add(selectCvLabel);
		
		JLabel descriptionLabel = new JLabel("Descrizione");
		descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		descriptionLabel.setBounds(316, 460, 96, 16);
		contentPane.add(descriptionLabel);
		
		//FINE REGISTRAZIONE FINE REGISTRAZIONE FINE REGISTRAZIONE FINE REGISTRAZIONE FINE REGISTRAZIONE FINE REGISTRAZIONE FINE REGISTRAZIONE 
		
		
		//componenti necessarie per la sezione di ricerca
		//RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA RICERCA 
		JLabel searchLabel = new JLabel("Stai cercando qualcuno? inserisci qui il suo nome o il suo cognome");
		searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		searchLabel.setBounds(40, 83, 521, 16);
		contentPane.add(searchLabel);
		
		nameSearchField = new JTextField();
		nameSearchField.setColumns(10);
		nameSearchField.setBounds(40, 111, 134, 26);
		contentPane.add(nameSearchField);
		PromptSupport.init("Name", getForeground(), getBackground(), nameSearchField);
		
		
		surnameSearchField = new JTextField();
		surnameSearchField.setColumns(10);
		surnameSearchField.setBounds(225, 111, 134, 26);
		contentPane.add(surnameSearchField);
		PromptSupport.init("Surname", getForeground(), getBackground(), surnameSearchField);
		
		JButton searchButton = new JButton("Cerca");
		searchButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				//cerca un utente
				String nome = nameSearchField.getText();
				String cognome = surnameSearchField.getText();
				
				if(!nome.equals("") && !cognome.equals("")){
					
					try{
						
						listaUtenti = Client.ricercaProfilo(nome,cognome);
					
						//utente.server.Cronologia(Profilo.textField_ID.getText(), "Ricerca profilo");
						
						if(!listaUtenti.isEmpty()){
							for(InformazioneProfilo ut : listaUtenti){
								ricerca = new RicercatoreNonRegistrato();

								String linkfoto = ut.getLinkFoto();
								ImageIcon image = ProprietaFoto.ResizeImage(linkfoto, 100, 100);
								
								ricerca.lblNome.setText(ut.getNome());
								ricerca.lblCognome.setText(ut.getCognome());
								ricerca.lblEmail.setText(ut.getEmail());
								ricerca.lblUserid.setText(ut.getUser_ID());
								ricerca.label_foto.setIcon(image);
								
								//Client.storeProfili("Non registrato", ut);
								ricerca.setVisible(true);

								
							}
							resetSearch();
						}else{
							 JOptionPane.showMessageDialog(null, "“Spiacente! La persona ricercata non è presente nei nostri archivi. Riprova fra qualche settimana.\n "
							 		+ "Puoi ricercare un nuovo profilo o creare il tuo profilo personale registrandoti a FACEDOOR qui.");
						}
						
					}catch(RemoteException rm){
						System.out.println(rm.getMessage());
		                JOptionPane.showMessageDialog(null, "Connection Failed", "ERROR", ERROR);

					}
				}
			}
		});
		searchButton.setBounds(371, 111, 110, 26);
		contentPane.add(searchButton);
		//fine componenti necessarie per la sezione di ricerca
		//FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA FINE RICERCA 
		
		
		//pannelli separatori
		
		JPanel divisore1 = new JPanel();
		divisore1.setBackground(new Color(0, 0, 128));
		divisore1.setBounds(0, 152, 600, 16);
		contentPane.add(divisore1);
		
		JPanel divisore2 = new JPanel();
		divisore2.setBackground(new Color(0, 0, 128));
		divisore2.setBounds(0, 246, 600, 16);
		contentPane.add(divisore2);
		
		JPanel divisore3 = new JPanel();
		divisore3.setBackground(new Color(0, 0, 128));
		divisore3.setBounds(0, 55, 600, 16);
		contentPane.add(divisore3);
		
		JPanel divisore4 = new JPanel();
		divisore4.setBackground(new Color(0, 0, 128));
		divisore4.setBounds(0, -1, 600, 16);
		contentPane.add(divisore4);
		
		JPanel divisore5 = new JPanel();
		divisore5.setBackground(new Color(0, 0, 128));
		divisore5.setBounds(0, 662, 600, 16);
		contentPane.add(divisore5);
		
		JPanel photoPanel = new JPanel();
		photoPanel.setBackground(new Color(204, 204, 255));
		photoPanel.setBounds(426, 280, 148, 122);
		contentPane.add(photoPanel);
		
		labelFoto = new JLabel("");
		photoPanel.add(labelFoto);
		
		
		dateRegistration = new JDateChooser();
		dateRegistration.setBounds(241, 374, 127, 28);
		contentPane.add(dateRegistration);	
		
		JButton btnCercaConFoto = new JButton("Cerca con foto");
		btnCercaConFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rileva = new RilevamentoVolto();
				rileva.setVisible(true);
			}
		});
		btnCercaConFoto.setBounds(472, 111, 122, 29);
		contentPane.add(btnCercaConFoto);
	}
	
	//****METODI***** 
	/**
	 * Metodo che permette di resettare i campi relativi a login!
	 */
	private void resetLogin(){
		usernameLoginField.setText("");
		passwordLoginField.setText("");
	}
	
	/**
	 * Metodo che permette di resettare i campi relativi alla ricerca di un utente!
	 */
	private void resetSearch(){
		nameSearchField.setText("");
		surnameSearchField.setText("");
	}
	
	/**
	 * Metodo che permette di resettare i campi relativi alla registrazione!
	 */
	private void resetRegistration(){
		nameRegistrationField.setText("");
		surnameRegistrationField.setText("");
		usernameRegistrationField.setText("");
		descriptionRegistrationField.setText("");
		residenceRegistrationField.setText("");
		dateRegistration.setDate(null);
		buttonGroup.setSelected(new DefaultButtonModel(), true);
		buttonGroup.clearSelection();
		labelFoto.setText("");
		labelFoto.setIcon(null);
		mailRegistrationField.setText("");
		passwordRegistrationField.setText("");
		photoPathField.setText("");
		cvPathField.setText("");	
	}
	
	/**
	 * Metodo che permette di settare i campi del profilo utente che ha appena effettuato il login.
	 * @param ut utente che effettua il login
	 */
	public void setCampiProfilo(InformazioneProfilo ut){
		
		String linkFoto = ut.getLinkFoto();
		ImageIcon image = ProprietaFoto.ResizeImage(linkFoto, 150, 150);
		Profilo.lblFoto.setIcon(image);
		Profilo.textField_Nome.setText(ut.getNome());
		Profilo.textField_Cognome.setText(ut.getCognome());
		Profilo.textField_ID.setText(ut.getUser_ID());
		Profilo.textField_Residenza.setText(ut.getResidenza());
		Profilo.data.setDate(ut.getDataDiNascita());
		Profilo.textField_Curriculum.setText(ut.getLinkPdf());
		Profilo.textField_foto.setText(ut.getLinkFoto());
		Profilo.textField_Email.setText(ut.getEmail());
		Profilo.editorPane_Descrizione.setText(ut.getDescrizione());
		Profilo.textField_password.setText(ut.getPassword());
		
		if(ut.getTipo().equals("Privato")){
			Profilo.rdbtnPrivato.setSelected(true);
		}else{
			Profilo.rdbtnPubblico.setSelected(true);
		}	
	}

	/**
	 * Metodo che permette di aggiungere una foto profilo associandola all'utente che sta effettuando la registrazione
	 */
	private void addFoto(){
		viewFoto.resetImage();
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(photoFilter);
		chooser.setAccessory(viewFoto);
		int opt = chooser.showSaveDialog(this);
		if(opt == JFileChooser.APPROVE_OPTION){
			linkfoto = chooser.getSelectedFile().getPath();
			photoPathField.setText(linkfoto);
			ImageIcon img = ProprietaFoto.ResizeImage(linkfoto, 130, 130);
			labelFoto.setIcon(img);
		}
	}

	/**
	 * Metodo che permette di aggiungere un file pdf associandolo all'utente che sta effettuando la registrazione
	 */
	
	private void addFile(){
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(fileFilter);
			int opt = chooser.showSaveDialog(this);
			if(opt == JFileChooser.APPROVE_OPTION){	
				linkFile = chooser.getSelectedFile().getAbsolutePath();
				cvPathField.setText(linkFile);
				JOptionPane.showMessageDialog(null, "Curriculum inserito correttamente!");	
			}else{
				JOptionPane.showMessageDialog(null, "Errore nel caricamento del Curriculum!");
			}
	}	
}

