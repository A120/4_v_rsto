package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logika_igre.logika;
import logika_igre.Polje;
import logika_igre.cetvorcek;

public class Igralno_Polje {
	
	private Okno master;
	
	private final static double LINE_WIDTH = 0.1; 	//relativna širina črte
	
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
	
	private void Narisi_X(Graphics2D g2, int i, int j) {
		double w = squareWidth();
		double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // sirina X
		double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
		double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
		g2.setColor(Color.blue);
		g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
		g2.drawLine((int)x, (int)y, (int)(x + r), (int)(y + r));
		g2.drawLine((int)(x + r), (int)y, (int)x, (int)(y + r));
	}
	
	
	
	private void Narisi_O(Graphics2D g2, int i, int j) {
		double w = squareWidth();
		double r = w * (1.0 - LINE_WIDTH - 2.0 * PADDING); // premer O
		double x = w * (i + 0.5 * LINE_WIDTH + PADDING);
		double y = w * (j + 0.5 * LINE_WIDTH + PADDING);
		g2.setColor(Color.red);
		g2.setStroke(new BasicStroke((float) (w * LINE_WIDTH)));
		g2.drawOval((int)x, (int)y, (int)r , (int)r);
	}
	
	
	protected void narisi_komponento(Graphics g) {
		super.narisi_komponento(g);
		Graphics2D g2 = (Graphics2D)g;

		double w = squareWidth();

		// če imamo zmagovalni cetvorcek, njeno ozadje pobarvamo
		cetvorec t= master.zmagovalniCetvorec();
		if (t != null) {
			g2.setColor(new Color(255, 255, 196));
			for (int k = 0; k < Igra.N; k++) {
				int i = t.x[k];
				int j = t.y[k];
				g2.fillRect((int)(w * i), (int)(w * j), (int)w, (int)w);
			}
		}
	}
		
		//manjkajo se crte
		
		
		// manjkajo se crte
		
		// križci in krožci - ne delajo se
		Polje[][] plosca = master.getPlosca();
		for (int j =0 , j<(Logika.M -1), j++) {
			if (Polje[][j] == null) {
				
				switch(plosca[][j]) {
				case X: Narisi_X(g2, i, j); break;
				case O: Narisi_O(g2, i, j); break;
				}
				
			}


		}
		break;
}
}
	

