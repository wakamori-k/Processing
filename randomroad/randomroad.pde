class Car{
	int wSize=10;
	int hSize=10;
	Car(int w,int h){
		wSize=w;
		hSize=h;
	}

	void update(float x,float y){
		stroke(0,0,0);
		rect(x,y,wSize,hSize);
	}
}

final int INTERVAL_MIN=50;
final int INTERVAL_MIN_LAWN=10;
final int CURB_SIZE=5;

int[][] road=new int[int(300/CURB_SIZE)][300];/*[y][x]*/

Car car;

void setup(){
	size(300,300);
	frameRate(100);
	colorMode(RGB,256);

	for(int i=0;i<height/CURB_SIZE;i++){
		for(int j=0;j<width;j++){
			road[i][j]=0;
		}
	}
	road[0][int(width/5)*2]=1;
	road[0][int(width/5)*3]=1;

	car=new Car(20,30);
}

void draw(){
	rect(0, 0, width*2, height*2);
	for(int i=height/CURB_SIZE-1;i>0;i--){/*1行下にずらす*/
		for(int j=0;j<width;j++){
			road[i][j]=road[i-1][j];
		}
	}

	do{/*一番上の行の新しい道*/
		int xtemp,ytemp;
		xtemp=getRightX(0);
		ytemp=getLeftX(0);

		int r=int(random(0,3));
		road[0][xtemp]=0;
		if(r==0 && xtemp>INTERVAL_MIN_LAWN){/*左にシフト*/
			road[0][xtemp-1]=1;
		}else if(r==1 && xtemp<width-INTERVAL_MIN_LAWN-1) {/*右にシフト*/
			road[0][xtemp+1]=1;
		}else{/*変化なし*/
			road[0][xtemp]=1;
		}

		r=int(random(0,3));
		road[0][ytemp]=0;
		if(r==0 && ytemp>INTERVAL_MIN_LAWN){/*左にシフト*/
			road[0][ytemp-1]=1;
		}else if(r==1 && ytemp<width-INTERVAL_MIN_LAWN-1) {/*右にシフト*/
			road[0][ytemp+1]=1;
		}else{/*変化なし*/
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
	}while(getInterval(0)<INTERVAL_MIN);/*道の幅が最小より小さかったらやり直し*/

	for(int i=0;i<height/CURB_SIZE;i++){/*道を書く*/

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

int getRightX(int y){
	for(int i=width-1;i>=0;i--){
		if(road[y][i]==1)
			return i;
	}
	return -1;
}

int getLeftX(int y){
	for(int i=0;i<width;i++){
		if(road[y][i]==1)
			return i;
	}
	return -1;
}

int getInterval(int y){
	int r,l;
	r=getRightX(y);
	l=getLeftX(y);
	if(r==-1 || l==-1)
		return -1;
	else 
		return r-l-1;
}