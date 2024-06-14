package Aktorler;

import OyunAlani.Alan;

public class Ok extends Eleman {
	protected static final int ARROW_SPEED = 20;
	private double angle;
	public Ok(Alan alan) {
		super(alan);
		setAdlar(new String[] { "ok.gif" });
	}

	public void act() {
		super.act();
		if (angle < 0)
			y += (Math.cos(-1 * angle)) * ARROW_SPEED;
		else
			y += (Math.cos(angle)) * ARROW_SPEED;
		x -= (Math.sin(angle)) * ARROW_SPEED;		

		if (y > alan.YUKSEKLIK)
			remove();// bunu yaparak birden cok mermi olusruutp
		// yapmamayi sagliyoruz
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

}
