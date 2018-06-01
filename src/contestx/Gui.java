package contestx;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Dimension;

import javax.swing.JTextPane;

public class Gui extends JFrame {

	private JPanel contentPane;
	private ArrayList<String> teams_junior;
	private ArrayList<String> teams_senior;
	private ArrayList<String> judge_experienced;
	private ArrayList<String> judge_unexperienced;
	private ArrayList<JPanel> debates;
	
	private JButton btnNew = new JButton("New");
	private JButton btnTimezone = new JButton("Timezone 1");
	private JButton btnTimezone_1 = new JButton("Timezone 2");
	private JButton btnTimezone_2 = new JButton("Timezone 3");
	
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	
	private JFrame subFrame;
	private JTextField txtVon;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private final JLabel label = new JLabel(":");
	private final JLabel label_1 = new JLabel(":");
	private final JLabel label_2 = new JLabel(":");
	private final JTextField textField_4 = new JTextField();
	private final JLabel label_3 = new JLabel(":");
	private final JTextField textField_5 = new JTextField();
	private final JTextField textField_6 = new JTextField();
	private final JTextField textField_7 = new JTextField();
	private final JLabel label_4 = new JLabel(":");
	private final JLabel label_5 = new JLabel("von:");
	private final JLabel label_6 = new JLabel("bis:");
	private final JTextField textField_8 = new JTextField();
	private final JLabel label_7 = new JLabel(":");
	private final JTextField textField_9 = new JTextField();
	private final JTextField textField_10 = new JTextField();
	private final JTextField textField_11 = new JTextField();
	private final JLabel label_8 = new JLabel(":");
	private final JLabel label_9 = new JLabel("von:");
	private final JLabel label_10 = new JLabel("bis:");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		
		
		subFrame = new JFrame();
		teams_junior = new ArrayList<String>();
		teams_senior = new ArrayList<String>();
		judge_experienced = new ArrayList<String>();
		judge_unexperienced = new ArrayList<String>();
		debates = new ArrayList<JPanel>();
//		debates.add(new JPanel());
	//	debates.get(0).setBorder(new LineBorder(new Color(0, 0, 0)));
	//	debates.get(0).setLayout(new BorderLayout(2, 2));;
	//	debates.get(0).setPreferredSize(new Dimension(100, 100));
	//	debates.get(0).add(new JButton("Hi"), BorderLayout.NORTH);
	//	JButton testbtn = new JButton();
	//	testbtn.setPreferredSize(new Dimension(10, 10));
	//	debates.get(0).add(testbtn, BorderLayout.LINE_END);
	//	debates.get(0).add(new JButton("Hi"), BorderLayout.LINE_START);
	//	//debates.get(0).add(new JButton("Hi"), BorderLayout.CENTER);
	//	debates.get(0).add(new JButton("Hi"), BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1390, 817);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Timezone 1-Button impl.
		btnTimezone.setEnabled(false);
		btnTimezone.setBounds(47, 152, 137, 52);
		contentPane.add(btnTimezone);
		
		//Timezone 2-Button impl.
		btnTimezone_1.setEnabled(false);
		btnTimezone_1.setBounds(47, 309, 137, 52);
		contentPane.add(btnTimezone_1);
		
