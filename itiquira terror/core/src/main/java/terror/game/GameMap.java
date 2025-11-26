package terror.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.files.FileHandle; 

public class GameMap {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;

	public GameMap(String tmxPath) {
        
        FileHandle file = Gdx.files.internal(tmxPath);
        
        
        map = new TmxMapLoader().load(file.path());
        
        
        renderer = new OrthogonalTiledMapRenderer(map);
    }
	public void render(OrthographicCamera camera) {
		Gdx.gl.glClearColor(0, 0, 0, 1); 
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    if (map != null) {
	        renderer.setView(camera);
	        renderer.render();
	    }
	}

    
	public void dispose() {
	    if (map != null) {
	        map.dispose();
	        renderer.dispose();
	    }
	}
}