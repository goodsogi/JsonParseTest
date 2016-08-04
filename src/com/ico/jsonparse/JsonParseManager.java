package com.ico.jsonparse;

import android.os.Handler;
import android.util.Log;

public class JsonParseManager {
	
	private static String jsonUrl1 = "http://172.30.1.16:8888/jsonParse/one.json";
	private static String jsonUrl2 = "http://172.30.1.16:8888/jsonParse/two.json";
	private static String jsonUrl3 = "http://172.30.1.16:8888/jsonParse/three.json";
	
	private static JsonParseManager _instance;
	
	static final int _JSON_ONE_DATA = 0;
	static final int _JSON_TWO_DATA = 1;
	static final int _JSON_THREE_DATA = 2;
	
	
	private ICallBackFunction _callBackFunction;
	private Thread jsonThread = null;
	private Handler jsonHandler = null;
	
	private String _resultData = "";
	private String _jsonUrl = "";
	
	private int _dataValue;
	
	
	/**
	 * JsonParseManager를 사용하기 위한 초기화. 최초 한번만 실행
	 */
	public static void _init(){
		_instance = new JsonParseManager();
	}
	
	/**
	 * one.json파일을 연동하기 위한 함수
	 * @param callBackFunction : callback을 받을 객체
	 */
	public static void _call_json_one(ICallBackFunction callBackFunction){
		if(_instance != null){
			_instance.callJsonData(callBackFunction, jsonUrl1, _JSON_ONE_DATA);
		}
		
	}
	
	/**
	 * two.json파일을 연동하기 위한 함수
	 * @param callBackFunction : callback을 받을 객체
	 */
	public static void _call_json_two(ICallBackFunction callBackFunction){
		if(_instance != null){
			_instance.callJsonData(callBackFunction, jsonUrl2, _JSON_TWO_DATA);
		}
	}
	
	/**
	 * three.json파일을 연동하기 위한 함수
	 * @param callBackFunction : callback을 받을 객체
	 */
	public static void _call_json_three(ICallBackFunction callBackFunction){
		if(_instance != null){
			_instance.callJsonData(callBackFunction, jsonUrl3, _JSON_THREE_DATA);
		}
		
	}
	
	private void callJsonData(ICallBackFunction callBackFunction, String jsonUrl, int dataValue){
		jsonThread = null;
		_resultData = "";
		
		_callBackFunction = callBackFunction;
		_jsonUrl = jsonUrl;
		_dataValue = dataValue;
		
		jsonHandler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				switch(_dataValue){
				case _JSON_ONE_DATA :
					_callBackFunction.onJsonOneData(_resultData);
					break;
				
				case _JSON_TWO_DATA :
					_callBackFunction.onJsonTwoData(_resultData);
					break;
					
				case _JSON_THREE_DATA :
					_callBackFunction.onJsonThreeData(_resultData);
					break;
			}
			};
		};
		
		
		jsonThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					String urlHeader = _jsonUrl.substring(0, 5);
					if(urlHeader.equals("https")){
						_resultData = HttpsContent.getStringContent(_jsonUrl);
					}else if(urlHeader.equals("http:")){
						_resultData = HttpContent.getStringContent(_jsonUrl);
					}
					
					jsonHandler.sendEmptyMessage(0);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.d("", "callJsonData - Exception :: " + e.toString());
				}
				
			}
		});
		jsonThread.start();
	}
	
	/**
	 * 콜백 인터페이스
	 * @author ICO
	 *
	 */
	public interface ICallBackFunction{
		public void onJsonOneData(String resultData);
		public void onJsonTwoData(String resultData);
		public void onJsonThreeData(String resultData);
	}
}
