package com.paladin.health.service.core.xk.message;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paladin.framework.spring.SpringContainer;

@Component
public class MessageContainer implements SpringContainer {

	@Autowired
	private MessageProperties messageProperties;

	private Map<String, Festival> sFestivalMap = new LinkedHashMap<>();
	private Map<String, Festival> lFestivalMap = new LinkedHashMap<>();
	private Map<String, Festival> stFestivalMap = new LinkedHashMap<>();
	private Map<String, Festival> ssFestivalMap = new LinkedHashMap<>();

	private void addSolarFestival(Festival festival, Map<String, Festival> festivalMap) {
		int m = festival.getMonth();
		int d = festival.getDay();
		if (m <= 0 || d <= 0)
			return;
		int delay = festival.getDelay();
		if (delay <= 0)
			delay = 1;

		d--;
		for (int i = 0; i < delay; i++) {
			d++;
			if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
				if (d > 31) {
					m++;
					d = 1;
				}
			} else if (m == 2) {
				if (d > 28) {
					m++;
					d = 1;
					festivalMap.put("2/29" + d, festival);
				}
			} else {
				if (d > 30) {
					m++;
					d = 1;
				}
			}
			festivalMap.put(m + "/" + d, festival);
		}
	}

	private void addLunarFestival(Festival festival, Map<String, Festival> festivalMap) {
		int m = festival.getMonth();
		int d = festival.getDay();
		if (m <= 0 || d <= 0)
			return;

		int delay = festival.getDelay();
		if (delay <= 0)
			delay = 1;

		d--;
		for (int i = 0; i < delay; i++) {
			d++;
			if (d > 29) {
				m++;
				d = 1;
				festivalMap.put("2/30" + d, festival);
			}
			festivalMap.put(m + "/" + d, festival);
		}
	}
	
	public boolean afterInitialize() {

		for (Festival festival : messageProperties.getSolar()) {
			addSolarFestival(festival, stFestivalMap);
		}

		for (Festival festival : messageProperties.getFestival()) {
			addSolarFestival(festival, sFestivalMap);
		}

		for (Festival festival : messageProperties.getLfestival()) {
			addLunarFestival(festival, lFestivalMap);
		}

		for (Festival festival : messageProperties.getSeason()) {
			ssFestivalMap.put(festival.getName(), festival);
		}
		return true;
	};

	public String getOneFestivalMessage() {
		return getOneFestivalMessage(Calendar.getInstance());
	}

	public String getOneFestivalMessage(Calendar calendar) {
		Festival festival = getFestival(calendar);
		if (festival != null) {
			List<String> contents = festival.getContent();
			int size = contents.size();
			if (size == 1) {
				return contents.get(0);
			} else if (size > 1) {
				return contents.get(new Random().nextInt(size));
			}
		}

		return null;
	}

	public Festival getFestival(Calendar calendar) {
		Festival festival = null;

		/*
		 * 先查公历节日，没有再查农历节日，没有再查节气，都没就季节
		 */
		int sMonth = calendar.get(Calendar.MONTH) + 1;
		int sDay = calendar.get(Calendar.DAY_OF_MONTH);

		String sKey = sMonth + "/" + sDay;
		festival = sFestivalMap.get(sKey);

		if (festival == null) {
			Lunar lunar = new Lunar();
			int lMonth = lunar.getLunarMonth();
			int lDay = lunar.getLunarDay();

			String lKey = lMonth + "/" + lDay;
			festival = lFestivalMap.get(lKey);

			if (festival == null) {
				festival = stFestivalMap.get(sKey);

				if (festival == null) {
					String season = null;
					if (sMonth == 3 || sMonth == 4 || sMonth == 5) {
						season = "春季";
					} else if (sMonth == 6 || sMonth == 7 || sMonth == 8) {
						season = "夏季";
					} else if (sMonth == 9 || sMonth == 10 || sMonth == 11) {
						season = "秋季";
					} else {
						season = "冬季";
					}
					festival = ssFestivalMap.get(season);
				}
			}
		}
		return festival;
	}

}
