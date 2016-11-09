import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import weather.JsonUtils;
import weather.Result;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by lixiwei on 2016/11/9.
 */
public class WeatherMsgSender
{
    public static String requestWeather(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey",  "8f0d8c85736672941de5d67c35e87ca4");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void sendMessage(String[] res,String phone) throws ApiException
    {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23526586", "1a87cf3c7ebe9f5b2cd4a98b0399dc4d");
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setSmsType("normal");
        req.setSmsFreeSignName("瑶宝宝天气推送");
        req.setSmsParamString("{" +
                "day:'" + res[0] + "'," +
                "night:'" + res[1] + "'," +
                "high:'" + res[2] + "'," +
                "low:'" + res[3] + "'," +
                "precip:'" + res[4] + "'," +
                "wind_direction:'" + res[5] + "'," +
                "wind_scale:'" + res[6] + "'}"
        );
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_25630447");
        AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
        System.out.println("推送结果:" + rsp.getBody());
    }
    public static void main(String[] args) throws ApiException, InterruptedException
    {
        String httpUrl = "http://apis.baidu.com/thinkpage/weather_api/suggestion";
        String httpArg = "location=beijing&language=zh-Hans&unit=c&start=0&days=1";
        String resultJson;
        while (true)
        {
            System.out.println("当前时间:" + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            if (new SimpleDateFormat("HH:mm").format(new Date()).indexOf("06:") != -1)
            {
                resultJson = requestWeather(httpUrl, httpArg);
                while (resultJson.length() < 100)
                {
                    System.out.println("获取天气Json失败" + resultJson);
                    resultJson = requestWeather(httpUrl, httpArg);
                    Thread.sleep(1000 * 60);
                }
                System.out.println(resultJson);
                Result res = JsonUtils.getObject(resultJson, Result.class);
                sendMessage(res.getRes(),"13522781970");
                sendMessage(res.getRes(),"15810034686");
                Thread.sleep(22 * 60 * 60 *1000 ); // 休息22小时
            }
            Thread.sleep( 5 * 60 * 1000 ); // 空闲的2小时每5分钟检查一次是否到时间
        }
    }
}
