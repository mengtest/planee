package com.example.util;

import android.graphics.Rect;
/*
 * ����������
 */
public class MoreRect {
	//���Rect
	private Rect rect[];
	//����Rect
	private Rect absRect[];
	private int x;
	private int y;
	
	public MoreRect(int x, int y, Rect...rect){
		//����rect����
		this.rect = new Rect[rect.length];
		absRect = new Rect[rect.length];
		//Ϊrect��ʼ��
		int left;
		int top;
		int right;
		int bottom;
		for(int i=0; i<rect.length; i++){
			left = x + rect[i].left;
			top = y + rect[i].top;
			right = x + rect[i].right;
			bottom = y + rect[i].bottom;
			
			this.rect[i] = rect[i];
			absRect[i] = new Rect(left, top, right, bottom);
		}
	}
	
	/*
	 * ��һ�����η�����ײ
	 */
	public boolean isCollision(Rect rect){
		for(int i=0; i<this.rect.length; i++){
			if(rect.intersect(absRect[i]))
				return true;
		}
		return false;
	}
	
	/*
	 * ��������η�����ײ
	 */
	public boolean isCollision(MoreRect moreRect){
		for(int i=0; i<absRect.length; i++){
			if(moreRect.isCollision(absRect[i]))
				return true;
		}
		return false;
	}
	
	/*
	 * ����Rectλ��
	 */
	public void set(int x, int y){
		int left;
		int top;
		int right;
		int bottom;
		for(int i=0; i<rect.length; i++){
			left = x + rect[i].left;
			top = y + rect[i].top;
			right = x + rect[i].right;
			bottom = y + rect[i].bottom;
			absRect[i].set(left, top, right, bottom);
		}
	}
	/*
	 * �õ���������
	 */
	public int length(){
		return rect.length;
	}
	/*
	 * �õ�����
	 */
	public Rect getAbsRect(int i){
		return absRect[i];
	}
	public Rect getRect(int i){
		return rect[i];
	}
}
