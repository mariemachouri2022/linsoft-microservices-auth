package com.maghrebia.Consulting.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "user-service", url = "http://localhost:8023/users")
public interface UserClient {
    @GetMapping("/{id}")
    UserDto getUserById(@PathVariable("id") Long id);

    @GetMapping("/{id}/history")
    List<ServiceUsageDto> getUserHistory(@PathVariable("id") Long id);
}
