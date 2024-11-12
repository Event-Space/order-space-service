package com.eventspace.spring.spaceservice.Configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StorageConfiguration {
    @Value("${storage.directory}")
    private String location = "upload-dir";
}
