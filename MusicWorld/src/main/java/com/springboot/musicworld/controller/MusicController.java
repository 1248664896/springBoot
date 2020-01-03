package com.springboot.musicworld.controller;

import com.springboot.musicworld.common.Constants;
import com.springboot.musicworld.entity.MusicPicture;
import com.springboot.musicworld.entity.Musics;
import com.springboot.musicworld.entity.MusicsType;
import com.springboot.musicworld.service.MusicPictureService;
import com.springboot.musicworld.service.MusicsService;
import com.springboot.musicworld.service.MusicsTypeService;
import com.springboot.musicworld.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/music")
public class MusicController {


    @Autowired
    private MusicsService musicsService ;

    @Autowired
    private MusicsTypeService musicsTypeService;

    @Autowired
    private MusicPictureService musicPictureService;

    @RequestMapping("/musicManage")
    public String musicManage(HttpServletRequest request){
        request.setAttribute("request","musicManage");
        return "/admin/musicManage";
    }

    /**
     * 音乐信息
     * @param request
     * @return
     */
    @GetMapping("/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("path", "edit");
        //查询所有的一级分类
       // List<GoodsCategory> firstLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(0L), NewBeeMallCategoryLevelEnum.LEVEL_ONE.getLevel());
//        if (!CollectionUtils.isEmpty(firstLevelCategories)) {
//            //查询一级分类列表中第一个实体的所有二级分类
//            List<GoodsCategory> secondLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(firstLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_TWO.getLevel());
//            if (!CollectionUtils.isEmpty(secondLevelCategories)) {
//                //查询二级分类列表中第一个实体的所有三级分类
//                List<GoodsCategory> thirdLevelCategories = newBeeMallCategoryService.selectByLevelAndParentIdsAndNumber(Collections.singletonList(secondLevelCategories.get(0).getCategoryId()), NewBeeMallCategoryLevelEnum.LEVEL_THREE.getLevel());
//                request.setAttribute("firstLevelCategories", firstLevelCategories);
//                request.setAttribute("secondLevelCategories", secondLevelCategories);
//                request.setAttribute("thirdLevelCategories", thirdLevelCategories);
                List<MusicsType> listA  =  musicsTypeService.musicsTypeList();
                request.setAttribute("listA",listA);
                request.setAttribute("path", "goods-edit");
                return "/admin/musicEdit";
            }
//        }
//        return "error/error_5xx";
//    }


    /**
     * 订单页面
     * @param request
     * @return
     */
    @GetMapping("/orders")
    public String ordersPage(HttpServletRequest request) {
        request.setAttribute("path", "orders");
        return "/admin/music_order";
    }

    /**
     * 跳转
     * @param request
     * @param categoryLevel
     * @param parentId
     * @param backParentId
     * @return
     */
    @GetMapping("/categories")
    public String categoriesPage(HttpServletRequest request, @RequestParam("categoryLevel") Byte categoryLevel, @RequestParam("parentId") Long parentId, @RequestParam("backParentId") Long backParentId) {
        if (categoryLevel == null || categoryLevel < 1 || categoryLevel > 3) {
            return "error/error_5xx";
        }
        request.setAttribute("path", "newbee_mall_category");
        request.setAttribute("parentId", parentId);
        request.setAttribute("backParentId", backParentId);
        request.setAttribute("categoryLevel", categoryLevel);
        return "admin/newbee_mall_category";
    }


