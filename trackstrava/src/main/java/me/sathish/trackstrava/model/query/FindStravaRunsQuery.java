package me.sathish.trackstrava.model.query;
public record FindStravaRunsQuery(int pageNo, int pageSize, String sortBy, String sortDir) {}
