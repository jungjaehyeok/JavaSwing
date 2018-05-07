package RhythmEx;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RhythmKeyListener extends KeyAdapter{
	
	public void keyPressed(KeyEvent e){
		if(RhythmGame.game == null){
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP ){
			RhythmGame.game.pressUp();
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ){
			RhythmGame.game.pressDown();
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ){
			RhythmGame.game.pressLeft();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT ){
			RhythmGame.game.pressRight();
		}
	}
	
	
	public void keyReleased(KeyEvent e){
		if(RhythmGame.game == null){
			return;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP ){
			RhythmGame.game.releaseUp();
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN ){
			RhythmGame.game.releaseDown();
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT ){
			RhythmGame.game.releaseLeft();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT ){
			RhythmGame.game.releaseRight();
		}
		
}
}