import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

class juego extends JPanel implements ActionListener {

	private Map<Integer, JButton> list;
	private Map<Integer,JTextPane> listPane;
    private Color tablePaint[][]; 
    private JButton botones[];
    JButton bb;
    Color AZUL_APAGADO= new Color(192,192,130);
    //JTextPane p[];
    int lastMov[];
    
    
    Color idPlayer1,idPlayer2;//first player is in red, second one in blue
    int turn=0;
    int num=0;
    JFrame endGame;
    boolean end,beguin,found,cpu;
	public juego(int num,boolean begin,boolean cpu) {
		//reset();
		end=false;
		this.beguin=begin;
		this.cpu=cpu;
		this.num=num;
		if(begin) {
			idPlayer1=Color.blue;
			idPlayer2=Color.red;
			
			
			}else {
			idPlayer2=Color.blue;
			idPlayer1=Color.red;
			}
		System.out.println("tablero de "+num);
		tablePaint= new Color[num][num];
		botones= new JButton[num*num];
		//p= new JTextPane[num*num];
		int x=50,y=30,ancho=70;
		int aux=x;
		//setLayout(new dispositionTable(num));
		setLayout(null);
		
			/*JButton boton1 = new JButton();
			/*boton1.setOpaque(false);
			boton1.setContentAreaFilled(false);
			boton1.setBorderPainted(false);
			JTextPane p = new JTextPane();
			p.setBackground(AZUL_APAGADO);
			
			boton1.setBounds(50,30,70,70);
			p.setBounds(50, 30, 70, 70);*/
			int res[];
			for (int i = 0; i < num*num; i++) {
				
				res=conversionLineTPlain(i);
				
				tablePaint[res[0]][res[1]]=Color.white;
				botones[i] = new JButton();
				/*boton1.setOpaque(false);
				boton1.setContentAreaFilled(false);
				boton1.setBorderPainted(false);*/
				JTextPane p = new JTextPane();
				
				
			    //	p[i]=new JTextPane();
				//Component c1 = parent.getComponent(i+1);
				
				if(i%num==0) {
				y+=ancho;
				x=aux;
				botones[i].setBounds(x,y,ancho,ancho);
				p.setBounds(x,y,ancho,ancho);

				}else {
					
				x+=ancho;
				botones[i].setBounds(x,y,ancho,ancho);
				p.setBounds(x, y, ancho, ancho);
					//System.out.println("holaa");
				}
				
				//p.disable();
				p.setBackground(Color.WHITE);
				botones[i].setOpaque(false);
				botones[i].setContentAreaFilled(false);
				botones[i].setBorderPainted(false);
				botones[i].setName(""+i);
				botones[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						Color panel =p.getBackground();
						
			
						
						if(panel==Color.WHITE && !end) {
							int res[]=conversionLineTPlain(Integer.parseInt(((JButton)e.getSource()).getName()));
							
							if(turn%2==0) {
								
								p.setBackground(Color.red);
								tablePaint[res[0]][res[1]]=Color.red;
								turn++;
								
							}else {
								p.setBackground(Color.blue);
								turn++;
								tablePaint[res[0]][res[1]]=Color.blue;
								
							}
							
							
							for (int j = 0; j < res.length-1; j++) {
								System.out.println(res[j]+" "+res[j+1]);
								System.out.println(conversionPlainToLine(res[0], res[1]));
								
								
							}
							
							lastMov=res;
							
						}
						
						
						if(checkWin(tablePaint)|| draw() ) {
							reset();
							end=true;
							endGame.setVisible(true);
							endGame.setEnabled(true);
							imprimirTablero();
							
							System.out.println("Has ganado");
							
						}else {
							imprimirTablero();
							
							if(cpu) {
								
							algorithm(beguin);
							}
							
						}
						
					}

					
				});
			
