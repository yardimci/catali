package Aktorler;

import AnaOyun.Catali;
import OyunAlani.Alan;

public class Melek extends Eleman {

	public static final int MAX_HP = 150;

	protected static final int ANGEL_SPEED = 8;

	protected static final double FIRING_FREQUENCY = 0.1;

	public static final int OYUN_YUKSEKLIK = 300;

	private boolean sagdanSola = false;

	protected int vx, vy;

	private int hp;

	public Melek(Alan alan) {
		super(alan);
		setAdlar(new String[] { "melek1.gif" });
		hp = MAX_HP;
	}

	public void act()// 2 yonlu hareket
	{
		super.act();
		x += vx;
		y += vy;
		if (Math.random() < FIRING_FREQUENCY)
			ates();
		updateSpeed();
	}

	protected void updateSpeed() {
		vx = 0;
		vy = 0;
		if (sagdanSola == false) {
			if (x < 181) {
				vy += 5;
				vx += 9;
			}
			if ((x > 180) && (x < 361)) {
				vy -= 5;
				vx += 9;
			}
			if ((x > 360) && (x < 481)) {
				vy += 5;
				vx += 9;
			}
			if ((x > 480) && (x < 640)) {
				vy -= 5;
				vx += 9;
			}
			if (x > 630) {
				sagdanSola = true;
			}
		}

		if (sagdanSola) {
			if (x < 181) {
				vy -= 5;
				vx -= 9;
			}
			if ((x > 180) && (x < 361)) {
				vy += 5;
				vx -= 9;
			}
			if ((x > 360) && (x < 481)) {
				vy -= 5;
				vx -= 9;
			}
			if ((x > 480) && (x < 641)) {
				vy += 5;
				vx -= 9;
			}
			if (x < 10) {
				sagdanSola = false;
			}
		}

	}

	public int getVx() {
		return vx;
	}

	public void setVx(int i) {
		vx = i;
	}

	public void ates() {
		Ok b = new Ok(alan);
		b.setX(x);
		b.setY(y - b.getYukseklik());
		double ust = b.getX() - Catali.ali.getX();
		double alt = Catali.ali.getY() - b.getY();
		double tan = ust / alt;
		b.setAngle(Math.atan(tan));
		alan.elemanEkle(b);
		// alan.getSesKaynak().playSound("missile.wav");
	}

	public void collision(Eleman a)// aktor canavarlarla

	{
		if (a instanceof Zincir) {
			a.remove();
			hp -= 10;
			if (hp <= 0) {
				remove();
				alan.getAli().SkorEkle(50);
			} else
				alan.getAli().SkorEkle(30);
		}
	}
}
