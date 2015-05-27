package com.jim.stone.pai;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class Match {
    private List<BasePai> pk1 = new ArrayList<BasePai>();// �����ƿ�
    private List<BasePai> pk2 = new ArrayList<BasePai>();// �Է��ƿ�
    private List<BasePai> sp1 = new ArrayList<BasePai>();// ��������
    private List<BasePai> sp2 = new ArrayList<BasePai>();// �Է�����
    private List<MatchPai> cp1 = new ArrayList<MatchPai>();// ��������
    private List<MatchPai> cp2 = new ArrayList<MatchPai>();// �Է�����

    private int f1;// ������
    private int f2;// �Է���
    private int l1;// ����Ѫ
    private int l2;// �Է�Ѫ
    public boolean isMyRound = true;// �Ƿ��Ǽ�������.true=������,false=�Է���

    public String session1 = "";
    public String session2 = "";

    public void Matchini() {
	PaiKu paiKu = new PaiKu();
	pk1 = paiKu.getPaiList1();
	pk2 = paiKu.getPaiList2();
	cp1.clear();
	cp2.clear();
	sp1.clear();
	sp1.clear();
	f1 = 1;
	f2 = 1;
	l1 = 30;
	l2 = 30;

    }

    public List<MatchPai> getCp1() {
	return cp1;
    }

    public List<MatchPai> getCp2() {
	return cp2;
    }

    public int getF1() {
	return f1;
    }

    public int getF2() {
	return f2;
    }

    public int getL1() {
	return l1;
    }

    public int getL2() {
	return l2;
    }

    public int getPk1Size() {
	return pk1.size();
    }

    public int getPk2Size() {
	return pk2.size();
    }

    public List<BasePai> getSp1() {
	return sp1;
    }

    public List<BasePai> getSp2() {
	return sp2;
    }

    public boolean getIsMyRound() {
	return isMyRound;
    }

    public void PaiKuSort() {
	// Collections.shuffle(paiList);
    }

    public String getAll(Match m) {

	String jsonString = JSON.toJSONString(m);
	System.out.println("match:" + jsonString);
	return jsonString;
    }

    public void zhuapai() {
	System.out.println("isMyRound:" + isMyRound);
	// ������ƿ��е��Ʒ�������
	if (pk1.size() > 0) {
	    int i = (int) (Math.random() * pk1.size());
	    BasePai basePai = pk1.get(i);
	    pk1.remove(i);
	    sp1.add(basePai);
	}
	// ��������
	isMyRound = !isMyRound;
    }

    public void dachu() {
	BasePai basePai = sp1.get(0);
	sp1.remove(0);
	MatchPai matchPai = new MatchPai(basePai, 2);
	cp1.add(matchPai);
    }

    public static void main(String[] args) {
	Match match = new Match();
	match.Matchini();
	match.zhuapai();
	match.getAll(match);
    }
}
