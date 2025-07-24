package com.maghrebia.Consulting.feign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceUsageDto implements Serializable {
    private String serviceName;
    private int usageCount;
    private int lastUsedDaysAgo;
}