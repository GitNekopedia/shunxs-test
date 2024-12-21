1. 用户注册
  URL: /api/users/register
  方法: POST
  参数:
  nickname (String): 用户昵称
  响应:
  成功: 返回新创建的用户对象
  失败: 返回错误信息
  示例:
  POST /api/users/register?nickname=JohnDoe

  

2. 用户登录
  URL: /api/users/login
  方法: POST
  请求体:
  {
    "nickname": "用户昵称"
  }
  响应:
  成功: 返回用户对象
  失败: 返回 null
  示例:
  POST /api/users/login
  Content-Type: application/json

{
  "nickname": "JohnDoe"
}



3. 上传头像
URL: /api/users/avatar
方法: POST
参数:
file (MultipartFile): 头像文件
响应:
成功: 返回头像 URL
失败: 返回错误信息
示例:
POST /api/users/avatar
Content-Type: multipart/form-data



4. 关注用户
URL: /api/users/follow
方法: POST
请求体:
{
    "followedId": "被关注用户的ID"
}
响应:
成功: 返回成功消息
失败: 返回错误信息
示例:
POST /api/users/follow
Content-Type: application/json

{
  "followedId": 123
}



5. 获取关注者列表
URL: /api/users/followers
方法: GET
参数:
nicknameSearch (String, 可选): 用于搜索昵称
page (int, 默认值: 1): 页码
size (int, 默认值: 10): 每页数量
响应:
成功: 返回关注者列表
失败: 返回错误信息
示例:
GET /api/users/followers?nicknameSearch=John&page=1&size=20





注意事项：
关注用户和获取关注者列表接口需要用户已经登录。
获取关注者列表支持分页和昵称搜索。
所有接口都返回统一的 BaseResponse 格式，包含状态码、数据和消息。