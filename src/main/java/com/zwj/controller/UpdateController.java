package com.zwj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zwj.bean.*;
import com.zwj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author zwj
 * @date 2021/10/30 - 17:10
 */
@Controller
public class UpdateController {

    @Autowired
    EditorService editorService;

    @Autowired
    KindService kindService;

    @Autowired
    CompanyService companyService;

    @Autowired
    ImageService imageService;

    @Autowired
    NewsService newsService;

    @Autowired
    TechnologyService technologyService;

    //导航
    @RequestMapping("/mNav")
    public String nav(HttpSession session){
        QueryWrapper<Editor> wrapper = new QueryWrapper<>();
        QueryWrapper<Editor> kind = wrapper.eq("editor_kind", 1);
        List<Editor> editors = editorService.getBaseMapper().selectList(kind);
        session.setAttribute("editors",editors);
        return "manage/nav";
    }

    @RequestMapping("/updateNav")
    public String updateNav(Editor editor,HttpSession session){
        QueryWrapper<Editor> wrapper = new QueryWrapper<>();
        QueryWrapper<Editor> kind = wrapper.eq("id", editor.getId());
        editorService.update(editor, kind);
        QueryWrapper<Editor> wrapper2 = new QueryWrapper<>();
        QueryWrapper<Editor> kind2 = wrapper2.eq("editor_kind", 1);
        List<Editor> editors = editorService.getBaseMapper().selectList(kind2);
        session.setAttribute("editors",editors);
        return "manage/nav";
    }

    //侧栏
    @RequestMapping("/mSide")
    public String side(HttpSession session){
        List<Kind> mKind = kindService.getBaseMapper().selectList(null);
        session.setAttribute("mKind",mKind);
        return "manage/side";
    }

    @RequestMapping("/updateSide")
    public String updateSide(Kind kind,HttpSession session){
        QueryWrapper<Kind> wrapper = new QueryWrapper<>();
        QueryWrapper<Kind> uk = wrapper.eq("id", kind.getId());
        kindService.update(kind,uk);
        List<Kind> mKind = kindService.getBaseMapper().selectList(null);
        session.setAttribute("mKind",mKind);
        return "manage/side";
    }

    @ResponseBody
    @DeleteMapping("/optionKind")
    public boolean deleteKind(@RequestParam("kindId") String kindId){
        QueryWrapper<Kind> wrapper = new QueryWrapper<>();
        QueryWrapper<Kind> rel = wrapper.eq("id", kindId);
        boolean i = kindService.remove(rel);
        return i;
    }

    @ResponseBody
    @GetMapping("/optionKind")
    public int addKind(@RequestParam("proKind") String proKind){
        int count = kindService.count();
        Kind kind = new Kind();
        kind.setProKind(proKind);
        kind.setProNum(String.valueOf(count+1));
        int i = kindService.getBaseMapper().insert(kind);
        return i;
    }

    @RequestMapping("/mCompany")
    public String mCompany(HttpSession session){
        List<Company> companies = companyService.getBaseMapper().selectList(null);
        session.setAttribute("mCompany",companies.get(0));
        session.setAttribute("isError","");
        return "manage/company";
    }

    @RequestMapping("/updateCompany")
    public String updateCom(Company company,HttpSession session){
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        QueryWrapper<Company> cp = wrapper.eq("id", company.getId());
        companyService.update(company,cp);
        List<Company> companies = companyService.getBaseMapper().selectList(null);
        session.setAttribute("mCompany",companies.get(0));
        session.setAttribute("isError","修改成功");
        return "manage/company";
    }

    //banner
    @RequestMapping("/mBanner")
    public String banner(HttpSession session){
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        QueryWrapper<Image> kind = wrapper.eq("img_kind", 1);
        List<Image> banners = imageService.getBaseMapper().selectList(kind);
        session.setAttribute("banners",banners);
        return "manage/banner";
    }

    @PostMapping("/updateBanner")
    public String updateBanner(Image image, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        QueryWrapper<Image> imageId = wrapper.eq("id", image.getId());
        Image img = imageService.getOne(imageId);
        File oldFile = new File("/data/upload/banner",img.getImgName());
        oldFile.delete();
        //获取文件名
        String filename = file.getOriginalFilename();
        if (!"".equals(filename)) {
            File realPath = new File("/data/upload/banner");
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
            image.setImgName(filename);
        }
        imageService.update(image,imageId);
        return "redirect:/mBanner";
    }


