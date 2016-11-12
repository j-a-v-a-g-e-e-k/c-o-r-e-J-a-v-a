package com.design.pattern.Structural;

class MySQLHelper{
	public void getMySQLConnection(){
		System.out.println("MySQL Connected");
	}
	public void getMySQLHTMLReport(){
		System.out.println("MySQLHTMLReport generated");
	}
	public void getMySQLPDFReport(){
		System.out.println("MySQLPDFReport generated");
	}	
}

class MyOracleHelper{
	public void getMyOracleConnection(){
		System.out.println("MyOracle Connected");
	}
	public void getMyOracleHTMLReport(){
		System.out.println("MyOracleHTMLReport generated");
	}
	public void getMySQLPDFReport(){
		System.out.println("MyOraclePDFReport generated");
	}	
}

class Facade{
	public static void getReport(DBType dbType, ReportType reportType){
		MySQLHelper mySQLHelper = new MySQLHelper();
		MyOracleHelper myOracleHelper = new MyOracleHelper();
		switch(dbType){
		case MYSQL:
			switch(reportType){
			case HTML:
				mySQLHelper.getMySQLConnection();
				mySQLHelper.getMySQLHTMLReport();
			case PDF:
				mySQLHelper.getMySQLConnection();
				mySQLHelper.getMySQLPDFReport();
			}			
		case ORACLE:
			switch(reportType){
			case HTML:
				myOracleHelper.getMyOracleConnection();
				myOracleHelper.getMyOracleHTMLReport();
			case PDF:
				myOracleHelper.getMyOracleConnection();
				myOracleHelper.getMySQLPDFReport();
			}
		}
	}
}

enum DBType{ MYSQL,ORACLE; }
enum ReportType{ HTML, PDF ; }

public class FacadeDemo {
	public static void main(String [] args){
		Facade.getReport(DBType.ORACLE, ReportType.PDF);
	}
}
