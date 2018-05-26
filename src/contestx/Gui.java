package contestx;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;
import javax.swing.JTextPane;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private ArrayList<String> schools;
	
	private JButton btnNew = new JButton("New");
	private JButton btnTimezone = new JButton("Timezone 1");
	private JButton btnTimezone_1 = new JButton("Timezone 2");
	private JButton btnTimezone_2 = new JButton("Timezone 3");
	private JButton btnCreate = new JButton("Create");
	
	private JCheckBox chckbxIstsenior = new JCheckBox("has Senior-Team");
	private JCheckBox chckbxHatJuniorteam = new JCheckBox("has Junior-Team");
	
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		schools = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Timezone 1-Button impl.
		btnTimezone.setEnabled(false);
		btnTimezone.setBounds(42, 180, 137, 52);
		contentPane.add(btnTimezone);
		
		//Timezone 2-Button impl.
		btnTimezone_1.setEnabled(false);
		btnTimezone_1.setBounds(42, 337, 137, 52);
		contentPane.add(btnTimezone_1);
		
		//Timezone 3-Button impl.
		btnTimezone_2.setEnabled(false);
		btnTimezone_2.setBounds(42, 492, 137, 52);
		contentPane.add(btnTimezone_2);
		
		
		//anzupassen (
		JButton btnAddSchool = new JButton("Add School");
		btnAddSchool.setEnabled(false);
		btnAddSchool.setBounds(642, 32, 140, 54);
		contentPane.add(btnAddSchool);
		
		JButton btnAddSpeaker = new JButton("Add Speaker");
		btnAddSpeaker.setBounds(42, 616, 104, 23);
		contentPane.add(btnAddSpeaker);
		
		JButton btnAddJudge = new JButton("Add Judge");
		btnAddJudge.setBounds(42, 650, 104, 23);
		contentPane.add(btnAddJudge);
		// )
		
		//checkBoxes impl.
		chckbxIstsenior.setBounds(793, 32, 165, 23);
		chckbxIstsenior.setEnabled(false);
		contentPane.add(chckbxIstsenior);
		
		
		chckbxHatJuniorteam.setBounds(793, 63, 165, 23);
		chckbxHatJuniorteam.setEnabled(false);
		contentPane.add(chckbxHatJuniorteam);
		
		//Implementierung der 3 Panels ohne Border
		panel_2.setBorder(null);
		panel_2.setBounds(194, 449, 734, 138);
		contentPane.add(panel_2);
		
		
		panel_1.setBorder(null);
		panel_1.setBounds(194, 295, 734, 138);
		contentPane.add(panel_1);
		
		
		panel.setBorder(null);
		panel.setBounds(194, 139, 734, 138);
		contentPane.add(panel);
		
		
		//Eingabe-Feld für Schulen
		textField_1 = new JTextField();
		textField_1.setBounds(191, 32, 435, 54);
		textField_1.setEnabled(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		//New-Button macht den Rest der contentPane sichtbar
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//zu füllen
				btnTimezone.setEnabled(true);
				btnTimezone_1.setEnabled(true);
				btnTimezone_2.setEnabled(true);
				btnAddSchool.setEnabled(true);
				textField_1.setEnabled(true);
				chckbxHatJuniorteam.setEnabled(true);
				chckbxHatJuniorteam.setEnabled(true);
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			}
		});
		
		btnNew.setBounds(10, 11, 140, 88);
		contentPane.add(btnNew);
		
		btnCreate.setBounds(964, 32, 157, 54);
		contentPane.add(btnCreate);
		cutPanels(10);
	} //IDEE: Debates könnten als JTextPanes angezeigt werden und die Klasse "Debate" die teilnehmenden Teams, Generation, Judges und Raum als String ausgeben, der dort zentriert eingetragen wird.
	  //2. IDEE: Debates könnten als weiteres Panel im BoxLayout angezeigt werden. Dort hinein könnten dann JButtons gesetzt werden, die beim "hovern" weitere Infos anzeigen..
	
	public void cutPanels(int pieces) {
		panel.setLayout(new GridLayout(0, pieces, 0, 0));
		panel_1.setLayout(new GridLayout(0, pieces, 0, 0));
		panel_2.setLayout(new GridLayout(0, pieces, 0, 0));
	}
	public void centerText(JTextPane tp) {
		StyledDocument doc = tp.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}
}