    //业绩
    @RequestMapping("/mScore")
    public String score(HttpSession session){
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        QueryWrapper<Image> kind = wrapper.eq("img_kind", 2);
        List<Image> scores = imageService.getBaseMapper().selectList(kind);
        session.setAttribute("scores",scores);
        return "manage/score";
    }

    @PostMapping("/updateScore")
    public String updateScore(Image image, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        QueryWrapper<Image> imageId = wrapper.eq("id", image.getId());
        Image img = imageService.getOne(imageId);
        File oldFile = new File("/data/upload/score",img.getImgName());
        oldFile.delete();
        //获取文件名
        String filename = file.getOriginalFilename();
        if (!"".equals(filename)) {
            File realPath = new File("/data/upload/score");
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
            image.setImgName(filename);
        }
        imageService.update(image,imageId);
        return "redirect:/mScore";
    }

    //荣誉资质
    @RequestMapping("/mHonor")
    public String honor(HttpSession session){
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        QueryWrapper<Image> kind = wrapper.eq("img_kind", 3);
        QueryWrapper<Image> wrapper1 = new QueryWrapper<>();
        QueryWrapper<Image> kind2 = wrapper1.eq("img_kind", 4);
        List<Image> honors = imageService.getBaseMapper().selectList(kind);
        Image image = imageService.getBaseMapper().selectList(kind2).get(0);
        session.setAttribute("honors",honors);
        session.setAttribute("ewm",image);
        return "manage/honor";
    }

    @PostMapping("/updateHonor")
    public String updateHonor(Image image, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        QueryWrapper<Image> imageId = wrapper.eq("id", image.getId());
        Image img = imageService.getOne(imageId);
        File oldFile = new File("/data/upload/honor",img.getImgName());
        oldFile.delete();
        //获取文件名
        String filename = file.getOriginalFilename();
        if (!"".equals(filename)) {
            File realPath = new File("/data/upload/honor");
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
            image.setImgName(filename);
        }
        imageService.update(image,imageId);
        return "redirect:/mHonor";
    }

    //ewm

    @PostMapping("/updateEwm")
    public String updateEwm(Image image, @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        QueryWrapper<Image> imageId = wrapper.eq("id", image.getId());
        Image img = imageService.getOne(imageId);
        File oldFile = new File("/data/upload/ewm",img.getImgName());
        oldFile.delete();
        //获取文件名
        String filename = file.getOriginalFilename();
        if (!"".equals(filename)) {
            File realPath = new File("/data/upload/ewm");
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
            image.setImgName(filename);
        }
        imageService.update(image,imageId);
        return "redirect:/mHonor";
    }

    @RequestMapping("/mNews")
    public String news(HttpSession session){
        List<News> newsList = newsService.getBaseMapper().selectList(null);
        List<Technology> technologyList = technologyService.getBaseMapper().selectList(null);
        session.setAttribute("newsList",newsList);
        session.setAttribute("technologyList",technologyList);
        session.setAttribute("result","");
        return "manage/news";
    }

    @PostMapping("/addArticle")
    public String addArticle(@RequestParam("title") String title,@RequestParam("conKind") String kind,@RequestParam("context") String context,@RequestParam("author") String author,HttpSession session){
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(date);
        if (kind.equals("1")){
            News n = new News();
            n.setNewsTitle(title);
            n.setNewsContext(context);
            n.setNewsDate(d);
            n.setNewsAuthor(author);
            n.setNewsShow(0);
            int i = newsService.getBaseMapper().insert(n);
            if (i==1){
                session.setAttribute("result","添加成功");
            }else {
                session.setAttribute("result","添加失败");
            }
            return "redirect:/mNews";
        }else {
            Technology t = new Technology();
            t.setTecTitle(title);
            t.setTecContext(context);
            t.setTecDate(d);
            t.setTecAuthor(author);
            t.setTecShow(0);
            int i = technologyService.getBaseMapper().insert(t);
            if (i==1){
                session.setAttribute("result","添加成功");
            }else {
                session.setAttribute("result","添加失败");
            }
            return "redirect:/mNews";
        }


    }

    @DeleteMapping("/optionArticle")
    @ResponseBody
    public boolean deleteArticle(@RequestParam("id") int id,@RequestParam("kind") int kind){
        if (kind==1){
            QueryWrapper<News> wrapper = new QueryWrapper<>();
            QueryWrapper<News> n = wrapper.eq("id", id);
            boolean i = newsService.remove(n);
            return i;
        }else {
            QueryWrapper<Technology> w = new QueryWrapper<>();
            QueryWrapper<Technology> t = w.eq("id", id);
            boolean i = technologyService.remove(t);
            return i;
        }
    }

}
