import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

class juego extends JPanel implements ActionListener {

	private Map<Integer, JButton> list;
	private Map<Integer, JTextPane> listPane;
	private Color tablePaint[][];
	private JButton botones[];
	JButton bb;
	Color AZUL_APAGADO = new Color(192, 192, 130);
	// JTextPane p[];
	int lastMov[], levels[];

	Color colorHumano, idIa;// first player is in red, second one in blue
	int humano = 0, ia = 1, fichasIa = 3, fichas1 = 3;
	int turn = 0;
	int num = 0;
	JFrame endGame;
	boolean end, beguin, found, cpu;

	public juego(int num, boolean begin, boolean cpu) {
		// reset();
		end = false;
		this.beguin = begin;
		this.cpu = cpu;
		this.num = num;
		if (begin) {
			colorHumano = Color.blue;
			idIa = Color.red;

		} else {
			idIa = Color.blue;
			colorHumano = Color.red;
		}
		System.out.println("tablero de " + num);
		tablePaint = new Color[num][num];
		botones = new JButton[num * num];
		// p= new JTextPane[num*num];
		int x = 50, y = 30, ancho = 70;
		int aux = x;
		// setLayout(new dispositionTable(num));
	private Map<Integer,JTextPane> listPane;
    private Color tablePaint[][]; 
    private JButton botones[];
    JButton bb;
    Color AZUL_APAGADO= new Color(192,192,130);
    //JTextPane p[];
    int lastMov[];
    
   
    Color idPlayer1,idIa;//first player is in red, second one in blue
    int humano=0,ia=1,fichasIa=3,fichas1=3;
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
			idIa=Color.red;
			
			
			}else {
			idIa=Color.blue;
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

		/*
		 * JButton boton1 = new JButton(); /*boton1.setOpaque(false);
		 * boton1.setContentAreaFilled(false); boton1.setBorderPainted(false); JTextPane
		 * p = new JTextPane(); p.setBackground(AZUL_APAGADO);
		 * 
		 * boton1.setBounds(50,30,70,70); p.setBounds(50, 30, 70, 70);
		 */
		int res[];
		for (int i = 0; i < num * num; i++) {

			res = conversionLineTPlain(i);

			tablePaint[res[0]][res[1]] = Color.white;
			botones[i] = new JButton();
			/*
			 * boton1.setOpaque(false); boton1.setContentAreaFilled(false);
			 * boton1.setBorderPainted(false);
			 */
			JTextPane p = new JTextPane();

			// p[i]=new JTextPane();
			// Component c1 = parent.getComponent(i+1);

			if (i % num == 0) {
				y += ancho;
				x = aux;
				botones[i].setBounds(x, y, ancho, ancho);
				p.setBounds(x, y, ancho, ancho);

			} else {

				x += ancho;
				botones[i].setBounds(x, y, ancho, ancho);
				p.setBounds(x, y, ancho, ancho);
				// System.out.println("holaa");
			}

			// p.disable();
			p.setBackground(Color.WHITE);
			botones[i].setOpaque(false);
			botones[i].setContentAreaFilled(false);
			botones[i].setBorderPainted(false);
			botones[i].setName("" + i);
			botones[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					Color panel = p.getBackground();

					if (panel == Color.WHITE && !end) {
						int res[] = conversionLineTPlain(Integer.parseInt(((JButton) e.getSource()).getName()));

						if (turn % 2 == 0) {

							p.setBackground(Color.red);
							tablePaint[res[0]][res[1]] = Color.red;
							turn++;

						} else {
							p.setBackground(Color.blue);
							turn++;
							tablePaint[res[0]][res[1]] = Color.blue;

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
							minimax(tablePaint,0,false,turn);	
							//algorithm(beguin);
							}
							
						}

					}

				}

			});

			add(botones[i]);
			if (beguin && i == 4 && cpu) {

				p.setBackground(idIa);
				tablePaint[1][1] = idIa;
				turn++;
				beguin = false;
			}
			add(p);

		}

		/*
		 * for (int i = 0; i < tablePaint.length; i++) {
		 * 
		 * for (int j = 0; j < tablePaint.length; j++) {
		 * 
		 * tablePaint[i][j]=Color.white;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 */

		imprimirTablero();
		System.out.println(beguin);
		System.out.println(this.cpu);

	}

	public juego(int num, boolean begin, boolean cpu, int a) {
		// reset();
		end = false;
		this.beguin = begin;
		this.cpu = cpu;
		this.num = num;
		if (begin) {
			colorHumano = Color.red;
			idIa = Color.blue;
			ia = 0;// jugador 2 es la maquina
			humano = 1;
		} else {
			idIa = Color.blue;
			colorHumano = Color.red;
		}
		System.out.println("tablero de " + num);
		tablePaint = new Color[num][num];
		botones = new JButton[num * num];
		// p= new JTextPane[num*num];
		int x = 50, y = 30, ancho = 70;
		int aux = x;
		// setLayout(new dispositionTable(num));
		setLayout(null);

		/*
		 * JButton boton1 = new JButton(); /*boton1.setOpaque(false);
		 * boton1.setContentAreaFilled(false); boton1.setBorderPainted(false); JTextPane
		 * p = new JTextPane(); p.setBackground(AZUL_APAGADO);
		 * 
		 * boton1.setBounds(50,30,70,70); p.setBounds(50, 30, 70, 70);
		 */
		int res[];
		for (int i = 0; i < num * num; i++) {

			res = conversionLineTPlain(i);

			tablePaint[res[0]][res[1]] = Color.white;
			botones[i] = new JButton();
			/*
			 * boton1.setOpaque(false); boton1.setContentAreaFilled(false);
			 * boton1.setBorderPainted(false);
			 */
			JTextPane p = new JTextPane();

			// p[i]=new JTextPane();
			// Component c1 = parent.getComponent(i+1);

			if (i % num == 0) {
				y += ancho;
				x = aux;
				botones[i].setBounds(x, y, ancho, ancho);
				p.setBounds(x, y, ancho, ancho);

			} else {

				x += ancho;
				botones[i].setBounds(x, y, ancho, ancho);
				p.setBounds(x, y, ancho, ancho);
				// System.out.println("holaa");
			}

			// p.disable();
			p.setBackground(Color.WHITE);
			botones[i].setOpaque(false);
			botones[i].setContentAreaFilled(false);
			botones[i].setBorderPainted(false);
			botones[i].setName("" + i);
			botones[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub

					Color panel = p.getBackground();

					int res[] = conversionLineTPlain(Integer.parseInt(((JButton) e.getSource()).getName()));

					if (panel == Color.WHITE && !end) {
						// quita fichas
						// player1 id=1, player2 id=2
						if (turn % 2 == humano && fichas1 > 0) {

							lastMov = res;
						if (turn % 2 == humano && fichas1 > 0) {
							
							lastMov=res;
							p.setBackground(Color.red);
							tablePaint[res[0]][res[1]] = Color.red;
							turn++;
							fichas1--;

						} else if (turn % 2 == ia && fichasIa > 0) {
							lastMov = res;
							p.setBackground(Color.blue);
							turn++;
							tablePaint[res[0]][res[1]] = Color.blue;
							fichasIa--;
						}

						for (int j = 0; j < res.length - 1; j++) {
							System.out.println(res[j] + " " + res[j + 1]);
							System.out.println(conversionPlainToLine(res[0], res[1]));

						}

						lastMov = res;

					}

					// pone Fichas Nuevas
					if (panel != Color.white && turn % 2 == ia && panel == idIa) {

						fichasIa++;

						tablePaint[res[0]][res[1]] = Color.white;
						p.setBackground(Color.white);

					}
					if (panel != Color.white && turn % 2 == humano && panel == colorHumano) {
					if (panel != Color.white && turn % 2 == humano && panel == idPlayer1) {

						fichas1++;

						tablePaint[res[0]][res[1]] = Color.white;
						p.setBackground(Color.white);

					}

					if (checkWin(tablePaint) || draw()) {
						reset();
						end = true;
						endGame.setVisible(true);
						endGame.setEnabled(true);
						imprimirTablero();

						System.out.println("Has ganado");

					} else {
						imprimirTablero();

						
						  if (cpu && turn % 2 == ia) {
						  
						  //algorithm(beguin);
							estrategiaGanadora();
						  
						  }
						 
						  

					}

				}

			});

			add(botones[i]);
			if (beguin && i == 4 && cpu) {

				p.setBackground(idIa);
				tablePaint[1][1] = idIa;
				turn++;
				beguin = false;
			}
			add(p);

		}

		/*
		 * for (int i = 0; i < tablePaint.length; i++) {
		 * 
		 * for (int j = 0; j < tablePaint.length; j++) {
		 * 
		 * tablePaint[i][j]=Color.white;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
		 */

		imprimirTablero();
		System.out.println(beguin);
		System.out.println(this.cpu);

	}
	//Conecta4
	public juego(int num, boolean begin, boolean cpu, char ch) {
		// reset();
		end = false;
		this.beguin = begin;
		this.cpu = cpu;
		this.num = num;
		this.levels = new int[7];
		if (begin) {
			colorHumano = Color.blue;
			idIa = Color.red;

		} else {
			idIa = Color.blue;
			colorHumano = Color.red;
		}
		System.out.println("tablero de conecta");
		System.out.println("->>" + num);
		tablePaint = new Color[num][7];
		botones = new JButton[num * 7];
		// p= new JTextPane[num*num];
		int x = 50, y = 30, ancho = 70;
		int aux = x;
		// setLayout(new dispositionTable(num));
		setLayout(null);

		/*
		 * JButton boton1 = new JButton(); /*boton1.setOpaque(false);
		 * boton1.setContentAreaFilled(false); boton1.setBorderPainted(false); JTextPane
		 * p = new JTextPane(); p.setBackground(AZUL_APAGADO);
		 * 
		 * boton1.setBounds(50,30,70,70); p.setBounds(50, 30, 70, 70);
		 */

		Arrays.fill(levels, 0);

		int res[];
		for (int i = 0; i < num * 7; i++) {

			res = conversionLineTPlainConnect4(i);
			System.out.println("Res:" + i);
			tablePaint[res[0]][res[1]] = Color.white;
			botones[i] = new JButton();
			/*
			 * boton1.setOpaque(false); boton1.setContentAreaFilled(false);
			 * boton1.setBorderPainted(false);
			 */
			JTextPane p = new JTextPane();

			// p[i]=new JTextPane();
			// Component c1 = parent.getComponent(i+1);

			if (i % 7 == 0) {
				y += ancho;
				x = aux;
				botones[i].setBounds(x, y, ancho, ancho);
				p.setBounds(x, y, ancho, ancho);

			} else {

				x += ancho;
				botones[i].setBounds(x, y, ancho, ancho);
				p.setBounds(x, y, ancho, ancho);
				// System.out.println("holaa");
			}

			// p.disable();
			p.setBackground(Color.WHITE);
			botones[i].setOpaque(false);
			botones[i].setContentAreaFilled(false);
			botones[i].setBorderPainted(false);
			botones[i].setName("" + i);
			botones[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int arrayAux[] = new int[2];
					Arrays.fill(arrayAux, -1);
					Color panel = p.getBackground();

					if (panel == Color.WHITE && !end) {
						int res[] = conversionLineTPlainConnect4(Integer.parseInt(((JButton) e.getSource()).getName()));
						int pos[] = availableConnect4(res[1]);

						if (Arrays.equals(pos, res)) {
							if (turn % 2 == ia) {

								p.setBackground(Color.red);
								tablePaint[pos[0]][pos[1]] = Color.red;
								turn++;

							} else {

								p.setBackground(Color.blue);
								turn++;
								tablePaint[res[0]][res[1]] = Color.blue;

							}

							for (int j = 0; j < res.length - 1; j++) {
								System.out.println(res[j] + " " + res[j + 1]);
								System.out.println(conversionPlaneToLineConnect4(res[0], res[1]));

							}

							lastMov = res;
							if (checkWin(tablePaint) || draw()) {
								reset();
								end = true;
								endGame.setVisible(true);
								endGame.setEnabled(true);
								imprimirTablero();

								System.out.println("Has ganado");

							} else {
								imprimirTablero();

								if (cpu) {

									// algorithm(beguin);
								}

							}

						} else if (!Arrays.equals(pos, arrayAux)) {

							System.out.println("me va a la posicion" + pos[0] + "_" + pos[1]);
							botones[conversionPlaneToLineConnect4(pos[0], pos[1])].doClick();

						}

					}
				}
			});

			add(botones[i]);
			if (beguin && i == 4 && cpu) {

				p.setBackground(idIa);
				tablePaint[1][1] = idIa;
				turn++;
				beguin = false;
			}
			add(p);

		}

		/*
		 * for (int i = 0; i < tablePaint.length; i++) {
		 * 
		 * for (int j = 0; j < tablePaint.length; j++) {
		 * 
		 * tablePaint[i][j]=Color.white;
		 * 
		 * }
		 * 
		 * }
		 * 
		 * 
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
		 * 
		 * Set<Entry<Integer,JButton>> l =list.entrySet();
		 * Iterator<Entry<Integer,JButton>> it = l.iterator();
		 * 
		 * boolean found= false; int i=0; while(it.hasNext() && !found){
		 * 
		 * Entry<Integer,JButton> aux = it.next();
		 * 
		 * JButton aux1 = (JButton) e.getSource();
		 * 
		 * if(aux1.equals(aux.getValue())) {
		 * 
		 * 
		 * found=true;
		 * 
		 * listPainted.put(aux.getKey(), true);
		 * 
		 * aux1.setBackground(Color.RED); list.put(aux.getKey(), aux1); }
		 * 
		 * }
		 */

		// bb.setBackground(Color.red);

		// repaint();

		/*
		 * if(turn%2==0) {
		 * 
		 * prueba.setBackground(Color.blue); turn++; }else {
		 * 
		 * prueba.setBackground(Color.red); turn++;
		 * 
		 * }
		 */

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

		return found = (th1.isCheck() || th2.isCheck() || th3.isCheck() || th4.isCheck());

	}

	private void reset() {

		endGame = new JFrame();
		Toolkit miPantalla = Toolkit.getDefaultToolkit();

		Dimension tamanoPantalla = miPantalla.getScreenSize();// devuelve el tamaño del monitor principal
		System.out.println(tamanoPantalla.height + " " + tamanoPantalla.width);
		int altura = tamanoPantalla.height;
		int largo = tamanoPantalla.width;
		endGame.setSize(largo / 8, altura / 8);
		endGame.setLocation(tamanoPantalla.width / 4 + largo / 8, tamanoPantalla.height / 4 + altura / 8);
		endGame.setLayout(new BorderLayout());
		JButton reiniciar = new JButton("Reiniciar");
		reiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (checkWin(tablePaint)) {
					endGame.dispose();
					System.out.println("Has ganado");
				}
			}

		});
		endGame.add(reiniciar, BorderLayout.SOUTH);
		endGame.add(new gameInfo(turn % 2, draw()), BorderLayout.CENTER);
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
		System.out.println(String.valueOf(idIa.getRGB()));

		if (tablePaint[lastMov[0]][lastMov[1]] != idIa) {

			if (!(found = whoWins(found, table, idIa))) {

				found = whoWins(found, table, colorHumano);

			}

			if (!found) {

				imprimirTablero();

				res = nearLastMov();

				botones[conversionPlainToLine(res[0], res[1])].doClick();
				;

			}

		}

	}

	int[] conversionLineTPlainConnect4(int i) {
		int res[] = new int[2];

		res[0] = i / 7;// fila
		System.out.println(res[0]);
		res[1] = i % 7;// columna
		System.out.println(res[1]);
		return res;

	}

	int[] conversionLineTPlain(int i) {

		int res[] = new int[2];

		res[0] = i / num;// fila
		res[1] = i % num;// columna
		return res;

	}

	int conversionPlainToLine(int x, int y) {
		System.out.println("return: " + (x * num + y));
		return x * num + y;

	}

	int conversionPlaneToLineConnect4(int x, int y) {
		System.out.println("return: " + (x * num + y));
		return x * 7 + y;
	}

	int[] nearLastMov() {

		int res[] = new int[2], i = lastMov[0], j = lastMov[1];
		boolean found = false;
		Color[][] aux = tableAux();

		while (i < aux.length && i < lastMov[0] + 3 && !found) {

			while (j < aux[i].length && j < lastMov[1] + 3 && !found) {

				if (aux[i][j] == Color.white) {

					found = true;
					res[0] = i - 1;
					res[1] = j - 1;
				}

				j++;

			}
			j = 0;

			i++;

		}

		return res;

	}

	Color[][] tableAux() {

		Color[][] res = new Color[2 + num][2 + num];
		int j = 0;

		for (int i = 0; i < res.length; i++) {

			for (j = 0; j < res[i].length - 1; j++) {

				if (i == 0 || i == res.length - 1 || j == 0 || j == res[i].length - 1) {

					res[i][j] = Color.black;
				} else {

					res[i][j] = tablePaint[i - 1][j - 1];
				}
			}

			j = 0;

		}

		System.out.println("Acabado aux");
		return res;

	}

	private boolean draw() {
		int j = 0, i = 0;
		boolean res = true;
		while (i < tablePaint.length && res) {

			while (j < tablePaint[i].length && res) {

				if (tablePaint[i][j] == Color.white) {

					res = false;
				}

				j++;
			}
			j = 0;

			i++;
		}

		return res;

	}

	public boolean whoWins(boolean found, Color table[][], Color idPlayer) {
		int i = 0, j = 0;

		while (i < table.length && !found) {

			while (j < table[i].length && !found) {

				if (table[i][j] == Color.white) {
					table[i][j] = idPlayer;

					if (checkWin(table)) {

						found = true;
						System.out.println("te iba a ganar");
						botones[conversionPlainToLine(i, j)].doClick();

						// table[i][j]=idPlayer;
					} else {

						table[i][j] = Color.white;
					}
	
	public boolean whoWins(boolean found,Color table[][],Color idPlayer) {
		int i=0,j=0;
		
		while(i<table.length && !found) {
			
			while(j<table[i].length && !found) {
				
				if(table[i][j]==Color.white) {
				table[i][j]=idPlayer;
				
				if(checkWin(table)) {
					
					found=true;
					System.out.println("Te iba a ganar");
					botones[conversionPlainToLine(i, j)].doClick();
					
					//table[i][j]=idPlayer;
				}else {
					
					table[i][j]=Color.white;
				}

				j++;
			}
			j = 0;

			i++;
		}
		return found;

	}

	public void imprimirTablero() {

		for (int i = 0; i < tablePaint.length; i++) {

			for (int j = 0; j < tablePaint[i].length; j++) {

				System.out.print(String.valueOf(tablePaint[i][j].getRGB()) + " ");

			}
			System.out.println("");

		}
	}

	// level es la columna que le pasamos
	int[] availableConnect4(int level) {

		int pos[] = new int[2];
		Arrays.fill(pos, -1);
		boolean found = false;
		int i = tablePaint.length - 1;

		while (i >= 0 && !found) {

			if (tablePaint[i][level] == Color.white) {
				found = true;
				pos[0] = i;
				pos[1] = level;

			}

			i--;

		}

		return pos;
	}

	public void estrategiaGanadora() {
		
		boolean move= false;
		
		Color[][]table = tablePaint.clone();
		
		if(tablePaint[1][1]==Color.white && fichasIa>0) {
			
			botones[4].doClick();
			move=true;
		
			
		}
		
		if (!(move = whoWins(found, table, idIa))) {

			move = whoWins(found, table, colorHumano);

		}
		
		
		
		
		if(!move) {
		int aux[]=nearLastMov();
		
		int op[]=opposite(aux[0], aux[1]);
		
		botones[conversionPlainToLine(op[0],op[1])].doClick();
		
		}

	}


	int[] opposite(int i, int j) {

		int res[] = new int[2];
		Arrays.fill(res, 1);

		if (i == 1 || j == 1) {

			switch (i) {
			case 1:
				if (j == 0) {

					res[0] = i;
					res[1] = 2;

				} else {

					res[0] = i;
					res[1] = 0;

				}

				break;

			default:

				if (i == 0) {

					res[0] = 2;
					res[1] = j;

				} else if(i==2){

					res[0] = 0;
					res[1] = j;

				}

				break;
			}

		}else {		
			if(i==j) {
				
				switch (i) {
				case 0:
					res[0]=2;
					res[1]=2;
					break;

				default:
					res[0]=0;
					res[1]=0;
					break;
				}
				
			}else {		
				res[0]=j;
				res[1]=i;
			}
			
			
			
		}

		return res;
	}
	
	private static int getRandomNumberRange( int min, int max) {		
		Random r= new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
	
	public void nextTurn(){
		ArrayList<Posicion> let= new ArrayList<Posicion>();
		for(int i=0;i<3;i++) {
			for( int j=0; j<3; j++) {
				if(tablePaint[i][j]==Color.white) {
					let.add(new Posicion(i,j));
				}
			}
		}
		Posicion move;
		int randomInt= getRandomNumberRange(0,let.size()-1);
		move=let.get(randomInt);
		tablePaint[move.getI()][move.getJ()]= idIa;
		turn+=1;	
	}
	
	public void bestMove(){
		Color [][] colores=tablePaint.clone();
		ArrayList<Posicion> let= new ArrayList<Posicion>();
		int bestScore=(int)Double.NEGATIVE_INFINITY ;
		Posicion move;
		for(int i=0;i<3;i++) {
			for( int j=0; j<3; j++) {
				if(colores[i][j]==Color.white) {
					colores[i][j]= idIa;
					int score=minimax(colores,0,false, turn);
					colores[i][j]=Color.white;
					if(score > bestScore) {
						bestScore= score;
						move=new Posicion(i,j);
						
						
					}
				}
			}
		}
		
		int randomInt= getRandomNumberRange(0,let.size()-1);
		move=let.get(randomInt);
		colores[move.getI()][move.getJ()]= idIa;
		turn+=1;	
	}
	
	
	
	public int minimax (Color[][] tablero, int depth, boolean isMaximizing, int turno) {
		Color [][] colores=tablePaint.clone();
		boolean result= checkWin(colores); //si alguien ha ganado
		int score;
		if(result) { // si es que si (true)
			if(turno%2==ia) {
				score=1;
			}
			else {
				score=-1;
			}
			return score;
			
		}
			
		if(isMaximizing) {
			int bestScore=(int)Double.NEGATIVE_INFINITY ;
			for(int i=0;i<3;i++) {
				for( int j=0; j<3; j++) {
					if(colores[i][j]==Color.white) {
						colores[i][j]= idIa;
						score= minimax(colores, depth+1, false, turno);
						colores[i][j]= Color.white;
						bestScore= Math.max(score, bestScore);
					}
				}
				
			}
			return bestScore;
		} else {
			int bestScore=(int)Double.POSITIVE_INFINITY ;
			for(int i=0;i<3;i++) {
				for( int j=0; j<3; j++) {
					if(colores[i][j]==Color.white) {
						colores[i][j]= idPlayer1;
						score= minimax(colores, depth+1, true, turno);
						colores[i][j]= Color.white;
						bestScore= Math.min(score, bestScore);
					}
				}
				
			}
			return bestScore;
			
		}
	
	}
}