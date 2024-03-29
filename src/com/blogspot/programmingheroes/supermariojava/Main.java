
// PlatformGame.java -------------------------------------------------

package com.blogspot.programmingheroes.supermariojava;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.blogspot.programmingheroes.supermariojava.loaders.ImagesLoader;
import com.blogspot.programmingheroes.supermariojava.loaders.Map;
import com.blogspot.programmingheroes.supermariojava.loaders.SoundsLoader;





/**
 *
 */
public class Main extends Stage {

	// For load images, sounds and maps.
	private ImagesLoader loader;
	private SoundsLoader sounds;
	private Map map;
	private BufferedImage heartIcon;
	private BufferedImage gameOverScreen;
	private float gravity = 0.2F;
	private final int NEXTSTAGE_TIME = 2000;
	
	Point pointCursor = new Point(-1,-1);

	private Mario m;
	
	public Main(boolean applet) {
		super(CANVAS);
		setFPS(80);
		setSize(960, 640);
		// Creamos el mapa en el mundo=1 nivel=1.
		map = new Map(this, 1, 1);
		// Creamos los cargadores pero de momento
		// no cargamos nada.
		loader = new ImagesLoader("res/img", "loader");
		sounds = new SoundsLoader("res/sounds", "loader");
		// A占폸dimos los cargadores de sonido y de
		// imagen a el objeto Stage (superclase).
		setImagesLoader(loader);
		setSoundsLoader(sounds);
	}

	public Main() {
		super(JFRAME);
		setFPS(80);
		setSize(960-6, 640-6);
		window.setResizable(false);
		// Creamos el mapa en el mundo=1 nivel=1.
		map = new Map(this, 1, 1);
		// Creamos los cargadores pero de momento
		// no cargamos nada.
		loader = new ImagesLoader("res/img", "loader");
		sounds = new SoundsLoader("res/sounds", "loader");
		// A占폸dimos los cargadores de sonido y de
		// imagen a el objeto Stage (superclase).
		setImagesLoader(loader);
		setSoundsLoader(sounds);
	}
	
	public static void heartUp() {
		//코인 다먹고 다음 판으로 넘어갈 시 heart 1개 증가
		//Coin안에 N_COINS는 맵에 있는 총 코인수, COINS_CATCHED은 먹은 코인수!
		if (Coin.N_COINS == Coin.COINS_CATCHED) {
			Coin.N_COINS = 0;
			Coin.COINS_CATCHED = 0;
			Player.remainingLives++;
		}
	}
	
	public synchronized void initStage() {
		// Cargamos las im占퐂enes y los sonidos
		// que est占퐊 indicados en el archivo externo.
		loader.startLoader();
		sounds.startLoader();
		
		// Iniciamos el mapa.
		map.initMap();

		// Creamos un jugador.
		m = new Mario(this);
		map.addPlayer(m);
	}
	public synchronized void updateStage() {
		map.act();
		if (!gameOver && Portal.checkTouchPortal) {
			gameOver();
			new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(NEXTSTAGE_TIME);
					} catch (Exception e) {}
					Coin.N_COINS = 0;
					gameOver = false;
					Portal.checkTouchPortal = false;
					map.nextLevel();
					map.addPlayer(m);
					Coin.COINS_CATCHED = 0;
				}
			}).start();
			heartUp();
		}
	}
	
	public synchronized void renderStage(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0,WIDTH,HEIGHT);
		map.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		drawRemainingLives(g2);

		if (gameOver) {
			g2.setColor(Color.WHITE);
			g2.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
			g2.setFont(new Font(Font.MONOSPACED,
				Font.BOLD, 30));
			g2.drawString("Next Stage",
				WIDTH/2-100, HEIGHT/2-10);
		}
	}

	public void gameEnd() {
		map.act();
		if (Player.remainingLives <= 0) {
			new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(NEXTSTAGE_TIME);
					} catch (Exception e) {}
					map.gameOverView();
					map.addPlayer(m);
				}
			}).start();
		}
	}
	
    private void drawRemainingLives(Graphics2D g2) {
		g2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		g2.setColor(Color.WHITE);
        String displayedStr = "♥: " + m.getRemainingLives() ;
        g2.drawImage(heartIcon, 50, 10, null);
        g2.drawString(displayedStr, 100, 50);
    }

	public synchronized void mouseMoved(MouseEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		for (int i=0; i<map.players.size(); i++) {
			map.players.get(i).keyPressed(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		for (int i=0; i<map.players.size(); i++) {
			map.players.get(i).keyReleased(e);
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public float getGravity() {
		return gravity;
	}

	public Map getCurrentMap() {
		return map;
	}

	public static void main(String[] args) {
		Main p = new Main();
		p.getWindow().setVisible(true);
		p.startGame();
	}
}  // fin de la clase PlatformGame

// PlatformGame.java ------------------------------------------------