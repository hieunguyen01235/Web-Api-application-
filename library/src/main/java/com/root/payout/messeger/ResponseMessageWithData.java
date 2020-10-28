package com.root.payout.messeger;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessageWithData {
	private String message;
	private Date timetamp;
	private Object data;
}