				add(botones[i]);
				if(beguin&&i==4&&cpu) {
					
					p.setBackground(idPlayer2);
					tablePaint[1][1]=idPlayer2;
					turn++;
					beguin=false;
				}
				add(p);
				
				
			}
			
			
			
			/*
			for (int i = 0; i < tablePaint.length; i++) {
				
				for (int j = 0; j < tablePaint.length; j++) {
					
					tablePaint[i][j]=Color.white;
					
				}
				
			}
		

		*/
			
			
			imprimirTablero();
			System.out.println(beguin);
			System.out.println(this.cpu);
		

	}
	
	

	public Map<Integer, JButton> getList() {
		return list;
	}



	public void setList(Map<Integer, JButton> list) {
		this.list = list;
	}



	public Map<Integer, JTextPane> getListPane() {
		return listPane;
	}



	public void setListPane(Map<Integer, JTextPane> listPane) {
		this.listPane = listPane;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*
		
		Set<Entry<Integer,JButton>> l =list.entrySet();
		Iterator<Entry<Integer,JButton>> it = l.iterator();
		
		boolean found= false;
		int i=0;
		while(it.hasNext() && !found){
			
			Entry<Integer,JButton> aux = it.next();
			
			JButton aux1 = (JButton) e.getSource();
			
			if(aux1.equals(aux.getValue())) {
				
				
				found=true;
				
				listPainted.put(aux.getKey(), true);
				
				aux1.setBackground(Color.RED);
				list.put(aux.getKey(), aux1);
			}
			
		}
		*/
		
		//bb.setBackground(Color.red);
		
		//repaint();
		
		/*if(turn%2==0) {
		
		prueba.setBackground(Color.blue);
		turn++;
		}else {
			
		prueba.setBackground(Color.red);
		turn++;
			
		}*/
		
		

	}

	public void paintComponent(Graphics g) {

		

	}
	
	private boolean checkWin(Color[][] table) {
		// TODO Auto-generated method stub
		
		
		CheckWin th1 = new CheckWin(table, 1);
		CheckWin th2 = new CheckWin(table, 2);
		CheckWin th3 = new CheckWin(table, 3);
		CheckWin th4 = new CheckWin(table, 4);
		th1.start();
		th2.start();
		th3.start();
		th4.start();
		try {
			th1.join();
			th2.join();
			th3.join();
			th4.join();
			System.out.println("acabado");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return found=(th1.isCheck()||th2.isCheck()||th3.isCheck()||th4.isCheck());
		
		
		
	}
	
	private void reset() {
		
		endGame=new JFrame();
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		
		Dimension tamanoPantalla = miPantalla.getScreenSize();// devuelve el tamaño del monitor principal
		System.out.println(tamanoPantalla.height + " " + tamanoPantalla.width);
		int altura = tamanoPantalla.height;
		int largo = tamanoPantalla.width;
		endGame.setSize(largo / 8, altura / 8);
		endGame.setLocation(tamanoPantalla.width / 4 + largo / 8, tamanoPantalla.height / 4 + altura / 8);
		endGame.setLayout(new BorderLayout());
		JButton reiniciar= new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
							if(checkWin(tablePaint)) {	
								endGame.dispose();
								System.out.println("Has ganado");
							}
						}

						
					});
		endGame.add(reiniciar, BorderLayout.SOUTH);
		endGame.add(new gameInfo(turn%2,draw()),BorderLayout.CENTER);
		endGame.setEnabled(false);
		
		
		
	}
	
	void algorithm(boolean beguin) {
		int res[]=new int[2],i=0,j=0;
		boolean found=false;
		
		Color[][] table = tablePaint;
		
		
		
		
		
		
		
		if(table[1][1]==Color.white){
			
			res=conversionLineTPlain(4);
			this.beguin=false;
			botones[4].doClick();
			
			
		}
		System.out.println(String.valueOf(idPlayer2.getRGB()));
		
		if(tablePaint[lastMov[0]][lastMov[1]]!=idPlayer2) {
			
			
			if(!(found=whoWins(found, table, idPlayer2))){
				
				
				found=whoWins(found, table, idPlayer1);
				
			}

			
			if(!found ) {
			
			imprimirTablero();
				
			res=nearLastMov();
			
			botones[conversionPlainToLine(res[0],res[1])].doClick();;
			
			
			}
			
		}
		
		
		
	}
	
	int[] conversionLineTPlain(int i) {
		
		int res[]= new int[2];
		
		res[0]=i/num;//fila
		res[1]=i%num;//columna
		return res;
		
		
		
	}
	int conversionPlainToLine(int x, int y) {
		System.out.println("return: "+(x*num+y));
		return x*num+y;
		
	}
	
	int[] nearLastMov(){
		
		int res[] = new int[2],i=lastMov[0],j=lastMov[1];
		boolean found=false;
		Color[][] aux = tableAux();
		
		while(i<aux.length && i<lastMov[0]+3 && !found) {
			
			while(j<aux[i].length&& j<lastMov[1]+3 && !found) {
				
				if(aux[i][j]==Color.white ) {
					
					found=true;
					res[0]=i-1;
					res[1]=j-1;					
				}
				
				j++;
				
			}
			j=0;
			
			i++;
			
		}
		
		
		return res;
		
	}
	
	Color[][] tableAux(){
		
		Color[][] res= new Color[2+num][2+num]; 
		int j=0;
		
		for (int i = 0; i < res.length; i++) {
				
			
			for ( j = 0; j < res[i].length-1; j++) {
				
				if(i==0||i==res.length-1 || j==0 || j==res[i].length-1) {
					
					res[i][j]=Color.black;
				}else {
					
					res[i][j]=tablePaint[i-1][j-1];
				}
			}
			
			j=0;
			
		}
		
		System.out.println("Acabado aux");
		return res;
		
	}
	
	private boolean draw() {
		int j=0,i=0;
		boolean res= true;
		while(i<tablePaint.length && res) {
			
			while(j<tablePaint[i].length && res) {
				
				if(tablePaint[i][j]==Color.white) {
					
					res=false;
				}
				
				
				j++;
			}
			j=0;
			
			i++;
		}
		
		return res;
		
		
	}
	
	public boolean whoWins(boolean found,Color table[][],Color idPlayer) {
		int i=0,j=0;
		
		while(i<table.length && !found) {
			
			while(j<table[i].length && !found) {
				
				if(table[i][j]==Color.white) {
				table[i][j]=idPlayer;
				
				if(checkWin(table)) {
					
					found=true;
					System.out.println("te iba a ganar");
					botones[conversionPlainToLine(i, j)].doClick();
					
					//table[i][j]=idPlayer;
				}else {
					
					table[i][j]=Color.white;
				}
				}
				
				j++;
			}
			j=0;
			
			i++;
		}
		return found;
		
	}
	
	
	public void imprimirTablero() {
		
		for (int i = 0; i < tablePaint.length; i++) {
			
			for (int j = 0; j < tablePaint[i].length; j++) {
				
				System.out.print(String.valueOf(tablePaint[i][j].getRGB()) +" ");
				
			}
			System.out.println("");
			
		}
	}
	
	


}