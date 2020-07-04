package com.aye10032.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadUtil {

    private String rootPath;

    public LoadUtil(String appDirectory){
        this.rootPath = appDirectory;
    }

    public boolean isToday(long party){
        boolean flag = true;

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        String date = ft.format(dNow);

        File timeFile = new File(rootPath + "/" + party + "/time.dat");
        if (!timeFile.exists()){
            timeFile.getParentFile().mkdirs();
            try {
                timeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileInputStream fip = new FileInputStream(timeFile);
            InputStreamReader reader = new InputStreamReader(fip, StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            while (reader.ready()) {
                sb.append((char) reader.read());
            }
            if(!sb.toString().equals(date)){
                flag = false;
            }
            reader.close();
            fip.close();

            if (!flag){
                FileOutputStream fop = new FileOutputStream(timeFile);
                OutputStreamWriter writer = new OutputStreamWriter(fop, StandardCharsets.UTF_8);
                writer.append(date);
                writer.close();
                fop.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }

}
