import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ball extends PApplet {

class Circle{
	private float x,y;				//\u5ea7\u6a19
	private float across;			//\u5186\u306e\u76f4\u5f84
	private PVector speed; 			//\u901f\u5ea6\u30d9\u30af\u30c8\u30eb
	private int c;				//\u8272
	private int id;

	Circle(int id,float y,float x,int c,float across){
		this.y = y;
		this.x = x;
		this.across = across;
		this.c = c;
		speed = new PVector(0,5);
		this.id = id;
	}

	public void update(){
		y += speed.y;
		x += speed.x;

		if(y+across/2.0f > height){
			speed.y *= -1;
			y = height - across/2.0f;

		}else if(y-across/2.0f < 0){
			speed.y *= -1;
			y = across/2.0f;
		}
		if(x+across/2.0f > width){
			speed.x *= -1;
			x = width - across/2.0f;
		}else if( x-across/2.0f < 0){
			speed.x *= -1;
			x = across/2.0f;
		}

		fill(c);
		ellipse(x, y, this.across, across);
	}

	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y;
	}

	public float getAcross(){
		return this.across;
	}

	public PVector getSpeedVector(){
		return this.speed;
	}

	public void rotateSpeedVector(float rad){
		this.speed.rotate(rad);
	}

	public void changeColor(){
		this.c = colors[ PApplet.parseInt(random(0, colors.length ))  ];
	}
}



Circle[] ball;
final int ball_num = 30;
final int colors[] = {color(0xff, 0x7f, 0x7f), color(0xff, 0x7f, 0xbf), color(0xff, 0x7f, 0xff), color(0xbf, 0x7f, 0xff), color(0x7f, 0x7f, 0xff), color(0x7f, 0xbf, 0xff), color(0x7f, 0xff, 0xff), color(0x7f, 0xff, 0xbf), color(0x7f, 0xff, 0x7f), color(0xbf, 0xff, 0x7f), color(0xff, 0xff, 0x7f), color(0xff, 0xbf, 0x7f)};
final float ball_size = 40.0f;

public void setup(){
	size(1000,500);
	smooth();
	noStroke();
	colorMode(RGB,256);


	ball = new Circle[ball_num];

	for(int i=0;i<ball_num;i++){
		ball[i] = new Circle(i,PApplet.parseInt(random(0,height)), PApplet.parseInt(random(0,width)), colors[ PApplet.parseInt(random(0, colors.length )) ], ball_size);
	}
//	frameRate(100);
}

public void draw(){
	background(0,0,0);
	for(int i=0;i<ball_num;i++){
		ball[i].update();
	}
	
	for(int i=0;i<ball_num;i++){
		for(int j=0;j<ball_num;j++){
			float dist = sqrt(sq(abs(ball[i].getX()-ball[j].getX())) + sq(abs(ball[i].getY()-ball[j].getY())));
			float min_dist = ball[i].getAcross()/2.0f + ball[j].getAcross()/2.0f;
			if(i != j && dist < min_dist){
				PVector pv = new PVector(ball[i].getX()-ball[j].getX(), ball[i].getY()-ball[j].getY());
				float rad = PVector.angleBetween(ball[i].getSpeedVector(),pv);
				ball[i].rotateSpeedVector(rad);
				ball[i].changeColor();
			}
		}
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ball" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
