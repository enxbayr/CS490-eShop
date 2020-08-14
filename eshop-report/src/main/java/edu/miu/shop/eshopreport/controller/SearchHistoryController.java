package edu.miu.shop.eshopreport.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.miu.shop.eshopreport.domain.SearchHistory;
import edu.miu.shop.eshopreport.service.HistorySearchService;
import edu.miu.shop.eshopreport.service.impl.HistorySearchServiceImpl;
import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/reports/export")
public class SearchHistoryController {

	@Autowired
	HistorySearchServiceImpl hisService;
	
	@GetMapping("/html")
	private String exportReport() throws FileNotFoundException, JRException {
		return hisService.exportReport("html");
	}
	
	
}
