
// Brick.java -------------------------------------------------------

package com.blogspot.programmingheroes.supermariojava;


import java.applet.AudioClip;
import java.awt.Rectangle;

import com.blogspot.programmingheroes.supermariojava.loaders.ImagesLoader;
import com.blogspot.programmingheroes.supermariojava.loaders.Map;
import com.blogspot.programmingheroes.supermariojava.loaders.SoundsLoader;


/**
 *
 */
public class Fire extends WorldObject {

	/* Permite que todos los objetos de esta misma clase
	 * puedan pasar de una imagen a otra todos sincronizados.
	 */
	protected static int indexClass = 0;


	/* Indica que es necesario actualizar el indexClass. */
	protected static boolean changeImg = false;

	/* N�mero de im�genes que representan al sprite. */
	public static int LENGHT_IMAGES = 4;

	/* Permite actualizar las im�genes para todos los objetos
	 * de esa clase. */
	public static void actClass() {
		if (changeImg) {
			indexClass = (indexClass+1)%LENGHT_IMAGES;
			changeImg = false;
		}
	}

	public static AudioClip[] audio;
	public static int indexAudio;
	public static boolean first = true;

	protected String imgNormal = "fire*_0";

	// Indica si el ladrillo est� ya roto, har�
	// el efecto que le va a hacer desaparecer.
	protected boolean effect = false;

	// Indica si el ladrillo ha sido golpeado cuando
	// el jugador no tiene la capacidad de romperlos,
	// por lo tanto indica que el ladrillo se est� moviendo.
	protected boolean moving = false;
	
	// Velocidad del movimiento.
	protected float movingSpeed = 1.5F;

	// Representa la posici�n inicial en la que se encuentra
	// y el la cual se quedar� quieto el ladrillo cuando
	// realize se movimiento al ser golpeado
	protected float initY = 0;

	public Fire(Stage s) {
		super(s);
		supportsPlayer = true;
		setPreferredSize(Map.TILE_X_SIZE, Map.TILE_Y_SIZE);
		setImages(imgNormal, 0, 4);
		// Rect�ngulo para las colisiones
		bounds.add(new Rectangle(-1, -1, width+1, height+1));
		if (first) {
			first = false;
			indexAudio = 0;
			audio = new AudioClip[5];
			for (int i=0; i<audio.length; i++) {
				audio[i] = stage.getSoundsLoader()
					.getAudio("coin.wav", true, true); // TODO nombre de los sonidos
			}
		}
	}

	public void act() {
		if (moving) {
			move();
			speed.setY(speed.getAccurateY()-((Main)stage).getGravity());
			if (y >= initY) {
				speed.setY(0);
				moving = false;
			}
		}
		int frameFrec = (int)(stage.getFPS()/10);
		if (frameFrec==0 
			|| stage.getTotalUpdates()%frameFrec == 0) {
				setImage(indexClass);
				changeImg = true;
		}
	}

	public void collision(Sprite s) {
		if (s instanceof Player && supportsPlayer) {
			Player p = (Player)s;
			// Colisiones del eje Y
			if(p.getFoot().intersects(getHead())) {
				// 하트 하나 감소하고 처음위치로 초기화
				//만약 남는 하트가 하나도 없으면 게임오버 창으로 이동
				map.makeThisMap();
				map.addPlayer(p);
				
				System.out.println("죽었습니다.");
			}

		}
	}

	public void setY(float yPos) {
		super.setY(yPos);
		initY = yPos;
	}

}