package app.controller.versioning_api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/version")
public class VersionController {

	@GetMapping("/hw")
	public String hw(){
		return "HW";
	}
	
	@GetMapping("/hwt")
	public String hwt(){
		return "HWT";
	}
}
