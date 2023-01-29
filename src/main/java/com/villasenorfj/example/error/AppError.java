package com.villasenorfj.example.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AppError {
	/** Message. */
	private String message;
	/** Status. */
	private Integer status;
}
