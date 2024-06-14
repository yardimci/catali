package Aktorler;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import Yuklemeler.ImageKaynak;
import OyunAlani.Alan;

public class Eleman
{
	protected Alan alan;
	protected int x,y;
	protected int genislik, yukseklik;
	
	protected ImageKaynak imagekaynak;
	protected String[] adlar;//dosyaların adlarını tutar ör ali için ali1 ali2 gibi
	
	protected int simdikiFrame;
	protected int frameHizi;
	protected int zaman;
	
	protected boolean kaldirilacak;
	
	
	public Eleman(Alan alan) 
	{
	    this.alan=alan;	
        imagekaynak = alan.getImageKaynak();
        simdikiFrame = 0;
        frameHizi = 5;
        zaman=0;
	}
	
	public void remove()
	{
		kaldirilacak = true;
	}
	  
	public boolean isMarkedForRemoval() 
	{
	    return kaldirilacak;
	}
	
	public void paint(Graphics2D g)
	{
	    g.drawImage( imagekaynak.getImages(adlar[simdikiFrame]), x,y, (ImageObserver) alan );
	}
	
	public void act()
    {
		zaman++;
		if (zaman % frameHizi == 0)//o eleman i�in counter b�l�nebilirlik e ula�m�sa frame g�ncellemsi yap�l�r
		{
		   zaman=0;
		   simdikiFrame = (simdikiFrame + 1) % adlar.length;
		}
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getGenislik() {
		return genislik;
	}

	public void setGenislik(int genislik) {
		this.genislik = genislik;
	}

	public int getYukseklik() {
		return yukseklik;
	}

	public void setYukseklik(int yukseklik) {
		this.yukseklik = yukseklik;
	}

	public String[] getAdlar() {
		return adlar;
	}

	public void setAdlar(String[] adlar) {
		this.adlar = adlar;
		 yukseklik = 0;
		 genislik = 0;
		 for (int i = 0; i < adlar.length; i++ ) 
		 {
		       BufferedImage image =imagekaynak.getImages(adlar[i]);
		       yukseklik = Math.max(yukseklik,image.getHeight());
		       genislik = Math.max(genislik,image.getWidth());
		      
		 }
	}

	public int getSimdikiFrame() {
		return simdikiFrame;
	}

	public void setSimdikiFrame(int simdikiFrame) {
		this.simdikiFrame = simdikiFrame;
	}
	
	public Rectangle getBounds() 
	{
	   return new Rectangle(x,y,genislik,yukseklik);
	}
	
	public void collision(Eleman e)
	 {}

	public int getFrameHizi() {
		return frameHizi;
	}

	public void setFrameHizi(int frameHizi) {
		this.frameHizi = frameHizi;
	}

}
