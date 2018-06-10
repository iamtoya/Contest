package contestx;

import java.awt.BorderLayout;
import javax.swing.*;
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

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Verwaltung extends JFrame {

	private static final int SCHULEN = 0;
	private static final int JUDGES = 1;
	private static final int SPEAKERS = 2;
	private JPanel contentPane;
	private Debatingplan dp;
	private ButtonGroup radioButtonGroup;
	private JList liste;
	private JRadioButton rdbtnNewSchools;
	private JRadioButton rdbtnNewJudges;
	private JRadioButton rdbtnNewSpeaker;
	private JTextField textField;
	
	public Verwaltung(Debatingplan dp) {
		liste = new JList();
		rdbtnNewSpeaker = new JRadioButton("Speaker");
		rdbtnNewJudges = new JRadioButton("Judges");
		rdbtnNewSchools = new JRadioButton("Schools");
		radioButtonGroup = new ButtonGroup();
		
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
	
			
		
		
		liste.setBounds(122, 350, 1, 1);
		getContentPane().add(liste);
		
		textField = new JTextField();
		textField.setBounds(87, 327, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
		
		
	public void anzeigen() {
		this.setVisible(true);
	}	
	
	public void aktualisierenMit(int was) {
		switch(was) {
		case(0): 
			textField.setText("schulen");
			break;
		case(1):
			textField.setText("judges");
			break;
		case(2):
			textField.setText("Speaker");
			break;
		}
	}
}