		//Timezone 3-Button impl.
		btnTimezone_2.setEnabled(false);
		btnTimezone_2.setBounds(47, 463, 137, 52);
		contentPane.add(btnTimezone_2);
		
		
		//Add-SchoolButton
		JButton btnAddSchool = new JButton("AddSchool");
		btnAddSchool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEnterSchoolDialog();
			}
		});
		
		//Add-Judge Button
		JButton btnAddJudge = new JButton("Add Judge");
		btnAddJudge.setEnabled(false);
				btnAddJudge.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						showEnterJudgeDialog();
					}
		});
				
		btnAddSchool.setEnabled(false);
		btnAddSchool.setBounds(368, 28, 140, 54);
		contentPane.add(btnAddSchool);
		
		btnAddJudge.setBounds(526, 29, 140, 52);
		contentPane.add(btnAddJudge);
		
		//Implementierung der 3 Panels ohne Border
		panel_2.setBorder(null);
		panel_2.setBounds(194, 449, 734, 150);
		contentPane.add(panel_2);
		
		
		panel_1.setBorder(null);
		panel_1.setBounds(194, 295, 734, 150);
		contentPane.add(panel_1);
		
		
		panel.setBorder(null);
		panel.setBounds(194, 139, 734, 150);
		contentPane.add(panel);
		
		
		//New-Button macht den Rest der contentPane sichtbar
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//zu füllen
				btnTimezone.setEnabled(true);
				btnTimezone_1.setEnabled(true);
				btnTimezone_2.setEnabled(true);
				btnAddSchool.setEnabled(true);
				btnAddJudge.setEnabled(true);
				//chckbxHatJuniorteam.setEnabled(true);
				//chckbxHatJuniorteam.setEnabled(true);
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
				createRelativeSubpanels(5);
				
			}
		});
		
		btnNew.setBounds(10, 11, 140, 88);
		contentPane.add(btnNew);
		
		txtVon = new JTextField();
		txtVon.setBounds(72, 207, 36, 20);
		contentPane.add(txtVon);
		txtVon.setColumns(10);
		
		JLabel lblVon = new JLabel("von:");
		lblVon.setBounds(47, 207, 46, 14);
		contentPane.add(lblVon);
		
		JLabel lblBis = new JLabel("bis:");
		lblBis.setBounds(51, 235, 46, 14);
		contentPane.add(lblBis);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(72, 235, 36, 20);
		contentPane.add(textField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(119, 235, 36, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(119, 207, 36, 20);
		contentPane.add(textField_3);
		label.setBounds(128, 210, -13, 14);
		
		contentPane.add(label);
		label_1.setBounds(112, 210, 46, 14);
		
		contentPane.add(label_1);
		label_2.setBounds(112, 238, 46, 14);
		
		contentPane.add(label_2);
		textField_4.setColumns(10);
		textField_4.setBounds(119, 391, 36, 20);
		
		contentPane.add(textField_4);
		label_3.setBounds(112, 394, 46, 14);
		
		contentPane.add(label_3);
		textField_5.setColumns(10);
		textField_5.setBounds(72, 391, 36, 20);
		
		contentPane.add(textField_5);
		textField_6.setColumns(10);
		textField_6.setBounds(72, 363, 36, 20);
		
		contentPane.add(textField_6);
		textField_7.setColumns(10);
		textField_7.setBounds(119, 363, 36, 20);
		
		contentPane.add(textField_7);
		label_4.setBounds(112, 366, 46, 14);
		
		contentPane.add(label_4);
		label_5.setBounds(47, 363, 46, 14);
		
		contentPane.add(label_5);
		label_6.setBounds(51, 391, 46, 14);
		
		contentPane.add(label_6);
		textField_8.setColumns(10);
		textField_8.setBounds(119, 545, 36, 20);
		
		contentPane.add(textField_8);
		label_7.setBounds(112, 548, 46, 14);
		
		contentPane.add(label_7);
		textField_9.setColumns(10);
		textField_9.setBounds(72, 545, 36, 20);
		
		contentPane.add(textField_9);
		textField_10.setColumns(10);
		textField_10.setBounds(72, 517, 36, 20);
		
		contentPane.add(textField_10);
		textField_11.setColumns(10);
		textField_11.setBounds(119, 517, 36, 20);
		
		contentPane.add(textField_11);
		label_8.setBounds(112, 520, 46, 14);
		
		contentPane.add(label_8);
		label_9.setBounds(47, 517, 46, 14);
		
		contentPane.add(label_9);
		label_10.setBounds(51, 545, 46, 14);
		
		contentPane.add(label_10);
		
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
	public void createRelativeSubpanels(int debatesPerTime) {
		panel.setBounds(panel.getX(), panel.getY(), debatesPerTime*225, panel.getHeight());
		panel_1.setBounds(panel_1.getX(), panel_1.getY(), debatesPerTime*225, panel_1.getHeight());
		panel_2.setBounds(panel_2.getX(), panel_2.getY(), debatesPerTime*225, panel_2.getHeight());
		cutPanels(debatesPerTime);
		for(int i = 0; i < debatesPerTime*3; i++) {
			debates.add(new JPanel());
			debates.get(i).setBorder(new LineBorder(new Color(0, 0, 0)));
			debates.get(i).setLayout(new BorderLayout(1, 1));;
			debates.get(i).add(new JButton("Room Nr."), BorderLayout.NORTH);
			debates.get(i).add(new JButton("<html>Pro<br/>Leibniz</html>"), BorderLayout.WEST);
			debates.get(i).add(new JButton("Motion"), BorderLayout.CENTER);
			debates.get(i).add(new JButton("<html>Con<br/>Schiller</html>"), BorderLayout.EAST);
			debates.get(i).add(new JButton("Judges"), BorderLayout.SOUTH);
		}
		for(int i = 0; i < debatesPerTime; i++) {
			panel.add(debates.get(i));
			panel_1.add(debates.get(i+debatesPerTime));
			panel_2.add(debates.get(i+debatesPerTime+debatesPerTime));
		}
	}
	public void showEnterSchoolDialog() {
		JCheckBox[] chckbxs = {new JCheckBox("has junior team"), new JCheckBox("has senior team")};
		Object[] options = {"Enter school name:", chckbxs};
		String s = (String)JOptionPane.showInputDialog(subFrame, options);
		if(s != null && s.length() > 0) {
			if(chckbxs[0].isSelected() && chckbxs[1].isSelected()) {
				teams_junior.add(s);
				teams_senior.add(s);
			}
			else if(chckbxs[0].isSelected()) teams_junior.add(s);
			else if(chckbxs[1].isSelected()) teams_senior.add(s);
			else {
				JOptionPane.showMessageDialog(subFrame, "Your school has neither a senior nor a junior team", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	

	
	public void showEnterJudgeDialog() {
		JCheckBox[] chckbx = {new JCheckBox("is experienced")};
		Object[] options = {"Enter judge name:", chckbx};
		String s = (String)JOptionPane.showInputDialog(subFrame, options);
		if(s != null && s.length() > 0) {
			if(chckbx[0].isSelected()) {
				judge_experienced.add(s);
			}
			else {
				judge_unexperienced.add(s);
			}			
		}
		else {
			JOptionPane.showMessageDialog(subFrame, "No judge name entered.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}