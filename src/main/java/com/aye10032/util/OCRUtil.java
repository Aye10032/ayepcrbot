package com.aye10032.util;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

public class OCRUtil {
    public static final String APP_ID = "16759010";
    public static final String API_KEY = "0sjWF0HCYOCmyzUBmMPV5lhx";
    public static final String SECRET_KEY = "aWIG2y1YyUFKAHwmyaMeo77EAgtzsFDN";
    AipOcr client;

    public static void main(String[] args) {
        OCRUtil ocrUtil = new OCRUtil("D:\\program\\GitHub\\ayepcrbot\\");
        System.out.println(ocrUtil.doOCR("D:\\program\\GitHub\\ayepcrbot\\1.png"));
    }


    public OCRUtil(String rootPath){
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    }

    public String doOCR(String image){
        JSONObject res = client.basicGeneral(image, new HashMap<String, String>());

        return res.getJSONArray("words_result").toString();
    }


}
