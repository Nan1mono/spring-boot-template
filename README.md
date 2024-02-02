# Spring-boot-Template

> 🚀🚀🚀旨在提供一个更简单、更易用、更轻量的Springboot脚手架。如果你有更好的点子，欢迎提交推送给我！

模板脚手架分为了基于boot 3.x版本和基于 boot 2.x版本

## 里程碑

- 2024-02-02
  1. 支持template-logger功能，提供对于日志记录开关、等级、日志文件名称、天数归档大小、路径等支持。使用该功能需要引入template-logger-starter。详细说明请移步：[template-logger-starter](https://github.com/Nan1mono/template-logger-starter)
  2. 配置文件添加对于数据插入时meta-handler自定义字段的支持
  3. 添加token加密盐和过期时间
  4. 添加security配置，额外提供了按钮权限和白名单配置
  5. 添加了密码试错设定（pass-error），提供密码试错开关、次数以及锁定时间
  6. 添加用户信息缓存设定，可以选择使用本地缓存或Redis