    /**
     * 单个文件上传
     * 添加音乐
     * @param file
     * @return
     */
    @RequestMapping("/addMusicA")
    @ResponseBody
    public String addMusicA(@RequestParam("file") MultipartFile file,@RequestParam("status") String status, @RequestParam("type") String type, @RequestParam("info") String  info, @RequestParam("name") String name, HttpServletRequest request) throws IOException {
       String id = (String) request.getSession().getAttribute("id");
        String UUid = UUID.randomUUID().toString();

        if(file.isEmpty()){
            return "文件为空";
        }else{


//           if (!filename.toLowerCase().endsWith(".jpg")) {
//                error("上传图片的扩展名必须是jpg", request, response);//error是吧错误信息转发的jsp页面的方法
//                return;
//            }
//


            //获取文件名
            String fileName =   file.getOriginalFilename();
          //  System.out.println("文件名--------------->"+fileName);

            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
           // System.out.println("文件后缀名--------------->"+suffixName);

            // 设置文件存储路径
            String path ="D:\\musicWorld\\newFile\\";
            String filePathName =  "D:\\musicWorld\\sourceFile\\";             //路径一
            String newFilePathName = path ;         //路径二


            String filePath =  filePathName + fileName ;
            File fileA = new File(filePath);//把原件存放在String filePathName = "D:\\musicWorld\\Downloads\\";

            FileOutputStream fos = null;
            //重命名文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");//时间格式 (日期格式化)
            String strDate = sdf.format(new Date());
            String newFileName =  UUid + strDate ;
            newFilePathName =  newFilePathName+ newFileName + suffixName ;
            fos = new FileOutputStream(newFilePathName);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
           // File fileB = new File(newFilePathName);

           Long length = fileA.length();
           Long Size = file.getSize();


//            System.out.println("文件长度"+"---->"+length +"-----"+"大小"+"----->"+Size+"文件名--------------->"+fileName+"文件后缀名--------------->"+suffixName+"新文件名" //+newFileName
//            );

            // 检测是否存在目录renameTo
            if(!fileA.getParentFile().exists()){
                fileA.getParentFile().mkdirs();// 新建文件夹

                return "失败";
            }else{

                 file.transferTo(fileA);
               // file.transferTo((Path) fos);
                //把信息入库
                Musics musics = new Musics();
                musics.setId(UUid);
                musics.setName(name);
                musics.setFileName(fileName);
                musics.setInfo(info);
                musics.setType(type);
                //musics.setSize(Size);
                musics.setNewFileName(newFileName+suffixName);
                musics.setAddDateTime(new Date());
                if(status.equals("1")){
                    musics.setStatus(Constants.Putaway);
                }else{
                    musics.setStatus(Constants.UnShelve);
                }
               boolean result =  musicsService.addMusicA(musics);
               if(result){
                   return "成功";

               }else {
                   return "添加失败";
               }
            }
        }
    }


    /**
     * 多文件上传
     * @param request
     * @return
     */
    @RequestMapping("/addMusicB")
    public String addMusicB(HttpServletRequest request){
        List<MultipartFile> files =( (MultipartHttpServletRequest) request).getFiles("") ;
        //List<Musics> files =( (MultipartHttpServletRequest) request).getFiles("") ;
        MultipartFile file = null;
        BufferedOutputStream stream =null ;
        for(int i =0;i<files.size();i++){ //1、++i是先改变i的值即加1后再使用i的值；而i++是先使用i的值在改变它的值即加。2、for循环内部仅形式不同：当i++循环和++i循环在for循环内部，虽然形式上明显不同，但输出结果可
            file = files.get(i);

            // 获取文件名
            String fileName = file.getOriginalFilename();
            String filePathName = "D:/musicWorld/Downloads";
            String path = filePathName + filePathName ;
            File fileB = new File(path);
            // 检测是否存在目录
            if (!fileB.getParentFile().exists()) {
                fileB.getParentFile().mkdirs();// 新建文件夹
            }
            if(!file.isEmpty()){
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(
                            new FileOutputStream(new File(filePathName+ file.getOriginalFilename()))  // 设置文件路径及名字

                    );
                    stream.write(bytes);// 写入
                    stream.close();


                } catch (IOException e) {
                    stream = null ;
                    e.printStackTrace();
                    return  "第" + i +"个文件上传失败===>" +e.getMessage();
                }
            }else{
                return "第 " + i + " 个文件上传失败因为文件为空";
            }

        }


