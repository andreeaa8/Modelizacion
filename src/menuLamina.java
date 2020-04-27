import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

class menuLamina extends JPanel {
	
	private int gameMode=3;
	boolean pushed = false;
	JComboBox miCombo = new JComboBox();
	JComboBox miCombo2 = new JComboBox();
	JComboBox miCombo3 = new JComboBox();
	JButton button;

	public menuLamina(Dimension size) {

		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

		Box caja1 = Box.createVerticalBox();

		
		JLabel label = new JLabel("Modo de juego");
		label.setMaximumSize(label.getPreferredSize());
		button = new JButton("Ok");
		JLabel label1 = new JLabel();
		label1.setText("¿Quien empieza?");
		
		miCombo.addItem("3 en raya");
		miCombo.addItem("4 en raya");
		
		miCombo3.addItem("CPU");
		miCombo3.addItem("sin CPU");

		miCombo.setMaximumSize(miCombo.getPreferredSize());
		miCombo3.setMaximumSize(miCombo3.getPreferredSize());
		
		
		label.setAlignmentX(miCombo.getAlignmentX());
	
		miCombo2.addItem("tu");
		miCombo2.addItem("la maquina");

		miCombo2.setMaximumSize(miCombo2.getPreferredSize());
		label1.setAlignmentX(miCombo2.getAlignmentX());
		button.setAlignmentX(miCombo.getAlignmentX());
		
		
		
		
		miCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (miCombo.getSelectedItem().equals("3 en raya")) {
					
					
					gameMode=3;
					
					System.out.println("cambio a 3");
					
				}else {
					
					gameMode=4;
					
					System.out.println("cambio a 4");
				}
				pushed=true;
				
				
				
			}
		});
		
		/*
		 * add(label,BorderLayout.EAST); //add(miCombo,BorderLayout.WEST);
		 * add(label1,BorderLayout.CENTER);
		 */
		caja1.add(Box.createVerticalStrut((int) (size.getHeight() / 4)));
		caja1.add(label);
		caja1.add(Box.createVerticalStrut(20));
		
		caja1.add(miCombo);
		caja1.add(miCombo3);
		caja1.add(Box.createVerticalStrut(20));
		caja1.add(label1);
		caja1.add(Box.createVerticalStrut(20));
		caja1.add(miCombo2);
		caja1.add(Box.createVerticalStrut(20));
		caja1.add(button);

		add(caja1);

		/*
		 * comboBox1.setBounds(50, 40, 30, 70); comboBox2.setBounds(50,20,30,70);
		 * comboBox3.setBounds(50, 100, 30, 70); AIBoxLevel.setBounds(50, 60, 30, 70);
		 * miCombo.setBounds(50, 80, 30, 70);
		 */

	}

	public int getgameMode() {

		return gameMode;

	}

}