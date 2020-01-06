import java.io.Serializable;
import java.time.LocalDateTime;


public class HallOfFameData implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rank;
	private String name;
	private LocalDateTime gameDate;
	private int score;
	public HallOfFameData(){
	}
	public HallOfFameData(String name, LocalDateTime gameDate, int score) {
		this.name = name;
		this.gameDate = gameDate;
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public String getGameDate() {
		String output = String.format("%d년 %d월 %d일 %d시 %d분", gameDate.getYear(), gameDate.getMonth().getValue(), gameDate.getDayOfMonth(), 
			gameDate.getHour(), gameDate.getMinute());
		return output;
	}
	public LocalDateTime getDate(){
		return gameDate;
	}
	public int getRank() {
		return rank;
	}
	public int getScore() {
		return score;
	}
	public String getGameScore(){
		return String.format("%,d", score);
	}
	public void setRank(int rank){
		this.rank = rank;
	}
}

