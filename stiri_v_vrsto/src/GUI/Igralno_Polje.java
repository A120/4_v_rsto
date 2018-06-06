package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Logika_igre.*;


public class Igralno_Polje extends JPanel implements MouseListener {
	
	private Okno master;
	
	private final static double LINE_WIDTH = 0.1; 	//relativna �irina �rte
	
	private final static double PADDING = 0.1; //prostor okoli simbolovv O in X
	
	public Igralno_Polje(Okno master) {
		super();
		setBackground(Color.white);
		this.master = master;
		this.addMouseListener(this);
	}
	
	
	public Dimension getPreferredSize() {
		return new Dimension(600, 700);
	}
	
	private double squareWidth() {
		return Math.min(getWidth(), getHeight()) / Math.max(Logika.M, Logika.N);
	}
	
	/**
	 * Nari�e X v polje (i, j).
	 * @param g2
	 * @param i
	 * @param j
	 */
	private void narisiX(Graphics2D g2, int i, int j) {
		double w = squareWidth();
		double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // �irina X
		double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
		double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
		g2.setColor(Color.blue);
		g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
		g2.drawLine((int)x, (int)y, (int)(x + r), (int)(y + r));
		g2.drawLine((int)(x + r), (int)y, (int)x, (int)(y + r));
	}
	
	/**
	 * Nari�e O v polje (i, j).	
	 * @param g2
	 * @param i
	 * @param j
	 */
	private void narisiO(Graphics2D g2, int i, int j) {
		double w = squareWidth();
		double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // Premer O
		double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
		double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
		g2.drawOval((int)x, (int)y, (int)r , (int)r);
	}
	
	
	protected void narisiKomponento(Graphics g) {
		super.narisiKomponento(g);
		Graphics2D g2 = (Graphics2D)g;

		double w = squareWidth();

		// �e imamo zmagovalni �etvorec, njegovo ozadje pobarvamo.
		Cetvorec z = master.zmagovalniCetvorec();
		if (z != null) {
			g2.setColor(new Color(255, 255, 196));
			for (int k = 0; k < Logika.A; k++) {
				int i = z.x[k];
				int j = z.y[k];
				g2.fillRect((int)(w * i), (int)(w * j), (int)w, (int)w);
			}
		}
		
		//manjkajo se crte
		
		
		// Ri�emo kri�ce in kro�ce
		Polje[][] plosca = master.vrniPlosco();
		if (plosca != null) {
			for (int i = 0; i < Logika.M; i++) {
				for (int j = 0; j < Logika.N; j++) {
					switch(plosca[i][j]) {
					case O: narisiO(g2, i, j); break;
					case X: narisiX(g2, i, j); break;
					default: break;
					}
				}
			}
		}	
	}
	
	public void mouseClicked(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		int w = (int)(squareWidth());
		int j = x / w ;
		double dj = (x % w) / squareWidth();
		int i = y / w ;
		double di = (y % w) / squareWidth();
		if (0 <= i && i < Logika.M &&
		    0.5 * LINE_WIDTH < di && di < 1.0 - 0.5 * LINE_WIDTH &&
		    0 <= j && j < Logika.N && 
		    0.5 * LINE_WIDTH < dj && dj < 1.0 - 0.5 * LINE_WIDTH) {
			master.klikNaPolje(i, j);
		}
	}
	
	public void mousePressed(MouseEvent e) {
		//Ignoriramo
	}

	public void mouseReleased(MouseEvent e) {
		//Ignoriramo
	}

	public void mouseEntered(MouseEvent e) {
		//Ignoriramo
	}

	public void mouseExited(MouseEvent e) {
		//Ignoriramo
	}
}
	

