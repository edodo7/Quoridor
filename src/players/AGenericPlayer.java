package players;

import java.io.IOException;
import java.io.Serializable;

import board.Board;
import board.Case;
/**
 * Cette classe modelise le comportement de n'impote quel joueur(qu'il soit humain ou IA).
 * @author Eduardo Dom
 */
public abstract class AGenericPlayer implements Serializable{
	public static Case[][] plateau = Board.getTableau();
	protected int x;
	protected int y;
	public int walls;
	/**
	 * Permet de placer un mur a droite d'une case 
	 * @param case1 ,la case sur laquelle il faut placer le mur
	 */
	public void putWallRight(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if (i != 8){
			plateau[i][j].setEdgeRightBegin(true);
			plateau[i+1][j].setEdgeRightEnd(true);
			plateau[i][j+1].setEdgeLeftBegin(true);
			plateau[i+1][j+1].setEdgeLeftEnd(true);
		}
		else{
			plateau[i][j].setEdgeRightEnd(true);
			plateau[i-1][j].setEdgeRightBegin(true);
			plateau[i][j+1].setEdgeLeftEnd(true);
			plateau[i-1][j-1].setEdgeLeftBegin(true);
		}
	}
	/**
	 * Permet de placer un mur a gauche d'une case
	 * @param case1 ,la case sur laquelle il faut placer le mur
	 */
	public void putWallLeft(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if (i != 8){
			plateau[i][j].setEdgeLeftBegin(true);
			plateau[i+1][j].setEdgeLeftEnd(true);
			plateau[i][j-1].setEdgeRightBegin(true);
			plateau[i+1][j-1].setEdgeRightEnd(true);
		}
		else{
			plateau[i][j].setEdgeLeftEnd(true);
			plateau[i-1][j].setEdgeLeftBegin(true);
			plateau[i][j-1].setEdgeRightEnd(true);
			plateau[i-1][j-1].setEdgeRightBegin(true);
		}
	}
	/**
	 * Permet de placer un mur en haut d'une case 
	 * @param case1 ,la case sur laquelle il faut placer le mur
	 */
	public void putWallUp(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if ( j != 8){
			plateau[i][j].setEdgeUpBegin(true);
			plateau[i][j+1].setEdgeUpEnd(true);
			plateau[i-1][j].setEdgeDownBegin(true);
			plateau[i-1][j+1].setEdgeDownEnd(true);
		}
		else{
			plateau[i][j].setEdgeUpEnd(true);
			plateau[i][j-1].setEdgeUpBegin(true);
			plateau[i-1][j].setEdgeDownEnd(true);
			plateau[i-1][j-1].setEdgeDownBegin(true);
		}
	}
	/**
	 * Permet de placer un mur en bas d'une case
	 * @param case1 ,la case sur laquelle il faut placer le mur
	 */
	public void putWallDown(Case case1){
		int i = case1.getX();
		int j = case1.getY();
		if (j != 8){
			plateau[i][j].setEdgeDownBegin(true);
			plateau[i][j+1].setEdgeDownEnd(true);
			plateau[i+1][j].setEdgeUpBegin(true);
			plateau[i+1][j+1].setEdgeUpEnd(true);
		}
		else{
			plateau[i][j].setEdgeDownEnd(true);
			plateau[i][j-1].setEdgeDownBegin(true);
			plateau[i+1][j].setEdgeUpEnd(true);
			plateau[i+1][j-1].setEdgeUpBegin(true);
		}
	}
	
	/**
	 * Permet au joueur de se deplacer vers la droite
	 */
	public void moveRight(){
		plateau[x][y].setEmpty(true);
		y++;
		plateau[x][y].setEmpty(false);
	}
	/**
	 * Permet au joueur de se deplacer vers la gauche
	 */
	public void moveLeft(){
		plateau[x][y].setEmpty(true);
		y--;
		plateau[x][y].setEmpty(false);
	}
	/**
	 * Permet au joueur de se deplacer vers le haut
	 */
	public void moveUp(){
		plateau[x][y].setEmpty(true);
		x--;
		plateau[x][y].setEmpty(false);
	}
	/**
	 * Permet au joueur au joueur de se deplacer vers le bas
	 */
	public void moveDown(){
		plateau[x][y].setEmpty(true);
		x++;
		plateau[x][y].setEmpty(false);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int new_x){
		x = new_x;
	}
	
	public void setY(int new_y){
		y = new_y;
	}
	
	public abstract void play()throws IOException;
	public abstract boolean move(int x,int y);
}
