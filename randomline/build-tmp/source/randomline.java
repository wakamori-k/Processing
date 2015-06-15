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

public class randomline extends PApplet {

class MyPairFloat{
	public float first;
	public float second;

	MyPairFloat(float i,float j){
		this.first = i;
		this.second = j;
	}
}

final int NUM = 100;
final float SIZE = 10;
MyPairFloat[] grids = new MyPairFloat[NUM];//\u5ea7\u6a19

public void setup(){
	size(800,500);
	colorMode(RGB, 256);
	//frameRate(10);

	for(int i=0;i<NUM;i++){
		grids[i] = new MyPairFloat(random(0, width),random(0, height));
	}
}

public void draw(){
	background(0,0,0);
	for(int i=0;i<NUM;i++){
		grids[i].first = random(0, width);
		grids[i].second = random(0, height);
		fill(0, 127, 255);
		//ellipse(grids[i].first,grids[i].second ,SIZE ,SIZE);
	}


	for(int i=0;i<NUM;i++){
		for(int j=0;j<NUM;j++){
			if(i != j){
				if(PApplet.parseInt(random(0, 30)) == 0){// 1/10\u306e\u78ba\u7acb\u3067\u7dda\u3092\u3072\u304f
					stroke(0, 127, 255);//\u7dda\u306e\u8272
					line(grids[i].first, grids[i].second, grids[j].first, grids[j].second);
				}
			}
		}
	}
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "randomline" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
