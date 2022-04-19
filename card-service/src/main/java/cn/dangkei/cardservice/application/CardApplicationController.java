package cn.dangkei.cardservice.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/application")
public class CardApplicationController {

	private static final Log LOG = LogFactory.getLog(CardApplicationController.class);

	private final CardApplicationService cardApplicationService;

	CardApplicationController(CardApplicationService cardApplicationService) {

		LOG.info("CardApplicationController is initialition !!!");
		this.cardApplicationService = cardApplicationService;
	}

	@PostMapping()
	Mono<ApplicationResult> apply(@RequestBody CardApplicationDto cardApplicationDTO) {

		LOG.info("someone application card!!!");

		return cardApplicationService.registerApplication(cardApplicationDTO);
	}

}
