package terror.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
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
	private VidaHud vidaHud;
	private Stamina staminaHud;
	private Movimento movimento;
	

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
		map = new GameMap("mapav2.tmx");
		Vector2 StartPosition = new Vector2(16,450);
		player = Player.getInstance(StartPosition, viewport);
		vidaHud = new VidaHud (player);	
		staminaHud = new Stamina (player);
		movimento = new Movimento (player,map);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        movimento.andar(delta);
        //centraliza e Atualiza a Câmera
        centerCameraOnPlayer();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Usa GL20 para limpar a tela
        map.render(camera);
        batch.setProjectionMatrix(camera.combined);	
        batch.begin();
        batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);
        batch.end();
        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        batch.begin();
        vidaHud.desenho(batch);	
        staminaHud.desenho(batch);
        batch.end();
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
		vidaHud.dispose();
		
	
}}
