package api.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProvider2 {

	@DataProvider(name = "Data")
	public Object[][] getUserData() throws IOException {
	    String path = System.getProperty("user.dir") + "//student.xlsx";
	    XLUtility xl = new XLUtility(path);

	    int rowCount = xl.getRowCount("Sheet1");
	    int cellCount = xl.getCellCount("Sheet1", 0); // Assuming 0 is header row

	    Object[][] data = new Object[rowCount][5]; // Only 5 columns expected by test method

//	    for (int i = 1; i <= rowCount; i++) {
//	        data[i - 1][0] = xl.getCellData("Sheet1", i, 0); // id
//	        data[i - 1][1] = xl.getCellData("Sheet1", i, 1); // name
//	        data[i - 1][2] = xl.getCellData("Sheet1", i, 2); // location
//	        data[i - 1][3] = xl.getCellData("Sheet1", i, 3); // phone
//
//	        List<String> courseList = new ArrayList<>();
//	        for (int j = 4; j < cellCount; j++) {
//	            String course = xl.getCellData("Sheet1", i, j);
//	            if (course != null && !course.trim().isEmpty()) {
//	                courseList.add(course);
//	            }
//	        }
//
//	        data[i - 1][4] = courseList; // 5th column is the List<String>
//	    }
	    int j=0;
	    for(int i=1;i<=rowCount;i++){
	    	for(j=0;j<4;j++){
	    	data[i-1][j]=xl.getCellData("Sheet1",i,j);
	    	}
	    	List<String> courseList=new ArrayList<>();
	    	for(int k=4;k<cellCount;k++){
	    	String course=xl.getCellData("Sheet1",i,k);
	    	if(course!=null && !course.trim().isEmpty()){
	    	courseList.add(course);
	    	}
	    	}
	    	data[i-1][j]=courseList;
	    	}

	    return data;
	}
	
	@DataProvider(name="Id")
	public Object[] getID() throws IOException {
		String path=System.getProperty("user.dir")+"//student.xlsx";
		XLUtility xl=new XLUtility(path);
		int rowcount=xl.getRowCount("Sheet1");
		Object[] userId=new Object[rowcount];
		for(int i=1;i<=rowcount;i++) {
			userId[i-1]=xl.getCellData("Sheet1",i,0);
		}
		return userId;
	}
//	@DataProvider(name = "Id")
//	public Object[] getID() throws IOException {
//	    String path = System.getProperty("user.dir") + "//student.xlsx";
//	    XLUtility xl = new XLUtility(path);
//	    int rowcount = xl.getRowCount("Sheet1");
//	    Object[] userId = new Object[rowcount];
//	    for (int i = 1; i <= rowcount; i++) {
//	        userId[i - 1] = xl.getCellData("Sheet1", i, 0); // No parseInt
//	    }
//	    return userId;
//	}

}
