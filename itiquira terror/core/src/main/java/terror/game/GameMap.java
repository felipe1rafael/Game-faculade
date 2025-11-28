package terror.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.files.FileHandle; 

public class GameMap {
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private Array<Rectangle> collisionRectangles;
	public GameMap(String tmxPath) {
        FileHandle file = Gdx.files.internal(tmxPath);
        map = new TmxMapLoader().load(file.path());
        renderer = new OrthogonalTiledMapRenderer(map);
        collisionRectangles = new Array<>();
        MapObjects objects = map.getLayers().get("Collisions").getObjects();
        for (MapObject object : objects) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                collisionRectangles.add(rect);
            }
        }
		}
    
	public void render(OrthographicCamera camera) {
		Gdx.gl.glClearColor(0, 0, 0, 1); 
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    if (map != null) {
	        renderer.setView(camera);
	        renderer.render();
	    }
	}
    public boolean isColliding(Rectangle playerBounds) {
        for (Rectangle collisionRect : collisionRectangles) {
            if (playerBounds.overlaps(collisionRect)) {
            	return true;
            }
        }
        return false; 	
    }

    
	public void dispose() {
	    if (map != null) {
	        map.dispose();
	        renderer.dispose();
	    }
	}
}