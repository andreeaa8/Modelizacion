import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

class marcoAux extends JFrame{
	
	
	public marcoAux() {
		setLayout(new BorderLayout());
		marco m =new marco();
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		
		Dimension tamanoPantalla = miPantalla.getScreenSize();// devuelve el tamaño del monitor principal
		System.out.println(tamanoPantalla.height + " " + tamanoPantalla.width);
		int altura = tamanoPantalla.height;
		int largo = tamanoPantalla.width;
		setSize(largo / 2, altura / 2);
		setLocation(tamanoPantalla.width / 4, tamanoPantalla.height / 4);
		add(m,BorderLayout.CENTER);
		setVisible(true);
		
		
	}

	



}