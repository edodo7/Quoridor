package players;
import java.util.Random;

import board.Board;
import rules.Rules;
import board.Case;
import java.io.IOException;
/**
 * Cette classe mod�lise L'IA facile ainsi que son comportement
 * @author Eduardo Dom
 *
 */
public class RandomAI extends AGenericPlayer{
	public static AGenericPlayer joueur2 = Board.getSecondPlayer();
	private boolean isFirstPlayer;
	private Case[][] plateau;
	
	public RandomAI (boolean FirstPlayer){
		this.isFirstPlayer = FirstPlayer;
		if(FirstPlayer){
			this.x = 0;
			this.y = 4;
		}
		else{
			this.x = 8;
			this.y = 4;
		}
		this.plateau = Board.getTableau();
		walls = 10;
	}
	
	
	/**
	 * Permet a l'IA de se deplacer vers une case
	 * @param i ,la ligne sur laquelle se trouve la case
	 * @param j ,la colonne sur laquelle se trouve la case
	 * @return Vrai 
	 */
	public boolean move(int i,int j){
		plateau[x][y].setEmpty(true);
		x = i;
		y = j;
		plateau[x][y].setEmpty(false);
		return true;
	}
	/**
	 * Permet a l'IA de jouer 
	 */
	public void play()throws IOException{
		Random whatChoice = new Random();
		int WallOrMove = whatChoice.nextInt(2);
		if (WallOrMove == 0 && walls > 0){//on place un mur 
			boolean not_done = true;
			while(not_done){
				int wall = whatChoice.nextInt(4);
				int i = whatChoice.nextInt(7)+1;
				int j = whatChoice.nextInt(7)+1;
				if (wall == 0 && Rules.canPutWallUp(plateau[i][j]) && Rules.canReallyPutWallUp(plateau[i][j])){
					putWallUp(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur au dessus de la case en position ("+i+","+j+")");
					walls--;
					not_done = false;
				}
				if (wall == 1 && Rules.canPutWallDown(plateau[i][j])&& Rules.canReallyPutWallDown(plateau[i][j])){
					putWallDown(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur en dessous de la case en position ("+i+","+j+")");
					walls--;
					not_done = false;
				}				
				if (wall == 2 && Rules.canPutWallRight(plateau[i][j])&& Rules.canReallyPutWallRight(plateau[i][j])){
					putWallRight(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur � droite de la case en position ("+i+","+j+")");
					walls--;
					not_done = false;
				}
				if (wall == 3 && Rules.canPutWallLeft(plateau[i][j])&& Rules.canReallyPutWallLeft(plateau[i][j])){
					putWallLeft(plateau[i][j]);
					System.out.println("L'IA facile a pos� un mur � gauche de la case en position ("+i+","+j+")");
					walls--;
					not_done = false;
				}
			}
		}
		else{
			boolean not_done = true;
			while(not_done){
				int whatDirection = whatChoice.nextInt(4);
				if (whatDirection == 0 && Rules.canMoveUp(this)){
					moveUp();
					not_done = false;
				}
				if (whatDirection == 1 && Rules.canMoveDown(this)){
					moveDown();
					not_done = false;
				}
				if (whatDirection == 2 && Rules.canMoveLeft(this)){
					moveLeft();
					not_done = false;
				}
				if(whatDirection == 3 && Rules.canMoveRight(this)){
					moveRight();
					not_done = false;
				}
			}
		}
	}
}