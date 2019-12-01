import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class JogoBatalhaTerrestre {
	private int [][] board = new int[10][10];
	private int tiros = 0;
	private int acertos = 0;
	
	public JogoBatalhaTerrestre() {
		Random randint = new Random();
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = 3;
			}
		}
		
		int i = 0;
		while (i < 5) {
			int x = randint.nextInt(10);
			int y = randint.nextInt(10);
			
			if (board[x][y] == 3) {
				board[x][y] = 1;
				i ++;
				
				if (x == 0 && y == 0) {
					board[0][1] = 2;
					board[1][0] = 2;
					board[1][1] = 2;
				}
				else if (x == 0 && y == 9) {
					board[0][8] = 2;
					board[1][8] = 2;
					board[1][9] = 2;
				}
				else if (x == 0 && (y != 9 && y != 0)) {
					board[0][y - 1] = 2;
					board[0][y + 1] = 2;
					board[1][y] = 2;
					board[1][y - 1] = 2;
					board[1][y + 1] = 2;
				}
				else if ((x != 9 && x != 0) && y == 0) {
					board[x - 1][0] = 2;
					board[x + 1][0] = 2;
					board[x][1] = 2;
					board[x - 1][1] = 2;
					board[x + 1][1] = 2;
				}
				else if (x == 9 && y == 0) {
					board[8][0] = 2;
					board[8][1] = 2;
					board[9][1] = 2;
				}
				else if (x == 9 && (y != 0 && y != 9)) {
					board[8][y - 1] = 2;
					board[8][y + 1] = 2;
					board[8][y] = 2;
					board[9][y - 1] = 2;
					board[9][y + 1] = 2;
				}
				else if (x == 9 && y == 9) {
					board[8][9] = 2;
					board[9][8] = 2;
					board[9][9] = 2;
				}
				else if ((x != 0 && x != 9) && y == 9) {
					board[x - 1][9] = 2;
					board[x + 1][9] = 2;
					board[x][8] = 2;
					board[x - 1][8] = 2;
					board[x + 1][8] = 2;
				}
				else if ((x != 0 && x != 9) && (y != 0 && y != 9)) {
					board[x][y - 1] = 2;
					board[x][y + 1] = 2;
					board[x - 1][y] = 2;
					board[x + 1][y] = 2;
					board[x - 1][y - 1] = 2;
					board[x - 1][y + 1] = 2;
					board[x + 1][y - 1] = 2;
					board[x + 1][y + 1] = 2;
				}
				
			}
		}		
	}

	public int[][] getBoard() {
		return board;
	}
	
	private void gravar(int x, int y, String message) {
		
		File file = new File("Tiros.txt");
		Scanner sc = null;
		StringBuilder temp = new StringBuilder();
		
		x++;
		y++;
		
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				temp.append(sc.nextLine() + "\n");
			}
			FileWriter arq;
			arq = new FileWriter(new File("Tiros.txt"));
			arq.write(temp.toString());
			arq.write(String.format("[%d][%d] %s", x, y, message));
			arq.close();
		}
		
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
	}
	
	public String atirar(int x, int y) throws Exception {
		int [][]m =board;
		String message = "";
		x--;
		y--;
		
		if ((x < 0 || x > 9) || (y < 0 || y > 9)) {
			throw new Exception("Coordenadas Inválidas !!!");
		
		} 
		
		if (m[x][y] == 0) {
			throw new Exception("Já atirou aqui !!!");
			
		}
		
		else {
			tiros ++;
			
			if (m[x][y] == 1) {
				m[x][y] = 0;
				acertos ++;
				message = "Alvo"; 
				gravar(x, y, message);
				
			} else if (m[x][y] == 2) {
				m[x][y] = 0;
				message = "Próximo";
				gravar(x, y, message);
				
			} else if (m[x][y] == 3) {
				m[x][y] = 0;
				message = "Distante";
				gravar(x, y, message);
				
			}

		} return message;
		
	}

	public int getTiros() {
		return tiros;
	}


	public int getAcertos() {
		return acertos;
	}
	
	public boolean terminou() {
		if (tiros == 20 || acertos == 5) {
			return true;
		}
		return false;
	}
	
	public String getResultadoFinal() {
		File file = new File("Tiros.txt");
		Scanner sc = null;
		StringBuilder temp = new StringBuilder();
		
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				temp.append(sc.nextLine() + "\n");
			}
			FileWriter arq;
			arq = new FileWriter(new File("Tiros.txt"));
			
			if (acertos == 5) {
				arq.write("Você venceu!\n");
			} else {
				arq.write("Você perdeu!\n");
			}
			arq.write("Com " + tiros + " tentativas e " + acertos + " acertos.");
			arq.write("\nHistórico:\n");
			arq.write(temp.toString());
			arq.close();
		}
		
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		StringBuilder res = new StringBuilder();
		
		try {
			sc = new Scanner(file);
			while(sc.hasNextLine()) {
				res.append(sc.nextLine() + "\n");
			}
		}
		
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		
		return res.toString();
	
	}
	
	public void limparArq() {
		
		FileWriter arq;
		try {
			arq = new FileWriter(new File("Tiros.txt"));
			arq.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder tab = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (this.getBoard()[i][j] != 0) {
					tab.append(j != 9 ? "- " : "-\n");
				} else {
					if (j != 9) {
						tab.append(this.getBoard()[i][j] + " ");
					} else {
						tab.append(this.getBoard()[i][j] + "\n");
					}
				}
			}
		} return tab.toString();
	}
}
