package com.jim.stone.pai;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public List<BasePai> pk = new ArrayList<BasePai>();// 己方牌库
    public List<BasePai> sp = new ArrayList<BasePai>();// 己方手牌
    public List<MatchPai> cp = new ArrayList<MatchPai>();// 己方场牌
    public int price;// 己方费
    public int life;// 己方血
    // public List<BasePai> pk = new ArrayList<BasePai>();// 对方牌库
    // public List<BasePai> sp = new ArrayList<BasePai>();// 对方手牌
    // public List<MatchPai> cp = new ArrayList<MatchPai>();// 对方场牌
    // public int price;// 对方费
    // public int life;// 对方血
}
