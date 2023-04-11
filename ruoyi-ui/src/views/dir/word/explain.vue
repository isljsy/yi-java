<template>
  <div class="app-container">
    <h1>释义</h1>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['system:explain:add']"
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="explainList" @selection-change="handleSelectionChange">
      <el-table-column label="汉语释义" align="left" prop="hanyu" />
      <el-table-column label="操作" align="left" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['system:explain:edit']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            v-hasPermi="['system:explain:remove']"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改汉语释义对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="汉语释义" prop="hanyu">
          <el-input v-model="form.hanyu" placeholder="请输入汉语释义" />
        </el-form-item>
        <el-divider content-position="left">彝语例句信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddDirSentence">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteDirSentence">删除</el-button>
          </el-col>
        </el-row>
        <el-table ref="dirSentence" :data="dirSentenceList" :row-class-name="rowDirSentenceIndex" @selection-change="handleDirSentenceSelectionChange">
          <el-table-column type="selection" width="50" align="left" />
          <el-table-column label="序号" align="left" prop="index" width="50" />
          <el-table-column label="彝文例句" align="left" prop="sentence">
            <template slot-scope="scope">
              <el-input v-model="scope.row.sentence"></el-input>
            </template>
          </el-table-column>
          <el-table-column label="汉语翻译" align="left" prop="hanyuSentence">
            <template slot-scope="scope">
              <el-input v-model="scope.row.hanyuSentence"></el-input>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listExplain, getExplain, delExplain, addExplain, updateExplain } from "@/api/dir/explain";

export default {
  name: "Explain",
  props: { id: [String] },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedDirSentence: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 汉语释义表格数据
      explainList: [],
      // 彝语例句表格数据
      dirSentenceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        wordId: this.id
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        hanyu: [
          { required: true, message: "汉语释义不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询汉语释义列表 */
    getList() {
      this.loading = true;
      listExplain(this.queryParams).then(response => {
        this.explainList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        wordId: null,
        hanyu: null
      };
      this.dirSentenceList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加汉语释义";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getExplain(id).then(response => {
        this.form = response.data;
        this.dirSentenceList = response.data.dirSentenceList;
        this.open = true;
        this.title = "修改汉语释义";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.dirSentenceList = this.dirSentenceList;
          if (this.form.id != null) {
            updateExplain(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExplain(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除汉语释义编号为"' + ids + '"的数据项？').then(function() {
        return delExplain(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 彝语例句序号 */
    rowDirSentenceIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 彝语例句添加按钮操作 */
    handleAddDirSentence() {
      const obj = {};
      obj.sentence = "";
      obj.hanyuSentence = "";
      this.dirSentenceList.push(obj);
    },
    /** 彝语例句删除按钮操作 */
    handleDeleteDirSentence() {
      if (this.checkedDirSentence.length == 0) {
        this.$modal.msgError("请先选择要删除的彝语例句数据");
      } else {
        const dirSentenceList = this.dirSentenceList;
        const checkedDirSentence = this.checkedDirSentence;
        this.dirSentenceList = dirSentenceList.filter(function(item) {
          return checkedDirSentence.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleDirSentenceSelectionChange(selection) {
      this.checkedDirSentence = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/explain/export', {
        ...this.queryParams
      }, `explain_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
