package com.alaaturki.novadesk.dto.dashboard;


import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DashboardStatsResponse {


    private long totalUsers;


    private long adminCount;


    private long userCount;


    private long totalTickets;

}