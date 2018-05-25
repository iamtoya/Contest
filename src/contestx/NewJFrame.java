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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class NewJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTimezone = new JButton("Timezone 1");
		btnTimezone.setEnabled(false);
		btnTimezone.setBounds(82, 158, 89, 52);
		contentPane.add(btnTimezone);
		
		JButton btnTimezone_1 = new JButton("Timezone 2");
		btnTimezone_1.setEnabled(false);
		btnTimezone_1.setBounds(82, 242, 89, 52);
		contentPane.add(btnTimezone_1);
		
		JButton btnTimezone_2 = new JButton("Timezone 3");
		btnTimezone_2.setEnabled(false);
		btnTimezone_2.setBounds(82, 331, 89, 52);
		contentPane.add(btnTimezone_2);
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//zu füllen
				btnTimezone.setEnabled(true);
				btnTimezone_1.setEnabled(true);
				btnTimezone_2.setEnabled(true);
			}
		});
		btnNew.setBounds(10, 11, 93, 52);
		contentPane.add(btnNew);
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 11, 174, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAddSchool = new JButton("Add School");
		btnAddSchool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddSchool.setBounds(338, 10, 104, 23);
		contentPane.add(btnAddSchool);
		
		JButton btnAddSpeaker = new JButton("Add Speaker");
		btnAddSpeaker.setBounds(338, 44, 104, 23);
		contentPane.add(btnAddSpeaker);
		
		JButton btnAddJudge = new JButton("Add Judge");
		btnAddJudge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddJudge.setBounds(338, 78, 104, 23);
		contentPane.add(btnAddJudge);
		
		JCheckBox chckbxIstsenior = new JCheckBox("hat Seniorteam");
		chckbxIstsenior.setBounds(448, 10, 104, 23);
		contentPane.add(chckbxIstsenior);
		
		JCheckBox chckbxHatJuniorteam = new JCheckBox("hat Juniorteam");
		chckbxHatJuniorteam.setBounds(554, 10, 104, 23);
		contentPane.add(chckbxHatJuniorteam);
		
		JPanel panel = new JPanel();
		panel.setBounds(191, 147, 515, 71);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 10, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(191, 233, 515, 71);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 10, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(191, 322, 515, 71);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 10, 0, 0));
	}
}
