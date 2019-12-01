import java.awt.EventQueue;

import javax.swing.JFrame;


public class AplicacaoSwing {
	private JFrame frame;
	private JogoBatalhaTerrestreComponente jogo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AplicacaoSwing window = new AplicacaoSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AplicacaoSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Jogo Batalha Terrestre");
		frame.setBounds(100, 60, 640, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		jogo = new JogoBatalhaTerrestreComponente();
		jogo.setBounds(20, 20, 640, 600);
		frame.getContentPane().add(jogo);
}}
