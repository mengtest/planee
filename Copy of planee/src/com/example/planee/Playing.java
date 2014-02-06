package com.example.planee;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.example.domain.Assets;
import com.example.domain.Audio;
import com.example.domain.Backgound;
import com.example.domain.Enemy;
import com.example.domain.Player;
import com.example.face.DisplayView;

public class Playing implements DisplayView{
	private GameView gameView;
	private Assets assets;
	/*��Ϸ����*/
	private Backgound backgound;
	/*��ǰ��Ϸ������Դͼ*/
	private Bitmap gameBackgound;
	/*����*/
	private Player player;
	/*�л�*/
	private List<Enemy> enemys = new ArrayList<Enemy>();
	/*��ʱ��*/
	private int count = 0;
	
	public Playing(GameView gameView){
		this.gameView = gameView;
		assets = gameView.getAssets();
		gameBackgound = assets.gameBackgound;
		backgound = new Backgound(gameBackgound);
		player = new Player(gameView);
		//��ӵл�
		enemys.add(new Enemy(gameView, 100, -280, Enemy.Type.EASY));
		enemys.add(new Enemy(gameView, 200, -280, Enemy.Type.PLAIN));
		enemys.add(new Enemy(gameView, 300, -280, Enemy.Type.HARD));
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		backgound.draw(canvas);
		//�����л�
		for(int i=0; i<enemys.size(); i++){
			enemys.get(i).draw(canvas);
		}
		//�������
		player.draw(canvas);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		backgound.onTouchEvent(event);
		player.onTouchEvent(event);
	}
	@Override
	public void logic() {
		// TODO Auto-generated method stub
		//��������������ô������Ϸ
		if (!player.isLive()) {
			count++;
			if (count > 80){
				//����
				gameView.setGameState(GameView.GAME_GAMEOVER);
			}
		}
		
		backgound.logic();
		player.logic();
		
		for(int i=0; i<enemys.size(); i++){
			//�л��߼�
			enemys.get(i).logic();
			//�����л���ײ			
			if(player.isLive() && !player.isInvincible() && enemys.get(i).getMoreRect().isCollision(player.getMoreRect())){
				Log.e("GameCollision", "--->true");
				player.minusHp(100);
				player.setInvincible(60);
				enemys.get(i).minusHp(100);
			}
			//�л����ӵ���ײ
			if(enemys.get(i).isHit(player.getBullets())){
				enemys.get(i).minusHp(10);
			}
			//�л�����ɾ���л�
			if(!enemys.get(i).isLive() && enemys.get(i).isPangOk()){
				enemys.remove(i);
			}
		}
		//�����µĵл�
		if(enemys.size() <= 1){
			enemys.add(new Enemy(gameView, 100, -280, Enemy.Type.EASY));
			enemys.add(new Enemy(gameView, 200, -280, Enemy.Type.PLAIN));
			enemys.add(new Enemy(gameView, 300, -280, Enemy.Type.HARD));
		}
	}
	
}
