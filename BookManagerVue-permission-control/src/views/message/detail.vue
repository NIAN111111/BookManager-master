<template>
  <div>
    <h1>留言详情</h1>
    <!-- 这里可以根据需求添加显示留言详情的内容 -->
    <p>留言用户 ID: {{ message.userId }}</p>
    <p>留言用户名: {{ message.userName }}</p> <!-- 添加显示用户名 -->
    <p>留言内容: {{ message.content }}</p>
    <p>留言时间: {{ formatDate(message.createTime) }}</p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import { fetchMessageDetail } from '@/api/message';

export default {
  name: 'MessageDetail',
  computed: {
    ...mapGetters(['id']),
  },
  data() {
    return {
      message: {},
    };
  },
  created() {
    this.fetchMessage();
  },
  methods: {
    fetchMessage() {
      const id = this.$route.params.id;
      fetchMessageDetail(id).then((res) => {
        this.message = res.data || {};
      }).catch((error) => {
        console.error('获取留言详情失败:', error);
        this.$message.error('获取留言详情失败');
      });
    },
    formatDate(timeStr) {
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
          parseInt(timeParts[2], 10),
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
    },
  },
};
</script>

<style scoped>
/* 这里可以添加样式 */
</style>
