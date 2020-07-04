package com.aye10032.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadUtil {

    private String rootPath;

    public LoadUtil(String appDirectory) {
        this.rootPath = appDirectory;
    }

    public boolean isToday(long party) {
        boolean flag = true;

        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String date = ft.format(dNow);

        File timeFile = new File(rootPath + "/" + party + "/time.dat");
        if (!timeFile.exists()) {
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
            if (!sb.toString().equals(date)) {
                flag = false;
            }
            reader.close();
            fip.close();

            if (!flag) {
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

    public int getPersonTime(long party, long person) {
        int times = 0;

        File timeFile = new File(rootPath + "/" + party + "/" + person + "/times.dat");
        if (!timeFile.exists()) {
            timeFile.getParentFile().mkdirs();
            try {
                timeFile.createNewFile();
                setPersonTimes(party, person, 0);
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
            times = Integer.parseInt(sb.toString());
            reader.close();
            fip.close();


            FileOutputStream fop = new FileOutputStream(timeFile);
            OutputStreamWriter writer = new OutputStreamWriter(fop, StandardCharsets.UTF_8);
            writer.append("0");
            writer.close();
            fop.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return times;
    }

    public void setPersonTimes(long party, long person, int times) {

        File timeFile = new File(rootPath + "/" + party + "/" + person + "/times.dat");

        boolean exists = false;

        if (!timeFile.exists()) {
            timeFile.getParentFile().mkdirs();
            try {
                timeFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            exists = true;
        }

        try {
            FileOutputStream fop = new FileOutputStream(timeFile);
            OutputStreamWriter writer = new OutputStreamWriter(fop, StandardCharsets.UTF_8);
            if (!exists) {
                writer.append("0");
            } else {
                writer.append(String.valueOf(times));
            }
            writer.close();
            fop.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getNickName(long party, long person){

        String nickName = "";
        File timeFile = new File(rootPath + "/" + party + "/" + person + "/nickname.dat");

        if (timeFile.exists()) {
            try {
                FileInputStream fip = new FileInputStream(timeFile);
                InputStreamReader reader = new InputStreamReader(fip, StandardCharsets.UTF_8);
                StringBuilder sb = new StringBuilder();
                while (reader.ready()) {
                    sb.append((char) reader.read());
                }
                nickName = sb.toString();
                reader.close();
                fip.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return nickName;
    }

    public void setNickName(long party, long person, String name){
        File timeFile = new File(rootPath + "/" + party + "/" + person + "/nickname.dat");

        if (!timeFile.getParentFile().exists()) {
            timeFile.getParentFile().mkdirs();
        }

        try {
            FileOutputStream fop = new FileOutputStream(timeFile);
            OutputStreamWriter writer = new OutputStreamWriter(fop, StandardCharsets.UTF_8);
            writer.append(name);
            writer.close();
            fop.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDamage(long party, long person, int damage){

    }

}
