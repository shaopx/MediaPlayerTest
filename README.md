# 其实是学习opengl的过程中整理的一些示例.  个人感觉, 对理解各种概念还是很有用的.

# MediaPlayerTest
汇集了最近学习安卓视频播放过程中涉及的一些列子.
主要包括SurfaceView, GLSurfaceView, SurfaceTexture的基本用法

## SurfaceView
#### SurfaceViewActivity
一个最基本的SurfaceView的使用的例子.

#### DoodleViewSurface
一个可以响应用户触摸事件的SurfaceView的例子.

## GLSurfaceView
#### GLSurfaceViewTestActivity
一个可以响应用户触摸事件的GLSurfaceView的例子.

#### CubeFlingActivity
一个可以响应用户手势的GLSurfaceView的例子


## Camara
#### CameraActivity
一个使用Camrea2接口的摄像头取景例子, 谷歌官方例子

#### VideoRecorderActivity
一个使用Camrea2接口的摄像头_录像_取景例子, 谷歌官方例子

## Opengl 学习
#### OpenGlWithGlSurfaceViewActivity   
最简单常见的方式, 使用GlSurfaceView  绘制一个三角形.  也包括了着色器创建, 程序连接, 链接, 使用等模板代码

#### EglImpl1Activity   
自己创建EGL环境,  不使用使用GlSurfaceView ,  绘制一个与上面例子相同的三角形

#### EglImp2Activity  
对上面的EglImpl1Activity方式继续扩展,  实现了两个渲染目标输出, 一个是屏幕, 一个是pbuffer, 并且最终把pbuffer的输出转换成Bitmap又显示在屏幕上. 

