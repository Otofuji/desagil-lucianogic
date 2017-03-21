
package br.pro.hashi.ensino.desagil.lucianogic.model;

public class OrGate extends Gate {
	private NandGate nandGate1;
	private NandGate nandGate2;
	private NandGate nandGate3;

	public OrGate() {
		super(1);
		nandGate1 = new NandGate();
		nandGate2 = new NandGate();
		nandGate3 = new NandGate();
	}

	@Override
	public boolean read() {
		return nandGate3.read();
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		nandGate1.connect(emitter, 0);
		nandGate1.connect(emitter, 1);
		
		nandGate2.connect(emitter, 0);
		nandGate2.connect(emitter, 1);
		
		nandGate3.connect(nandGate1, 0);
		nandGate3.connect(nandGate3, 1);
		
	}
}
