package com.mazhj.felix.control.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.control.pojo.model.DynamicComponents;
import com.mazhj.felix.control.service.DynamicComponentsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/dynamic-components")
public class DynamicComponentsController extends BaseController {

    private final DynamicComponentsService dynamicComponentsService;


    public DynamicComponentsController(DynamicComponentsService dynamicComponentsService) {
        this.dynamicComponentsService = dynamicComponentsService;
    }

    @GetMapping("/{componentId}")
    public AjaxResult getComponents(@PathVariable String componentId){
        List<DynamicComponents> components = this.dynamicComponentsService.getComponents(componentId);
        return success(components);
    }

    @DeleteMapping("/del/{id}")
    public AjaxResult delComponent(@PathVariable Long id){
        this.dynamicComponentsService.removeComponent(id);
        return success();
    }

    @DeleteMapping("/del")
    public AjaxResult delComponents(@RequestParam("ids[]") List<Long> ids){
        this.dynamicComponentsService.removeComponents(ids);
        return success();
    }

    @PutMapping("/add")
    public AjaxResult addComponent( MultipartFile file,  DynamicComponents dynamicComponents){
        this.dynamicComponentsService.addComponent(file,dynamicComponents);
        dynamicComponents.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return success(dynamicComponents);
    }

}
