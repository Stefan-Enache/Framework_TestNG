package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    // DataProvider 1

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\testData\\Opencart_LoginData.xlsx";   // taking Excel file from testData

        ExcelUtility xlUtil = new ExcelUtility(path);   // creating an object for XLUtility

        int totalRows = xlUtil.getRowCount("Sheet1");
        int totalColumns = xlUtil.getCellCount("Sheet1", 1);

        String loginData[][] = new String[totalRows][totalColumns];  // created two-dimensional array which can store the data: user and password

        for (int i = 1; i <= totalRows; i++)    // 1 skip header  // read the data from xl and store it in a two-dimensional array
        {
            for (int j = 0; j < totalColumns; j++)   // 0
            {
                loginData[i - 1][j] = xlUtil.getCellData("Sheet1", i, j);  // i - 1 to use position 0 in the 2D array,
            }                                                                        // i was previously 1 because of skipping the Excel header
        }
        return loginData;   // returning two dimension array

    }

    // DataProvider 2

    // DataProvider 3

    // DataProvider 4
}
