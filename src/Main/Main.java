package Main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setTitle("Game Maze");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false); // Kich thuoc co dinh
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();//tính toán kích thước tối thiểu cần thiết để hiển thị tất cả các thành phần con của JFrame và cập nhật kích thước của JFrame để phù hợp với kích thước tối thiểu đó.
		
		window.setLocationRelativeTo(null); // hien thi giua man hinh
		window.setVisible(true);
		
		
		gamePanel.startGameThread();
	}

}
