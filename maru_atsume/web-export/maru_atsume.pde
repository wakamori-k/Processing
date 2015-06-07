Circle[] circle;
int onMouse_num = 0;
final int circle_num = 100;
final color colors[] = {color(0xff, 0x7f, 0x7f), color(0xff, 0x7f, 0xbf), color(0xff, 0x7f, 0xff), color(0xbf, 0x7f, 0xff), color(0x7f, 0x7f, 0xff), color(0x7f, 0xbf, 0xff), color(0x7f, 0xff, 0xff), color(0x7f, 0xff, 0xbf), color(0x7f, 0xff, 0x7f), color(0xbf, 0xff, 0x7f), color(0xff, 0xff, 0x7f), color(0xff, 0xbf, 0x7f)};

void setup(){
	//size(500,500);
        size(innerWidth,innerHeight);
	smooth();
	noStroke();
	colorMode(RGB,256);


	circle = new Circle[circle_num];

	for(int i=0;i<circle_num;i++)
		circle[i] = new Circle(int(random(0,500)), int(random(0,500)), int(random(10,100)), colors[ int(random(0, colors.length ))  ]);

	frameRate(1000);
}

void draw(){
        size(innerWidth,innerHeight);
	background(255,255,255);
	for(int i=0;i<circle_num;i++)
		circle[i].update();
	
}

class Circle{
	float x,y;
	int across;
	color c;
	boolean onMouse = false;

	Circle(float y,float x,int across,color c){
		this.y = y;
		this.x = x;
		this.across = across;
		this.c = c;
	}

	void update(){
		if(onMouse_num < circle_num){
			if(abs(y - mouseY) > across/2.0 || abs(x - mouseX) > across/2.0){
				float xtmp,ytmp;
				xtmp = x;
				ytmp = y;
				x = random(x,x+1);
				y = random(y,y+1);

				if(x+across/2.0 > width || x+across/2.0 < 0){
					x = 0;
					y = random(0,500);
				}
				if(y+across/2.0 > height || y+across/2.0 < 0){
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

