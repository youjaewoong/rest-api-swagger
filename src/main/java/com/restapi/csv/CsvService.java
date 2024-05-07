package com.restapi.csv;

import com.restapi.user.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
 
//lombok annotation
@Slf4j
//spring annotation
@Service
public class CsvService {
 
    private static final String[] HEADERS = {"id", "name", "joinDate", "password", "ssn"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);
 
    //load data into csv
    public byte[] load(final List<User> residents) {
        return writeDataToCsv(residents);
    }
 
    //write data to csv
    private byte[] writeDataToCsv(final List<User> users) {
        log.info("Writing data to the csv printer");
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
             final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {
            for (final User user : users) {
                final List<String> data = Arrays.asList(
                        String.valueOf(user.getId()),
                        user.getName(),
                        String.valueOf(user.getJoinDate()),
                        user.getPassword(),
                        user.getSsn());
                printer.printRecord(data);
            }
            printer.flush();
            return stream.toString().getBytes("MS949");
        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }

	public List<User> getUsers() {
	    List<User> users = new ArrayList<>();
	    users.add(new User(1, "윤재웅", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2, "마예은", new Date(), "pass2", "801010-2222222"));
	    users.add(new User(3, "윤혁준", new Date(), "pass2", "901010-1111111"));
		return users;
	}
	
	 public byte[]  getCSVbyByte(HashMap<String, String> parameter) {

	       //추출 데이터 가져오기
	       List<HashMap<String, String>> exportData = new ArrayList<HashMap<String, String>>();
	       HashMap<String,String> map = new HashMap<>();
	       map.put("DATE_CREATE", "하이0");
	       map.put("PRINT_TEXT", "하이1");
	       map.put("URL", "하이2");
	       map.put("MEMO", "하이3");
	       map.put("IMAGE", "하이4");
	       map.put("COLOR", "하이5");
	       map.put("URL_TYPE", "하이6");
	       
	       exportData.add(map);
	       

	       //저장 위치 및 파일명
	       StringWriter sw = new StringWriter();
	       CSVPrinter csvPrinter = null;
	       String[] HEADER = {"DATE_CREATE", "PRINT_TEXT", "URL", "MEMO", "IMAGE", "COLOR", "URL_TYPE"};
	       
	       try {
	           csvPrinter = new CSVPrinter(sw, CSVFormat.DEFAULT.withHeader(HEADER));

	           for (HashMap<String, String> res : exportData) {
	               List<String> data  = Arrays.asList(
	                       res.get("DATE_CREATE"),
	                       res.get("PRINT_TEXT").replaceAll(",",""),
	                       res.get("URL"),
	                       res.get("MEMO"),
	                       res.get("IMAGE"),
	                       res.get("COLOR"),
	                       res.get("URL_TYPE")
	               );
	               csvPrinter.printRecord(data);
	           }
	           sw.flush();
	           return sw.toString().getBytes("MS949");

	       } catch (Exception e) {
	           e.printStackTrace();
	       } finally {
	           try {
	               csvPrinter.close();
	           } catch (IOException e) {
	               e.printStackTrace();
	           }
	       }
	       return null;
	   }

}