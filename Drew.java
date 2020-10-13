import processing.core.*;
public class Drew extends PApplet{
//	runs once, use to initialize
//	see processing.core.PApplet#setup()
	Paddle green;
	Paddle pink;
	Blocker blockUp;
	Blocker blockDown;
	Ball ball;
	private PFont myFont;
//	int ballVel = 0;
	
	public void setup(){
		rectMode(CENTER);
		green = new Paddle(this, 25, 400, 0xFF84F5B5); //0xFF84F5B5
		pink = new Paddle(this, 775, 400, 0xFFF584C4); //0xFFF584C4
		blockUp = new Blocker(this, 400, 200, 0xFF4DD2FF);
		blockDown = new Blocker(this, 400, 600, 0xFF4DD2FF);
		myFont = createFont("perpetua", 48, true);
		double ballAngle = Math.random()*100;
		while(ballAngle >= 65 || ballAngle <= 25){
			ballAngle = Math.random()*100;
		}
		ball = new Ball(this, 400, 400, 0, ballAngle, 0);//spawn in, velocity low - hit, velocity highh
	}
//	draws many times per second(FPS)
	public void draw(){
		frameRate(60);
		background(0,0,0);
		green.draw();
		pink.draw();
		blockUp.draw();
		blockDown.draw();
		ball.draw();
		if (w){
			green.moveUp(9);
		}
		if (s){
			green.moveDown(9);
		}
		if (up){
			pink.moveUp(9);
		}
		if (down){
			pink.moveDown(9);
		}
		ballMove();
		drawScore();
		if(w || s){
			ball.checkColGreen(green);
		}
		if(up || down){
			ball.checkColPink(pink);
		}
		if(ball.ballInGreen(green) && greenMoving()||
				ball.ballInPink(pink) && pinkMoving()){
			ball.halfMove();
		}
		else{
			ball.checkColGreenReg(green);
			ball.checkColPinkReg(pink);
		}
		if(mousePressed == true){
			stroke(255);
			line(pmouseX, pmouseY, mouseX, mouseY);
		}
	}

	public void drawScore(){
		textFont(myFont);
		fill(255);
		text("Green: " +Ball.greenScore,450,450);
		text("Pink: " +Ball.pinkScore,400,400);
	}
//	setup size x y or set fullScreen()
//	runs BEFORE SKETCH SETUP
//	no access to processing methods
//	@see procesing.core.PApplet#setup()
	public void settings(){
		size(800, 800);
	}
	public void ballMove(){
		ball.move();
		ball.reverseWall();
		ball.bounceBlocker(blockUp);
		ball.bounceBlocker(blockDown);
		ball.blockerBounceSide(blockUp);
		ball.blockerBounceSide(blockDown);
		if(ball.getX() <= 0 ||ball.getX() >= 800){
			System.out.println("Pink: "+Ball.pinkScore);	
			System.out.println("Green: "+Ball.greenScore);
		}
	}
	public static void main(String args[]){
		PApplet.main(new String[] {"Drew"});
	}
	
	public void keyPressed(){
		if(key == 'w' || key == 's'||
				keyCode == UP || keyCode == DOWN){
			ball.changeVelo(9);
		}
		if (key == 'w'){
			w = true;
		}
		else if (key == 's'){
			s = true;
		}
		if (keyCode == UP){
			up = true;
		}
		else if (keyCode == DOWN){
			down = true;
		}
	}

	private static boolean w;
	private static boolean s;
	private static boolean up;
	private static boolean down;
	public static boolean greenMoving(){
		if(w || s){
			return true;
		}
		return false;
	}
	public static boolean pinkMoving(){
		if(up || down){
			return true;
		}
		return false;
	}
	public void keyReleased(){
		if (key == 'w'){
			w = false;
		}
		else if (key == 's'){
			s = false;
		}
		if (keyCode == UP){
			up = false;
		}
		else if (keyCode == DOWN){
			down = false;
		}
	}
}

