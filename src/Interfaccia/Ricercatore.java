package Interfaccia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Facedoor.Client;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

/**
 * Classe che visualizza i campi relativi all'utente cercato.
 * @author Marco Mandelli e Mirko Longhi
 */

public class Ricercatore extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel frameRicercatore;
	protected JLabel lblUtenteRicercato;
	protected static JLabel lblNome;
	protected static JLabel lblCognome;
	protected static JPanel panel_foto;
	protected static JLabel label_foto;
	protected static JLabel lblEmail;
	protected static JLabel lblUserid;
	protected JButton btnRicercheEffettuate;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ricercatore frame = new Ricercatore();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ricercatore() {
		frameRicercatore = new JPanel();
		setBackground(Color.WHITE);
		frameRicercatore.setForeground(SystemColor.textHighlight);
		setBounds(100, 100, 600, 386);
		//frameRicercatore.setTitle("Facedoor");
		frameRicercatore.setBorder(new EmptyBorder(5, 5, 5, 5));
		frameRicercatore.setBackground(new Color(230, 230, 250));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
		frameRicercatore.setLayout(gridBagLayout);
		setContentPane(frameRicercatore);

		lblUtenteRicercato = new JLabel("Utente Ricercato");
		GridBagConstraints gbc_lblUtenteRicercato = new GridBagConstraints();
		gbc_lblUtenteRicercato.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUtenteRicercato.insets = new Insets(0, 0, 5, 5);
		gbc_lblUtenteRicercato.gridx = 5;
		gbc_lblUtenteRicercato.gridy = 3;
		frameRicercatore.add(lblUtenteRicercato, gbc_lblUtenteRicercato);
		
		lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 2;
		gbc_lblNome.gridy = 5;
		frameRicercatore.add(lblNome, gbc_lblNome);
		
		panel_foto = new JPanel();
		panel_foto.setBackground(new Color(230, 230, 250));
		GridBagConstraints gbc_panel_foto = new GridBagConstraints();
		gbc_panel_foto.gridwidth = 3;
		gbc_panel_foto.gridheight = 7;
		gbc_panel_foto.insets = new Insets(0, 0, 5, 5);
		gbc_panel_foto.fill = GridBagConstraints.BOTH;
		gbc_panel_foto.gridx = 6;
		gbc_panel_foto.gridy = 5;
		frameRicercatore.add(panel_foto, gbc_panel_foto);
		
		label_foto = new JLabel("");
		panel_foto.add(label_foto);
		
		lblCognome = new JLabel("Cognome");
		GridBagConstraints gbc_lblCognome = new GridBagConstraints();
		gbc_lblCognome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCognome.insets = new Insets(0, 0, 5, 5);
		gbc_lblCognome.gridx = 2;
		gbc_lblCognome.gridy = 6;
		frameRicercatore.add(lblCognome, gbc_lblCognome);
		
		lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 7;
		frameRicercatore.add(lblEmail, gbc_lblEmail);
		
		btnRicercheEffettuate = new JButton("Ricerche effettuate");
		btnRicercheEffettuate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Client.searchUtentiCercati(Profilo.textField_ID.getText(),Profilo.textField_Email.getText());	
					
					
				} catch (RemoteException | NullPointerException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		GridBagConstraints gbc_btnRicercheEffettuate = new GridBagConstraints();
		gbc_btnRicercheEffettuate.insets = new Insets(0, 0, 5, 5);
		gbc_btnRicercheEffettuate.gridx = 6;
		gbc_btnRicercheEffettuate.gridy = 13;
		frameRicercatore.add(btnRicercheEffettuate, gbc_btnRicercheEffettuate);
		
		lblUserid = new JLabel("User_id");
		GridBagConstraints gbc_lblUserid = new GridBagConstraints();
		gbc_lblUserid.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblUserid.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserid.gridx = 2;
		gbc_lblUserid.gridy = 8;
		frameRicercatore.add(lblUserid, gbc_lblUserid);	
	}
}
