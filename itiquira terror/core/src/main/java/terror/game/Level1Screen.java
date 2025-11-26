package terror.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;




public class Level1Screen  implements Screen{
	private Game game;
	private GameMap map;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Player player;
	private SpriteBatch batch;
	private final int WORLD_WIDTH = 250;
	private final int WORLD_HEIGHT = 250;
	

    public Level1Screen(Game game) {
	this.setGame(game);	
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
		viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		viewport.apply(true);
		batch = new SpriteBatch();
		map = new GameMap("mapav1.tmx");
		Vector2 StartPosition = new Vector2(16,450);
		player = Player.getInstance(StartPosition, viewport);

	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        andar(delta);
        //centraliza e Atualiza a Câmera
        centerCameraOnPlayer();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Usa GL20 para limpar a tela
        map.render(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        batch.end();
    }
	
    private void andar(float delta) {
        float deltaX = 0;
        float deltaY = 0;
        boolean estaCorrendo= Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
        player.Sprint(estaCorrendo);
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) ||
                Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.A)) {
        	if (Gdx.input.isKeyPressed(Input.Keys.W)) {
        		deltaY += 1;
        	}
        	if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        		deltaY -= 1;
        	}
        	if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        		deltaX += 1;
        	}
        	if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        		deltaX -= 1;
        	}
     // andar na diagonal
        	if (deltaX != 0 || deltaY != 0) {
        		float length = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        		deltaX /= length;
        		deltaY /= length;
        }
        }	
        player.move(deltaX, deltaY, delta);
    }
    // gerado automaticamente,para andar a câmera junto com o player
    private void centerCameraOnPlayer() {
		Vector2 playerPos = player.getPosition();
		camera.position.set(playerPos.x + player.getTexture().getWidth() / 2,
							playerPos.y + player.getTexture().getHeight() / 2,
							0);
		camera.update();
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
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		map.dispose();
		batch.dispose();
		player.getTexture().dispose();
		
		
	
}
