<template>
  <div class="app-container">
    <div class="filter-container" style="margin-bottom: 15px">
      <el-input v-model="newCategory" placeholder="输入新的图书分类名称" style="width: 200px"></el-input>
      <el-button @click="addCategory" type="primary" icon="el-icon-plus">添加分类</el-button>
    </div>
    <el-table :data="categories" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="100"></el-table-column>
      <el-table-column prop="categoryName" label="分类名称"></el-table-column>
      <el-table-column label="操作" width="100">
        <template #default="scope">
          <el-button @click="deleteCategory(scope.row.id)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      categories: [],
      newCategory: ''
    };
  },
  created() {
    this.fetchCategories();
  },
  methods: {
    fetchCategories() {
      axios.get('/api/categories')
        .then(response => {
          this.categories = response.data;
        })
        .catch(error => {
          console.error('获取图书分类失败:', error);
        });
    },
    addCategory() {
      if (this.newCategory) {
        axios.post('/api/categories', { categoryName: this.newCategory })
          .then(response => {
            this.categories.push(response.data);
            this.newCategory = '';
          })
          .catch(error => {
            console.error('添加图书分类失败:', error);
          });
      }
    },
    deleteCategory(id) {
      this.$confirm('确定要删除该分类吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete(`/api/categories/${id}`)
          .then(() => {
            this.categories = this.categories.filter(category => category.id !== id);
            this.$message.success('删除分类成功');
          })
          .catch(error => {
            console.error('删除图书分类失败:', error);
          });
      });
    }
  }
};
</script>
