
// Mario.java -------------------------------------------------------

package com.blogspot.programmingheroes.supermariojava;


import java.awt.Rectangle;
import java.awt.event.KeyEvent;


/**
 *
 */
public class Mario extends Player {
	
	public Mario(Stage s) {
		super(s);
		// Tama�o predeterminado del jugador.
		setPreferredSize(map.tileXSize, map.tileYSize);
		// Rect�ngulo para las colisiones
		bounds.add(new Rectangle(6, 1, width-12, height-1));
		}

	/* Asignaci�n de las teclas que controlan al jugador. */
	public void createKeys() {
		keyLeft = KeyEvent.VK_LEFT;
		keyRight = KeyEvent.VK_RIGHT;
		keyRun = KeyEvent.VK_SHIFT;
		keyJump = KeyEvent.VK_SPACE;
		keyCrouch = KeyEvent.VK_DOWN;
	}

	/* Asignaci�n de las im�genes que representan al jugador. */
	public void createImgs() {
		imgLStop = "marioLStop";
		imgRStop = "marioRStop";
		imgLJump = "marioLJump";
		imgRJump = "marioRJump";
		imgLFall = "marioLJump";
		imgRFall = "marioRJump";
		imgRight = "marioRWalk*";
		imgLeft = "marioLWalk*";
		imgRSlip = "marioRSlip";
		imgLSlip = "marioLSlip";
		imgRCrouch = "marioRCrouch";
		imgLCrouch = "marioLCrouch";
	}

}  // end of Mario class

//  end of Mario.java -----------------------------------------------