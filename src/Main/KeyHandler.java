package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {
	public GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	@Override
	public void keyTyped(KeyEvent e) {//được gọi khi một phím đã được nhấn và thả ra.
		
	}

	@Override
	public void keyPressed(KeyEvent e) {//được gọi khi một phím được nhấn xuống.
		
		int code = e.getKeyCode();// trả lại code khi ấn một nút
		
		if(code == KeyEvent.VK_W ) {
			upPressed = true;
		}
		if(code == KeyEvent.VK_S ) {
			downPressed = true;
		}
		if(code == KeyEvent.VK_A ) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D ) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P ) {
//			gp.running = false;
			if(gp.running == false ) {
				
				gp.running= true;
				
			}
			else if(gp.running == true ) {
				
				gp.running= false;
				
			}
			
		}
		if( code== KeyEvent.VK_N) {
			if(gp.player.check == false ) {
				
				gp.player.check= true;
				gp.player.setDefaultValues();
				
			}
			else if(gp.player.check == true ) {
				
				gp.player.check= false;
				gp.player.setDefaultValues();
				
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//: được gọi khi một phím được thả ra.
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W ) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A ) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D ) {
			rightPressed = false;
		}
	}

}
