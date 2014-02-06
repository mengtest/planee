package com.example.domain;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.face.DisplayView;
import com.example.planee.GameView;
import com.example.util.MoreRect;

public class Player implements DisplayView{
	/*��Ч������*/
	private Audio audio;
	/*��������*/
	private float x = 200f;
	private float y = 500f;
	/*��������*/
	private float touchX = 0;
	private float touchY = 0;
	/*���״̬*/
	private boolean live = true;
	/*���� Ĭ��ӦΪ��100*/
	private int hp = 90;
	/*���*/
	private int money;
	/*�����ܾ���*/
	private int distance;
	/*��Ϸ�÷�*/
	private int score;
	/*GameView����*/
	private GameView gameView;
	/*����ͼƬ*/
	private Bitmap playerBitmap;
	/*HP*/
	private int playerHpX = 5;
	private int playerHpY = 14;
	private int playerHpW;
	private int playerHp1_2W;
	private int playerHpX_1_2W;
	private Bitmap playerHp;
	private Bitmap playerHpLine;
	private Bitmap playerHpLineBack;
	/*�޵�״̬��ʱ��*/
	private int count = 0;
	/*�޵�״̬ʱ��*/
	private int time = 0;
	/*�޵�״̬��ʶ*/
	private boolean invincible = false;
	/*��ը*/
	private Pang pang;
	/*�ӵ�*/
	private List<Bullet> bullets = new ArrayList<Bullet>();
	/*�������*/
	private int bullet_count = 0;
	/*�����û���*/
	private Paint paint = new Paint();
	/*
	 * �������ײ�����*/
	private MoreRect moreRect;
	
	public Player(GameView gameView){
		this.gameView = gameView;
		/*�õ���Ч������*/
		audio = gameView.getAudio();
		/*������Ϸ��Դ*/
		playerBitmap = gameView.getAssets().playerBitmap;
		playerHp = gameView.getAssets().playerHp;
		playerHpW = playerHp.getWidth();
		playerHp1_2W = playerHpW/2;
		playerHpX_1_2W = playerHpX + playerHp1_2W;
		//Ѫ����Դ
		playerHpLine = gameView.getAssets().playerHpLine;
		playerHpLineBack = gameView.getAssets().playerHpLineBack;
		/*��ը��ʼ��*/
		pang = new Pang(gameView, x, y, 1f);
		//��ʼ��rect
		/*
		 * (48,0)*(79,80)
		 * (0,15)*(126,52)
		 */
		moreRect = new MoreRect(
			(int)x, (int)y, 
			new Rect(48,0,79,80),
			new Rect(0,15,126,52)
		);
		/*�����û��ʳ�ʼ��*/
		paint.setColor(Color.WHITE);
		paint.setTextSize(15);
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		//�����ӵ�
		for(int i=0; i<bullets.size(); i++){
			bullets.get(i).draw(canvas);
		}
		//����
		if(live){
			if(invincible && count%5 == 0){
			}
			else{
				canvas.drawBitmap(playerBitmap, x, y, null);
			}
		}
		else{
			pang.draw(canvas);
		}
		//Ѫ������
		canvas.drawBitmap(playerHpLineBack, playerHpX_1_2W, playerHpY, null);
		//��ʵѪ��
		if(hp!=0){
			canvas.save();
			canvas.clipRect(
					playerHpX_1_2W, 
					playerHpY, 
					playerHpX_1_2W+playerHpLine.getWidth(), 
					playerHpY+playerHpLine.getWidth());
			canvas.drawBitmap(playerHpLine, playerHpX_1_2W - (100-hp), playerHpY, null);
			canvas.restore();
		}
		//HP����
		canvas.drawBitmap(playerHp, playerHpX, playerHpY, null);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(live)
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN :
			break;
		case MotionEvent.ACTION_MOVE :
			x = event.getX(0) - playerBitmap.getWidth()/2;
			y = event.getY(0) - playerBitmap.getHeight();
			//����
			x = x / GameView.SCALE_X;
			y = y / GameView.SCALE_Y;
			break;
		case MotionEvent.ACTION_UP :
			break;
		}
	}
	
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		//����ֵС��0ʱ����
		if(hp <= 0){
			live = false;
			pang.logic();
		}
		//����Rect
		moreRect.set((int)x, (int)y);
		/////// �޵��߼�  ///////////
		if(invincible && count>time){
			invincible = false;
			count = 0;
			time = 0;
		}
		count++;
		//�ӵ��߼�
		for(int i=0; i<bullets.size(); i++){
			bullets.get(i).logic();
			if(!bullets.get(i).isLive()){
				bullets.remove(i);
			}
		}
		//����
		this.fire();
	}
	
	public MoreRect getMoreRect() {
		return moreRect;
	}
	/*����*/
	public void fire(){
		if(bullet_count%2 == 0 && live)
			bullets.add(new Bullet(gameView, (int)x+playerBitmap.getWidth()/2-33/2, (int)y, true));
		//�������
		bullet_count++;
	}
	/*��������ֵ*/
	public void setHp(int hp){
		this.hp = hp;
	}
	/*�õ�����ֵ*/
	public int getHp(){
		return hp;
	}
	/*����*/
	public void minusHp(int hp){
		if(!invincible){
			this.hp = this.hp - hp;
			if(this.hp<=0){
				pang.setLocation(x, y);
				live = false;
			}
		}
	}
	/*����*/
	public void plusHp(int hp){
		this.hp = this.hp + hp;
	}
	/*�Ƿ���*/
	public boolean isLive() {
		return live;
	}
	/*���ô��״̬*/
	public void setLive(boolean live) {
		this.live = live;
	}
	/*���á������޵�״̬*/
	public void setInvincible(int time){
		this.time = time;
		invincible = true;
	}
	/*�õ��޵�״̬��ʶ*/
	public boolean isInvincible() {
		return invincible;
	}
	/*�õ������ȥ���ӵ�*/
	public List<Bullet> getBullets(){
		return bullets;
	}
}
