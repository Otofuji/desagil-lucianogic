package br.pro.hashi.ensino.desagil.lucianogic.model;

public class MuxGate extends Gate {
	private Emitter[] emitters;
	private NandGate nandGate;
	
	public MuxGate() {
		super(2);
		emitters = new Emitter[2];
	}

	@Override
	public boolean read() {
		return !(emitters[0].read() && emitters[1].read());
	}

	@Override
	protected void doConnect(Emitter emitter, int index) {
		emitters[index] = emitter;
	}
}
