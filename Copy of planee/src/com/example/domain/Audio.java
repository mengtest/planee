package com.example.domain;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class Audio {
	//��ЧID
	public static int effcet_ui_ananniu;
	public static int effcet_sfx_xiaobaozha;
	public static int effcet_sfx_zhongbaozha;
	
	//��Դ������
	private AssetManager assetManager;
	//��Ч������
	private SoundPool soundPool;
	//ý�岥����
	private MediaPlayer mediaPlayer;
	//��������·��
	private String musicSrc;
	//��������ѭ����ʶ
	private boolean loop;
	
	public Audio(Context context){
		assetManager = context.getAssets();
		//���ֳ�ʼ��
		mediaPlayer = new MediaPlayer();
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		init();
	}
	
	/*
	 * ��ʼ��
	 */
	public void init(){
		effcet_ui_ananniu = loadSound("effcet_ui_ananniu.ogg");
		effcet_sfx_xiaobaozha = loadSound("effcet_sfx_xiaobaozha.ogg");
		effcet_sfx_zhongbaozha = loadSound("effcet_sfx_zhongbaozha.ogg");
	}
	/*
	 * ������Ч
	 */
	public void playSound(int id){
		soundPool.play(id, 1, 1, 0, 0, 1);
	}
	/*
	 * ������Ч
	 */
	private int loadSound(String src){
		try {
			return soundPool.load(assetManager.openFd(src), 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	/*
	 * ���ñ�������
	 */
	public void setMusic(String musicSrc){
		this.musicSrc = musicSrc;
		//ÿ����������ʱ����������mediaplayer
		mediaPlayer.reset();
		try {
			AssetFileDescriptor afd = assetManager.openFd(musicSrc);
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("������Դ����ʧ��");
		}
	}
	/*
	 * ��ʼ���ű�������
	 */
	public void start(){
		if(!mediaPlayer.isPlaying()){
			try {
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mediaPlayer.start();
		}
	}
	/*
	 * ֹͣ���ű�������
	 */
	public void stop(){
		if(mediaPlayer.isPlaying()){
			try {
				mediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mediaPlayer.stop();
		}
	}
	public void pause(){
		try {
			mediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mediaPlayer.pause();
	}
	/*
	 * ��������ѭ��ģʽ
	 */
	public void setLoop(boolean loop){
		mediaPlayer.setLooping(loop);
	}
	/*
	 * �õ���ǰ������ʵ��
	 */
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}
	/*
	 * �ͷ��ڴ�
	 **/
	public void release(){
		mediaPlayer.release();
//		soundPool.release();
	}
}
