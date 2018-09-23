package environnement;

public class Grid {
	Box[][] grid;
	
	public Grid() {
		for (int i=0;i<2;i++) {
			for(int j=0;j<10;j++) {
				grid[i][j]=new Box(0,0);
			}
		}
	}
	public Box getBoxI(int i,int j) {
		return(grid[i][j]);
	}
	
	public void generateEnvironment(){
		for (int i=0;i<2;i++) {
			for(int j=0;j<10;j++) {
				grid[i][j].generateJewel();
				grid[i][j].generateDirt();
			}
		}
	}
}
