import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class dispositionTable implements LayoutManager{
	
	int x=50,y=30;
    final int ancho=70;
    int table;
    public dispositionTable(int table) {
		// TODO Auto-generated constructor stub
    	
    	this.table=table;
	}
    
	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void layoutContainer(Container parent) {
		// TODO Auto-generated method stub
		
		int count = parent.getComponentCount();
		int aux= x;
		for (int i = 0; i < count/2; i++) {
			
			Component c = parent.getComponent(i);
			//Component c1 = parent.getComponent(i+1);
			if(i%table==0) {
			y+=ancho;
			x=aux;
			c.setBounds(x,y,ancho,ancho);
			//c1.setBounds(x,y,ancho,ancho);
			}else {
				
				x+=ancho;
				c.setBounds(x,y,ancho,ancho);
				//c1.setBounds(x, y, ancho, ancho);
				//System.out.println("holaa");
			}
			
		}
		
		y=30;
		x=aux;
		for (int i = count/2; i < count; i++) {
			
			Component c = parent.getComponent(i);
			//Component c1 = parent.getComponent(i+1);
			if(i%table==0) {
			y+=ancho;
			x=aux;
			c.setBounds(x,y,ancho,ancho);
			//c1.setBounds(x,y,ancho,ancho);
			}else {
				
				x+=ancho;
				c.setBounds(x,y,ancho,ancho);
				//c1.setBounds(x, y, ancho, ancho);
				//System.out.println("holaa");
			}
			
		}
		y=30;
		x=aux;
		
	}
	
	
	
	
}