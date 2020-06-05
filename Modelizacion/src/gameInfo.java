import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class gameInfo extends JPanel {

	private String info;

	public gameInfo(boolean player2, boolean checkWin) {
		// TODO Auto-generated constructor stub
		setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		JLabel textInfo;

		if (checkWin) {
			
			if (!player2) {

				textInfo = new JLabel("Has perdido");
			} else {

				textInfo = new JLabel("Has ganado");

			}
			

		} else {
			textInfo = new JLabel("Empate");
			
		}

		add(textInfo);

	}

}