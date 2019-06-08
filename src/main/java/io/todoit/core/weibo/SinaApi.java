package io.todoit.core.weibo;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

/**
 * 微博上传图片服务
 * @author:zhangd
 * @date:2019/6/7 18:05
 */
public class SinaApi {

    public String login(String user,String pass) {

        String url = "https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.15)&_=";
        String post = "entry=sso&gateway=1&from=null&savestate=30&useticket=0&pagerefer=&vsnf=1"
                + "&su=" + Base64.encode(user.getBytes()) + "&service=sso&sp=" + pass
                + "&sr=1024*768&encoding=UTF-8&cdult=3&domain=sina.com.cn&prelt=0&returntype=TEXT";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);

        //封装发送到服务提供者的参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("entry", "sso"));
        params.add(new BasicNameValuePair("gateway", "10001"));
        params.add(new BasicNameValuePair("from", "null"));
        params.add(new BasicNameValuePair("savestate", "0"));
        params.add(new BasicNameValuePair("pagerefer", ""));
        params.add(new BasicNameValuePair("vsnf", "1"));
        params.add(new BasicNameValuePair("su", Base64.encode(user.getBytes())));
        params.add(new BasicNameValuePair("service", "1"));
        params.add(new BasicNameValuePair("sp", pass));
        params.add(new BasicNameValuePair("sr", "1024*768"));
        params.add(new BasicNameValuePair("encoding", "UTF-8"));
        params.add(new BasicNameValuePair("cdult", "3"));
        params.add(new BasicNameValuePair("domain", "sina.com.cn"));
        params.add(new BasicNameValuePair("prelt", "0"));
        params.add(new BasicNameValuePair("returntype", "TEXT"));


        String ret = "";
        InputStream inputStream = null;
        try {
            method.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            //发送具体的http请求
            HttpResponse response = client.execute(method);

            //获得服务提供者响应的具体数据
            HttpEntity entity = response.getEntity();

            //读取返回内容
            inputStream = entity.getContent();


            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufr = new BufferedReader(isr);
            String str;

            while ((str = bufr.readLine()) != null) {
                ret += str;
            }
            Header[] headers = response.getHeaders("Set-Cookie");
            for (Header header : headers) {
                if(header.toString().indexOf("SUB=") > 0){
                    return header.getValue().split(";")[0];
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (IOException e) {

            }
        }
        return null;
    }

    public String uploadImg(String imgBase64,String ck) {


        String url = "http://picupload.weibo.com/interface/pic_upload.php?cb=https%3A%2F%2Fweibo.com%2Faj%2Fstatic%2Fupimgback.html%3F_wv%3D5%26callback%3DSTK_ijax_1551096206285100&mime=image%2Fjpeg&data=base64&url=weibo.com%2Fu%2F5734329255&markpos=1&logo=1&nick=&marks=0&app=miniblog&s=rdxt&pri=0&file_source=2";

        String ret = "";

        URL u = null;
        HttpURLConnection con = null;
        InputStream inputStream = null;
        //尝试发送请求
        try {

            u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Cookie", ck);


            PrintWriter printWriter = new PrintWriter(con.getOutputStream());
            // 发送请求参数
            printWriter.write("b64_data="+ URLEncoder.encode(imgBase64));
            // flush输出流的缓冲
            printWriter.flush();

            //读取返回内容
            inputStream = con.getInputStream();

            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufr = new BufferedReader(isr);
            String str;

            while ((str = bufr.readLine()) != null) {
                ret+=str;
            }

            String retCode = getSubString(con.getHeaderField("location")+"&","&ret=","&");
            if (retCode.equals("1")){
                ret = getSubString(con.getHeaderField("location")+"&","&pid=","&");

                if (!ret.equals("")){
                    ret = "http://wx1.sinaimg.cn/large/"+ret+".jpg";
                }
            }else {
                ret = null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 取两个文本之间的文本值
     *
     * @param text
     * @param left
     * @param right
     * @return
     */
    private  String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("["+new SinaApi().uploadImg("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","SUB=_2A25x_hwODeRhGeNP6VcS8izIzjiIHXVSigrGrDV_PUJbm9BeLRD3kW9NTlOY-yGrEYNGsHYwutHHvvX2S8Ez1X8R")+"]");
    }
}
