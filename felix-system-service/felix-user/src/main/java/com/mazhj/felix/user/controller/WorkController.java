package com.mazhj.felix.user.controller;

import com.mazhj.common.minio.service.MinioService;
import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.user.pojo.model.Work;
import com.mazhj.felix.user.service.WorkService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/work")
public class WorkController extends BaseController {

    private final WorkService workService;

    private final MinioService minioService;
    public WorkController(WorkService workService, MinioService minioService) {
        this.workService = workService;
        this.minioService = minioService;
    }

    @GetMapping("/get-works")
    public AjaxResult getWork(String status) {
        List<Work> works = this.workService.getWorksByStatus(status);
        return success(works);
    }

    @PostMapping("/upload-work")
    public AjaxResult uploadWork(Work work, MultipartFile file){
        String url = this.minioService.upload("work", file);
        work.setWorkUrl(url);
        this.workService.createWork(work);
        return success();
    }
}
