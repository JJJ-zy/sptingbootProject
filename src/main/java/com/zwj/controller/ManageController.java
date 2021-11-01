package com.zwj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwj.bean.Company;
import com.zwj.bean.Kind;
import com.zwj.bean.Product;
import com.zwj.bean.User;
import com.zwj.service.CompanyService;
import com.zwj.service.KindService;
import com.zwj.service.ProductService;
import com.zwj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

/**
 * @author zwj
 * @date 2021/10/28 - 18:01
 */
@Controller
public class ManageController {

    @Autowired
    ProductService productService;

    @Autowired
    KindService kindService;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;


    @RequestMapping("/login")
    public String toLogin(){
        return "manage/login";
    }

    @PostMapping("/toIndex")
    public String toIndex(@RequestParam("username") String username, @RequestParam("password") String password){
        List<User> users = userService.getBaseMapper().selectList(null);
        for (User user : users) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)){
                return "redirect:manage/index.html";
            }
        }

        return "redirect:login";
    }
    @GetMapping("/manage/index.html")
    public String Index(HttpSession session){
        List<Company> companies = companyService.getBaseMapper().selectList(null);
        List<Kind> manageKinds = kindService.getBaseMapper().selectList(null);
        session.setAttribute("company",companies.get(0));
        session.setAttribute("manageKinds",manageKinds);
        return "manage/index";
    }


    //获取商品信息
    @GetMapping("/optionPro")
    @ResponseBody
    public String goodsData() {
        ObjectMapper om = new ObjectMapper();
        List<Product> list = productService.list();
        for (Product product : list) {
            QueryWrapper<Kind> wrapper = new QueryWrapper<>();
            QueryWrapper<Kind> pro_num = wrapper.eq("pro_num", product.getProKind());
            String proKind = kindService.getOne(pro_num).getProKind();
            product.setProKind(proKind);
        }
        try {
            String s = om.writeValueAsString(list);
            return s;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加商品
    @PostMapping("optionGood")
    @ResponseBody
    public boolean addGoodData(Product product, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        //获取文件名
        String filename = file.getOriginalFilename();
        System.out.println("文件名:" + filename);
        if (!"".equals(filename)) {
            File realPath = new File("/data/upload");
            if (!realPath.exists()) {
                realPath.mkdir();
            }
            InputStream is = file.getInputStream();
            OutputStream os = new FileOutputStream(new File(realPath,filename));

            //读取写出
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                os.flush();
            }
            os.close();
            is.close();
            product.setProImg(filename);
        }
        product.setProTotal(0);
        boolean i = productService.save(product);
        return i;
    }

    //删除商品
    @DeleteMapping("/optionGood")
    @ResponseBody
    public boolean deleteGood(@RequestParam("proId") Integer proId){
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("pro_id",proId);
        Product good = productService.getOne(wrapper);
        File file = new File("/data/upload",good.getProImg());
        file.delete();
        boolean i = productService.remove(wrapper);
        return i;
    }

    //修改商品
    @PutMapping("optionGood")
    @ResponseBody
    public boolean updateGoodData(Product product, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("pro_id",product.getProId());
        Product good = productService.getOne(wrapper);
        File oldFile = new File("/data/upload",good.getProImg());
        oldFile.delete();
        //获取文件名
        String filename = file.getOriginalFilename();
        if (!"".equals(filename)) {
            File realPath = new File("/data/upload");
            System.out.println(realPath.getAbsolutePath());
            if (!realPath.exists()) {
                realPath.mkdir();
            }
            InputStream is = file.getInputStream();
            OutputStream os = new FileOutputStream(new File(realPath,filename));

            //读取写出
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                os.flush();
            }
            os.close();
            is.close();
            product.setProImg(filename);
        }
        product.setProTotal(0);
        boolean i = productService.update(product,wrapper);
        return i;
    }

    @RequestMapping("toDetail")
    public String toDetail(@RequestParam("proId") Integer proId, HttpSession session){
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("pro_id",proId);
        Product product = productService.getOne(wrapper);
        System.out.println(product.getProImg());
        session.setAttribute("pro", product);
        return "manage/detail";
    }

    @RequestMapping("toUpdate")
    public String toUpdate(@RequestParam("proId") Integer proId, HttpSession session){
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("pro_id",proId);
        Product product = productService.getOne(wrapper);
        System.out.println(product.getProImg());
        session.setAttribute("pro", product);
        return "manage/update";
    }

}
