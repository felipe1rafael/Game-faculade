	package terror.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Player {
	public static final String PLAYER_TAG = Player.class.getName();
	private int health;
	private int vel;
	private int vel2 =vel;
	private float stamina;
	private Texture texture;
	private Vector2 position;
	private Viewport viewport;
	private static Player instance;
	private Rectangle rectangleCorpo;
	private float altura ;
	private float largura ;
	private Player(Vector2 position, Viewport viewport) {
		this.health = 3;
		this.stamina = 100;
		this.vel = 50;
		this.vel2 = vel;
		this.texture = new Texture("playerteste.png");
		this.position = position;
		this.viewport = viewport;
		this.largura = texture.getWidth();
		this.altura = texture.getHeight();	
		rectangleCorpo = new Rectangle(position.x, position.y, largura, altura);
	}
	public static Player getInstance(Vector2 position, Viewport viewport) {
		if (instance == null) {
			instance = new Player(position, viewport);
		}
		
		return instance;
	}

	public void Sprint(boolean estaCorrendo) {
		if (estaCorrendo &&stamina > 0) {
			vel2 = vel * 3;
			stamina -= 1;
		} else {
			vel2 = vel;
			stamina += 0.2f;
		}
		
	}
	
	
	public void move(float deltaX, float deltaY, float deltaTime) {
	    float amountX = deltaX * vel2 * deltaTime;
	    float amountY = deltaY * vel2 * deltaTime;
	    position.x += amountX;
	    position.y += amountY;
	    rectangleCorpo.setPosition(position.x, position.y);
	}
	
	void dispose() {
		texture.dispose();
	}

	
}
