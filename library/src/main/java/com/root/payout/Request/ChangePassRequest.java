package com.root.payout.Request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassRequest {
	private String oldPassword;
	private String newPassword;
	private String reNewPassword;
}
