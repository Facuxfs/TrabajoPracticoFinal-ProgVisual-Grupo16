package ar.edu.unju.fi.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SoporteController {
	
	@GetMapping("/soporte")
	public String getSoportePage() {
		return "soporte";
	}
}