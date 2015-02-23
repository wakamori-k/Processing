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

public class randomroad extends PApplet {

class Car{
	int wSize=10;
	int hSize=10;
	Car(int w,int h){
		wSize=w;
		hSize=h;
	}

	public void update(float x,float y){
		stroke(0,0,0);
		rect(x,y,wSize,hSize);
	}
}

final int INTERVAL_MIN=50;
final int INTERVAL_MIN_LAWN=10;
final int CURB_SIZE=5;

int[][] road=new int[PApplet.parseInt(300/CURB_SIZE)][300];/*[y][x]*/

Car car;

public void setup(){
	size(300,300);
	frameRate(100);
	colorMode(RGB,256);

	for(int i=0;i<height/CURB_SIZE;i++){
		for(int j=0;j<width;j++){
			road[i][j]=0;
		}
	}
	road[0][PApplet.parseInt(width/5)*2]=1;
	road[0][PApplet.parseInt(width/5)*3]=1;

	car=new Car(20,30);
}

public void draw(){
	rect(0, 0, width*2, height*2);
	for(int i=height/CURB_SIZE-1;i>0;i--){/*1\u884c\u4e0b\u306b\u305a\u3089\u3059*/
		for(int j=0;j<width;j++){
			road[i][j]=road[i-1][j];
		}
	}

	do{/*\u4e00\u756a\u4e0a\u306e\u884c\u306e\u65b0\u3057\u3044\u9053*/
		int xtemp,ytemp;
		xtemp=getRightX(0);
		ytemp=getLeftX(0);

		int r=PApplet.parseInt(random(0,3));
		road[0][xtemp]=0;
		if(r==0 && xtemp>INTERVAL_MIN_LAWN){/*\u5de6\u306b\u30b7\u30d5\u30c8*/
			road[0][xtemp-1]=1;
		}else if(r==1 && xtemp<width-INTERVAL_MIN_LAWN-1) {/*\u53f3\u306b\u30b7\u30d5\u30c8*/
			road[0][xtemp+1]=1;
		}else{/*\u5909\u5316\u306a\u3057*/
			road[0][xtemp]=1;
		}

		r=PApplet.parseInt(random(0,3));
		road[0][ytemp]=0;
		if(r==0 && ytemp>INTERVAL_MIN_LAWN){/*\u5de6\u306b\u30b7\u30d5\u30c8*/
			road[0][ytemp-1]=1;
		}else if(r==1 && ytemp<width-INTERVAL_MIN_LAWN-1) {/*\u53f3\u306b\u30b7\u30d5\u30c8*/
			road[0][ytemp+1]=1;
		}else{/*\u5909\u5316\u306a\u3057*/
			road[0][ytemp]=1;
		}

		if(getInterval(0)<INTERVAL_MIN){
			for(int i=0;i<width;i++){
				if(i==xtemp || i==ytemp){
					road[0][i]=1;
				}else{
					road[0][i]=0;
				}
			}
		}
	}while(getInterval(0)<INTERVAL_MIN);/*\u9053\u306e\u5e45\u304c\u6700\u5c0f\u3088\u308a\u5c0f\u3055\u304b\u3063\u305f\u3089\u3084\u308a\u76f4\u3057*/

	for(int i=0;i<height/CURB_SIZE;i++){/*\u9053\u3092\u66f8\u304f*/

		for(int j=0;j<getLeftX(i);j++){
			strokeWeight(5);
			stroke(46,139,87);
			rect(j,i*CURB_SIZE,CURB_SIZE,CURB_SIZE);
		}

		strokeWeight(5);
		if(i%2==0)
			stroke(255,0,0);
		else
			stroke(255,255,255);
		rect(getLeftX(i),i*CURB_SIZE,CURB_SIZE,CURB_SIZE);

		for(int j=getLeftX(i)+CURB_SIZE;j<getRightX(i);j++){
			strokeWeight(5);
			stroke(148, 148, 148);
			rect(j,i*CURB_SIZE,CURB_SIZE,CURB_SIZE);
		}

		strokeWeight(5);
		if(i%2==0)
			stroke(255,0,0);
		else
			stroke(255,255,255);
		rect(getRightX(i),i*CURB_SIZE,CURB_SIZE,CURB_SIZE);

		for(int j=getRightX(i)+CURB_SIZE;j<width;j++){
			strokeWeight(5);		
			stroke(46,139,87);
			rect(j,i*CURB_SIZE,CURB_SIZE,CURB_SIZE);
		}
	}

	float center=(getRightX(20)+getLeftX(20))/2;
	car.update(center-10,150);

}

public int getRightX(int y){
	for(int i=width-1;i>=0;i--){
		if(road[y][i]==1)
			return i;
	}
	return -1;
}

public int getLeftX(int y){
	for(int i=0;i<width;i++){
		if(road[y][i]==1)
			return i;
	}
	return -1;
}

public int getInterval(int y){
	int r,l;
	r=getRightX(y);
	l=getLeftX(y);
	if(r==-1 || l==-1)
		return -1;
	else 
		return r-l-1;
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "randomroad" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
