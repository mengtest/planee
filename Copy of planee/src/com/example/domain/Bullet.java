package com.example.domain;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.face.DisplayView;
import com.example.planee.GameView;
import com.example.util.MoreRect;

public class Bullet implements DisplayView{
	//�ӵ�����
	private int x;
	private int y;
	//�ӵ����״̬
	private boolean live = true;
	//�ӵ���Դ
	private Bitmap bullet;
	//��ǰ�ӵ�֡
	private int flash = 0;
	//Ĭ���ӵ��ٶ�
	private int speedX = 0;
	private int speedY = 52;
	//�ӵ��ĺû�
	private boolean good;
	//�ӵ������
	private MoreRect moreRect;
	//�ӵ��Ƿ���ɱ�ը
	private boolean okPang = false;
	//�ӵ���ըʵ��
	private Pang pang;
	
	public Bullet(GameView gameView, int x, int y, boolean good){
		this.x = x;
		this.y = y;
		this.good = good;
		bullet = gameView.getAssets().bullet;
		//��ʼ���ӵ����
		moreRect = new MoreRect(x, y, new Rect(9, 1, 23, 45));
		//ʵ������ը
		pang = new Pang(gameView, x, y, 0.3f);
	}
	public Bullet(GameView gameView, int x, int y, boolean good, int speedX, int speedY){
		this(gameView, x, y, good);
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(isLive())
			canvas.drawBitmap(bullet, x, y, null);
		else if(okPang == false){
			pang.draw(canvas);
		}
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void logic() {
		// TODO Auto-generated method stub
//		����ӵ�������Ļ
		if(y < -bullet.getHeight() || y > GameView.GAME_HEIGHT){
			live = false;
			okPang = true;
			return;
		}
		if(live){
			x = x - speedX;
			y = y - speedY;
			//�����������ײλ��
			moreRect.set(x, y);
			//���ñ�ըλ��
			pang.setLocation(x, y);
		}
		else if(!live && !okPang){
			pang.logic();
			if(pang.isPangOk())
				okPang = true;
		}
	}
	
	/*�õ����*/
	public MoreRect getMoreRect(){
		return moreRect;
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public boolean isOkPang() {
		return okPang;
	}
}
