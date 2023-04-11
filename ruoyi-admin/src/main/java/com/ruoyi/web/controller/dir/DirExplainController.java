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
import com.ruoyi.dir.domain.DirExplain;
import com.ruoyi.dir.service.IDirExplainService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 汉语释义Controller
 * 
 * @author ljsy
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/system/explain")
public class DirExplainController extends BaseController
{
    @Autowired
    private IDirExplainService dirExplainService;

    /**
     * 查询汉语释义列表
     */
    @PreAuthorize("@ss.hasPermi('system:explain:list')")
    @GetMapping("/list")
    public TableDataInfo list(DirExplain dirExplain)
    {
        startPage();
        List<DirExplain> list = dirExplainService.selectDirExplainList(dirExplain);
        return getDataTable(list);
    }

    /**
     * 导出汉语释义列表
     */
    @PreAuthorize("@ss.hasPermi('system:explain:export')")
    @Log(title = "汉语释义", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DirExplain dirExplain)
    {
        List<DirExplain> list = dirExplainService.selectDirExplainList(dirExplain);
        ExcelUtil<DirExplain> util = new ExcelUtil<DirExplain>(DirExplain.class);
        util.exportExcel(response, list, "汉语释义数据");
    }

    /**
     * 获取汉语释义详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:explain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dirExplainService.selectDirExplainById(id));
    }

    /**
     * 新增汉语释义
     */
    @PreAuthorize("@ss.hasPermi('system:explain:add')")
    @Log(title = "汉语释义", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DirExplain dirExplain)
    {
        return toAjax(dirExplainService.insertDirExplain(dirExplain));
    }

    /**
     * 修改汉语释义
     */
    @PreAuthorize("@ss.hasPermi('system:explain:edit')")
    @Log(title = "汉语释义", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DirExplain dirExplain)
    {
        return toAjax(dirExplainService.updateDirExplain(dirExplain));
    }

    /**
     * 删除汉语释义
     */
    @PreAuthorize("@ss.hasPermi('system:explain:remove')")
    @Log(title = "汉语释义", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dirExplainService.deleteDirExplainByIds(ids));
    }
}
