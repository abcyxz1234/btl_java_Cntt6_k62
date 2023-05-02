package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints.Key;

import javax.swing.JPanel;

import tile.TileManager;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
	
	//SCREEN SETTING
	final int originalTileSize = 9; // Tao o 9*9  
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale; // nhan 2 thanh o 18*18
	public final int maxScreenCol = 41;
	public final int maxScreenRow = 41;
	public final int screenWith = tileSize * maxScreenCol; // 738px
	public final int screenHeight = tileSize * maxScreenRow;  //738px
	
	//FPS
	int FPS = 60;
	
	boolean running = true;
//	boolean isRepaintStopped = false;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	Player player = new Player(this,keyH);
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWith, screenHeight)); //Cài đặt kích thước ưu tiên khi không có ràng buộc kích thước được áp dụng bởi các thành phần khác hoặc bởi cấu trúc tổ chức của giao diện đồ họa.
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//kích hoạt tính năng double buffering cho JFrame và giảm thiểu hiện tượng nhấp nháy khi các hình ảnh được vẽ lên đối tượng này. 
//double buffering tạo một bản sao trong bộ đệm .  Sau đó bộ đệm sẽ đc vẽ lên màn hình 1 cách mượt mà
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		
		gameThread.start(); // khi chạy dòng này thì pt run sẽ tự động chạy

		
	}
	
//	 public void stopRepaint() {
//		 
//	        isRepaintStopped = true;
//	        
//	  }
//	 
//	 public void reRepasint() {
//	        isRepaintStopped = false;
//	        repaint();
//	    }

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;  //1b nano sec, vẽ lại sau mỗi 1/60s
		double nextDrawTime = System.nanoTime() + drawInterval; 
		
		while(gameThread != null  ) {
			
			long curentTime = System.nanoTime();
						


			if(running == true) {
				update(); // cập nhật thông tin 
				repaint(); // Goi phương thức paintComponent de ve thong tin cap nhant
				
			}else{
				repaint(); // Goi phương thức paintComponent de ve thong tin cap nhant

			}
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void update() {
		
		player.update();
	
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //   gọi phương thức paintComponent trong lớp JPanel để vẽ
		
		Graphics2D g2 = (Graphics2D)g; // đổi đồ họa g thành đồ họa Graphics2D
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		if(running == false && player.checkComplete == false) {
		    g2.setColor(Color.black);
		    g2.fillRect(tileSize*0, tileSize*0, tileSize*41, tileSize*41);
		    g2.setColor(Color.cyan);
		    g2.setFont(new Font("Arial", Font.BOLD, 40) );
		    g2.drawString("Bấm P Để Tiếp Tục", tileSize*10, tileSize*20);
		}
		
		
		if(player.check==true && player.checkComplete == true) {
			
			g2.setColor(Color.black);
			g2.fillRect( tileSize*9, tileSize*15, tileSize*25, tileSize*13);
			g2.setColor(Color.cyan);
			g2.setFont(new Font("Arial", Font.BOLD, 40) );
			g2.drawString("Hoàn Thành Trò Chơi!", tileSize*10, tileSize*20);
			g2.drawString("Bấm N Để Chơi Lại", tileSize*12, tileSize*24);
		}
		g2.dispose(); // pt dispose giải phóng bộ nhớ gom rác 
	}
}
