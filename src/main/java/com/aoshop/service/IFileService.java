package com.aoshop.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * author:liuao
 * description:
 * Date: create on 21:56 2017/8/27
 * modify by:
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
