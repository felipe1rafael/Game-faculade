package terror.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils; // Necessário para MathUtils.clamp

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Stamina implements Hud {
    private Texture barra;
    private int staminavalor = 10;
    private final float fixo_x;
    private final float fixo_y;
    private static final int staminaMax = 100; 
    
    private Player player;
    
    public Stamina(Player player) {
        this.barra = new Texture("ui/barra.png");
        this.player = player;
        fixo_y = 10f;
        fixo_x = Gdx.graphics.getWidth() - barra.getWidth() - 10f;
    }
    @Override
    public void desenho(SpriteBatch batch) {
        float stamina = player.getStamina();
        float percentage = stamina / (float)staminaMax;
        float totalFillHeight = barra.getHeight();
        float currentFillHeight = totalFillHeight * percentage;
        currentFillHeight = MathUtils.clamp(currentFillHeight, 0, totalFillHeight);
        batch.draw(barra, fixo_x, fixo_y, barra.getWidth(), currentFillHeight);      
    }
    
    public void dispose() {
		barra.dispose();
    }
}