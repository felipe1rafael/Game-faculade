package terror.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class Movimento {
private Player player;
private GameMap gameMap;
	public Movimento (Player player,GameMap gameMap) {
		this.player = player;
		this.gameMap = gameMap;
		
	}
	
	public void andar(float delta) {
	    float inputX = 0; 
	    float inputY = 0;
	    boolean estaCorrendo = Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
	    player.Sprint(estaCorrendo);
	    float velocidadeAtual = player.getVel2();
	    if (Gdx.input.isKeyPressed(Input.Keys.W)) {
	        inputY += 1;
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.S)) {
	        inputY -= 1;
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.D)) {
	        inputX += 1;
	    }
	    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
	        inputX -= 1;
	    }
	    if (inputX == 0 && inputY == 0) {
	        return; 
	    }
	    Rectangle playerBounds = player.getRectangleCorpo();
	    float originalX = playerBounds.x;
	    float originalY = playerBounds.y;
	    float moveAmountX = inputX * velocidadeAtual * delta;
	    playerBounds.x += moveAmountX;
	    if (gameMap.isColliding(playerBounds)) {
	        inputX = 0; 
	    }
	    playerBounds.x = originalX;
	    float moveAmountY = inputY * velocidadeAtual * delta;
	    playerBounds.y += moveAmountY;
	    if (gameMap.isColliding(playerBounds)) {
	        inputY = 0;
	    }
	    playerBounds.y = originalY;
	    player.move(inputX, inputY, delta);
	}
}
