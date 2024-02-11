package org.unisphere.unisphere.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ExceptionStatus {
	NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다"),
	;

	private final int status;
	private final String message;
	private final String reason;

	ExceptionStatus(HttpStatus status, String message) {
		this.status = status.value();
		this.message = message;
		this.reason = status.getReasonPhrase();
	}

	public ControllerException toControllerException() {
		return new ControllerException(this);
	}

	public ServiceException toServiceException() {
		return new ServiceException(this);
	}

	public DomainException toDomainException() {
		return new DomainException(this);
	}
}
