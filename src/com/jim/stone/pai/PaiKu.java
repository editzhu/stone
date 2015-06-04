package com.jim.stone.pai;

import java.util.ArrayList;
import java.util.List;

/*
 * ≈∆ø‚
 */
public class PaiKu {
    private List<BasePai> paiList1 = new ArrayList<BasePai>();
    private List<BasePai> paiList2 = new ArrayList<BasePai>();

    // ≥ı ºªØ
    public PaiKu() {
	// TODO Auto-generated constructor stub
	int life = 0;
	int attack = 0;
	int price = 0;
	String name = "";
	for (int i = 0; i < D.DB.length; i++) {
	    life = Integer.valueOf(D.DB[i][0]);
	    attack = Integer.valueOf(D.DB[i][1]);
	    price = Integer.valueOf(D.DB[i][2]);
	    name = D.DB[i][3];
	    paiList1.add(new BasePai(life, attack, price, name));
	    paiList2.add(new BasePai(life, attack, price, name));
	    paiList1.add(new BasePai(life, attack, price, name));
	    paiList2.add(new BasePai(life, attack, price, name));

	}

    }

    public List<BasePai> getPaiList1() {
	return paiList1;
    }

    public List<BasePai> getPaiList2() {
	return paiList2;
    }

    public static void main(String[] args) {
    }
}
