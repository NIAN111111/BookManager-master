<template>
  <div class="app-container">
    <el-table :data="statistics" border style="width: 100%">
      <el-table-column prop="username" label="用户名"></el-table-column>
      <el-table-column prop="borrowCount" label="借阅次数"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      statistics: []
    };
  },
  created() {
    this.fetchStatistics();
  },
  methods: {
    fetchStatistics() {
      axios.get('/api/statistics')
        .then(response => {
          this.statistics = response.data;
        })
        .catch(error => {
          console.error('获取用户借阅统计失败:', error);
        });
    }
  }
};
</script>
