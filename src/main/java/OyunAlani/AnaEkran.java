package OyunAlani;

import Yuklemeler.Skorlar;

import javax.swing.*;
import java.awt.*;

public class AnaEkran extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel jContentPane = null;

    private JButton jB1 = null;

    private JButton jB2 = null;

    private JButton jB3 = null;

    /**
     * This method initializes jB1
     *
     * @return javax.swing.JButton
     */
    private JButton getJB1() {
        if (jB1 == null) {
            jB1 = new JButton();
            jB1.setBounds(new Rectangle(32, 28, 182, 32));
            jB1.setText("Yeni Oyun");
            jB1.setBackground(Color.green);
            jB1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    //Catali c = new Catali();
                    //c.game();
                }
            });
        }
        return jB1;
    }

    /**
     * This method initializes jB2
     *
     * @return javax.swing.JButton
     */
    private JButton getJB2() {
        if (jB2 == null) {
            jB2 = new JButton();
            jB2.setBounds(new Rectangle(118, 97, 182, 32));
            jB2.setText("Hakkinda ve Hakkimizda");
            jB2.setBackground(new Color(102, 102, 255));
        }
        return jB2;
    }

    /**
     * This method initializes jB3
     *
     * @return javax.swing.JButton
     */
    private JButton getJB3() {
        if (jB3 == null) {
            jB3 = new JButton();
            jB3.setBounds(new Rectangle(193, 157, 182, 32));
            jB3.setText("Y�ksek Skorlar");
            jB3.setBackground(new Color(102, 0, 102));
            jB3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    Skorlar s = new Skorlar();
                    s.Listele();
                }
            });
        }
        return jB3;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AnaEkran thisClass = new AnaEkran();
                thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                thisClass.setVisible(true);
            }
        });
    }

    /**
     * This is the default constructor
     */
    public AnaEkran() {
        super();
        initialize();
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(421, 258);
        this.setContentPane(getJContentPane());
        this.setTitle("catALi' ye Ho�geldiniz ");
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJB1(), null);
            jContentPane.add(getJB2(), null);
            jContentPane.add(getJB3(), null);
        }
        return jContentPane;
    }


}  //  @jve:decl-index=0:visual-constraint="10,10"
