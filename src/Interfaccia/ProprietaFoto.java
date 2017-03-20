package Interfaccia;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe che permette di specificare e descrivere le caratteristiche di un immagine.
 * @author Marco Mandelli e Mirko Longhi
 *
 */

public class ProprietaFoto extends JLabel{

	private static final long serialVersionUID = 1L;

	public int width=130,height=130;
	/**
	 * Costruttore che permette di creare una foto 
	 */
	public ProprietaFoto(){
		this.setSize(width,height);
	}
	
	/**
	 * Metodo che permette di resettare l'icona dell'utente
	 */
	public void resetImage(){
		this.setIcon(null);
	}
	
	/**
	 * Metodo che permette di settare la grandezza di ogni immagine come si preferisce specificando larghezza e altezza
	 * 
	 * @param imgPath stringa contente l'indirizzo della foto
	 * @param width	larghezza della foto
	 * @param height altezza della foto
	 * @return ImageIcon ritorna l'immagine con le grandezza specificate
	 */
	public static ImageIcon ResizeImage(String imgPath, int width,int height){ 
		ImageIcon MyImage = new ImageIcon(imgPath); 
		Image img = MyImage.getImage(); 
		Image newImage = img.getScaledInstance(width, height ,Image.SCALE_SMOOTH); 
		ImageIcon image = new ImageIcon(newImage); 
		return image; 
	}
}
