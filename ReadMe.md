这里记录了一些主要的API接口
路径          状态码         响应信息         描述
/getGoods     1            success         获取商品信息

| 路径               | 状态码 | 相应信息        | 描述                         |
|------------------|-----|-------------|----------------------------|
| /api/getGoods    | 1   | success     | 获取商品信息，返回包含多个商品对象的数组       |
| /api/searchGoods | 2   | success     | 根据商品信息进行模糊查询，返回包含多个商品对象的数组 |
| /api/login       | 3   | log_success | 登录成功，返回用户类                 |
| /api/login       | -1  | log_fail    | 登录失败，返回错误结果                |
| /api/login       | 4   | reg_success | 注册成功，返回用户类                 |
| /api/login       | -2  | reg_fail    | 注册失败，返回结果为空                |

这里是聊天系统的一些说明
因为websocket通信只能传输字符串，所以在前后端传送消息时，
都必须写成json数据格式的字符串！！！，在由前后端分别自行转换成对应的json数据进一步处理
  在使用聊天系统之前，前端需要先发送注册客户端的数据，数据格式如下
  "
{
  "name":(这里可以是任何唯一标识用户的字符串)
}
  "

  聊天信息的统一格式
  "
  {
  "from":由谁发送，同上文name
  "to":向谁发送，同上文name
  "Time":发送时间
  "text":发送内容
  }
  "
  
