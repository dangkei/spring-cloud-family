package cn.dangkei.fraudverifier.user;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.dangkei.fraudverifier.VerificationResult;

@RestController
@RequestMapping("/users")
public class UserRegistrationVerificationController {
	private  final UserRegistrationVerificationService userService;

	public UserRegistrationVerificationController(UserRegistrationVerificationService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/verify")
	ResponseEntity<VerificationResult> verifyUser(@RequestParam("uuid") UUID uuid,
			@RequestParam("age") int age){
		VerificationResult result = userService.verifierUser(uuid, age);
		return ResponseEntity.ok(result);
	}
}
