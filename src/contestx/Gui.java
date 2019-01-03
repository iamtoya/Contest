package contestx;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import org.eclipse.swt.SWT;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.ListCellRenderer;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ScrollPaneConstants;
/**
 * Still existing errors:
 * TODO: "Keine Schule" vielleicht umbenennen in "andere" ?
 * Panels und Berechnen nur für Junior-Teams
 * judgeZuordnen: erfahrene Zuordnung und restl. Zuordnung kommunizieren nicht!
 * Daten Speichern und Laden (außer das Bild des Plans) 
 * @author Games, Andi, Jupp
 *
 */

public class Gui extends JFrame {

	private static final long serialVersionUID = -7824597793488283555L;
	//private ArrayList<Schule> schulen;
	//private ArrayList<Team> dp.getJuniorTeams();
	//private ArrayList<Team> dp.getSeniorTeams();
	//private ArrayList<Judge> judges;
	//private ArrayList<Speaker> speaker;
	private ArrayList<JPanel> debates; //die Debates werden hier vereinfacht als Panels betrachtet; Liste aller im Plan vorkommenden Debates als Panels: JUNIOR VOR SENIOR!!
	private JPanel contentPane;
	private Debatingplan dp;
	private Verwaltung verwaltung;
	private JButton btnTimezone = new JButton("Motion 1");
	private JButton btnTimezone_1 = new JButton("Motion 2");
	private JButton btnTimezone_2 = new JButton("Motion 3");
	
	private JPanel panel = new JPanel();
	private JPanel panel_1 = new JPanel();
	private JPanel panel_2 = new JPanel();
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	private final JPanel panel_5 = new JPanel();
	
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
	
	private int standard_width;
	
