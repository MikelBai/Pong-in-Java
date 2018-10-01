
public class Ball {
	static final int SIDE = 6;
	private Drew parent;
	private int x;
	private int y;
	private double velocity;
	private double angle;
	public static int greenScore = 0;
	public static int pinkScore = 0;
	public Ball(Drew d, int xcoord, int ycoord, int colorNum, double ang,  int velo){
		parent = d;
		x = xcoord;
		y = ycoord;
		velocity = velo;
		angle = ang;
	}
	public void draw(){
		parent.fill(255,255,255);
		parent.rect(x, y, SIDE, SIDE);
	}
	public void move(){
		x += velocity * Math.cos(Math.toRadians(angle));
		y += velocity * Math.sin(Math.toRadians(angle));
//		System.out.println(System.nanoTime());
		if(this.x > 800){
			this.x = 400;
			this.y = 400;
			++greenScore;
			angle = 180;
			velocity = 0;
		}
		if(this.x < 0){			
			this.x = 400;
			this.y = 400;
			++pinkScore;
			angle = 0;
			velocity = 0;
		}
	}
	public void negMove(){
		x -= velocity * Math.cos(Math.toRadians(angle));
		y -= velocity * Math.sin(Math.toRadians(angle));
	}
	public void halfMove(){
		x += velocity/2 * Math.cos(Math.toRadians(angle));
		y += velocity/2 * Math.sin(Math.toRadians(angle));
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getSide(){
		return SIDE;
	}
	public double getAngle(){
		return angle;
	}
	public void bounceGreenTop(Paddle paddle){
		switch(paddle.determineAngleTop(this)){
			case 0: angle = -20;//20 - 360 = 340
			move();
			System.out.println("0 green top");
			break;
			case -1: angle = -30;
			move();
			System.out.println("-1 green top");
			break;
			case -2: angle = -40;
			move();
			System.out.println("-2 green top");
			break;
			case -3: angle = -50;
			move();
			System.out.println("-3 green top");
			break;
			case -4: angle = -60;
			move();
			System.out.println("-4 green top");
			break;
			case -5: angle = -70;
			move();
			System.out.println("-5 green top");
			break;
			default: angle = -30;
			move();
			System.out.println("fa green top");
			break;
		}
	}
	public void bounceGreenBot(Paddle paddle){
		switch(paddle.determineAngleBot(this)){
			case 0: angle = 20;
			move();
			System.out.println("0 green bot");
			break;
			case 1: angle = 30;
			move();
			System.out.println("1 green bot");
			break;
			case 2: angle = 40;
			move();
			System.out.println("2 green bot");
			break;
			case 3: angle = 50;
			move();
			System.out.println("3 green bot");
			break;
			case 4: angle = 60;
			move();
			System.out.println("4 green bot");
			break;
			case 5: angle = 70;
			move();
			System.out.println("5 green bot");
			break;
			default: angle = 30;
			move();
			System.out.println("fs green bot");
			break;
		}
	}
	public void bouncePinkTop(Paddle paddle){
		switch(paddle.determineAngleTop(this)){
			case 0: angle = 200;//180 + 20
			System.out.println("0 pink top");
			break;
			case -1: angle = 210;
			move();
			System.out.println("-1 pink top");
			break;
			case -2: angle = 220;
			move();
			System.out.println("-2 pink top");
			break;
			case -3: angle = 230;
			move();
			System.out.println("-3 pink top");
			break;
			case -4: angle = 240;
			move();
			System.out.println("-4 pink top");
			break;
			case -5: angle = 250;
			move();
			System.out.println("-5 pink top");
			break;
			default: angle = 220;
			move();
			System.out.println("fd pink top");
			break;
		}
	}
	public void bouncePinkBot(Paddle paddle){
		switch(paddle.determineAngleBot(this)){
			case 0: angle = -200;//180 + 20 - 360
			move();
			System.out.println("0 pink bot");
			break;
			case 1: angle = -210;
			move();
			System.out.println("1 pink bot");
			break;
			case 2: angle = -220;
			move();
			System.out.println("2 pink bot");
			break;
			case 3: angle = -230;
			move();
			System.out.println("3 pink bot");
			break;
			case 4: angle = -240;
			move();
			System.out.println("4 pink bot");
			break;
			case 5: angle = -250;
			move();
			System.out.println("5 pink bot");
			break;
			default: angle = -220;
			move();
			System.out.println("ff pink bot");
			break;
		}
	}
	public void changeVelo(int newVelo){
		velocity = newVelo;
	}
	public void reverseWall(){
		if(this.y > 800 || this.y < 0){
			angle = 360 - angle;
		}
	}//360-angle
	public void bounceBlocker(Blocker blocker){
//		if(blocker.getX() > this.x ){
//			this.y >= blocker.getY() - blocker.getHalfHeight() &&
//		this.y <= blocker.getY() + blocker.getHalfHeight()
//		}
		if(this.x - SIDE/2 <= blocker.getX() + blocker.getHalfWidth() &&
			this.x + SIDE/2 >= blocker.getX() - blocker.getHalfWidth() &&
			!blocker.isInsideVert(this)){
			angle = 360 - angle;
//			System.out.println("FHADLK");
		}
		else if(this.x - SIDE/2 == blocker.getX() + blocker.getHalfWidth() ||
				this.x + SIDE/2 == blocker.getX() - blocker.getHalfWidth() &&
				!blocker.isInsideVert(this)){
			angle = 360 - angle;
			System.out.println("DFJLKDSF");
		}
	}
	public void checkColGreen(Paddle paddle){ //the leftmost paddle
		if(this.x - SIDE/2 <= paddle.getX() + paddle.getWidth()/2 &&
				this.x >= paddle.getX() - paddle.getHalfWidth()){
			if(paddle.isMid(this)){
				System.out.println(angle);
				angle = 180 - angle;
			}
			else if(paddle.isTop(this)){
				System.out.println(angle);
//				angle = 330;
				bounceGreenTop(paddle);
			}
			else if(paddle.isBot(this)){
				System.out.println(angle);
//				angle = 30;
				bounceGreenBot(paddle);
			}
		}
	}
	public void checkColGreenReg(Paddle paddle){
		if(this.x - SIDE/2 <= paddle.getX() + paddle.getWidth()/2&&
				this.y > paddle.getY() - paddle.getHalfHeight() &&
				this.y < paddle.getY() + paddle.getHalfHeight() &&
				this.x >= paddle.getX() - paddle.getHalfWidth()
				){
			angle = 180 - angle;
		}
	}
	public void checkColPink(Paddle paddle){//width = 10, getX = 20
		if(this.x + SIDE/2 >= paddle.getX() - paddle.getWidth()/2 &&
				this.x <= paddle.getX() + paddle.getHalfWidth()){
			if(paddle.isMid(this)){
				System.out.println(angle);
				angle = 180 - angle;
			}
			else if(paddle.isTop(this)){
				System.out.println(angle);
//				angle = 210;
				bouncePinkTop(paddle);
			}
			else if(paddle.isBot(this)){
				System.out.println(angle);
//				angle = 150;
				bouncePinkBot(paddle);
			}
//			if(Ball.x == paddle.getX() + paddle.getHalfHeight()){
//				angle = 70;
//			}
//			if(ball.)
		}
	}
	public void checkColPinkReg(Paddle paddle){
		if(this.x + SIDE/2 >= paddle.getX() - paddle.getWidth()/2 &&
				this.y > paddle.getY() - paddle.getHalfHeight() &&
				this.y < paddle.getY() + paddle.getHalfHeight() &&
				this.x <= paddle.getX() + paddle.getHalfWidth()){
			angle = 180 - angle;
		}
	}
	public boolean ballInGreen(Paddle paddle){
		if(this.x - this.getSide() < paddle.getX() - paddle.getHalfWidth() &&
				this.y > paddle.getY() - paddle.getHalfHeight() &&
				this.y < paddle.getY() + paddle.getHalfHeight()){
			return true;
		}
		return false;
	}
	public boolean ballInPink(Paddle paddle){
		if(this.x + this.getSide() > paddle.getX() + paddle.getHalfWidth() &&
				this.y > paddle.getY() - paddle.getHalfHeight() &&
				this.y < paddle.getY() + paddle.getHalfHeight()){
			return true;
		}
		return false;
	}
	public void blockerBounceSide(Blocker blocker){
		if(this.x > blocker.getX() + blocker.getHalfWidth() - 9||
				this.x < blocker.getX() - blocker.getHalfWidth() + 9){
			angle = 180 - angle;
		}
	}
}
