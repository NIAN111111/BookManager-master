// BookManager-master/BookManagerVue-permission-control/src/api/message.js
import request from '@/utils/request'

// 获取留言列表
export function fetchMessageList(query) {
  return request({
    url: '/message/list', // 请根据实际后端接口地址修改
    method: 'get',
    params: query
  })
}

// 添加留言
export function createMessage(data) {
  return request({
    url: '/message/create', // 请根据实际后端接口地址修改
    method: 'post',
    data
  })
}

// 获取留言详情
export function fetchMessageDetail(id) {
  return request({
    url: `/message/detail/${id}`,
    method: 'get'
  })
}
