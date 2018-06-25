package com.flyer.controller;


import com.flyer.PageInfo;
import com.flyer.domain.Customer;
import com.flyer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(value = "cumstomer")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @ResponseBody
    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    public Object list(PageInfo pageInfo){
        Map<String, Object> result = new HashMap<String, Object>();
        PageInfo<Customer> pageInfo1 = customerService.list(pageInfo);
        result.put("rows", pageInfo1.getRows());
        result.put("total", pageInfo1.getCount());
        return  result;
    }


    @ResponseBody
    @RequestMapping(value = "/saveorUpdate", method = RequestMethod.POST)
    public String saveorUpdate(Customer customer){
       Integer re =  customerService.saveorUpdate(customer);
        return "1";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public String delete(Integer id){
          customerService.delete(id);
          return "success";
    }

    @ResponseBody
    @RequestMapping("/uploadImg.do")
    public Object uploadPicture(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();
        File targetFile=null;
        String msg="";//返回存储路径
        int code=1;
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){
            String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/upload/";//存储路径
//            String returnUrl = "f:/upload/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("upload/"); //文件存储位置
//            String path =  "f:/upload/"; //文件存储位置
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            //先判断文件是否存在
            String fileAdd = sdf.format(new Date());
            File file1 =new File(path+"/"+fileAdd);
            //如果文件夹不存在则创建
            if(!file1 .exists()  && !file1 .isDirectory()){
                file1 .mkdir();
            }
            targetFile = new File(file1, fileName);
//          targetFile = new File(path, fileName);
            try {
                file.transferTo(targetFile);
//              msg=returnUrl+fileName;
                msg=returnUrl+fileAdd+"/"+fileName;
                code=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        result.put("code","0");
        result.put("message",msg);
        return result;
    }
}


