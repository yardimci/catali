package item;

import Aktorler.Eleman;
import OyunAlani.Alan;

public class Item extends Eleman {

	protected static final int itemHiz = 15; // final konulmali mi
	private static final int OYUN_YUKSEKLIK = 525;
	
	public Item(Alan alan,String[] ad) {
		super(alan);
		setAdlar(ad);

	}

	public void act() {
		super.act();
		y += itemHiz;
		if (y >= OYUN_YUKSEKLIK) // yonu neolcak ??
			remove();
	}

	public static int getItemHiz() {
		return itemHiz;
	}

}
