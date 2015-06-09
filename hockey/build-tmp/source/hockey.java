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

public class hockey extends PApplet {

float ballx,bally;
float speedx,speedy;
float malletH,malletW;
float ballSize;


public void setup(){
	size(800,500);
	colorMode(RGB,256);

	bally = height/2.0f;
	ballx = width/2.0f;
	speedx = 5;
	speedy = 5;

	ballSize = 40;
	malletW = 20;
	malletH = 50;
}

public void draw(){
	background(0,0,0);
	
	fill(0,0,255);
	ellipse(ballx, bally, ballSize/2, ballSize/2);

	ballx += speedx;
	bally += speedy;
	if(ballx-malletW <0 || ballx > width-malletW){
		speedx *= -1;
	}
	if(bally-malletW < 0 || bally > height-malletW){
		speedy *= -1;
	}

	fill(255,0,0);
	rect(0,bally-(malletH/2),malletW,malletH);
	rect(width-malletW,bally-(malletH/2),malletW,malletH);	

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "hockey" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
