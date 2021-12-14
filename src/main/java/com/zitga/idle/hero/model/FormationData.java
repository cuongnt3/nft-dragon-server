package com.zitga.idle.hero.model;

import com.zitga.idle.base.constant.BasicTag;
import com.zitga.idle.hero.constant.HeroTag;

import java.util.Map;

public class FormationData {

    private final int id;
    private final int frontLine;
    private final int backLine;

    public FormationData(Map<String, String> data) {
        id = Integer.parseInt(data.get(BasicTag.ID_TAG));

        frontLine = Integer.parseInt(data.get(HeroTag.FRONT_LINE_TAG));
        backLine = Integer.parseInt(data.get(HeroTag.BACK_LINE_TAG));
    }

    public int getId() {
        return id;
    }

    public int getFrontLine() {
        return frontLine;
    }

    public int getBackLine() {
        return backLine;
    }

    public int getPosition(int position){
        if (position < frontLine){
            return position;
        }

        return position - frontLine;
    }

    public boolean isFrontLine(int position){
        if (position < frontLine){
            return true;
        }

        return false;
    }
}