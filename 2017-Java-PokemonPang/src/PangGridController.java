import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * 한국기술교육대학교 컴퓨터공학부
 * 2017년도 2학기 학기 프로젝트: 포켓몬팡
 * @author 김상진 
 * 포켓몬팡의 Controller 클래스: 각 종 이벤트 처리
 */
public class PangGridController {
	private Timeline gameOverTimeline = new Timeline();
	private Timeline hintTimeline = new Timeline();
	private PangGridView view = null;
	private PangGridModel model = null;
	private Location srcLoc = null;
	private Location destLoc = null;
	/*
	 *  콤보 관련 멤버들
	 *  콤보는 최초 3초 이전에 계속 팡하면 콤보 증가, 그 다음에 2.5초, 2초, 1초
	 */
	private Timeline comboTimeline = new Timeline();
	private int[] comboTime = {3000, 2500, 2000, 1500};
	private int comboTimeIndex = 0;
	
	private void restartGameOverTimeLine(){
		gameOverTimeline.stop();
		gameOverTimeline.jumpTo(Duration.ZERO);
		gameOverTimeline.play();
	}
	private void restartHintTimeLine(){
		//System.out.println("힌트 재시작:" + model.getTimeLeft()+" : "+System.currentTimeMillis());
		view.removeHint();
		hintTimeline.stop();
		hintTimeline.jumpTo(Duration.ZERO);
		hintTimeline.play();
	}
	private void restartComboTimeLine(){		
		//System.out.println("콤보 재시작:" + model.getCombo()+", "+model.getTimeLeft()+" : "+System.currentTimeMillis());
		comboTimeline.stop();
		comboTimeline.getKeyFrames().clear();
		comboTimeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(comboTime[comboTimeIndex]),e->{
					//System.out.println("콤보 타임마웃:" + model.getTimeLeft()+" : "+System.currentTimeMillis());
					model.updateCombo(true);
					if(comboTimeIndex<comboTime.length-1) ++comboTimeIndex;
				}));
		comboTimeline.setCycleCount(1);
		comboTimeline.jumpTo(Duration.ZERO);
		comboTimeline.play();
	}
	/**
	 * 1초마다 타이머에 의해 호출되는 함수 
	 */
	public void gameOverHandle(ActionEvent event) {
		model.updateGameTime();
		if(model.isGameOver()) gameOver();
	}
	private void mouseClickHandle(MouseEvent mouseEvent) {
		double x = mouseEvent.getX()+1;
		double y = mouseEvent.getY()+1;
		
		int r = (int)(y/PangUtility.POKETMONIMAGESIZE);
    	int c = (int)(x/PangUtility.POKETMONIMAGESIZE);
    	if(srcLoc==null){
    		srcLoc = new Location(r,c);
    		if(model.processSpecialPokemon(r,c)){
    			processClick();
    			model.updateCombo(false);
    			restartHintTimeLine();
    			restartComboTimeLine();
    			srcLoc = null;
    		}
    	}
    	else{
    		destLoc = new Location(r,c);
    		if(model.isValidSwap(srcLoc,destLoc)){
    			model.swap(srcLoc, destLoc);
    			if(model.checkAndMark()) processClick();
    			model.updateCombo(false);
    			restartHintTimeLine();
    			restartComboTimeLine();
    		}
    		srcLoc = destLoc = null;
    	}
    }
	private void processClick(){
		SequentialTransition seq = new SequentialTransition();
		PauseTransition pushUpTransition = new PauseTransition(Duration.millis(100));
		pushUpTransition.setOnFinished(event->model.pushUpMarked());
		PauseTransition replaceTransition = new PauseTransition(Duration.millis(100));
		replaceTransition.setOnFinished(event->model.replaceMarked());
		seq.getChildren().addAll(pushUpTransition, replaceTransition);
		seq.setOnFinished(event->{
			Sound.play("pang");
			if(model.checkAndMark()) processClick();
			else{
				model.insertSpecialPokemon();
    			if(!model.findHints()) gameOver();
			}
		});
		seq.play();
	}
	
	public PangGridController(PangGridView view, PangGridModel model){
		this.view = view;
		this.model = model;
		view.getPangGrid().setOnMouseClicked(e->mouseClickHandle(e));
		gameOverTimeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(1000),e->gameOverHandle(e)));
		gameOverTimeline.setCycleCount(Animation.INDEFINITE);
		hintTimeline.getKeyFrames().add(
				new KeyFrame(Duration.millis(3000),e->{
					//System.out.println("힌트시간 초과:" + model.getTimeLeft()+" : "+System.currentTimeMillis());
					model.findHints();
					view.showHint();}));
		hintTimeline.setCycleCount(1);
	}
	public void startGame(){
		Sound.play("opening");
		PangUtility.pokemonInfoDialog("PokemonPang 게임시작", "게임을 시작하시겠습니까???");
		Sound.stop("opening");
		initGame();
	}
	/**
	 * 새 게임마다 새롭게 초기화되어야 하는 것들을 초기화
	 */
	public void initGame(){
		model.initAssign();
		comboTimeIndex = 0;
		restartGameOverTimeLine(); // 타이머 시작
		restartHintTimeLine(); // 힌트 타이머 시작
		restartComboTimeLine();	// 콤보 타이머 시작
		Sound.play("pokemon");
	}
	private void lastPang(){
		ArrayList<Location> ghostLocs = model.getSpecialLocs(Pokemon.GASTLY);
		for(Location loc: ghostLocs)
			if(model.processSpecialPokemon(loc.r, loc.c)) processClick();
		ArrayList<Location> dragonLocs = model.getSpecialLocs(Pokemon.DRAGONITE);
		for(Location loc: dragonLocs)
			if(model.processSpecialPokemon(loc.r, loc.c)) processClick();
	}
	public void gameOver(){
		lastPang();
		gameOverTimeline.stop(); 	// 각종 타이머 종료
		hintTimeline.stop();
		comboTimeline.stop();
		view.removeHint();
		Sound.stop("pokemon");
		final Window stage = view.getScene().getWindow();
		
		Platform.runLater(new Runnable() {
            @Override
            public void run() {
            	HallOfFame dialog = new HallOfFame();
    			dialog.show(model.getScore());
    			stage.hide();
            	if(PangUtility.pokemonConfirmDialog("PokemonPang 게임종료", "새 게임을 하시겠습니까???", 
            			"새 게임", "게임 종료")){
            		initGame();
            		((Stage)stage).show();
        		}
        		else Platform.exit();
            }
        });
	}
}
