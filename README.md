# PCRunMonkey

Monkey 传参工具设计-PC端

##使用说明

* 环境及运行

	环境： Java 1.7 以上；

	运行： 点击 bat目录下，“双击打开.bat”

	日志： 日志保存在运行的目录下，已打开的时间命名。

* `package` 是运行APP的包名，adb环境下运行 `adb shell dumpsys activity | find "mFocusedActivity"` 可活动当前界面App的package。
	
* 调试选项：

	* --ignore-crashes
通常，当应用程序崩溃或发生任何失控异常时，Monkey将停止运行。如果设置此选项，Monkey将继续向系统发送事件，直到计数完成。

	* --ignore-timeouts
通常，当应用程序发生任何超时错误时，Monkey将停止运行。如果设置此选项，Monkey将继续向系统发送事件，直到计数完成。

	* --ignore-security-exceptions
通常，当应用程序发生许可错误(如启动一个需要某些许可的Activity)时，Monkey将停止运行。如果设置了此选项，Monkey将继续向系统发送事件，直到计数完成。

	* --kill-process-after-error
通常，当Monkey由于一个错误而停止时，出错的应用程序将继续处于运行状态。当设置了此选项时，将会通知系统停止发生错误的进程。注意，正常的(成功的)结束，并没有停止启动的进程，设备只是在结束事件之后，简单地保持在最后的状态。


* 事件比例

	* --pct-touch
调整触摸事件的百分比(触摸事件是一个down-up事件，它发生在屏幕上的某单一位置)。

	* --pct-motion
调整动作事件的百分比(动作事件由屏幕上某处的一个down事件、一系列的伪随机事件和一个up事件组成)。

	* --pct-trackball
调整轨迹事件的百分比(轨迹事件由一个或几个随机的移动组成，有时还伴随有点击)。

	* --pct-nav
调整“基本”导航事件的百分比(导航事件由来自方向输入设备的up/down/left/right组成)。

	* --pct-appswitch
调整启动Activity的百分比。在随机间隔里，Monkey将执行一个startActivity()调用，作为最大程度覆盖包中全部Activity的一种方法。

	* --pct-anyevent
调整其它类型事件的百分比。它包罗了所有其它类型的事件，如：按键、其它不常用的设备按钮、等等。


* 效果

![run](pic/run.gif)




##**Situation：**

1、每次使用Monkey传参都不方便。

2、使用脚本不够形象，以图形界面方式进行启动。

3、编写过Android启动Monkey，无法成功启动理由：

    1、通过实践：通过 Runtime.getRuntime().exec 调用 monkey 问题无法解决
    2、非Root或同一签名App 

[原文链接](http://stackoverflow.com/questions/20518745/how-is-it-possible-to-run-monkey-from-android-app)


##**Tast**
1、掌握图像编程，需要了解图形、事件、侦听等类的使用。

2、设计工具的整体布局。

3、确定那些需要实现功能。

4、方便非专业用户的使用

##**Action**

###确定实现的功能：

1、实现图形界面的方式，传递参数功能

2、实现快捷启动功能“方案一”、方案二等功能

3、实现连接手机后获取已安装的 package信息。

4、实现启动时，判断是否已启动Monkey

5、实现停止正在运行的Monkey

6、实现日志保存在自定义目录下，目录以时间命名<未实现>

7、实现导出日志按钮<未实现>

8、实现批处理启动应用，不要打包成exe <未实现>

###遇到的问题：

1、使用JDK1.8 不支持 Java swing （JPanel、JLabel、JFrame等）图形类?

    安装JDK1.8，可以在Eclipse中，调整为JDK1.7编译环境即可。

2、如何调用外部命令？

    调用外部命令使用：Runtime.getRuntime().exec(String cmd)
    
3、调用外部命令时，造成线程阻塞（点击按钮后，一直显示等待状态，其它功能无法使用<类似假死>）

    主要分四步解决：
    1. 封装Runtime.getRuntime().exec(String cmd) 方法名为：execCommand;（CommandUtil.java）
    2. execCommand()中，在waitfor 插入WatchThread的处理代码（CommandUtil.java）
    3. 编写类继承Thread，再次封装execCommand，添加构造方法、启动方法。(MonkeyThread.java)
    4. 调用带参数的构造方法，启动


4、如何通过使用多个JSilder，控制调试参数百分比？

    主要思想为：
    1、当用户对某个滑条进行增量操作时，启动侦听事件，总体数据超过100时，对其他滑条进行减量操作。
    2、不对用户的减量操作响应。
    备注：因算法较为繁复，不细讲(该功能还存在Bug)

##**Result**

###成果：

1、已完成各功能，以友好的界面展示。

2、对非专业用户，提供快捷的运行方案。

###备注
1、选择测试应用前，必现连接手机；选择的应用为第三方App，若找不到先要的package，自行修改代码。

2、调试、与事件百分比仅实现常用功能；可根据需要自行修改。

3、事件百分比的数值，存在小bug，导致获取数值不准确的问题。

