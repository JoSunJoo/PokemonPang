import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 한국기술교육대학교 컴퓨터공학부
 * 2017년도 2학기 학기 프로젝트: 포켓몬팡
 * @author 김상진 
 * 포켓몬팡의 유틸리티 클래스: 
 * 1) 뷰, 모델, 컨트럴러에 속하지 않는 static 함수 모음
 * 2) 뷰, 모델, 컨트럴러에 공통으로 필요로 하는 상수 정의
 */

public interface PangUtility {
	public static int NUMBEROFMONS = 7;
	public static int GRIDSIZE = 7;
	public static int POKETMONIMAGESIZE = 80;
	public static void pokemonInfoDialog(String title, String content){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		ImageView icon = new ImageView(new Image("pokemon.png"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
		alert.showAndWait();
	}
	public static boolean pokemonConfirmDialog(String title, String content,
			String okButton, String cancelButton){
		Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(content);
    	ButtonType buttonTypeOK = new ButtonType(okButton, ButtonData.OK_DONE);
    	ButtonType buttonTypeCancel = new ButtonType(cancelButton, ButtonData.CANCEL_CLOSE);
    	alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
    	ImageView icon = new ImageView(new Image("pokemon.png"));
		icon.setFitHeight(80);
		icon.setPreserveRatio(true);
		alert.setGraphic(icon);
    	Optional<ButtonType> result = alert.showAndWait();
    	return (result.get() == buttonTypeOK);
	}
}
