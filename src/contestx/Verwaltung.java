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

	private static final int SCHULEN = 0;
	private static final int JUDGES = 1;
	private static final int SPEAKERS = 2;
	private JPanel contentPane;
	private Debatingplan dp;
	private ButtonGroup radioButtonGroup;
	private JRadioButton rdbtnNewSchools;
	private JRadioButton rdbtnNewJudges;
	private JRadioButton rdbtnNewSpeaker;
	private JTextField txtSchools;
	private JComboBox comboBox;
	private JTextField textFieldSchools1;
	private JCheckBox chckbxSchools1;
	private JCheckBox chckbxSchools2;
	
	public Verwaltung(Debatingplan dp) {
		rdbtnNewSpeaker = new JRadioButton("Speaker");
		rdbtnNewJudges = new JRadioButton("Judges");
		rdbtnNewSchools = new JRadioButton("Schools");
		radioButtonGroup = new ButtonGroup();
		txtSchools = new JTextField();
		comboBox = new JComboBox();
		this.dp=dp;
		textFieldSchools1 = new JTextField();
		
		//Allgemeine Eigenschaften des Fensters werden eingestellt
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Verwaltung");
		getContentPane().setLayout(null);
		setBounds(100, 100, 442, 384);
		
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
	
			
		//Debugfenster zum erkennen der funktion
		txtSchools.setBounds(35, 298, 377, 32);
		getContentPane().add(txtSchools);
		txtSchools.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Debug-Textfield:");
		lblNewLabel.setBounds(47, 273, 97, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose the object to be changed:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(35, 68, 238, 23);
		getContentPane().add(lblNewLabel_1);
		
		
		comboBox.setBounds(35, 102, 377, 32);
		getContentPane().add(comboBox);
		
		textFieldSchools1.setBounds(35, 168, 124, 20);
		getContentPane().add(textFieldSchools1);
		textFieldSchools1.setColumns(10);
		
		chckbxSchools1 = new JCheckBox("has junior team");
		chckbxSchools1.setBounds(176, 167, 109, 23);
		getContentPane().add(chckbxSchools1);
		
		chckbxSchools2 = new JCheckBox("has senior team");
		chckbxSchools2.setBounds(176, 200, 109, 23);
		getContentPane().add(chckbxSchools2);
		
		aktualisierenMit(SCHULEN);
		
		
	}
		
		
	public void anzeigen() {
		this.setVisible(true);
		aktualisierenMit(SCHULEN);
	}	
	
	public void aktualisierenMit(int was) {
		int i = 0;
		comboBox.removeAllItems();
		
		switch(was) {
		case(0): 			
			for(i = 0; i < dp.getSchulen().size(); i++ ) {
				comboBox.addItem(dp.getSchulen().get(i).getName());
			}
			nurAnzeigen(was);
			txtSchools.setText("schulen");
			break;
			case(1):
			txtSchools.setText("judges");
		nurAnzeigen(was);
			break;
		case(2):
			txtSchools.setText("Speaker");
			nurAnzeigen(was);
			break;
		}
	}
	
	public void nurAnzeigen(int was) {
		switch(was) {
		case(0): 			
			this.chckbxSchools1.setVisible(true);
			this.chckbxSchools2.setVisible(true);
			this.textFieldSchools1.setVisible(true);
			break;
		case(1):
			this.chckbxSchools1.setVisible(false);
			this.chckbxSchools2.setVisible(false);
			this.textFieldSchools1.setVisible(false);
			break;
		case(2):
			this.chckbxSchools1.setVisible(false);
			this.chckbxSchools2.setVisible(false);
			this.textFieldSchools1.setVisible(false);
			break;
		}
	}
}