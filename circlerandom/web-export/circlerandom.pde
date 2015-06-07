int WIDTH=1000;
int HEIGHT=1000;
int count=0;

void setup(){
   size(innerWidth,innerHeight);
   colorMode(HSB,100);
   background(99);
   frameRate(10);
}

void draw(){
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

void fade(){
   fill(99,30);
   rectMode(CORNER);
   rect(0, 0, WIDTH*2, HEIGHT*2);
} 

