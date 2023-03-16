package com.ns.service;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

public interface UploadService {
    Map<String,Object> upload();
}
