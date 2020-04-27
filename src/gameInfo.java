import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class gameInfo extends JPanel {

	private String info;

	public gameInfo(int num, boolean draw) {
		// TODO Auto-generated constructor stub
		setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		JLabel textInfo;

		if (draw) {
			textInfo = new JLabel("Empate");

		} else {
			if (num == 0) {

				textInfo = new JLabel("Has ganado");
			} else {

				textInfo = new JLabel("Has perdido");

			}
		}

		add(textInfo);

	}

}