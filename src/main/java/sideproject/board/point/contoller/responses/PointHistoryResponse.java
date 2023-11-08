package sideproject.board.point.contoller.responses;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointHistoryResponse {
	
	private String name;
	private int balance;
	private int chargeAmount;
	private LocalDateTime chargeTime = null;

}
