package com.example.restfulwebservice.builder.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;



public class ComUtil {

    //Timezone Setting
    public static JSONObject changeTimezoneObject(JSONObject obj, String columnNm, String timezoneCode, String dateFormat, boolean timeYN ) throws Exception {
        obj.put(columnNm, DateUtil.getLocalTime(StringUtil.isNullToString(obj.get(columnNm)), dateFormat, timezoneCode, timeYN));
        return obj;
    }
    
    
    //Timezone Setting
    public static JSONArray changeTimezoneArray(JSONArray list, String columnNm, String timezoneCode, String dateFormat, boolean timeYN ) throws Exception {
        JSONObject jo = new JSONObject();
        JSONArray resultArray = new JSONArray();
        
        for(int i=0; i<list.size(); i++){
            jo = new JSONObject();
            jo = (JSONObject)list.get(i);
            jo.put(columnNm, DateUtil.getLocalTime(StringUtil.isNullToString(jo.get(columnNm)), dateFormat, timezoneCode, timeYN));
            resultArray.add(jo);
        }
        
        return resultArray;
    }
        
    
    
    //로컬 아이피 가져오기
    public static String selectClientIpAddr(HttpServletRequest request) throws Exception {
        
        String ip = request.getHeader("X-FORWARDED-FOR");
        
        if(ip == null){
            ip = request.getRemoteAddr();
        }
        
        return ip;
    }
    
    
    //request array size 체크
    public static String[] RequiredSize(int len , String[] array) {
        
        String[] array2 = new String[len];
        if(array != null) {
            System.arraycopy(array, 0, array2, 0, array.length);
            for(int i=array.length; i<len; i++) {
                array2[i] = "";
            }
        } else {
            for(int i=0; i<len; i++) {
                array2[i] = "";
            }
        }
        return array2;
    }
    
    
    // Rest 호출하여 json 결과로 리턴
    public static JSONObject selectJson(String reqUrl, JSONObject requestParam, String restUrl) throws Exception {
        
        String url = restUrl + reqUrl;
        URL object = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        con.setRequestProperty("Accept", "application/json;charset=utf-8");
        con.setRequestMethod("POST");
        
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
        wr.write(requestParam.toString());
        wr.flush();

        JSONObject jsonObj = null;
        int httpRequest = con.getResponseCode();
        if (httpRequest == HttpURLConnection.HTTP_OK ) {
            InputStream in = new BufferedInputStream(con.getInputStream());
            String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            in.close();
            con.disconnect();

            JSONParser parser = new JSONParser();
            jsonObj = (JSONObject) parser.parse(result);
            
        }
        
        return jsonObj;
    }
    
    
    // Rest 호출하여 json 결과로 리턴
    public static String selectJsonToString(String reqUrl, JSONObject requestParam, String restUrl) throws Exception {
        
        String url = restUrl + reqUrl;
        URL object = new URL(url);
        
        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        con.setRequestProperty("Accept", "application/json;charset=utf-8");
        con.setRequestMethod("POST");
        
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
        wr.write(requestParam.toString());
        wr.flush();
        
        String result = "";
        int httpRequest = con.getResponseCode();
        if (httpRequest == HttpURLConnection.HTTP_OK ) {
            InputStream in = new BufferedInputStream(con.getInputStream());
            result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            in.close();
            con.disconnect();
        }
        
        return result;
    }
    
    
    // Rest 호출하여 json 결과로 리턴
    public static String selectJsonSSL(String reqUrl, JSONObject requestParam, String restUrl) throws Exception {
            
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    
            public java.security.cert.X509Certificate[] getAcceptedIssuers(){
                return null;
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                // TODO Auto-generated method stub                
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                // TODO Auto-generated method stub
            }
        }};
        
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        
        String url = restUrl + reqUrl;
           
        URL object = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) object.openConnection();
        con.setSSLSocketFactory(sc.getSocketFactory());
        
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        con.setRequestProperty("Accept", "application/json;charset=utf-8");
        con.setRequestMethod("POST");
        
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(),"UTF-8");
        wr.write(requestParam.toString());
        wr.flush();
        
        
        String result = "";
        int httpRequest = con.getResponseCode();
        if (httpRequest == HttpsURLConnection.HTTP_OK ) {
            InputStream in = new BufferedInputStream(con.getInputStream());
            result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            in.close();
            con.disconnect();
        }
        
        return result;
         
    }
    
    
    //바이트 글자수 자르기
    public static String getMaxByteString(String str, int maxLen) {
         StringBuilder sb = new StringBuilder();
         int curLen = 0;
         String curChar;
        
         for (int i = 0; i < str.length(); i++) {
              curChar = str.substring(i, i + 1);
              curLen += curChar.getBytes().length;
              if (curLen > maxLen) {
                   break;
              } else {
                   sb.append(curChar);
              }
         }
         return sb.toString();
    }
    
    
    //다국어 비교
    public static String selectApproverLanguageCode(String str) {
        String result = "";
        if(str != null) {
            if(str.equals("ko-KR")) {
                result = "ko_KR";
            } else if(str.equals("")) {
                result = "ko_KR";
            } else {
                result = "en_US";
            }
        } else {
            result = "ko_KR";
        }
        return result;
    }

    
    /**
     * 널인 문자열을 대체문자열로 치환한다 <BR>
     * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
     *
     * @param s 입력문자열
     * @param p 대체문자열로
     * @return 치환된 문자열
     */

    public static String null2str(String s, String p) {
        if (s == null || s.length() == 0) {
            return null2space(p);
        } else {
            return s.trim();
        }
    }
     
    
    public static int null2int(int s, int y) {
        Integer p = null2Integer(s);
        return p == null ? y : p.intValue();         
    }
     
     
    public static Integer null2Integer(int s) {
        if (s < 0) {
            return null;
        } else {
            return new Integer(s);
        }
    }
     
     
    /**
     * 널인 문자열을 스페이스("")로 치환한다 <BR>
     * 단, 좌우 공백이 있는 문자열은 trim 한다 <br>
     *
     * @param s 입력문자열
     * @return 치환된 문자열
     */
    public static String null2space(String s) {
        if (s == null || s.length() == 0) {
            return "";
        } else {
            return s.trim();
        }
    }
      
      
    /**
     * 패스워드 파람 리무브 테그 시
     * @param str
     * @return
     */
    public static String chkParam(String chkValue) throws Exception {
        if (chkValue == null || chkValue.length() == 0) return "";
        return chkValue;
    }
     
     
    /**
     * 특수문자 필터링
     * @param str
     * @return
     */
    public static String expFilter(String str) {
        String tmpNullStr = "";
        
        if (str != null && str.length() > 0) {
            str = str.replaceAll("eval\\((.*)\\)", " ");
            str = str.replaceAll("(?i)on[A-Za-z]*[\\s]*=[\\s]*[\\\"\\\'`][\\s]*.*[\\\"\\\'`]", " "); // on...="" 형태 제거
            str = str.replaceAll("(?i)on[A-Za-z]*[\\s]*=[\\s]*[\\\"\\\'`][\\s]*.*", " ");
            str = str.replaceAll("(?i)on[A-Za-z]*[\\s]*=", " ");
            str = str.replaceAll("[\\\"\\\'`][\\s]*javascript:(.*)[\\\"\\\'`]", " ");
            str = str.replaceAll("(?i)<script", "<dummy");
            str = str.replaceAll("(?i)</script", "</dummy");

            str = str.replaceAll("&amp;", "&");         
            str = str.replaceAll("&", "&amp;");
            str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
            str = str.replaceAll("\"", "&quot;");
            str = str.replaceAll("'", "&#39;");
            str = str.replaceAll("\\(","&#40;");           
            str = str.replaceAll("\\)","&#41;");      
            str = str.replaceAll("\\{","&#123;");           
            str = str.replaceAll("\\}","&#125;");

            return str;
        }
        
        return tmpNullStr;
    }

     
    /**
     * JS 용 필터링 (미변환 문자열이 js 에 표현될 때 사용)
     * @param str
     * @return
     */
    public static String jsFilter(String str) {
        return getRemoveNewLineText(expFilter(expReverseFilter(str)));
    }
     
     
    /**
     * 파라미터 넘어온 문자열을 역으로 치환함. (클라이언트만 사용) 
     * @param str
     * @return
     */
    public static String expReverseFilter(String str) {
        String tmpNullStr = "";
        
        if (str != null && str.length() > 0) {
            str = str.replaceAll("&amp;", "&");
            str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
            str = str.replaceAll("&quot;", "\"");
            //str = str.replaceAll("&#34;", "\"");
            str = str.replaceAll("&#39;", "'");
            str = str.replaceAll("&#58;", ":");
            str = str.replaceAll("&#59;", ";");
            str = str.replaceAll("&#40;", "(");
            str = str.replaceAll("&#41;", ")");
            str = str.replaceAll("&#63;", "?");
            str = str.replaceAll("&#123;", "{");
            str = str.replaceAll("&#125;", "}");
            str = str.replaceAll("&#33;", "!");
            str = str.replaceAll("&#42;", "*");
            str = str.replaceAll("&#64;", "@");
            //str = str.replaceAll("&#36;", "$");
            str = str.replaceAll("script", "dummyS");
            str = str.replaceAll("object", "dummyO");
            str = str.replaceAll("iframe", "dummyI");
            
            return str;
        }
         
        return tmpNullStr;
    } 
    
    
    public static String expBBSReverseFilter(String str) {
        String tmpNullStr = "";
        String str_low = "";
        
        if (str != null && str.length() > 0) {          
            str = str.replaceAll("&amp;", "&");
            str = str.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
            str = str.replaceAll("&quot;", "\"");
//            str = str.replaceAll("&#39;", "'");
            str = str.replaceAll("&#58;", ":");
            str = str.replaceAll("&#59;", ";");
            str = str.replaceAll("&#40;", "(");
            str = str.replaceAll("&#41;", ")");
            str = str.replaceAll("&#63;", "?");
            str = str.replaceAll("&#123;", "{");
            str = str.replaceAll("&#125;", "}");
            str = str.replaceAll("&#33;", "!");
            str = str.replaceAll("&#42;", "*");
            str = str.replaceAll("&#64;", "@");
            
            str_low = str.toLowerCase();
            if(str_low.contains("script")  || str_low.contains("object")   || str_low.contains("iframe")  || str_low.contains("QUOT") 
            || str_low.contains("onblur")  || str_low.contains("onchange") || str_low.contains("onclick") || str_low.contains("ondblclick")
            || str_low.contains("enerror") || str_low.contains("onfocus")  || str_low.contains("onload")  || str_low.contains("onmouse")
            || str_low.contains("onscroll")|| str_low.contains("onsubmit") || str_low.contains("onunload") )
            {
            	str = str_low;
                str = str.replaceAll("script", "dummyS");
                str = str.replaceAll("object", "dummyO");
                str = str.replaceAll("iframe", "dummyI");
                str = str.replaceAll("QUOT", "&quot;");
                str = str.replaceAll("onblur", "x-onblur");
				str = str.replaceAll("onchange", "x-onchange");
				str = str.replaceAll("onclick", "x-onclick");
				str = str.replaceAll("ondblclick", "x-ondblclick");
				str = str.replaceAll("enerror", "x-enerror");
				str = str.replaceAll("onfocus", "x-onfocus");
				str = str.replaceAll("onload", "x-onload");
				str = str.replaceAll("onmouse", "x-onmouse");
				str = str.replaceAll("onscroll", "x-onscroll");
				str = str.replaceAll("onsubmit", "x-onsubmit");
				str = str.replaceAll("onunload", "x-onunload");
            }
            
            return str;
        }
         
        return tmpNullStr;
    } 
     
     
    /**
     * 파라미터 넘어온 문자열을 역으로 치환함. (JSONString용) 
     * @param str
     * @return
     */
    public static String expReverseFilterForJson(String str) {
        String tmpNullStr = "";
        
        if (str != null && str.length() > 0) {          
            str = str.replaceAll("&quot;", "\"");   
            str = str.replaceAll("&#123;", "{");           
            str = str.replaceAll("&#125;", "}");           
            
            return str;
        }
         
        return tmpNullStr;
    } 
     
     
    /**
     * myBatis 에 ${} 로 사용될 문자열(필드명...)을 위한 filtering
     * @param str
     * @return
     */
    public static String expMyBatisInjection(String str) {
        String tmpNullStr = "";
         
        if (str != null && str.length() > 0) {
            str = str.replaceAll("'", "");  
            str = str.replaceAll("\"", "");
            str = str.replaceAll("\\\\", "");
            str = str.replaceAll("[)]", "");
            str = str.replaceAll(";", "");

            return str;
        }
         
        return tmpNullStr;
    }

     
    /**
     * URLEncoder.encode가 ' '을 '+' 으로 치환 하기 때문에 공백 이외의 문자만 encode 합니다.
     * 
     * @param str 문자
     * @param charSet 인코딩 문자셋
     * @return 한글 Encoding 문자열
     * @throws UnsupportedEncodingException
     */
    public static String urlEncode(String str, String charSet) throws UnsupportedEncodingException {
        StringBuffer buffer = null;
        StringTokenizer tokenizer = null;
        
        buffer = new StringBuffer();
        tokenizer = new StringTokenizer(str, " ");

        while(tokenizer.hasMoreTokens()){
             buffer.append(URLEncoder.encode(tokenizer.nextToken(), charSet) + " ");
        }
         
        return buffer.toString().trim();
    }
     
     
    /**
     * File size convert
     * Statements
     *
     * @param nSize
     * @return
     */
    public static String getByteSize(long nSize) {
        String byteSize = "" + nSize;
        byteSize += " Byte";

        if (nSize > 1024*1024*1024) {
            double fmtDouble = (double)nSize / (double)(1024*1024*1024);

            byteSize =  format(fmtDouble, "###,###,###.#");
            byteSize += " GB";
        } else if (nSize > 1024*1024) {
            double fmtDouble = (double)nSize / (double)(1024*1024);

            byteSize =  format(fmtDouble, "###.#");
            byteSize += " MB";
        } else if (nSize > 1024) {
            double fmtDouble = (double)nSize / (double)(1024);

            byteSize =  format(fmtDouble, "###.#");
            byteSize += " KB";
        }
        return byteSize;
    }
     
     
    public static String format( double cnt, String format ) {
        DecimalFormat fmt = new DecimalFormat( format );
        return fmt.format( cnt );
    }   
     
     
    public static Map Xml2Map(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = null; 
        Document document = null;
         
        String result = "";
        String fiid = "";
         
        try {
            builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new ByteArrayInputStream(xml.getBytes("UTF-8")));
            document = builder.parse(inputSource);
             
            NodeList resultNodeList = document.getElementsByTagName("RESULT");
            NodeList fiidNodeList = document.getElementsByTagName("FIID");
             
            if(resultNodeList != null && resultNodeList.getLength() > 0)
                if(resultNodeList.item(0).getChildNodes() != null && resultNodeList.item(0).getChildNodes().getLength() > 0)
                    result = resultNodeList.item(0).getChildNodes().item(0).getNodeValue();
            if(fiidNodeList != null && fiidNodeList.getLength() > 0)
                if(fiidNodeList.item(0).getChildNodes() != null && fiidNodeList.item(0).getChildNodes().getLength() > 0)
                    fiid = fiidNodeList.item(0).getChildNodes().item(0).getNodeValue();
             
        } catch(Exception e) {
            e.printStackTrace();
        }
         
        Map<String, String> map = new HashMap();
        map.put("RESULT", result);
        map.put("FIID", fiid);
        return (Map) map;
    }
     
     
    /**
     * 특정문자를 변환한다.
     * @param source source 문자열
     * @param pattern 바꿀 문자패턴
     * @param replace 적용할 문자패턴
     * @return 변환된 문자
     */
    public static String replace(String source, String pattern, String replace) {
        if (source != null) {
            final int len = pattern.length();
            StringBuffer sb = new StringBuffer();
            int found = -1;
            int start = 0;

            while((found = source.indexOf(pattern, start)) != -1) {
                sb.append(source.substring(start, found));
                sb.append(replace);
                start = found + len;
            }

            sb.append(source.substring(start));

            return sb.toString();
        }

        return "";
    }
     
     
    // 파라미터 모두 json 객체에 담기
    public static JSONObject paramList(HttpServletRequest request) throws Exception {
        JSONObject requestParam = new JSONObject();
         
        Enumeration params = request.getParameterNames();
         
        while(params.hasMoreElements()){
        	String name = (String)params.nextElement();
        	System.out.println("======> param name : " + name);
            String value = StringUtil.isNullToString(request.getParameter(name)); 

            requestParam.put(name, value);
        }          
        return requestParam;
    }
     
     
    //개행 문자열 변경
    public static String getRemoveNewLineText(String src){
        src = StringUtil.replace(src,"\r\n","\\n");
        src =  StringUtil.replace(src,"\n","\\n");
        return StringUtil.replace(src,"\r","\\n");
    }
     
     
    public static String removeLeadingZeroes(String value){
        String returnVal = "";
        try {
          returnVal = new Integer(value).toString();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            returnVal = "";
        }          
        return returnVal;
    }

     
    public static void setPropertyFromJsonString(Object obj, String jsonStr) {
        try {
            Map describe = BeanUtils.describe(obj);
            if (describe != null) {
                ObjectMapper mapper = new ObjectMapper();
                HashMap<String,String> sfJsonObj = mapper.readValue(jsonStr, HashMap.class);
                Iterator itr = sfJsonObj.keySet().iterator();
                while(itr.hasNext()) {
                    String key = (String)itr.next();
                    String value = (String)sfJsonObj.get(key);
                    try {
                        if (describe.containsKey(key)) {
                            BeanUtils.setProperty(obj, key, value);
                        }
                    } catch(Exception pe) {
                        pe.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     
    public static String getFormedPhoneNumber(String src) {
        if (src == null || src.trim().isEmpty())
            return "";
       
        src = src.trim();

        try {
            if (src.length() > 7) {
                return src.replaceFirst("(^02|[0-9]*)([0-9]{3})([0-9]{4})$", "$1-$2-$3");
            } else if (src.length() > 4) {
                return src.replaceFirst("(^02|[0-9]*)([0-9]{4})$", "$1-$2");
            } else {
                return src;
            }
        } catch(Exception e) {
            return src;
        }
    }
   
    public static String getDomain(HttpServletRequest request) {
      boolean secure= request.isSecure();

      String serverName = request.getServerName();
       
      String retURL = "";
       
      if (secure) {
          retURL = "https://" + serverName;
         
          if (request.getServerPort() != 443) {
              retURL += ":" + request.getServerPort();
          }
      } else {
          retURL = "http://" + serverName;
           
          if (request.getServerPort() != 80) {
              retURL += ":" + request.getServerPort();
          }
      }
       
      return retURL;
    }
    
    private static String sanitationParameter(String input) {
        
        String temp = StringUtil.EMPTY;
        
        if(StringUtil.isEmpty(input) || input == null) {
            return StringUtil.EMPTY;
        } else { 
            int original_length = input.length();
        
            temp = input;
            Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
            Matcher match= pt.matcher(temp);
            while(match.find()) {
                String s= match.group();
                temp=temp.replaceAll("\\"+s, "");
            }
            
            if(temp.length() != original_length) {
               return StringUtil.EMPTY;
            } else {
                return temp;
                
            }
        }
    }
    
    
    
    public static boolean hasUserRole(String roleCode) { 
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority(roleCode)); 
    }
    
    public static ArrayList<String> findAllRegEx(String target){
    	ArrayList<String> findList = new ArrayList();
        
        String regEx = "#(.*?)#";
        Pattern pattern = Pattern.compile(regEx); 
        Matcher matcher = pattern.matcher(target);
        
        int matchCount = 0;
        while (matcher.find()) {
        	findList.add(matcher.group());
        	//findList.add(matcher.group(1));
            //System.out.println(matchCount + " : " + matcher.group() + " ["+matcher.group(1)+"]");
            matchCount++;
        }
        //System.out.println("총 개수 : " + matchCount);
        return findList;
    }    
    
    
    /**
     * 주민번호 Masking
     * Statements
     *
     * @param ssn
     * @param hashDash
     * @return
     * @throws RuntimeException
     */
    public static String getSsnMasked(String ssn, boolean masking, boolean hashDash) throws RuntimeException {
        
        StringBuffer rtnStr = new StringBuffer();
        
        if(masking) {
            if(ssn != null && ssn.length() == 7) {
                rtnStr.append(ssn.subSequence(0,  1)).append("******");
            } else if(ssn != null && ssn.length() == 13) {
                rtnStr.append(ssn.subSequence(0,  6));
                if(hashDash) {
                    rtnStr.append("-");
                }
                rtnStr.append(ssn.subSequence(6,  7)).append("******");
            } else if(ssn != null && ssn.length() == 14) {
                rtnStr.append(ssn.subSequence(0,  8)).append("******");
            }            
        } else {
            if(ssn != null && ssn.length() == 13) {
                rtnStr.append(ssn.subSequence(0,  6));
                if(hashDash) {
                    rtnStr.append("-");
                }
                rtnStr.append(ssn.subSequence(6,  13));
            }            
        }
        

        
        return rtnStr.toString();
    }
    
    
    /**
     * 6자리 인증번호 생성
     *
     * @param len
     * @param dupCd
     * @return
     */
    public static String numberGen(int len, int dupCd) {
        
        Random rand = new Random();
        
        String numStr = "";
        
        for(int i=0; i<len; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            
            if(dupCd == 1) {
                numStr += ran;
            } else if(dupCd == 2) {
                if(!numStr.contains(ran)) {
                    numStr += ran;
                } else {
                    i -= 1;
                }
            }
            
        }
        
        return numStr;
    }
    
    
    // 천단위 콤마
    public static String setComma(String num) {
        
        String strResult = num; //출력할 결과를 저장할 변수
        Pattern p = Pattern.compile("(^[+-]?\\d+)(\\d{3})"); //정규표현식 
        Matcher regexMatcher = p.matcher(num); 
        
        try {
            int cnt = 0;
            while(regexMatcher.find()) {
                            
                strResult = regexMatcher.replaceAll("$1,$2"); //치환 : 그룹1 + "," + 그룹2
                
                regexMatcher.reset(strResult); 
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResult;
    }
    
    public static String setComma(int num) {        
        return setComma(String.valueOf(num));
    }    
    
    /**
     * uri 추출
     * Statements
     *
     * @param url
     * @return
     */
    public static String getUrlDomainName(String url) {
        String domainName = new String(url);

        int index = domainName.indexOf("://");

        if (index != -1) {
          domainName = domainName.substring(index + 3);
        }

        index = domainName.indexOf('/');

        if (index != -1) {
          domainName = domainName.substring(index, domainName.length());
        }

        return domainName;
    }
    
    
	public static String sqlErrHandler(Exception e) {
		e.printStackTrace();

		if (e instanceof NullPointerException) {
			return "NullPointerException";
		} else if (e instanceof DataIntegrityViolationException) {
			return "DataIntegrityViolationException";
		} else if (e instanceof SQLException) {
			SQLException se = (SQLException) e;
			System.out.println("se.getErrorCode() : " + se.getErrorCode());
			switch (se.getErrorCode()) {
			case 1: // ORA-00001 (무결성 제약 조건(ENT.EUA050PK)에 위배됩니다)
				return "COMA0001";
			case 984: // ORA-00984 (열을 사용할 수 없습니다.)
				return "COMA1984";
			case 1400:
				return "COMA1400";
			case 1722:
				return "COMA1722";
			case 3114:
				return "COMA3114";
			}
		}
		return "ETC";
	}
    
}