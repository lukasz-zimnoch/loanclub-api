package org.loanmeterserver.domain.client;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.loanmeterserver.domain.base.BaseAggregateRoot;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class Client extends BaseAggregateRoot {

	private final String firstName;

	private final String secondName;
}
