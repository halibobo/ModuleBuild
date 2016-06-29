## Module build to apk demo 
## 利用组件（Module）开发apk实例

----------

### 包含的组件及介绍



> #### :app

工程application入口



> #### :baselibrary

lib组件，是整个工程的公共组件，其它的组件都引用了它




> #### :tasks:ordermanager

application组件，是订单lib组件的外壳，负责测试订单lib




> ##### :tasks:pointmanager

lib组件，属于网点模块的独立的组件




> #### :orderlibrary

lib组件，属于订单模块的独立的组件

#### 【注】
**application组件**是指该组件本身就可以运行并打包成apk
**lib组件**是指该组件属于app的一部分，可以供其它组件使用但是本身不能打包成apk
