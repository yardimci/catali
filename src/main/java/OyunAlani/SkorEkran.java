package OyunAlani;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JToggleButton;
import java.awt.Point;
import java.awt.Font;

public class SkorEkran extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jL1 = null;

	public JLabel jL12 = null;

	public JLabel jL13 = null;

	private JLabel jL2 = null;

	public JLabel jL22 = null;

	public JLabel jL23 = null;

	private JLabel jL3 = null;

	public JLabel jL32 = null;

	public JLabel jL33 = null;

	private JLabel jL4 = null;

	public JLabel jL42 = null;

	public JLabel jL43 = null;

	private JLabel jL5 = null;

	public JLabel jL52 = null;

	public JLabel j53 = null;

	private JLabel jLabel = null;

	private JLabel jLabel2 = null;

	private JLabel jLabe3 = null;

	private JLabel jLabel111 = null;

	/**
	 * This is the default constructor
	 */
	public SkorEkran() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(434, 301);
		this.setContentPane(getJContentPane());
		this.setTitle("Yuksek Skorlar");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel111 = new JLabel();
			jLabel111.setText("catALi");
			jLabel111.setSize(new Dimension(105, 32));
			jLabel111.setFont(new Font("Felix Titling", Font.BOLD | Font.ITALIC, 24));
			jLabel111.setLocation(new Point(161, 214));
			jLabe3 = new JLabel();
			jLabe3.setBounds(new Rectangle(291, 18, 68, 13));
			jLabe3.setText("SKOR");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(153, 17, 57, 17));
			jLabel2.setText("OYUNCU");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(33, 13, 61, 17));
			jLabel.setText("SIRA");
			j53 = new JLabel();
			j53.setBounds(new Rectangle(294, 178, 68, 17));
			j53.setText("");
			jL52 = new JLabel();
			jL52.setBounds(new Rectangle(154, 179, 57, 16));
			jL52.setText("");
			jL5 = new JLabel();
			jL5.setBounds(new Rectangle(32, 177, 38, 16));
			jL5.setText("5.");
			jL43 = new JLabel();
			jL43.setBounds(new Rectangle(293, 151, 67, 16));
			jL43.setText("");
			jL42 = new JLabel();
			jL42.setBounds(new Rectangle(154, 148, 59, 17));
			jL42.setText("");
			jL4 = new JLabel();
			jL4.setBounds(new Rectangle(31, 147, 38, 16));
			jL4.setText("4.");
			jL33 = new JLabel();
			jL33.setBounds(new Rectangle(293, 120, 66, 17));
			jL33.setText("");
			jL32 = new JLabel();
			jL32.setBounds(new Rectangle(154, 117, 57, 16));
			jL32.setText("");
			jL3 = new JLabel();
			jL3.setBounds(new Rectangle(33, 116, 39, 15));
			jL3.setText("3.");
			jL23 = new JLabel();
			jL23.setBounds(new Rectangle(293, 86, 69, 17));
			jL23.setText("");
			jL22 = new JLabel();
			jL22.setBounds(new Rectangle(157, 83, 54, 16));
			jL22.setText("");
			jL2 = new JLabel();
			jL2.setBounds(new Rectangle(35, 83, 38, 16));
			jL2.setText("2.");
			jL13 = new JLabel();
			jL13.setBounds(new Rectangle(292, 49, 70, 16));
			jL13.setText("");
			jL12 = new JLabel();
			jL12.setBounds(new Rectangle(156, 52, 55, 15));
			jL12.setText("");
			jL1 = new JLabel();
			jL1.setBounds(new Rectangle(36, 51, 38, 16));
			jL1.setText("1.");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jL1, null);
			jContentPane.add(jL12, null);
			jContentPane.add(jL13, null);
			jContentPane.add(jL2, null);
			jContentPane.add(jL22, null);
			jContentPane.add(jL23, null);
			jContentPane.add(jL3, null);
			jContentPane.add(jL32, null);
			jContentPane.add(jL33, null);
			jContentPane.add(jL4, null);
			jContentPane.add(jL42, null);
			jContentPane.add(jL43, null);
			jContentPane.add(jL5, null);
			jContentPane.add(jL52, null);
			jContentPane.add(j53, null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabe3, null);
			jContentPane.add(jLabel111, null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
