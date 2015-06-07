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

public class circlerandom extends PApplet {

int WIDTH=1000;
int HEIGHT=1000;
int count=0;

public void setup(){
   size(500,500);
   colorMode(HSB,100);
   background(99);
   frameRate(100);
}

public void draw(){
   fill(random(100), 40, 99);
   noStroke();
   smooth();
   float r=random(50);
   ellipse(random(WIDTH), random(HEIGHT), r,r);
   count++;
   if(count==10){
     fade();
     count=0;
   }
} 

public void fade(){
   fill(99,30);
   rectMode(CORNER);
   rect(0, 0, WIDTH*2, HEIGHT*2);
} 
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "circlerandom" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
