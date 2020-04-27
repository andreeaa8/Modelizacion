import java.awt.Color;
import java.util.concurrent.Semaphore;


public class AnyChance extends Thread{
	
	private boolean check =false;
	private Color table[][];
	private int x,y;
	Semaphore sem;
	
	public AnyChance(Color table[][]) {
		
		
		this.table=table;
		x=table.length;
		y=table[0].length;
		sem= new Semaphore(1);
		
		
	}
	
	public void run() {
		
		
		
		
		
	}
	
	

}
