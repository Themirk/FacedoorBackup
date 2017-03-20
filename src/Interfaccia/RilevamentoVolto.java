package Interfaccia;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RilevamentoVolto extends JFrame {
	
	//CAMPI
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RilevamentoVolto frame = new RilevamentoVolto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public RilevamentoVolto() {
		setTitle("Rilevamento volto");
		setBackground(Color.WHITE);
		setBounds(100, 100, 600, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel photoPanel = new JPanel();
		photoPanel.setBackground(new Color(204, 204, 255));
		photoPanel.setBounds(42, 31, 259, 172);
		contentPane.add(photoPanel);
		
		JLabel lblRilevoIlVolto = new JLabel("Rilevo il volto da fotocamera");
		photoPanel.add(lblRilevoIlVolto);
		
		JButton btnScatta = new JButton("Scatta");
		btnScatta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//apro fotocamera/webcam per rilevamento volto
			
			}
		});
		btnScatta.setBounds(375, 96, 117, 29);
		contentPane.add(btnScatta);
		
		JLabel lblRisultatiRicerca = new JLabel("Risultati Ricerca");
		lblRisultatiRicerca.setBounds(251, 243, 101, 16);
		contentPane.add(lblRisultatiRicerca);
		
		JLabel lblNomeUtente = new JLabel("Nome utente");
		lblNomeUtente.setBounds(42, 314, 94, 16);
		contentPane.add(lblNomeUtente);
		
		JLabel lblCognomeUtente = new JLabel("Cognome utente");
		lblCognomeUtente.setBounds(42, 357, 109, 16);
		contentPane.add(lblCognomeUtente);
		
		JLabel lblEmailUtente = new JLabel("Email utente");
		lblEmailUtente.setBounds(42, 418, 94, 16);
		contentPane.add(lblEmailUtente);
		
		JLabel lblUseridUtente = new JLabel("User_id utente");
		lblUseridUtente.setBounds(42, 472, 94, 16);
		contentPane.add(lblUseridUtente);
		
		
		//Il seguente pannello mi permetterà di visualizzare la foto profilo dell'utente cercato tramite rilevamento facciale
		JPanel photoPanel2 = new JPanel();
		photoPanel2.setBackground(new Color(204, 204, 255));
		photoPanel2.setBounds(375, 314, 180, 174);
		contentPane.add(photoPanel2);
		
		JLabel lblFotoProfiloUtente = new JLabel("Foto profilo utente");
		photoPanel2.add(lblFotoProfiloUtente);
		
	}
}
