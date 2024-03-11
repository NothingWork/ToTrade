这里记录了一些主要的API接口
本项目已整合springdoc自动生成Restful风格的api文档，请启动项目后在下面地址查看
http://127.0.0.1:8080/swagger-ui/index.html#/
| 路径                     | 状态码  | 相应信息         | 描述                         |
|------------------------|------|--------------|----------------------------|
| /api/getGoods          | 101  | success      | 获取商品信息，返回包含多个商品对象的数组       |
| /api/searchGoods       | 102  | success      | 根据商品信息进行模糊查询，返回包含多个商品对象的数组 |
| /api/login             | 201  | log_success  | 登录成功，返回用户类                 |
| /api/login             | -201 | log_fail     | 登录失败，返回错误结果                |
| /api/login             | 202  | reg_success  | 注册成功，返回用户类                 |
| /api/login             | -202 | reg_fail     | 注册失败，返回结果为空                |
| /api/sendMessage       | 301  | send_success | 发送消息，消息内容存入数据库             |
| /api/getUnReadMessages | 302  | success      | 获取未读消息(用户登录时调用)，返回未读消息条数   |
| /api/getAllMessages    | 303  | success      | 获取该用户所有消息，用于渲染聊天记录         |

这里是聊天系统的一些说明
在使用聊天系统之前需要通过websocket发送一段字符串作为连接至聊天服务器的用户名(唯一)
发送消息通过/api/sendMessage接口实现

该项目主要实现了聊天系统，因为其他系统的许多接口均为一些重复的基本数据库修改操作且与
所以未进行完善

待完善接口：用户修改，商品添加，商品修改