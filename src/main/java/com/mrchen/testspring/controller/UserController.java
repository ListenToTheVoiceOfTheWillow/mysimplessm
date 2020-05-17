package com.mrchen.testspring.controller;

import com.mrchen.myspring.mvc.annotation.Controller;
import com.mrchen.myspring.mvc.annotation.RequestMapping;
import com.mrchen.myspring.mvc.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;



@Controller
public class UserController {

  @RequestMapping("query")
  @ResponseBody
  public Map<String,Object> query(Integer id,String name){
     Map<String ,Object> map=new HashMap<>();
     map.put("id",id);
     map.put("name",name);
     return map;
  }
}
