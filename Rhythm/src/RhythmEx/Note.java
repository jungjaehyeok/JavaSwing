package RhythmEx;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Note extends Thread{

	private Image noteBasic = new ImageIcon(Main.class.getResource("../images/UpNote.png")).getImage();	
	private int x,y;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType(){
		return noteType;
	}
	
	
	public boolean isProceeded(){
		return proceeded;
	}
	public void close(){
		proceeded = false;
	}
	
	public Note(int y,String noteType){
		this.y = y;
		if(noteType.equals("Left")){
			x = 460;
			noteBasic = new ImageIcon(Main.class.getResource("../images/LeftNote.png")).getImage();	
		}else if(noteType.equals("Down")){
			x = 560;
			noteBasic = new ImageIcon(Main.class.getResource("../images/DownNote.png")).getImage();	
		}else if(noteType.equals("Up")){
			x = 660;
		}else if(noteType.equals("Right")){
			x = 760;
			noteBasic = new ImageIcon(Main.class.getResource("../images/RightNote.png")).getImage();	
		}
		this.noteType=noteType;
	}
	public void screenDraw(Graphics g){	
		g.drawImage(noteBasic,x,y,null);

	}
	public void raise(){
		y-=Main.NOTE_SPEED;
		if(y<53){
			System.out.println("miss");
			close();
		}
	}
	public void run(){
		try{
			while(true){
				raise();
				if(proceeded){
					Thread.sleep(Main.SLEEP_TIME);
				}else{
					interrupt();
					break;
				} 
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public String judge(){
		if(y<=175&&y>155){
			System.out.println("good");
			close();
			return "good";
		}
		if(y<=155&&y>135){
			System.out.println("excellent");
			close();
			return "excellent";
		}
		if(y<=135&&y>115){
			System.out.println("perfect");
			close();
			return "perfect";
		}
		if(y<=115&&y>95){
			System.out.println("excellent");
			close();
			return "excellent";
		}
		if(y<=95&&y>75){
			System.out.println("good");
			close();
			return "good";
		}
		if(y<=75){
			System.out.println("miss");
			close();
			return "miss";
		}
		return "none";
	}

	public int getY(){
		return y;
	}
}
