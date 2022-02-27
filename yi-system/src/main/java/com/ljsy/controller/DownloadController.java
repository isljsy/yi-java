package com.ljsy.controller;


import com.ljsy.service.YiWordsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/download")
@PreAuthorize("hasAnyRole('admin')")
public class DownloadController {

    @Value("${linux-path}")
    String linuxPath;

    @Value("${windows-path}")
    String windowPath;


    @Resource
    private YiWordsService service;

    @ApiOperation("小程序后台数据库文件下载")
    @GetMapping("db")
    public void getJSON(HttpServletResponse response) {
        // 修改为小程序导入所用的json格式
        String jsonStr = service.getJSON().replace("},{\"c", "}{\"c");
        String str = jsonStr.substring(1, jsonStr.length()-1);
        // 获得的json字符串转为字节数组
        byte[] bytes = str.getBytes(StandardCharsets.UTF_16);
        // 设置响应头
        setResponse(response, "xcx_db.json", bytes.length);
        try {

            OutputStream responseOS = response.getOutputStream();
            responseOS.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @ApiOperation("小程序拼音和搜索词文件下载")
    @GetMapping("searchWords")
    public void getSearchJS(HttpServletResponse response) {
        // 取文件内容
        byte[] bytes = service.getSearchJS().getBytes(StandardCharsets.UTF_16);
        // 设置响应头
        setResponse(response, "searchWord.js", bytes.length);
        try {
            // 写入响应
            OutputStream responseOS = response.getOutputStream();
            responseOS.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @ApiOperation("小程序各地区辅音分类拼音表文件下载")
    @GetMapping("pinyinSort")
    public void getPinyinJS(HttpServletResponse response) {
        // 转为字节数组
        byte[] bytes = service.getPinyinJS().getBytes(StandardCharsets.UTF_16);
        // 设置响应头
        setResponse(response, "pinyinSort.js", bytes.length);
        try {
            // 写入响应
            OutputStream responseOS = response.getOutputStream();
            responseOS.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("汉语表数据文件下载")
    @GetMapping("hanyuCsv")
    public void getHanyuCsv(HttpServletResponse response) {
        // 转为字节数组
        byte[] bytes = service.getHanyuCsv().getBytes(StandardCharsets.UTF_8);
        // 设置响应头
        setResponse(response, "id_hanyu_key.csv", bytes.length);
        try {
            // 写入响应
            OutputStream responseOS = response.getOutputStream();
            responseOS.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取文件路径
     */
    private String getPath() {
        if (System.getProperty("os.name").startsWith("Win")) {
            return windowPath;
        } else {
            return linuxPath;
        }
    }


    /**
     * 设置响应头为文件下载
     *
     * @param response 响应
     * @param name     文件名
     * @param length   文件长度
     */
    private void setResponse(HttpServletResponse response, String name, long length) {
        // 设置编码
        response.setCharacterEncoding("UTF-8");
        // 设置以附件方式下载, 设置文件名
        response.addHeader("Content-Disposition", "attachment;filename=" + name);
        // 告知浏览器文件大小
        response.addHeader("Content-Length", "" + length);
        // 响应内容类型
        response.setContentType("application/octet-stream");
    }
}
