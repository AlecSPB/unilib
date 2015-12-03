package com.seoulapp.unilib.network;

import android.os.AsyncTask;
import android.util.Log;

import com.seoulapp.unilib.interfaces.HttpServerSuccess;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SAMSUNG on 2015-10-29.
 */
public class ProcessHttpTask extends AsyncTask<Object, Void, Map> {
    private HttpServerSuccess mHttpServerSuccess;

    public void setHttpServerSuccess(HttpServerSuccess HttpServerSuccess) {
        this.mHttpServerSuccess = HttpServerSuccess;
    }

    @Override
    protected Map doInBackground(Object[] params) {
        String url = (String) params[0];
        String[] jsonName = (String[]) params[1];
        Map parserMap = getBookDate(url, jsonName);
        return parserMap;
    }

    @Override
    protected void onPostExecute(Map map) {
        super.onPostExecute(map);
        if(mHttpServerSuccess != null) mHttpServerSuccess.onSuccess(map);
    }

    private Map getBookDate(String url, String[] jsonName) {
//        String url = "http://133.130.106.19:5000/books/56274186b53e735851b1a90f";
        DefaultHttpClient client = new DefaultHttpClient();
        Map parserMap = null;
        try {
            HttpGet get = new HttpGet(url);
            HttpParams param = client.getParams();
            HttpConnectionParams.setConnectionTimeout(param, 5000);
            HttpConnectionParams.setSoTimeout(param, 5000);
            HttpResponse response = client.execute(get);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String result = "";
            String line = null;
            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
            parserMap = jsonParserList(result, jsonName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
            return parserMap;
        }
    }

    public Map jsonParserList(String pRecvServerPage, String[] jsonName) {
        Map parseredData =  new HashMap<>();
        try {
            Log.i("TAG", pRecvServerPage);
            JSONObject json = new JSONObject(pRecvServerPage);
//            JSONObject json2 = json.getJSONObject("owner");
//            String[] jsonName = {"phnNo","addrOpt", "addr3", "addr2", "userId", "_id", "modYmdt", "regYmdt", "books"};
            for(String item : jsonName) {
                if(json.has(item)) {
                    parseredData.put(item, json.getString(item));
                    Log.i("TAG", item + " : " + parseredData.get(item));
                } else {
                    parseredData.put(item, "");
                }
            }
           /* for(int i=0; i<jsonName.length; i++) {
                if(json.has(jsonName[i])) {
                    parseredData.put(jsonName[i], json.getString(jsonName[i]));
                    Log.i("TAG", jsonName[i] + " : " + parseredData.get(jsonName[i]));
                } else {
                    parseredData.put(jsonName[i], "");
                }
            }*/
        } catch (JSONException e) {
            Log.i("TAG", "데이터 입력 실패!!");
            e.printStackTrace();
        } finally {
            return parseredData;
        }
    }
}
