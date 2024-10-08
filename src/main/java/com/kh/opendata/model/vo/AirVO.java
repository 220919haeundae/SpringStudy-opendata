package com.kh.opendata.model.vo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import lombok.Data;

@Data
public class AirVO {
	// 측정소 이름
	private String stationName;
	// 측정 일시
	private String dataTime;
	// 통합대기환경 수치
	private String khaiValue;
	// 미세먼지 농도
	private String pm10Value;
	// 아황산가스 농도
	private String so2Value;
	// 일산화탄소 농도
	private String coValue;
	// 이산화질소 농도
	private String no2Value;
	// 오존 농도
	private String o3Value;
	
}
