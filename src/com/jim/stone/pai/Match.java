package com.jim.stone.pai;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class Match {

    public Match() {
	player = new Player[2];
	player[0] = new Player();
	player[1] = new Player();
    }

    Player[] player;
    public boolean isRound = true;// 是否是己方这轮.true=1方轮,false=2方轮

    public String session1 = "";
    public String session2 = "";
    public int is1 = 0;// 是否1号,1=1号,2=2号
    public int stage = 0;// 阶段

    public String reponse(String tag, String sessionId, int fromPai, int toPai) {
	if (session1.equals(sessionId))
	    is1 = 1;
	else if (session2.equals(sessionId))
	    is1 = 2;
	else
	    is1 = 0;
	if (stage == 0) {
	    System.out.println(session1 + session2);
	    System.out.println("stage1:" + stage);
	    if (session1.length() > 0 && session2.length() > 0) {
		System.out.println("stage2:" + stage);
		// stage 1 开始之前的准备

		player[0].price = 10;
		player[1].price = 10;
		zhuapai(0);
		zhuapai(0);
		zhuapai(0);
		zhuapai(1);
		zhuapai(1);
		zhuapai(1);
		zhuapai(1);
		dachu(0, 0);
		dachu(0, 0);
		dachu(1, 0);
		dachu(1, 0);
		System.out.println("hehe");
		stage = 1;
	    }
	} else if (stage > 0) {
	    int playerId = -1;
	    if (sessionId == session1) {
		playerId = 0;
	    } else if (sessionId == session2) {
		playerId = 1;
	    }
	    if (playerId < 0) {
		return "{\"error\":\"session错误\"}";
	    }
	    if (!checkRound(sessionId, tag))
		return "{\"error\":\"不是你的轮\"}";
	    if (tag.equals("zhuapai")) {
		zhuapai(playerId);
	    } else if (tag.equals("dachu")) {
		dachu(playerId, fromPai);
	    } else if (tag.equals("ini")) {
		matchini();
	    } else if (tag.equals("stop")) {
		stop(playerId);
	    } else if (tag.equals("attack")) {
		attack(playerId, fromPai, toPai);
	    }
	    return getAll(this);
	}
	return "{\"error\":\"未捕获stage\"}";
    }

    public void matchini() {
	PaiKu paiku = new PaiKu();
	player[0].pk = paiku.getPaiList1();
	player[1].pk = paiku.getPaiList2();
	player[0].cp.clear();
	player[1].cp.clear();
	player[0].sp.clear();
	player[1].sp.clear();
	player[0].price = 0;
	player[1].price = 0;
	player[0].life = 30;
	player[1].life = 30;
	stage = 0;
    }

    public List<MatchPai> getCp1() {
	return player[0].cp;
    }

    public List<MatchPai> getCp2() {
	return player[1].cp;
    }

    public int getF1() {
	return player[0].price;
    }

    public int getF2() {
	return player[1].price;
    }

    public int getL1() {
	return player[0].life;
    }

    public int getL2() {
	return player[1].life;
    }

    public int getPk1Size() {
	return player[0].pk.size();
    }

    public int getPk2Size() {
	return player[1].pk.size();
    }

    public List<BasePai> getSp1() {
	return player[0].sp;
    }

    public List<BasePai> getSp2() {
	return player[1].sp;
    }

    public boolean getIsRound() {
	return isRound;
    }

    public void PaiKuSort() {
	// Collections.shuffle(paiList);
    }

    public String getAll(Match m) {

	String jsonString = JSON.toJSONString(m);
	return jsonString;
    }

    public void zhuapai(int playId) {
	if (player[playId].pk.size() <= 0 || player[playId].sp.size() >= 10) {
	    return;
	}
	int i = (int) (Math.random() * player[playId].pk.size());
	BasePai basePai = player[playId].pk.get(i);
	player[playId].pk.remove(i);
	player[playId].sp.add(basePai);
    }

    public void dachu(int playId, int index) {
	// 验证
	if (player[playId].sp.size() <= index)
	    return;
	if (player[playId].cp.size() >= D.MAXCP)
	    return;
	// 减费
	if (player[playId].price - player[playId].sp.get(index).price < 0)
	    return;

	player[playId].price -= player[playId].sp.get(index).price;
	BasePai basePai = player[playId].sp.get(index);
	player[playId].sp.remove(index);
	MatchPai matchPai = new MatchPai(basePai, basePai.life);
	// 加步数
	matchPai.setStep(0);// 刚dachu,setp=0
	player[playId].cp.add(index, matchPai);
    }

    // attacker 0-15,byattacker 0-15
    public void attack(int playerId, int attacker, int byAttacker) {
	int att;
	int byAtt;
	if (playerId == 0) {
	    att = 0;
	    byAtt = 1;
	} else {
	    att = 1;
	    byAtt = 0;
	}
	System.out.println(playerId + ":" + att + ":" + byAtt + ":" + attacker + ":" + byAttacker);
	// 检测步
	if (player[att].cp.get(attacker).getStep() <= 0)
	    return;
	if (byAttacker == 7) {// 7就是本体
	    // 攻击
	    player[byAtt].life -= player[att].cp.get(attacker).attack;
	    // 反弹伤害,7号不反弹

	    // 减步
	    player[att].cp.get(attacker).setStep(0);
	} else {
	    if (player[att].cp.size() <= attacker || player[byAtt].cp.size() <= byAttacker)
		return;
	    // 攻击
	    player[byAtt].cp.get(byAttacker).life -= player[att].cp.get(attacker).attack;
	    // 反弹伤害
	    player[att].cp.get(attacker).life -= player[byAtt].cp.get(byAttacker).attack;
	    // 减步
	    player[att].cp.get(attacker).setStep(0);
	}

	// 检测Life
	for (int i = 0; i < 2; i++) {
	    if (player[i].life <= 0) {
		gameover();
	    }
	}
	for (int i = 0; i < 2; i++) {
	    int[] tmp = { 0, 0, 0, 0, 0, 0, 0 };
	    for (int j = 0; j < player[i].cp.size(); j++) {
		if (player[i].cp.get(j).life <= 0) {
		    tmp[j] = 1;
		}
	    }
	    for (int j = 0; j < player[i].cp.size(); j++) {
		if (tmp[j] == 1) {
		    System.out.println("delete:" + j);
		    player[i].cp.remove(j);
		}
	    }
	}

    }

    public void stop(int playerId) {
	isRound = !isRound;
	if (playerId == 1) {
	    stage += 1;
	    // 每回合加费
	    player[0].price = stage + 10;
	    player[1].price = stage + 10;
	}
	// 每回合加step
	for (int j = 0; j < player[playerId].cp.size(); j++) {
	    player[playerId].cp.get(j).setStep(1);
	}
    }

    public boolean checkRound(String session, String tag) {
	if (tag.equals("flash"))
	    return true;

	if (session1.equals(session) && isRound)
	    return true;
	if (session2.equals(session) && (!isRound))
	    return true;
	System.out.println("不是你的轮");
	return false;
    }

    public void gameover() {
	System.out.println("gameover");
    }

    public static void main(String[] args) {
	Match match = new Match();
	System.out.println(match.player[0]);
    }
}
