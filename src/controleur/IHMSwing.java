package controleur;
import observeurs.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;


public class IHMSwing extends JPanel implements UpdateEventListener {

	private ArrayList<ObserveurSwing> lobs;
	private BufferedImage im;
	
	public IHMSwing() {
		super();
		lobs = new ArrayList<ObserveurSwing>(0);
		this.im = null;
	}
	public void add(ObserveurSwing o) {
		lobs.add(o);
	}
	
	public void setImage(BufferedImage im) {
		this.im = im;
	}
	
	
	public void paint(){
		Graphics g = im.getGraphics();
        super.paint(g);

        for(ObserveurSwing o: lobs)
            o.print(g);
    }
	@Override
	public void manageUpdate() {
		// TODO Auto-generated method stub
		repaint(); // ordre de mise à jour  
        /*try {      // temporisation (sinon, on ne voit rien)
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
	}

}
