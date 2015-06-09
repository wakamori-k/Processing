float ballx,bally;
float speedx,speedy;
float malletH,malletW;
float ballSize;


void setup(){
	size(800,500);
	colorMode(RGB,256);

	bally = height/2.0;
	ballx = width/2.0;
	speedx = 5;
	speedy = 5;

	ballSize = 40;
	malletW = 20;
	malletH = 50;
}

void draw(){
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