package terror.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Player {
	public static final String PLAYER_TAG = Player.class.getName();
	private int health;
	private int vel;
	private int vel2 =vel;
	private int stamina;
	private Texture texture;
	private Vector2 position;
	private Viewport viewport;
	
	
	

	Player(Vector2 position, Viewport viewport) {
		this.health = 100;
		this.stamina = 100;
		this.vel = 50;
		this.vel2 = vel;
		this.texture = new Texture("playerteste.png");
		this.health = 100;
		this.position = position;
		this.viewport = viewport;
	}

	public void Sprint(boolean estaCorrendo) {
		if (estaCorrendo &&stamina > 0) {
			vel2 = vel * 2;
			stamina -= 1;
		} else {
			vel2 = vel;
		}
		
	}
	
	public final Texture getTexture() {
		return texture;
	}

	public final void setTexture(Texture texture) {
		this.texture = texture;
	}
	public void move(float deltaX, float deltaY, float deltaTime) {
	    position.x += deltaX * vel2 * deltaTime;
	    position.y += deltaY * vel2 * deltaTime;
	}
	

	public  Vector2 getPosition() {
		return position;
	}

	public  void setPosition(Vector2 position) {
		this.position = position;
	}

	public  Viewport getViewport() {
		return viewport;
	}

	public  void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	void dispose() {
		texture.dispose();
	}

	
}
