package com.h2csolutions.user.status;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    UP("up"),
    DOWN("down"),
    UNKNOWN("unknown"),
    PENDING("pending"),
    COMPLETED("completed"),
    ACTIVE("active"),
    INACTIVE("inactive"),
    DELETED("deleted");

    private final String value;
}
