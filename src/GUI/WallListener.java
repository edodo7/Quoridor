package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import board.Board;
import board.Case;
import mainAndRules.Rules;
import players.AGenericPlayer;
import players.HumanPlayer;

public class WallListener implements ActionListener {   
	
	private int x;
	private int y;
	private boolean isVertical;
	private static AGenericPlayer joueur2 = Board.getSecondPlayer();
	private static AGenericPlayer joueur1 = Board.getFirstPlayer();
	private static Case[][] realTab = Board.getTableau();
	
	public WallListener(int x,int y,boolean isVertical){
		this.x = x;
		this.y = y;
		this.isVertical = isVertical;
	}
	
	public void actionPerformed(ActionEvent e){
		if (isVertical){
			if(Main.tourJoueur1){
				if ( Rules.canPutWallRight(Board.getTableau()[x][y]) && Rules.canReallyPutWallRight(Board.getTableau()[x][y])){
					joueur1.putWallRight(Board.getTableau()[x][y]);
					Main.tourJoueur1 = false;
				}
			}
			else{
				if (Rules.canPutWallRight(Board.getTableau()[x][y]) && Rules.canReallyPutWallRight(Board.getTableau()[x][y])){
					joueur2.putWallRight(Board.getTableau()[x][y]);
					Main.tourJoueur1 = true;
				}
			}
		}
		else{
			if(Main.tourJoueur1){
				if (Rules.canPutWallDown(Board.getTableau()[x][y]) && Rules.canReallyPutWallDown(Board.getTableau()[x][y])){
					joueur1.putWallDown(Board.getTableau()[x][y]);
					Main.tourJoueur1 = false;
				}
			}
			else{
				if (Rules.canPutWallDown(Board.getTableau()[x][y]) && Rules.canReallyPutWallDown(Board.getTableau()[x][y])){
					joueur2.putWallDown(Board.getTableau()[x][y]);
					Main.tourJoueur1 = true;
				}
			}
		}
	}
}
