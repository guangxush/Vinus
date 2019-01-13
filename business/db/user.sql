CREATE TABLE `user` (
`id`  int(12) NOT NULL AUTO_INCREMENT ,
`user_id`  int(10) NOT NULL ,
`user_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=DYNAMIC
;