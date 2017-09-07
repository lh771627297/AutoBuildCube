package com.liuhuan.buildcube;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.nio.charset.Charset;


public class AutoBuildCube {
    public static void main(String[] args) {
        String url = "http://172.26.5.200:7070/kylin/api/cubes/cube_svc_session_20170903/build";
        String jsons = "{\"startTime\":\"1504310400000\", \"endTime\":\"1504396800000\", \"buildType\":\"BUILD\"}";
        String username = "ADMIN";
        String password = "KYLIN";
        System.out.printf(doPutJson(username,password,url,jsons));
    }


    public static String doPutJson(String username, String pwd, String url,String json) {
        String response = null;
        HttpPut httpPut = new HttpPut(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            StringEntity stringEntity = new StringEntity(json);
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");

            String auth = username + ":" + pwd;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);

            httpPut.setHeader("Authorization",authHeader);
            httpPut.setEntity(stringEntity);
            HttpResponse httpResponse = httpClient.execute(httpPut);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                response = EntityUtils.toString(httpResponse.getEntity());
            } else {
                throw new RuntimeException();
            }
            httpPut.releaseConnection();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return response;
    }


}
