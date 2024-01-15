package me.sathish.trackgarmin.model.query;

public record FindGarminRunsQuery(int pageNo, int pageSize, String sortBy, String sortDir) {}
