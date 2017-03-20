package Interfaccia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Facedoor.Client;
import Facedoor.InformazioneProfilo;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Insets;
import javax.swing.JTextField;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;

/**
 * Classe che permette di visualizzare il profilo di un utente, mostrando tutti i campi relativi a un utente e con la possibilità di cancellare e modificare il profilo.
 * @author Marco Mandelli e Mirko Longhi
 */

public class Profilo extends JFrame {

	//CAMPI
	private static final long serialVersionUID = 1L;
	protected JFrame frameProfilo;
	protected JPanel contentPane;
	protected static JTextField textField_Nome;
	protected static JTextField textField_Cognome;
	public static JTextField textField_ID;
	protected static JTextField textField_Email;
	protected static JTextField textField_Curriculum;
	private JPanel panel;
	private JPanel panel_2;
	private JButton btnEsci;
	private JButton btnModificaProfilo;
	private JButton btnCancellaProfilo;
	protected static JEditorPane editorPane_Descrizione;
	private JLabel lblLaTuaDescrizione;
	protected static JRadioButton rdbtnPrivato;
	protected static JRadioButton rdbtnPubblico;
	protected static JLabel lblFoto;
	private JButton btnRicerca;
	ButtonGroup gruppoBottoni = new ButtonGroup();
	InformazioneProfilo dati;
	Profilo profilo;
	protected static JTextField textField_foto;
	protected static JTextField textField_password;
	private JButton btnConfermaModifica;
	private JButton btnCurriculum;
	private JButton btnFoto;
	private JButton btnAggiungiAltro;
	private final FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Filtro file", "pdf");
	private final FileNameExtensionFilter photoFilter = new FileNameExtensionFilter("Filtro immagine", "jpeg", "jpg");
	ProprietaFoto viewFoto = new ProprietaFoto();
	String linkfoto;
	String linkFile;
	private JButton btnAggiungiFoto;
	LinkedList<String> listaFoto;
	protected static JTextField textField_Residenza;
	protected static JDateChooser data;
	private JTextField searchName;
	private JTextField searchSurname;
	LinkedList<InformazioneProfilo> listautenti = new LinkedList<>();
	private JPanel panel_1;
	private Ricercatore ricerca;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profilo frame = new Profilo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Profilo() {
		frameProfilo = new JFrame();
		frameProfilo.setForeground(SystemColor.textHighlight);
		frameProfilo.setTitle("Profilo Facedoor");
		frameProfilo.setBounds(100, 100, 600, 700);
		frameProfilo.getContentPane().setBackground(new Color(230, 230, 250));
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 30, 85, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 31, 35, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		frameProfilo.getContentPane().setLayout(gbl_contentPane);
		
		panel_1 = new JPanel();
		panel_1.setToolTipText("");
		panel_1.setBackground(new Color(0, 0, 128));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.gridwidth = 11;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		frameProfilo.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{141, 110, 0, 134, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{36, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblProfilo = new JLabel("PROFILO");
		GridBagConstraints gbc_lblProfilo = new GridBagConstraints();
		gbc_lblProfilo.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblProfilo.insets = new Insets(0, 0, 0, 5);
		gbc_lblProfilo.gridx = 0;
		gbc_lblProfilo.gridy = 0;
		panel_1.add(lblProfilo, gbc_lblProfilo);
		lblProfilo.setForeground(Color.WHITE);
		lblProfilo.setFont(new Font("Savoye LET", Font.BOLD | Font.ITALIC, 30));
		lblProfilo.setBackground(SystemColor.text);
		
		btnRicerca = new JButton("Ricerca");
		btnRicerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cerca un utente
				String nome = searchName.getText();
				String cognome = searchSurname.getText();
				
				if(!nome.equals("") && !cognome.equals("")){
					
					try{
						
						listautenti = Client.ricercaProfilo(nome, cognome);
					
						//utente.server.Cronologia(Profilo.textField_ID.getText(), "Ricerca profilo");
						
						if(!listautenti.isEmpty()){
							for(InformazioneProfilo ut : listautenti){
								//mi apre tot finestre per quanti sono il numero di persone con lo stesso nome e cognome
								ricerca = new Ricercatore();

								String linkfoto = ut.getLinkFoto();
								ImageIcon image = ProprietaFoto.ResizeImage(linkfoto, 100, 100);
								
								Ricercatore.lblNome.setText(ut.getNome());
								Ricercatore.lblCognome.setText(ut.getCognome());
								Ricercatore.lblEmail.setText(ut.getEmail());
								Ricercatore.lblUserid.setText(ut.getUser_ID());
								Ricercatore.label_foto.setIcon(image);
								Client.storeProfili(textField_ID.getText(),textField_Email.getText(), ut);
								ricerca.setVisible(true);
								
								searchName.setText("");
								searchSurname.setText("");
							}
							
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
		
		searchName = new JTextField();
		GridBagConstraints gbc_searchName = new GridBagConstraints();
		gbc_searchName.gridwidth = 2;
		gbc_searchName.insets = new Insets(0, 0, 0, 5);
		gbc_searchName.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchName.gridx = 1;
		gbc_searchName.gridy = 0;
		panel_1.add(searchName, gbc_searchName);
		searchName.setColumns(10);
		
		searchSurname = new JTextField();
		GridBagConstraints gbc_searchSurname = new GridBagConstraints();
		gbc_searchSurname.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchSurname.insets = new Insets(0, 0, 0, 5);
		gbc_searchSurname.gridx = 3;
		gbc_searchSurname.gridy = 0;
		panel_1.add(searchSurname, gbc_searchSurname);
		searchSurname.setColumns(10);
		
		GridBagConstraints gbc_btnRicerca = new GridBagConstraints();
		gbc_btnRicerca.insets = new Insets(0, 0, 0, 5);
		gbc_btnRicerca.gridx = 6;
		gbc_btnRicerca.gridy = 0;
		panel_1.add(btnRicerca, gbc_btnRicerca);
		
		JLabel lblDatiUtente = new JLabel("Dati Utente:");
		GridBagConstraints gbc_lblDatiUtente = new GridBagConstraints();
		gbc_lblDatiUtente.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatiUtente.gridx = 2;
		gbc_lblDatiUtente.gridy = 2;
		frameProfilo.getContentPane().add(lblDatiUtente, gbc_lblDatiUtente);
		
		textField_Nome = new JTextField();
		textField_Nome.setEnabled(false);
		GridBagConstraints gbc_textField_Nome = new GridBagConstraints();
		gbc_textField_Nome.gridwidth = 2;
		gbc_textField_Nome.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Nome.gridx = 2;
		gbc_textField_Nome.gridy = 3;
		frameProfilo.getContentPane().add(textField_Nome, gbc_textField_Nome);
		textField_Nome.setColumns(10);
		
		textField_Residenza = new JTextField();
		textField_Residenza.setEnabled(false);
		textField_Residenza.setEditable(false);
		GridBagConstraints gbc_textField_Residenza = new GridBagConstraints();
		gbc_textField_Residenza.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Residenza.gridwidth = 2;
		gbc_textField_Residenza.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Residenza.gridx = 4;
		gbc_textField_Residenza.gridy = 3;
		frameProfilo.getContentPane().add(textField_Residenza, gbc_textField_Residenza);
		textField_Residenza.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.gridheight = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 6;
		gbc_panel.gridy = 3;
		frameProfilo.getContentPane().add(panel, gbc_panel);
		
		lblFoto = new JLabel("");
		panel.add(lblFoto);
		
		textField_Cognome = new JTextField();
		textField_Cognome.setEnabled(false);
		GridBagConstraints gbc_textField_Cognome = new GridBagConstraints();
		gbc_textField_Cognome.gridwidth = 2;
		gbc_textField_Cognome.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Cognome.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Cognome.gridx = 2;
		gbc_textField_Cognome.gridy = 4;
		frameProfilo.getContentPane().add(textField_Cognome, gbc_textField_Cognome);
		textField_Cognome.setColumns(10);
		
		data = new JDateChooser();
		data.setEnabled(false);
		GridBagConstraints gbc_data = new GridBagConstraints();
		gbc_data.gridwidth = 2;
		gbc_data.insets = new Insets(0, 0, 5, 5);
		gbc_data.fill = GridBagConstraints.BOTH;
		gbc_data.gridx = 4;
		gbc_data.gridy = 4;
		frameProfilo.getContentPane().add(data, gbc_data);
		
		textField_ID = new JTextField();
		textField_ID.setEnabled(false);
		GridBagConstraints gbc_textField_ID = new GridBagConstraints();
		gbc_textField_ID.gridwidth = 2;
		gbc_textField_ID.insets = new Insets(0, 0, 5, 5);
		gbc_textField_ID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_ID.gridx = 2;
		gbc_textField_ID.gridy = 5;
		frameProfilo.getContentPane().add(textField_ID, gbc_textField_ID);
		textField_ID.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setEnabled(false);
		GridBagConstraints gbc_textField_Email = new GridBagConstraints();
		gbc_textField_Email.gridwidth = 2;
		gbc_textField_Email.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Email.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Email.gridx = 2;
		gbc_textField_Email.gridy = 6;
		frameProfilo.getContentPane().add(textField_Email, gbc_textField_Email);
		textField_Email.setColumns(10);
		
		btnCurriculum = new JButton("Curriculum");
		btnCurriculum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Curriculum.setText("");
				addFile();
				try {
					Desktop desktop = null;
					if (Desktop.isDesktopSupported()) {
						desktop = Desktop.getDesktop();
					}

					desktop.open(new File(textField_Curriculum.getText()));
					
				} catch (IOException ioe) {
						ioe.printStackTrace();
				
				} catch (IllegalArgumentException n){
						JOptionPane.showMessageDialog(null,"Il file non esiste!","errore", JOptionPane.WARNING_MESSAGE);
						return;
					}
			}
		});
		
		textField_Curriculum = new JTextField();
		textField_Curriculum.setEnabled(false);
		GridBagConstraints gbc_textField_Curriculum = new GridBagConstraints();
		gbc_textField_Curriculum.gridwidth = 2;
		gbc_textField_Curriculum.insets = new Insets(0, 0, 5, 5);
		gbc_textField_Curriculum.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_Curriculum.gridx = 2;
		gbc_textField_Curriculum.gridy = 7;
		frameProfilo.getContentPane().add(textField_Curriculum, gbc_textField_Curriculum);
		textField_Curriculum.setColumns(10);
		btnCurriculum.setEnabled(false);
		GridBagConstraints gbc_btnCurriculum = new GridBagConstraints();
		gbc_btnCurriculum.gridwidth = 2;
		gbc_btnCurriculum.insets = new Insets(0, 0, 5, 5);
		gbc_btnCurriculum.gridx = 4;
		gbc_btnCurriculum.gridy = 7;
		frameProfilo.getContentPane().add(btnCurriculum, gbc_btnCurriculum);
		
		btnFoto = new JButton("Foto");
		btnFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_foto.setText("");
				addFoto();
			}
		});
		
