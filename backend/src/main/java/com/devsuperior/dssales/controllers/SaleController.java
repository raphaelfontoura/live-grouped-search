package com.devsuperior.dssales.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dssales.dto.SalesByStoreDTO;
import com.devsuperior.dssales.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/by-store")
	public ResponseEntity<List<SalesByStoreDTO>> salesByStore() {
		List<SalesByStoreDTO> list = service.searchByStore();
		return ResponseEntity.ok(list);
	}
}
