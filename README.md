# Vinus
Using cache save the log temporarily and then persistence into the database.

### Function

#### Business Module:
1. login and register function
2. send email if the service exception
3. send user behavior data to mq module controller
4. send log to mq module controller

#### MQ Module:
1. send message to rabbit mq 
2. receive message of rabbit mq
3. listener message of rabbit mq 
4. save message in redis cache and get the message scheduled

#### DB Module:
1. persistence the message in mysql
2. receive the message by mq controller

#### Show Module:
1. display the logs in table by bootstrap

### Technology Stack
- Spring Boot（Aspect, Model, Redis, MySQL, ScheduledTask, RabbitMQ, Web）
- Rabbit MQ
- Redis
- MySQL