	private final JButton btnFirstPlace = new JButton("first place");
	private final JButton btnSecondPlace = new JButton("second place");
	private final JButton btnNewButton_2 = new JButton("third place");
	private final JButton btnFindBestTeams = new JButton("Find best teams");
	private final JButton button_1 = new JButton("first place");
	private final JButton button_2 = new JButton("second place");
	private final JButton button_3 = new JButton("third place");
	private DefaultListModel listModel = new DefaultListModel();
	private final JList list = new JList(listModel);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch(Exception e) {
			e.printStackTrace();
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
			dp.getSchulen().get(i).getJuniorTeam().addSpeaker(new Speaker("Tim", dp.getSchulen().get(i).getJuniorTeam(), 1));
			dp.getSchulen().get(i).getJuniorTeam().addSpeaker(new Speaker("Joe", dp.getSchulen().get(i).getJuniorTeam(), 1));
			dp.getSchulen().get(i).getJuniorTeam().addSpeaker(new Speaker("Ann", dp.getSchulen().get(i).getJuniorTeam(), 1));
			//dp.getSeniorTeams().add(new Team(dp.getSchulen().get(i), false));
		}
		//schulen problem gelöst
		dp.getSchulen().get(2).getJuniorTeam().addSpeaker(new Speaker("Hans", dp.getSchulen().get(2).getJuniorTeam(), 1));
		dp._speakerDummys(); //fügt Speaker aus Schulen zur >dp.speaker<-Liste hinzu
		
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
		setBounds(100, 100, 1389, 1060);
		standard_width = this.getWidth();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(255, 255, 255));
		
		
		//Timezone 1-Button impl.
		btnTimezone.setEnabled(true);
		btnTimezone.setBounds(446, 139, 137, 99);
		btnTimezone.setBackground(new Color(255, 255, 255));
		btnTimezone.setContentAreaFilled(false);
		btnTimezone.setOpaque(true);
		btnTimezone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnTimezone.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(btnTimezone);
		btnTimezone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = (String)JOptionPane.showInputDialog("Enter a new motion");
				if(!(s == null)) {
					if(!(s.length() == 0)) {
						dp.setMotion(s, 0);
						btnTimezone.setText("<html><center>" + s + "</center></html>");
					}
				}
			}
		});
		
		
		//Timezone 2-Button impl.
		btnTimezone_1.setEnabled(true);
		btnTimezone_1.setBounds(446, 392, 137, 100);
		btnTimezone_1.setBackground(new Color(255, 255, 255));
		btnTimezone_1.setContentAreaFilled(false);
		btnTimezone_1.setOpaque(true);
		btnTimezone_1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnTimezone_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(btnTimezone_1);
		btnTimezone_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = (String)JOptionPane.showInputDialog("Enter a new motion");
				if(!(s == null)) {
					if(!(s.length() == 0)) {
						dp.setMotion(s, 1);
						btnTimezone_1.setText("<html><center>" + s + "</center></html>");
					}
				}
			}
		});
		
		//Timezone 3-Button impl.
		btnTimezone_2.setEnabled(true);
		btnTimezone_2.setBounds(446, 645, 137, 100);
		btnTimezone_2.setBackground(new Color(255, 255, 255));
		btnTimezone_2.setContentAreaFilled(false);
		btnTimezone_2.setOpaque(true);
		btnTimezone_2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		btnTimezone_2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(btnTimezone_2);
		btnTimezone_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = (String)JOptionPane.showInputDialog("Enter a new motion");
				if(!(s == null)) {
					if(!(s.length() == 0)) {
						dp.setMotion(s, 2);
						btnTimezone_2.setText("<html><center>" + s + "</center></html>");
					}
				}
			}
		});
		
		
		//Add-SchoolButton
		JButton btnAddSchool = new JButton("AddSchool");
		btnAddSchool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showEnterSchoolDialog(null);
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
		btnAddSpeaker.setEnabled(false);
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
		panel_2.setBounds(598, 645, 734, 100);
		panel_2.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_2);
		
		
		panel_1.setBorder(null);
		panel_1.setBounds(598, 392, 734, 100);
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1);
		
		
		panel.setBorder(null);
		panel.setBounds(598, 139, 734, 100);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		
		panel_3.setBorder(null);
		panel_3.setBounds(598, 255, 734, 100);
		panel_3.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_3);
		
		panel_4.setBorder(null);
		panel_4.setBounds(598, 508, 734, 100);
		panel_4.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_4);
		
		panel_5.setBorder(null);
		panel_5.setBounds(598, 761, 734, 100);
		panel_5.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_5);
		
		txtVon = new JTextField();
		txtVon.setBounds(492, 254, 36, 20);
		contentPane.add(txtVon);
		txtVon.setColumns(10);
		
		JLabel lblVon = new JLabel("von:");
		lblVon.setBounds(456, 254, 46, 14);
		contentPane.add(lblVon);
		
		JLabel lblBis = new JLabel("bis:");
		lblBis.setBounds(460, 282, 46, 14);
		contentPane.add(lblBis);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(492, 282, 36, 20);
		contentPane.add(textField);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(539, 282, 36, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(539, 254, 36, 20);
		contentPane.add(textField_3);
		label.setBounds(548, 257, -13, 14);
		
		contentPane.add(label);
		label_1.setBounds(532, 257, 46, 14);
		
		contentPane.add(label_1);
		label_2.setBounds(532, 285, 46, 14);
		
		contentPane.add(label_2);
		textField_4.setColumns(10);
		textField_4.setBounds(544, 536, 36, 20);
		
		contentPane.add(textField_4);
		label_3.setBounds(537, 539, 46, 14);
		
		contentPane.add(label_3);
		textField_5.setColumns(10);
		textField_5.setBounds(497, 536, 36, 20);
		
		contentPane.add(textField_5);
		textField_6.setColumns(10);
		textField_6.setBounds(497, 508, 36, 20);
		
		contentPane.add(textField_6);
		textField_7.setColumns(10);
		textField_7.setBounds(544, 508, 36, 20);
		
		contentPane.add(textField_7);
		label_4.setBounds(537, 511, 46, 14);
		
		contentPane.add(label_4);
		label_5.setBounds(461, 508, 46, 14);
		
		contentPane.add(label_5);
		label_6.setBounds(465, 536, 46, 14);
		
		contentPane.add(label_6);
		textField_8.setColumns(10);
		textField_8.setBounds(544, 789, 36, 20);
		
		contentPane.add(textField_8);
		label_7.setBounds(537, 792, 46, 14);
		
		contentPane.add(label_7);
		textField_9.setColumns(10);
		textField_9.setBounds(497, 789, 36, 20);
		
		contentPane.add(textField_9);
		textField_10.setColumns(10);
		textField_10.setBounds(497, 761, 36, 20);
		
		contentPane.add(textField_10);
		textField_11.setColumns(10);
		textField_11.setBounds(544, 761, 36, 20);
		
		contentPane.add(textField_11);
		label_8.setBounds(537, 764, 46, 14);
		
		contentPane.add(label_8);
		label_9.setBounds(461, 761, 46, 14);
		
		contentPane.add(label_9);
		label_10.setBounds(465, 789, 46, 14);
		
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
					panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
					panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
					
					ArrayList<Debate> debatesJ = berechne(true, false); //für Junior-Teams berechnen
					ArrayList<Debate> debatesS = berechne(false, true); //für Senior-Teams berechnen
					int dPTjunior = debatesJ.size() / 3;
					int dPTsenior = debatesS.size() / 3;
					
					//Für separate Panel:
					JPanel[] panels = new JPanel[3];
					panels[0] = panel;
					panels[1] = panel_1;
					panels[2] = panel_2;
					createRelativeSubpanels(dPTjunior, debatesJ, panels);
					panels[0] = panel_3;
					panels[1] = panel_4;
					panels[2] = panel_5;
					createRelativeSubpanels(dPTsenior, debatesS, panels);
					
					//Für ein gemeinsames Panel:
					/*ArrayList<Debate> debatesJS = new ArrayList<Debate>();
					boolean senior = true;
					for(int i = 0; i < 3; i++) {
						senior = !senior;
						if(!senior) {
							for(int j = 0; j < dPTjunior; j++) {
								debatesJS.add(debatesJ.get((i * dPTjunior) + j));
							}
							i--;
						}
						else {
							for(int j = 0; j < dPTsenior; j++) {
								debatesJS.add(debatesS.get((i * dPTsenior) + j));
							}
						}
					}
					int dPT = debatesJS.size() / 3;
					createRelativeSubpanels(dPT, debatesJS);*/
				}
			}
		});
		btnBerechne.setBounds(671, 45, 209, 54);
		contentPane.add(btnBerechne);
		
		JButton btnManage = new JButton("Manage");
		btnManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manage();
			}
		});
		btnManage.setBounds(28, 32, 156, 81);
		contentPane.add(btnManage);
		btnAddSpeaker.setBounds(516, 47, 140, 51);
		
		contentPane.add(btnAddSpeaker);
		
		JButton btnNewButton = new JButton("Calculate judges");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dp.judgesZuordnen2()) {
					int dpt = (dp.getJuniorDebates().size()/3) + (dp.getSeniorDebates().size()/3);
					Judge[][] cjudges1 = dp.getCalculatedJudges(1);
					Judge[][] cjudges2 = dp.getCalculatedJudges(2);
					Judge[][] cjudges3 = dp.getCalculatedJudges(3);
					debates = getDebates(); //debates aktualisieren
					for(int i = 0; i < debates.size(); i++) {
						JButton b = (JButton) debates.get(i).getComponent(3);
						b.setText("");
					}
					for(int i = 0; i < dpt; i++) {
						JButton b1 = (JButton) debates.get(i).getComponent(3);
						JButton b2 = (JButton) debates.get(i + dpt).getComponent(3);
						JButton b3 = (JButton) debates.get(i + (2*dpt)).getComponent(3);
						for(int j = 0; j < 3; j++) {
							if(j == 0) {
								b1.setText(b1.getText() + cjudges1[i][j].getName());
								b2.setText(b2.getText() + cjudges2[i][j].getName());
								b3.setText(b3.getText() + cjudges3[i][j].getName());
							}
							else {
								b1.setText(b1.getText() + ", " + cjudges1[i][j].getName());
								b2.setText(b2.getText() + ", " + cjudges2[i][j].getName());
								b3.setText(b3.getText() + ", " + cjudges3[i][j].getName());
							}
						}
					}
					System.out.println("zz1 has duplicate " + hasDuplicate(dp.getCalculatedJudges(1))); //should be false
					System.out.println("zz2 has duplicate " + hasDuplicate(dp.getCalculatedJudges(2))); //should be false
					System.out.println("zz3 has duplicate " + hasDuplicate(dp.getCalculatedJudges(3))); //should be false
				}
			}
		});
		btnNewButton.setBounds(895, 45, 147, 54);
		contentPane.add(btnNewButton);
		
		JButton btnSave = new JButton("Export");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SWTdialog d = new SWTdialog(SWT.SAVE);
				
				File f = d.setFile();
				if(f != null) imagescreen(f);
			}
		});
		btnSave.setBounds(1057, 45, 130, 54);
		contentPane.add(btnSave);
		
		JButton btnSavePlan = new JButton("Save plan");
		btnSavePlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SWTdialog d = new SWTdialog(SWT.SAVE);
				String path = d.open();
				if(path != null) {
					writeToFile(dp, path);
					refresh();
				}
			}
		});
		btnSavePlan.setBounds(1202, 15, 130, 39);
		contentPane.add(btnSavePlan);
		
		JButton btnLoadPlan = new JButton("Load plan");
		btnLoadPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SWTdialog s = new SWTdialog(SWT.OPEN);
				String path = s.open();
				
				if(path != null) {
					dp = (Debatingplan) readFromFile(path);
					if(dp != null) {
						//Panels zurücksetzen
						panel.removeAll();
						panel_1.removeAll();
						panel_2.removeAll();
						panel_3.removeAll();
						panel_4.removeAll();
						panel_5.removeAll();
						panel.setBorder(null); //der WICHTIGSTE Befehl überhaupt!!!!!!! MUSS UNBEDINGT DA BLEIBEN!!!
						panel.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
						panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
						debates.clear();
						
						setDPGui(); //im gespeicherten dp ist ebenfalls ein GUI referenziert, doch es ist nicht dieses hier
						verwaltung.setDP(dp); //für Verwaltung gilt ähnliches mit dem Debatingplan
						
						listModel.clear();
						for(int i = 0; i < dp.getSchulen().size(); i++) { //Schulen-Liste neu berechnen
							if(!(dp.getSchulen().get(i).getName().equals("other"))) listModel.addElement(dp.getSchulen().get(i));
						}
						
						btnTimezone.setText("<html><center>" + dp.getMotion(0) + "</center></html>");
						btnTimezone_1.setText("<html><center>" + dp.getMotion(1) + "</center></html>");
						btnTimezone_2.setText("<html><center>" + dp.getMotion(2) + "</center></html>");
						
						if(!(dp.zeitzone1.getStartHours() == 0)) txtVon.setText("" + dp.zeitzone1.getStartHours());
						if(!(dp.zeitzone1.getStartMins() == 0)) textField_3.setText("" + dp.zeitzone1.getStartMins());
						if(!(dp.zeitzone1.getEndHours() == 0)) textField.setText("" + dp.zeitzone1.getEndHours());
						if(!(dp.zeitzone1.getEndMins() == 0)) textField_2.setText("" + dp.zeitzone1.getEndMins());
						
						if(!(dp.zeitzone3.getStartHours() == 0)) textField_10.setText("" + dp.zeitzone3.getStartHours());
						if(!(dp.zeitzone3.getStartMins() == 0)) textField_11.setText("" + dp.zeitzone3.getStartMins());
						if(!(dp.zeitzone3.getEndHours() == 0)) textField_9.setText("" + dp.zeitzone3.getEndHours());
						if(!(dp.zeitzone3.getEndMins() == 0)) textField_8.setText("" + dp.zeitzone3.getEndMins());
						
						if(!(dp.zeitzone2.getStartHours() == 0)) textField_6.setText("" + dp.zeitzone2.getStartHours());
						if(!(dp.zeitzone2.getStartMins() == 0)) textField_7.setText("" + dp.zeitzone2.getStartMins());
						if(!(dp.zeitzone2.getEndHours() == 0)) textField_5.setText("" + dp.zeitzone2.getEndHours());
						if(!(dp.zeitzone2.getEndMins() == 0)) textField_4.setText("" + dp.zeitzone2.getEndMins());
						
						
						ArrayList<Debate> debatesJ = dp.getJuniorDebates();
						ArrayList<Debate> debatesS = dp.getSeniorDebates();
						int dPTjunior = debatesJ.size() / 3;
						int dPTsenior = debatesS.size() / 3;
						//Für separate Panel:
						JPanel[] panels = new JPanel[3];
						panels[0] = panel;
						panels[1] = panel_1;
						panels[2] = panel_2;
						createRelativeSubpanels(dPTjunior, debatesJ, panels);
						panels[0] = panel_3;
						panels[1] = panel_4;
						panels[2] = panel_5;
						createRelativeSubpanels(dPTsenior, debatesS, panels);
					}
				}
			}
		});
		btnLoadPlan.setBounds(1202, 70, 130, 29);
		
		contentPane.add(btnLoadPlan);
		

		
		btnFirstPlace.setBounds(821, 912, 110, 23);
		
		contentPane.add(btnFirstPlace);
		btnSecondPlace.setBounds(821, 939, 110, 23);
		
		contentPane.add(btnSecondPlace);
		btnNewButton_2.setBounds(821, 965, 110, 23);
		
		contentPane.add(btnNewButton_2);
		btnFindBestTeams.setBounds(959, 939, 123, 39);
		btnFindBestTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dp.bestTeams2(true);
				dp.bestTeams2(false);
				
				Team[] winnerTeams = dp.getBestTeams(true);
				JTextArea textField = new JTextArea();
				try {
					textField.append("First junior-team with " + winnerTeams[0].getWinAmount() + " wins and " + winnerTeams[0].getHoechstPunkte() + " maximum points:\n");
					textField.append("\t" + winnerTeams[0].getSchule().getName() + "\n");
					textField.append("Second junior-team with " + winnerTeams[1].getWinAmount() + " wins and " + winnerTeams[1].getHoechstPunkte() + " maximum points:\n");
					textField.append("\t" + winnerTeams[1].getSchule().getName() + "\n");
					textField.append("Third junior-team with " + winnerTeams[2].getWinAmount() + " wins and " + winnerTeams[2].getHoechstPunkte() + " maximum points:\n");
					textField.append("\t" + winnerTeams[2].getSchule().getName() + "\n");
					
					winnerTeams = dp.getBestTeams(false);
					textField.append("First senior-team with " + winnerTeams[0].getWinAmount() + " wins and " + winnerTeams[0].getHoechstPunkte() + " maximum points:\n");
					textField.append("\t" + winnerTeams[0].getSchule().getName() + "\n");
					textField.append("Second senior-team with " + winnerTeams[1].getWinAmount() + " wins and " + winnerTeams[1].getHoechstPunkte() + " maximum points:\n");
					textField.append("\t" + winnerTeams[1].getSchule().getName() + "\n");
					textField.append("Third senior-team with " + winnerTeams[2].getWinAmount() + " wins and " + winnerTeams[2].getHoechstPunkte() + " maximum points:\n");
					textField.append("\t" + winnerTeams[2].getSchule().getName() + "\n");
					JScrollPane sp = new JScrollPane(textField);
					JOptionPane.showMessageDialog(subFrame, sp, "Best Speaker", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Please first calculate teams.", "Error: No teams found", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		contentPane.add(btnFindBestTeams);
		button_1.setBounds(1092, 912, 110, 23);
		
		contentPane.add(button_1);
		button_2.setBounds(1092, 939, 110, 23);
		
		contentPane.add(button_2);
		button_3.setBounds(1092, 965, 110, 23);
		
		contentPane.add(button_3);
		
		JButton btnNewButton_1 = new JButton("Find best speakers");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					dp.bestSpeaker2();
					btnFirstPlace.setText(dp.getErsterSpeaker().get(0).getName());
					//oder:
					JTextArea textField = new JTextArea();
					textField.append("Speaker with highest score (" + dp.getErsterSpeaker().get(0).getHoechstePunkte() + "):\n");
					for(int i = 0; i < dp.getErsterSpeaker().size(); i++) {
						textField.append("\t" + dp.getErsterSpeaker().get(i).getName() + "\n");
					}
					if(dp.getZweiterSpeaker().size() > 0) textField.append("Speaker with second highest score (" + dp.getZweiterSpeaker().get(0).getHoechstePunkte() + "):\n");
					for(int i = 0; i < dp.getZweiterSpeaker().size(); i++) {
						textField.append("\t" + dp.getZweiterSpeaker().get(i).getName() + "\n");
					}
					if(dp.getDritterSpeaker().size() > 0) textField.append("Speaker with third highest score (" + dp.getDritterSpeaker().get(0).getHoechstePunkte() + "):\n");
					for(int i = 0; i < dp.getDritterSpeaker().size(); i++) {
						textField.append("\t" + dp.getDritterSpeaker().get(i).getName() + "\n");
					}
					
					JScrollPane sp = new JScrollPane(textField);
					JOptionPane.showMessageDialog(subFrame, sp, "Best Speaker", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(IndexOutOfBoundsException ind)
				{
					throw ind;
					//JOptionPane.showMessageDialog(null, "Please add Speakers to all teams", "Speakers not found", JOptionPane.ERROR_MESSAGE);;
				}
			}
		});
		btnNewButton_1.setBounds(688, 939, 123, 39);
		contentPane.add(btnNewButton_1);
		
		//listScroller.setPreferredSize(new Dimension(250, 80));
		//listScroller.setViewportView(list);
		
		list.setBounds(38, 139, 375, 721);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setFixedCellHeight(50);
		list.setVisibleRowCount(6);
		list.setBackground(new Color(248, 248, 248));
		list.setCellRenderer(new Renderer());
		SchoolMenu schoolmenu = new SchoolMenu();
		
		MouseListener mouseListener = new MouseListener() {
			public void mouseClicked(MouseEvent e) { 
				if(e.getButton() == MouseEvent.BUTTON3) { //right click
					list.setSelectedIndex(list.locationToIndex(e.getPoint()));
					schoolmenu.show(list, e.getX(), e.getY());
				}
			}
			public void mouseEntered(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		};
		list.addMouseListener(mouseListener);
		contentPane.add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(38, 139, 375, 722);
		contentPane.add(scrollPane);
		
		JButton btnExportScores = new JButton("<html><center>Export Scores</center></html>");
		btnExportScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SWTdialog s = new SWTdialog(SWT.OPEN);
				String path = s.open();
				try {
					exportScores(path);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExportScores.setBounds(1217, 912, 115, 76);
		contentPane.add(btnExportScores);
		for(int i = 0; i < dp.getSchulen().size(); i++) {
			if(!(dp.getSchulen().get(i).getName() == "other")) listModel.addElement(dp.getSchulen().get(i));
		}
		
	} //IDEE: Debates könnten als JTextPanes angezeigt werden und die Klasse "Debate" die teilnehmenden Teams, Generation, Judges und Raum als String ausgeben, der dort zentriert eingetragen wird.
	  //2. IDEE: Debates könnten als weiteres Panel im BoxLayout angezeigt werden. Dort hinein könnten dann JButtons gesetzt werden, die beim "hovern" weitere Infos anzeigen..
	
	public void imagescreen(File f) {
		//Versuch des Speicherns des Frames als Bild
		int h = panel_5.getHeight() + (panel_5.getY()-panel.getY()) + 10;
		int width = 0;
		//je nachdem ob es mehr junior- oder senior-debates gibt
		if(panel.getWidth() >= panel_5.getWidth()) width = panel.getWidth() + (panel.getX()-btnTimezone.getX()) + 10;
		else width = panel_5.getWidth() + (panel_5.getX()-btnTimezone.getX()) + 10;
		
		
		Rectangle r = new Rectangle(btnTimezone.getX()-5, panel.getY()-5, width, h);
		BufferedImage bi = ScreenImage.createImage(contentPane, r);
		try {
			ScreenImage.writeImage(bi, f, false);
		} catch (IOException e1) {
			if(!e1.getMessage().equals("File already exists.")) JOptionPane.showMessageDialog(subFrame, e1.getMessage());
			else {
				int i = JOptionPane.showConfirmDialog(subFrame, "File already exists. Override it?");
				if(i == JOptionPane.YES_OPTION) { //wenn ja gewählt
					try {
						ScreenImage.writeImage(bi, f, true);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(subFrame, e1.getMessage());
					}
				}
			}
		}
	}
	public void cutPanels(int pieces, JPanel[] panels) {
		for(int i = 0; i < panels.length; i++) {
			panels[i].setLayout(new GridLayout(0, pieces, 0, 0));
		}
	}
	public void centerText(JTextPane tp) {
		StyledDocument doc = tp.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
	}
	
	public void createRelativeSubpanels(int debatesPerTime, ArrayList<Debate> array, JPanel[] panels) {
		System.out.println("Component-Count before: " + panel.getComponentCount());
		//Länge der 3 großen Panels wird entsprechen der Zahl der Debates
		//und der empfohlenen Panel-Breite entsprechend des längsten Namens angepasst
		Font f = new Font("Tahoma", Font.PLAIN, 16);
		FontMetrics m = btnTimezone.getFontMetrics(f); //nur zufällig btnTimezone gewählt, hätte auch jeder andere Component mit Font-Metrics sein können
		int width = dp.getRecommendedPanelWidth(m);
		/*while(width == -1) { //Error Code: String zu lang
			f = new Font("Tahoma", Font.PLAIN, f.getSize()-1);
			width = dp.getRecommendedPanelWidth(btnTimezone.getFontMetrics(f));
		}*/
		System.out.println(width);
		//Länge der Panels, in denen die Debates angezeigt werden sollen, wird festgelegt
		for(int i = 0; i < panels.length; i++) {
			panels[i].setBounds(panels[i].getX(), panels[i].getY(), debatesPerTime*width, 110);
		}
		cutPanels(debatesPerTime, panels); //Panels werden in Subpanels zerschnitten
		ArrayList<JPanel> panel_list = new ArrayList<JPanel>();
		for(int i = 0; i < debatesPerTime*3; i++) { //die einzelnen Subpanels werden >debates< hinzugefügt (inklusive Layout/Beschriftung)
			panel_list.add(new JPanel());
			panel_list.get(i).setBorder(new LineBorder(new Color(0, 0, 0))); //Grenzen werden gezeichnet
			BorderLayout layout = new BorderLayout(1, 1);
			panel_list.get(i).setLayout(layout); //BorderLayout wird festgelegt
			JButton northB = new JButton("Room Nr. " + array.get(i).getRaum());
			panel_list.get(i).add(northB, BorderLayout.NORTH);
			layout.getLayoutComponent(BorderLayout.NORTH).setPreferredSize(new Dimension(width, 22)); 
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
			
			JButton westB = new JButton("<html><b>Pro</b><br/>" + array.get(i).getTeamPro().getSchule().getName().replaceAll("-", "- ") + "</html>"); //aus "array" wird der Name des Pro-Teams ausgelesen 
			westB.setHorizontalAlignment(SwingConstants.LEFT);
			Font individualF = new Font("Tahoma", Font.PLAIN, 16);
			while(dp.getPanelWidth(array.get(i).getTeamPro().getSchule().getName(), westB.getFontMetrics(individualF)) == 250) {
				individualF = new Font("Tahoma", Font.PLAIN, individualF.getSize()-1);
			}
			westB.setFont(individualF);
			panel_list.get(i).add(westB, BorderLayout.WEST);
			layout.getLayoutComponent(BorderLayout.WEST).setPreferredSize(new Dimension(width/2, 150)); //die Breite der Buttons wird festgelegt, um Einheitlichkeit zu schaffen
			//TeamPro-button clicked
			westB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int j = 0;
					int zeitzone = 0;
					while(!panel_list.get(j).getComponent(1).equals(westB)) { //findet den index des entsprechenden array-eintrags zum button 
						j++;
					}
					if(debatesPerTime > j) zeitzone = 1;
					else if(j >= debatesPerTime) {
						if(j >= debatesPerTime*2) zeitzone = 3;
						else zeitzone = 2;
					}
					try {
						if(zeitzone == 1) {
							dp.zeitzone1.setStartHours(Integer.parseInt(txtVon.getText()));
							dp.zeitzone1.setStartMins(Integer.parseInt(textField_3.getText()));
							dp.zeitzone1.setEndHours(Integer.parseInt(textField.getText()));
							dp.zeitzone1.setEndMins(Integer.parseInt(textField_2.getText()));
							showEnterPointsDialog(array.get(j), true, westB, dp.zeitzone1); //Zeitzone 1
						}
					
						else if(zeitzone == 3) {
							dp.zeitzone3.setStartHours(Integer.parseInt(textField_10.getText()));
							dp.zeitzone3.setStartMins(Integer.parseInt(textField_11.getText()));
							dp.zeitzone3.setEndHours(Integer.parseInt(textField_9.getText()));
							dp.zeitzone3.setEndMins(Integer.parseInt(textField_8.getText()));
							showEnterPointsDialog(array.get(j), true, westB, dp.zeitzone3); //Zeitzone 3
						}
						else if(zeitzone == 2){
							dp.zeitzone2.setStartHours(Integer.parseInt(textField_6.getText()));
							dp.zeitzone2.setStartMins(Integer.parseInt(textField_7.getText()));
							dp.zeitzone2.setEndHours(Integer.parseInt(textField_5.getText()));
							dp.zeitzone2.setEndMins(Integer.parseInt(textField_4.getText()));
							showEnterPointsDialog(array.get(j), true, westB, dp.zeitzone2); //Zeitzone 2
						}
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(subFrame, "You must enter values to the timezones", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			JButton eastB = new JButton("<html><b>Con</b><br/>" + array.get(i).getTeamCon().getSchule().getName().replaceAll("-", "- ") + "</html>");
			eastB.setHorizontalAlignment(SwingConstants.LEFT); //Text auf Button soll für maximale Buchstabenaufnahme linksbündig sein (mehrzeilig wird der Anfang der Folgezeilen auf den der obersten gesetzt)
			individualF = new Font("Tahoma", Font.PLAIN, 16);
			while(dp.getPanelWidth(array.get(i).getTeamCon().getSchule().getName(), westB.getFontMetrics(individualF)) == 250) {
				individualF = new Font("Tahoma", Font.PLAIN, individualF.getSize()-1);
			}
			eastB.setFont(individualF);
			panel_list.get(i).add(eastB, BorderLayout.EAST);
			layout.getLayoutComponent(BorderLayout.EAST).setPreferredSize(new Dimension(width/2, 150)); 
			//TeamCon-button clicked
			eastB.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int j = 0;
					int zeitzone = 0;
					while(!panel_list.get(j).getComponent(2).equals(eastB)) { //findet den index des entsprechenden array-eintrags zum button 
						j++;
					}
					if(debatesPerTime > j) zeitzone = 1;
					else if(j >= debatesPerTime) {
						if(j >= debatesPerTime*2) zeitzone = 3;
						else zeitzone = 2;
					}
					try {
						if(zeitzone == 1) {
							dp.zeitzone1.setStartHours(Integer.parseInt(txtVon.getText()));
							dp.zeitzone1.setStartMins(Integer.parseInt(textField_3.getText()));
							dp.zeitzone1.setEndHours(Integer.parseInt(textField.getText()));
							dp.zeitzone1.setEndMins(Integer.parseInt(textField_2.getText()));
							showEnterPointsDialog(array.get(j), false, eastB, dp.zeitzone1); //Zeitzone 1
						}
					
						else if(zeitzone == 3) {
							dp.zeitzone3.setStartHours(Integer.parseInt(textField_10.getText()));
							dp.zeitzone3.setStartMins(Integer.parseInt(textField_11.getText()));
							dp.zeitzone3.setEndHours(Integer.parseInt(textField_9.getText()));
							dp.zeitzone3.setEndMins(Integer.parseInt(textField_8.getText()));
							showEnterPointsDialog(array.get(j), false, eastB, dp.zeitzone3); //Zeitzone 3
						}
						else if(zeitzone == 2){
							dp.zeitzone2.setStartHours(Integer.parseInt(textField_6.getText()));
							dp.zeitzone2.setStartMins(Integer.parseInt(textField_7.getText()));
							dp.zeitzone2.setEndHours(Integer.parseInt(textField_5.getText()));
							dp.zeitzone2.setEndMins(Integer.parseInt(textField_4.getText()));
							showEnterPointsDialog(array.get(j), false, eastB, dp.zeitzone2); //Zeitzone 2
						}
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(subFrame, "You must enter values to the timezones", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			String text = "";
			for(int j = 0; j < array.get(i).getJudgeList().size(); j++) { //String aus den Judges bilden
				if(text != "") text = text + ", " + array.get(i).getJudge(j).getName();
				else text = array.get(i).getJudge(j).getName();
			}
			JButton southB = new JButton(text);
			panel_list.get(i).add(southB, BorderLayout.SOUTH);
	        layout.getLayoutComponent(BorderLayout.SOUTH).setPreferredSize(new Dimension(width, 22));
	        
	        //Subpanels werden entsprechend Junior/Senior eingefärbt
	        if(array.get(i).getTeamPro().getIsJunior()) { //falls es sich um ein Junior-Debate handelt
	        	northB.setBackground(new Color(153, 214, 255)); //Raum-Hintergrund blau gesetzt
	        	northB.setContentAreaFilled(false);
	        	northB.setOpaque(true);
	        	southB.setBackground(new Color(153, 214, 255)); //Judges-Hintergrund blau gesetzt
	        	southB.setContentAreaFilled(false);
	        	southB.setOpaque(true);
	        }
	        else { //es handelt sich um ein Senior-Debate
	        	northB.setBackground(new Color(255, 153, 153)); //Raum-Hintergrund rot gesetzt
	        	northB.setContentAreaFilled(false);
	        	northB.setOpaque(true);
	        	southB.setBackground(new Color(255, 153, 153)); //Judges-Hintergrund rot gesetzt
	        	southB.setContentAreaFilled(false);
	        	southB.setOpaque(true);
	        }
	        //(Team-Hintergründe werden gelb: es wurde noch keine korrekte Speaker-Zuordnung vorgenommen)
	        int zeitzone = 0;
	        int k = 0;
	        while(!panel_list.get(k).getComponent(1).equals(westB)) {
	        	k++;
	        }
	        if(k >= debatesPerTime * 2) zeitzone = 3;
	        else if(k >= debatesPerTime) zeitzone = 2;
	        else zeitzone = 1;
	        westB.setBackground(array.get(i).getTeamPro().getColor(zeitzone));
	        westB.setContentAreaFilled(false);
	        westB.setOpaque(true);
	        eastB.setBackground(array.get(i).getTeamCon().getColor(zeitzone));
	        eastB.setContentAreaFilled(false);
	        eastB.setOpaque(true);
		}
		for(int i = 0; i < debatesPerTime; i++) { //die in debates gespeicherten, oben modifizierten Panels werden den drei großen Panels hinzugefügt
			for(int j = 0; j < panels.length; j++) {
				panels[j].add(panel_list.get(i + (j * debatesPerTime)));
			}
		}
		int new_width = panel.getWidth() + panel.getX() + 40;
		if(new_width < standard_width) new_width = standard_width;
		this.setBounds(this.getX(), this.getY(), new_width, this.getHeight()); //update the width relative to the length of the panels
		//remove painted border of long panels
		for(int i = 0; i < panels.length; i++) {
			panels[i].setBorder(null);
		}
	}
	
	public void showEnterSchoolDialog(Schule school_param) {
		JCheckBox[] chckbxs = {new JCheckBox("has junior team"), new JCheckBox("has senior team")}; //2 CheckBoxen fragen ab, welche Teams die eingetragene Schule bereitstellt
		if(school_param == null) {
			chckbxs[0].setSelected(true); //CheckBoxen werden zu Beginn auf true gesetzt (erleichtert schnelle Eingabe)
			chckbxs[1].setSelected(true);
		}
		else {
			chckbxs[0].setSelected(school_param.getHasJuniorTeam());
			chckbxs[1].setSelected(school_param.getHasSeniorTeam());
		}
		Object[] options = {"Enter school name:", chckbxs};
		String s = "";
		if(school_param == null) s = (String)JOptionPane.showInputDialog(subFrame, options); //Schulname wird in "s" gespeichert
		else s = (String)JOptionPane.showInputDialog(subFrame, options, school_param.getName());
		
		if(school_param == null && s != null && s.length() > 0) { //der Fall, dass keine Schule eingegeben wurde wird hier abgefangen
			if(chckbxs[0].isSelected() && chckbxs[1].isSelected()) { //unterschieden wird in der Anzahl der gewählten Checkboxes
				Schule schule = new Schule(s, true, true);
				dp.getSchulen().add(schule);
				Team teamjunior = new Team(schule, true);
				Team teamsenior = new Team(schule, false);
				dp.getJuniorTeams().add(teamjunior); //die team-listen werden erweitert
				dp.getSeniorTeams().add(teamsenior);
				listModel.addElement(schule);
			}
			else if (chckbxs[0].isSelected()) {
				Schule schule = new Schule(s, true, false);
				dp.getSchulen().add(schule);
				dp.getJuniorTeams().add(new Team(schule, true));
				listModel.addElement(schule);
			}
			else if (chckbxs[1].isSelected()) {
				Schule schule = new Schule(s, false, true);
				dp.getSchulen().add(schule);
				dp.getJuniorTeams().add(new Team(schule, false));
				listModel.addElement(schule);
			}
			else {
				//keine Checkbox wurde ausgewählt -> kein Team dieser Schule nimmt teil -> die Schule nimmt nicht teil
				JOptionPane.showMessageDialog(subFrame, "Your school has neither a senior nor a junior team", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(s != null && s.length() > 0) { //alte Schule
			if(!chckbxs[0].isSelected() && !chckbxs[1].isSelected()) {
				//keine Checkbox wurde ausgewählt -> kein Team dieser Schule nimmt teil -> die Schule nimmt nicht teil
				JOptionPane.showMessageDialog(subFrame, "Your school has neither a senior nor a junior team", "Error Message", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(chckbxs[0].isSelected() && !school_param.getHasJuniorTeam()) { //wenn es vorher KEIN juniorteam gab
				Team teamjunior = new Team(school_param, true);
				dp.getJuniorTeams().add(teamjunior);
			}
			if(chckbxs[1].isSelected() && !school_param.getHasSeniorTeam()) { //wenn es vorher KEIN seniorteam gab
				Team teamsenior = new Team(school_param, false);
				dp.getSeniorTeams().add(teamsenior);
			}
			if(s != school_param.getName()) { //wenn der Schulname geändert wurde
				school_param.setName(s);
				
			}
			if(!chckbxs[0].isSelected() && school_param.getHasJuniorTeam()) { //wenn es vorher ein juniorteam gab
				dp.getJuniorTeams().remove(school_param.getJuniorTeam());
				school_param.setJuniorTeam(null); //delete reference
				school_param.setHasJuniorTeam(false);
			}
			if(!chckbxs[1].isSelected() && school_param.getHasSeniorTeam()) { //wenn es vorher ein seniorteam gab
				dp.getSeniorTeams().remove(school_param.getSeniorTeam());
				school_param.setHasSeniorTeam(false);
				school_param.setSeniorTeam(null); //delete reference
			}
		}
		try{
			if(s.length() == 0) { //try/catch, da code Exception erzeugt, die aber nicht weiter relevant ist
				JOptionPane.showMessageDialog(subFrame, "Please enter a school name", "Error Message", JOptionPane.ERROR_MESSAGE);
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
		Object[] options = {"Select judge's school:", combo, chckbx, "Enter judge's name:"};
		String s = (String)JOptionPane.showInputDialog(subFrame, options);
		boolean judgeAlreadyExisting = false;
		for(int i = 0; i < dp.getJudges().size(); i++) { //prüft ob Judge(-name) schon existiert
			if(dp.getJudgeAt(i).getName().equals(s)) judgeAlreadyExisting = true;
		}
		
		int index = combo.getSelectedIndex();
		if(s!= null) { //wenn nicht auf CLOSE geklickt wurde
			if(!s.contains(",") && !judgeAlreadyExisting) {
				if(s.length() > 0) {
					if(chckbx[0].isSelected()) {
						dp.addJudge(new Judge(s, dp.getSchulen().get(index), true)); //erfahrener Judge wird hinzugefügt
					}
					else {
						dp.addJudge(new Judge(s, dp.getSchulen().get(index), false)); //unerfahrener Judge wird hinzugefügt
					}			
				}
				try{ 
					if(s.length() == 0) { //try-catch, da code Exception erzeugt, die aber nicht weiter relevant ist
						JOptionPane.showMessageDialog(subFrame, "No judge name entered.", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NullPointerException e) {
					
				}
			}
			else if(judgeAlreadyExisting) {
				JOptionPane.showMessageDialog(subFrame, "Judge already exists.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
			else if(s.contains(",")) {
				JOptionPane.showMessageDialog(subFrame, "Judge names can't contain a comma", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
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
	public void showEnterPointsDialog(Debate debate, boolean pro, JButton button, Zeitzone zeitzone) {
		Team selectedTeam;
		if(pro) selectedTeam = debate.getTeamPro();
		else selectedTeam = debate.getTeamCon();
		Speaker[] speakers = new Speaker[selectedTeam.getAllSpeaker().size()];
		if(selectedTeam.getAllSpeaker().size() < 3) { //es wurden noch keine Speaker hinzugefügt
			JOptionPane.showMessageDialog(subFrame, "Please make sure you have added enough speakers to this team.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String[] speaker_names = new String[speakers.length];
		//speakers und speaker_names haben dieselbe Ordnung und bezeichnen dieselben Speaker
		for(int i = 0; i < speakers.length; i++) { //Speaker-Array wird gefüllt
			speakers[i] = selectedTeam.getAllSpeaker().get(i);
			speaker_names[i] = speakers[i].getName();
		}
		JComboBox[] speaker = {new JComboBox(speaker_names), new JComboBox(speaker_names), new JComboBox(speaker_names), new JComboBox(speaker_names)}; //JLists are to enter the Speaker
		
		JTextField[] points = {new JTextField(""), new JTextField(""), new JTextField(""), new JTextField("")}; //JTextFields are to enter the points
		
		//>speaker< richtig setzen
		Speaker[] selectedSpeaker = selectedTeam.getSpeakersAtTime(zeitzone.getNumber() - 1);
		for(int i = 0; i < speaker.length; i++) {
			if(!(selectedSpeaker[i] == null)) speaker[i].setSelectedItem(selectedSpeaker[i].getName());
		}
		int[] selectedPoints = selectedTeam.getSpeakerPunkteAt(zeitzone.getNumber());
		for(int i = 0; i < points.length; i++) {
			if(selectedPoints[i] != 0) points[i].setText("" + selectedPoints[i]);
		}
		
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
				boolean speakerFilledCorrectly = true;
				boolean everythingCorrect = true;
				for(int i = 0; i < 4; i++) {
					//Speaker-Reihe
					takenSpeakers[i] = new Speaker("", selectedTeam); //takenSpeaker wird resettet, damit bei mehrfachen Eingabeversuchen kein Fehler entsteht
					for(int j = 0; j <= i; j++) { //verhindert doppeltes Eintragen von Speakern durch Vergleich voriger Speaker mit dem jetzigen
						if(takenSpeakers[j].equals(speakers[speaker[i].getSelectedIndex()]) && i != 3) { //wenn Speaker bereits eingetragen
							speakerFilledCorrectly = false;
							everythingCorrect = false;
						}
					}
					takenSpeakers[i] = speakers[speaker[i].getSelectedIndex()];
					if(takenSpeakers[i].getName() == "") { //wenn kein existenter Speaker ausgewählt wurde
						speakerFilledCorrectly = false;
						everythingCorrect = false;
					}
				}
				//Punkte eintragen getrennt, da NumberFormatException zum Abbruch zwingen kann
				for(int i = 0; i < 4; i++) {
					//Punkte-Reihe
					if(points[i].getText().length() != 0) {
						givenPoints[i] = Integer.parseInt(points[i].getText());
					}
					else {
						try {
							givenPoints[i] = Integer.parseInt(points[i].getText());
						}
						catch(NumberFormatException ex) {
							givenPoints[i] = 0;
						}
						everythingCorrect = false;
					}
				}
				subFrame.dispose(); //resettet subFrame und schließt ihn
				if(speakerFilledCorrectly) {
					selectedTeam.setPoints(takenSpeakers, givenPoints, zeitzone);
					//Button-Farbe ändern
					if(debate.getTeamPro().getIsJunior()) button.setBackground(new Color(153, 214, 255)); // rot/blau: noch Punkte einzutragen
					else button.setBackground(new Color(255, 153, 153));
					button.setContentAreaFilled(false);
					button.setOpaque(true);
					
					for(int i = 0; i < selectedTeam.getAllSpeaker().size(); i++) { //KonsolenAusgabe
						for(int j = 0; j < 6; j++) {
							System.out.println(selectedTeam.getAllSpeaker().get(i).getName() + " " + selectedTeam.getAllSpeaker().get(i).getPunkteIn(j));
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(subFrame, "You must select at least 3 different existing Team-Members", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				
				if(everythingCorrect) {
					//ermittelt, ob gewonnen
					boolean win = false;
					int punkte_gegner = 0;
					int punkte_eigen = 0;
					if(pro) punkte_gegner = debate.getTeamCon().getPunkteAt(zeitzone.getNumber() - 1);
					else punkte_gegner = debate.getTeamPro().getPunkteAt(zeitzone.getNumber() - 1);
					for(int i = 0; i < givenPoints.length; i++) {
						punkte_eigen += givenPoints[i];
					}
					if(!(punkte_gegner == 0) && !(punkte_gegner > punkte_eigen)) { //Gegner-Punkte bereits eingetragen oder weniger als eigene
						win = true;
					}
					selectedTeam.setWin(zeitzone.getNumber() - 1, win);
					//selectedTeam.setPoints(takenSpeakers, givenPoints, zeitzone, win); //Punkte in den Teams eintragen
					
					//Button-Farbe ändern
					button.setBackground(new Color(153, 255, 161)); //grün: alles fertig
					button.setContentAreaFilled(false);
					button.setOpaque(true);
				}
				else {
					boolean run = true;
					int index = 0;
					while(run && index < 4) {
						if(points[index].getText().length() != 0) { //wenn versucht wurde, Punkte einzutragen
							run = false;
							JOptionPane.showMessageDialog(subFrame, "Not every field was filled correctly.\nNotice: You can't use decimal numbers.", "Error Message", JOptionPane.ERROR_MESSAGE);
						}
						index++;
					}
					/*if(run) { //es wurde nicht versucht, Punkte einzutragen
						//gewählte Speaker eintragen
						selectedTeam.setSpeakersAtTime(zeitzone.getNumber() - 1, takenSpeakers);
						//Button-Farbe ändern
						if(debate.getTeamPro().getIsJunior()) button.setBackground(new Color(153, 214, 255)); // rot/blau: noch Punkte einzutragen
						else button.setBackground(new Color(255, 153, 153));
						button.setContentAreaFilled(false);
						button.setOpaque(true);
					}*/
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
		subPanel1.setLayout(new GridLayout(6, 0, 0, 20)); //6 Zeilen links
		
		JPanel subPanel2 = new JPanel();
		subFrameCP.add(subPanel2);
		subPanel2.setLayout(new GridLayout(6, 0, 0, 20)); //6 Zeilen rechts
		
		subPanel1.add(new JLabel("Speakers for this Debate"));
		subPanel2.add(new JLabel("Gained Points"));
		for(int i = 0; i < speaker.length; i++) { //fügt JComboBoxes und JTextFields den Panels hinzu
			subPanel1.add(speaker[i]);
			subPanel2.add(points[i]);
		}
		subPanel1.add(okCancel[0]);
		subPanel2.add(okCancel[1]);
	 	
	}
	
	public void showEditSpeakerDialog(Team team) {
		ArrayList<Speaker> speaker_to_copy = team.getAllSpeaker();
		ArrayList<Speaker> speaker = new ArrayList<Speaker>(speaker_to_copy);
		//create Components
		DefaultListModel model = new DefaultListModel();
		JList speaker_list = new JList(model);
		speaker_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		speaker_list.setLayoutOrientation(JList.VERTICAL);
		speaker_list.setFixedCellHeight(50);
		speaker_list.setCellRenderer(new Renderer());
		JScrollPane sp = new JScrollPane(speaker_list);
		//fill list
		for(int i = 0; i < speaker.size(); i++) {
			model.addElement(speaker.get(i));
		}
		//create buttons + actionListeners
		JButton add_speaker = new JButton("Add Speaker");
		add_speaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String)JOptionPane.showInputDialog("Enter speaker's name:");
				if(!(s == null) && !(s.length() == 0)) {
					Speaker new_speaker = new Speaker(s, team);
					speaker.add(new_speaker);
					model.addElement(new_speaker);
				}
			}
		});
		JButton delete_speaker = new JButton("Delete Speaker");
		delete_speaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = speaker_list.getSelectedIndex();
				if(!(selectedIndex == -1)) {
					Speaker toRemove = (Speaker) model.get(selectedIndex);
					speaker.remove(toRemove);
					model.remove(selectedIndex);
				}
			}
		});
		JButton okay = new JButton("Okay");
		okay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dp.getSpeaker().removeAll(team.getAllSpeaker());
				team.setAllSpeaker(speaker);
				dp.getSpeaker().addAll(speaker);
				for(int i = 0; i < dp.getSpeaker().size(); i++) {
					System.out.println(dp.getSpeaker().get(i).getTeam().getSchule().getName() + ": " + dp.getSpeaker().get(i).getName());
				}
				subFrame.dispose();
			}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				subFrame.dispose();
			}
		});
		
		//create Content-Pane
		subFrame.setVisible(true);
		subFrame.setBounds(500, 150, 500, 500);
		JPanel cpane = new JPanel();
		subFrame.setContentPane(cpane);
		cpane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		//add components to content pane
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.weighty = 0;
		c.ipady = 40;
		c.gridx = 0;
		c.gridy = 0;
		cpane.add(add_speaker, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		cpane.add(delete_speaker, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 300;
		c.weighty = 1;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		cpane.add(sp, c);
		//cpane.add(speaker_list, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 0;
		c.ipady = 50;
		c.gridwidth = 1;
		c.gridy = 2;
		cpane.add(okay, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		cpane.add(cancel, c);
	}
	
	public String breakStringIfTooLong(String s, double length) { //funktioniert noch nicht!
		int pieces = (int) Math.ceil(s.length()/length);
		int l = (int) length;
		String[] final_string = new String[pieces + 1];
		for(int i = 0; i < pieces; i++) {
			if(((i*l) + l) > s.length() - 1) final_string[i] = s.substring(i * l, s.length()) + "<br/>";
			else final_string[i] = s.substring(i * l, (i*l) + l) + "<br/>";
			System.out.println(final_string[i]);
		}
		final_string[pieces - 1] = final_string[pieces - 1].replaceAll("<br/>", "");
		final_string[pieces] = "</html>";
		String str = new String();
		for(int i = 0; i < final_string.length; i++) {
			str = str + final_string[i];
		}
		return str;
	}
	public ArrayList<Team> getJuniorTeams() {
		return dp.getJuniorTeams();
	}
	public ArrayList<Team> getSeniorTeams() {
		return dp.getSeniorTeams();
	}
	
	public ArrayList<Debate> berechne(boolean junior, boolean avoid_reset) {
		//Panels zurücksetzen(mehrfaches drücken)
		if(!avoid_reset) {
			panel.removeAll();
			panel_1.removeAll();
			panel_2.removeAll();
			panel_3.removeAll();
			panel_4.removeAll();
			panel_5.removeAll();
			panel.setBorder(null); //der WICHTIGSTE Befehl überhaupt!!!!!!! MUSS UNBEDINGT DA BLEIBEN!!!
			panel_1.setBorder(null);
			panel_2.setBorder(null);
			panel_3.setBorder(null);
			panel_4.setBorder(null);
			panel_5.setBorder(null);
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
			debates.clear();
		}
		//berechnen lassen
		ArrayList<Debate> array = dp.berechne(junior, avoid_reset);
		return array;
	}
	
	public void manage() {
		verwaltung.anzeigen();		
	}
	
	public ArrayList<JPanel> getDebates() {
		//update the debates-list
		debates.clear();
		//this is java:
		debates.addAll(new ArrayList<JPanel>(Arrays.asList(Arrays.asList(panel.getComponents()).toArray(new JPanel[panel.getComponents().length]))));
		debates.addAll(new ArrayList<JPanel>(Arrays.asList(Arrays.asList(panel_3.getComponents()).toArray(new JPanel[panel_3.getComponents().length]))));
		debates.addAll(new ArrayList<JPanel>(Arrays.asList(Arrays.asList(panel_1.getComponents()).toArray(new JPanel[panel_1.getComponents().length]))));
		debates.addAll(new ArrayList<JPanel>(Arrays.asList(Arrays.asList(panel_4.getComponents()).toArray(new JPanel[panel_4.getComponents().length]))));
		debates.addAll(new ArrayList<JPanel>(Arrays.asList(Arrays.asList(panel_2.getComponents()).toArray(new JPanel[panel_2.getComponents().length]))));
		debates.addAll(new ArrayList<JPanel>(Arrays.asList(Arrays.asList(panel_5.getComponents()).toArray(new JPanel[panel_5.getComponents().length]))));
		return debates;
	}
	public void writeToFile(Object obj, String filepath) {
		try {
			// write object to file
			FileOutputStream fos = new FileOutputStream(filepath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Object readFromFile(String filepath) {
		Object result = null;
		try {
			// read object from file
						FileInputStream fis = new FileInputStream(filepath);
						ObjectInputStream ois = new ObjectInputStream(fis);
						result = ois.readObject();
						ois.close();
						return result;
		}
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
			return null;
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(subFrame, "File not found.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		catch (IOException e) {
			//e.printStackTrace();
			return null;
		}
	}
	public void setDPGui() {
		dp.setGui(this);
	}
	
	public boolean hasDuplicate(Judge[][] items) {
		  Set<Judge> appeared = new HashSet<>();
		  for(int i = 0; i < items.length; i++) {
			  for (Judge item : items[i]) {
			    if (!appeared.add(item)) {
			      return true;
			    }
			  }
		  }
	  return false;
	}
	
	public void refresh() {
		//Motion-Button's border neu berechnen
		btnTimezone.setBorder(new LineBorder(Color.BLACK));
		btnTimezone_1.setBorder(new LineBorder(Color.BLACK));
		btnTimezone_2.setBorder(new LineBorder(Color.BLACK));
	}
	
	public void exportScores(String path) throws IOException {
		FileWriter fw = null;
		PrintWriter out = null;
		try {
			fw = new FileWriter(path);
			out = new PrintWriter(fw);
		}
		catch(NullPointerException e) {
			return;
		}
		//Export speakers
		dp.sortSpeaker2();
		ArrayList<Speaker> speaker = dp.getSpeaker();
		int rows = speaker.size();
		int columns = 8; //Name, Schulname (junior), ZZ1, ZZ1 Reply, ZZ2, ZZ2 Reply, ZZ3, ZZ3 Reply
		out.println("Speaker:");
		out.println("Name, Schoolname (junior/senior), Speech (" + dp.zeitzone1.print() + "), Reply-Speech (" + dp.zeitzone1.print() + "), "
				+ "Speech (" + dp.zeitzone2.print() + "), Reply-Speech (" + dp.zeitzone2.print() + "), Speech (" + dp.zeitzone3.print() + "), Reply-Speech (" + dp.zeitzone3.print() + ")");
		for(int i = 0; i < rows; i++) {
			if(!(speaker.get(i).getTeam().getSchule().getName().equals("other"))) {
				out.print(speaker.get(i).getName() + ",");
				String junior = "";
				if(speaker.get(i).getTeam().getIsJunior()) junior = "junior";
				else junior = "senior";
				out.print(speaker.get(i).getTeam().getSchule().getName() + " (" + junior + "),");
				out.print(speaker.get(i).getPunkteIn(0) + ",");
				out.print(speaker.get(i).getPunkteIn(3) + ",");
				out.print(speaker.get(i).getPunkteIn(1) + ",");
				out.print(speaker.get(i).getPunkteIn(4) + ",");
				out.print(speaker.get(i).getPunkteIn(2) + ",");
				out.println(speaker.get(i).getPunkteIn(5));
			}
		}
		out.println();
		out.println("Junior-Teams:");
		//Export Junior-Teams
		exportTeams(true, out);
		out.println("Senior-Teams:");
		exportTeams(false, out);
		out.flush();
		out.close();
		fw.close();
	}
	
	public void exportTeams(boolean junior, PrintWriter out) {
		dp.sortTeams2(junior);
		ArrayList<Team> teams;
		if(junior) {
			teams = dp.getJuniorTeams();
		}
		else {
			teams = dp.getSeniorTeams();
		}
		out.println("Name, Wins, Points at (" + dp.zeitzone1.print() + "), Points at (" + dp.zeitzone2.print() + "), Points at (" + dp.zeitzone3.print() + ")");
		for(int i = 0; i < teams.size(); i++) {
			out.print(teams.get(i).getSchule().getName() + ",");
			out.print(teams.get(i).getWinAmount() + ",");
			out.print(teams.get(i).getPunkteAt(0) + ",");
			out.print(teams.get(i).getPunkteAt(1) + ",");
			out.println(teams.get(i).getPunkteAt(2));
		}
		out.println();
	}
	
	
	public class Renderer extends JButton implements ListCellRenderer {
		public Renderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}

		@Override
		public java.awt.Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			Color color = new Color(244, 244, 244);
			Color selected_color = new Color(220, 220, 220);
			try{
				Schule schule = (Schule) value;
				setText(schule.getName());
				color = new Color(255, 243, 153);
				selected_color = new Color(237, 226, 142);
				
				if(schule.getHasJuniorTeam()) {
					if(schule.getJuniorTeam().getAllSpeaker().size() >= 3) {
						if(schule.getHasSeniorTeam()) {
							if(schule.getSeniorTeam().getAllSpeaker().size() >= 3) {
								color = new Color(153, 255, 161); //grün
								selected_color = new Color(136, 226, 143);
							}
						}
						else {
							color = new Color(153, 255, 161); //grün
							selected_color = new Color(136, 226, 143);
						}
					}
				}
				else {
					if(schule.getHasSeniorTeam()) {
						if(schule.getSeniorTeam().getAllSpeaker().size() >= 3) {
							color = new Color(153, 255, 161); //grün
							selected_color = new Color(136, 226, 143);
						}
					}
				}
				if(isSelected) {
					setBackground(selected_color);
					setForeground(list.getSelectionForeground());
				}
				else {
					setBackground(color);
					setForeground(list.getForeground());
				}
			}
			catch(Exception e) {
				if(e.getMessage().contains("Speaker")) {
					color = new Color(244, 244, 244);
					Speaker speaker = (Speaker) value;
					setText(speaker.getName());
					if(isSelected) {
						setBackground(new Color(140, 184, 255));
						setForeground(list.getSelectionForeground());
					}
					else {
						setBackground(color);
						setForeground(list.getForeground());
					}
				}
			}
			setContentAreaFilled(false);
			setOpaque(true);
			return this;
		}
	}
	
	public class SchoolMenu extends JPopupMenu {
		JMenuItem[] items;
		public SchoolMenu() {
			items = new JMenuItem[4];
			for(int i = 0; i < items.length; i++) {
				items[i] = new JMenuItem();
			}
			items[0].setText("Edit");
			items[0].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Schule schule = (Schule) listModel.get(list.getSelectedIndex());
					showEnterSchoolDialog(schule);
				}
			});
			items[1].setText("Edit Junior-Team Speakers");
			items[1].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Schule schule = (Schule) listModel.get(list.getSelectedIndex());
					if(schule.getHasJuniorTeam()) {
						showEditSpeakerDialog(schule.getJuniorTeam());
					}
					else JOptionPane.showMessageDialog(subFrame, "This school has no junior team.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			});
			items[2].setText("Edit Senior-Team Speakers");
			items[2].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Schule schule = (Schule) listModel.get(list.getSelectedIndex());
					if(schule.getHasSeniorTeam()) {
						showEditSpeakerDialog(schule.getSeniorTeam());
					}
					else JOptionPane.showMessageDialog(subFrame, "This school has no senior team.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			});
			items[3].setText("Delete");
			items[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedIndex = list.getSelectedIndex();
					Schule schule = (Schule) listModel.getElementAt(selectedIndex);
					if(schule.getHasJuniorTeam()) {
						//remove speakers
						for(int i = 0; i < schule.getJuniorTeam().getAllSpeaker().size(); i++) {
							dp.getSpeaker().remove(schule.getJuniorTeam().getAllSpeaker().get(i));
						}
						//remove team
						dp.getJuniorTeams().remove(schule.getJuniorTeam());
					}
					if(schule.getHasSeniorTeam()) {
						//remove speakers
						for(int i = 0; i < schule.getSeniorTeam().getAllSpeaker().size(); i++) {
							dp.getSpeaker().remove(schule.getSeniorTeam().getAllSpeaker().get(i));
						}
						//remove team
						dp.getSeniorTeams().remove(schule.getSeniorTeam());
					}
					dp.getSchulen().remove(schule);
					listModel.remove(selectedIndex);
				}
			});
			for(int i = 0; i < items.length; i++) {
				add(items[i]);
			}
		}
	}
}
//NEIN NEIN NEIN NEIN NEIN
