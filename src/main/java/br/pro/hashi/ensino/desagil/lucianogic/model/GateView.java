package br.pro.hashi.ensino.desagil.lucianogic.model;

import java.util.LinkedList;
import java.util.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


import javax.swing.JCheckBox;


public class GateView extends JPanel implements KeyListener, ItemListener {

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
			
		public GateView(Gate gate){
			this.gate = gate;
			
			
			
			
			criaCheck(gate.getSize());
			
			
			resultado = new JCheckBox("Resultado");
			resultado.setMnemonic(KeyEvent.VK_C); 
		   
		    resultado.setEnabled(false);
			
				
			for(int i=0; i<listaCheck.size(); i++){
				
				listaCheck.get(i).setMnemonic(KeyEvent.VK_G); 
				listaCheck.get(i).setSelected(false);
				
				listaCheck.get(i).addItemListener(this);
				
				add(listaCheck.get(i));
				
				gate.connect(listaSwitch.get(i), i);
				
				
			}
			resultado.setSelected(gate.read());
			add(resultado);
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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

		}
}
