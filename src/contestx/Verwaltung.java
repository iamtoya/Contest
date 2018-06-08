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

	private JPanel contentPane;
	private Debatingplan dp;
	private ButtonGroup radioButtonGroup;
	
	public Verwaltung(Debatingplan dp) {
		setAlwaysOnTop(true);
		setResizable(false);
		setTitle("Verwaltung");
		getContentPane().setLayout(null);
		setBounds(100, 100, 442, 682);
		
		JRadioButton rdbtnNewSchools = new JRadioButton("Schools");
		rdbtnNewSchools.setSelected(true);
		rdbtnNewSchools.setBounds(35, 43, 109, 23);
		getContentPane().add(rdbtnNewSchools);
		
		JRadioButton rdbtnNewJudges = new JRadioButton("Judges");
		rdbtnNewJudges.setBounds(161, 43, 109, 23);
		getContentPane().add(rdbtnNewJudges);
		
		JRadioButton rdbtnNewSpeaker = new JRadioButton("Speaker");
		rdbtnNewSpeaker.setBounds(303, 43, 109, 23);
		getContentPane().add(rdbtnNewSpeaker);
		
		radioButtonGroup = new ButtonGroup();
		
		radioButtonGroup.add(rdbtnNewSchools);
		radioButtonGroup.add(rdbtnNewJudges);
		radioButtonGroup.add(rdbtnNewSpeaker);
	}
		
		
		public void anzeigen() {
			this.setVisible(true);
		}	
}