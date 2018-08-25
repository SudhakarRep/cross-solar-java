package com.crossover.techtrial.service;

import java.util.List;

import com.crossover.techtrial.dto.DailyElectricity;

/**
 * PanelService interface for Panels.
 * @author Crossover
 *
 */
public interface DailyElectricityService {
  
	public List<DailyElectricity> listDailyPanelUsageData(long panelId);
}
