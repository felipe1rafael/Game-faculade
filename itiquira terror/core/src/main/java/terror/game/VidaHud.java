		package terror.game;
		
		import com.badlogic.gdx.Gdx;
		import com.badlogic.gdx.graphics.Texture;
		import com.badlogic.gdx.graphics.g2d.SpriteBatch;
		
		import lombok.Getter;
		import lombok.Setter;
		@Setter
		@Getter
		public class VidaHud implements Hud {
		private Texture vidaTexture;		
		private final float espaco = 5f; 		
		private final float fixo_Y;
		private Player player;
		public VidaHud(Player player) {
			this.vidaTexture = new Texture("ui/coracao.png");
			fixo_Y = Gdx.graphics.getHeight() - 700;
			this.player = player;
		}
		@Override
		public void desenho(SpriteBatch batch) {
		    int coracoes = player.getHealth(); 
		    float currentX = 10f;
		    if(coracoes > 0 && coracoes < 4) {
		    for (int i = 0; i < coracoes; i++) {
		        batch.draw(vidaTexture, currentX, fixo_Y);
		        currentX += vidaTexture.getWidth() + espaco;
		    }}
		}
		public void dispose() {
		    vidaTexture.dispose();
		}
		}
		
