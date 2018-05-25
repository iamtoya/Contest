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
import java.awt.Color;

public class NewJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private ArrayList<String> schools;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewJFrame frame = new NewJFrame();
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
	public NewJFrame() {
		
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
		
		JButton btnTimezone = new JButton("Timezone 1");
		btnTimezone.setEnabled(false);
		btnTimezone.setBounds(42, 180, 137, 52);
		contentPane.add(btnTimezone);
		
		JButton btnTimezone_1 = new JButton("Timezone 2");
		btnTimezone_1.setEnabled(false);
		btnTimezone_1.setBounds(42, 337, 137, 52);
		contentPane.add(btnTimezone_1);
		
		JButton btnTimezone_2 = new JButton("Timezone 3");
		btnTimezone_2.setEnabled(false);
		btnTimezone_2.setBounds(42, 492, 137, 52);
		contentPane.add(btnTimezone_2);
		
		
		JButton btnAddSpeaker = new JButton("Add Speaker");
		btnAddSpeaker.setBounds(42, 616, 104, 23);
		contentPane.add(btnAddSpeaker);
		
		JButton btnAddJudge = new JButton("Add Judge");
		btnAddJudge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddJudge.setBounds(42, 650, 104, 23);
		contentPane.add(btnAddJudge);
		
		JCheckBox chckbxIstsenior = new JCheckBox("has Senior-Team");
		chckbxIstsenior.setBounds(793, 32, 165, 23);
		chckbxIstsenior.setEnabled(false);
		contentPane.add(chckbxIstsenior);
		
		JCheckBox chckbxHatJuniorteam = new JCheckBox("has Junior-Team");
		chckbxHatJuniorteam.setBounds(793, 63, 165, 23);
		chckbxHatJuniorteam.setEnabled(false);
		contentPane.add(chckbxHatJuniorteam);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBounds(194, 449, 734, 138);
		contentPane.add(panel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		panel_1.setBounds(194, 295, 734, 138);
		contentPane.add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(194, 139, 734, 138);
		contentPane.add(panel);
		
		JButton btnAddSchool = new JButton("Add School");
		
		btnAddSchool.setEnabled(false);
		btnAddSchool.setBounds(642, 32, 140, 54);
		contentPane.add(btnAddSchool);
		
		textField_1 = new JTextField();
		textField_1.setBounds(191, 32, 435, 54);
		textField_1.setEnabled(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnNew = new JButton("New");
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
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCreate.setBounds(964, 32, 157, 54);
		contentPane.add(btnCreate);
		
		btnAddSchool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				schools.add(textField_1.getText());
			}
		});

		panel.setLayout(new GridLayout(0, 10, 0, 0));
		panel_1.setLayout(new GridLayout(0, 10, 0, 0));
		panel_2.setLayout(new GridLayout(0, 10, 0, 0));
	}
	public void seperatePanel() {
		
	}
}
