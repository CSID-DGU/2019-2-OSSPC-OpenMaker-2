
// WorldObject.java -------------------------------------------------

package com.blogspot.programmingheroes.supermariojava;

import com.blogspot.programmingheroes.supermariojava.loaders.Map;


/**
 *
 */
public class WorldObject extends Sprite {

	// Indica que no hay suelo.
	public static final int NOT_FLOOR = 1000000000;

	/* Indica si es capaz de sostener a un jugador
	 * encima del Sprite. */
	protected boolean supportsPlayer;

	/* Indica si es capaz de sostener a un enemigo
	 * encima del Sprite. */
	protected boolean supportsEnemige;

	/* Referencia a el mapa a el cual pertenece el
	 * objeto. */
	protected Map map;

	/**
	 *
	 */
	public WorldObject(Stage s) {
		super(s);
		map = ((Main)s).getCurrentMap();
		supportsPlayer = supportsEnemige = false;
	}

	// M�todos GET --------------------------------------------------
	public int getFloor() {
		if (supportsPlayer || supportsEnemige) {
			return (int)y;
		}
		return NOT_FLOOR;
	}
	//  fin de m�todos GET ------------------------------------------


	// M�todos SET --------------------------------------------------
	public void setSupportPlayer(boolean support) {
		this.supportsPlayer = false;
	}
	//  fin de los m�todos SET --------------------------------------


	// M�todos BOOLEANOS --------------------------------------------
	public boolean supportsPlayer() {
		return supportsPlayer;
	}
	//  fin de los m�todos BOOLEANOS --------------------------------


}  // fin de la clase WorldObject

//  fin de WorldObject.java -----------------------------------------