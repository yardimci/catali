package Aktorler;

import OyunAlani.Alan;
import item.Hizlanma;
import item.Item;
import item.Puan;
import item.Tas;

import java.awt.event.KeyEvent;

public class Ali extends Eleman {

    protected int vx;

    private boolean left, right;

    protected static final int Ali_HIZI = 20;

    public static final int MAX_HP = 200;

    private int score;

    private int hp;

    private int hiz;

    public Ali(Alan alan) {

        super(alan);
        setAdlar(new String[]{"ali.gif", "ali2.gif"});
        setFrameHizi(5);
        hp = MAX_HP;
        score = 0;
        hiz = Ali_HIZI;
    }

    public void act() {

        super.act();

        x += vx;

        if (x < 0 || x > Alan.GENISLIK)

            vx = -vx;

        if (x <= -50)
            x = -50;

        if (x > (alan.GENISLIK - getGenislik()) + 10)
            x = (alan.GENISLIK - getGenislik()) + 10;

    }

    public int getVx() {
        return vx;
    }

    public void setVx(int i) {
        vx = i;
    }

    protected void updateSpeed() {

        vx = 0;

        if (left)
            vx = -hiz;

        if (right)
            vx = hiz;

    }

    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                left = false;
                break;

            case KeyEvent.VK_RIGHT:
                right = false;
                break;

        }

        updateSpeed();

    }

    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                left = true;
                break;

            case KeyEvent.VK_RIGHT:
                right = true;
                break;

            case KeyEvent.VK_SPACE:
                zincirleme();
                break;

        }

        updateSpeed();

    }

    public void zincirleme() {

        Zincir z = new Zincir(alan);
        z.setX(x + getGenislik() - 40);
        z.setY(y - z.getYukseklik());
        alan.elemanEkle(z);
        alan.getSesKaynak().playSound("loadclip.wav");
    }

    public void collision(Eleman e) {
        if (e instanceof Balon) {
            e.remove();
            SkorEkle(20);
            HpEkle(-10);
            alan.getSesKaynak().playSound("ahh.wav");
        }
        if (e instanceof Ok) {
            e.remove();
            HpEkle(-20);
            alan.getSesKaynak().playSound("ahh.wav");
        }
        if (e instanceof Simsek) {
            e.remove();
            HpEkle(-30);
            alan.getSesKaynak().playSound("ahh.wav");
        }
        if (e instanceof Item) {
            Item ed = (Item) e;
            String[] img = ed.getAdlar();
            if (img[0] == "tas.gif") {
                Tas asd = (Tas) ed;
                HpEkle(asd.Acitir());
                alan.getSesKaynak().playSound("acidi.wav");
            } else if (img[0] == "bot.gif") {
                if (hiz < 45) {
                    Hizlanma asd = (Hizlanma) ed;
                    addHiz(asd.Hizlandir());
                }
            } else if (img[0] == "puan.gif") {
                Puan asd = (Puan) ed;
                addScore(asd.puanItem());
            }
            e.remove();
        }
        if (getHp() < 0)
            alan.gameOver();

    }

    public int getHiz() {
        return hiz;
    }

    public void setHiz(int hiz) {
        this.hiz = hiz;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void addScore(int i) {
        score += i;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void SkorEkle(int i) {
        score += i;
    }

    public void HpEkle(int i) {
        hp += i;
        if (hp > MAX_HP)
            hp = MAX_HP;
    }

    public void addHiz(int i) {
        this.hiz += i;
    }

}
