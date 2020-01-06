import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;

public class Sound {
	public static String dir;
    static {
        dir = "file:///" + System.getProperty("user.dir").replace('\\', '/').replaceAll(" ", "%20");
        try {
            dir = new java.net.URI(dir).toASCIIString();
        } 
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private static AudioClip opening = new AudioClip(dir + "/opening.mp3");
	private static AudioClip pang = new AudioClip(dir + "/pang.mp3");
	private static AudioClip dragonite = new AudioClip(dir + "/dragonite.mp3");
	private static AudioClip pokemon = new AudioClip(dir + "/pokemon.mp3");
	private static AudioClip snorlax = new AudioClip(dir + "/clock.mp3");
	
	
	//private static AudioClip bomb = new AudioClip(dir + "/bomb.mp3");
	
	private static Map<String, AudioClip> soundBox = new HashMap<>();
	static{
		soundBox.put("opening", opening);
		soundBox.put("pokemon", pokemon);
		soundBox.put("pang", pang);
		soundBox.put("dragonite", dragonite);
		soundBox.put("snorlax", snorlax);
	}
	
	public static void play(String key){	
		AudioClip clip = soundBox.get(key);
		if(clip!=null){
			clip.play();
		}
	}
	public static void stop(String key){
		AudioClip clip = soundBox.get(key);
		if(clip!=null){
			clip.stop();
		}
	}
}
