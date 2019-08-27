package org.loanmeterserver.domain.shared.vo;

import com.google.common.base.Preconditions;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

@Value
public class Account {

    private final String username;

    public Account(String username) {
        Preconditions.checkArgument(StringUtils.isNotBlank(username), "Username cannot be blank");
        this.username = username;
    }
}
