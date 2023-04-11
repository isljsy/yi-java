import request from '@/utils/request'

// 查询汉语释义列表
export function listExplain(query) {
  return request({
    url: '/system/explain/list',
    method: 'get',
    params: query
  })
}

// 查询汉语释义详细
export function getExplain(id) {
  return request({
    url: '/system/explain/' + id,
    method: 'get'
  })
}

// 新增汉语释义
export function addExplain(data) {
  return request({
    url: '/system/explain',
    method: 'post',
    data: data
  })
}

// 修改汉语释义
export function updateExplain(data) {
  return request({
    url: '/system/explain',
    method: 'put',
    data: data
  })
}

// 删除汉语释义
export function delExplain(id) {
  return request({
    url: '/system/explain/' + id,
    method: 'delete'
  })
}
