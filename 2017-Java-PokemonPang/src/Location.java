
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
