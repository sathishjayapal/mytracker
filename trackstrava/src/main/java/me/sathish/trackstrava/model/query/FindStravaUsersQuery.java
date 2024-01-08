package me.sathish.trackstrava.model.query;

public record FindStravaUsersQuery(int pageNo, int pageSize, String sortBy, String sortDir) {}
