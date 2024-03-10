package com.mazhj.felix.control.controller;

import com.mazhj.common.web.controller.BaseController;
import com.mazhj.common.web.response.AjaxResult;
import com.mazhj.felix.control.pojo.model.DynamicComponents;
import com.mazhj.felix.control.service.DynamicComponentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DynamicComponentsController extends BaseController {

    private final DynamicComponentsService dynamicComponentsService;


    public DynamicComponentsController(DynamicComponentsService dynamicComponentsService) {
        this.dynamicComponentsService = dynamicComponentsService;
    }

    @GetMapping("/dynamic-components/{componentId}")
    public AjaxResult getComponents(@PathVariable String componentId){
        List<DynamicComponents> components = this.dynamicComponentsService.getComponents(componentId);
        return success(components);
    }

}
