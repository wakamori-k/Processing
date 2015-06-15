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
MyPairFloat[] grids = new MyPairFloat[NUM];//座標

void setup(){
	size(800,500);
	colorMode(RGB, 256);
	//frameRate(10);

	for(int i=0;i<NUM;i++){
		grids[i] = new MyPairFloat(random(0, width),random(0, height));
	}
}

void draw(){
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
				if(int(random(0, 30)) == 0){// 1/10の確立で線をひく
					stroke(0, 127, 255);//線の色
					line(grids[i].first, grids[i].second, grids[j].first, grids[j].second);
				}
			}
		}
	}
}
