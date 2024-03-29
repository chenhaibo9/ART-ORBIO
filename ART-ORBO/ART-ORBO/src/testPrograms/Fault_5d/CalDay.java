
	
	package testPrograms.Fault_5d;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.util.Calendar;
	import java.util.Date;
	/**
	 * 
	 * @author zxz
	 * Â¼Ã†Ã‹Ã£Ã�Â¬Ã’Â»Ã„ÃªÃ–Ã�Â£Â¬Ã�Â½ÃˆÃ•Ã†ÃšÂ¼Ã¤Â¸Ã´ÂµÃ„ÃŒÃ¬ÃŠÃ½
	 *
	 */
	public class CalDay extends PUT_5D {

		public static double[] min={ 1, 1, 1, 1, 1800};
		public static double[] max={12, 31, 12, 31, 2200};

		public static double failureRate = 0.000632;
		public static int Dimension = 5;
		
		
		public static int cal(int month1, int day1, int month2, int day2, int year) {
			int numDays;
			if (month2 == month1) { // Ã”ÃšÃ�Â¬Ã’Â»Ã”Ã‚Ã–Ã�
				numDays = day2 - day1;
			} else {
				int daysIn[] = { 0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
				int m4 = year % 4;
				int m100 = year % 100;
				int m400 = year % 400;
				if ((m4 != 0) || (m100 == 0) && (m400 != 0)) {
					daysIn[2] = 28;
				} else {
					daysIn[2] = 29;
				}
				numDays = day2 + (daysIn[month1] - day1);
				for (int i = month1 + 1; i <= month2 - 1; i++) {
					numDays = daysIn[i] + numDays;
				}
			}
			return numDays;
		}

		public static int calErr(int month1, int day1, int month2, int day2, int year) {
			int numDays;
			if (month2 == month1) {
				numDays = day2 - day1;
			} else {
				int daysIn[] = { 0, 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
				int m4 = year % 4;
				int m100 = year % 100;
				int m400 = year % 400;
				if ((m4 != 0) || (m100 == 0)) {   // ERROR if ((m4 != 0) || (m100 == 0) && (m400 != 0)) {
					daysIn[2] = 28;
				} else {
					daysIn[2] = 29;
				}
				numDays = day2 + (daysIn[month1] - day1);
				for (int i = month1 + 1; i <= month2 - 1; i++) {
					numDays = daysIn[i] + numDays;
				}
			}
			return numDays;
		}

		/*
		 * Â¸Ã¹Â¾Ã�Ã„Ãª-Ã”Ã‚ Â»Ã±ÃˆÂ¡Â¸ÃƒÃ”Ã‚ÂµÃ„Ã—Ã®Â´Ã³ÃŒÃ¬ÃŠÃ½
		 */
		public static int getDaysByYearMonth(int year, int month) {
			Calendar a = Calendar.getInstance();
			a.set(Calendar.YEAR, year);
			a.set(Calendar.MONTH, month - 1);
			a.set(Calendar.DATE, 1);
			a.roll(Calendar.DATE, -1);
			int maxDate = a.get(Calendar.DATE);
			return maxDate;
		}

	
	@Override
	public boolean isCorrect(int month1, int day1, int month2, int day2, int year) {
		// TODO Auto-generated method stub
		int maxDay1 = getDaysByYearMonth(year, month1); // ï¿½ï¿½ï¿½ï¿½ï¿½Ò»ï¿½ï¿½ï¿½ï¿½ï¿½ÚµÄµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		int maxDay2 = getDaysByYearMonth(year, month2); // ï¿½ï¿½ï¿½ï¿½Ú¶ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ÚµÄµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		if (day1 > maxDay1 || day2 > maxDay2) { // ï¿½ï¿½ï¿½Ö·Ç·ï¿½ï¿½ï¿½ï¿½ï¿½ Ö±ï¿½Ó·ï¿½ï¿½ï¿½false
			return true;
		}

		Date date1 = new Date(year, month1, day1);
		Date date2 = new Date(year, month2, day2);

		if (date1.after(date2)) { // Ê¹data1ï¿½ï¿½ï¿½ï¿½data2
			Date dateTemp = date1;
			date1 = date2;
			date2 = dateTemp;
		}

		int origResult = cal(date1.getMonth(), date1.getDate(), date2.getMonth(), date2.getDate(), date1.getYear());
		int errResult = calErr(date1.getMonth(), date1.getDate(), date2.getMonth(), date2.getDate(), date1.getYear());
//		if (origResult != errResult) { 
//			return true;
//		}
//		return false;
		return origResult == errResult;
	}

	@Override
	public double modified_fn(int month1, int day1, int month2, int day2, int year) {
		// TODO Auto-generated method stub
		int maxDay1 = getDaysByYearMonth(year, month1); // ï¿½ï¿½ï¿½ï¿½ï¿½Ò»ï¿½ï¿½ï¿½ï¿½ï¿½ÚµÄµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		int maxDay2 = getDaysByYearMonth(year, month2); // ï¿½ï¿½ï¿½ï¿½Ú¶ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ÚµÄµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		if (day1 > maxDay1 || day2 > maxDay2) { // ï¿½ï¿½ï¿½Ö·Ç·ï¿½ï¿½ï¿½ï¿½ï¿½ Ö±ï¿½Ó·ï¿½ï¿½ï¿½false
			return 0;
		}
		Date date1 = new Date(year, month1, day1);
		Date date2 = new Date(year, month2, day2);
		if (date1.after(date2)) { // Ê¹data1ï¿½ï¿½ï¿½ï¿½data2
			Date dateTemp = date1;
			date1 = date2;
			date2 = dateTemp;
		}
		int result = calErr(date1.getMonth(), date1.getDate(), date2.getMonth(), date2.getDate(), date1.getYear());
		return 0;
	}

}
