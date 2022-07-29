package com.fth.controller;

import com.fth.pojo.ProductInfo;
import com.fth.pojo.vo.ProductInfoVo;
import com.fth.service.ProductInfoService;
import com.fth.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.windows.WPrinterJob;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author FengTianhao
 * @date 2022/7/25 20:37
 */
@Controller
@RequestMapping("/prod")
public class ProductInfoAction {

    public static final int PAGE_SIZE = 5;
    String saveFileName="";

    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping("/getall")
    public String  getAll(HttpServletRequest request)
    {
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    @RequestMapping("/split")
    public String split(HttpServletRequest request)
    {
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("prodVo");
        System.out.println("---------------------split"+vo);

        if(vo!=null)
        {
            info = productInfoService.splitPageVo((ProductInfoVo)vo,PAGE_SIZE);
            request.getSession().removeAttribute("prodVo");
        }
        else
        {
            info = productInfoService.splitPage(1,PAGE_SIZE);

        }
        request.setAttribute("info",info);
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(ProductInfoVo vo, HttpSession session)
    {
        PageInfo info =productInfoService.splitPageVo(vo,PAGE_SIZE);
        session.setAttribute("info",info);
    }

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request)
    {
        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/image_big");

        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);
        System.out.println(saveFileName);
        return object.toString();

    }


    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request)
    {
        System.out.println("save");
        System.out.println(info);
        System.out.println(saveFileName);
        System.out.println("pimage"+info.getpImage());
        info.setpImage(saveFileName);
        System.out.println("pimage"+info.getpImage());

        info.setpDate(new Date());
        int num= -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num > 0)
        {
            request.setAttribute("msg","增加成功");
        }
        else
        {
            request.setAttribute("msg","增加失败");
        }
        saveFileName = "";
        return "forward:/prod/split.action";

    }
    @RequestMapping("/one")
    public String one(int pid, ProductInfoVo vo, Model model,HttpSession session)
    {
        ProductInfo info = productInfoService.getById(pid);
//        将多条件以及页码放在session
        System.out.println("one------------"+vo);
        session.setAttribute("prodVo",vo);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info, HttpServletRequest request)
    {
        System.out.println("update"+info);
        if(!saveFileName.equals(""))
        {
            info.setpImage(saveFileName);
        }
        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0)
        {
            request.setAttribute("msg","更新成");
        }
        else
        {
            request.setAttribute("msg","跟新新失败");
        }
        saveFileName = "";
        return "forward:/prod/split.action";
    }

    @RequestMapping("/delete")
    public String delete(int pid,ProductInfoVo vo,HttpServletRequest request)
    {
        System.out.println("delete");
        int num =-1;
        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(num);
        if(num>0)
        {
            request.setAttribute("msg","删除成功");
            request.getSession().setAttribute("deleteProdVo",vo);
        }
        else
        {
            request.setAttribute("msg","删除失败");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=UTF-8")
    public  Object deleteAjaxSplit(HttpServletRequest request)
    {
        PageInfo info = null;
        Object vo = request.getSession().getAttribute("deleteProdVo");
        if(vo!=null)
        {
            info = productInfoService.splitPageVo((ProductInfoVo) vo,PAGE_SIZE);

        }
        else
        {
            info = productInfoService.splitPage(1,PAGE_SIZE);

        }
        request.getSession().setAttribute("info",info);
        System.out.println("deleteAjaxSplit"+request.getAttribute("msg"));
        return request.getAttribute("msg");
    }

    @RequestMapping("/deleteBatch")
    public  String deleteBatch(String pids, HttpServletRequest request)
    {
        String[] ids = pids.split(",");

        try {
            int num = productInfoService.deleteBatch(ids);
            System.out.println("deleteBatch"+num);
            if (num>0)
            {
                request.setAttribute("msg","删除成功");
            }
            else
            {
                request.setAttribute("msg","删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","不可删除");
        }
        System.out.println(request.getAttribute("msg"));
        return "forward:/prod/deleteAjaxSplit.action";
    }

//    d多条件查询

    @ResponseBody
    @RequestMapping("/condition")
    public void condition(ProductInfoVo vo, HttpSession session)
    {
        System.out.println("typeId"+vo.getTypeId());
        System.out.println("pName"+vo.getpName());
        System.out.println("lPrice"+vo.getlPrice());
        System.out.println("hPrice"+vo.gethPrice());
        List<ProductInfo> list = productInfoService.selectCondition(vo);
        session.setAttribute("list",list);
    }

}
