package com.innowise.accounting.mapper;

import java.sql.ResultSet;
import java.util.Optional;

public interface RowMapper<T> {
    Optional<T> mapRow(ResultSet set);
}
