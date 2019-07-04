package org.loanmeterserver.domain.client;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.loanmeterserver.domain.base.BaseAggregateRoot;

@Getter
public class Client extends BaseAggregateRoot {

	private final String firstName;

	private final String secondName;

	public Client(String firstName, String secondName) {
		Preconditions.checkArgument(StringUtils.isNotBlank(firstName) && firstName.length() <= 20,
				"Invalid first name");
		Preconditions.checkArgument(StringUtils.isNotBlank(secondName) && secondName.length() <= 20,
				"Invalid second name");
		this.firstName = firstName;
		this.secondName = secondName;
	}
}
