import java.util.ArrayList;

/**
 * 한국기술교육대학교 컴퓨터공학부
 * 2017년도 2학기 학기 프로젝트: 포켓몬팡
 * @author 김상진 
 * 모델이 뷰에 전달하는 정보
 */

public class ModelToViewData {
	// size==0이면 전체 갱신
	public ArrayList<Location> locationList;
	public Pokemon[][] gridData;
	public ModelToViewData(ArrayList<Location> locationList, Pokemon[][] gridData){
		this.locationList = locationList;
		this.gridData = gridData;
	}
}
