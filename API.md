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
