package RhythmEx;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;



public class RhythmGame extends JFrame {
	JPanel panel; 
	private Image screenImage;
	private Graphics screenGraphic;

	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(Main.class.getResource("../images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(Main.class.getResource("../images/rightButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(Main.class.getResource("../images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(Main.class.getResource("../images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(Main.class.getResource("../images/backButtonBasic.png"));
	
	private Image titleImage = new ImageIcon(Main.class.getResource("../images/firetitle.png")).getImage();
	private Image selectedImage = new ImageIcon(Main.class.getResource("../images/fire.jpg")).getImage();
	private Image background = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
	private Image gameinfo = new ImageIcon(Main.class.getResource("../images/gameinfo.png")).getImage();
	private Image Leveleasy = new ImageIcon(Main.class.getResource("../images/Leveleasy.png")).getImage();
	private Image Levelhard = new ImageIcon(Main.class.getResource("../images/Levelhard.png")).getImage();
	
	
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	private int mouseX, mouseY;
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	private boolean Level = false;
	ArrayList<Track> trackList = new ArrayList<Track>();
	private Music introMusic = new Music("introMusic.mp3", true);
	private Music selectedMusic;
	private int nowSelected = 0;
	
	public static Game game;
	
	public RhythmGame() {
		trackList.add(new Track("firetitle.png", "fire.jpg",
				"gameimage.jpg", "fireselected.mp3", "fire.mp3","fire"));
		trackList.add(new Track("hotlinetitle.png", "hotline.jpg",
				"gameimage.jpg", "hotlineselected.mp3", "hotline.mp3","hotline"));

		trackList.add(new Track("momtitle.png", "mom.jpg",
				"gameimage.jpg", "momselected.mp3", "mom.mp3","mom"));
		
		panel = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(background, 0, 0, null);
				if(isMainScreen)
				{
					g.drawImage(selectedImage, 418, 100, null);
					g.drawImage(titleImage, 420, 520, null);
				}
				if(isGameScreen)
				{
					g.drawImage(gameinfo,0, 0, null);
					g.drawImage(titleImage, 125, 10, null);
					game.screenDraw(g);
					
					if(Level==true){
						g.drawImage(Leveleasy, 600, 3, null);
					}else {
						g.drawImage(Levelhard, 600, 7, null);
					}
					
				}
				paintComponents(g);
				try{Thread.sleep(5);}
					catch(Exception e){
						e.printStackTrace();
					}
				
				this.repaint();
			}
		};
		setTitle("리듬게임");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(panel);
		setVisible(true);
		setLayout(null);
		panel.setLayout(null);
		introMusic.start();
		panel.addKeyListener(new RhythmKeyListener());
		

		
		
		startButton.setBounds(700, 270, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				enterMain();
			}
		});
		panel.add(startButton);
		
		quitButton.setBounds(700, 430, 400, 100);
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
				quitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
				quitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
		});		
	
		panel.add(quitButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(570, 30, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
			}
		});
		panel.add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(650, 30, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				selectRight();
			}
		});
		panel.add(rightButton);
		
		easyButton.setVisible(false);
		easyButton.setBounds(375, 600, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
				easyButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
				easyButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Level = true;
				gameStart(nowSelected, "easy");
			}
		});
		panel.add(easyButton);
		
		hardButton.setVisible(false);
		hardButton.setBounds(735, 600, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
				hardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
				hardButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Level = false;
				gameStart(nowSelected, "hard");
			}
		});
		panel.add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20,0,60,60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		backButton.addActionListener(new backMain());
		panel.add(backButton);
		
		
		
	}
	
	public void selectTrack(int nowSelected) {
		if(selectedMusic != null)
			selectedMusic.close();
		titleImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		selectedImage = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getStartImage())).getImage();
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
		selectedMusic.start();
	}
	
	public void selectLeft() {
		if(nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}
	public void gameStart(int nowSelected, String difficulty) {
		if(selectedMusic != null)
		selectedMusic.close();
		isMainScreen = false;
		isGameScreen = true;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		game = new Game(trackList.get(nowSelected).gettitleName(),difficulty,trackList.get(nowSelected).getGameMusic());
		game.start();
		panel.requestFocus();
		setFocusable(true);
	}
	public void enterMain(){
		introMusic.close();
		selectTrack(0);
		startButton.setVisible(false);
		quitButton.setVisible(false);
		background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
				.getImage();
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		isMainScreen = true;
		isGameScreen = false;
	}
	class backMain implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			isMainScreen = true;
			isGameScreen = false;
			leftButton.setVisible(true);
			rightButton.setVisible(true);
			easyButton.setVisible(true);
			hardButton.setVisible(true);
			background = new ImageIcon(Main.class.getResource("../images/mainBackground.jpg"))
					.getImage();
			backButton.setVisible(false);
			selectTrack(nowSelected);
			game.close();
			
			
		}}

	
	
}
