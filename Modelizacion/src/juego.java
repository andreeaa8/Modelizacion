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
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.TabableView;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

class juego extends JPanel implements ActionListener {

	private Map<Integer, JButton> list;
	private Map<Integer, JTextPane> listPane;
	private Map<Integer,Integer> correspondencia;
	private Color tablePaint[][];
	private JButton botones[];
	ArrayList<Posicion> movimientosQueFaltan;
	JButton bb;
	Color AZUL_APAGADO = new Color(192, 192, 130);
	// JTextPane p[];
	int lastMov[], levels[], winnerMove[];

	Color colorHumano, idIa;// first player is in red, second one in blue
	int humano = 0, ia = 1, fichasIa = 3, fichas1 = 3;
	int turn = 0;
	int num = 0;
	JFrame endGame;
	boolean end, beguin, found, cpu, movimientoFinal, flag;
	int mode;

	public juego(int num, boolean begin, boolean cpu) {
		// reset();
		end = false;
		this.beguin = begin;
		this.cpu = cpu;
		this.num = num;
		this.mode = 0;
		winnerMove = new int[2];
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

						if (checkWin(tablePaint) || draw()) {
							reset();
							end = true;
							endGame.setVisible(true);
							endGame.setEnabled(true);
							imprimirTablero(tablePaint);

							System.out.println("Has ganado");

						} else {
							imprimirTablero(tablePaint);

							if (cpu) {
								//minimax(tablePaint, 0, false, turn);
								algorithm(beguin);
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
		Arrays.fill(winnerMove, -1);
		imprimirTablero(tablePaint);
		System.out.println(beguin);
		System.out.println(this.cpu);

	}

	public juego(int num, boolean begin, boolean cpu, int mode) {
		// reset();
		end = false;
		this.beguin = begin;
		this.cpu = cpu;
		this.num = num;
		this.mode = 1;
		flag = true;
		winnerMove = new int[2];
		Arrays.fill(winnerMove, -1);
		movimientoFinal = false;
		correspondencia=  new HashMap<Integer, Integer>();
		movimientosQueFaltan = new ArrayList<Posicion>();
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
						lastMov = res;
						tablePaint[res[0]][res[1]] = Color.white;
						p.setBackground(Color.white);

					}
					if (panel != Color.white && turn % 2 == humano && panel == colorHumano) {

						fichas1++;
						lastMov = res;
						tablePaint[res[0]][res[1]] = Color.white;
						p.setBackground(Color.white);

					}

					if (checkWin(tablePaint) || draw()) {
						reset();
						end = true;
						endGame.setVisible(true);
						endGame.setEnabled(true);
						imprimirTablero(tablePaint);

						System.out.println("Has ganado");

					} else {
						imprimirTablero(tablePaint);

						if (cpu && turn % 2 == ia && flag) {

							// algorithm(beguin);
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
				fichasIa--;
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

		imprimirTablero(tablePaint);
		System.out.println(beguin);
		System.out.println(this.cpu);

	}

	// Conecta4
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
								movimientosQueFaltan = new ArrayList<Posicion>();
								end = true;
								endGame.setVisible(true);
								endGame.setEnabled(true);
								imprimirTablero(tablePaint);

								System.out.println("Has ganado");

							} else {
								imprimirTablero(tablePaint);

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

		imprimirTablero(tablePaint);
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
		endGame.add(new gameInfo(turn % 2 == ia, checkWin(tablePaint)), BorderLayout.CENTER);
		endGame.setEnabled(false);

	}

	void algorithm(boolean beguin) {
		int res[] = new int[2], i = 0, j = 0;
		boolean found = false;

		Color[][] table = tablePaint;

		if (table[1][1] == Color.white) {

			res = conversionLineTPlain(4);
			this.beguin = false;
			botones[4].doClick();

		}
		System.out.println(String.valueOf(idIa.getRGB()));

		if (tablePaint[lastMov[0]][lastMov[1]] != idIa) {

			if (!(found = whoWins(found, table, idIa))) {

				found = whoWins(found, table, colorHumano);

			}

			if (!found) {

				imprimirTablero(tablePaint);

				res = nearLastMov(tablePaint);

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

	int[] nearLastMov(Color[][] table) {

		int res[]= new int[2]; 
		Arrays.fill(res, -1);
		int i = lastMov[0], j = lastMov[1];
		boolean found = false;
		Color[][] aux = tableAux(table);

		while (i < aux.length && i < lastMov[0] + 3 && !found) {

			while (j < aux[i].length && j < lastMov[1] + 3 && !found) {

				if (aux[i][j] == Color.white) {

					found = true;
					res[0] = i - 1;
					res[1] = j - 1;
				}

				j++;

			}
			j = lastMov[1];

			i++;

		}

		return res;

	}

	Color[][] tableAux(Color[][] tablePaint) {

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
						if (fichasIa > 0) {
							System.out.println("Te iba a ganar");
							System.out.println(i + ":" + j);
							botones[conversionPlainToLine(i, j)].doClick();
						} else {

							winnerMove[0] = i;
							winnerMove[1] = j;
						}

						// table[i][j]=idPlayer;
					} else {

						table[i][j] = Color.white;
					}

				}
				j++;

			}
			j = 0;

			i++;

		}
		return found;

	}

	public void imprimirTablero(Color[][] tablePaint) {

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

		boolean move = false;
		int[] corner = { 0, 0 }, corner1 = { 2, 0 }, corner2 = { 0, 2 }, corner3 = { 2, 2 };
		ArrayList<int[]> corners = new ArrayList<int[]>();
		corners.add(corner);
		corners.add(corner1);
		corners.add(corner2);
		corners.add(corner3);

		Color[][] table = new Color[num][num];

		for (int i = 0; i < table.length; i++) {

			table[i] = Arrays.copyOf(tablePaint[i], num);

		}

		if (tablePaint[1][1] == Color.white && fichasIa > 0) {

			botones[4].doClick();
			move = true;

		}

		if (!movimientoFinal) {

			if (!(move = whoWins(found, table, idIa))) {

				move = whoWins(found, table, colorHumano);

				if (move) {

					movimientoFinal = true;
				}

			}

		}

		if (!move && !movimientoFinal) {
			int aux[] = nearLastMov(tablePaint);

			if (aux[1] == 1 && aux[0] == 0 && lastMov[0] == 1 && lastMov[1] == 2) {

				aux[1] += 1;
			}

			if (aux[1] == 0 && aux[0] == 1 && lastMov[0] == 2 && lastMov[1] == 1) {

				aux[0] += 1;
			}

			int op[] = opposite(aux[0], aux[1]);

			botones[conversionPlainToLine(op[0], op[1])].doClick();

		}

		if (turn % 2 == ia && fichasIa == 0) {

			whoWins(found, table, idIa);
			
			int[] posibleSolution=Arrays.copyOf(winnerMove, 2);
			if(winnerMove[0]!=-1){
			table[winnerMove[0]][winnerMove[1]]=Color.black;
			imprimirTablero(table);
			whoWins(false, table, idIa);
			// hay dos posibles soluciones?
			if(!Arrays.equals(posibleSolution,winnerMove)){
				
				// sabemos que hay dos soluciones
				// solo hay que elejir la ganadora 
				if(Arrays.equals(corner, winnerMove)||Arrays.equals(corner1, winnerMove)||Arrays.equals(corner2, winnerMove)||Arrays.equals(corner3, winnerMove)){
					table[winnerMove[0]][winnerMove[1]]=Color.BLACK;
					winnerMove=Arrays.copyOf(posibleSolution,2);
				 
				}
				
			}
				// solo hay una posible solucion en winner
				table[winnerMove[0]][winnerMove[1]]=Color.white;
		}
			if (winnerMove[0] != -1) {

				// if (movimientosQueFaltan.isEmpty()) {

				for (int i = 0; i < table.length; i++) {

					table[i] = Arrays.copyOf(tablePaint[i], num);

				}

				// si hay dos soluciones puede que una de ellas nos la han bloqueado
				// tenemos que ver cual de las dos no esta bloqueada y dar a la que podemos
				// llegar

				int[] posibleStep = availableWinnerOneStep(table);

				if (posibleStep[0] != -1) {

					flag = false;
					botones[conversionPlainToLine(posibleStep[0], posibleStep[1])].doClick();
					botones[conversionPlainToLine(winnerMove[0], winnerMove[1])].doClick();
					flag = true;

				} else {
					//puede haber mas de una 
					
					
					
					
					// caso 2
					flag = false;
					ArrayList<Posicion> cornersAux = opositesCorners(winnerMove);

					if (tablePaint[cornersAux.get(0).getI()][cornersAux.get(0).getJ()] == idIa) {

						if ((winnerMove[1] - cornersAux.get(0).getJ()) == 0) {

							movimientosQueFaltan = getProperLineCol(tablePaint, winnerMove[1]);
							botones[conversionPlainToLine(cornersAux.get(0).getI(),cornersAux.get(0).getJ())].doClick();

						} else {

							movimientosQueFaltan = getProperLineFila(tablePaint, winnerMove[0]);
							botones[conversionPlainToLine(cornersAux.get(0).getI(),cornersAux.get(0).getJ())].doClick();
						}

					} else {

						if ( (winnerMove[1] - cornersAux.get(1).getJ()) == 0) {

							movimientosQueFaltan = getProperLineCol(tablePaint, winnerMove[1]);
							botones[conversionPlainToLine(cornersAux.get(1).getI(),cornersAux.get(1).getJ())].doClick();

						} else {

							movimientosQueFaltan = getProperLineFila(tablePaint, winnerMove[0]);
							botones[conversionPlainToLine(cornersAux.get(1).getI(),cornersAux.get(1).getJ())].doClick();
						}

					}

					

					int auxi = movimientosQueFaltan.get(movimientosQueFaltan.size() - 1).getI(),
							auxj = movimientosQueFaltan.get(movimientosQueFaltan.size() - 1).getJ();

					
					botones[conversionPlainToLine(auxi, auxj)].doClick();

					movimientosQueFaltan.remove(movimientosQueFaltan.size() - 1);

					flag = true;

				}
				// }

			} else {

				// caso1
				// no hay movimiento ganador estamos en el caso 1 hay que hacer dos mov
				// 1º localiamos esquina
				boolean found = false;
				int i = 0;
				while (i < corners.size() && !found) {

					if (tablePaint[corners.get(i)[0]][corners.get(i)[1]] == idIa) {

						found = true;
						// 2º con la funcion miramos la columna y fila a ver si hay algun movimiento
						// permitido (2 mov)

						getProperLine(tablePaint);
						flag = false;
						Color[][] tableAux = tableAux(tablePaint);
						int iAux = movimientosQueFaltan.get(movimientosQueFaltan.size() - 1).getI() + 1;
						int jAux = movimientosQueFaltan.get(movimientosQueFaltan.size() - 1).getJ() + 1;

						found = false;

						if (tableAux[iAux - 1][jAux] == idIa && tableAux[iAux - 1][jAux] != Color.black) {

							found = true;
							botones[conversionPlainToLine(iAux - 2, jAux - 1)].doClick();

						}

						if (!found && tableAux[iAux + 1][jAux] == idIa && tableAux[iAux + 1][jAux] != Color.black) {

							found = true;
							botones[conversionPlainToLine(iAux, jAux - 1)].doClick();

						}

						if (!found && tableAux[iAux][jAux + 1] == idIa && tableAux[iAux][jAux + 1] != Color.black) {

							found = true;
							botones[conversionPlainToLine(iAux - 1, jAux)].doClick();

						}

						if (!found && tableAux[iAux][jAux - 1] == idIa && tableAux[iAux][jAux - 1] != Color.black) {

							found = true;
							botones[conversionPlainToLine(iAux - 1, jAux - 2)].doClick();

						}

						imprimirTablero(tablePaint);

						botones[conversionPlainToLine(iAux - 1, jAux - 1)].doClick();
						imprimirTablero(tablePaint);
						movimientosQueFaltan.remove(movimientosQueFaltan.size() - 1);
						flag = true;

					}

					i++;

				}

			}
		}

	}

	ArrayList<Posicion> opositesCorners(int[] corner) {

		ArrayList<Posicion> res = new ArrayList<Posicion>();

		if (corner[0] == corner[1]) {
			// primero fila y luego columna
			res.add(new Posicion(2, 0));
			res.add(new Posicion(0, 2));

		} else {
			// primero fila y luego columna
			res.add(new Posicion(0, 0));
			res.add(new Posicion(2, 2));

		}

		return res;
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

				} else if (i == 2) {

					res[0] = 0;
					res[1] = j;

				}

				break;
			}

		} else {
			if (i == j) {

				switch (i) {
				case 0:
					res[0] = 2;
					res[1] = 2;
					break;

				default:
					res[0] = 0;
					res[1] = 0;
					break;
				}

			} else {
				res[0] = j;
				res[1] = i;
			}

		}

		return res;
	}

	// hay que asegurarse de que la llamada se hace bien
	private void getProperLine(Color[][] table) {

		movimientosQueFaltan = new ArrayList<Posicion>();

		// fila sup
		movimientosQueFaltan = getProperLineFila(table, 0);
		// fila inf

		if (movimientosQueFaltan.isEmpty()) {
			movimientosQueFaltan = getProperLineFila(table, 2);
		}
		// col sup
		if (movimientosQueFaltan.isEmpty()) {
			movimientosQueFaltan = getProperLineCol(table, 0);
		}
		// col inf
		if (movimientosQueFaltan.isEmpty()) {
			movimientosQueFaltan = getProperLineCol(table, 2);
		}

	}

	private ArrayList<Posicion> getProperLineFila(Color[][] table, int fil) {

		ArrayList<Posicion> res = new ArrayList<Posicion>();

		int i = 0;

		while (i < table[fil].length - 1) {

			if (table[fil][i] == table[fil][i + 1] && table[fil][i + 1] == Color.white) {

				res.add(new Posicion(fil, i));
				res.add(new Posicion(fil, i + 1));

			}

			i++;

		}

		return res;
	}

	private ArrayList<Posicion> getProperLineCol(Color[][] table, int col) {

		ArrayList<Posicion> res = new ArrayList<Posicion>();

		int i = 0;

		while (i < table.length - 1) {

			if (table[i][col] == table[i + 1][col] && table[i + 1][col] == Color.white) {

				res.add(new Posicion(i, col));
				res.add(new Posicion(i + 1, col));

			}

			i++;

		}

		return res;

	}

	private int[] availableWinnerOneStep(Color[][] tableOr) {
		// desde la posicion de winner hay qie mover fichas y ver que ganamos
		boolean found = false;
		int i = winnerMove[0] + 1, j = winnerMove[1] + 1;
		int[] res = new int[2];
		Arrays.fill(res, -1);

		Color[][] table = tableAux(tablePaint);

		if (table[i - 1][j] == idIa && table[i - 1][j] != Color.black) {

			tableOr[i - 2][j - 1] = Color.white;
			tableOr[winnerMove[0]][winnerMove[1]] = idIa;
			imprimirTablero(tableOr);
			if (checkWin(tableOr)) {

				found = true;
				res[0] = i - 2;
				res[1] = j - 1;
			}

			tableOr[i - 2][j - 1] = idIa;
			tableOr[winnerMove[0]][winnerMove[1]] = Color.white;
			imprimirTablero(tableOr);
		}

		if (!found && table[i + 1][j] == idIa && table[i + 1][j] != Color.black) {

			tableOr[i][j - 1] = Color.white;
			imprimirTablero(tableOr);
			tableOr[winnerMove[0]][winnerMove[1]] = idIa;
			imprimirTablero(tableOr);
			if (checkWin(tableOr)) {

				found = true;
				res[0] = i;
				res[1] = j - 1;
			}

			tableOr[i][j - 1] = idIa;
			tableOr[winnerMove[0]][winnerMove[1]] = Color.white;

		}

		if (!found && table[i][j + 1] == idIa && table[i][j + 1] != Color.black) {

			tableOr[i - 1][j] = Color.white;
			tableOr[winnerMove[0]][winnerMove[1]] = idIa;
			imprimirTablero(tableOr);
			if (checkWin(tableOr)) {

				found = true;
				res[0] = i - 1;
				res[1] = j;
			}

			tableOr[i - 1][j - 1] = idIa;
			tableOr[winnerMove[0]][winnerMove[1]] = Color.white;
		}

		if (!found && table[i][j - 1] == idIa && table[i][j - 1] != Color.black) {

			tableOr[i - 1][j - 2] = Color.white;
			tableOr[winnerMove[0]][winnerMove[1]] = idIa;
			imprimirTablero(tableOr);
			if (checkWin(tableOr)) {

				found = true;
				res[0] = i - 1;
				res[1] = j - 2;
			}
			tableOr[i - 1][j - 2] = idIa;
			tableOr[winnerMove[0]][winnerMove[1]] = Color.white;
		}

		return res;
	}

	private static int getRandomNumberRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public void nextTurn() {
		ArrayList<Posicion> let = new ArrayList<Posicion>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tablePaint[i][j] == Color.white) {
					let.add(new Posicion(i, j));
				}
			}
		}
		Posicion move;
		int randomInt = getRandomNumberRange(0, let.size() - 1);
		move = let.get(randomInt);
		tablePaint[move.getI()][move.getJ()] = idIa;
		turn += 1;
	}

	public void bestMove() {
		Color[][] colores = tablePaint.clone();
		ArrayList<Posicion> let = new ArrayList<Posicion>();
		int bestScore = (int) Double.NEGATIVE_INFINITY;
		Posicion move;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (colores[i][j] == Color.white) {
					colores[i][j] = idIa;
					int score = minimax(colores, 0, false, turn);
					colores[i][j] = Color.white;
					if (score > bestScore) {
						bestScore = score;
						move = new Posicion(i, j);

					}
				}
			}
		}

		int randomInt = getRandomNumberRange(0, let.size() - 1);
		move = let.get(randomInt);
		colores[move.getI()][move.getJ()] = idIa;
		turn += 1;
	}

	public int minimax(Color[][] tablero, int depth, boolean isMaximizing, int turno) {
		Color[][] colores = tablePaint.clone();
		boolean result = checkWin(colores); // si alguien ha ganado
		int score;
		if (result) { // si es que si (true)
			if (turno % 2 == ia) {
				score = 1;
			} else {
				score = -1;
			}
			return score;

		}

		if (isMaximizing) {
			int bestScore = (int) Double.NEGATIVE_INFINITY;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (colores[i][j] == Color.white) {
						colores[i][j] = idIa;
						score = minimax(colores, depth + 1, false, turno);
						colores[i][j] = Color.white;
						bestScore = Math.max(score, bestScore);
					}
				}

			}
		} else {
			int bestScore = (int) Double.POSITIVE_INFINITY;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (colores[i][j] == Color.white) {
						colores[i][j] = colorHumano;
						score = minimax(colores, depth + 1, true, turno);
						colores[i][j] = Color.white;
						bestScore = Math.min(score, bestScore);
					}
				}

			}
			return bestScore;

		}
		return 5;
	}

    public void girar(int var){
		//var=0 no hace nada 
		//var=1 gira 90º
		//var=2 gira 180º
		//var=3 gira 270º
		//posición girada
		int res,aux;
		switch (var) {
			case 0:
				//AQUI NO SE HACE NAAAAADAAAAAAAAA
				aux=0;
				break;
			case 1:
				
			aux=2;
				break;
			case 2:

			aux=4;	
				break;
			
			default:
			aux=6;
				break;
		}
		
		for(int i=1; i< 9;i++){
			res =  (i-aux)%8;
			correspondencia.put(i, res);
			
		}
	
	}
    
    
    private ArrayList<Posicion> positionsPermited(){
    	boolean found=false;
    	ArrayList<Posicion> res = new ArrayList<Posicion>();
    	Color[][] tabla = new Color[3][3];
    	int[] elem; 
    	
    	for (int i = 0; i < tabla.length; i++) {
    		
    		tabla[i]=Arrays.copyOf(tablePaint[i],3);
			
		}
    	
    	
    	while(!found) {
    		
    		elem=nearLastMov(tabla);
    		
    		
    		if(elem[0]==-1) {
    			
    			found=true;
    			
    		}else {
    			res.add(new Posicion(elem[0],elem[1]));
    			tabla[elem[0]][elem[1]]=Color.black;
    		}
    		
    		
    	}
    	
    	
    	
    	
    	
    	
    	return res;
    	
    }
    
	
}


