import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class JogoBatalhaTerrestreComponente extends JPanel {
	private JLabel[][] grid;
	private JLabel lblResultado;
	private JLabel lblTiros;
	private JLabel lblAcertos;
	private JButton btnStart;
	private JogoBatalhaTerrestre jogo;

	public JogoBatalhaTerrestreComponente() {
		this.setLayout(null);
		this.setSize(640, 680);
		
		jogo = new JogoBatalhaTerrestre();
		
		lblTiros = new JLabel("Tiros: " + jogo.getTiros());
		lblTiros.setBounds(32, 525, 89, 14);
		add(lblTiros);
		
		lblAcertos = new JLabel("Acertos: " + jogo.getAcertos());
		lblAcertos.setBounds(32, 550, 106, 14);
		add(lblAcertos);
		
		lblResultado = new JLabel("Alvo: ");
		lblResultado.setBounds(32, 575, 270, 14);
		add(lblResultado);
		
		btnStart = new JButton("New Game");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jogo = new JogoBatalhaTerrestre();
				
				for (int x = 1; x < 11; x++) {
					for (int y = 1; y < 11; y++) {
						grid[x][y].setBackground(Color.WHITE);
					}
				}
				
				lblTiros.setText("Tiros: 0");
				lblAcertos.setText("Acertos: 0");
				lblResultado.setText("Alvo: ");
				jogo.limparArq();
			}
		});
		btnStart.setBounds(32, 478, 140, 23);
		add(btnStart);
		
		int n=11;
		grid = new JLabel[n][n];
		for(int x=1; x < n; x++){
			for(int y=1; y < n; y++){
				grid[x][y]=new JLabel();
				add(grid[x][y]);
				grid[x][y].setBounds(x*40, y*40, 40, 40);
				grid[x][y].setBackground(Color.WHITE);
				grid[x][y].setHorizontalAlignment(SwingConstants.CENTER);
				grid[x][y].setVerticalAlignment(SwingConstants.CENTER);
				grid[x][y].setBorder(new LineBorder(Color.DARK_GRAY, 2, true));
				grid[x][y].setOpaque(true);
				
				grid[x][y].addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){	
						JLabel b = (JLabel)e.getSource();
						int x = b.getX()/40;
						int y = b.getY()/40;
						
						try {
							String resposta = jogo.atirar(x, y);
							if (resposta == "Alvo") {
								grid[x][y].setBackground(Color.GREEN);
								lblAcertos.setText("Acertos: " + jogo.getAcertos());
								
							} else if (resposta == "Próximo") {
								grid[x][y].setBackground(Color.CYAN);

							} else if (resposta == "Distante") {
								grid[x][y].setBackground(Color.RED);
								
							}
							
							lblTiros.setText("Tiros: " + jogo.getTiros());	
							lblResultado.setText("Alvo: " + resposta);
							
							
						} catch (Exception e1) {
							lblResultado.setText("Alvo: " + e1.getMessage());
						}
						
						if (jogo.terminou()) {
							String result = jogo.getResultadoFinal();
							JOptionPane.showMessageDialog(null, result);
							jogo.limparArq();
							
						}
					}
				});
			}
		}
	}
}
