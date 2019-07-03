package org.loanmeterserver.domain.client;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

public class ClientFactory {

    public Client create(String firstName, String secondName) {
        Preconditions.checkArgument(StringUtils.isNotBlank(firstName), "First name cannot be blank");
        Preconditions.checkArgument(StringUtils.isNotBlank(secondName), "Second name cannot be blank");
        return new Client(firstName, secondName);
    }
}
