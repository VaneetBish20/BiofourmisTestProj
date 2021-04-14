package com.biofourmis.qa.utility;

import java.util.ArrayList;

import excelUtility.ExcelReader;


public class TestUtil {
	
	static ExcelReader reader;
	
	public static ArrayList<Object[]> getDataFromExcel(){
	
	ArrayList<Object[]> myData = new ArrayList<Object[]>();
	// .//src//test//resources//excel//testdata.xlsx
	//C:/Vaneet Bish/TestAutomation/SeleniumLectures2020/BiofourmisTests/src/main/java/com/biofourmis/qa/testdata
	try {
		reader = new ExcelReader("C:/Vaneet Bish/TestAutomation/SeleniumLectures2020/BiofourmisTests/src/main/java/testdata/Signup.xlsx");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	for(int rowNum =2; rowNum <= reader.getRowCount("Signup"); rowNum++) {
		String emailAddress = reader.getCellData("Signup", "emailaddress", rowNum);
		String firstName = reader.getCellData("Signup", "firstname", rowNum);
		String lastName = reader.getCellData("Signup", "lastname", rowNum);
		String password = reader.getCellData("Signup", "password", rowNum);
		String address = reader.getCellData("Signup", "address", rowNum);
		String city = reader.getCellData("Signup", "city", rowNum);
		String state = reader.getCellData("Signup", "state", rowNum);
		
		Object ob[] = {emailAddress, firstName, lastName, password, address, city, state};
		myData.add(ob);
		
	}
	return myData;

}
}
	
