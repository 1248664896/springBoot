package com.springboot.musicworld.controller;

import com.springboot.musicworld.entity.Musics;
import com.springboot.musicworld.service.MusicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private MusicsService musicsService ;

     //@ResponseBody
       @RequestMapping("/")
        public String index(Model model){
        List<Musics> list = musicsService.musicsList();
        model.addAttribute("list",list);
        return "/index/index" ;
    }


//    @ResponseBody
//    @RequestMapping("/indexss")
//    public String indexs(){
//        return "springboot" ;
//    }


    /**
     * 主页轮播图设置
     * @return
     */
    @RequestMapping("/index/carousel")
    public String indexManage(HttpServletRequest request){
        request.setAttribute("path", "index_carousel");
        return "/admin/index_carousel";
    }


    /**
     * 主页栏目设置
     * @return
     */
    @RequestMapping("/admin/index_column")
    public String index_column(HttpServletRequest request){
        request.setAttribute("path", "index_column");
        return "/admin/index_column";
    }




}
