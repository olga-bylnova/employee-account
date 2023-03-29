package com.innowise.accounting.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class IdParser {
    public static long parseId(String queryString) {
        String[] queryParts = queryString.split("/");
        return Long.parseLong(queryParts[queryParts.length-1]);
    }
}
