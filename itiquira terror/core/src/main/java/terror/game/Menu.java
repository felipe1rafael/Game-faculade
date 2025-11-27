package terror.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class Menu implements Screen {
	private Texture sheet;
    private Animation<TextureRegion> anim;
    private float time = 0;
	private SpriteBatch batch;
	private Game game;
	private Skin playButtonSkin;
	private Stage stage;
	private Viewport viewport;
	
	public Menu(Game game) {
        this.game = game;
    }
    @Override
    public void show() {
        // Prepare your screen here.
    	viewport = new ScreenViewport();
    	stage = new Stage(viewport);
    	batch = new SpriteBatch();
        playButtonSkin = new Skin(Gdx.files.internal("ui/playbotao.json"));
        Table table = new Table(playButtonSkin);
        table.setFillParent(true);
        TextButton playButton = new TextButton( "Play",playButtonSkin);
        table.add(playButton).width(200).height(80);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
		playButton.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ChangeListener() {
		    @Override
		    public void changed (ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
		        game.setScreen(new Level1Screen(game));
		    }
		});
        sheet = new Texture("menuFrame.png");
        TextureRegion[][] tmp = TextureRegion.split(sheet, 1280, 730); 
        TextureRegion[] frames = new TextureRegion[tmp.length * tmp[0].length];
        int index = 0;
        for (int i = 0; i < tmp.length; i++)
            for (int j = 0; j < tmp[0].length; j++)
                frames[index++] = tmp[i][j];

        anim = new Animation<>(0.3f, frames); 
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
    	Gdx.gl.glClearColor(0,0,0,1);
    	time += delta;
        TextureRegion frame = anim.getKeyFrame(time, true);
    	batch.begin();
    	batch.draw(frame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	batch.end();
    	stage.act(delta);//tem que ser desenhado depois do batch.end()
    	stage.draw();
    	
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;
        viewport.update(width, height, true);
        stage.getViewport().update(width, height, true);

        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    	sheet.dispose();
    	batch.dispose();
    	stage.dispose();
    	playButtonSkin.dispose();
    	
    }
}