
import java.awt.Color;




public class minimax extends Thread {

    private Color[][] table;
	private int score=Integer.MIN_VALUE;
	private Color idIa,colorHumano;

    public minimax(Color[][] table ,Color idIa,Color colorHumano){
		this.table= new Color[3][3];
        for (int i = 0; i < table.length; i++) {
			this.table[i]=table[i].clone();
			
		}
		this.colorHumano=colorHumano;
		this.idIa=idIa;
        
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();

       score= minimax(table, 0, false);


    }

    public int getScore() {
        return score;
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
			//System.out.println("acabado");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  (th1.isCheck() || th2.isCheck() || th3.isCheck() || th4.isCheck());

    }
    

    private boolean draw(Color[][] tablero) {
        int j = 0, i = 0;
        boolean res = true;
        while (i < tablero.length && res) {

            while (j < tablero[i].length && res) {

                if (tablero[i][j] == Color.WHITE) {

					res = false;
				}

				j++;
			}
			j = 0;

			i++;
		}

		return res;

	}

    public int minimax(Color[][] tablero, int depth, boolean isMaximizing) {
		Color[][] colores = tablero.clone();
		
		//antes de ir al bucle comprobamos si se ha acabado y quien ha ganado
		boolean result = checkWin(colores); // si alguien ha ganado
		if(result||draw(tablero)){

		if(result){
			
			return isMaximizing ? -1 : 1;
		}
		else{
			//imprimirTablero(colores);
			return 0;
		}
		}

		// implementa alpha y beta
		int score;		
		if (isMaximizing) {
			//IA true
			int bestScore = Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (colores[i][j] == Color.WHITE) {
						colores[i][j] = idIa;
						score = minimax(colores, depth + 1, false);
						colores[i][j] = Color.white;
						bestScore = Math.max(score, bestScore);
					}
				}
			}
			return bestScore;
		} else {
			//humano false
			int bestScore = Integer.MAX_VALUE;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (colores[i][j] == Color.white) {
						colores[i][j] = colorHumano;
						score = minimax(colores, depth + 1, true);
						colores[i][j] = Color.white;
						bestScore = Math.min(score, bestScore);
					}
				}

			}
			return bestScore;

		}
		
		
	}

}