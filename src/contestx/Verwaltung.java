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
	
	public Verwaltung(Debatingplan dp) {
		rdbtnNewSpeaker = new JRadioButton("Speaker");
		rdbtnNewJudges = new JRadioButton("Judges");
		rdbtnNewSchools = new JRadioButton("Schools");
		radioButtonGroup = new ButtonGroup();
		txtSchools = new JTextField();
		comboBox = new JComboBox();
		this.dp=dp;
		
		//Allgemeine Dinge des Fensters werden eingestellt
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Verwaltung");
		getContentPane().setLayout(null);
		setBounds(100, 100, 442, 682);
		
		//Auswahlbuttons werden zugewiesen/verteilt
		rdbtnNewSchools.setSelected(true);
		rdbtnNewSchools.setBounds(35, 43, 109, 23);
		getContentPane().add(rdbtnNewSchools);		
		
		rdbtnNewJudges.setBounds(161, 43, 109, 23);
		getContentPane().add(rdbtnNewJudges);		
		
		rdbtnNewSpeaker.setBounds(303, 43, 109, 23);
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
		txtSchools.setBounds(35, 594, 377, 32);
		getContentPane().add(txtSchools);
		txtSchools.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Debug-Textfield:");
		lblNewLabel.setBounds(35, 569, 97, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datens\u00E4tze:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(35, 206, 109, 23);
		getContentPane().add(lblNewLabel_1);
		
		
		comboBox.setBounds(35, 240, 377, 319);
		getContentPane().add(comboBox);
		
		aktualisierenMit(SCHULEN);
		
		
	}
		
		
	public void anzeigen() {
		this.setVisible(true);
	}	
	
	public void aktualisierenMit(int was) {
		int i = 0;
		comboBox.removeAllItems();
		
		switch(was) {
		case(0): 			
			for(i = 0; i < dp.getSchulen().size(); i++ ) {
				comboBox.addItem(dp.getSchulen().get(i).getName());
			}
			txtSchools.setText("schulen");
			break;
		case(1):
			txtSchools.setText("judges");
			break;
		case(2):
			txtSchools.setText("Speaker");
			break;
		}
	}
}