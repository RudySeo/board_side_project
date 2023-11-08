package sideproject.board.point.contoller.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PointHistoryResponse {
	private int balance;
	private int chargeAmount;
}
