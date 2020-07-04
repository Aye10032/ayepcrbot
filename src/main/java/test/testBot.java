package test;

import com.aye10032.Ayepcrbot;
import com.aye10032.util.CQMsg;

import java.util.Scanner;

public class testBot {

    public static void main(String[] args) {
        Ayepcrbot ayepcrbot = new Ayepcrbot();
        ayepcrbot.startup();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入测试内容:");
        while (true) {
            String s = sc.nextLine();
            ayepcrbot.test(CQMsg.getTempMsg(s));
        }
    }

}
