package com.ruoyi.web.controller.dir;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dir.domain.DirPinyin;
import com.ruoyi.dir.service.IDirPinyinService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 拼音Controller
 * 
 * @author ljsy
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/dir/pinyin")
public class DirPinyinController extends BaseController
{
    @Autowired
    private IDirPinyinService dirPinyinService;

    /**
     * 查询拼音列表
     */
    @PreAuthorize("@ss.hasPermi('dir:pinyin:list')")
    @GetMapping("/list")
    public TableDataInfo list(DirPinyin dirPinyin)
    {
        startPage();
        List<DirPinyin> list = dirPinyinService.selectDirPinyinList(dirPinyin);
        return getDataTable(list);
    }

    /**
     * 导出拼音列表
     */
    @PreAuthorize("@ss.hasPermi('dir:pinyin:export')")
    @Log(title = "拼音", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DirPinyin dirPinyin)
    {
        List<DirPinyin> list = dirPinyinService.selectDirPinyinList(dirPinyin);
        ExcelUtil<DirPinyin> util = new ExcelUtil<DirPinyin>(DirPinyin.class);
        util.exportExcel(response, list, "拼音数据");
    }

    /**
     * 获取拼音详细信息
     */
    @PreAuthorize("@ss.hasPermi('dir:pinyin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dirPinyinService.selectDirPinyinById(id));
    }

    /**
     * 新增拼音
     */
    @PreAuthorize("@ss.hasPermi('dir:pinyin:add')")
    @Log(title = "拼音", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DirPinyin dirPinyin)
    {
        return toAjax(dirPinyinService.insertDirPinyin(dirPinyin));
    }

    /**
     * 修改拼音
     */
    @PreAuthorize("@ss.hasPermi('dir:pinyin:edit')")
    @Log(title = "拼音", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DirPinyin dirPinyin)
    {
        return toAjax(dirPinyinService.updateDirPinyin(dirPinyin));
    }

    /**
     * 删除拼音
     */
    @PreAuthorize("@ss.hasPermi('dir:pinyin:remove')")
    @Log(title = "拼音", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dirPinyinService.deleteDirPinyinByIds(ids));
    }
}
