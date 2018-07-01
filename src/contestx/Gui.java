package contestx;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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

import com.sun.xml.internal.ws.api.Component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Gui extends JFrame {

	
	//private ArrayList<Schule> schulen;
	//private ArrayList<Team> dp.getJuniorTeams();
	//private ArrayList<Team> dp.getSeniorTeams();
	//private ArrayList<Judge> judges;
	//private ArrayList<Speaker> speaker;
	private ArrayList<JPanel> debates; //die Debates werden hier vereinfacht als Panels betrachtet; Liste aller im Plan vorkommenden Debates als Panels
	private JPanel contentPane;
	private Debatingplan dp;
	private Verwaltung verwaltung;
	private JButton btnTimezone = new JButton("Timezone 1");
	private JButton btnTimezone_1 = new JButton("Timezone 2");
	private JButton btnTimezone_2 = new JButton("Timezone 3");
	
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	
	private JFrame subFrame; //für alle möglichen Dialogfelder
	private JTextField txtVon;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private final JTextField textField_4 = new JTextField();
	private final JTextField textField_5 = new JTextField();
	private final JTextField textField_6 = new JTextField();
	private final JTextField textField_7 = new JTextField();
	private final JTextField textField_8 = new JTextField();
	private final JTextField textField_9 = new JTextField();
	private final JTextField textField_10 = new JTextField();
	private final JTextField textField_11 = new JTextField();
	private final JLabel label = new JLabel(":");
	private final JLabel label_1 = new JLabel(":");
	private final JLabel label_2 = new JLabel(":");
	private final JLabel label_3 = new JLabel(":");
	private final JLabel label_4 = new JLabel(":");
	private final JLabel label_5 = new JLabel("von:");
	private final JLabel label_6 = new JLabel("bis:");
	private final JLabel label_7 = new JLabel(":");
	private final JLabel label_8 = new JLabel(":");
	private final JLabel label_9 = new JLabel("von:");
	private final JLabel label_10 = new JLabel("bis:");
	
	//Attribute für showEnterPointsDialog (für den ActionListener)
	private Team selectedTeam;

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
		subFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //resettet sich beim Schließen
		
		//dp.getJuniorTeams() = new ArrayList<Team>();
		//dp.getSeniorTeams() = new ArrayList<Team>();
				
		//breakStringIfTooLong(dp.getJuniorTeams().get(3));
		//dp.getSeniorTeams() = new ArrayList<Team>();
		debates = new ArrayList<JPanel>();
		
		dp = new Debatingplan(this);
		verwaltung = new Verwaltung(dp);

		for(int i = 0; i < dp.getSchulen().size(); i++) {
			//dp.getJuniorTeams().add(new Team(dp.getSchulen().get(i), true));
			dp.getSchulen().get(i).getJuniorTeam().setSpeakerAt(0,new Speaker("Tim", dp.getSchulen().get(i).getJuniorTeam()));
			dp.getSchulen().get(i).getJuniorTeam().setSpeakerAt(1,new Speaker("Joe", dp.getSchulen().get(i).getJuniorTeam()));
			dp.getSchulen().get(i).getJuniorTeam().setSpeakerAt(2,new Speaker("Ann", dp.getSchulen().get(i).getJuniorTeam()));
			//dp.getSeniorTeams().add(new Team(dp.getSchulen().get(i), false));
		}
		//schulen problem gelöst
		dp.getSchulen().get(2).getJuniorTeam().setSpeakerAt(3,new Speaker("Hans", dp.getSchulen().get(2).getJuniorTeam()));
		
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
		setBounds(100, 100, 1389, 708);
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
		btnAddJudge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEnterJudgeDialog();
				int i = 0;
			}
		});
		
		//Add-Speaker Button
		JButton btnAddSpeaker = new JButton("Add Speaker");
		btnAddSpeaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showEnterSpeakerDialog();
			}
		});
		
				
		btnAddSchool.setEnabled(true);
		btnAddSchool.setBounds(216, 45, 140, 54);
		contentPane.add(btnAddSchool);
		
		btnAddJudge.setBounds(366, 46, 140, 52);
		contentPane.add(btnAddJudge);
		
		//Implementierung der 3 Panels ohne Border
		panel_2.setBorder(null);
		panel_2.setBounds(194, 449, 734, 100);
		contentPane.add(panel_2);
		
		
		panel_1.setBorder(null);
		panel_1.setBounds(194, 295, 734, 100);
		contentPane.add(panel_1);
		
		
		panel.setBorder(null);
		panel.setBounds(194, 139, 734, 100);
		contentPane.add(panel);
		
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
		
		JButton btnBerechne = new JButton("Calculate with insert data");
		btnBerechne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dp.getSchulen().size()>5) {
					btnTimezone.setEnabled(true);
					btnTimezone_1.setEnabled(true);
					btnTimezone_2.setEnabled(true);
					//btnAddSchool.setEnabled(true);
					btnAddJudge.setEnabled(true);
					//chckbxHatJuniorteam.setEnabled(true);
					//chckbxHatJuniorteam.setEnabled(true);
					panel.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
					
					ArrayList<Debate> debatesJ = berechne(true);
					int dPTjunior = debatesJ.size()/3;
					createRelativeSubpanels(dPTjunior, debatesJ);
					dPTjunior = 0;
				}
			}
		});
		btnBerechne.setBounds(986, 45, 169, 54);
		contentPane.add(btnBerechne);
		
		JButton btnManage = new JButton("Manage");
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manage();
			}
		});
		btnManage.setBounds(701, 32, 156, 81);
		contentPane.add(btnManage);
		btnAddSpeaker.setBounds(516, 47, 140, 51);
		
		contentPane.add(btnAddSpeaker);
		
		JButton btnNewButton = new JButton("Calculate judges");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dp.judgesZuordnen();
			}
		});
		btnNewButton.setBounds(1168, 45, 140, 54);
		contentPane.add(btnNewButton);
		
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
	
	public void createRelativeSubpanels(int debatesPerTime, ArrayList<Debate> array) {
		
		panel.setBounds(panel.getX(), panel.getY(), debatesPerTime*150, panel.getHeight()); //Länge der 3 großen Panels wird entsprechen der Zahl der Debates angepasst
		panel_1.setBounds(panel_1.getX(), panel_1.getY(), debatesPerTime*150, panel_1.getHeight());
		panel_2.setBounds(panel_2.getX(), panel_2.getY(), debatesPerTime*150, panel_2.getHeight());
		cutPanels(debatesPerTime); //Panels werden in Subpanels zerschnitten
		for(int i = 0; i < debatesPerTime*3; i++) {
			debates.add(new JPanel());
			debates.get(i).setBorder(new LineBorder(new Color(0, 0, 0))); //Grenzen werden gezeichnet
			BorderLayout layout = new BorderLayout(1, 1);
			debates.get(i).setLayout(layout); //BorderLayout wird festgelegt
			JButton northB = new JButton("Room Nr. ?");
			debates.get(i).add(northB, BorderLayout.NORTH);
			northB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String s=JOptionPane.showInputDialog("Room Nr."); //wenn der Button gedrückt wird, öffnet sich ein weiteres FEnster in welches man die Raumnummer eingeben kann
					if(s=="")
					{
						JOptionPane.showMessageDialog(subFrame, "No room entered", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						northB.setText("Room Nr. " + s);				
					}
				}
			});
			
			JButton westB = new JButton("<html>Pro<br/>" + array.get(i).getTeamPro().getSchule().getName() + "</html>"); //aus "array" wird der Name des Pro-Teams an i-ter Stelle ausgelesen 
			westB.setHorizontalAlignment(SwingConstants.LEFT);
			debates.get(i).add(westB, BorderLayout.WEST);
			layout.getLayoutComponent(BorderLayout.WEST).setPreferredSize(new Dimension(75, 150)); //die Breite der Buttons wird festgelegt, um Einheitlichkeit zu schaffen
			//TeamPro-button clicked
			westB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int j = 0;
					while(!debates.get(j).getComponent(1).equals(westB)) { //findet den index des entsprechenden array-eintrags zum button 
						j++;
					}
					try {
						if(debatesPerTime > j) showEnterPointsDialog(array.get(j).getTeamPro(), 
								new Zeitzone(Integer.parseInt(txtVon.getText()), Integer.parseInt(textField_3.getText()), 
										 	 Integer.parseInt(textField.getText()), Integer.parseInt(textField_2.getText()), 1)); //Zeitzone 1
					
						else if(j >= debatesPerTime) {
							if(j >= debatesPerTime*2) showEnterPointsDialog(array.get(j).getTeamPro(), 
									new Zeitzone(Integer.parseInt(textField_10.getText()), Integer.parseInt(textField_11.getText()), 
											 	 Integer.parseInt(textField_9.getText()), Integer.parseInt(textField_8.getText()), 3)); //Zeitzone 3
						
							else showEnterPointsDialog(array.get(j).getTeamPro(), 
									new Zeitzone(Integer.parseInt(textField_6.getText()), Integer.parseInt(textField_7.getText()), 
											 	 Integer.parseInt(textField_5.getText()), Integer.parseInt(textField_4.getText()), 2)); //Zeitzone 2
						}
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(subFrame, "You must enter values to the timezones", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			JButton eastB = new JButton("<html>Con<br/>" + array.get(i).getTeamCon().getSchule().getName() + "</html>");
			eastB.setHorizontalAlignment(SwingConstants.LEFT); //Text auf Button soll für maximale Buchstabenaufnahme linksbündig sein (mehrzeilig wird der Anfang der Folgezeilen auf den der obersten gesetzt)
			debates.get(i).add(eastB, BorderLayout.EAST);
			layout.getLayoutComponent(BorderLayout.EAST).setPreferredSize(new Dimension(75, 150)); 
			//TeamPro-button clicked
			eastB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int j = 0;
					while(!debates.get(j).getComponent(2).equals(eastB)) { //findet den index des entsprechenden array-eintrags zum button 
						j++;
					}
					try {
						if(debatesPerTime > j) showEnterPointsDialog(array.get(j).getTeamCon(), 
								new Zeitzone(Integer.parseInt(txtVon.getText()), Integer.parseInt(textField_3.getText()), 
										 	 Integer.parseInt(textField.getText()), Integer.parseInt(textField_2.getText()), 1)); //Zeitzone 1
					
						else if(j >= debatesPerTime) {
							if(j >= debatesPerTime*2) showEnterPointsDialog(array.get(j).getTeamCon(), 
									new Zeitzone(Integer.parseInt(textField_10.getText()), Integer.parseInt(textField_11.getText()), 
											 	 Integer.parseInt(textField_9.getText()), Integer.parseInt(textField_8.getText()), 3)); //Zeitzone 3
						
							else showEnterPointsDialog(array.get(j).getTeamCon(), 
									new Zeitzone(Integer.parseInt(textField_6.getText()), Integer.parseInt(textField_7.getText()), 
											 	 Integer.parseInt(textField_5.getText()), Integer.parseInt(textField_4.getText()), 2)); //Zeitzone 2
						}
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(subFrame, "You must enter values to the timezones", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			    JButton southB = new JButton("");
	            debates.get(i).add(southB, BorderLayout.SOUTH);
	        /*  southB.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent arg0) {
	            		String s=JOptionPane.showInputDialog("Judge"); //wenn der Button gedrückt wird, öffnet sich ein weiteres FEnster in welches man den Judge eingeben kann
	               
	            		boolean e=false;
	            		for(int i=0; i<dp.getJudges().size();i++)
	            		{
	            			if(dp.getJudgeAt(i).getName().equals(s))
	            			{
	            				if(dp.getJudgeAt(i).getErfahren())
	            				{
	            					e=true;
	            				}
	            			}
	            		}
	            		if(e){
	            			southB.setText("Judge" + s);
	            		}
	            		else{
	            			JOptionPane.showMessageDialog(subFrame, "No experienced judge entered", "Error Message", JOptionPane.ERROR_MESSAGE);
	            		}
	                }
	            });
	            */
		}
		for(int i = 0; i < debatesPerTime; i++) { //die in debates gespeicherten, oben modifizierten Panels werden den drei großen Panels hinzugefügt
			panel.add(debates.get(i));
			panel_1.add(debates.get(i+debatesPerTime));
			panel_2.add(debates.get(i+debatesPerTime+debatesPerTime));
		}
	}
	
	public void showEnterSchoolDialog() {
		JCheckBox[] chckbxs = {new JCheckBox("has junior team"), new JCheckBox("has senior team")}; //2 CheckBoxen fragen ab, welche Teams die eingetragene Schule bereitstellt
		chckbxs[0].setSelected(true); //CheckBoxen werden zu Beginn auf true gesetzt (erleichtert schnelle Eingabe)
		chckbxs[1].setSelected(true);
		Object[] options = {"Enter school name:", chckbxs};
		String s = (String)JOptionPane.showInputDialog(subFrame, options); //Schulname wird in "s" gespeichert
		
		if(s != null && s.length() > 0) { //der Fall, dass keine Schule eingegeben wurde wird hier abgefangen
			if(chckbxs[0].isSelected() && chckbxs[1].isSelected()) { //unterschieden wird in der Anzahl der gewählten Checkboxes
				dp.getSchulen().add(new Schule(s, true, true));
				dp.getJuniorTeams().add(new Team(dp.getSchulen().get(dp.getSchulen().size()-1), true)); //die team-listen werden erweitert
				dp.getSeniorTeams().add(new Team(dp.getSchulen().get(dp.getSchulen().size()-1), false));
			}
			else if (chckbxs[0].isSelected()) {
				dp.getSchulen().add(new Schule(s, true, false));
				dp.getJuniorTeams().add(new Team(dp.getSchulen().get(dp.getSchulen().size()-1), true));
			}
			else if (chckbxs[1].isSelected()) {
				dp.getSchulen().add(new Schule(s, false, true));
				dp.getJuniorTeams().add(new Team(dp.getSchulen().get(dp.getSchulen().size()-1), false));
			}
			else {
			//keine Checkbox wurde ausgewählt -> kein Team dieser Schule nimmt teil -> die Schule nimmt nicht teil
				JOptionPane.showMessageDialog(subFrame, "Your school has neither a senior nor a junior team", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		try{
			if(s.length() == 0) { //try/catch, da code Exception erzeugt, die aber nicht weiter relevant ist
				JOptionPane.showMessageDialog(subFrame, "Enter a school name", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(NullPointerException e) {
			
		}
	}
	

	
	public void showEnterJudgeDialog() {
		JCheckBox[] chckbx = {new JCheckBox("is experienced")};
		chckbx[0].setSelected(true);
		JComboBox combo = new JComboBox();

		for(int i = 0; i < dp.getSchulen().size(); i++) {
			combo.addItem(dp.getSchulen().get(i).getName());
		}
		combo.addItem("keine Schule");
		Object[] options = {"Select judge's school:", combo, "Enter judge's name:", chckbx};
		String s = (String)JOptionPane.showInputDialog(subFrame, options);
		boolean judgeAlreadyExisting = false;
		for(int i = 0; i < dp.getJudges().size(); i++) {
			if(dp.getJudgeAt(i).getName().equals(s)) judgeAlreadyExisting = true;
		}
		
		int index = combo.getSelectedIndex();
		if(s != null && !s.contains(",") && !judgeAlreadyExisting) {
			if(s.length() > 0) {
				if(chckbx[0].isSelected()) {
					if(index+1 != combo.getItemCount()) dp.addJudge(new Judge(s, dp.getSchulen().get(index), true));
					else { //wenn "keine Schule" ausgewählt
						dp.addJudge(new Judge(s, true)); //Dummy-Schule noch hinzuzufügen
					}
				}
				else {
					if(index+1 != combo.getItemCount()) dp.addJudge(new Judge(s, dp.getSchulen().get(index), false));
					else {
						dp.addJudge(new Judge(s, false)); //Dummy-Schule noch hinzuzufügen
					}
				}			
			}
			try{ 
				if(s.length() == 0) { //try/catch, da code Exception erzeugt, die aber nicht weiter relevant ist
					JOptionPane.showMessageDialog(subFrame, "No judge name entered.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(NullPointerException e) {
				
			}
		}
		else {
			if(!judgeAlreadyExisting) JOptionPane.showMessageDialog(subFrame, "Judge names can't contain a comma or be empty.", "Error Message", JOptionPane.ERROR_MESSAGE);
			else JOptionPane.showMessageDialog(subFrame, "Judge already exists.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void showEnterSpeakerDialog() {
		Object[] options = {"Enter speaker name:"};
		String s = (String)JOptionPane.showInputDialog(subFrame, options);
		if(s != null && s.length() > 0) {
			dp.getSpeaker().add(new Speaker(s));
		}
		try{ 
			if(s.length() == 0) { //try/catch, da code Exception erzeugt, die aber nicht weiter relevant ist
				JOptionPane.showMessageDialog(subFrame, "No speaker name entered.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(NullPointerException e) {
			
		}
	}
	
	//Methode zur Eingabe der Punkte
	public void showEnterPointsDialog(Team selectedTeam, Zeitzone zeitzone) {
		Speaker[] speakers = new Speaker[selectedTeam.getAllSpeaker().size()];
		String[] speaker_names = new String[speakers.length];
		//speakers und speaker_names haben dieselbe Ordnung und bezeichnen dieselben Speaker
		for(int i = 0; i < speakers.length; i++) { //Speaker-Array wird gefüllt
			speakers[i] = selectedTeam.getAllSpeaker().get(i);
			speaker_names[i] = speakers[i].getName();
		}
		JComboBox[] speaker = {new JComboBox(speaker_names), new JComboBox(speaker_names), new JComboBox(speaker_names), new JComboBox(speaker_names)}; //JLists are to enter the Speaker
		
		JTextField[] points = {new JTextField(), new JTextField(), new JTextField(), new JTextField()}; //JTextFields are to enter the points
		
		JButton[] okCancel = {new JButton("Okay"), new JButton("Cancel")};
		okCancel[0].setBackground(Color.GREEN);
		okCancel[1].setBackground(Color.RED);
		okCancel[1].addActionListener(new ActionListener() { //Cancel-Button
			public void actionPerformed(ActionEvent e) {
				subFrame.dispose(); //resettet subFrame und schließt ihn
			}
		});
		//Attribute für okay-Button
		int[] givenPoints = new int[4]; //speichert die gegebenen Punkte
		Speaker[] takenSpeakers = new Speaker[4];
		for(int i = 0; i < 4; i++) { //takenSpeakers füllen, um NullPointerException zu verhindern
			takenSpeakers[i] = new Speaker("", selectedTeam);
		}
		
		//Okay-Button
		okCancel[0].addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				boolean everythingCorrect = true;
				try {
					for(int i = 0; i < 4; i++) {
						takenSpeakers[i] = new Speaker("", selectedTeam); //takenSpeaker wird resettet, damit bei mehrfachen Eingabeversuchen kein Fehler entsteht
						givenPoints[i] = Integer.parseInt(points[i].getText());
						for(int j = 0; j <= i; j++) { //verhindert doppeltes Eintragen von Speakern
							if(takenSpeakers[j].equals(speakers[speaker[i].getSelectedIndex()]) && i != 3) { //wenn Speaker bereits eingetragen
								everythingCorrect = false;
							}
						}
						takenSpeakers[i] = speakers[speaker[i].getSelectedIndex()];
						if(takenSpeakers[i].getName() == "") { //wenn kein existenter Speaker ausgewählt wurde
							everythingCorrect = false;
						}
					}
					if(everythingCorrect) subFrame.dispose(); //resettet subFrame und schließt ihn
					else {
						JOptionPane.showMessageDialog(subFrame, "You must select 3 different existing Team-Members", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException ex) {
					everythingCorrect = false;
					JOptionPane.showMessageDialog(subFrame, "Not every field was filled correctly.\nNotice: You can't use decimal numbers.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				if(everythingCorrect) {
																				// TRUE UND TRUE ALS TESTWERTE EINGETRAGEN!!!!!!!
						selectedTeam.setPoints(takenSpeakers, givenPoints, zeitzone,true, true); //Punkte in den Teams eintragen 
					for(int i = 0; i < selectedTeam.getAllSpeaker().size(); i++) { //KonsolenAusgabe
						for(int j = 0; j < 6; j++) {
							System.out.println(selectedTeam.getAllSpeaker().get(i).getName() + " " + selectedTeam.getAllSpeaker().get(i).getPunkteIn(j));
						}
					}
				}
			}
		});
		
		//JFrame-Aufbau:
		subFrame.setVisible(true);
		subFrame.setBounds(500, 150, 500, 500);
		JPanel subFrameCP = new JPanel();
		subFrame.setContentPane(subFrameCP);
		subFrameCP.setLayout(new GridLayout(0, 2, 20, 0)); //2 Spalten
		
		JPanel subPanel1 = new JPanel();
		subFrameCP.add(subPanel1);
		subPanel1.setLayout(new GridLayout(5, 0, 0, 20)); //5 Zeilen
		
		JPanel subPanel2 = new JPanel();
		subFrameCP.add(subPanel2);
		subPanel2.setLayout(new GridLayout(5, 0, 0, 20)); //5 Zeilen
		
		for(int i = 0; i < speaker.length; i++) { //fügt JComboBoxes und JTextFields den Panels hinzu
			subPanel1.add(speaker[i]);
			subPanel2.add(points[i]);
		}
		subPanel1.add(okCancel[0]);
		subPanel2.add(okCancel[1]);
	 	
	}
	
	public String breakStringIfTooLong(String s) { //funktioniert noch nicht!
		
		String temp = "";
		char[] str = s.toCharArray();
		int pieces = (int)Math.floor(s.length()/6);
		String[] fin = new String[pieces+2];
		fin[0] = "<html>";
		for(int i = 1; i <= pieces; i++) {
			for(int j = 1; j <= 6; j++) {
				temp = temp + str[i*j-1];
			}
			fin[i] = temp;
			temp = "";
		}
		fin[pieces+1] = "</html>";
		for(int i = 0; i < fin.length; i++){
			System.out.println(fin[i]);
		}
		return fin.toString();
	}
	public ArrayList<Team> getJuniorTeams() {
		return dp.getJuniorTeams();
	}
	public ArrayList<Team> getSeniorTeams() {
		return dp.getSeniorTeams();
	}
	
	public ArrayList<Debate> berechne(boolean junior) {
		//Panels zurücksetzen(mehrfaches drücken)
		panel.removeAll();
		panel_1.removeAll();
		panel_2.removeAll();
		panel.setBorder(null); //der WICHTIGSTE Befehl überhaupt!!!!!!! MUSS UNBEDINGT DA BLEIBEN!!!
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		debates.clear();
		//berechnen lassen
		ArrayList<Debate> array = dp.berechne(junior);
		return array;
	}
	
	public void manage() {
		verwaltung.anzeigen();		
	}
	
	public ArrayList<JPanel> getDebates() {
		return debates;
	}
}
//NEIN NEIN NEIN NEIN NEIN NEIN
