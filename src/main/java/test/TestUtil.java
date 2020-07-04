package test;

import com.aye10032.util.LoadUtil;

public class TestUtil {

    public static void main(String[] args) {
        String appPath = "D:\\program\\Github\\ayepcrbot\\";

        LoadUtil loadUtil = new LoadUtil(appPath);

        loadUtil.setNickName(237348L,2375985957L,"Aye10032");

        System.out.println(loadUtil.isToday(237348L));
        System.out.println(loadUtil.getPersonTime(237348L,2375985957L));
        System.out.println(loadUtil.getNickName(237348L,2375985957L));
    }

}
