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
	private JTextField textField_1;
	private ArrayList<String> teams_junior;
	private ArrayList<String> teams_senior;
	private ArrayList<JPanel> debates;
	
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
	
	private JFrame subFrame;
	private final JButton button = new JButton("Start");
	private final JButton button_1 = new JButton("Start");
	private final JButton button_2 = new JButton("End");
	private final JButton button_3 = new JButton("End");

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
		
		subFrame = new JFrame();
		teams_junior = new ArrayList<String>();
		teams_senior = new ArrayList<String>();
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
		btnTimezone.setBounds(42, 186, 137, 52);
		contentPane.add(btnTimezone);
		
		//Timezone 2-Button impl.
		btnTimezone_1.setEnabled(false);
		btnTimezone_1.setBounds(42, 343, 137, 52);
		contentPane.add(btnTimezone_1);
		
		//Timezone 3-Button impl.
		btnTimezone_2.setEnabled(false);
		btnTimezone_2.setBounds(42, 497, 137, 52);
		contentPane.add(btnTimezone_2);
		
		
		//anzupassen (
		JButton btnAddSchool = new JButton("Add");
		btnAddSchool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEnterSchoolDialog();
			}
		});
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
		panel_2.setBounds(194, 449, 734, 150);
		contentPane.add(panel_2);
		
		
		panel_1.setBorder(null);
		panel_1.setBounds(194, 295, 734, 150);
		contentPane.add(panel_1);
		
		
		panel.setBorder(null);
		panel.setBounds(194, 139, 734, 150);
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
				createRelativeSubpanels(5);
				
			}
		});
		
		btnNew.setBounds(10, 11, 140, 88);
		contentPane.add(btnNew);
		
		btnCreate.setBounds(964, 32, 157, 54);
		contentPane.add(btnCreate);
		
		JButton btnStarttime = new JButton("Start");
		btnStarttime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tfh = new JTextField();
				
				JTextField tfm = new JTextField();
				JLabel labelh = new JLabel("Enter hour:");
				JLabel labelm = new JLabel("Enter minute:");
				Object[] options={labelh,tfh, labelm, tfm };
				JOptionPane.showOptionDialog(subFrame, "Enter Timezone:", "Enter Timezone", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
			}
		});
		btnStarttime.setBounds(37, 249, 66, 23);
		contentPane.add(btnStarttime);
		
		JButton btnNewButton = new JButton("End");
		btnNewButton.setBounds(113, 249, 66, 23);
		contentPane.add(btnNewButton);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(37, 406, 66, 23);
		
		contentPane.add(button);
		button_1.setBounds(37, 560, 66, 23);
		
		contentPane.add(button_1);
		button_2.setBounds(113, 406, 66, 23);
		
		contentPane.add(button_2);
		button_3.setBounds(113, 560, 66, 23);
		
		contentPane.add(button_3);
		
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
}
