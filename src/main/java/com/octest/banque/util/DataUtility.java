package com.octest.banque.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
Data Utility est également une classe personnalisée qui nous aidera à convertir nos données en un type
 tel que convertir les données String au format int Set Date, etc.
  */

public class DataUtility 
{
	/**
	 *  Format de la date
	 */
	public static final String APP_DATE_FORMAT = "MM/dd/yyyy";


	
	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);


	/**
	 * Convertir objet en string
	 * 
	 * 
	 */
	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}

	/**
	 * Convertit une  chaîne en entier
     *
	 */
	public static String getStringData(Object val) {
		
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

	/**
	 * Converts String into Integer
	 * 
	 * @param val
	 * @return
	 */
	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}

	/**
	 *chaîne en longue
     *
	 */
	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}
	
	public static double getDouble(String val) {
		if (DataValidator.isDouble(val)) {
			return Double.parseDouble(val);
		} else {
			return 0.0;
		}
	}


	/**
	 * chaîne en date
	 */
	 public static Date getDate(String val) {
	        Date date = null;
	        try {
	            date = formatter.parse(val);
	        } catch (Exception e) {

	        }
	        return date;
	    }

	public static Date getDate1(String val) {
		Date date = null;
		
		try {
			date = formatter.parse(val);
			
		}catch(Exception e){}
		return date;
	}
	/**
	date en chaine 
	 */
	public static String getDateString(Date date) {
		
		try {
		   if(date!=null) {
				return formatter.format(date);
			}
			else{
				return "";
			}
		} catch (Exception e) {
			return "";
		}
		
	}

   /**
	 * date apres n jours
	 * 
	 */
	 static Date getDate(Date date, int day) {
		return null;
	}

	 /**
       * Converts String into Time
	   *
	   */
	public static Timestamp getTimestamp(long l) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}
	
	/**
	 * Converts String into Time
	 *
	 */
	public static Timestamp getTimestamp(String cdt) {

		Timestamp timeStamp = null;
		try {
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}
	/**
	 *
	 * 
	 * Converts Time into Long
	 * 
	 *
	 */
	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 
	 * l'heure actuelle
     *
	 */
	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}
	
	public static long getRandom() {
		Random random=new Random();
		return (long)random.nextInt(100000000);
	}

	public static void main(String[] args)
	{
		DataUtility d=new DataUtility();
		
		Date date=new Date();
		
		
		System.out.println("formate date :"+getDate("12/09/1991"));
	}

}

