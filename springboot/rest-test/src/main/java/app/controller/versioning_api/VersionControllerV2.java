package app.controller.versioning_api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/version")
public class VersionControllerV2 extends VersionController {
	@GetMapping("/hw")
	public String hw(){
		return "HW - v2";
	}
}