        return "成功";
    }


    // 文件下载
    @GetMapping("/downloadfile")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "1111.txt";// 文件名
        if (fileName != null) {
            // 设置文件路径
            File file = new File("D:/Test/Downloads/1111.txt");
            // File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    //log.info("下载"+fileName+"成功");
                    System.out.println("下载"+fileName+"成功");
                    return "下载成功";
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return "下载失败";
    }


    @RequestMapping("/musicsList")
    //@ResponseBody
     public String musicsList(HttpServletRequest request){
        List<Musics> list =  musicsService.musicsList();
        request.setAttribute("list",list);
        System.out.println("列表---------------->"+list);
        return "/admin/musicManage";
     }


    @RequestMapping("/paging/MusicsList")
    @ResponseBody
    public Result findMusicsList(HttpServletRequest request, @RequestParam Map<String , Object> params){
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageResult list =  musicsService.findMusicsList(pageQueryUtil);
        //request.setAttribute("list",list);
        System.out.println("列表---------------->"+list);
        return ResultGenerator.genSuccessResult(list);
    }




    @RequestMapping("/goAddMusicsType")
    public String goAddMusicsType(){

        return "";
    }


    /**
     * add 音乐类型
     * @param name
     * @return
     */
     @RequestMapping("/addMusicsType")
     @ResponseBody
     public boolean addMusicsType(@RequestParam("name") String name ){
        System.out.println("name----------------->"+name);
         MusicsType musicsType = new MusicsType();
         musicsType.setId(PublicUtil.getUUID());
         musicsType.setName(name);
        boolean result =  musicsTypeService.addMusicsType(musicsType);
        if(result)
            return true;
        return false;
     }




//TODO
    @RequestMapping("/uploadToFile")
    @ResponseBody
    public String uploadToUser(@RequestParam("file") MultipartFile file, HttpServletRequest req, Model model) {
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        // 获取文件存放地址
        String dataPath = "";
        String filePath = dataPath;
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();// 不存在路径则进行创建
        }
        FileOutputStream out = null;
        try {
            // 重新自定义文件的名称
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String d = sdf.format(date);// 时间
            filePath = filePath + d + fileName;
            out = new FileOutputStream(filePath);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            return "error";
        }
        return filePath; // 返回文件地址
    }


    /**
     * 图片上传
     * @param file
     * @param model
     * @return
     */
    @RequestMapping("/musicImgUpload")
    public String  musicImgUpload(@RequestParam("file") MultipartFile file, Model model, @RequestParam("musicsId") String musicsId, MusicPicture musicPicture){
        String uuid = UUID.randomUUID().toString();
        if(file.isEmpty()){
            return "文件不能为空";
        }else{
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取后缀名
            String filePath = Constants.MUSIC_IMG_FILE_PATH;//文件上传路径
            fileName = uuid + suffixName;
            File files = new File(filePath+fileName);
            if(!files.getParentFile().exists()){

            }
            try {
                file.transferTo(files);
                //图片信息入库
                musicPicture.setId(uuid);
                musicPicture.setMusicsId(musicsId);
                musicPicture.setName(uuid);
                musicPictureService.addMusicPicture(musicPicture);
                //网站例子  https://blog.csdn.net/qq_38762237/article/details/81282444
                String imgFileName = "/musicWorld/imgFile/" + fileName;
                model.addAttribute("imgFileName",imgFileName);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "file";
        }
    }


    /**
     * 上架
     * @param id
     * @return
     */
    @RequestMapping("/musicPutaway")
    @ResponseBody
    public boolean musicPutaway(@RequestParam("ids") String id ){
        System.out.println("id----------------->"+id);
        boolean result = musicsService.musicPutaway(id);
        if(result){
            return true;
        }
        return false;
    }


    /**
     * 下架
     * @param id
     * @return
     */
    @RequestMapping("/musicUnShelve")
    @ResponseBody
    public boolean musicUnShelve(@RequestParam("ids") String id , @RequestBody String[] ids){
        System.out.println("id----------------->"+id);
        boolean result = musicsService.musicUnShelve(ids);
        if(result){
            return true;
        }
        return false;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/musicDelete")
    @ResponseBody
    public boolean musicDelete(@RequestParam("ids") String id){
        System.out.println("id----------------->"+id);
        boolean result = musicsService.musicDelete(id);
        if(result){
            return true;
        }
        return false;
    }


    @RequestMapping("/listA")
    public String listA(Model model){
        List<Musics> list = musicsService.musicsList();
        model.addAttribute("list",list);
        return "/index/index";
    }


}
