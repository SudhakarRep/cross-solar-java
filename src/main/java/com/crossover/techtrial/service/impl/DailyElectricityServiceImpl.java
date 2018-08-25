package com.crossover.techtrial.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Service;

import com.crossover.techtrial.dto.DailyElectricity;
import com.crossover.techtrial.service.DailyElectricityService;


/**
 * PanelServiceImpl for panel related handling.
 * @author Crossover
 *
 */
@Service
public class DailyElectricityServiceImpl implements DailyElectricityService {

	@PersistenceUnit
    private EntityManagerFactory emf;
    
    @SuppressWarnings("unchecked")
	public List<DailyElectricity> listDailyPanelUsageData(long panelId) {
	        EntityManager em = emf.createEntityManager();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
	        List<Object[]> dailyPanelConsumptionList = em
	                .createQuery("SELECT DATE_FORMAT(he.readingAt,'%Y-%m-%d') AS date, SUM(he.generatedElectricity) AS sum, "
	                		+ " AVG(he.generatedElectricity) AS average, MIN(he.generatedElectricity) AS min, MAX(he.generatedElectricity) AS max  FROM "
	                		+ " HourlyElectricity he, Panel p WHERE he.panel.id=p.id  "
	                		+ " AND p.id="+panelId+" GROUP BY DATE_FORMAT(he.readingAt,'%Y-%m-%d') ")
	                .getResultList();
	        
	        List<DailyElectricity> dailyElectricityList = new ArrayList<DailyElectricity>();
	        for(Object[] dailyObject : dailyPanelConsumptionList) {
	        	DailyElectricity dailyElectricity = new DailyElectricity();
	        	dailyElectricity.setDate(LocalDate.parse(dailyObject[0].toString(),formatter));
	        	dailyElectricity.setSum(Long.parseLong(dailyObject[1].toString()));
	        	dailyElectricity.setAverage(Double.parseDouble(dailyObject[2].toString()));
	        	dailyElectricity.setMin(Long.parseLong(dailyObject[3].toString()));
	        	dailyElectricity.setMax(Long.parseLong(dailyObject[4].toString()));
	        	dailyElectricityList.add(dailyElectricity);
	        }
          
        return dailyElectricityList;
    }
	

}
