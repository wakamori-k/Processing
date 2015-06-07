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

public class circle extends PApplet {

Circle[] circle;
int onMouse_num = 0;
final int circle_num = 100;
final int colors[] = {color(0xff, 0x7f, 0x7f), color(0xff, 0x7f, 0xbf), color(0xff, 0x7f, 0xff), color(0xbf, 0x7f, 0xff), color(0x7f, 0x7f, 0xff), color(0x7f, 0xbf, 0xff), color(0x7f, 0xff, 0xff), color(0x7f, 0xff, 0xbf), color(0x7f, 0xff, 0x7f), color(0xbf, 0xff, 0x7f), color(0xff, 0xff, 0x7f), color(0xff, 0xbf, 0x7f)};

public void setup(){
	size(500,500);
	smooth();
	noStroke();
	colorMode(RGB,256);


	circle = new Circle[circle_num];

	for(int i=0;i<circle_num;i++)
		circle[i] = new Circle(PApplet.parseInt(random(0,500)), PApplet.parseInt(random(0,500)), PApplet.parseInt(random(10,100)), colors[ PApplet.parseInt(random(0, colors.length ))  ]);

	frameRate(1000);
}

public void draw(){
	background(255,255,255);
	for(int i=0;i<circle_num;i++)
		circle[i].update();
	
}

class Circle{
	float x,y;
	int across;
	int c;
	boolean onMouse = false;

	Circle(float y,float x,int across,int c){
		this.y = y;
		this.x = x;
		this.across = across;
		this.c = c;
	}

	public void update(){
		if(onMouse_num < circle_num){
			if(abs(y - mouseY) > across/2.0f || abs(x - mouseX) > across/2.0f){
				float xtmp,ytmp;
				xtmp = x;
				ytmp = y;
				x = random(x,x+1);
				y = random(y,y+1);

				if(x+across/2.0f > width || x+across/2.0f < 0){
					x = 0;
					y = random(0,500);
				}
				if(y+across/2.0f > height || y+across/2.0f < 0){
					x = random(0,500);
					y = 0;
				}
			}else if(!onMouse){
				onMouse = true;
				onMouse_num ++ ;
			}
			if(onMouse){
				x = mouseX;
				y = mouseY;
			}
		}else{
			textSize(50);
			text("CLEAR!", 10, 50);
		}
		fill(c);
		ellipse(x, y, this.across, across);
	}

}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "circle" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
