import request from '@/utils/request'

// 查询彝文列表
export function listWord(query) {
  return request({
    url: '/system/word/list',
    method: 'get',
    params: query
  })
}

// 查询彝文详细
export function getWord(id) {
  return request({
    url: '/system/word/' + id,
    method: 'get'
  })
}

// 新增彝文
export function addWord(data) {
  return request({
    url: '/system/word',
    method: 'post',
    data: data
  })
}

// 修改彝文
export function updateWord(data) {
  return request({
    url: '/system/word',
    method: 'put',
    data: data
  })
}

// 删除彝文
export function delWord(id) {
  return request({
    url: '/system/word/' + id,
    method: 'delete'
  })
}
