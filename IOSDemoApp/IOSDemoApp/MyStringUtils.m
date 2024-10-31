//
//  MyStringUtils.m
//  IOSDemoApp
//
//  Created by ZYB on 2024/10/30.
//

#import "MyStringUtils.h"

@implementation MyStringUtils

+ (BOOL)isStringNullOrEmpty:(NSString * _Nullable)string {
    // 检查字符串是否为 null 或空字符串
    return string == nil || [string isEqualToString:@""];
}

@end
