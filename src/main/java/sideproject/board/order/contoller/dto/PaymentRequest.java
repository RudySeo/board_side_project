package sideproject.board.order.contoller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequest {

	private Long productId;

	private Long price;

	private boolean stock;

}
