package utility;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

public class ClockUtil {
	private static Clock clock = Clock.systemDefaultZone();
	private static ZoneId zoneId = ZoneId.systemDefault();

	public static LocalDate now() {
		return LocalDate.now(getClock());
	}

	public static void useFixedClockAt(LocalDate datePassata) {
		clock = Clock.fixed(datePassata.atStartOfDay(zoneId).toInstant(), zoneId);
	}

	public static void useSystemDefaultZoneClock() {
		clock = Clock.systemDefaultZone();
	}

	private static Clock getClock() {
		return clock;
	}
}
