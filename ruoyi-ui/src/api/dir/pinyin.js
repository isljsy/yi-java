import request from '@/utils/request'

// 查询拼音列表
export function listPinyinByWordId(query) {
  return request({
    url: '/dir/pinyin/list',
    method: 'get',
    params: query
  })
}

// 查询拼音详细
export function getPinyin(id) {
  return request({
    url: '/dir/pinyin/' + id,
    method: 'get'
  })
}

// 新增拼音
export function addPinyin(data) {
  return request({
    url: '/dir/pinyin',
    method: 'post',
    data: data
  })
}

// 修改拼音
export function updatePinyin(data) {
  return request({
    url: '/dir/pinyin',
    method: 'put',
    data: data
  })
}

// 删除拼音
export function delPinyin(id) {
  return request({
    url: '/dir/pinyin/' + id,
    method: 'delete'
  })
}
