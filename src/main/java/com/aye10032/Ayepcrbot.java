package com.aye10032;

import com.aye10032.util.CQMsg;
import com.aye10032.util.LoadUtil;
import com.aye10032.util.OCRUtil;
import com.aye10032.util.PersonUtil;
import org.meowy.cqp.jcq.entity.CoolQ;
import org.meowy.cqp.jcq.entity.ICQVer;
import org.meowy.cqp.jcq.entity.IMsg;
import org.meowy.cqp.jcq.entity.IRequest;
import org.meowy.cqp.jcq.event.JcqAppAbstract;

public class Ayepcrbot extends JcqAppAbstract implements ICQVer, IMsg, IRequest {

    PersonUtil personUtil;
    LoadUtil loadUtil;

    public Ayepcrbot() {

    }

    public Ayepcrbot(CoolQ CQ) {
        super(CQ);
    }

    @Override
    public String appInfo() {
        String AppID = "com.aye10032.ayepcrbot";
        return CQAPIVER + "," + AppID;
    }

    @Override
    public int startup() {
        appDirectory = CQ.getAppDirectory();
        System.out.println(appDirectory);
        loadUtil = new LoadUtil(appDirectory);
        personUtil = new PersonUtil(appDirectory, CC);

        return 0;
    }

    @Override
    public int exit() {
        return 0;
    }

    @Override
    public int enable() {
        return 0;
    }

    @Override
    public int disable() {
        return 0;
    }

    @Override
    public int privateMsg(int i, int i1, long l, String s, int i2) {
        return 0;
    }

    @Override
    public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg, int font) {
        if (msg.startsWith("报刀")) {
            CQ.sendGroupMsg(fromGroup, personUtil.addKnife(fromQQ, fromGroup, msg));
        }else if (msg.equals("创建公会")) {
            CQ.sendGroupMsg(fromGroup, personUtil.initParty(fromGroup));
        }else if (msg.startsWith("注册角色")) {
            CQ.sendGroupMsg(fromGroup, personUtil.initPerson(fromGroup,fromQQ,msg));
        }
        return 0;
    }

    @Override
    public int discussMsg(int i, int i1, long l, long l1, String s, int i2) {
        return 0;
    }

    @Override
    public int groupUpload(int i, int i1, long l, long l1, String s) {
        return 0;
    }

    @Override
    public int groupAdmin(int i, int i1, long l, long l1) {
        return 0;
    }

    @Override
    public int groupMemberDecrease(int i, int i1, long l, long l1, long l2) {
        return 0;
    }

    @Override
    public int groupMemberIncrease(int i, int i1, long l, long l1, long l2) {
        return 0;
    }

    @Override
    public int friendAdd(int i, int i1, long l) {
        return 0;
    }

    @Override
    public int requestAddFriend(int i, int i1, long l, String s, String s1) {
        return 0;
    }

    @Override
    public int requestAddGroup(int i, int i1, long l, long l1, String s, String s1) {
        return 0;
    }

    public int test(CQMsg cqMsg) {
        if (cqMsg.isGroupMsg()) {
            return groupMsg(cqMsg.subType, cqMsg.msgId, cqMsg.fromGroup, cqMsg.fromClient, "", cqMsg.msg, cqMsg.font);
        } else {
            return privateMsg(cqMsg.subType, cqMsg.msgId, cqMsg.fromClient, cqMsg.msg, cqMsg.font);
        }
    }
}
