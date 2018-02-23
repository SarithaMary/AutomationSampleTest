package com.sample.utils;

import java.util.ArrayList;

public class TestUtil {
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 20;
	public static String TESTDATA_PATH = "src/main/java/com/sample/testdata/TestData.xlsx";
	
	static Xls_Reader reader;

	public static ArrayList<Object[]> getTestDatafromXL(String sheetName) {
		
		ArrayList<Object[]> MyData= new ArrayList<Object[]>();

		try {
			reader = new Xls_Reader(TESTDATA_PATH); //"com.sample.testdata/TestData.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int rowCount = reader.getRowCount(sheetName);

		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String fName = reader.getCellData(sheetName, "fName", rowNum);
			String surName = reader.getCellData(sheetName, "surName", rowNum);
			String eMail = reader.getCellData(sheetName, "eMail", rowNum);
			String eMail_ReEnter = reader.getCellData(sheetName, "eMail_ReEnter", rowNum);
			String newPwd = reader.getCellData(sheetName, "newPwd", rowNum);
			String gender = reader.getCellData(sheetName, "gender", rowNum);
			String mandatoryMsg = reader.getCellData(sheetName, "mandatoryMsg", rowNum);
			
			Object Ob[]={fName, surName,eMail,eMail_ReEnter,newPwd,gender,mandatoryMsg};
			MyData.add(Ob);
		}
		return MyData;
	}

}
