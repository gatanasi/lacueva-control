package com.lacueva.control.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtilThreadSafe {
	/**
	 * Helper class for "thread-safe" SimpleDateFormat'ers, holding them in a
	 * ThreadLocal ensures they are not called from different threads at the
	 * same time, without resorting to synchronization
	 */
	static class FormattersThreadCache extends
			ThreadLocal<Map<String, SimpleDateFormat>> {
		@Override
		protected Map<String, SimpleDateFormat> initialValue() {
			return new HashMap<String, SimpleDateFormat>();
		}

		public SimpleDateFormat get(String formatString) {
			Map<String, SimpleDateFormat> map = get();
			SimpleDateFormat sdf = map.get(formatString);
			if (sdf == null) {
				sdf = new SimpleDateFormat(formatString);
				map.put(formatString, sdf);
			}
			return sdf;
		}
	};

	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";

	/** ThreadLocal with SimpleDateFormat'ers */
	private static final FormattersThreadCache fmtCache = new FormattersThreadCache();

	public static Date parse(String date) throws ParseException {
		return parse(ISO_DATE_FORMAT, date);
	}

	public static String format(Date date) {
		return format(ISO_DATE_FORMAT, date);
	}

	private static Date parse(String format, String date) throws ParseException {
		return fmtCache.get(format).parse(date);
	}

	private static String format(String format, Date date) {
		return fmtCache.get(format).format(date);
	}
}