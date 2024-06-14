package Aktorler;

import AnaOyun.Catali;
import OyunAlani.Alan;

public class Zeyyus extends Eleman {

    protected int vx, vy;

    protected static final double FIRING_FREQUENCY = 0.3; //

    public static final int MAX_SHIELDS = 400;

    private static final int OYUN_YUKSEKLIK = 100;

    public static final int speed = 12;

    private boolean sagdanSola = true;
    private int shields;

    public Zeyyus(Alan alan) {
        super(alan);
        setAdlar(new String[]{"zeus.gif"});
        shields = MAX_SHIELDS;
    }

    public void act() {
        super.act();
        x += vx;
        y += vy;
        if (Math.random() < FIRING_FREQUENCY)
            fire();
        updateSpeed();


    }

    protected void updateSpeed() {
        vx = 0;
        vy = 0;
        int i = Catali.ali.x - this.x;
        if (i > 0) {
            sagdanSola = false;
        } else {
            sagdanSola = true;
        }
        if (sagdanSola == false) {
            if (x < 180) {
                vy += 5;
                vx += 9;
            }
            if ((x > 180) && (x < 360)) {
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
            if (x < 180) {
                vy -= 5;
                vx -= 9;
            }
            if ((x > 180) && (x < 360)) {
                vy += 5;
                vx -= 9;
            }
            if ((x > 361) && (x < 481)) {
                vy -= 5;
                vx -= 9;
            }
            if ((x > 480) && (x < 641)) {
                vy += 5;
                vx -= 9;
            }
            if (x < 20) {
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

    public void fire() {
        Simsek m = new Simsek(alan);
        m.setX(x + getGenislik() / 2);
        m.setY(y + getYukseklik());
        double ust = m.getX() - Catali.ali.getX();
        double alt = Catali.ali.getY() - m.getY();
        double tan = ust / alt;
        m.setAci(Math.atan(tan));
        alan.elemanEkle(m);
    }


    public int getShields() {
        return shields;
    }

    public void setShields(int i) {
        shields = i;
    }

    public void collision(Eleman a) {
        if (a instanceof Zincir) {
            a.remove();
            shields -= 10;
            if (shields <= 0) {
                remove();
                alan.victory();
                alan.getAli().SkorEkle(100);
            } else alan.getAli().SkorEkle(70);
        }
    }
}
