package com.jim.stone.pai;

/*
 * ��ʵ����,�̳�BasePai,������һЩ����
 */
public class MatchPai extends BasePai {
    private int id;
    private int realLife;
    private int step;

    MatchPai(int l, int a, int p, String n) {
	life = l;
	attack = a;
	price = p;
	name = n;
    }

    public void setStep(int step) {
	this.step = step;
    }

    public int getStep() {
	return step;
    }

    MatchPai(BasePai b, int rl) {
	life = b.life;
	attack = b.attack;
	price = b.price;
	name = b.name;
	realLife = rl;
    }

    public void setId(int id) {
	this.id = id;
    }

    public void setRealLife(int realLife) {
	this.realLife = realLife;
    }

    @Override
    public String toString() {
	// TODO Auto-generated method stub
	return "life:" + life + " attack:" + attack + " price:" + price + " name:" + name + " realLife:" + realLife;
    }
}