		textField_foto = new JTextField();
		textField_foto.setEnabled(false);
		GridBagConstraints gbc_textField_foto = new GridBagConstraints();
		gbc_textField_foto.gridwidth = 2;
		gbc_textField_foto.insets = new Insets(0, 0, 5, 5);
		gbc_textField_foto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_foto.gridx = 2;
		gbc_textField_foto.gridy = 8;
		frameProfilo.getContentPane().add(textField_foto, gbc_textField_foto);
		textField_foto.setColumns(10);
		btnFoto.setEnabled(false);
		GridBagConstraints gbc_btnFoto = new GridBagConstraints();
		gbc_btnFoto.gridwidth = 2;
		gbc_btnFoto.insets = new Insets(0, 0, 5, 5);
		gbc_btnFoto.gridx = 4;
		gbc_btnFoto.gridy = 8;
		frameProfilo.getContentPane().add(btnFoto, gbc_btnFoto);
		
		btnAggiungiFoto = new JButton("Visualizza foto");
		btnAggiungiFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FotoGallery visual = new FotoGallery();
				visual.frameVisual.setVisible(true);
			}
		});
		
		textField_password = new JTextField();
		textField_password.setEnabled(false);
		GridBagConstraints gbc_textField_password = new GridBagConstraints();
		gbc_textField_password.gridwidth = 2;
		gbc_textField_password.insets = new Insets(0, 0, 5, 5);
		gbc_textField_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_password.gridx = 2;
		gbc_textField_password.gridy = 9;
		frameProfilo.getContentPane().add(textField_password, gbc_textField_password);
		textField_password.setColumns(10);
		GridBagConstraints gbc_btnAggiungiFoto = new GridBagConstraints();
		gbc_btnAggiungiFoto.gridwidth = 2;
		gbc_btnAggiungiFoto.insets = new Insets(0, 0, 5, 5);
		gbc_btnAggiungiFoto.gridx = 4;
		gbc_btnAggiungiFoto.gridy = 9;
		frameProfilo.getContentPane().add(btnAggiungiFoto, gbc_btnAggiungiFoto);
		
		btnAggiungiAltro = new JButton("Aggiungi Altro");
		btnAggiungiAltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addAltreFoto();
			}
		});
		GridBagConstraints gbc_btnAggiungiAltro = new GridBagConstraints();
		gbc_btnAggiungiAltro.insets = new Insets(0, 0, 5, 5);
		gbc_btnAggiungiAltro.gridx = 8;
		gbc_btnAggiungiAltro.gridy = 9;
		frameProfilo.getContentPane().add(btnAggiungiAltro, gbc_btnAggiungiAltro);
		
		rdbtnPrivato = new JRadioButton("Privato");
		rdbtnPrivato.setEnabled(false);
		GridBagConstraints gbc_rdbtnPrivato = new GridBagConstraints();
		gbc_rdbtnPrivato.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPrivato.gridx = 2;
		gbc_rdbtnPrivato.gridy = 10;
		frameProfilo.getContentPane().add(rdbtnPrivato, gbc_rdbtnPrivato);
		gruppoBottoni.add(rdbtnPrivato);
		
		rdbtnPubblico = new JRadioButton("Pubblico");
		rdbtnPubblico.setEnabled(false);
		GridBagConstraints gbc_rdbtnPubblico = new GridBagConstraints();
		gbc_rdbtnPubblico.gridwidth = 2;
		gbc_rdbtnPubblico.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPubblico.gridx = 4;
		gbc_rdbtnPubblico.gridy = 10;
		frameProfilo.getContentPane().add(rdbtnPubblico, gbc_rdbtnPubblico);
		gruppoBottoni.add(rdbtnPubblico);
		
		lblLaTuaDescrizione = new JLabel("La tua descrizione:");
		GridBagConstraints gbc_lblLaTuaDescrizione = new GridBagConstraints();
		gbc_lblLaTuaDescrizione.insets = new Insets(0, 0, 5, 5);
		gbc_lblLaTuaDescrizione.gridx = 2;
		gbc_lblLaTuaDescrizione.gridy = 11;
		frameProfilo.getContentPane().add(lblLaTuaDescrizione, gbc_lblLaTuaDescrizione);
		
		editorPane_Descrizione = new JEditorPane();
		editorPane_Descrizione.setEditable(false);
		editorPane_Descrizione.setEnabled(false);
		editorPane_Descrizione.setBackground(Color.WHITE);
		GridBagConstraints gbc_editorPane_Descrizione = new GridBagConstraints();
		gbc_editorPane_Descrizione.gridwidth = 7;
		gbc_editorPane_Descrizione.gridheight = 2;
		gbc_editorPane_Descrizione.insets = new Insets(0, 0, 5, 0);
		gbc_editorPane_Descrizione.fill = GridBagConstraints.BOTH;
		gbc_editorPane_Descrizione.gridx = 4;
		gbc_editorPane_Descrizione.gridy = 11;
		frameProfilo.getContentPane().add(editorPane_Descrizione, gbc_editorPane_Descrizione);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 128));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 2;
		gbc_panel_2.gridwidth = 11;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 13;
		frameProfilo.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{103, 411, 99};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0};
		panel_2.setLayout(gbl_panel_2);
		
		btnEsci = new JButton("Esci");
		btnEsci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameProfilo.setVisible(false);
			}
		});
		
		btnModificaProfilo = new JButton("Modifica profilo");
		GridBagConstraints gbc_btnModificaProfilo = new GridBagConstraints();
		gbc_btnModificaProfilo.insets = new Insets(0, 0, 5, 5);
		gbc_btnModificaProfilo.gridx = 1;
		gbc_btnModificaProfilo.gridy = 1;
		panel_2.add(btnModificaProfilo, gbc_btnModificaProfilo);
		btnModificaProfilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					textField_Nome.setEnabled(true);
					textField_Cognome.setEnabled(true);
					textField_Email.setEnabled(true);
					data.setEnabled(true);
					textField_Residenza.setEditable(true);
					textField_Residenza.setEnabled(true);
					editorPane_Descrizione.setEditable(true);
					editorPane_Descrizione.setEnabled(true);
					textField_Curriculum.setEnabled(true);
					rdbtnPrivato.setEnabled(true);
					rdbtnPubblico.setEnabled(true);
					textField_password.setEnabled(true);
					textField_foto.setEnabled(true);
					btnConfermaModifica.setEnabled(true);
					btnCurriculum.setEnabled(true);
					btnFoto.setEnabled(true);
			}
		});
		
		btnConfermaModifica = new JButton("Conferma Modifica");
		btnConfermaModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//UTENTE PRIVATO
				if(!textField_Nome.getText().equals("") && !textField_Cognome.getText().equals("") 
						&& !textField_Curriculum.getText().equals("") 
						&& !editorPane_Descrizione.getText().equals("")
						&& !textField_password.getText().equals("") 
						&& !textField_foto.getText().equals("") 
						&& rdbtnPrivato.isSelected() 
						&& !data.getDate().equals(null) 
						&& !textField_Residenza.getText().equals("")){
					
						Date sqldata = new Date(data.getDate().getTime());
						
						InformazioneProfilo newprofilo;
						newprofilo = new InformazioneProfilo(textField_Nome.getText(),
								textField_Cognome.getText(), textField_ID.getText(),
								textField_Residenza.getText(),sqldata ,textField_Email.getText(), 
								textField_password.getText(), rdbtnPrivato.getText(), 
								editorPane_Descrizione.getText(), textField_foto.getText(),
								textField_Curriculum.getText());
					
							Client.modificaProfilo(textField_ID.getText(),newprofilo);
							Client.cronologia(textField_ID.getText(), "Modifica profilo");
							JOptionPane.showMessageDialog(null, " MODIFICA EFFETTUATA CON SUCCESSO ");
                    
							String linkfoto = newprofilo.getLinkFoto();
							ImageIcon img = ProprietaFoto.ResizeImage(linkfoto, 130, 130);
							lblFoto.setIcon(img);
						
							textField_Nome.setEnabled(false);
							textField_Cognome.setEnabled(false);
							textField_Email.setEnabled(false);
							data.setEnabled(false);
							textField_Residenza.setEditable(false);
							textField_Residenza.setEnabled(false);
							editorPane_Descrizione.setEditable(false);
							editorPane_Descrizione.setEnabled(false);
							textField_Curriculum.setEnabled(false);
							rdbtnPrivato.setEnabled(false);
							rdbtnPubblico.setEnabled(false);
							textField_password.setEnabled(false);
							textField_foto.setEnabled(false);
							btnConfermaModifica.setEnabled(false);
							btnCurriculum.setEnabled(false);
							btnFoto.setEnabled(false);
				}
				//UTENTE PUBBLICO
				else if(!textField_Nome.getText().equals("") 
						&& !textField_Cognome.getText().equals("") 
						&& !textField_Curriculum.getText().equals("") 
						&& !editorPane_Descrizione.getText().equals("")
						&& !textField_password.getText().equals("") 
						&& !textField_foto.getText().equals("") 
						&& rdbtnPubblico.isSelected() 
						&& !data.getDate().equals(null) 
						&& !textField_Residenza.getText().equals("")){
					
						Date sqldata = new Date(data.getDate().getTime());
					
						InformazioneProfilo newprofilo;
						newprofilo = new InformazioneProfilo(textField_Nome.getText(), textField_Cognome.getText(), textField_ID.getText(), textField_Residenza.getText(),sqldata , textField_Email.getText(), textField_password.getText(), rdbtnPubblico.getText(), editorPane_Descrizione.getText(), textField_foto.getText(), textField_Curriculum.getText());
						
							Client.modificaProfilo(textField_ID.getText(),newprofilo);
							Client.cronologia(textField_ID.getText(), "Modifica profilo");
						
							JOptionPane.showMessageDialog(null, " MODIFICA EFFETTUATA CON SUCCESSO ");
                    
							String linkfoto = newprofilo.getLinkFoto();
							ImageIcon img = ProprietaFoto.ResizeImage(linkfoto, 130, 130);
							lblFoto.setIcon(img);
						
							textField_Nome.setEnabled(false);
							textField_Cognome.setEnabled(false);
							textField_Email.setEnabled(false);
							data.setEnabled(false);
							textField_Residenza.setEditable(false);
							textField_Residenza.setEnabled(false);
							editorPane_Descrizione.setEditable(false);
							editorPane_Descrizione.setEnabled(false);
							textField_Curriculum.setEnabled(false);
							rdbtnPrivato.setEnabled(false);
							rdbtnPubblico.setEnabled(false);
							textField_password.setEnabled(false);
							textField_foto.setEnabled(false);
							btnConfermaModifica.setEnabled(false);
							btnCurriculum.setEnabled(false);
							btnFoto.setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "Impossibile effettuare la modifica del profilo controllare i campi! ");
				}
				}catch(RemoteException e1){
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Modifica fallita!");
				}
			}
		});
		GridBagConstraints gbc_btnConfermaModifica = new GridBagConstraints();
		gbc_btnConfermaModifica.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfermaModifica.gridx = 1;
		gbc_btnConfermaModifica.gridy = 2;
		panel_2.add(btnConfermaModifica, gbc_btnConfermaModifica);
		
		btnCancellaProfilo = new JButton("Cancella profilo");
		GridBagConstraints gbc_btnCancellaProfilo = new GridBagConstraints();
		gbc_btnCancellaProfilo.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancellaProfilo.gridx = 1;
		gbc_btnCancellaProfilo.gridy = 3;
		panel_2.add(btnCancellaProfilo, gbc_btnCancellaProfilo);
		btnCancellaProfilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client.cronologia(textField_ID.getText(), "Cancellazione profilo");

					Client.cancellaProfilo(textField_ID.getText());
					dati = null;
					
					JOptionPane.showMessageDialog(null, "Cancellazione avvenuta con successo!");
					frameProfilo.setVisible(false);
					
				} catch (RemoteException e1) {
					System.out.println(e1.getMessage());
					JOptionPane.showMessageDialog(null, "Cancellazione fallita!");
				}
			}
		});
		GridBagConstraints gbc_btnEsci = new GridBagConstraints();
		gbc_btnEsci.gridx = 2;
		gbc_btnEsci.gridy = 4;
		panel_2.add(btnEsci, gbc_btnEsci);
	}
	//***METODI***
	/**
	 * Metodo che permette di aggiungere un file pdf durante la modifica del profilo.
	 */
	
	private void addFile(){
		try{
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(fileFilter);
			int opt = chooser.showSaveDialog(this);

			if(opt == JFileChooser.APPROVE_OPTION){
				linkFile = chooser.getSelectedFile().getAbsolutePath();
				textField_Curriculum.setText(linkFile);
				JOptionPane.showMessageDialog(null, "Curriculum inserito correttamente!");
				Client.cronologia(textField_ID.getText(), "Inserimento Curriculum!");
			}else{
				JOptionPane.showMessageDialog(null, "Errore nel caricamento del Curriculum!");
			}
		}catch(RemoteException rm){
			System.out.println(rm.getMessage());
			rm.printStackTrace();
		}
	}
	
	/**
	 * Metodo che permette di aggiungere una foto all'utente durante la modifica del proprio profilo.
	 */
	private void addFoto(){
		viewFoto.resetImage();
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(photoFilter);
		chooser.setAccessory(viewFoto);
		int opt = chooser.showSaveDialog(this);
		if(opt == JFileChooser.APPROVE_OPTION){
			linkfoto = chooser.getSelectedFile().getPath();
			textField_foto.setText(linkfoto);
			ImageIcon img = ProprietaFoto.ResizeImage(linkfoto, 130, 130);
			lblFoto.setIcon(img);				
		}
	}
	/**
	 * Metodo che permette di aggiungere altre foto al profilo dell'utente.
	 */
	private void addAltreFoto(){
		try{
			viewFoto.resetImage();
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(photoFilter);
			chooser.setAccessory(viewFoto);
			int opt = chooser.showSaveDialog(this);
			
			if(opt == JFileChooser.APPROVE_OPTION){
				linkfoto = chooser.getSelectedFile().getPath();
				
				if(Client.addPicture(textField_ID.getText(), linkfoto)){
					JOptionPane.showMessageDialog(null, "Foto inserita correttamente!");
					Client.cronologia(textField_ID.getText(),"Inserimento foto!");
				}else{
					JOptionPane.showMessageDialog(null, "Foto non inserita! Foto gallery piena");
					btnAggiungiAltro.setEnabled(false);
				}
			}
			
		}catch(RemoteException rm){
			System.out.println(rm.getMessage());
			rm.printStackTrace();
		}
	}	
}
