package com.maghrebia.User.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUsageDTO {
    private String serviceName;
    private int usageCount;
    private int lastUsedDaysAgo;
}
