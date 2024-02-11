package org.unisphere.unisphere.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(ControllerException.class)
	public ResponseEntity<?> controllerExceptionHandler(ControllerException e) {
		log.info("[ControllerException] {} : {}", e.status.getReason(), e.status.getMessage());
		e.printStackTrace();
		return ResponseEntity
				.status(e.status.getStatus())
				.body(e.status);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<?> serviceExceptionHandler(ServiceException e) {
		log.info("[ServiceException] {} : {}", e.status.getReason(), e.status.getMessage());
		e.printStackTrace();
		return ResponseEntity
				.status(e.status.getStatus())
				.body(e.status);
	}

	@ExceptionHandler(DomainException.class)
	public ResponseEntity<?> domainExceptionHandler(DomainException e) {
		log.warn("[DomainException] {} : {}", e.status.getReason(), e.status.getMessage());
		return ResponseEntity
				.status(e.status.getStatus())
				.body(e.status);
	}
}
