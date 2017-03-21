
package br.pro.hashi.ensino.desagil.lucianogic.model;

public class MuxGate extends Gate {
	private NotGate notGate;
	private AndGate andGate1;
	private AndGate andGate2;
	private OrGate orGate;

	public MuxGate() {
		super(3);
		notGate = new NotGate();
		andGate1 = new AndGate();
		andGate2 = new AndGate();
		orGate = new OrGate();
		
		orGate.connect(andGate1, 0);
		orGate.connect(andGate2, 1);
		
	}

	@Override
	public boolean read() {
		return orGate.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		
			notGate.connect(emitter, 2);
			
			andGate1.connect(notGate, 0);
			andGate1.connect(emitter, 1);
	
			andGate2.connect(emitter,  2);
			andGate2.connect(emitter, 1);
			
		}	
		
		
	}

