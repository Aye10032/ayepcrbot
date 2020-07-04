package com.aye10032.util;

import org.meowy.cqp.jcq.entity.CQImage;
import org.meowy.cqp.jcq.message.CQCode;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PersonUtil {

    private String rootPath;
    private OCRUtil ocrUtil;
    CQCode cc;

    public PersonUtil(String RootPath, CQCode CC) {
        this.rootPath = RootPath;
        ocrUtil = new OCRUtil(rootPath);
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
            }else {
                String[] msgs = MSG.split(" ");
                if (msgs.length != 2){
                    returnMSG = "格式不正确！请回复\"注册角色 [游戏ID]\"绑定游戏角色";
                }else {
                    person.mkdirs();
                    File name = new File(person.getPath(),msgs[1]);
                    try {
                        name.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
                    if (imageList.size() == 0 && MSG.split(" ").length != 2){
                        returnMSG = "格式不正确！请输入\"报刀 [数值]\"或\"报刀+结算界面截图\"";
                    }else if (imageList.size() == 0 && MSG.split(" ").length == 2){
                        
                    }else {
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

}
