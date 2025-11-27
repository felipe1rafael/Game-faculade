package terror.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Movimento {
private Player player;
	
	public Movimento (Player player) {
		this.player = player;
	}
	
	public void andar(float delta) {
		float deltaX = 0;
		float deltaY = 0;
		boolean estaCorrendo = false;

		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
			estaCorrendo = true;
		}
		player.Sprint(estaCorrendo);

		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			deltaY = 1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			deltaY = -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			deltaX = -1;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			deltaX = 1;
		}

		player.move(deltaX, deltaY, delta);
	}
}
