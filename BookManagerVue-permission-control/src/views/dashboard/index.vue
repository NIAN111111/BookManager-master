<template>
  <div class="dashboard-container">
    <div class="welcome-section">
      <h1 class="welcome-title">欢迎使用图书管理系统</h1>
      <div class="developer-info"></div>
    </div>
    <hr class="divider">
    <div class="environment-info">
      <h2>项目开发环境介绍</h2>
      <div class="info-group">
        <div class="info-column">
          <p>操作系统: Windows 10 home</p>
          <p>开发工具: Intellij IDEA 2021.2.1(Ultimate Edition)</p>
          <p>Java 版本: 1.8.0</p>
        </div>
        <div class="info-column">
          <p>服务器: Tomcat 8.5</p>
          <p>数据库: MySQL 5.7</p>
          <p>前端技术: HTML+CSS+JavaScript+Vue+Axios+ElementUI</p>
          <p>后端技术: Java+SpringBoot+MyBatis+MySQL</p>
        </div>
      </div>
    </div>

    <!-- 图表容器 -->
    <div class="chart-container">
      <h2>图书类型的数量分布</h2>
      <div ref="bookTypeChart" class="chart" style="width: 100%; height: 300px;"></div>

      <h2>借阅书籍类型分布</h2>
      <div ref="borrowTypeChart" class="chart" style="width: 100%; height: 300px;"></div>

      <h2>不同月份的借阅次数变化</h2>
      <div ref="borrowTimeChart" class="chart" style="width: 100%; height: 300px;"></div>
    </div>
  </div>
</template>

<style scoped>
.chart-container {
  margin-top: 20px;
  padding: 20px;
  background-color: #f5f7fa;
  border-radius: 8px;
}

.chart {
  margin-bottom: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
  background-color: #fff;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>

<script>
import { mapGetters } from 'vuex'
import * as echarts from 'echarts'
// 导入封装的请求工具（确保使用的是配置好baseURL的service）
import request from '@/utils/request'

export default {
  name: 'Dashboard',
  computed: {
   ...mapGetters([
      'id',
      'name',
      'roles'
    ])
  },
  mounted() {
    this.fetchDashboardData();
  },
  methods: {
    fetchDashboardData() {
      // 关键修改：删除重复的BookManager前缀，使用相对路径
      request.get('/api/dashboard/data') // 实际请求路径：baseURL + /api/dashboard/data
        .then(response => {
          const data = response.data;
          this.drawBookTypeChart(data.bookTypeData);
          this.drawBorrowTypeChart(data.borrowTypeData);
          this.drawBorrowTimeChart(data.borrowTimeData);
        })
        .catch(error => {
          console.error('获取仪表盘数据失败:', error);
          // 使用模拟数据作为备选
          this.drawBookTypeChart();
          this.drawBorrowTypeChart();
          this.drawBorrowTimeChart();
        });
    },
    // 图书类型数量分布
    drawBookTypeChart(bookTypeData) {
      if (!bookTypeData || bookTypeData.length === 0) {
        bookTypeData = [
          { typeName: '计算机', count: 50 },
          { typeName: '历史', count: 30 },
          { typeName: '文学', count: 80 },
          { typeName: '科幻', count: 20 },
          { typeName: '其它', count: 15 },
          { typeName: '小说', count: 100 },
          { typeName: '外语', count: 25 }
        ];
      }

      const bookTypeNames = bookTypeData.map(item => item.typeName);
      const bookTypeCounts = bookTypeData.map(item => item.count);

      const chart = echarts.init(this.$refs.bookTypeChart);
      const option = {
        title: { text: '不同图书类型的数量分布' },
        xAxis: { type: 'category', data: bookTypeNames },
        yAxis: { type: 'value' },
        series: [{
          data: bookTypeCounts,
          type: 'bar',
          itemStyle: { color: '#409eff' }
        }]
      }
      chart.setOption(option);
    },
    // 借阅书籍类型分布
    drawBorrowTypeChart(borrowTypeData) {
      if (!borrowTypeData || borrowTypeData.length === 0) {
        borrowTypeData = [
          { typeName: '小说', borrowCount: 120 },
          { typeName: '计算机', borrowCount: 95 },
          { typeName: '文学', borrowCount: 88 },
          { typeName: '历史', borrowCount: 45 },
          { typeName: '外语', borrowCount: 30 },
          { typeName: '科幻', borrowCount: 25 },
          { typeName: '其它', borrowCount: 15 }
        ];
      }

      const typeNames = borrowTypeData.map(item => item.typeName);
      const borrowCounts = borrowTypeData.map(item => item.borrowCount);

      const chart = echarts.init(this.$refs.borrowTypeChart);
      const option = {
        title: {
          text: '各类型图书借阅量分布',
          subtext: '单位：本'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: { type: 'shadow' }
        },
        xAxis: {
          type: 'category',
          data: typeNames,
          name: '图书类型'
        },
        yAxis: {
          type: 'value',
          name: '借阅数量'
        },
        series: [{
          name: '借阅量',
          data: borrowCounts,
          type: 'bar',
          itemStyle: { color: '#67c23a' }
        }]
      };
      chart.setOption(option);
    },
    // 借阅时间趋势
    drawBorrowTimeChart(borrowData) {
      if (!borrowData || borrowData.length === 0) {
        borrowData = [
          { month: '2025-01', count: 10 },
          { month: '2025-02', count: 15 },
          { month: '2025-03', count: 8 },
          { month: '2025-04', count: 12 },
          { month: '2025-05', count: 18 },
          { month: '2025-06', count: 20 },
          { month: '2025-07', count: 16 }
        ];
      }

      const months = borrowData.map(item => item.month);
      const borrowCounts = borrowData.map(item => item.count);

      const chart = echarts.init(this.$refs.borrowTimeChart);
      const option = {
        title: { text: '不同月份的借阅次数变化' },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: months
        },
        yAxis: { type: 'value' },
        series: [{
          data: borrowCounts,
          type: 'line',
          smooth: true,
          itemStyle: { color: '#409eff' }
        }]
      }
      chart.setOption(option);
    }
  }
}
</script>
