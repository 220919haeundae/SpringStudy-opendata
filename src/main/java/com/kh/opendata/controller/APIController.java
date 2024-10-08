package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kh.opendata.model.vo.AirVO;


/**
 * Servlet implementation class APIController
 */
@Controller
public class APIController {
	private String SERVICE_KEY = "BnQT8Nl4CUETJgL6o7JoFM5RHdE%2BkMJI3HMW5WNTclXs0wHa%2BuFBzpjr41fUYOblRgD7pSAyJDGObO0P8TZMtw%3D%3D";
	
	@ResponseBody
	@RequestMapping(value = "air.do", produces = "application/json; charset=utf-8;")
	public String airPollution(@RequestParam(value = "location", defaultValue = "서울") String location) throws IOException {
		//대기오염 정보를 조회(api 사용)
		String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty"
					+ "?serviceKey=" + SERVICE_KEY
					+ "&sidoName=" + URLEncoder.encode(location, "UTF-8")
					+ "&returnType=json"
					+ "&numOfRows=" + "200"
					+ "&pageNo=" + "1";
		
		URL reqUrl = new URL(url);
		HttpURLConnection urlConn = (HttpURLConnection)reqUrl.openConnection();
		urlConn.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
		
		String resText = "";
		String line;
		while((line = br.readLine()) != null) {
			resText += line;
		}
		br.close();
		urlConn.disconnect();
		
		System.out.println(resText);
		
			JSONObject jobject;
			try {
				jobject = (JSONObject)(new JSONParser().parse(resText));
				
				System.out.println(jobject);
				JSONObject response = (JSONObject)jobject.get("response");
				System.out.println(response);
				JSONObject body = (JSONObject)response.get("body");
				System.out.println(body);
				JSONArray items = (JSONArray)body.get("items");
				System.out.println(items);
				 // Gson 객체 생성
		        Gson gson = new Gson();

		        // JSON에서 Response 객체로 변환
		        Type listType = new TypeToken<ArrayList<AirVO>>() {}.getType();
		        
		        // JSONArray를 List<AirVO>로 변환
		        List<AirVO> list = gson.fromJson(items.toString(), listType);
		        System.out.println(gson.toJson(list));
		        resText = gson.toJson(list);
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
		return resText;
	}
}

class Response {
    private List<AirVO> items;

    public List<AirVO> getItems() {
        return items;
    }
}
