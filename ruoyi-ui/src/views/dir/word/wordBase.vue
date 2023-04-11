<template>
  <div class="app-container">
    <h1 style="font-size: 50px; margin: 0" v-text="wordList[0].word" />
    <h1>基本信息</h1>
    <el-table v-loading="loading" :data="wordList">
      <el-table-column label="ID" align="left" prop="id" />
      <el-table-column label="页码" align="left" prop="page" />
      <el-table-column label="部首" align="left" prop="radical">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dir_radical" :value="scope.row.radical" />
        </template>
      </el-table-column>
      <el-table-column label="笔画" align="left" prop="count" />
      <el-table-column label="字码" align="left" prop="code" />
      <el-table-column label="操作" align="left" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['system:word:edit']"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改彝文对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="彝文" prop="word">
          <el-input v-model="form.word" placeholder="请输入彝文" />
        </el-form-item>
        <el-form-item label="页码" prop="page">
          <el-input v-model="form.page" placeholder="请输入页码" />
        </el-form-item>
        <el-form-item label="部首" prop="radical">
          <el-select v-model="form.radical" placeholder="请选择部首">
            <el-option
              v-for="dict in dict.type.dir_radical"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
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
import { listWord, getWord, addWord, updateWord } from "@/api/dir/word";

export default {
  name: "WordBase",
  dicts: ['dir_radical'],
  props: {
    id: {
      type: String
    }
  },
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
      wordList: [{ word: '' }],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 1,
        id: this.id
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        word: [
          { required: true, message: "彝文不能为空", trigger: "blur" }
        ],
        radical: [
          { required: true, message: "部首不能为空", trigger: "change" }
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
        console.log()
        this.wordList = response.rows;
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改彝文";
      });
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
    }
  }
};
</script>
