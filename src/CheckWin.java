import java.awt.Color;

public class CheckWin extends Thread{

	private boolean check = false;
	private Color table[][];
	private int x, y, id;

	public CheckWin(Color table[][], int id) {

		this.table = table;
		x = table.length;
		y = table[0].length;
		this.id = id;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		int i = 0, j = 0;

		if (id == 1) {
			// by row
		
			while (i < x && !check) {
				
				boolean ant = true;
				while(j<y-1 && ant) {
					
				if (table[i][j].equals(table[i][j + 1]) && !table[i][j].equals(Color.white)) {
					ant=true;
				}else {
					ant=false;
				}
				
				j++;
				
				}
				
				
				if(ant) {
					
					check=true;
				}

				j = 0;
				i++;

			}
			
			//System.out.println("he acabado 1");

		} else if (id == 2) {
			// by columns
			
			while (j < y && !check) {

				boolean aux = true;

				while (i < x-1 && aux) {

					if (table[i][j].equals(table[i + 1][j])&& !table[i][j].equals(Color.white)) {
						aux = true;
					}else {
						aux=false;
					}
					i++;

				}
				
				
				if (aux) {
					check = true;
				}

				i = 0;
				j++;

			}
			
			//System.out.println("he acabado 2");

		} else if (id == 3) {
			// by diag L

			boolean ant = true;
			
			

			while (i < x - 1 && ant) {

				if (table[i][i].equals(table[i + 1][i + 1])&& !table[i][i].equals(Color.white)) {

					ant = true;
				} else {
					ant = false;
				}
				
				i++;

			}
			//System.out.println("he acabado 3");
			if (ant) {
				check = true;
			}

		} else {
			// by diag R

			boolean ant = true;
			
			while (i < x - 1  && ant) {

				if (table[x-1-i][i].equals(table[x-2-i][i+1])&& !table[x-1-i][i].equals(Color.white)) {
					ant = true;
				} else {
					ant = false;
				}
				
				i++;
				
				

			}
			//System.out.println("he acabado 4");
			
			if (ant) {
				check = true;
			}
			
		}

	}

	public boolean isCheck() {
		return check;
	}

}
