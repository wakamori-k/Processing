Circle[] ball;
final int ball_num = 50;
final color colors[] = {color(0xff, 0x7f, 0x7f), color(0xff, 0x7f, 0xbf), color(0xff, 0x7f, 0xff), color(0xbf, 0x7f, 0xff), color(0x7f, 0x7f, 0xff), color(0x7f, 0xbf, 0xff), color(0x7f, 0xff, 0xff), color(0x7f, 0xff, 0xbf), color(0x7f, 0xff, 0x7f), color(0xbf, 0xff, 0x7f), color(0xff, 0xff, 0x7f), color(0xff, 0xbf, 0x7f)};


void setup(){
	size(800,500);
	smooth();
	noStroke();
	colorMode(RGB,256);


	ball = new Circle[ball_num];

	for(int i=0;i<ball_num;i++){
		ball[i] = new Circle(i,int(random(0,500)), int(random(0,500)), colors[ int(random(0, colors.length ))  ]);
	}
	//frameRate(10);
}

void draw(){
	background(255,255,255);
	for(int i=0;i<ball_num;i++){
		ball[i].update();
	}
	
}

class Circle{
	private float x,y;				//座標
	private float across;			//円の直径
	private PVector speed; 			//速度ベクトル
	private color c;				//色
	private int id;

	Circle(int id,float y,float x,color c){
		this.y = y;
		this.x = x;
		this.across = 20.0;
		this.c = c;
		speed = new PVector(0,5);
		this.id = id;
	}

	public void update(){
		y += speed.y;
		x += speed.x;

		if(y+across/2.0 > height){
			speed.y *= -1;
			y = height - across/2.0;

		}else if(y-across/2.0 < 0){
			speed.y *= -1;
			y = across/2.0;
		}
		if(x+across/2.0 > width){
			speed.x *= -1;
			x = width - across/2.0;
		}else if( x-across/2.0 < 0){
			speed.x *= -1;
			x = across/2.0;
		}

		for(int i=0;i<ball_num;i++){
			float dist = sqrt(sq(abs(x-ball[i].getX())) + sq(abs(y-ball[i].getY())));
			float min_dist = across/2.0 + ball[i].getAcross()/2.0;
			if(i != id && dist < min_dist){
				PVector pv = new PVector(x-ball[i].getX(), y-ball[i].getY());
				float rad = PVector.angleBetween(speed,pv);
				speed.rotate(rad);
				changeColor();
			}
		}

		fill(c);
		ellipse(x, y, this.across, across);
	}

	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y;
	}

	public float getAcross(){
		return this.across;
	}

	public PVector getSpeedVector(){
		return this.speed;
	}

	private void changeColor(){
		this.c = colors[ int(random(0, colors.length ))  ];
	}
}

Circle[] ball;
final int ball_num = 50;
final color colors[] = {color(0xff, 0x7f, 0x7f), color(0xff, 0x7f, 0xbf), color(0xff, 0x7f, 0xff), color(0xbf, 0x7f, 0xff), color(0x7f, 0x7f, 0xff), color(0x7f, 0xbf, 0xff), color(0x7f, 0xff, 0xff), color(0x7f, 0xff, 0xbf), color(0x7f, 0xff, 0x7f), color(0xbf, 0xff, 0x7f), color(0xff, 0xff, 0x7f), color(0xff, 0xbf, 0x7f)};


void setup(){
	size(800,500);
	smooth();
	noStroke();
	colorMode(RGB,256);


	ball = new Circle[ball_num];

	for(int i=0;i<ball_num;i++){
		ball[i] = new Circle(i,int(random(0,500)), int(random(0,500)), colors[ int(random(0, colors.length ))  ]);
	}
	//frameRate(10);
}

void draw(){
	background(255,255,255);
	for(int i=0;i<ball_num;i++){
		ball[i].update();
	}
	
}

class Circle{
	private float x,y;				//座標
	private float across;			//円の直径
	private PVector speed; 			//速度ベクトル
	private color c;				//色
	private int id;

	Circle(int id,float y,float x,color c){
		this.y = y;
		this.x = x;
		this.across = 20.0;
		this.c = c;
		speed = new PVector(0,5);
		this.id = id;
	}

	public void update(){
		y += speed.y;
		x += speed.x;

		if(y+across/2.0 > height){
			speed.y *= -1;
			y = height - across/2.0;

		}else if(y-across/2.0 < 0){
			speed.y *= -1;
			y = across/2.0;
		}
		if(x+across/2.0 > width){
			speed.x *= -1;
			x = width - across/2.0;
		}else if( x-across/2.0 < 0){
			speed.x *= -1;
			x = across/2.0;
		}

		for(int i=0;i<ball_num;i++){
			float dist = sqrt(sq(abs(x-ball[i].getX())) + sq(abs(y-ball[i].getY())));
			float min_dist = across/2.0 + ball[i].getAcross()/2.0;
			if(i != id && dist < min_dist){
				PVector pv = new PVector(x-ball[i].getX(), y-ball[i].getY());
				float rad = PVector.angleBetween(speed,pv);
				speed.rotate(rad);
				changeColor();
			}
		}

		fill(c);
		ellipse(x, y, this.across, across);
	}

	public float getX(){
		return this.x;
	}

	public float getY(){
		return this.y;
	}

	public float getAcross(){
		return this.across;
	}

	public PVector getSpeedVector(){
		return this.speed;
	}

	private void changeColor(){
		this.c = colors[ int(random(0, colors.length ))  ];
	}
}


