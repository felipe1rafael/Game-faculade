package terror.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/** First screen of the application. Displayed after the application is created. */
public class Menu implements Screen {
	private Texture sheet;
    private Animation<TextureRegion> anim;
    private float time = 0;
	private SpriteBatch batch;
	private Game game;
	private BitmapFont font;
	public Menu(Game game) {
        this.game = game;
    }
    @Override
    public void show() {
        // Prepare your screen here.
    	batch = new SpriteBatch();
        font = new BitmapFont();
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
    	font.draw(batch,"clique para iniciar", Gdx.graphics.getWidth()/2 - 50, 50);	
    	batch.end();
    	if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new Level1Screen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

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
    	font.dispose();
    	batch.dispose();
    }
}