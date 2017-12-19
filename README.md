# demo
自己新写的demo  关于懒加载

viewpager切换不同页面进行网络请求的loading 里面模拟网络请求  可根据实际情况变动 在网络请求前显示 网络请求成功或者失败后消失
主要是在viewpager缓存不够的情况下 view会销毁，而framgnet实例仍然存在，导致加载多次，可以在界面销毁的时候  初始化状态，防止多次加载
在 onDestoryView回调里做了具体操作
新手写的很一般 
