
package br.pro.hashi.ensino.desagil.lucianogic.model;

public class AndGate extends Gate {
	private NandGate nandGate1;
	private NandGate nandGate2;
	

	public AndGate() {
		super(2);
		nandGate1 = new NandGate();
		nandGate2 = new NandGate();
		
		nandGate2.connect(nandGate1,  0);
		nandGate2.connect(nandGate1, 1);
		
	}

	@Override
	public boolean read() {
		return nandGate2.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
			nandGate1.connect(emitter, 0);
			nandGate1.connect(emitter, 1);

			
			
		
		
	}
}
