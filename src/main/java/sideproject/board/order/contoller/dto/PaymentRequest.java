package sideproject.board.order.contoller.dto;

import lombok.Data;

@Data
public class PaymentRequest {

	private Long productId;

	private Long price;

	private boolean stock;

}
