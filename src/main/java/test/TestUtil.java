package test;

import com.aye10032.util.LoadUtil;

public class TestUtil {

    public static void main(String[] args) {
        String appPath = "D:\\program\\Github\\ayepcrbot\\";

        LoadUtil loadUtil = new LoadUtil(appPath);

        System.out.println(loadUtil.isToday(237348L));
    }

}
