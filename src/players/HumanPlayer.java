package players;

import java.io.IOException;
import java.util.Scanner;

import rules.Rules;
/**
 * Cette classe modelise le comportemet du  joueur humain.
 * @author Eduardo Dom
 *
 */
public class HumanPlayer extends AGenericPlayer
{
	private boolean isFirstPlayer;
	public HumanPlayer(boolean isFirstPlayer)
	{
		this.isFirstPlayer = isFirstPlayer;
		if(isFirstPlayer){
			this.x =0;
			this.y =4;
		}
		else{
			this.x = 8;
			this.y = 4;
		}
		walls = 10;
	}
	

	/**
	 * Permet au joueur humain de se deplacer en mode console 
	 */
	public void move()
	{
		boolean continuer = true;
		while (continuer){
			Scanner	direction = new Scanner(System.in);
			System.out.println("Dans quelle direction voulez-vous vous deplacer? Gauche[G],Droite[D],Haut[H],Bas[B]");
			String reponse = direction.nextLine();
			if (reponse.equals("G") && Rules.canMoveLeft(this))
			{
				moveLeft();
				continuer = false;
			}
			else if (reponse.equals("D") && Rules.canMoveRight(this))
			{
				moveRight();
				continuer = false;
			}
			else if (reponse.equals("H") && Rules.canMoveUp(this))
			{
				moveUp();
				continuer = false;
			}
			else if (reponse.equals("B") && Rules.canMoveDown(this))
			{
				moveDown();
				continuer = false;
			}
			else{
				System.out.println("Impossible de se deplacer dans cette direction");
			}
		}
	}
	/**
	 * Permet au joueur humain de se deplacer en mode graphique 
	 * @param i La ligne sur laquelle se trouve la case
	 * @param j La colonne sur laquelle se trouve la case
	 * @return Vrai si le joueur a pu se deplacer,Faux sinon 
	 */
	public boolean move(int i,int j){
		if (Rules.canMove(plateau[this.x][this.y], plateau[i][j])){
			plateau[x][y].setEmpty(true);
			this.x = i;
			this.y = j;
			plateau[this.x][this.y].setEmpty(false);
			return true;
		}
		else{
			return false;
		}
	}
	

	/**
	 * Permet au joueur humain de placer un mur sur une case en mode console 
	 * @param i ,la ligne sur laquelle se trouve la case
	 * @param j ,la colonne sur laquelle se trouve la case
	 * @throws IOException
	 */
	public void setWall(int i,int j) throws IOException
	{
		Scanner wall = new Scanner(System.in);
		System.out.println("Voulez-vous un mur horizontal? Oui[O] ou Non[N]");
		String reponse =  wall.nextLine(); 	
		if (reponse.equals("N"))
		{
			System.out.println("Un mur � droite ou � gauche? Gauche[G] ou Droite[D]");
			String reponse2 = wall.nextLine();
			if (reponse2.equals("D")){
				if (Rules.canPutWallRight(plateau[i][j])){
					if (Rules.canReallyPutWallRight(plateau[i][j])){
						putWallRight(plateau[i][j]);
					}
					else{
						throw new IOException("Placer un mur ici bloquerait le chemin d'un joueur!");
					}
				}
				else{
					throw new IOException("Impossible de placer un mur ici");
				}
			}
			else if (reponse2.equals("G")){
				if (Rules.canPutWallLeft(plateau[i][j])){
					if (Rules.canReallyPutWallLeft(plateau[i][j])){
						putWallLeft(plateau[i][j]);
					}
					else{
						throw new IOException("Placer un mur ici bloquerait le chemin d'un joueur!");
					}
				}
				else{
					throw new IOException("Impossible de placer un mur ici");
				}
			}
		}
		else
		{
			System.out.println("Un mur en haut ou en bas? Haut[H] ou Bas[B]");
			String reponse3 = wall.nextLine();
			if (reponse3.equals("H"))
			{
				if (Rules.canPutWallUp(plateau[i][j])){
					if(Rules.canReallyPutWallUp(plateau[i][j])){
						putWallUp(plateau[i][j]);
					}
					else{
						throw new IOException("Placer un mur ici bloquerait le chemin d'un joueur!");
					}
				}
				else{
					throw new IOException("Impossible de placer un mur ici");
				}
			}
			else if (reponse3.equals("B"))
			{
				if(Rules.canPutWallDown(plateau[i][j])){
					if(Rules.canReallyPutWallDown(plateau[i][j])){
						putWallDown(plateau[i][j]);
					}
					else{
						throw new IOException("Placer un mur ici bloquerait le chemin d'un joueur!");
					}
				}
				else{
					throw new IOException("Impossible de placer un mur ici");
				}
			}
		}
	}
	
	/**
	 * Permet au joueur humain de jouer en mode console
	 */
	public void play()throws IOException
	{
		Scanner action = new Scanner(System.in);
		String reponse;
		System.out.println("Vous pouvez vous deplacer[D] ou placer un mur sur une case[M].Que voulez vous faire?");
		reponse = action.nextLine();
		if (reponse.equals("D"))
		{
			move();
		}
		if (reponse.equals("M"))
		{
			boolean not_done = true;
			while(not_done){
				System.out.println("A quelle ligne se situe la case?");
				int i = action.nextInt();
				System.out.println("A quelle collone se situe la case?");
				int j = action.nextInt();
				setWall(i,j);
				not_done = false;
			}
		}
	}
}
