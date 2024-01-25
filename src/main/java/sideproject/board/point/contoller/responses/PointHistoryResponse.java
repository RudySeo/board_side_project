package sideproject.board.point.contoller.responses;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointHistoryResponse implements Serializable {

	private String name;
	private int balance;
	private int chargeAmount;


	private LocalDateTime chargeTime;

}
