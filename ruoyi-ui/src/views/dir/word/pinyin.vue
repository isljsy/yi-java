<template>
  <div class="app-container">
    <h1>拼音</h1>
    <el-table v-loading="loading" :data="pinyinList" :row-key="(row)=>{return row.id}">
      <el-table-column label="拼音" align="left" prop="pinyin" />
      <el-table-column label="地区" align="left" prop="local">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.dir_local" :value="scope.row.local"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="left" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['dir:pinyin:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['dir:pinyin:remove']"-->
<!--          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改拼音对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="拼音" prop="pinyin">
          <el-input v-model="form.pinyin" placeholder="请输入拼音" />
        </el-form-item>
        <el-form-item label="地区" prop="local">
          <el-select v-model="form.local" placeholder="请选择地区">
            <el-option
              v-for="dict in dict.type.dir_local"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listPinyinByWordId, getPinyin, delPinyin, addPinyin, updatePinyin } from "@/api/dir/pinyin";

export default {
  name: "Pinyin",
  dicts: ['dir_local'],
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
      // 拼音表格数据
      pinyinList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        word: this.id
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        pinyin: [
          { required: true, message: "拼音不能为空", trigger: "blur" }
        ],
        local: [
          { required: true, message: "地区不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询拼音列表 */
    getList() {
      this.loading = true;
      listPinyinByWordId(this.queryParams).then(response => {
        this.pinyinList = response.rows;
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
        pinyin: null,
        local: null
      };
      this.resetForm("form");
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加拼音";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPinyin(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改拼音";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePinyin(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPinyin(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除拼音编号为"' + ids + '"的数据项？').then(function() {
        return delPinyin(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
