<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :model="queryParams" size="small" :inline="true" label-width="68px">
      <el-form-item label="彝文" prop="word">
        <el-input
          v-model="queryParams.word"
          placeholder="请输入彝文"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="页码" prop="page">
        <el-input
          v-model="queryParams.page"
          placeholder="请输入页码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部首" prop="radical">
        <el-select v-model="queryParams.radical" placeholder="请选择部首" clearable>
          <el-option
            v-for="dict in dict.type.dir_radical"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="笔画" prop="count">
        <el-input
          v-model="queryParams.count"
          placeholder="请输入笔画"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入字码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="释义" prop="params.hanyu">
        <el-input
          v-model="queryParams.params.hanyu"
          placeholder="请输入释义"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="wordList" @row-click="handleUpdate">
      <el-table-column label="ID" align="center" prop="id" width="80"/>
      <el-table-column label="彝文" align="center" prop="word" />
      <el-table-column label="页码" align="center" prop="page" />
      <el-table-column label="部首" align="center" prop="radical">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dir_radical" :value="scope.row.radical"/>
        </template>
      </el-table-column>
      <el-table-column label="笔画" align="center" prop="count" />
      <el-table-column label="字码" align="left" prop="code" />
      <el-table-column label="释义" align="left" prop="hanyuStr"/>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改彝文对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="彝文" prop="word">
          <el-input v-model="form.word" class="yi-font" placeholder="请输入彝文" />
        </el-form-item>
        <el-form-item label="页码" prop="page">
          <el-input v-model="form.page" placeholder="请输入页码" />
        </el-form-item>
        <el-form-item label="部首" prop="radical">
          <el-input v-model="form.radical" placeholder="请输入部首" />
        </el-form-item>
        <el-form-item label="笔画" prop="count">
          <el-input v-model="form.count" placeholder="请输入笔画" />
        </el-form-item>
        <el-form-item label="字码" prop="code">
          <el-input v-model="form.code" placeholder="请输入字码" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listWord, addWord, updateWord } from "@/api/dir/word";

export default {
  name: "Word",
  dicts: ['dir_radical'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 彝文表格数据
      wordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        word: null,
        page: null,
        radical: null,
        count: null,
        code: null,
        params: {
          hanyu: null
        }
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        word: [
          { required: true, message: "彝文不能为空", trigger: "blur"}
        ],
        radical: [
          { required: true, message: "部首不能为空", trigger: "blur" }
        ],
        count: [
          { required: true, message: "笔画不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "字码不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询彝文列表 */
    getList() {
      this.loading = true;
      listWord(this.queryParams).then(response => {
        this.wordList = response.rows.map(row => {
          row.hanyuStr = row.hanyuList.join("/");
          return row;
        });
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
        word: null,
        page: null,
        radical: null,
        count: null,
        code: null
      };
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.$router.push({ path: 'dir/word/editWord', query: { id: row.id }})
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/word/export', {
        ...this.queryParams
      }, `word_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
</style>
