package terror.game;

import com.badlogic.gdx.Gdx;
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
    
	public void renderCamadasCima(OrthographicCamera camera) {
		if (map != null) {
	        renderer.setView(camera);
	        int[] foregroundLayers = {
	            map.getLayers().getIndex("arvore")
	        }; 
	        renderer.render(foregroundLayers);
	    }
	    }
	public void renderCamadasBaixo(OrthographicCamera camera) {
		if (map != null) {
	        renderer.setView(camera);
	        int[] foregroundLayers = {
	            map.getLayers().getIndex("chao"),
	            map.getLayers().getIndex("camada1"),
	        }; 
	        renderer.render(foregroundLayers);
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