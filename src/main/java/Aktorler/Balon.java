package Aktorler;

import item.Hizlanma;
import item.Item;
import item.Puan;
import item.Tas;
import OyunAlani.Alan;

public class Balon extends Eleman {// itemlar:pleyer h�zland�rma,
	// ta�,ekstra boba 3 l� atar

	protected int vy;

	protected int vx;

	protected int balonHiz = 5;

	protected static final double ITEM_FREQUENCY = 0.3;

	private static final int OYUN_YUKSEKLIK = 525;

	public Balon(Alan alan) {
		super(alan);
		int i = (int) (Math.random() * 4);
		switch (i) {
		case 0:
			setAdlar(new String[] { "balon_yesil.gif" });
			break;
		case 1:
			setAdlar(new String[] { "balon_mor.gif" });
			break;
		case 2:
			setAdlar(new String[] { "balon_sari.gif" });
			break;
		case 3:
			setAdlar(new String[] { "balon_siyah.gif" });
			break;
		}

		setFrameHizi(100);
	}

	public void act()

	{
		super.act();
		if (Math.random() < 0.5) {
			vx += 3;
		} else {
			vx += -3;
		}
		y += 3;
		x += vx;

		if (vx >= 15)
			vx = 15;
		if (vx <= -15)
			vx = -15;

		if (x < 0 || x > (alan.GENISLIK - getGenislik()))
			vx = -vx;

		if (y >= OYUN_YUKSEKLIK)// yere carpma
		{
			remove();
		}
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getBalonHiz() {
		return balonHiz;
	}

	public void setBalonHiz(int balonHiz) {
		this.balonHiz = balonHiz;
	}

	public void collision(Eleman a) {
		if (a instanceof Zincir) {

			a.remove();

			if (Math.random() < ITEM_FREQUENCY) {
				Item item;
				int i = (int) (Math.random() * 3);

				if (i == 0) {
					item = new Tas(alan, new String[] { "tas.gif" });
				} else if (i == 1)//
				{
					item = new Hizlanma(alan, new String[] { "bot.gif" });
				} else {
					item = new Puan(alan, new String[] { "puan.gif" });
				}
				item.setX(x);
				item.setY(y);

				alan.elemanEkle(item);

			}
			remove();
			alan.getAli().SkorEkle(10);

		}

	}
}
