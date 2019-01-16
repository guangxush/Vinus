### 数据缓存的三种方式
- 通过RabbitMQ保存日志消息，并主动发送给DB端，DB端被动接收
- 通过RabbitMQ保存消息，DB端主动发起消息监听
- 通过Redis缓存消息到Channel，监听Redis消息之后发送给DB端， DB端被动接收
- 通过Redis缓存消息到List,定时任务POP元素到DB端，DB端被动监听