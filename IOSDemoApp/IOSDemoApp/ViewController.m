//
//  ViewController.m
//  IOSDemoApp
//
//  Created by 祁旋 on 2024/6/3.
//

#import "ViewController.h"
//#import "KMMLog/KMMLog.h"
#import "MyFramework/MyFramework.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    // 创建按钮
    UIButton *button = [UIButton buttonWithType:UIButtonTypeSystem];
    [button setTitle:@"Press Me" forState:UIControlStateNormal];
    button.frame = CGRectMake(100, 100, 100, 50); // 设置按钮位置和大小
    
    // 为按钮添加点击事件
    [button addTarget:self action:@selector(buttonPressed) forControlEvents:UIControlEventTouchUpInside];
    
    // 将按钮添加到视图中
    [self.view addSubview:button];
}

// 按钮点击事件处理方法
- (void)buttonPressed {
    NSLog(@"Hello World");
    // 获取 KMMLKMMLog 的共享实例
//    KMMLKMMLog *logger = [KMMLKMMLog shared];
    
    // 使用 PrintLnTag:msg: 方法打印日志
//    [logger PrintLnTag:@"INFO" msg:@"Hello World from Objective-C"];
    
    
    // 创建 MyFrameworkIOSPlatform 实例
    MyFrameworkIOSPlatform *iosPlatform = [[MyFrameworkIOSPlatform alloc] init];

    // 访问 name 属性
    NSLog(@"Platform name: %@", iosPlatform.name);
    
    
    // 初始化 MyFrameworkNetworkUtils 实例
    MyFrameworkNetworkHelper *network = [MyFrameworkNetworkHelper shared];
    
    // 调用 getUrl 方法
    NSString *url = @"http://127.0.0.1:8080/zyb/zhangshan";
    [network getWithNativeUrl:url];
    
    
    // 调用 getUrl 方法
    [network getWithKtorUrl:url];
    
    
    // 初始化 MyFrameworkNetworkUtils 实例
    MyFrameworkLogUtils *logUtils = [[MyFrameworkLogUtils alloc] init];
    
    // 调用 getUrl 方法
    
    [logUtils logTag:@"ios使用KMM的log函数" msg:@"哈哈哈哈哈哈哈哈"];
}


@end
