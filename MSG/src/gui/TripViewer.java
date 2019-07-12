package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class TripViewer extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TripViewer frame = new TripViewer();
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
	public TripViewer() {
		setResizable(false);
		setTitle("TripViewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Driver ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Output type:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		Choice choice = new Choice();
		choice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		choice.add("Text");
		choice.add("Png");
		choice.setBackground(Color.WHITE);
		choice.setForeground(Color.BLACK);
		
		JLabel lblOutputFileName = new JLabel("Output file name:");
		lblOutputFileName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Generate map");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(choice, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblOutputFileName))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel)
					.addGap(10)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(22)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(choice, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(lblOutputFileName, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(53))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
