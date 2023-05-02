package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.GamePanel;
import Main.KeyHandler;

public class Player {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public int playerX,playerY;
	public int playerSpeed;
	public String direction;

	public Rectangle solidArea;
	public boolean collisionOn = false;
	public boolean check= false;
	public boolean checkComplete= false; //Ktra xem đến đích chưa
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 10;
		solidArea.height = 10;
		setDefaultValues();

	}
	
	public void setDefaultValues() {
		
		playerX = 18;
		playerY = 18;
		playerSpeed = 1;
		direction = "up";
		
	}
	
	public void update() {
		if(this.keyH.upPressed == true || this.keyH.downPressed ==true|| 
				this.keyH.leftPressed == true|| this.keyH.rightPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
		}
		
		
		
		//Check Tile Collision
		if( check == false) collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//IF COLLISION IS FALSE , PLAYER CAN MOVE
		if(collisionOn == false) {
			
			switch(direction) {
			case "up" :
				playerY -= playerSpeed;
				break;
			case "down" :
				playerY += playerSpeed;
				break;
			case "left" :
				playerX -= playerSpeed;
				break;
			case "right" :
				playerX += playerSpeed;
				break;
			
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		g2.setColor(Color.orange);
		g2.fillRect(playerX, playerY, gp.tileSize - 8, gp.tileSize-8);
		
	}
	
}
