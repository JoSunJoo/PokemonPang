import java.util.ArrayList;
 // 모델이 뷰에 전달하는 정보

public class ModelToViewData {
	// size==0이면 전체 갱신
	public ArrayList<Location> locationList;
	public Pokemon[][] gridData;
	public ModelToViewData(ArrayList<Location> locationList, Pokemon[][] gridData){
		this.locationList = locationList;
		this.gridData = gridData;
	}
}
