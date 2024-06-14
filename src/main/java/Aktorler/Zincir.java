package Aktorler;

import OyunAlani.Alan;

public class Zincir extends Eleman {
	protected static final int zincirHizi = 10;

	public Zincir(Alan alan) {
		super(alan);
		setAdlar(new String[] { "zincir.gif" });
		setFrameHizi(50);// frame speed normalde 1 ama bu eleman icin artk
							// 10:)
	   //	yEksenleri=new ArrayList<Integer>();
	}

	public void act() {
		super.act();
		y -= zincirHizi;
		if (y < 0)
			remove();
	}

	
}
