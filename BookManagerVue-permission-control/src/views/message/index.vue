<!-- BookManager-master/BookManagerVue-permission-control/src/views/message/index.vue -->
<template>
  <div class="app-container">
    <!-- 添加留言表单 -->
    <div class="filter-container" style="margin-bottom: 15px">
      <el-input v-model="newMessage" placeholder="请输入留言内容" style="width: 400px;" class="filter-item" />
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreateMessage">
        提交留言
      </el-button>
    </div>

    <!-- 留言列表（用户ID改为用户名） -->
    <el-table
      ref="messageTable"
      :data="messageList"
      border
      style="width: 100%">
      <el-table-column
        prop="userName"  <!-- 显示用户名 -->
        label="用户名"
        width="120">
      </el-table-column>
      <el-table-column
        prop="content"
        label="留言内容"
        min-width="300"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="留言时间"
        width="200"
        :formatter="formatDate">
      </el-table-column>
    </el-table>

    <!-- 分页条 -->
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="queryParam.page"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="queryParam.limit"
      layout="total, sizes, prev, pager, next, jumper"
      :total="recordTotal"
      style="margin-top: 15px">
    </el-pagination>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import waves from '@/directive/waves'
import { fetchMessageList, createMessage } from '@/api/message'
import { queryUsers } from '@/api/user'  // 导入获取所有用户的接口

export default {
  name: 'Message',
  directives: { waves },
  computed: {
    ...mapGetters(['id', 'username'])  // 获取当前登录用户的ID和用户名
  },
  data() {
    return {
      messageList: [],
      recordTotal: 0,
      queryParam: {
        page: 1,
        limit: 10
      },
      newMessage: '',
      userMap: new Map()  // 存储用户ID到用户名的映射（缓存用户信息）
    }
  },
  created() {
    // 先加载所有用户信息到map，避免重复请求
    this.loadAllUsers()
      .then(() => {
        this.fetchMessages();  // 加载留言列表
      })
  },
  methods: {
    // 加载所有用户信息，构建userId->username的映射
    async loadAllUsers() {
      try {
        const res = await queryUsers();
        const users = res.data || [];
        users.forEach(user => {
          this.userMap.set(user.id, user.username);  // 假设用户表主键为id，用户名为username
        });
      } catch (error) {
        console.error('加载用户信息失败:', error);
        this.$message.error('加载用户信息失败，可能无法显示用户名');
      }
    },

    // 获取留言列表并补充用户名
    fetchMessages() {
      fetchMessageList(this.queryParam).then(res => {
        console.log('留言列表数据:', res.data);
        let messages = res.data || [];

        // 为每条留言添加username字段
        messages = messages.map(msg => {
          return {
            ...msg,
            userName: this.userMap.get(msg.userId) || '未知用户'  // 从map中获取用户名，默认显示"未知用户"
          };
        });

        this.messageList = messages;
        this.recordTotal = res.count || 0;
      }).catch(error => {
        console.error('获取留言列表失败:', error)
        this.$message.error('获取留言列表失败')
      })
    },

    // 分页相关方法（不变）
    handleSizeChange(curSize) {
      this.queryParam.limit = curSize
      this.fetchMessages()
    },
    handleCurrentChange(curPage) {
      this.queryParam.page = curPage
      this.fetchMessages()
    },

    // 提交留言（不变）
    handleCreateMessage() {
      if (this.newMessage.trim() === '') {
        this.$message.warning('请输入留言内容')
        return
      }
      if (!this.id) {
        this.$message.warning('请先登录')
        return
      }
      createMessage({
        userId: this.id,
        content: this.newMessage
      }).then(res => {
        if (res && res.code === 0) {
          this.$message.success('留言提交成功')
          this.newMessage = ''
          this.fetchMessages()  // 刷新列表
        } else {
          this.$message.error(res?.message || '留言提交失败')
        }
      }).catch(error => {
        console.error('提交留言失败:', error)
        this.$message.error('提交留言失败')
      })
    },

    // 时间格式化方法（不变）
    formatDate(row) {
      const timeStr = row.createTime;
      if (!timeStr) {
        return '未知时间';
      }
      if (typeof timeStr === 'string') {
        const dateParts = timeStr.split(' ')[0].split('-');
        const timeParts = timeStr.split(' ')[1]?.split(':') || [0, 0, 0];
        const date = new Date(
          parseInt(dateParts[0], 10),
          parseInt(dateParts[1], 10) - 1,
          parseInt(dateParts[2], 10),
          parseInt(timeParts[0], 10),
          parseInt(timeParts[1], 10),
          parseInt(timeParts[2], 10)
        );
        if (isNaN(date.getTime())) {
          return '无效时间';
        }
        return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')} ${String(date.getHours()).padStart(2, '0')}:${String(date.getMinutes()).padStart(2, '0')}:${String(date.getSeconds()).padStart(2, '0')}`;
      }
      if (typeof timeStr === 'number' || !isNaN(Number(timeStr))) {
        const date = new Date(Number(timeStr));
        if (!isNaN(date.getTime())) {
          return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')} ${String(date.getHours()).padStart(2, '0')}:${String(date.getMinutes()).padStart(2, '0')}:${String(date.getSeconds()).padStart(2, '0')}`;
        }
      }
      return '未知时间';
    }
  }
}
</script>

<style scoped>
.filter-container {
  display: flex;
  align-items: center;
  gap: 10px;
}
.el-table {
  margin-top: 10px;
}
</style>
