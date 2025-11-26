package terror.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Level1Screen  implements Screen{
	private Game game;
	private GameMap map;
	private OrthographicCamera camera;
	private Viewport viewport;
	private final int WORLD_WIDTH = 1280;
	private final int WORLD_HEIGHT = 720;

    public Level1Screen(Game game) {
	this.setGame(game);	
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		viewport.apply(true);
		map = new GameMap("mapav1.tmx");

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		camera.update();
		 map.render(camera);
		
	}

	@Override
	public void resize(int width, int height) {
	    viewport.update(width, height, true); 
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		map.dispose();
		
		
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

}
