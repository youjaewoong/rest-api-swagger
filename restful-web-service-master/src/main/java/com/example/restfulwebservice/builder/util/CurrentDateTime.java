package com.example.restfulwebservice.builder.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 유형 설명을 삽입하십시오.
 * 작성 날짜: (2000-11-01 오후 4:08:37)
 * @작성자: Administrator
 */
public final class CurrentDateTime {

	/**
	 * CurrentDateTime 생성자 주석.
	 */
	public CurrentDateTime() {
		super();
	}
	
	
	/** 현재 일자를 return한다. */
	public static String getDate() { 
		Calendar cal = Calendar.getInstance();
		int yy = cal.get(Calendar.YEAR);
		int mo = cal.get(Calendar.MONTH)+1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);
			
		String yyy = null;
		String mmo = null;
		String ddd = null;
		
		//if(yy < 2000)   yyy = "19" + yy;
		//else yyy = "" + yy;
		yyy = "" + yy;
		if(mo < 10) mmo = "0" + mo;
		else mmo = "" + mo;
		if(dd < 10) ddd = "0" + dd;
		else ddd = "" + dd;

		String addDate = "" + yyy + mmo + ddd;
		return addDate;
	}
	
	
	public static String getDashDate() {
		Calendar cal = Calendar.getInstance();
		int yy = cal.get(Calendar.YEAR);
		int mo = cal.get(Calendar.MONTH)+1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);
			
		String yyy = null;
		String mmo = null;
		String ddd = null;
		
		//if(yy < 2000)   yyy = "19" + yy;
		//else yyy = "" + yy;
		yyy = "" + yy;
		if(mo < 10) mmo = "0" + mo;
		else mmo = "" + mo;
		if(dd < 10) ddd = "0" + dd;
		else ddd = "" + dd;

		String addDate = "" + yyy + "-" + mmo + "-" + ddd;
		return addDate;
	}	
	
	public static String getDashDate(String yyyymmdd, int add) {
		String ymd = yyyymmdd.replaceAll("/", "");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR,  Integer.parseInt(ymd.substring(0,4)));
		cal.set(Calendar.MONTH, Integer.parseInt(ymd.substring(4,6))- 1) ;
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ymd.substring(6,8)) + add);

		int yy = cal.get(Calendar.YEAR);
		int mo = cal.get(Calendar.MONTH) + 1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);

		String yyy = null;
		String mmo = null;
		String ddd = null;

		yyy = "" + yy;
		if (mo < 10)
			mmo = "0" + mo;
		else
			mmo = "" + mo;
		if (dd < 10)
			ddd = "0" + dd;
		else
			ddd = "" + dd;

		String addDate = "" + yyy + "/" + mmo + "/" + ddd;
		return addDate;
	}
	
	public static String getDate1() {
		Calendar cal = Calendar.getInstance();
		int yy = cal.get(Calendar.YEAR);
		int mo = cal.get(Calendar.MONTH)+1;
		int dd = cal.get(Calendar.DAY_OF_MONTH);
			
		String yyy = null;
		String mmo = null;
		String ddd = null;
		
		//if(yy < 2000)   yyy = "19" + yy;
		//else yyy = "" + yy;
		yyy = "" + yy;
		if(mo < 10) mmo = "0" + mo;
		else mmo = "" + mo;
		if(dd < 10) ddd = "0" + dd;
		else ddd = "" + dd;

		String addDate = "" + yyy + "/" + mmo + "/" + ddd;
		return addDate;
	}
	
	public synchronized static String getDate(String yyyymmdd, int addDay) {
		Calendar cal = Calendar.getInstance();
		try{
			
			int new_yy = Integer.parseInt(yyyymmdd.substring(0,4));
			int new_mm = Integer.parseInt(yyyymmdd.substring(4,6));
			int new_dd = Integer.parseInt(yyyymmdd.substring(6,8));
			
			cal.set(new_yy,new_mm-1,new_dd);	
	
			cal.add(cal.DATE, addDay);
	
			int yy = cal.get(Calendar.YEAR);
			int mo = cal.get(Calendar.MONTH)+1;
			int dd = cal.get(Calendar.DAY_OF_MONTH);
				
			String yyy = null;
			String mmo = null;
			String ddd = null;
			
			yyy = "" + yy;
			if(mo < 10) mmo = "0" + mo;
			else mmo = "" + mo;
			if(dd < 10) ddd = "0" + dd;
			else ddd = "" + dd;
	
			String addDate = "" + yyy + mmo + ddd;
			return addDate;
		}catch (Exception e) {
			return "";
			// TODO: handle exception
		}

	}
	
	
	/** 현재 일자 및 시간을 'yyyy.mm.dd HH:MM 현재'로 formatting */
	public static String getDatetime() {
		
		String temp = getDate() +":"+ getTime() ;
		return temp;
	}
	
	
	public static String getDay()	{
		
		String ddd;
		
		Calendar cal = Calendar.getInstance();
		int dd = cal.get(Calendar.DAY_OF_MONTH);
		if(dd < 10) ddd = "0" + dd;
		else ddd = "" + dd;
		return ddd;
		
	}
	
	
	//각달의 총 날수 구하기
	public synchronized static int getMaxday(String yyyymm){
		
        int total_day =0;
		int new_yyyy=Integer.parseInt(yyyymm.substring(0,4));
		int new_mm = Integer.parseInt(yyyymm.substring(4,6));

		   if (new_mm==1) total_day=31;
		   else if(new_mm==2){
			   if(((new_yyyy % 4 ==0) && (new_yyyy % 100 != 0)) || (new_yyyy % 400 == 0))
				   total_day = 29;
			   else
				   total_day = 28;
		   }
		   else if(new_mm == 3) total_day = 31;
		   else if(new_mm == 4) total_day = 30;
		   else if(new_mm == 5) total_day = 31;
		   else if(new_mm == 6) total_day = 30;
		   else if(new_mm == 7) total_day = 31;
		   else if(new_mm == 8) total_day = 31;
		   else if(new_mm == 9) total_day = 30;
		   else if(new_mm == 10) total_day = 31;
		   else if(new_mm == 11) total_day = 30;
		   else if(new_mm == 12) total_day = 31;

          return total_day;
          
	}
	
		
	public static int getMinute(int hhmmss) {
		
		int time = 0;
		
		time = getSecond(hhmmss);
		return time/60;	
	}
	
	
	public static String getMonth()	{
		
		String mmo;
		
		Calendar cal = Calendar.getInstance();
		int mo = cal.get(Calendar.MONTH) +1;
		if(mo < 10) mmo = "0" + mo;
		else mmo = "" + mo;
		return mmo;
		
	}
	
	/**
	 * 입력된 날자 기준으로 지난 월이나 이후 월을 구한다. 
	 * @param yyyymmdd
	 * @param addMonth
	 * @return
	 */
	public static String addMonth(String yyyymmdd, int addMonth) {
		
		Calendar cal = Calendar.getInstance();
		int new_yy = Integer.parseInt(yyyymmdd.substring(0,4));
		int new_mm = Integer.parseInt(yyyymmdd.substring(4,6));
		int new_dd = Integer.parseInt(yyyymmdd.substring(6,8));
		
		cal.set(new_yy,new_mm-1,new_dd);	

		cal.add(cal.MONTH, addMonth);

		int mo = cal.get(Calendar.MONTH)+1;
			
		String mmo = null;
		
		if(mo < 10) mmo = "0" + mo;
		else mmo = "" + mo;
		
		String addDate = "" + mmo ;
		return addDate;

	}
	
	public synchronized static String getMonth(String yyyymmdd, int addMonth) {
		
		Calendar cal = Calendar.getInstance();
		int new_yy = Integer.parseInt(yyyymmdd.substring(0,4));
		int new_mm = Integer.parseInt(yyyymmdd.substring(4,6));
		int new_dd = Integer.parseInt(yyyymmdd.substring(6,8));
		
		cal.set(new_yy,new_mm-1,new_dd);	

		cal.add(cal.MONTH, addMonth);

		int yy = cal.get(Calendar.YEAR);
		int mo = cal.get(Calendar.MONTH)+1;
		int dd = cal.get(Calendar.DATE);
			
		String yyy = null;
		String mmo = null;
		String ddd = null;
		
		yyy = "" + yy;
		if(mo < 10) mmo = "0" + mo;
		else mmo = "" + mo;
		
		if(dd < 10) ddd = "0" + dd;
		else ddd = "" + dd;

		String addDate = "" + yyy + mmo + ddd;
		return addDate;

	}
	
	
	/**
	 * 메소드 설명을 삽입하십시오.
	 * 작성 날짜: (2002-08-09 오후 6:11:52)
	 * @return java.lang.String
	 */
	public static String getSearchTime() {
		
			Calendar cal = Calendar.getInstance();
			int yy = cal.get(Calendar.YEAR);
			int mo = cal.get(Calendar.MONTH)+1;
			int dd = cal.get(Calendar.DAY_OF_MONTH);
			int hh = cal.get(Calendar.HOUR_OF_DAY);
			int mm = cal.get(Calendar.MINUTE);
			int ss = cal.get(Calendar.SECOND);
				
			String yyy = null;
			String mmo = null;
			String ddd = null;
			String hhh = null;
			String mmm = null;
			String sss = null;
			
			yyy = "" + yy;
			if(mo < 10) mmo = "0" + mo;
			else mmo = "" + mo;
			if(dd < 10) ddd = "0" + dd;
			else ddd = "" + dd;
	
			String addDate = "" + yyy +"/"+ mmo +"/"+ ddd;
	
			if(hh < 10) hhh = "0" + hh;
			else hhh = "" + hh;
			if(mm < 10) mmm = "0" + mm;
			else mmm = "" + mm;
			if(ss < 10) sss = "0" + ss;
			else sss = "" + ss;
	
			String addTime = "  " + hhh +":" + mmm +":" + sss;
			addDate += addTime;
	
			return addDate;
	}
	
	
	public static int getSecond(int hhmmss) {
		
		String sign = "";
		String timeString = "";
		int time = 0;
		int hh = 0;
		int mm = 0;
		int ss = 0;
		
		if (hhmmss < 0) {
			sign = "-";
			timeString = Integer.toString(hhmmss).substring(1);
		} else {
			timeString = Integer.toString(hhmmss);
		}
		if (timeString.length() < 6) {
			StringBuffer bf = new StringBuffer();
			for (int i=timeString.length(); i < 6 ; i++) {
				bf.append("0");
			}
			timeString = bf.toString() + timeString;	
		}	
		 hh = Integer.parseInt(timeString.substring(0,2)) * 3600;
		 mm = Integer.parseInt(timeString.substring(2,4)) * 60;
		 ss = Integer.parseInt(timeString.substring(4));
		 time = hh + mm + ss; 
		if (sign.equals("-")) {
			return time * -1;
		} else {	
			return time;
		}	
	}
	
	
	/** 현재 시간을 return한다. */
	public static String getTime() {
		
		Calendar cal = Calendar.getInstance();
//		int hh = cal.get(Calendar.HOUR_OF_DAY) + 1;
		int hh = cal.get(Calendar.HOUR_OF_DAY);
		int mm = cal.get(Calendar.MINUTE);
		int ss = cal.get(Calendar.SECOND);
			
		String hhh = null;
		String mmm = null;
		String sss = null;
			
		if(hh < 10) hhh = "0" + hh;
		else hhh = "" + hh;
		if(mm < 10) mmm = "0" + mm;
		else mmm = "" + mm;
		if(ss < 10) sss = "0" + ss;
		else sss = "" + ss;

		String addTime = "" + hhh + mmm + sss;
		return addTime;
	}
	
	
	//요일구하기
	public synchronized static int getWeek(String yyyymmdd, int addDay){

		Calendar cal = Calendar.getInstance();
		int new_yy = Integer.parseInt(yyyymmdd.substring(0,4));
		int new_mm = Integer.parseInt(yyyymmdd.substring(4,6));
		int new_dd = Integer.parseInt(yyyymmdd.substring(6,8));

		cal.set(new_yy,new_mm-1,new_dd);
        cal.add(cal.DATE, addDay);

		int week = cal.get(cal.DAY_OF_WEEK);
		return week;
	}

	
	public static String getYear()	{
		
		Calendar cal = Calendar.getInstance();
		int yy = cal.get(Calendar.YEAR);

		return String.valueOf(yy);
	}
	
	  /**
	 * 시작일과 종료일의 날짜 차이를 계산한다.
	 * 작성 날짜: (2006-11-08)
	 * @return long
	 * @param type 	java.lang.String 	시작일 ( 20061201 )
	 * @param type 	java.lang.String 	종료일 ( 20061221 )
	 */
	public static long diffOfDate(String begin, String end) throws Exception
	  {
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			Date beginDate = formatter.parse(begin);
			Date endDate = formatter.parse(end);
			long diff = endDate.getTime() - beginDate.getTime();
			long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays;
		}catch (Exception e) {
			// TODO: handle exception
			return 0L;
		}
	}
	
	/**
	 *  num을 주면 ...7을 7일후날짜..-7 일전 날짜...
	 *   0 날짜일 1요일
	 */
	public static String[][] getDayOfDay(String  YYYYMMDD,  int  num) {
		
		String Year, Month, Today, Stdt = YYYYMMDD;
		String [][] sDate = new String[2][num < 0 ? -num:num==0?1:num];
	
		Calendar date = Calendar.getInstance( Locale.KOREA );
		int  loop_cnt, week, year, month, day, day_num, last_day, i, j;
		String [] day_name = {"", "일", "월", "화", "수", "목", "금", "토" };
		
		try{
			
			if(Stdt.length() == 6) Stdt = Stdt + "01";	// 1날. YYYYMM
			else if(Stdt.length() < 6) Stdt = CurrentDateTime.getDate();  //""
			else if(Stdt.length() > 8) Stdt = Stdt.substring(0, 8);  // YYYYMMDDHHMM
	
			day = Integer.parseInt(Stdt.substring(6)); //DD 추출
			Stdt = Stdt.substring(0, 6);		// YYYYMM 추출		
			last_day = getMonthDays(Stdt);
			
			loop_cnt = num < 0 ? -num : num;
			if(num >= 0){
				for(i = 0; i < loop_cnt; i++){
					sDate[0][i] =  Stdt + ( day < 10 ? "0" : "") + String.valueOf(day);
					sDate[1][i] = day_name[getDayOfWeek(sDate[0][i])];			
					if(++day > last_day){
						year = Integer.parseInt(Stdt.substring(0, 4));
						month = Integer.parseInt(Stdt.substring(4, 6));
						if(month == 12){ month = 1; ++year; }
						else ++month;
						day = 1;
						Stdt = String.valueOf(year) + (month < 10? "0" : "") + month;
						last_day = getMonthDays(Stdt);
					}				
				}
			}
			else{
				for(i = loop_cnt - 1; i >= 0; i--){
					sDate[0][i] =  Stdt + ( day < 10 ? "0" : "") + String.valueOf(day);
					sDate[1][i] = day_name[getDayOfWeek(sDate[0][i])];			
					if(--day < 1){
						year = Integer.parseInt(Stdt.substring(0, 4));
						month = Integer.parseInt(Stdt.substring(4, 6));
						if(month == 1){ month = 12; --year; }
						else --month;
						Stdt = String.valueOf(year) + (month < 10? "0" : "") + month;
						day = getMonthDays(Stdt);					
					}							
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return sDate;
	}
	
	
	public static int getDayOfWeek(String fdat) {
		int year2 = Integer.parseInt(fdat.substring(0,4));
		int mon2 = Integer.parseInt(fdat.substring(4,6));
		int day2 = Integer.parseInt(fdat.substring(6,8));
		
		Calendar calendar = Calendar.getInstance( Locale.KOREA );
		
		calendar.set(Calendar.YEAR,year2);
		calendar.set(Calendar.MONTH,mon2-1);
		calendar.set(Calendar.DATE,day2);
		
		return calendar.get(Calendar.DAY_OF_WEEK );
	}
	
	
	public static int getMonthDays(int _year, int _month){
			
		int year = _year;
		int month = _month-1;
		int days[] = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
		if ( ((year % 4 == 0) && !(year % 100 == 0))|| (year % 400 == 0) )
			days[1] = 29;
		return days[month];
	}
// 기숙사용 수정	
	public static int getMonthDays2(int _year, int _month){
		
		int year = _year;
		int month = _month-1;
		int days[] = new int[]{30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
	
		return days[month];
	}
	
	
	
	public static int getMonthDays(String stdt){
		
		int year = Integer.parseInt(stdt.substring(0,4));
		int month = Integer.parseInt(stdt.substring(4,6));
		
		return getMonthDays(year, month);
		
	}
// 기숙사 달력 계산용	
	public static int getMonthDays2(String stdt){
		
		int year = Integer.parseInt(stdt.substring(0,4));
		int month = Integer.parseInt(stdt.substring(4,6));
		
		return getMonthDays2(year, month);
		
	}
	
	
	/**  yyyymm1 과 yyyymm2 사의 월수를 리턴. 
	  * ex> 200112, 200203 --> 4 s_yearmm 은 e_yyyymm보다 작거나 같아야 한다.
	  *
	*/
	public static int getMonthGugan(String s_yyyymm, String e_yyyymm) {
		
		int cnt = 1;
		String frdt, endt ;
		int f_month, e_month, tmp, f_year, e_year, month, i;
			
		endt = "";
		frdt = s_yyyymm;	
	    i = month =  tmp = f_month =  e_month = 0;
		if(frdt == null || frdt.length() < 6)  return 0;
		if(e_yyyymm == null || e_yyyymm.length() <  6)	
			endt = CurrentDateTime.getDate().substring(0, 6);		
		else endt = e_yyyymm;
		f_year = Integer.parseInt(frdt.substring(0, 4));
		e_year = Integer.parseInt(endt.substring(0, 4));
		f_month = Integer.parseInt(frdt.substring(4,6));
		e_month = Integer.parseInt(endt.substring(4,6));
	
		// 년도가 다를때..
		tmp = f_year;
		month = f_month;
		while(tmp < e_year){
			for(i = month; i <=12; i++) ++cnt;
			++tmp;
			month = 1;
		}	
		tmp = month;
		while(tmp != e_month){
			++cnt;
			++tmp;
		}
		return  cnt;
	}
	
	/**  yyyymmdd1 과 yyyymmdd2 사의 일수를 리턴. 
	  * ex> 20011201, 20020301 --> 61  s_ymd 은 e_ymd보다 작거나 같아야 한다.
	  *
	*/
		
	public static int getDateGugan(String s_ymd, String e_ymd) {
		
		int cnt = 0;
		String frdt, endt ;
		int   year, month, day, sign;
		
		if(s_ymd == null || s_ymd.length() < 8)  return 0;
		if(e_ymd == null || e_ymd.length() <  8){
			e_ymd = CurrentDateTime.getDate();
		}
		if( s_ymd.compareTo(e_ymd) < 0){
			frdt = s_ymd;
			endt = e_ymd;
			sign = 1;
		}else{
			frdt = e_ymd;
			endt = s_ymd;
			sign = -1;
		}
		year = Integer.parseInt(endt.substring(0, 4));
		month = Integer.parseInt(endt.substring(4,6));
		day = Integer.parseInt(endt.substring(6,8));
		 

		while( !frdt.equals(endt) ){
			
			day--;
			if(day == 0){
				month--;
				if(month == 0){
					month = 12;
					year--;
				}
				day = getMonthDays(year, month);
			}
			endt = String.valueOf(year)  
					+ ( String.valueOf(month).length() != 2 ? "0" + String.valueOf(month) : String.valueOf(month) ) 
					+ ( String.valueOf(day).length() != 2 ? "0" + String.valueOf(day) : String.valueOf(day) ); 
			cnt++;
		}
		
		return  cnt * sign;
	}
	
	public static void main(String[] args) {
		System.out.println("gugan:" + getDateGugan("20091013", ""));
	}
	/**  이전/년월 구하기
	  * ex> 200207, -1 -> 200206을 리턴 
	*/
	public static  String getSTDT(String _Stdt, int  num) {
		
		String Stdt = _Stdt;
		String Year, Month;
		int   year,  month,  loop_cnt;
		
		try{
			if( Stdt.equals("") )
				Stdt = CurrentDateTime.getDate().substring(0, 6);
					
			Year = Stdt.substring(0, 4);
			Month = Stdt.substring(4, 6);
			
			year = Integer.parseInt(Year);
			month = Integer.parseInt(Month);
			loop_cnt = num < 0 ? -num : num;
		
			if(num < 0){
				for(int i = 0; i < loop_cnt; i++){
					month--;
					if(month == 0){
						year--;
						month = 12;
					}					
				}				
			}
			else{
				for(int i = 0; i < loop_cnt; i++){
					month++;
					if(month == 13){
						year++;
						month = 1;
					}			
				}				
			}
			
			Year = String.valueOf(year);
			Month = String.valueOf(month);
			if(Month.length() == 1)
					Month = "0" + Month;		
			Stdt = Year + Month;
			
		}catch	(Exception e){
			e.printStackTrace();
		}
			
		return Stdt;
	}
	/** 현재 시간을 return한다. */
	public static String getTimeMilli() {
		
		Calendar cal = Calendar.getInstance();
//		int hh = cal.get(Calendar.HOUR_OF_DAY) + 1;
		int hh = cal.get(Calendar.HOUR_OF_DAY);
		int mm = cal.get(Calendar.MINUTE);
		int ss = cal.get(Calendar.SECOND);
		int milli = cal.get(Calendar.MILLISECOND);
			
		String hhh = null;
		String mmm = null;
		String sss = null;
			
		if(hh < 10) hhh = "0" + hh;
		else hhh = "" + hh;
		if(mm < 10) mmm = "0" + mm;
		else mmm = "" + mm;
		if(ss < 10) sss = "0" + ss;
		else sss = "" + ss;

		String addTime = "" + hhh + mmm + sss + StringUtil.leftPad(String.valueOf(milli), 3, "0");
		return addTime;
	}
	
}
