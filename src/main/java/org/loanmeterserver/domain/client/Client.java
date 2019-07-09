package org.loanmeterserver.domain.client;

import com.google.common.base.Preconditions;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.loanmeterserver.domain.shared.base.BaseAggregateRoot;

@Getter
public class Client extends BaseAggregateRoot {

	private final String firstName;

	private final String secondName;

	public Client(String firstName, String secondName) {
		Preconditions.checkArgument(StringUtils.isNotBlank(firstName), "First name cannot be blank");
		Preconditions.checkArgument(StringUtils.isNotBlank(secondName),"Second name cannot be blank");
		this.firstName = firstName;
		this.secondName = secondName;
	}
}
