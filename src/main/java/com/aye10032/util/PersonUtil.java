package com.aye10032.util;

import org.meowy.cqp.jcq.entity.CQImage;
import org.meowy.cqp.jcq.message.CQCode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PersonUtil {

    CQCode cc;
    private String rootPath;
    private OCRUtil ocrUtil;
    private LoadUtil loadUtil;

    public PersonUtil(String RootPath, CQCode CC, LoadUtil loadUtil) {
        this.rootPath = RootPath;
        ocrUtil = new OCRUtil(rootPath);
        this.loadUtil = loadUtil;
        this.cc = CC;
    }

    public String initParty(long Group) {
        String returnMSG = "";
        File party = new File(rootPath + "/" + Group);

        if (party.exists()) {
            returnMSG = "公会已存在，若要重置公会信息可输入\"重置\"";
        } else {
            party.mkdirs();
            returnMSG = "公会创建成功！";
        }

        return returnMSG;
    }

    public String initPerson(long Group, long QQ, String MSG) {
        String returnMSG = "";
        File party = new File(rootPath + "/" + Group);

        if (party.exists()) {
            File person = new File(rootPath + "/" + Group + "/" + QQ);
            if (person.exists()) {
                returnMSG = "玩家信息已存在，若要重置个人信息可输入\"重新注册 [游戏ID]\"";
            } else {
                String[] msgs = MSG.split(" ");
                if (msgs.length != 2) {
                    returnMSG = "格式不正确！请回复\"注册角色 [游戏ID]\"绑定游戏角色";
                } else {
                    loadUtil.setNickName(Group, QQ, msgs[1]);
                    returnMSG = "玩家信息已注册，开始挂树吧";
                }
            }
        } else {
            returnMSG = "当前群不存在公会，请回复\"创建公会\"以创建公会";
        }

        return returnMSG;
    }

    public String addKnife(long QQ, long Group, String MSG) {

        String returnMSG = "";
        File party = new File(rootPath + "/" + Group);

        if (party.exists()) {
            File person = new File(rootPath + "/" + Group + "/" + QQ);
            if (!person.exists()) {
                returnMSG = "初次使用BOT，请回复\"注册角色 [游戏ID]\"绑定游戏角色";
            } else {
                try {
                    List<CQImage> imageList = cc.getCQImages(MSG);
                    if (imageList.size() == 0 && MSG.split(" ").length != 2) {
                        returnMSG = "格式不正确！请输入\"报刀 [数值]\"或\"报刀+结算界面截图\"";
                    } else if (imageList.size() == 0 && MSG.split(" ").length == 2) {
                        try {
                            int damage = Integer.parseInt(MSG.split(" ")[1]);
                            if (!loadUtil.isToday(Group)) {
                                loadUtil.setPersonTimes(Group, QQ, 1);
                            } else {
                                int times = loadUtil.getPersonTime(Group, QQ);
                                if (times >= 3) {
                                    returnMSG = "今天已经3刀了，请明天再来吧";
                                } else {
                                    loadUtil.addDamage(Group, QQ, damage);
                                    returnMSG = cc.at(QQ) + "(" + loadUtil.getNickName(Group, QQ) + ")" + "已上报伤害" + damage
                                            + ",今日已出" + times + "刀";
                                }
                            }
                        } catch (NumberFormatException e) {
                            returnMSG = "格式不正确！请输入\"报刀 [数值]\"或\"报刀+结算界面截图\"";
                        }
                    } else {
                        cc.getCQImage(MSG).download(person.getPath(), "1.png");
                        returnMSG = ocrUtil.doOCR(person.getPath() + "/1.png");
                    }
                } catch (IOException e) {
                    returnMSG = e.toString();
                }
            }
        } else {
            returnMSG = "当前群不存在公会，请回复\"创建公会\"以创建公会";
        }

        return returnMSG;
    }

    public String addKnife(long QQ, long Group, List<CQImage> images) {

        String returnMSG = "";
        File party = new File(rootPath + "/" + Group);

        if (party.exists()) {
            File person = new File(rootPath + "/" + Group + "/" + QQ);
            if (!person.exists()) {
                returnMSG = "初次使用BOT，请回复\"注册角色 [游戏ID]\"绑定游戏角色";
            } else {
                try {
                    images.get(0).download(person.getPath(), "1.png");
                    returnMSG = ocrUtil.doOCR(person.getPath() + "/1.png");
                } catch (IOException e) {
                    returnMSG = e.toString();
                }
            }
        } else {
            returnMSG = "当前群不存在公会，请回复\"创建公会\"以创建公会";
        }

        return returnMSG;
    }

}
