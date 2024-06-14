package OyunAlani;

import Yuklemeler.*;
import Aktorler.*;

public interface Alan 
{
	public static final int GENISLIK=800;
	public static final int YUKSEKLIK=640;	
	public static final int HIZ=100;
	public ImageKaynak getImageKaynak(); 
	public void elemanEkle(Eleman e);
	public Ali getAli();
	public void gameOver();
	public void victory();
	public SesKaynak getSesKaynak();
	
}
