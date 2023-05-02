package Main;

import entity.Player;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Player player) {
		
		int playerLeftX = player.playerX + player.solidArea.x;
		int playerRightX = player.playerX + player.solidArea.x + player.solidArea.width;
		int playerTopY = player.playerY + player.solidArea.y;
		int playerBottomY = player.playerY + player.solidArea.y + player.solidArea.height;
	
		int playerLeftCol  = playerLeftX/gp.tileSize;
		int playerRightCol = playerRightX/gp.tileSize;
		int playerTopRow = playerTopY/gp.tileSize;
		int playerBottomRow = playerBottomY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(player.direction) { //kiem tra vi tri vat the
		case "up" :
			playerTopRow = (playerTopY - player.playerSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[playerLeftCol][playerTopRow];
			tileNum2 = gp.tileM.mapTileNum[playerRightCol][playerTopRow];
//			System.out.println(tileNum1+" "+tileNum2);
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {

				player.collisionOn = true;
			}
			break;
		case "down" :
			playerBottomRow = (playerBottomY + player.playerSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[playerLeftCol][playerBottomRow];
			tileNum2 = gp.tileM.mapTileNum[playerRightCol][playerBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				
				player.collisionOn = true;
			}
			break;
		case "left" :
			playerLeftCol = (playerLeftX - player.playerSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[playerLeftCol][playerTopRow];
			tileNum2 = gp.tileM.mapTileNum[playerRightCol][playerBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				if(tileNum1 == 3 || tileNum2==3){
					
					gp.player.check= true;
					gp.player.checkComplete = true;
					
				} 
//				System.out.println(gp.player.check);
				player.collisionOn = true;
			}
			break;
		case "right" :
			playerRightCol = (playerRightX + player.playerSpeed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[playerRightCol][playerTopRow];
			tileNum2 = gp.tileM.mapTileNum[playerRightCol][playerBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				
				if(tileNum1 == 3 || tileNum2==3) {
					gp.player.check= true;
					gp.player.checkComplete = true;
				}
				
//				System.out.println(gp.player.check);
				player.collisionOn = true;
			}
			break;
		}
		
	}
}
