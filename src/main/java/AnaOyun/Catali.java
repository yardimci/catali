package AnaOyun;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import OyunAlani.Alan;
import Yuklemeler.ImageKaynak;
import Yuklemeler.OyuncuSkor;
import Yuklemeler.Skorlar;

import Yuklemeler.SesKaynak;
import Aktorler.Ali;
import Aktorler.Balon;
import Aktorler.Eleman;
import Aktorler.Melek;
import Aktorler.Zeyyus;

public class Catali extends Canvas implements Alan, KeyListener {
	public static Ali ali;

	private BufferStrategy strategy;

	private BufferedImage tugla;

	private BufferedImage arkaFon;

	public static int altSinir = 595;// 

	private ImageKaynak imagekaynak;

	private SesKaynak seskaynak;

	private ArrayList elemanlar;

	private int zaman;

	private long usedTime;

	private boolean gameEnded = false;

	private boolean vic = false;

	private boolean levelEnded = false;

	public static int balonSayisi = 0;

	public static int melekSayisi = 3;

	private String[] senaryo;

	public Catali() {
		imagekaynak = new ImageKaynak();
		seskaynak = new SesKaynak();

		JFrame anaEkran = new JFrame("CatALi");
		JPanel panel = (JPanel) anaEkran.getContentPane();
		setBounds(0, 0, Alan.GENISLIK, Alan.YUKSEKLIK);
		panel.setPreferredSize(new Dimension(Alan.GENISLIK, Alan.YUKSEKLIK));
		panel.setLayout(null);
		panel.add(this);
		anaEkran.setBounds(0, 0, Alan.GENISLIK, Alan.YUKSEKLIK);
		anaEkran.setVisible(true);
		addKeyListener(this);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		requestFocus();
		anaEkran.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	public void initWorld(int indis) {

		balonSayisi = 0;
		elemanlar = new ArrayList();
		int balonSayi = 20;
		if (indis == 0) {
			balonSayi = 20;
		} else {
			balonSayi = 30;
		}
		for (int i = 0; i < balonSayi; i++) {
			addBalon();
		}

		initAli(indis);

		seskaynak.loopSound("cloud.wav");
	}

	public void initAli(int i) {
		if (i == 0)
			ali = new Ali(this);
		ali.setX(Alan.GENISLIK / 2);
		ali.setY(altSinir - ali.getYukseklik());
	}

	public void addBalon() {
		Balon m = new Balon(this);
		m.setX((int) (Math.random() * Alan.GENISLIK));
		m.setY((int) (Math.random() * Alan.YUKSEKLIK) - 400);
		m.setVx((int) (Math.random() * 20 - 10));
		elemanlar.add(m);
		balonSayisi++;
	}

	public void game() throws InterruptedException {
		usedTime = 1000;
		zaman = 0;
		for (int i = 0; i < 2; i++) {

			initWorld(i);
			if (i == 0) {
				paintSenaryo();
				try {
					Thread.currentThread().sleep(3000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
				paintLevelOver(1);
				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}

			} else {
				paintLevelOver(2);

				try {
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			while (isVisible() && !gameEnded) {
				zaman++;
				if (balonSayisi == 0) {
					balonSayisi--;
					if (i == 0) {
						initMelek();
						getSesKaynak().playSound("aa.wav");
					} else if (i == 1) {
						initZeyyus();
						getSesKaynak().playSound("z.wav");
					}
				}

				if (melekSayisi == 0) {
					melekSayisi--;
					levelOver();
					getSesKaynak().playSound("nihoha.wav");
					break;
				}

				long baslamaZamani = System.currentTimeMillis();
				updateWorld();
				checkCollisions();
				paintWorld(i);

				usedTime = System.currentTimeMillis() - baslamaZamani;
				try {
					Thread.sleep(HIZ);
				} catch (InterruptedException e) {
				}
			}
			if (gameEnded)
				break;
		}
		if (vic) {
			paintvictory();
			getSesKaynak().playSound("yendim.wav");
			KayitAl();
		} else {
			paintGameOver();
			getSesKaynak().playSound("oldom.wav");
			KayitAl();
		}

	}

	private void initZeyyus() {
		Zeyyus ze = new Zeyyus(this);
		ze.setX((300));
		ze.setY(10);
		elemanlar.add(ze);
	}

	private void initMelek() {
		for (int i = 0; i < 3; i++) {
			Melek me = new Melek(this);
			me.setX((int) (Math.random() * (Alan.GENISLIK * 0.7)));
			me.setY((int) (Math.random() * (Melek.OYUN_YUKSEKLIK * 0.7)));
			elemanlar.add(me);
		}

	}

	private void checkCollisions() {
		Rectangle playerBounds = ali.getBounds();
		for (int i = 0; i < elemanlar.size(); i++) {
			Eleman a1 = (Eleman) elemanlar.get(i);
			Rectangle r1 = a1.getBounds();
			if (r1.intersects(playerBounds)) {
				ali.collision(a1);
				a1.collision(ali);
			}
			for (int j = i + 1; j < elemanlar.size(); j++) {
				Eleman a2 = (Eleman) elemanlar.get(j);
				Rectangle r2 = a2.getBounds();
				if (r1.intersects(r2)) {
					a1.collision(a2);
					a2.collision(a1);
				}
			}
		}

	}

	public void paintWorld(int indis) {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		tugla = imagekaynak.getImages("zemin.jpg");
		g.setPaint(new TexturePaint(tugla, new Rectangle(0, 0,
				tugla.getWidth(), tugla.getHeight())));
		g.fillRect(0, 580, getWidth(), 50);

		String arkaFonadi;
		if (indis == 0) {
			arkaFonadi = "lvl1.gif";
		} else {
			arkaFonadi = "lvl2.gif";
		}
		arkaFon = imagekaynak.getImages(arkaFonadi);
		g.setPaint(new TexturePaint(arkaFon, new Rectangle(0, 0, arkaFon
				.getWidth(), arkaFon.getHeight())));
		g.fillRect(0, 0, getWidth(), 580);
		for (int i = 0; i < elemanlar.size(); i++) {
			Eleman m = (Eleman) elemanlar.get(i);
			m.paint(g);
		}
		ali.paint(g);
		paintDurum(g);
		// paintStatus(g);
		strategy.show();
	}

	public void updateWorld() {
		for (int i = 0; i < elemanlar.size(); i++) {
			Eleman m = (Eleman) elemanlar.get(i);
			if (m.isMarkedForRemoval()) {
				if (m instanceof Balon)
					balonSayisi--;
				if (m instanceof Melek)
					melekSayisi--;
				elemanlar.remove(i);
			} else {

				m.act();
			}
		}
		ali.act();
	}

	public void paintGameOver() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		BufferedImage oluAli;
		oluAli = imagekaynak.getImages("ali3.gif");
		g.setPaint(new TexturePaint(oluAli, new Rectangle(0, 0, oluAli
				.getWidth(), oluAli.getHeight())));
		g
				.drawImage(oluAli, (Alan.GENISLIK - oluAli.getWidth()) / 2,
						(Alan.YUKSEKLIK - oluAli.getHeight()) / 2,
						(ImageObserver) this);

		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g
				.drawString("OYUN BITTI!", Alan.GENISLIK / 2 - 100,
						Alan.YUKSEKLIK / 2);
		strategy.show();
	}

	public void paintvictory() {

		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

		BufferedImage oluZeus;
		oluZeus = imagekaynak.getImages("zeus2.gif");
		g.setPaint(new TexturePaint(oluZeus, new Rectangle(0, 0, oluZeus
				.getWidth(), oluZeus.getHeight())));
		g.drawImage(oluZeus, (Alan.GENISLIK - oluZeus.getWidth()) / 2,
				(Alan.YUKSEKLIK - oluZeus.getHeight()) / 2,
				(ImageObserver) this);

		g.setColor(Color.GREEN);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("TEBRIKLER!", (Alan.GENISLIK / 2) - 200,
				Alan.YUKSEKLIK / 2);
		strategy.show();

	}

	public void paintLevelOver(int lvl) {
		String ad;
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 40));

		if (lvl == 1)
			ad = "LEVEL 1";
		else
			ad = "LEVEL 2";
		g.drawString(ad, Alan.GENISLIK / 2 - 100, Alan.YUKSEKLIK / 2);
		strategy.show();
	}

	public void keyPressed(KeyEvent e) {
		ali.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		ali.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		Catali c = new Catali();
		try {
			c.game();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void elemanEkle(Eleman e) {
		elemanlar.add(e);

	}

	public void gameOver() {
		gameEnded = true;
	}

	public void victory() {
		gameEnded = true;
		vic = true;
	}

	public void levelOver() {
		levelEnded = true;
	}

	public Ali getAli() {
		return ali;
	}

	public SesKaynak getSesKaynak() {
		return seskaynak;
	}

	public ImageKaynak getImageKaynak() {
		// TODO Auto-generated method stub
		return imagekaynak;
	}

	public void paintHP(Graphics2D g) {
		Color a = new Color(255, 100, 100, 150);
		g.setPaint(a);
		int genislik1 = (int) (ali.MAX_HP * 0.7);
		g.fillRect(10, 15, genislik1, 20);
		Color b = new Color(100, 150, 200);
		g.setPaint(b);
		int genislik2 = (int) (ali.getHp() * 0.7);
		g.fillRect(10, 15, genislik2, 20);
		g.setFont(new Font("Arial", Font.BOLD, 13));
		Color c = new Color(200, 100, 100);
		g.setPaint(c);
		g.drawString("Hit Point", 10, 15);

	}

	public void paintScore(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 13));
		Color a = new Color(200, 100, 100);
		g.setPaint(a);
		g.drawString("Score:", 10, 48);
		g.setPaint(Color.red);
		g.drawString(ali.getScore() + "", 80, 48);
	}

	public void paintfps(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.white);
		if (usedTime > 0)
			g.drawString(String.valueOf("FPS:" + 1000 / usedTime), 730, 15);
		else
			g.drawString("FPS: ---", 730, 15);
	}

