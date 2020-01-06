/**
 * 한국기술교육대학교 컴퓨터공학부
 * 2017년도 2학기 학기 프로젝트: 포켓몬팡
 * @author 김상진 
 * 그리드 위치 접근 자료구조
 */

public class Location {
	public int r = 0;
	public int c = 0;
	public Location(int r, int c){
		this.r = r;
		this.c = c;
	}
	public boolean equals(Object o){
		if(o==null||getClass()!=o.getClass()) return false;
		if(this==o) return true;
		Location loc = (Location)o;
		return r==loc.r&&c==loc.c;
	}
	public static boolean valid(int r, int c){
		return (r>=0&&r<PangUtility.GRIDSIZE && 
				c>=0&&c<PangUtility.GRIDSIZE);
	}
}
