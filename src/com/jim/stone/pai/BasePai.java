package com.jim.stone.pai;

/*
 * �ƵĻ�����Ϣ
 */

public class BasePai {
    public int life;
    public int attack;
    public int price;
    public String name;

    BasePai() {

    }

    BasePai(int l, int a, int p, String n) {
	life = l;
	attack = a;
	price = p;
	name = n;
    }

    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return "life:" + life + " attack:" + attack + " price:" + price + " name:" + name;
    }
}
