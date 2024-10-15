package com.eventspace.spring.spaceservice.Configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StorageConfiguration {
    private String location = "upload-dir";
}
