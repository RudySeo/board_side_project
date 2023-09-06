package sideproject.board.common;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommonResponseEntity<T> {
	private boolean result;
	private T data;
	private HttpStatus status = HttpStatus.OK;

	@Builder
	public CommonResponseEntity(boolean result, T data, HttpStatus status) {
		this.result = result;
		this.data = data;
		this.status = status;
	}
}
