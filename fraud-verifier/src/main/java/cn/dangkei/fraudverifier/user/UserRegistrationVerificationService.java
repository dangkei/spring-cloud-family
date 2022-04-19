package cn.dangkei.fraudverifier.user;

import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.dangkei.fraudverifier.VerificationResult;

@Service
public class UserRegistrationVerificationService {

	VerificationResult verifierUser(UUID uuid,int age) {
		if(age<18 ||age>99) {
			return VerificationResult.failed(uuid);
		}
		return VerificationResult.passed(uuid);
	}

}
