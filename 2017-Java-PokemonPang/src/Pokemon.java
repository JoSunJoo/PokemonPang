import javafx.scene.image.Image;

public enum Pokemon {
	BULBASAUR("bulbasaur.png"), 		// 이상해
	CHARMANDER("charmander.png"), 	// 피아리
	CYNDAQUIL("cyndaquil.png"), 		// 브케인 
	EEVEE("eevee.png"),				// 이브이 
	JIGGLYPUFF("jigglypuff.png"),		// 푸린 
	PIKACHU("pikachu.png"), 			// 피카추 
	SQUIRTLE("squirtle.png"), 			// 꼬부기
	POKEBALL("pokeball.png"),			// 체크 용도 
	DRAGONITE("dragonite.png"), 		// 망나뇽  특수 아이템 
	GASTLY("phantom.png"), 			// 고오스  특수 아이템
	SNORLAX("snorlax.png"); 			// 잠맘보  특수 아이템
	private Image image;
	private Pokemon(String fileName){
		this.image = new Image(fileName);
	}
	public Image getImage(){
		return image;
	}
}
