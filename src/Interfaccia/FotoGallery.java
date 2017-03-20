package Interfaccia;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Facedoor.Client;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Classe che permette di visualizzare le foto che un utente possiede. Un utente potrà avere al massimo 5 foto!
 * 
 * @author Marco Mandelli e Mirko Longhi
 *
 */

public class FotoGallery extends JFrame {

	private static final long serialVersionUID = 6316203361375028862L;
	JFrame frameVisual;
	JLabel lblFoto1,lblFoto2,lblFoto3,lblFoto4,lblFoto5;

	ArrayList<String> listaFoto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FotoGallery frame = new FotoGallery();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FotoGallery() {
		frameVisual = new JFrame();
		frameVisual.setForeground(SystemColor.textHighlight);
		frameVisual.setTitle("Facedoor");
		frameVisual.setBounds(100, 100, 600, 700);
		frameVisual.getContentPane().setBackground(Color.WHITE);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		frameVisual.getContentPane().setLayout(gridBagLayout_1);
		
		try {
			listaFoto = Client.viewPicture(Profilo.textField_ID.getText());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		JLabel TestoFotoProfilo = new JLabel("Foto Profilo");
		GridBagConstraints gbc_TestoFotoProfilo = new GridBagConstraints();
		gbc_TestoFotoProfilo.insets = new Insets(0, 0, 5, 5);
		gbc_TestoFotoProfilo.gridx = 1;
		gbc_TestoFotoProfilo.gridy = 0;
		frameVisual.getContentPane().add(TestoFotoProfilo, gbc_TestoFotoProfilo);
		
		JLabel testoFoto1 = new JLabel("Foto 1");
		GridBagConstraints gbc_testoFoto1 = new GridBagConstraints();
		gbc_testoFoto1.insets = new Insets(0, 0, 5, 5);
		gbc_testoFoto1.gridx = 6;
		gbc_testoFoto1.gridy = 0;
		frameVisual.getContentPane().add(testoFoto1, gbc_testoFoto1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		frameVisual.getContentPane().add(panel_1, gbc_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setForeground(Color.BLACK);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.gridheight = 4;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 6;
		gbc_panel_2.gridy = 1;
		frameVisual.getContentPane().add(panel_2, gbc_panel_2);
		
		JLabel TestoFoto2 = new JLabel("Foto 2");
		GridBagConstraints gbc_TestoFoto2 = new GridBagConstraints();
		gbc_TestoFoto2.insets = new Insets(0, 0, 5, 5);
		gbc_TestoFoto2.gridx = 1;
		gbc_TestoFoto2.gridy = 5;
		frameVisual.getContentPane().add(TestoFoto2, gbc_TestoFoto2);
		
		JLabel TestoFoto3 = new JLabel("Foto3");
		GridBagConstraints gbc_TestoFoto3 = new GridBagConstraints();
		gbc_TestoFoto3.insets = new Insets(0, 0, 5, 5);
		gbc_TestoFoto3.gridx = 6;
		gbc_TestoFoto3.gridy = 5;
		frameVisual.getContentPane().add(TestoFoto3, gbc_TestoFoto3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 2;
		gbc_panel_3.gridheight = 5;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 6;
		frameVisual.getContentPane().add(panel_3, gbc_panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.gridwidth = 2;
		gbc_panel_4.gridheight = 5;
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 6;
		gbc_panel_4.gridy = 6;
		frameVisual.getContentPane().add(panel_4, gbc_panel_4);
		
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridwidth = 2;
		gbc_panel_5.gridheight = 3;
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 11;
		frameVisual.getContentPane().add(panel_5, gbc_panel_5);
		
		JLabel testoFoto_4 = new JLabel("Foto 4");
		GridBagConstraints gbc_testoFoto_4 = new GridBagConstraints();
		gbc_testoFoto_4.insets = new Insets(0, 0, 5, 5);
		gbc_testoFoto_4.gridx = 4;
		gbc_testoFoto_4.gridy = 11;
		frameVisual.getContentPane().add(testoFoto_4, gbc_testoFoto_4);
		
		if(listaFoto.size() == 1){
		
			lblFoto1 = new JLabel("");
			panel_1.add(lblFoto1);
			lblFoto1.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(0), 130, 130));
		
		} else if(listaFoto.size() == 2){
		
			lblFoto1 = new JLabel("");
			panel_1.add(lblFoto1);
			lblFoto1.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(0), 130, 130));
			
			lblFoto2 = new JLabel("");
			panel_2.add(lblFoto2);
			lblFoto2.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(1), 130, 130));

		} else if(listaFoto.size() == 3){
		
			lblFoto1 = new JLabel("");
			panel_1.add(lblFoto1);
			lblFoto1.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(0), 130, 130));
			
			lblFoto2 = new JLabel("");
			panel_2.add(lblFoto2);
			lblFoto2.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(1), 130, 130));
		
			lblFoto3 = new JLabel("");
			panel_3.add(lblFoto3);
			lblFoto3.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(2), 130, 130));
		
		} else if(listaFoto.size() == 4){
		
			lblFoto1 = new JLabel("");
			panel_1.add(lblFoto1);
			lblFoto1.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(0), 130, 130));
			
			lblFoto2 = new JLabel("");
			panel_2.add(lblFoto2);
			lblFoto2.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(1), 130, 130));
		
			lblFoto3 = new JLabel("");
			panel_3.add(lblFoto3);
			lblFoto3.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(2), 130, 130));
		
		
			lblFoto4 = new JLabel("");
			panel_4.add(lblFoto4);
			lblFoto4.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(3), 130, 130));
			
		
		}else if(listaFoto.size() == 5){
				
				lblFoto1 = new JLabel("");
				panel_1.add(lblFoto1);
				System.out.println(listaFoto);
				lblFoto1.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(0), 130, 130));
				
				lblFoto2 = new JLabel("");
				panel_2.add(lblFoto2);
				lblFoto2.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(1), 130, 130));
			
				lblFoto3 = new JLabel("");
				panel_3.add(lblFoto3);
				lblFoto3.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(2), 130, 130));
			
				lblFoto4 = new JLabel("");
				panel_4.add(lblFoto4);
				lblFoto4.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(3), 130, 130));
				
				lblFoto5 = new JLabel("");
				panel_5.add(lblFoto5);
				lblFoto5.setIcon(ProprietaFoto.ResizeImage(listaFoto.get(4), 130, 130));
		}else{
			System.out.println("Impossibile visualizzare altre foto!");
		}
	}	
}
