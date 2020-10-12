//160 degrees to work with
//top + 80
//bottom - 80
//middle * 0.2
//then 0.4, 0.6, 0.8, 1
public class Paddle{ //extends pApplet vs Drew parent?
	private final int WIDTH = 10;
	private final int HALFWIDTH = WIDTH/2;//5
	private final int HEIGHT = 140;
	private final int HALFHEIGHT = HEIGHT/2;//70
	private final int MIDZONE = 10;
//	private final int TOPEDGE = this.y - HALFHEIGHT;
//	private final int BOTEDGE = this.y + HALFHEIGHT;
	private Drew parent;
	private int x;
	private int y;
	private int color;
	
	public Paddle(Drew d, int xcoord, int ycoord, int colorNum){
		parent = d;
		x = xcoord;
		y = ycoord;
		color = colorNum;
	}
	public void draw(){
		parent.fill(color);
		parent.rect(x,y,WIDTH,HEIGHT);
	}
	public boolean hasBallInside(Ball ball){
		if(ball.getX() > this.x - HALFWIDTH  && ball.getX() < this.x + HALFWIDTH &&
				ball.getY() > this.y - HALFHEIGHT && ball.getY() < this.y + HALFHEIGHT){
			return true;
		}
		return false;
	}
	public boolean isTop(Ball ball){
		if(ball.getY() < this.y - MIDZONE && ball.getY() > this.y - HALFHEIGHT){//top 70
			return true;
		}
		return false;
	}
	public boolean isBot(Ball ball){
		if(ball.getY() > this.y + MIDZONE && ball.getY() < this.y + HALFHEIGHT){//bot 70
			return true;
		}
		return false;
	}
	public boolean isMid(Ball ball){
		if(ball.getY() > this.y - MIDZONE && ball.getY() < this.y + MIDZONE){//middle 40
			return true;
		}
		return false;
	}
	public int determineAngleTop(Ball ball){
		return (ball.getY() - this.y + MIDZONE)/10;
	}
//	(int)(ball.y - paddle.y + MIDZONE)/10
	public int determineAngleBot(Ball ball){
		return (ball.getY() - this.y - MIDZONE)/10;
	}
	public void moveDown(int i){
		y += i;
	}
	public void moveUp(int i){
		y -= i;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return WIDTH;
	}
	public int getHeight(){
		return HEIGHT;
	}
	public int getHalfHeight(){
		return HALFHEIGHT;
	}
	public int getHalfWidth(){
		return HALFWIDTH;
	}
	public int getColor(){
		return color;
	}
//	update TOPEDGE if you wanna do this EVER
//	int totCount = 1;
//	for(int i = this.y - MIDZONE; i >= TOPEDGE; i-=10){
//		++totCount;
//		if(ball.getY() >= i){
//			return totCount;
//		}
//	}
//	return -1;
//	update BOTEDGE if you wanna do this EVER
//	int totCount = 1;
//	for(int i = this.y + MIDZONE; i <= BOTEDGE; i+=10){
//		++totCount;
//		if(ball.getY() <= i){
//			return totCount;
//		}
//	}
//	return -1;
}
