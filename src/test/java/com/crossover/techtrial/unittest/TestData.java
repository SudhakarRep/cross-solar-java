package com.crossover.techtrial.unittest;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.techtrial.model.HourlyElectricity;
import com.crossover.techtrial.model.Panel;
import com.crossover.techtrial.service.HourlyElectricityService;
import com.crossover.techtrial.service.PanelService;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@SpringBootTest
public class TestData extends CrossSolarApplicationTests {
	
	@Autowired
	private PanelService panelService;

	@Autowired
	private HourlyElectricityService hourlyElectricityService;
	
	@Test
	public void getSerial() {
		
		Random rand = new Random(); 
 		int value = rand.nextInt(5544); 
 		
		Panel panel = new Panel();
		panel.setBrand("Brand1"+value);
		panel.setLatitude(Double.parseDouble("2345"+value));
		panel.setLongitude(Double.parseDouble("5678"+value));
		panel.setSerial("Serial1"+value);
		panelService.register(panel);
		
		Panel panelData = panelService.findBySerial("Serial1"+value);
		assertEquals(panel.getSerial(), panelData.getSerial());
	}

	@Test
	public void saveHourlyData() {
		
		Panel panelData = panelService.findBySerial("SCED1234");
		
		HourlyElectricity hourlyElectricity = new HourlyElectricity();
		hourlyElectricity.setGeneratedElectricity(Long.parseLong("1234"));
		hourlyElectricity.setPanel(panelData);
		hourlyElectricity.setReadingAt(LocalDateTime.now());
		hourlyElectricityService.save(hourlyElectricity);
		
		Assert.assertNotNull(hourlyElectricityService);
	}

	
}
