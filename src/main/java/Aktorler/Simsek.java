package Aktorler;

import OyunAlani.Alan;

public class Simsek extends Eleman {

    protected static final int simsekHiz = 30; // final konulmal� m�
    private double aci;

    public Simsek(Alan alan) {
        super(alan);
        setAdlar(new String[]{"simsek.gif"});
        setFrameHizi(1);

    }

    public void act() {
        super.act();
        if (aci < 0)
            y += (Math.cos(-1 * aci)) * simsekHiz;
        else
            y += (Math.cos(aci)) * simsekHiz;
        x -= (Math.sin(aci)) * simsekHiz;

        if (y > alan.YUKSEKLIK)
            remove();// bunu yaparak birden cok mermi
        //
    }

    public static int getSimsekHiz() {
        return simsekHiz;
    }

    public double getAci() {
        return aci;
    }

    public void setAci(double aci) {
        this.aci = aci;
    }

}