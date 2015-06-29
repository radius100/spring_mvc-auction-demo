package auction.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import auction.entity.Item;

public class DateTimeUtils {

	static public String getDateAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MMM-YYYY");

		return dtf.print(dateTime);
	}

	static public String getTimeAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern("HH:mm");

		return dtf.print(dateTime);
	}

	static public String getDateTimeAsString(Date date) {

		DateTime dateTime = new DateTime(date);

		DateTimeFormatter dtf = DateTimeFormat.forPattern("dd-MMM-YYYY HH:mm");

		return dtf.print(dateTime);
	}

	static public Item createItemDateMessage4IndexJsp(Item item) {

		DateTime dateStart = new DateTime(item.getStartDate());
		DateTime dateFinish = new DateTime(item.getFinishDate());
		DateTime dateCurrent = new DateTime();

		dateCurrent = dateCurrent.minusMinutes(25);
		// dateStart=dateStart.minusMinutes(35);
		dateFinish = dateFinish.plusDays(2);

		if (dateCurrent.isBefore(dateStart)) {

			Duration duration1 = new Duration(dateCurrent, dateStart);
			long d1 = duration1.getStandardDays();
			long h1 = duration1.getStandardHours();

			if (d1 >= 1) {
				item.setDateMessage(1);
				item.setDateValue(d1);
			} else if (h1 >= 1 && h1 <= 24) {
				item.setDateMessage(2);
				item.setDateValue(h1);
			}

			else if (h1 < 1)
				item.setDateMessage(3);

		} else if (dateCurrent.isBefore(dateFinish) && dateCurrent.isAfter(dateStart)) {

			Duration duration2 = new Duration(dateCurrent, dateFinish);
			long d2 = duration2.getStandardDays();
			long h2 = duration2.getStandardHours();

			if (d2 >= 1) {
				item.setDateMessage(4);
				item.setDateValue(d2);
			}

			else if (h2 >= 1 && h2 <= 24) {
				item.setDateMessage(5);
				item.setDateValue(h2);
			}

			else if (h2 < 1)
				item.setDateMessage(6);
		}

		return item;
	}
}
