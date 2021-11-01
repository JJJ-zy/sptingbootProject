package com.zwj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwj.bean.*;
import com.zwj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zwj
 * @date 2021/10/13 - 12:05
 */
@Controller
public class ProductController {

    @Autowired
    ProductService service;

    @Autowired
    KindService kindService;

    @Autowired
    EditorService editorService;

    @Autowired
    CompanyService companyService;

    @Autowired
    NewsService newsService;

    @Autowired
    TechnologyService technologyService;

    @Autowired
    ImageService imageService;

    @RequestMapping({"/","/index"})
    public String index(@RequestParam(value = "pn",defaultValue = "1") int pn, HttpSession session){
        Page<Product> page = new Page<>(pn,16);
        //分页查询商品
        IPage<Product> paging = service.getBaseMapper().selectPage(page,null);
        List<Product> records = paging.getRecords();
        Set kinds = new LinkedHashSet();
        //商品种类
        List<Kind> allKinds = kindService.getBaseMapper().selectList(null);
        //导航栏
        QueryWrapper<Editor> headWrapper = new QueryWrapper<>();
        QueryWrapper<Editor> hWrapper = headWrapper.eq("editor_kind", 1);
        List<Editor> hEditor = editorService.getBaseMapper().selectList(hWrapper);
        //footer
        QueryWrapper<Editor> footerWrapper2 = new QueryWrapper<>();
        QueryWrapper<Editor> fWrapper2 = footerWrapper2.eq("editor_kind", 3);
        List<Editor> fEditor2 = editorService.getBaseMapper().selectList(fWrapper2);
        QueryWrapper<Editor> footerWrapper3 = new QueryWrapper<>();
        QueryWrapper<Editor> fWrapper3 = footerWrapper3.eq("editor_kind", 4);
        List<Editor> fEditor3 = editorService.getBaseMapper().selectList(fWrapper3);
        //address
        List<Company> companies = companyService.getBaseMapper().selectList(null);
        for (Product record : records) {
            String kind = record.getProKind();
            QueryWrapper<Kind> wrapper = new QueryWrapper<>();
            QueryWrapper<Kind> num = wrapper.eq("pro_num", kind);
            Kind kindServiceOne = kindService.getOne(num);
            kinds.add(kindServiceOne.getProKind());
        }

        //图片
        List<Image> images = imageService.getBaseMapper().selectList(null);
        Object[] objects = kinds.toArray();
        session.setAttribute("kinds",objects);
        session.setAttribute("products",records);
        session.setAttribute("allKinds",allKinds);
        session.setAttribute("hEditor",hEditor);
        session.setAttribute("fEditor2",fEditor2);
        session.setAttribute("fEditor3",fEditor3);
        session.setAttribute("company",companies.get(0));
        session.setAttribute("images",images);

        return "index";
    }


    @RequestMapping("/company")
    public String intro(){
        return "company";
    }

    @RequestMapping("/product")
    public String product(@RequestParam(value = "pn",defaultValue = "1") int pn, @RequestParam(value = "kind",defaultValue = "0") int kind, HttpSession session){
        Page<Product> page = new Page<>(pn,16);
        if (kind != 0){
            QueryWrapper<Product> wrapper = new QueryWrapper<>();
            QueryWrapper<Product> pro_kind = wrapper.eq("pro_kind", kind);
            IPage<Product> paging = service.getBaseMapper().selectPage(page,pro_kind);
            List<Product> records = paging.getRecords();
            QueryWrapper<Kind> wrapper2 = new QueryWrapper<>();
            QueryWrapper<Kind> pro_num = wrapper2.eq("pro_num", kind);
            Kind kindTitle = kindService.getOne(pro_num);
            session.setAttribute("products2",records);
            session.setAttribute("pn",pn);
            session.setAttribute("pages",paging.getPages());
            session.setAttribute("kindTitle",kindTitle);
        }else {
            IPage<Product> paging = service.getBaseMapper().selectPage(page,null);
            List<Product> records = paging.getRecords();
            session.setAttribute("products2",records);
            session.setAttribute("pn",pn);
            session.setAttribute("pages",paging.getPages());
            session.setAttribute("kindTitle",new Kind(0,"产品中心","0"));
        }
        return "product";
    }

    //详情
    @RequestMapping("/details")
    public String detail(@RequestParam("proId") int proId, HttpSession session){
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        QueryWrapper<Product> pro = wrapper.eq("pro_id", proId);
        Product product = service.getOne(pro);
        Product product1 = product;
        int total = Integer.valueOf(product.getProTotal())+1;
        product1.setProTotal(total);
        service.update(product1,wrapper);
        session.setAttribute("product",product);
        return "detail";
    }

    //业绩
    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    //联系我们
    @RequestMapping("/target")
    public String target(){
        return "target";
    }

    //新闻
    @RequestMapping("/news")
    public String news(HttpSession session){
        List<News> news = newsService.getBaseMapper().selectList(null);
        session.setAttribute("news",news);
        return "news";
    }

    @RequestMapping("/newsDetail")
    public String newsDetail(@RequestParam("id") int id,HttpSession session){
        QueryWrapper<News> wrapper = new QueryWrapper<>();
        QueryWrapper<News> newId = wrapper.eq("id", id);
        News news = newsService.getOne(newId);
        News newsDetail = news;
        newsDetail.setNewsShow(news.getNewsShow()+1);
        session.setAttribute("newsDetail",newsDetail);
        newsService.update(newsDetail,newId);
        return "newsDetail";
    }

    //技术支持
    @RequestMapping("/technology")
    public String technology(HttpSession session){
        List<Technology> technologies = technologyService.getBaseMapper().selectList(null);
        session.setAttribute("tecs",technologies);
        return "technology";
    }

    @RequestMapping("/tecDetail")
    public String tecDetail(@RequestParam("id") int id,HttpSession session){
        QueryWrapper<Technology> wrapper = new QueryWrapper<>();
        QueryWrapper<Technology> tecId = wrapper.eq("id", id);
        Technology tec = technologyService.getOne(tecId);
        Technology technology = tec;
        technology.setTecShow(tec.getTecShow()+1);
        session.setAttribute("technology",technology);
        technologyService.update(technology,tecId);
        return "tecDetail";
    }

    //荣誉资质
    @RequestMapping("/honor")
    public String honor(){
        return "honor";
    }
}
