package br.pro.hashi.ensino.desagil.lucianogic.model;

import java.util.LinkedList;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



import javax.swing.JCheckBox;
import javax.swing.JColorChooser;


public class GateView extends FixedPanel implements KeyListener, ItemListener, MouseListener {
	
	private Image image;


	// Necessario para serializar objetos desta classe.
		private static final long serialVersionUID = 1L;
		
		List<JCheckBox> listaCheck;
		List<Switch> listaSwitch;
		
		
		public void criaCheck (int size){
			listaCheck = new LinkedList<>();
			listaSwitch = new LinkedList<>();
			
			for(int i=0; i<size; i++){
				listaCheck.add(new JCheckBox());
				listaSwitch.add(new Switch());
			}
		}
			
		private Gate gate;
		private JCheckBox resultado;
		private LED led;
		private Color color;
			
		public GateView(Gate gate){
			super(370, 220);
			this.gate = gate;
			super.addMouseListener(this);
			Color color = new Color(250, 100, 17);
			
			int r = color.getRed();
			int g = color.getGreen();
			int b = color.getBlue();
			led = new LED(r, g, b);
			
			
			
			
			image = loadImage(gate.toString());
			
			
			criaCheck(gate.getSize());
			
			
			resultado = new JCheckBox("Resultado");
			resultado.setMnemonic(KeyEvent.VK_C); 
		   
		    resultado.setEnabled(false);
			
		    int n = listaCheck.size()+1;	
		    
			for(int i=0; i<listaCheck.size(); i++){
				
				listaCheck.get(i).setMnemonic(KeyEvent.VK_G); 
				listaCheck.get(i).setSelected(false);
				
				listaCheck.get(i).addItemListener(this);
				int j = i + 1;
				add(listaCheck.get(i),10,220*j/n,50,25);
				
				gate.connect(listaSwitch.get(i), i);
				
				
			}
			
			
			led.connect(gate, 0);
			//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
		
				
		@Override
		public void mouseClicked(MouseEvent e) {
		   int x = e.getX();
		   int y = e.getY();
			
			if (x >= 250 && x <= 350 && y >= 60 && y <= 160){
				color = JColorChooser.showDialog(this, null, null);

				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				led = new LED(r, g, b);
				led.connect(gate, 0);
				repaint();
				getToolkit().sync();
				
			}
	
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}
		


		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			int i;
			
			Object source = e.getItemSelectable();
			for(i=0; i<listaCheck.size(); i++){
				if (listaCheck.get(i) == source){
					break;
				}	
			}
			listaSwitch.get(i);
			
			if (e.getStateChange() == ItemEvent.DESELECTED){
				listaSwitch.get(i).setOn(false);
			}
			else{
				listaSwitch.get(i).setOn(true);
			}
			resultado.setSelected(gate.read());
			repaint();
			getToolkit().sync();
			
		}
		private Image loadImage(String filename) {
			URL url = getClass().getResource("/img/" + filename + ".png");
			ImageIcon icon = new ImageIcon(url);
			return icon.getImage();
		}
		
		
		@Override
		public void paintComponent(Graphics g) {
			
			g.drawImage(image, 70, 50, 200, 150, null);
			color = new Color(led.getR(),led.getG(),led.getB());
			if(led.isOn() == true){
				g.setColor(color);
				g.fillOval(300, 100, 50, 50);
			}
			
			else{
				g.setColor(Color.BLACK);
				g.fillOval(300, 100, 50, 50);
				
			}
			// Evita bugs visuais em alguns sistemas operacionais.
			getToolkit().sync();
			
	    }
}
