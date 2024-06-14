package Yuklemeler;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageKaynak extends Kaynak 
{
	
	protected Object loadResource(URL url) 
	{
		try 
		{
			return ImageIO.read(url);
		} 
		catch (Exception e)
		{
			System.out.println("İmage bulunamadi "+url);
			System.out.println("Hata: "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0);
			return null;
		}
	}

	public BufferedImage getImages(String name) 
	{
		return (BufferedImage)getResource(name);
	}
	
}
