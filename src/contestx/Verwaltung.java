package contestx;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;

public class Verwaltung extends JFrame {
	//Klassenattribute, auf die in Methoden zugegriffen wird
	private static final int SCHULEN = 0;
	private static final int JUDGES = 1;
	private static final int SPEAKERS = 2;
	private JPanel contentPane;
	private Debatingplan dp;
	private ButtonGroup radioButtonGroup;
	private JRadioButton rdbtnNewSchools;
	private JRadioButton rdbtnNewJudges;
	private JRadioButton rdbtnNewSpeaker;
	private JComboBox comboBox;
	private JTextField textFieldSchools1;
	private JCheckBox chckbxSchools1;
	private JCheckBox chckbxSchools2;
	private JButton btnChange;
	private JTextField textFieldJudges;
	private JCheckBox chckbxIsExperienced;
	private JComboBox comboBoxJudgesSchools;
	private JCheckBox chckbxJudgesZZ1;
	private JCheckBox chckbxJudgesZZ2;
	private JCheckBox chckbxJudgesZZ3;
	private JLabel lblName;
	private JLabel lblSchool;
	private JTextField textFieldSpeaker;
	private JLabel lblNewLabel_1;
	
	
	
	public Verwaltung(Debatingplan dp) {
		this.dp=dp;
		rdbtnNewSpeaker = new JRadioButton("Speaker");
		rdbtnNewJudges = new JRadioButton("Judges");
		rdbtnNewSchools = new JRadioButton("Schools");
		radioButtonGroup = new ButtonGroup();
		comboBox = new JComboBox();
		textFieldSchools1 = new JTextField();
		
		
		
		
		
		//Allgemeine Eigenschaften des Fensters werden eingestellt
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Verwaltung");
		getContentPane().setLayout(null);
		setBounds(100, 100, 444, 363);
		
		
		
		
		//Auswahlbuttons werden zugewiesen/verteilt
		rdbtnNewSchools.setSelected(true);
		rdbtnNewSchools.setBounds(35, 23, 109, 23);
		getContentPane().add(rdbtnNewSchools);		
		
		rdbtnNewJudges.setBounds(164, 23, 109, 23);
		getContentPane().add(rdbtnNewJudges);		
		
		rdbtnNewSpeaker.setBounds(303, 23, 109, 23);
		getContentPane().add(rdbtnNewSpeaker);		
		
		
		
		
		
		//Buttons werden der Gruppe zugewiesen, damit nur einer ausgewählt werden kann
		radioButtonGroup.add(rdbtnNewSchools);
		radioButtonGroup.add(rdbtnNewJudges);
		radioButtonGroup.add(rdbtnNewSpeaker);
		
		
		
		//Allgemeine Komponenten werden erstellt und bekommen Position und Eigenschaften
		lblNewLabel_1 = new JLabel("Choose the school to be changed:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(35, 68, 238, 23);
		getContentPane().add(lblNewLabel_1);		
		
		comboBox.setBounds(35, 102, 377, 32);
		getContentPane().add(comboBox);
		
		textFieldSchools1.setBounds(35, 194, 124, 20);
		getContentPane().add(textFieldSchools1);
		textFieldSchools1.setColumns(10);
		
		chckbxSchools1 = new JCheckBox("has junior team");
		chckbxSchools1.setBounds(176, 193, 109, 23);
		getContentPane().add(chckbxSchools1);
		
		chckbxSchools2 = new JCheckBox("has senior team");
		chckbxSchools2.setBounds(176, 231, 109, 23);
		getContentPane().add(chckbxSchools2);
		
		btnChange = new JButton("Change");
		btnChange.setBounds(35, 300, 377, 23);
		getContentPane().add(btnChange);
		
		textFieldJudges = new JTextField();
		textFieldJudges.setBounds(35, 194, 124, 20);
		getContentPane().add(textFieldJudges);
		textFieldJudges.setColumns(10);
		
		chckbxIsExperienced = new JCheckBox("is experienced");
		chckbxIsExperienced.setBounds(176, 193, 97, 23);
		getContentPane().add(chckbxIsExperienced);
		
		comboBoxJudgesSchools = new JComboBox();
		comboBoxJudgesSchools.setBounds(35, 246, 124, 20);
		getContentPane().add(comboBoxJudgesSchools);
		
		chckbxJudgesZZ1 = new JCheckBox("timezone 1");
		chckbxJudgesZZ1.setSelected(true);
		chckbxJudgesZZ1.setBounds(287, 193, 97, 23);
		getContentPane().add(chckbxJudgesZZ1);
		
		chckbxJudgesZZ2 = new JCheckBox("timezone 2");
		chckbxJudgesZZ2.setSelected(true);
		chckbxJudgesZZ2.setBounds(287, 219, 97, 23);
		getContentPane().add(chckbxJudgesZZ2);
		
		chckbxJudgesZZ3 = new JCheckBox("timezone 3");
		chckbxJudgesZZ3.setSelected(true);
		chckbxJudgesZZ3.setBounds(287, 245, 97, 23);
		getContentPane().add(chckbxJudgesZZ3);
		
		JLabel lblChangeHere = new JLabel("Change here:");
		lblChangeHere.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChangeHere.setBounds(35, 145, 238, 23);
		getContentPane().add(lblChangeHere);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(35, 179, 46, 14);
		getContentPane().add(lblName);
		
		lblSchool = new JLabel("School:");
		lblSchool.setBounds(35, 225, 46, 14);
		getContentPane().add(lblSchool);
		
		textFieldSpeaker = new JTextField();
		textFieldSpeaker.setBounds(35, 194, 124, 20);
		getContentPane().add(textFieldSpeaker);
		textFieldSpeaker.setColumns(10);
		lblSchool.setVisible(false);
		
		
		
		
		
		
		
		//Methoden werden Buttons zugeteilt
		rdbtnNewSchools.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aktualisierenMit(SCHULEN);
			};
		});
				
		rdbtnNewJudges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aktualisierenMit(JUDGES);
			};
		});
			
		rdbtnNewSpeaker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aktualisierenMit(SPEAKERS);
			};
		});
				
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change();
			};
		});
		
		
		
		
		//am Ende wird das Fenster auf den voreingestellten Button  Schule angepasst und Daten derselben Gruppe mit "aktualisierenMit()" aktualisiert
		aktualisierenMit(SCHULEN);		
	}
		
		
	//Fenster wird wieder angezeigt und resettet (Wieder Schools ausgewählt und dargestellt)
	public void anzeigen() {
		this.setVisible(true);
		rdbtnNewSchools.setSelected(true);		
		aktualisierenMit(SCHULEN);
	}	
	
	
	//aktualisiert das Fenster mit den Daten der mitgeteilten Gruppe "was"
	public void aktualisierenMit(int was) {
		int i = 0;
		comboBox.removeAllItems();
		
		switch(was) {
		case(0): //Schulen			
			for(i = 0; i < dp.getSchulen().size(); i++ ) {					//Alle Schulen werden in die Auswahlliste eingetragen
				comboBox.addItem(dp.getSchulen().get(i).getName());
			}
			this.lblNewLabel_1.setText("Choose the school to be changed:");
			nurAnzeigen(was);			
			break;
		case(1): //Judges
			for(i = 0; i < dp.getJudges().size(); i++ ) {					//Alle Judges werden in die Auswahlliste eingetragen
				comboBox.addItem(dp.getJudges().get(i).getName());
			}
			comboBoxJudgesSchools.removeAllItems();
			for(i = 0; i < dp.getSchulen().size(); i++ ) {					//Schulen werden zusätzlich geaddet, da benötigt für Judges
				comboBoxJudgesSchools.addItem(dp.getSchulen().get(i).getName());
			}
			this.lblNewLabel_1.setText("Choose the judge to be changed:");	
			nurAnzeigen(was);
			break;
		case(2): //Speaker
			for(i = 0; i < dp.getSpeaker().size(); i++ ) {					//Alle Speaker werden in die Auswahlliste eingetragen
				comboBox.addItem(dp.getSpeaker().get(i).getName());
			}
		this.lblNewLabel_1.setText("Choose the speaker to be changed:");
			nurAnzeigen(was);
			break;
		}
	}
	
	
	//Zeigt die Komponenten an, die für gewählte Gruppe wichtig sind, andere werden ausgeblendet
	public void nurAnzeigen(int was) {
		switch(was) {
		case(0): 			
			this.chckbxSchools1.setVisible(true);
			this.chckbxSchools2.setVisible(true);
			this.textFieldSchools1.setVisible(true);
			this.chckbxIsExperienced.setVisible(false);
			this.comboBoxJudgesSchools.setVisible(false);
			this.textFieldJudges.setVisible(false);
			this.chckbxJudgesZZ1.setVisible(false);
			this.chckbxJudgesZZ2.setVisible(false);
			this.chckbxJudgesZZ3.setVisible(false);
			this.lblSchool.setVisible(false);
			this.textFieldSpeaker.setVisible(false);
			break;
		case(1):
			this.chckbxSchools1.setVisible(false);
			this.chckbxSchools2.setVisible(false);
			this.textFieldSchools1.setVisible(false);
			this.chckbxIsExperienced.setVisible(true);
			this.comboBoxJudgesSchools.setVisible(true);
			this.textFieldJudges.setVisible(true);
			this.chckbxJudgesZZ1.setVisible(true);
			this.chckbxJudgesZZ2.setVisible(true);
			this.chckbxJudgesZZ3.setVisible(true);
			this.lblSchool.setVisible(true);
			this.textFieldSpeaker.setVisible(false);
			break;
		case(2):
			this.chckbxSchools1.setVisible(false);
			this.chckbxSchools2.setVisible(false);
			this.textFieldSchools1.setVisible(false);
			this.chckbxIsExperienced.setVisible(false);
			this.comboBoxJudgesSchools.setVisible(false);
			this.textFieldJudges.setVisible(false);
			this.chckbxJudgesZZ1.setVisible(false);
			this.chckbxJudgesZZ2.setVisible(false);
			this.chckbxJudgesZZ3.setVisible(false);
			this.lblSchool.setVisible(false);
			this.textFieldSpeaker.setVisible(true);
			break;
		}
	}
	
	
	//eingetrage Änderungen werden im Debatingplan geändert, anschlie0end wird Fenster aktualisiert, um Änderungen direkt anzuzeigen
	public void change() {
		if(rdbtnNewSchools.isSelected()==true) { 			
			dp.getSchulen().get(comboBox.getSelectedIndex()).setName(textFieldSchools1.getText());
			dp.getSchulen().get(comboBox.getSelectedIndex()).setHasJuniorTeam(chckbxSchools1.isSelected());
			dp.getSchulen().get(comboBox.getSelectedIndex()).setHasSeniorTeam(chckbxSchools2.isSelected());
			aktualisierenMit(SCHULEN);
		}
		else if(rdbtnNewJudges.isSelected()==true) {
			dp.getJudges().get(comboBox.getSelectedIndex()).setName(textFieldJudges.getText());
			dp.getJudges().get(comboBox.getSelectedIndex()).setErfahren(chckbxIsExperienced.isSelected());
			dp.getJudges().get(comboBox.getSelectedIndex()).setSchule(dp.getSchulen().get(comboBoxJudgesSchools.getSelectedIndex()));
			dp.getJudges().get(comboBox.getSelectedIndex()).setKannZuZZ1(chckbxJudgesZZ1.isSelected());
			dp.getJudges().get(comboBox.getSelectedIndex()).setKannZuZZ2(chckbxJudgesZZ2.isSelected());
			dp.getJudges().get(comboBox.getSelectedIndex()).setKannZuZZ3(chckbxJudgesZZ3.isSelected());
			aktualisierenMit(JUDGES);
		}
		else if(rdbtnNewSpeaker.isSelected()==true) {
			dp.getSpeaker().get(comboBox.getSelectedIndex()).setName(textFieldSpeaker.getText());
			aktualisierenMit(SPEAKERS);
		}
	}
}