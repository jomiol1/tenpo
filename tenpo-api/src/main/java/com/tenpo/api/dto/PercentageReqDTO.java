package com.tenpo.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PercentageReqDTO {
	
	@NotNull
	@Min(1)
	Double number1;
	
	@NotNull
	@Min(1)
	Double number2;

}
