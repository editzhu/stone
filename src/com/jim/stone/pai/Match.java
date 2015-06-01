package com.jim.stone.pai;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class Match {
    private List<BasePai> pk1 = new ArrayList<BasePai>();// 己方牌库
    private List<BasePai> pk2 = new ArrayList<BasePai>();// 对方牌库
    private List<BasePai> sp1 = new ArrayList<BasePai>();// 己方手牌
    private List<BasePai> sp2 = new ArrayList<BasePai>();// 对方手牌
    private List<MatchPai> cp1 = new ArrayList<MatchPai>();// 己方场牌
    private List<MatchPai> cp2 = new ArrayList<MatchPai>();// 对方场牌

    private int f1;// 己方费
    private int f2;// 对方费
    private int l1;// 己方血
    private int l2;// 对方血
    public boolean isRound = true;// 是否是己方这轮.true=1方轮,false=2方轮

    public String session1 = "";
    public String session2 = "";
    public int is1 = 0;// 是否1号,true=1号,false=2号

    public void Matchini() {
	PaiKu paiKu = new PaiKu();
	pk1 = paiKu.getPaiList1();
	pk2 = paiKu.getPaiList2();
	cp1.clear();
	cp2.clear();
	sp1.clear();
	sp2.clear();
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

    public boolean getIsRound() {
	return isRound;
    }

    public void PaiKuSort() {
	// Collections.shuffle(paiList);
    }

    public String getAll(Match m) {

	String jsonString = JSON.toJSONString(m);
	System.out.println("match:" + jsonString);
	return jsonString;
    }

    public void zhuapai(String session) {
	if (!checkRound(session))
	    return;
	if (session.equals(session1)) {
	    System.out.println("you are 1");
	    zhuapai_ex(pk1, sp1);
	}
	if (session.equals(session2)) {
	    System.out.println("you are 2");
	    zhuapai_ex(pk2, sp2);

	}
    }

    private void zhuapai_ex(List<BasePai> p, List<BasePai> s) {
	if (p.size() > 0) {
	    int i = (int) (Math.random() * p.size());
	    BasePai basePai = p.get(i);
	    p.remove(i);
	    s.add(basePai);
	}
    }

    public void dachu(String session, int index) {
	if (!checkRound(session))
	    return;
	if (session.equals(session1)) {
	    System.out.println("you are 1");
	    dachu_ex(sp1, cp1, index);
	}
	if (session.equals(session2)) {
	    System.out.println("you are 2");
	    dachu_ex(sp2, cp2, index);

	}

    }

    private void dachu_ex(List<BasePai> s, List<MatchPai> c, int index) {
	if (s.size() <= index)
	    return;
	if (c.size() >= D.MAXCP)
	    return;
	BasePai basePai = s.get(index);
	s.remove(index);
	List<MatchPai> c1 = c;
	MatchPai matchPai = new MatchPai(basePai, basePai.life);
	c1.clear();
	c1.add(matchPai);
	c1.addAll(c);
	c = c1;
    }

    public void attack(String session) {
	if (!checkRound(session))
	    return;
	if (session.equals(session1)) {
	    attack_ex(cp1, l2);
	}
	if (session.equals(session2)) {
	    System.out.println("you are 2");
	    attack_ex(cp2, l1);

	}
    }

    public void attack_ex(List<MatchPai> c, int life) {

	if (c.size() <= 0)
	    return;
	l2 = l2 - c.get(0).attack;

    }

    public void stop() {
	isRound = !isRound;
    }

    public boolean checkRound(String session) {
	if (session1.equals(session))
	    is1 = 1;
	else if (session2.equals(session))
	    is1 = 2;
	else
	    is1 = 0;
	if (session1.equals(session) && isRound)
	    return true;
	if (session2.equals(session) && (!isRound))
	    return true;
	System.out.println("不是你的轮");
	return false;
    }

    public static void main(String[] args) {
	Match match = new Match();
	match.Matchini();
	match.getAll(match);
	match.attack_ex(match.cp1, 30);
	List<String> list = new ArrayList<String>();
	List<String> l = new ArrayList<String>();
	list.add("1");
	list.add("2");
	System.out.println(list);
	l.add("3");
	l.addAll(list);
	System.out.println(l);
	list = l;
	System.out.println(list);
    }
}
