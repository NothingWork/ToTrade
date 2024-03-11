这里记录了一些主要的API接口


| 路径                     | 请求包含参数               | 状态码 | 相应信息         | 描述                         |
|------------------------|----------------------|-----|--------------|----------------------------|
| /api/getGoods          |                      | 1   | success      | 获取商品信息，返回包含多个商品对象的数组       |
| /api/searchGoods       |                      | 2   | success      | 根据商品信息进行模糊查询，返回包含多个商品对象的数组 |
| /api/login             |                      | 3   | log_success  | 登录成功，返回用户类                 |
| /api/login             |                      | -1  | log_fail     | 登录失败，返回错误结果                |
| /api/login             |                      | 4   | reg_success  | 注册成功，返回用户类                 |
| /api/login             |                      | -2  | reg_fail     | 注册失败，返回结果为空                |
| /api/sendMessage       | from,to,text (均为字符串) | 5   | send_success | 发送消息，消息内容存入数据库             |
| /api/getUnReadMessages | name (字符串)           | 6   | success      | 获取未读消息(用户登录时调用)，返回未读消息条数   |


这里是聊天系统的一些说明
在使用聊天系统之前需要通过websocket发送一段字符串作为连接至聊天服务器的用户名(唯一)
发送消息通过/api/sendMessage接口实现

