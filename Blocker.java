public class Blocker {
	private Drew parent;
	private int width = 200;
	private int halfWidth = width/2;
	private int height = 12;
	private int halfHeight = height/2;
	private int x;
	private int y;
	private int color;
	public Blocker(Drew d, int xcoord, int ycoord, int colornum){
		parent = d;
		x = xcoord;
		y = ycoord;
		color = colornum;
	}
	public void draw(){
		parent.fill(color);
		parent.rect(x,y,width,height);
	}
//	public void bounce(Ball ball){
//		if(ball.getX() > this.x ){
//			
//		}
//		if(ball.getX() > this.x - this.halfWidth &&
//			ball.getX() < this.x + this.halfWidth &&
//			ball.getY() > this.y - this.halfHeight &&
//			ball.getY() < this.y + this.halfHeight){
//			
//		}
//	}
	public boolean isInsideVert(Ball ball){
		if(ball.getY() > this.y - halfHeight && ball.getY() < this.y + halfHeight){
			return true;
		}
		return false;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getHalfWidth(){
		return halfWidth;
	}
	public int getHalfHeight(){
		return halfHeight;
	}
}
