import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class marco extends JPanel implements ActionListener{
	
	private boolean pushed=false;
	juego game;
	menuLamina m;
	JButton txt = new JButton("empezar");
	public marco() {
		
		
		
		setLayout(new BorderLayout());
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
	
		txt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				m.button.doClick();
				remove(game);
				
				System.out.println("he entrado");
				if(m.getgameMode()==3) {
					game= new juego(m.getgameMode(),m.getBeguin(),m.getCpu());
					add(game,BorderLayout.CENTER);
				}else if(m.getgameMode()==6) {
						game= new juego(m.getgameMode(),m.getBeguin(),m.getCpu(),'a');
						add(game,BorderLayout.CENTER);
					}else {
						
						game= new juego(3,m.getBeguin(),m.getCpu(),1);
						add(game,BorderLayout.CENTER);			
					}
				txt.setText("reiniciar");
				revalidate();
				repaint();
				
			}
		});
		//chapuza
		/*txt.setBounds(610, 315, 1000, 50);
		txt.setOpaque(false);
		txt.setContentAreaFilled(false);
		txt.setBorderPainted(false);*/
		
		add(txt,BorderLayout.SOUTH);
		
		
		Dimension tamanoPantalla = miPantalla.getScreenSize();// devuelve el tamaño del monitor principal
		System.out.println(tamanoPantalla.height + " " + tamanoPantalla.width);
		int altura = tamanoPantalla.height;
		int largo = tamanoPantalla.width;
		setSize(largo / 2, altura / 2);
		setLocation(tamanoPantalla.width / 4, tamanoPantalla.height / 4);
		

		 m = new menuLamina(getSize());
		// menuLamina m1 = new menuLamina(getSize());
	
		 
		
		add(m,BorderLayout.EAST);
		
		 game = new juego(3,m.getBeguin(),m.getCpu());
		//add(j,BorderLayout.CENTER);
		
		//j.setBounds(50, 30, 600, 610);
		
	
		
		add(game,BorderLayout.CENTER);
		
		
		//add(new gameInfo(), BorderLayout.WEST);

		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("he entrado aqui pisha");
		
	}
	
	


	void removeComponents(JPanel panel) {
		
		
		for (Component component : panel.getComponents()) {
			
			component.setEnabled(false);
			remove(component);
			
			
		}
		
		
		
		
	}
	

	
	

}