	public void paintDurum(Graphics2D g) {
		paintHP(g);
		paintScore(g);
		paintfps(g);
	}

	private void KayitAl() {
		String yanit = JOptionPane
				.showInputDialog("Lutfen adinizi Giriniz:(Opsiyonel)");
		if (yanit == null)
			yanit = "CATALI";
		Skorlar a = new Skorlar();
		OyuncuSkor o = new OyuncuSkor(ali.getScore(), yanit);
		a.oku();
		a.SkorEkleme(o);
		a.Listele();
		a.yaz();

	}

	public void paintSenaryo() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		arkaFon = imagekaynak.getImages("lvl1.gif");
		g.setPaint(new TexturePaint(arkaFon, new Rectangle(0, 0, arkaFon
				.getWidth(), arkaFon.getHeight())));
		g.fillRect(0, 0, getWidth(), 580);

		senaryo = new String[20];
		senaryo[0] = "GECMISINDE BALONLARLA ILGILI SORUNLARI OLAN ALI";
		senaryo[1] = "BALONLARDAN NEFRET EDER VE HEPSINI PATLATMAK ISTER";
		senaryo[2] = "AMA COCUKLAR BOLANLARIN PATLAMASINA COK UZULURLER ";
		senaryo[3] = "MELEKLERI VE ZEYYUSU YARDIMA CAGIRIRLAR ";
		senaryo[4] = "VE AMANSIZ MUCADELE BASLAR....";
		senaryo[5] = "BALONLARI PATLAT, MELEKLERI VE ZEYYUSU YOK ET..";
		senaryo[6] = "    ";
		senaryo[7] = "   Oyun az sonra baslayacak...!";

		for (int i = 0; i < 8; i++) {

			String ad;
			g.setColor(Color.blue);
			g.setFont(new Font("Arial", Font.BOLD, 15));

			ad = senaryo[i];

			g.drawString(ad, Alan.GENISLIK / 2 - 200, 100 + (i * 50));
			strategy.show();
		}
	}

}
